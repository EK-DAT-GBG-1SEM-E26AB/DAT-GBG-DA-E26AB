# Projektarbejde, sprint 1: gem i fil og robusthed

## Beskrivelse

Dagens undervisning er afsat til [Delfinen](../../projekter/delfinen/readme.md), sprint 1.

Dagens tema er to krav, der gælder hele programmet:

* **F14:** Alle data gemmes i tekstfiler og indlæses, når programmet starter. Intet må gå tabt.
* **Robusthed:** Programmet må ikke gå ned på forkert input, og mangler datafilen, starter det med
  en tom klub.

I kan det meste fra [Filmsamling del 6](../../projekter/filmsamling/del-6-exceptions.md) og
[del 7](../../projekter/filmsamling/del-7-filer.md). Det nye i Delfinen er, at der er **flere
slags data, der hænger sammen**: resultater hører til en svømmer, og en svømmer har discipliner.
Hvordan gemmer man det i en tekstfil, og hvordan bliver det til de rigtige objekter igen?

Eksemplet er Kulturhuset: events og de billetter, der er solgt til dem.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* gemme objekter, der **peger på hinanden**, ved at gemme et **id** i stedet for hele objektet
* indlæse filerne i den **rigtige rækkefølge** og finde objekterne igen ud fra id'et
* gemme og indlæse en **enum** som tekst
* **springe ødelagte linjer over** og fortælle brugeren om det
* starte med tomme data, når filen **ikke findes**
* læse tal og datoer fra brugeren, så programmet **aldrig går ned** på forkert input
* holde **eksempeldata** i repoet uden at få merge-konflikter

## Se disse videoer før undervisningen:

