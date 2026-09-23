# Delfinen – sprint 2 og check-in

## Beskrivelse

Første hele arbejdsdag i sprint 2 – og sidste check-in med underviseren før afleveringen.

Som ved de to første check-ins er **I i lokalet, og underviseren er online**. Hver gruppe har et
kort møde. Hvornår jeres gruppe er på, får I at vide af underviseren. Resten af dagen arbejder I
på sprint 2.

Dagens lille kvalitetstema er **robusthed**: programmet må ikke gå ned på forkert input. Det er et
af [de ikke-funktionelle krav](../../projekter/delfinen/readme.md#ikke-funktionelle-krav), og det er
nemt at glemme, når man har travlt med at nå træner-delen.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* præsentere jeres plan for sprint 2 kort og præcist: sprintmål, sprint backlog og hvad der ryger
  først
* forklare, hvad I ændrer efter kode review og retrospektiv
* afprøve jeres eget program systematisk med forkert input
* fange forkert input dér, hvor brugeren kan få besked, og spørge igen

## Se disse videoer før undervisningen:

Ingen ny video. Har du brug for at genopfriske exceptions, så se
[exception handling](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=9h5m29s)
(til: 09:13:28) fra kursusrækken.

## Læs nedenstående før undervisningen

---

### Check-in: hav det her klar

Mødet er kort, så vær forberedt. Sid samlet ved **én skærm**, så alle fire er med, og hav boardet
åbent. Fra [Scrum i Delfinen](../../projekter/delfinen/scrum.md#check-in-med-underviseren--18-11-25-11-og-02-12):

| Punkt | Vis |
|---|---|
| **Sprintmål** | Sætningen fra i går – og hvad der skal til, for at den er sand 08-12 |
| **Sprint backlog** | Kortene i *Sprint Backlog* og *I gang*, med estimater og navne på |
| **Kode review** | De vigtigste fund fra issuet – og hvilke af dem I retter |
| **Retrospektiv** | De (højst) tre ændringer, I besluttede |
| **Plan B** | Hvad I dropper, hvis tiden bliver knap – i rækkefølge |
| **Aflevering** | Hvornår dokumentationen og ITF-diasshowet bliver lavet, og af hvem |

Og så **jeres spørgsmål**. Skriv dem ned inden mødet – om casen, om designet, om noget, I er
uenige om i gruppen. Det er den sidste gang, underviseren ser jeres plan, før I afleverer.

> **Kører programmet?** Vis gerne kort det, der er kommet til siden sidste check-in – men brug ikke
> hele mødet på en demo. Planen er vigtigere i dag.

---

### Dagens kvalitetstema: vælt jeres eget program

Kravet siger: *"Programmet må ikke gå ned på forkert input (bogstaver, hvor der skal stå et tal, en
ugyldig dato, et medlemsnummer, der ikke findes). Mangler datafilen, starter programmet med en tom
klub."*

I jagtede nedbrud [24-11](../../48/02_tir_2026-11-24/README.md#robusthed-programmet-må-ikke-gå-ned)
i sprint 1. Siden er der kommet ny kode til – ofte træner-delen – så tag jagten igen. Den bedste
måde at finde ud af, om det holder, er at **prøve at vælte det**. Byt computer med en anden i
gruppen, og gå listen igennem. Skriv ned, hvad der skete.

| # | Prøv | Forventet |
|---|---|---|
| 1 | Skriv `abc` i hovedmenuen | En besked – og menuen igen |
| 2 | Skriv et menuvalg, der ikke findes, fx `99` | En besked – og menuen igen |
| 3 | Skriv `31-13-2010` som fødselsdato | En besked – og spørg igen |
| 4 | Skriv `31-02-2010` som fødselsdato | Afvist (se nedenfor) |
| 5 | Skriv en fødselsdato i fremtiden | Afvist |
| 6 | Opret et medlem uden navn (tryk bare Enter) | Afvist |
| 7 | Registrér betaling for medlemsnummer `9999` | *"Medlemmet findes ikke"* |
| 8 | Registrér en træningstid som `hurtig` | En besked – og spørg igen |
| 9 | Registrér en tid i en disciplin, svømmeren ikke er aktiv i | Afvist (se [reglerne](../../projekter/delfinen/readme.md#reglerne-gjort-præcise)) |
| 10 | Vis top 5 i en disciplin, hvor ingen har en tid | En tom liste eller en besked – ikke en fejl |
| 11 | Omdøb datafilen, og start programmet | En tom klub |
| 12 | Skriv et navn med `;` (eller det tegn, jeres fil bruger som skilletegn) | Afvist – ellers ødelægger det filformatet |

Hver gang programmet går ned, så lav et kort på boardet – med præcis det, I tastede.

#### Hvor skal fejlen fanges?

I lærte det i [Filmsamling del 6](../../projekter/filmsamling/del-6-exceptions.md):

* **Brugerfladen** fanger forkert input (`NumberFormatException`, `DateTimeParseException`) og
  spørger igen – som `readInt` og `readDate` fra
  [24-11](../../48/02_tir_2026-11-24/README.md#robusthed-programmet-må-ikke-gå-ned). Se også
  [Spørg igen, indtil svaret er gyldigt](../../projekter/filmsamling/del-6-exceptions.md#spørg-igen-indtil-svaret-er-gyldigt)
  og [Datoer fra brugeren](../../projekter/filmsamling/del-6-exceptions.md#datoer-fra-brugeren).
* **Domænet** afviser ugyldige værdier med `throw` – fx en tid i en disciplin, svømmeren ikke er
  aktiv i. Brugerfladen fanger den og viser beskeden.
* Et medlemsnummer, der ikke findes, er ikke en fejl i programmet – det er noget, brugeren kan
  gøre. En metode, der leder efter et medlem, kan returnere `null`, og brugerfladen tjekker det med
  en `if`.

Har I skrevet `readInt` og `readDate` én gang i brugerfladen, skal rettelserne kun laves **ét** sted.
Står `Integer.parseInt` spredt ud over hele `UserInterface`, så saml det nu.

#### 31-02-2010

Prøv punkt 4 på listen. Med `DateTimeFormatter.ofPattern("dd-MM-yyyy")` bliver `31-02-2010` **ikke**
afvist – Java retter den stille og roligt til `28-02-2010`. Løsningen – en **streng** formatter med
`ResolverStyle.STRICT` og `uuuu` i stedet for `yyyy` – står under
[31-11-2026 er ikke en dato](../../48/02_tir_2026-11-24/README.md#31-11-2026-er-ikke-en-dato) fra
24-11. Har I ikke fået den med, så gør det nu.

> Bruger I den samme formatter til at **gemme** datoer i filen, så tjek, at filen stadig kan
> indlæses bagefter. `dd-MM-uuuu` skriver datoer præcis som `dd-MM-yyyy` gjorde.

---

### Tjekliste for dagen

- [ ] Check-in holdt – og det, I fik at vide, står på boardet
- [ ] Stand-up holdt – også for dem, der er hjemme
- [ ] Listen [*Vælt jeres eget program*](#dagens-kvalitetstema-vælt-jeres-eget-program) gået
      igennem, og hvert nedbrud står som et kort
- [ ] Mindst én user story fra sprint backloggen er flyttet til *Færdig*
- [ ] Alt, der virker, er merget til `main` inden I går hjem

---

## Det vigtigste at tage med

* check-in handler om **planen**: sprintmål, sprint backlog, plan B og afleveringen
* skriv jeres spørgsmål ned **før** mødet
* prøv at vælte jeres eget program – med de input, en rigtig bruger kunne finde på
* **brugerfladen** fanger forkert input og spørger igen; **domænet** afviser ugyldige værdier med
  `throw`
* `31-02-2010` bliver ikke afvist, medmindre formatteren er `STRICT` – og så med `uuuu`

## Aktiviteter i undervisningen

### 1. Daily stand-up

Foran boardet, 10–15 minutter. Hvad lavede jeg i går? Hvad laver jeg nu? Er der noget, der
forhindrer mig?

### 2. Check-in med underviseren

Gør jer klar efter [Check-in: hav det her klar](#check-in-hav-det-her-klar). Når det er jeres tur,
sidder alle fire ved én skærm.

### 3. Vælt jeres eget program

Byt computer, og gå [listen](#dagens-kvalitetstema-vælt-jeres-eget-program) igennem. Lav kort på
boardet for det, der går galt.

### 4. Sprint 2

Arbejd videre på sprint backloggen. Merge ofte.
