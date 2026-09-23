# FURPS, datoer og design-review

## Beskrivelse

Filmsamlingen kan nu det hele: oprette, vise, søge, rette og slette – og den har tests. I dag
træder vi et skridt tilbage og stiller tre spørgsmål:

1. **Hvor godt** er programmet? Ikke bare *hvad* kan det, men er det let at bruge, kan man stole på
   det, og er det let at ændre? Til det bruger vi huskereglen **FURPS**.
2. **Hvornår** så jeg sidst den film? Programmet skal kunne regne med **datoer** – og til det har
   Java klassen `LocalDate`.
3. **Holder koden?** Før vi bygger mere på, gennemgår I jeres egen kode efter en tjekliste – et
   **design-review**.

Alle tre hører til [Filmsamling del 5½](../../projekter/filmsamling/del-5-datoer.md). Denne side
forklarer begreberne; opgaven står i projektet.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* forklare de fem bogstaver i **FURPS** og give et eksempel på hvert
* skelne mellem **funktionelle krav** og **kvalitetskrav**
* omskrive et vagt krav til et krav, der kan **afprøves**
* oprette datoer med `LocalDate.now()`, `LocalDate.of(...)` og `LocalDate.parse(...)`
* lægge dage, måneder og år til en dato – og forklare, hvorfor resultatet skal gemmes (**immutable**)
* sammenligne datoer med `isBefore`, `isAfter` og `isEqual` og regne dage mellem dem med
  `ChronoUnit.DAYS.between`
* formatere og indlæse datoer med `DateTimeFormatter` – og undgå fælden med `mm`
* bruge `null` til at betyde "ingen dato endnu" uden at få en `NullPointerException`
* gennemgå kode efter en tjekliste og give konkret, venlig feedback

## Se disse videoer før undervisningen:

* [dates & times](https://www.youtube.com/watch?v=xTtL8E4LzTQ&t=10h11m42s) (til: 10:20:24)

Videoen viser også `LocalTime` og `LocalDateTime` (med klokkeslæt). Vi bruger kun `LocalDate` –
datoer uden klokkeslæt.

## Læs nedenstående før undervisningen

---

# Del 1: FURPS

### Hvad programmet kan – og hvor godt

Indtil nu har kravene til filmsamlingen været **user stories**: *som filmentusiast vil jeg kunne
søge efter film ...* De beskriver, **hvad** programmet skal kunne. Det kaldes **funktionelle krav**.

Men to programmer kan gøre præcis det samme og alligevel være vidt forskellige at bruge og at
arbejde videre på. Det ene går ned, når man taster forkert; det andet siger venligt "prøv igen".
Det ene har tests og klare klasser; det andet er én lang metode. Den slags krav kaldes
**kvalitetskrav** (eller *ikke-funktionelle krav*).

**FURPS** er en huskeregel for, hvilke slags krav man bør tænke over:

| | Engelsk | Spørgsmålet | Eksempel fra en webshop |
|---|---|---|---|
| **F** | Functionality | Hvad kan programmet? | Man kan lægge varer i kurven og betale |
| **U** | Usability | Er det let at bruge? | Man kan gennemføre et køb uden at oprette en konto |
| **R** | Reliability | Kan man stole på det? | En betaling bliver aldrig trukket to gange, heller ikke hvis siden genindlæses |
| **P** | Performance | Er det hurtigt og sparsomt nok? | Søgeresultatet vises på under ét sekund |
| **S** | Supportability | Er det let at vedligeholde og ændre? | Betalingen ligger i én klasse, så udbyderen kan skiftes |

**F** er user stories. **URPS** er det, der gør forskellen på et program, der "bare virker", og et
program, man gider bruge og gider arbejde videre på.

> Man ser også **FURPS+**. Plusset dækker over begrænsninger, man ikke selv vælger – fx "skal
> skrives i Java" eller "skal køre på skolens computere".

### Et krav skal kunne afprøves

"Programmet skal være brugervenligt" er et ønske, ikke et krav. Hvordan skulle man nogensinde
afgøre, om det er opfyldt? Et godt krav er så konkret, at man kan **teste** det – med en unit test
eller ved at prøve programmet:

| Vagt | Kan afprøves |
|---|---|
| Programmet skal være brugervenligt. | Ukendte menuvalg giver en besked, og menuen vises igen. |
| Programmet må ikke gå ned. | Bogstaver, hvor der forventes et tal, giver en besked og et nyt forsøg. |
| Programmet skal være hurtigt. | Søgning i 10.000 film tager under ét sekund. |
| Koden skal være god. | `System.out` og `Scanner` bruges kun i `UserInterface`. |

Læg mærke til, at det sidste krav er et **S**-krav – og at I har overholdt det siden Adventure.

### FURPS i filmsamlingen

I skal skrive jeres egen FURPS-liste til filmsamlingen i `docs/furps.md` – mindst ét konkret krav
pr. bogstav, og for hvert: er det opfyldt, og hvor? Formatet og et eksempel står i
[del 5½, afsnit 1](../../projekter/filmsamling/del-5-datoer.md#1-furps). Filen er et af de fem krav
til den endelige [aflevering](../../projekter/filmsamling/readme.md#aflevering).

Nogle af kravene er ikke opfyldt endnu – og det er fint. Flere af projektets kommende dele handler
netop om dem: robusthed (**R**) i del 6, at gemme kun når der er ændringer (**P**) i del 7. Skriv
"Nej – del 6", og ret det, når I når dertil.

---

# Del 2: Datoer med LocalDate

### Hvorfor ikke bare et tal eller en tekst?

Man kunne gemme en dato som teksten `"26-10-2026"` eller som tre `int`s. Men prøv så at svare på:
hvor mange dage er der fra 20. oktober til 3. november? Hvilken dato er 30 dage efter 15.
december? Er 29. februar 2026 en rigtig dato?

Måneder har forskellig længde, år skifter, og hvert fjerde år er der skudår. Det er let at gøre
forkert og kedeligt at gøre rigtigt. Java har gjort det for os: klassen `LocalDate` i pakken
`java.time`. En `LocalDate` er en dato – år, måned og dag – **uden** klokkeslæt.

### Opret en dato

```java
LocalDate today = LocalDate.now();                      // dagens dato
LocalDate premiere = LocalDate.of(1975, 6, 20);         // år, måned, dag
LocalDate review = LocalDate.parse("2026-10-26");       // ISO-format: åååå-mm-dd
```

`LocalDate` har ingen `new` – man bruger `now`, `of` eller `parse`. Prøver man at lave en dato, der
ikke findes, siger Java fra med det samme:

```java
LocalDate.of(2026, 2, 29);
```

```text
Exception in thread "main" java.time.DateTimeException: Invalid date 'February 29' as '2026' is not a leap year
```

### Spørg datoen

```java
System.out.println(premiere);                           // 1975-06-20
System.out.println(premiere.getYear());                 // 1975
System.out.println(premiere.getMonthValue());           // 6
System.out.println(premiere.getDayOfWeek());            // FRIDAY
```

`println` af en `LocalDate` giver altid ISO-formatet `åååå-mm-dd`. Til brugeren vil vi have det på
dansk – det kommer om lidt.

### Regn med datoer

```java
LocalDate nextWeek = review.plusDays(7);
System.out.println(nextWeek);                           // 2026-11-02
System.out.println(review.minusMonths(1));              // 2026-09-26
```

Der er `plusDays`, `plusWeeks`, `plusMonths`, `plusYears` og de tilsvarende `minus...`. De holder
selv styr på månedsskift, årsskift og skudår:

```java
System.out.println(LocalDate.of(2026, 12, 31).plusDays(1));    // 2027-01-01
System.out.println(LocalDate.of(2026, 1, 31).plusMonths(1));   // 2026-02-28
System.out.println(LocalDate.of(2028, 1, 31).plusMonths(1));   // 2028-02-29 (skudår)
```

### En dato kan ikke ændres

`LocalDate` er **immutable**: når en dato først er lavet, kan den ikke ændres. `plusDays` ændrer
altså **ikke** datoen – den returnerer en **ny** dato. Glemmer man at gemme den, sker der ingenting:

```java
LocalDate date = LocalDate.of(2026, 10, 26);
date.plusDays(7);                 // FEJL: resultatet bliver smidt væk
System.out.println(date);         // 2026-10-26 – uændret!

date = date.plusDays(7);          // rigtigt: gem den nye dato
System.out.println(date);         // 2026-11-02
```

Det er den samme regel som for `String`: `title.toLowerCase()` ændrer ikke `title`.

### Sammenlign datoer

```java
System.out.println(premiere.isBefore(review));          // true
System.out.println(review.isAfter(nextWeek));           // false
System.out.println(review.isEqual(LocalDate.of(2026, 10, 26)));   // true
```

Brug **ikke** `<`, `>` og `==` på datoer. `<` og `>` kompilerer slet ikke, og `==` tjekker, om det er
det **samme objekt** – ikke om det er den samme dag.

### Hvor mange dage imellem?

```java
long days = ChronoUnit.DAYS.between(review, nextWeek);
System.out.println(days);                               // 7
System.out.println(ChronoUnit.DAYS.between(nextWeek, review));    // -7
System.out.println(ChronoUnit.YEARS.between(premiere, review));   // 51
```

`ChronoUnit.DAYS.between(fra, til)` tæller dagene **fra** den første **til** den anden. Bytter man
om, bliver resultatet negativt. `YEARS` tæller **hele** år – nyttigt til en alder.

### Formatér og indlæs: DateTimeFormatter

Til brugeren vil vi skrive datoen på dansk måde, `26-10-2026`. Det gør en `DateTimeFormatter` med et
**mønster**:

```java
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
System.out.println(review.format(formatter));           // 26-10-2026

LocalDate typed = LocalDate.parse("24-12-2026", formatter);
System.out.println(typed);                              // 2026-12-24
```

Den samme formatter virker begge veje: `format` laver en dato om til tekst, `parse` laver tekst om
til en dato.

> **`MM` er måned, `mm` er minutter.** Skriver man `"dd-mm-yyyy"`, går programmet ned, så snart en
> dato skal formateres – en dato har jo ingen minutter:
>
> ```text
> java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: MinuteOfHour
> ```

Hvad sker der, hvis brugeren skriver `"i går"` til `parse`? Programmet går ned med en
`DateTimeParseException`. Det lærer I at håndtere i [del 6](../../projekter/filmsamling/del-6-exceptions.md).

Importerne: `java.time.LocalDate`, `java.time.temporal.ChronoUnit` og
`java.time.format.DateTimeFormatter`.

### Ingen dato endnu: null

En film, man aldrig har set, har ingen "sidst set"-dato. I filmsamlingen er `lastWatched` derfor
`null`, indtil filmen er set. Det kræver omtanke, hver gang datoen bruges – `null.isBefore(...)`
giver en `NullPointerException`. Rækkefølgen i betingelsen redder os:

```java
if (!movie.hasBeenWatched() || movie.getLastWatched().isBefore(date)) {
```

`||` stopper, så snart venstre side er `true`. Er filmen aldrig set, bliver højre side aldrig kørt.
Se hele forklaringen i [del 5½](../../projekter/filmsamling/del-5-datoer.md#krav-til-koden). Og
når datoen skal **vises**, skal der stå `aldrig` – ikke `null`.

### Datoer og tests: giv datoen som parameter

En metode, der selv kalder `LocalDate.now()`, giver et nyt resultat hver dag – og så kan man ikke
skrive en test med et fast forventet resultat. Derfor får metoderne i `Movie` og `MovieCollection`
**datoen som parameter**, og det er kun `Controller`, der kalder `LocalDate.now()`. Det er samme idé
som `isClassic(int currentYear)` i Bogsamling. I dagens opgaver gør I det samme med et udlån på et
bibliotek.

---

# Del 3: Design-review

### Hvorfor gennemgå sin egen kode?

Om godt en uge afleverer I – og få dage efter læser en anden gruppe jeres kode i et **code review**. Det er
bedre selv at finde det, de ville finde. Og det er billigere at rydde op nu, før datoer, exceptions,
filer og sortering bygges oven på.

Et **review** er en gennemgang af kode med friske øjne, efter en tjekliste. Det er ikke en test af,
om programmet virker – det har I tests til. Det er et blik på, om koden er let at **læse, forstå og
ændre**. Altså **S**'et i FURPS.

### Tjeklisten

I dag bruger I den korte tjekliste i
[del 5½, afsnit 3](../../projekter/filmsamling/del-5-datoer.md#3-design-review-af-jeres-egen-kode). Den
er et udsnit af det fulde [review-skema](../../projekter/filmsamling/kode-review.md), som I bruger
over for en anden gruppe den 02-11 og den 06-11. Alle punkterne er ting, I har lært: ansvar og
kobling fra uge 39, `UserInterface` som eneste sted med `System.out` fra Adventure, gode navne.

### Sådan ser problemerne ud

Nogle klassiske tegn på, at koden trænger til oprydning – det kaldes *code smells*:

| Tegn | Eksempel | Hvorfor er det et problem? |
|---|---|---|
| Uklare navne | `list2`, `temp`, `x()`, `s` | man skal læse hele metoden for at forstå, hvad den gør |
| En metode gør to ting | søger **og** udskriver | kan ikke testes, kan ikke genbruges |
| `System.out` eller `Scanner` uden for `UserInterface` | "Er du sikker?" i `MovieCollection` | blander brugerflade og logik; kan ikke testes |
| `public` attributter | `public ArrayList<Movie> movies` | alle kan ændre samlingen uden om klassen |
| Magiske tal | `if (length > 120)` | hvad betyder 120? Og hvor mange steder står det? |
| Gentaget kode | den samme søgeløkke tre steder | en fejl skal rettes tre steder |

I dagens opgaver får I en klasse fuld af den slags at øve jer på.

### Feedback, der kan bruges

Når I reviewer – jeres egen kode eller andres – så:

* **Vær konkret.** Ikke "navnene er dårlige", men "`list2` i `MovieCollection` kunne hedde `movies`".
* **Tal om koden, ikke om personen.** "Den her metode gør to ting" – ikke "du har lavet ...".
* **Spørg hellere end at dømme.** "Hvorfor ligger `Scanner` her?" Der kan være en god grund.
* **Sig også, hvad der er godt.** Så ved gruppen, hvad de skal blive ved med.

---

## Det vigtigste at tage med

* **FURPS**: Functionality, Usability, Reliability, Performance, Supportability – user stories er
  **F**, resten er **kvalitetskrav**
* et godt krav kan **afprøves**
* `LocalDate` er en dato uden klokkeslæt: `now()`, `of(år, måned, dag)`, `parse(...)`
* `LocalDate` er **immutable** – `date = date.plusDays(7)`, ikke bare `date.plusDays(7)`
* sammenlign med `isBefore`/`isAfter`/`isEqual`, tæl med `ChronoUnit.DAYS.between(fra, til)`
* `DateTimeFormatter.ofPattern("dd-MM-yyyy")` – **`MM`** er måned, `mm` er minutter
* `null` = ingen dato; tjek med `||`, så højre side ikke køres på `null`
* giv datoen som **parameter**, så koden kan testes
* et review ser på, om koden er let at læse og ændre – vær konkret, og tal om koden

## Aktiviteter i undervisningen

### 1. FURPS

Lav [del A i opgaverne](opgaver.md#del-a--furps) sammen i gruppen. Skriv derefter jeres egen
`docs/furps.md` efter [del 5½](../../projekter/filmsamling/del-5-datoer.md#krav-docsfurpsmd): én
skriver, alle byder ind. Commit og push.

### 2. LocalDate

Lav [del B i opgaverne](opgaver.md#del-b--localdate) – hver for sig. Start med de små
forudsig-opgaver, og byg så udlånet med tests.

### 3. Filmsamling del 5½ – datoer

Lav US8 og US9 efter [del 5½](../../projekter/filmsamling/del-5-datoer.md#2-datoer), og fordel
arbejdet som i den [anbefalede procedure](../../projekter/filmsamling/del-5-datoer.md#anbefalet-procedure).
Husk testene – især grænsen: en film set **præcis** på datoen.

### 4. Design-review

Når datoerne virker, og testene er grønne: øv jer først på klassen i
[del C i opgaverne](opgaver.md#del-c--design-review), og gennemgå så **jeres egen** kode sammen ved
én skærm efter [tjeklisten i del 5½](../../projekter/filmsamling/del-5-datoer.md#3-design-review-af-jeres-egen-kode).
Ret det, I finder, og commit rettelserne hver for sig.

Del 5½ **bør være færdig inden tirsdag 27-10**.