Ingen ny video. Genlæs [Filmsamling del 7 → Det skal I bruge](../../projekter/filmsamling/del-7-filer.md#det-skal-i-bruge)
og [Filmsamling del 6 → Spørg igen, indtil svaret er gyldigt](../../projekter/filmsamling/del-6-exceptions.md#spørg-igen-indtil-svaret-er-gyldigt).

## Læs nedenstående før undervisningen

---

### Objekter, der peger på hinanden

I Kulturhuset hører en billet til et event. I programmet har `Ticket` en attribut `Event event`, der
peger på selve `Event`-objektet.

Men i en tekstfil kan man ikke skrive et objekt. Man kan kun skrive tekst. Løsningen er at give
hvert event et **unikt id** og gemme **id'et** i billetlinjen:

```text
id;name;date
1;Julekoncert;2026-12-10
2;Stand-up aften;2026-12-12
```

```text
id;eventId;type;purchaseDate
1;1;PRESALE;2026-12-01
2;1;STUDENT;2026-11-30
3;2;DOOR;2026-12-12
```

Billet 1 og 2 er til event 1, billet 3 er til event 2. Id'et er **nøglen**, der binder de to
filer sammen.

I Delfinen har I allerede et unikt id: **medlemsnummeret** (F3). Det er oplagt at bruge, når en
linje i en fil skal pege på et medlem.

### Gem

`FileHandler` skriver én fil pr. slags objekt. For billetterne skrives eventets **id**:

```java
    public void save(Venue venue) throws FileNotFoundException {
        PrintStream events = new PrintStream(new File(eventFile));
        events.println(EVENT_HEADER);
        for (Event event : venue.getEvents()) {
            events.println(event.getId() + ";" + event.getName() + ";" + event.getDate());
        }
        events.close();

        PrintStream tickets = new PrintStream(new File(ticketFile));
        tickets.println(TICKET_HEADER);
        for (Ticket ticket : venue.getTickets()) {
            // Billetten gemmer kun eventets id – ikke hele eventet
            tickets.println(ticket.getId() + ";" + ticket.getEvent().getId() + ";"
                    + ticket.getType() + ";" + ticket.getPurchaseDate());
        }
        tickets.close();
    }
```

`ticket.getType()` er en enum. Når den sættes sammen med en `String`, bliver den til sit navn,
fx `PRESALE`. `getPurchaseDate()` bliver til `2026-12-01`, som `LocalDate.parse` kan læse igen.

### Indlæs: i den rigtige rækkefølge

Når billetterne læses, skal deres event **allerede findes**, så man kan slå det op ud fra id'et.
Derfor læses **events først**:

```java
    // Events først: billetterne skal kunne finde deres event ud fra id'et
    public Venue load() throws FileNotFoundException {
        Venue venue = new Venue();
        skippedLineCount = 0;

        Scanner events = new Scanner(new File(eventFile));
        if (events.hasNextLine()) {
            events.nextLine();   // spring overskriften over
        }
        while (events.hasNextLine()) {
            Event event = parseEvent(events.nextLine());
            if (event == null) {
                skippedLineCount++;
            } else {
                venue.addEvent(event);
            }
        }
        events.close();

        File file = new File(ticketFile);
        if (!file.exists()) {
            return venue;   // events, men ingen solgte billetter endnu
        }
        Scanner tickets = new Scanner(file);
        if (tickets.hasNextLine()) {
            tickets.nextLine();
        }
        while (tickets.hasNextLine()) {
            Ticket ticket = parseTicket(tickets.nextLine(), venue);
            if (ticket == null) {
                skippedLineCount++;
            } else {
                venue.addTicket(ticket);
            }
        }
        tickets.close();
        return venue;
    }
```

Og selve opslaget, når en billetlinje bliver til et objekt:

```java
    private Ticket parseTicket(String line, Venue venue) {
        String[] fields = line.split(";", -1);
        if (fields.length != 4) {
            return null;
        }
        try {
            int id = Integer.parseInt(fields[0]);
            Event event = venue.findEvent(Integer.parseInt(fields[1]));
            if (event == null) {
                return null;   // billetten peger på et event, der ikke findes
            }
            TicketType type = TicketType.valueOf(fields[2]);
            LocalDate purchaseDate = LocalDate.parse(fields[3]);
            return new Ticket(id, event, type, purchaseDate);
        } catch (IllegalArgumentException e) {
            // Dækker både NumberFormatException og en ukendt billettype i valueOf
            return null;
        } catch (DateTimeParseException e) {
            return null;
        }
    }
```

Tre ting at lægge mærke til:

* `venue.findEvent(id)` finder **det samme** `Event`-objekt, som blev indlæst lige før. Billetten
  peger på det rigtige event, ikke på en kopi.
* `TicketType.valueOf("PRESALE")` laver teksten om til enum-værdien igen. Står der noget, der ikke
  er en billettype, kaster den en `IllegalArgumentException`, og linjen springes over.
* En linje, der peger på et event, som ikke findes, springes også over og tælles med. Den slags
  sker, hvis nogen har rettet i filen i hånden.

### Hvornår skal der gemmes?

Det sikreste er at gemme **efter hver ændring**. Så er intet tabt, hvis programmet lukkes, eller
strømmen går. `Controller` ved, hvornår noget ændres, så det er den, der kalder `save`:

```java
    // Gemmer med det samme – så er intet tabt, hvis programmet lukkes
    public Event addEvent(String name, LocalDate date) throws FileNotFoundException {
        Event event = venue.addEvent(name, date);
        fileHandler.save(venue);
        return event;
    }
```

Med få hundrede medlemmer tager det ingen tid at skrive hele filen igen. Og domæneklasserne ved
stadig intet om filer.

### Mangler filen?

Første gang programmet startes, findes filen ikke. Så skal programmet starte med tomme data og
sige det, ikke gå ned. `load()` kaster `FileNotFoundException`, og `UserInterface` fanger den, som i
Filmsamling:

```java
    private void loadData() {
        try {
            controller.load();
            System.out.println(controller.getEvents().size() + " events er indlæst.");
            int skipped = controller.getSkippedLineCount();
            if (skipped > 0) {
                System.out.println("Advarsel: " + skipped + " linje(r) kunne ikke læses og er sprunget over.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Der er ingen gemte data endnu – du starter med et tomt kulturhus.");
        }
    }
```

---

### Eksempeldata i repoet

Delfinen kræver **eksempeldata** i repoet: mindst 10 medlemmer af alle slags og nogle resultater,
så man kan afprøve programmet med det samme. Men når fire personer kører programmet og opretter
testmedlemmer, ændres datafilen hos alle fire, og så giver den merge-konflikter.

**Aftal i gruppen:**

1. **Én** person laver eksempeldata, når formatet ligger fast, og merger det.
2. **Commit aldrig datafilen ved et uheld.** Se efter i commit-vinduet, før du committer. Står
   datafilen der, og har du ikke ændret eksempeldata med vilje, så fjern fluebenet ud for den,
   eller rul ændringen tilbage: højreklik → **Rollback** i IntelliJ, eller
   `git restore members.csv` i terminalen.
3. **Testene bruger deres egen fil**, som de sletter bagefter, som i Filmsamling. Så rører en test
   aldrig ved eksempeldata.

Skifter I filformat undervejs, så skal eksempeldata rettes i **samme** branch, så `main` altid kan
læse sin egen fil.

---

### Robusthed: programmet må ikke gå ned

Gå jeres menu igennem, og find hvert sted, brugeren taster noget. For hvert sted: hvad sker der,
hvis hun taster noget forkert?

| Input | Hvad der skal ske |
|---|---|
| Bogstaver, hvor der skal stå et tal | *"abc er ikke et tal. Prøv igen."* |
| En dato, der ikke findes: 31-11-2026 | *"er ikke en gyldig dato"*, og der spørges igen |
| Et medlemsnummer, der ikke findes | *"Der er intet medlem med nummer 99."* Ingen `NullPointerException` |
| Et tomt navn | Spørg igen |
| Et navn med `;` | Afvis det: det ville ødelægge filformatet |
| En tid som `1:05.32`, når I forventer `65.32` (eller omvendt) | Tydelig besked om, hvordan tiden skal skrives |

Samme mønster som i Filmsamling: **spørg igen, indtil svaret er gyldigt**, og saml det i små
metoder i `UserInterface`, så I kun skriver det én gang:

```java
    // Spørger igen, indtil brugeren har tastet et helt tal
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\" er ikke et tal. Prøv igen.");
            }
        }
    }
```

#### 31-11-2026 er ikke en dato

Der er en fælde med datoer. `DateTimeFormatter.ofPattern("dd-MM-yyyy")` **accepterer**
`31-11-2026` og laver den stille og roligt om til 30-11-2026. Brugeren får ingen fejl, men en
anden dato, end hun tastede. Det er et klart brud på "robusthed".

Brug `ResolverStyle.STRICT`, og skriv året som `uuuu` (med `STRICT` afvises **alle** datoer, hvis
året står som `yyyy`, som der står i Filmsamlingens frivillige opgave *31-02-2026*):

```java
    // STRICT afviser datoer, der ikke findes (fx 31-11-2026). Med STRICT skal året skrives uuuu, ikke yyyy
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
```

```java
    // Spørger igen, indtil brugeren har tastet en gyldig dato
    private LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("\"" + input + "\" er ikke en gyldig dato. Skriv fx 10-12-2026.");
            }
        }
    }
```

En kørsel:

```text
Dato (dd-mm-åååå): 31-11-2026
"31-11-2026" er ikke en gyldig dato. Skriv fx 10-12-2026.
Dato (dd-mm-åååå): 10-12-2026
Event nr. 1 er oprettet.
```

Importér `java.time.format.ResolverStyle` og `java.time.format.DateTimeParseException`.

---

### Test filhåndteringen

Som i Filmsamling får `FileHandler` sine egne tests, med sine egne filer, der slettes efter hver
test med `@AfterEach`. Den vigtigste test er, at **det, der gemmes, kommer rigtigt tilbage**, også
forbindelserne:

```java
    @Test
    void savedVenueCanBeLoadedAgain() throws FileNotFoundException {
        Venue venue = new Venue();
        Event concert = venue.addEvent("Julekoncert i Æblehaven", LocalDate.of(2026, 12, 10));
        venue.addTicket(new Ticket(1, concert, TicketType.STUDENT, LocalDate.of(2026, 11, 30)));

        fileHandler.save(venue);
        Venue loaded = fileHandler.load();

        assertEquals(1, loaded.getEvents().size());
        assertEquals("Julekoncert i Æblehaven", loaded.getEvents().get(0).getName());
        assertEquals(1, loaded.getTickets().size());
        Ticket ticket = loaded.getTickets().get(0);
        // Billetten peger på det indlæste event – ikke på en kopi
        assertSame(loaded.getEvents().get(0), ticket.getEvent());
        assertEquals(72, ticket.getPrice());
    }
```

`assertSame` tjekker, at det er **præcis det samme objekt**, ikke bare et, der ligner. Det er
beviset på, at opslaget via id virker.

Skriv også en test med en fil, som testen selv skriver med nogle **ødelagte linjer** (et ukendt id,
en ukendt type, et felt for lidt), og tjek, at de gode linjer bliver indlæst, og at de dårlige
bliver talt.

---

## Det vigtigste at tage med

* objekter, der peger på hinanden, gemmes med et **id**: i Delfinen **medlemsnummeret**
* indlæs i den **rigtige rækkefølge**, og find objektet igen ud fra id'et
* en enum gemmes som sit **navn** og læses igen med `valueOf`
* **gem efter hver ændring**; `Controller` kalder `FileHandler`, domænet ved intet om filer
* mangler filen, starter programmet **tomt og siger det**; ødelagte linjer springes over og tælles
* **commit aldrig datafilen ved et uheld**; testene bruger deres egen fil
* spørg igen ved forkert input, og brug `ResolverStyle.STRICT` med `uuuu`, så 31-11 afvises

## Aktiviteter i undervisningen

### 1. Stand-up (15 min)

I morgen er der check-in. Hvad skal være merget til `main` inden da?

### 2. Filformatet

Er gem og indlæs ikke lavet, så lav det i dag. **Aftal filformatet i gruppen først** (hvilke filer,
hvilke felter, i hvilken rækkefølge), og skriv det i en kommentar øverst i `FileHandler`. Så kan
flere arbejde på det samtidig.

### 3. Robusthedsjagt (30 min)

Byt computer med en anden i gruppen, og prøv at få den andens del af programmet til at **gå ned**.
Brug tabellen ovenfor. Hver gang det lykkes, så skriv det på boardet som en opgave, og ret det.

### 4. Arbejd videre på sprinten

Hvad der ellers står i jeres Sprint Backlog.

### Tjekliste, før I går hjem

- [ ] Programmet gemmer efter hver ændring, og alt er der stadig efter en genstart.
- [ ] Programmet starter uden datafil og siger, at klubben er tom.
- [ ] Programmet går ikke ned på bogstaver, ugyldige datoer eller medlemsnumre, der ikke findes.
- [ ] Alle tests er grønne på `main`.
- [ ] `main` kan køre, og det, I vil vise ved check-in i morgen, er merget.
