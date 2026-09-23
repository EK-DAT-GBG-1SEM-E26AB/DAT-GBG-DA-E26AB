# Forberedelse af code review

## Beskrivelse

Filmsamlingen er afleveret. I morgen laver grupperne **code review** af hinandens færdige
projekter – **hele gruppe mod hele gruppe** – efter
[review-skemaet](../../projekter/filmsamling/kode-review.md).

I dag forbereder I jer. **Der er ingen underviser i dag.** Siden her fortæller jer alt, hvad I skal
gøre – læs den igennem sammen i gruppen, før I går i gang.

Et godt review kræver, at man har læst koden **før** mødet. Sidder man og ser den for første gang,
mens programmørerne kigger med, når man kun at kommentere det, der springer i øjnene – typisk
indrykning og navne. Har man læst den i ro og mag, kan man stille de gode spørgsmål: *"Hvorfor
ligger sorteringen i `Controller` og ikke i `MovieCollection`?"*

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* clone et andet repository, åbne det i IntelliJ og finde hashen på en bestemt commit
* køre et ukendt program og dets tests og vurdere resultatet
* læse en anden gruppes kode systematisk efter et skema
* formulere konkrete fund og spørgsmål med klasse, metode og linjenummer
* skelne mellem et problem i koden og en anden, lige så god løsning

## Se disse videoer før undervisningen:

Ingen video i dag. Læs i stedet [review-skemaet](../../projekter/filmsamling/kode-review.md) –
især afsnittet [Torsdag 05-11: forberedelse](../../projekter/filmsamling/kode-review.md#torsdag-05-11-forberedelse).

## Læs nedenstående før undervisningen

---

### Hvem skal I reviewe?

Underviseren parrer grupperne. Parringen står der, hvor underviseren har lagt den – i itslearning
eller Teams.

**Kan I ikke finde ud af, hvilken gruppe I skal reviewe?** Så review den **samme gruppe som ved
øvelsen mandag 02-11**, og skriv til underviseren, at I gør det.

Når I kender den anden gruppe, skal I bruge **linket til deres repository**. Det står i deres
`README.md`, eller I kan spørge dem direkte. De sidder med præcis samme opgave i dag.

---

### Den rigtige version: `main` på GitHub, når reviewet starter

I reviewer den version, der ligger på **`main` på GitHub, når reviewet starter** i morgen. I dag
forbereder I jer på den version, der ligger der nu.

**Clone** repositoriet i IntelliJ: **File → New → Project from Version Control**, og indsæt linket.
I skal ikke committe noget i den andens repository.

I morgen, lige før reviewet starter:

1. Hent den nyeste version med **Git → Pull** i IntelliJ, så I har det, der ligger på `main`.
2. Åbn repositoriet på GitHub, og klik på antallet af **commits** over fillisten. Den øverste er
   den nyeste.
3. Notér de første **7 tegn** af dens hash – den står til højre, fx `a3f9c21`. Den skal i skemaet.

> **Pas på, I ikke kommer til at ændre i jeres eget projekt.** Clone den anden gruppes projekt i en
> **ny** mappe og et **nyt** IntelliJ-vindue. Luk jeres eget projekt, hvis I er i tvivl om, hvilket
> vindue I er i.

---

### Kør programmet og testene

Det første afsnit i skemaet efter *GitHub* er *Kør programmet og testene*. Gør det, **før** I læser
koden:

* Vent, til IntelliJ har indlæst Maven-projektet. Er `org.junit` rødt, så klik på **Reload All
  Maven Projects** i **Maven**-vinduet.
* Kør `Main`. Prøv hvert menuvalg. Skriv bogstaver, hvor der skal stå tal.
* Højreklik på `src/test/java` → **Run 'All Tests'**. Hvor mange tests er der? Er de grønne?

**Kan projektet ikke åbnes eller køre?** Skriv præcist ned, hvad der sker – fejlbeskeden, og hvad I
har prøvet. Det er et vigtigt fund i sig selv. Læs så koden alligevel; det meste af skemaet kan
besvares uden at køre programmet.

---

### Læs koden efter skemaet

Kopiér skemaet: åbn [kode-review.md](../../projekter/filmsamling/kode-review.md) på GitHub, klik på
**Raw**, og kopiér teksten ind i en ny fil hos jer selv. Udfyld den, efterhånden som I læser.

**Del arbejdet**, men gå fundene igennem **sammen**, før I går hjem. Et forslag til en gruppe på tre:

| Person | Læser |
| --- | --- |
| 1 | *GitHub*, *Packages*, `UserInterface` |
| 2 | `Movie`, `MovieCollection`, *Comparator-klasserne* |
| 3 | `Controller`, *FileHandler – filen*, *Tests* |

Er I to, så tag halvdelen hver. Afsnittet *Klasser* skal gentages for hver klasse – brug de
spørgsmål, der giver mening, og slet dem, der ikke passer.

**Sådan læser man kode, man ikke selv har skrevet:**

1. **Start udefra.** Kig på klassediagrammet i `docs`. Fold alle metoder sammen i IntelliJ
   (<kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>-</kbd>, på Mac <kbd>Cmd</kbd>+<kbd>Shift</kbd>+<kbd>-</kbd>),
   og læs kun navnene. Kan I regne ud, hvad hver metode gør?
2. **Følg én user story.** Vælg fx "Vis film sorteret efter årstal", og følg den fra menuen i
   `UserInterface` ned gennem `Controller` til comparatoren. <kbd>Ctrl</kbd>+klik (Mac:
   <kbd>Cmd</kbd>+klik) på et metodekald hopper til metoden.
3. **Brug debuggeren**, hvis I ikke forstår, hvad der sker – sæt et breakpoint og gå skridt for
   skridt, som på [debugger-dagen 14-09](../../38/01_man_2026-09-14/README.md).
4. **Skriv fund ned med det samme** – med klasse, metode og linjenummer. I kan ikke huske det i
   morgen.

---

### Fund, forslag og spørgsmål

Ikke alt, der er anderledes end jeres egen løsning, er et problem. Sortér det, I finder, i tre
bunker:

| Slags | Eksempel | Skriv det sådan |
| --- | --- | --- |
| **Fund** – noget, der bryder et krav eller en regel | `System.out.println` i `MovieCollection` linje 58 | som et 💣-svar i skemaet |
| **Forslag** – noget, der kunne være bedre | `getList()` kunne hedde `getMovies()` | under *Yderligere kommentarer* |
| **Spørgsmål** – noget, I ikke forstår | *"Hvorfor gemmer I kun, når brugeren afslutter?"* | som spørgsmål til i morgen |

Spørgsmålene er ofte de mest værdifulde. Der kan være en god grund, I ikke kender – eller også
opdager programmørerne selv, at der ikke var det.

**Husk det gode.** Find mindst **tre** ting, der er gjort godt: et godt navn, en god test, en
elegant løsning. Skriv dem under *Det er særlig godt*. Det er lige så vigtigt for den anden gruppe
at vide, hvad de skal blive ved med.

> **Hold jer til koden.** Brugerfladen og menuens udseende er ikke det, vi kigger efter. Og skriv om
> koden – ikke om personerne: *"Metoden er 80 linjer lang"*, ikke *"I skriver lange metoder"*.

---

### Hvis jeres gruppe er bagud

Nåede I ikke at aflevere i går, eller mangler noget af det, der skal til?

* **Ikke afleveret?** Opgaven i itslearning lukkede ved deadline – der er ingen aflevering efter.
  Skriv til underviseren i dag, hvad der skete. Filmsamling skal være afleveret for at kunne
  indstilles til eksamen, så vent ikke.
* **Mangler et af de fem krav?** Så skulle I have afleveret det, I havde, og skrevet i
  afleveringen, hvad der mangler. Har I ikke gjort det, så skriv det til underviseren i dag.
* **Forbered reviewet alligevel.** Den anden gruppe har krav på et ordentligt review af deres kode,
  uanset hvordan det gik med jeres. Gør forberedelsen færdig først, og brug så resten af dagen på
  jeres egne rester.
* **Er I kun én fra gruppen i dag?** Lav forberedelsen alene, så godt du kan, og del dine noter med
  resten af gruppen.

Fredagens review foregår på den version, der ligger på `main` på GitHub, når reviewet starter. Det
er stadig værd at rette i jeres eget projekt – men gør forberedelsen færdig først.

---

### Til sidst: tjek jeres eget projekt

Brug den sidste halve time på at kigge på **jeres eget** repository med de samme briller. I morgen
skal I forklare jeres kode til den anden gruppe. Alle i gruppen skal kunne svare på spørgsmål om
**hele** programmet – også de klasser, man ikke selv har skrevet.

* Hvilke klasser har I ikke selv skrevet? Læs dem nu.
* Hvilke af skemaets 💣-spørgsmål ville I selv svare **ja** til? Så er I forberedt på at forklare
  hvorfor – eller at sige, hvad I ville gøre anderledes.

---

## Det vigtigste at tage med

* der er **ingen underviser** i dag – siden her er jeres plan
* review den version, der ligger på **`main` på GitHub, når reviewet starter** – notér de 7 første tegn af hashen
* clone i en **ny** mappe og et **nyt** vindue; ændr intet i den anden gruppes repository
* kør programmet og testene **før** I læser koden
* del klasserne mellem jer, men gå fundene igennem **sammen**
* sortér i fund, forslag og spørgsmål – og find mindst tre ting, der er gjort godt
* er I bagud: skriv til underviseren i dag – og forbered reviewet alligevel

## Aktiviteter i undervisningen

### 1. Kom i gang (første halve time)

Læs siden igennem sammen. Find ud af, hvilken gruppe I skal reviewe, find deres repository, og
clone det.

### 2. Kør og læs (formiddag)

Clone projektet, kør programmet og testene, og fordel klasserne mellem jer. Udfyld hver jeres del
af skemaet.

### 3. Saml fundene (efter frokost)

Gå hele skemaet igennem sammen. Bliv enige om:

* de tre ting, der er **særlig gode**
* de tre **vigtigste** ting at rette
* de spørgsmål, I vil stille i morgen

Skriv det ind i skemaet under *Yderligere kommentarer*. Skemaet bliver færdigt i morgen, når I har
hørt programmørernes svar.

### 4. Jeres eget projekt (sidste halve time)

Se [Til sidst: tjek jeres eget projekt](#til-sidst-tjek-jeres-eget-projekt). I morgen er det jer,
der skal forklare.
