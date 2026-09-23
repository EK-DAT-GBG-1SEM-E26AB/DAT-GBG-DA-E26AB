# Code review – Filmsamling

> Skema til code review af Filmsamling-projektet. Del af det samlede
> [Filmsamling-projekt](readme.md).

Dette er et skema til code review af et projekt – med fokus på den objektorienterede struktur,
lagdelingen i packages, navngivning af klasser, metoder og variable, tests, exceptions, filer,
sortering og kommentarer.

> **Der er ikke fokus på brugerfladen og brugeroplevelsen.** Om menuen er pæn, er ikke det, vi
> kigger efter. Vi kigger på, hvor let koden er at læse, forstå og vedligeholde for en anden
> udvikler – **S**'et i FURPS.

Et review er **ikke** en karakter og ikke ris eller ros. Det er en faglig samtale om koden, som
begge grupper skal have noget ud af: den ene får friske øjne på sin kode, den anden ser, hvordan
andre har løst de samme problemer.

## Hvornår bruges skemaet?

| Dag | Hvad |
|---|---|
| man 02-11 | **Øvelse** i [del 8](del-8-refaktorering.md): to grupper reviewer hinandens kode på samme måde som fredag, men kun afsnittene *GitHub*, *Klasser* for `Movie` og `UserInterface`, og *Tests*. |
| tor 05-11 | **Forberedelse** (uden underviser): I cloner den gruppe, I skal reviewe, kører programmet og testene, læser koden og udfylder så meget af skemaet, I kan. |
| fre 06-11 | **Reviewet**: de to grupper sidder sammen og reviewer hinandens kode på skift. |

Underviseren parrer grupperne. Det er **hele gruppe mod hele gruppe**: to grupper sidder sammen,
og hver gruppe reviewer den anden gruppes projekt – først det ene, så det andet.

## Sådan gør I

### Torsdag 05-11: forberedelse

1. Find den anden gruppes repository (linket står i deres `README.md`, eller spørg dem).
2. Clone repositoriet i IntelliJ (**File → New → Project from Version Control**). I reviewer den
   version, der ligger på **`main` på GitHub, når reviewet starter** fredag. Lav derfor et
   **pull** fredag, lige før I går i gang, så I har den nyeste version – og notér de første 7 tegn
   af hashen på den commit, I reviewer.
3. Kan I ikke finde ud af, hvilken gruppe I skal reviewe, så review den samme gruppe som ved
   øvelsen mandag 02-11, og skriv til underviseren.
4. Kør programmet. Kør alle tests.
5. Læs koden, og udfyld skemaet så langt, I kan. Skriv spørgsmål ned til fredag – ting, I ikke
   forstår, eller som I ville have løst anderledes.

Del arbejdet i gruppen, fx én klasse pr. person, men gå fundene igennem sammen, før I går hjem.

### Fredag 06-11: reviewet

De to grupper sidder sammen – alle fra begge grupper er med hele tiden. Reviewet tager to runder:

* **Runde 1:** gruppe A reviewer gruppe B's projekt. Gruppe B er **programmører** og forklarer
  deres kode.
* **Runde 2:** rollerne byttes – gruppe B reviewer gruppe A's projekt.

I hver runde:

1. Én fra reviewer-gruppen har projektet åbent i IntelliJ på en skærm, som alle kan se.
2. En anden fra reviewer-gruppen skriver i skemaet.
3. Gå skemaet igennem. Programmørerne forklarer, når der er spørgsmål – "hvorfor gjorde I sådan?"
4. Afslut med afsnittet *Yderligere kommentarer*: hvad er særlig godt, og hvad er de tre vigtigste
   ting at rette?

Del tiden ligeligt mellem de to runder.

### Aflevering af reviewet

Giv det udfyldte skema til den gruppe, I har reviewet. Den nemmeste måde er et **issue** i deres
repository på GitHub:

1. Åbn denne fil på GitHub, og klik på **Raw** – så får I Markdown-teksten.
2. Kopiér den ind i jeres egen fil og udfyld den (i IntelliJ eller en anden editor).
3. I den anden gruppes repository: fanen **Issues → New issue**. Titel: `Code review fra gruppe ...`.
   Indsæt det udfyldte skema, og klik **Create**.

Afkrydsningsfelterne `[ ]` og `[x]` bliver vist som rigtige checkbokse på GitHub.

---

| | |
|---|---|
| **Gruppen, der reviewes** | *gruppens navn eller medlemmer* |
| **GitHub repository** | *url* |
| **Commit hash** (7 tegn) | |
| **Reviewer-gruppen** | *gruppens navn eller medlemmer* |
| **Runde** | 1 eller 2 |
| **Dato** | |

### Sådan læses skemaet

* Spørgsmål markeret med 💣 er **dårlige** – de skal helst kunne besvares med **nej**.
* Sæt kryds (`[x]`), hvis der kan svares **ja** til et spørgsmål.
* Er der grund til eksempler eller uddybende kommentarer, så skriv dem på linjen lige under
  spørgsmålet – gerne med klasse, metode og linjenummer.
* Nogle spørgsmål er måske ikke relevante – fx exceptions i en klasse, der ingen exceptions har. Så
  **slet** blot spørgsmålet.

---

## GitHub

Før I kigger på koden, så kig på, hvad der ellers ligger i repositoriet.

* [ ] Er der en `.gitignore`-fil?
* [ ] 💣 Er der uvedkommende filer i GitHub (fx `target`-mappen, `.class`-filer, `movies.csv`)?
* [ ] Er der en `README.md` med fornavn og GitHub-brugernavn på hvert medlem og en forklaring på,
      hvordan man kører programmet?
* [ ] Ligger dokumentation og andet ikke-kode i en `docs`-mappe – både klassediagram og
      `furps.md` (mindst ét krav pr. bogstav)?
* [ ] Passer klassediagrammet med koden?
* [ ] Viser historikken commits fra **alle** i gruppen?
* [ ] Er commit-beskederne til at forstå – kan man se, hvad hver commit gjorde?

---

## Kør programmet og testene

* [ ] Kan projektet åbnes og kompileres uden fejl?
* [ ] Starter programmet, også når der ingen `movies.csv` er?
* [ ] Er **alle** tests grønne? *Antal tests:*
* [ ] 💣 Går programmet ned, hvis man skriver bogstaver, hvor der skal stå et tal?

---

## Packages

Der bør være tre packages under `src/main/java`: `ui`, `domainmodel` og `datasource` (eller
lignende navne).

* [ ] Er der tre packages med sigende navne?
* [ ] Er det let at regne ud, hvilken rolle hver package har?
* [ ] Ligger hver klasse i den package, der passer til dens ansvar?
* [ ] 💣 Importerer en klasse i `domainmodel` eller `datasource` noget fra `ui`?
* [ ] Ligger testene i packages med samme navne under `src/test/java`?

---

## Klasser

Der bør være mindst disse klasser: `Main`, `UserInterface`, `Controller`, `MovieCollection`,
`Movie` og `FileHandler` – og en række comparator-klasser. Er nogle navngivet anderledes, så
diskutér, om navnet er bedre, værre eller lige så godt.

Gentag dette afsnit for `Movie`, `MovieCollection`, `Controller`, `UserInterface` og `FileHandler`.
Comparator-klasserne har deres eget afsnit længere nede.

### Klassen: `-navn-`

* [ ] Er klassen godt navngivet?
* [ ] Er det let at regne ud, hvilken rolle klassen har?
* [ ] Er der brugt tydelig ental/flertal for at vise, om klassen indeholder ét eller flere
      underobjekter?
* [ ] 💣 Er der `System.out` eller `Scanner` i klassen, selv om den ikke er `UserInterface`?

#### Attributter / fields

* [ ] 💣 Er der oprettet attributter, der burde være lokale variable i stedet?
* [ ] 💣 Er der erklæret ubenyttede attributter?

**Er attributter navngivet godt?**

* [ ] Er navnene selvforklarende?
* [ ] Er navnene fra "problemdomænet"?
* [ ] Er navnene tydeligt og korrekt ental eller flertal?
* [ ] Er navnene alle på samme sprog (engelsk)?

**Har attributter korrekt access?**

* [ ] Er der brugt `private`?
* [ ] Er der getter og setter (hvis nødvendigt)?
* [ ] 💣 Er attributter gjort `public` uden getter og setter?
* [ ] 💣 Er der hverken brugt `private` eller `public`, men bare "ingenting"?

**Getters / setters**

* [ ] 💣 Er der ubenyttede get- og set-metoder?
* [ ] 💣 Er der metoder kaldet `get` eller `set`, som ikke er en getter/setter?
* [ ] 💣 Eller omvendt: er der gettere eller settere, der hedder noget andet end `get`, `set` eller
      (for `boolean`) `is`?

#### Metoder

Kig på hver metode, først "udefra", altså uden at læse koden inde i metoderne. Det hjælper måske at
"folde" alle metoderne sammen (<kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>-</kbd>, på Mac
<kbd>Cmd</kbd>+<kbd>Shift</kbd>+<kbd>-</kbd>).

**Er metoder navngivet godt?**

* [ ] Er navnene selvforklarende?
* [ ] Er navnene fra "problemdomænet"?
* [ ] Er det tydeligt, hvad hver metode gør, ud fra sit navn?
* [ ] Er det tydeligt nok ud fra navnet, om metoder returnerer eller modtager værdier?
* [ ] Hvis metoden modtager parametre, er det så umiddelbart til at regne ud, hvad de er til, ud fra
      navngivningen?
* [ ] Hvis metoden returnerer en værdi, giver datatypen så umiddelbar mening?
* [ ] Er der en fornuftig ensartethed/struktur i navngivningen af metoderne?
* [ ] Er metodenavnene navngivet med korrekt brug af `camelCase`?
* [ ] Er parameternavne navngivet med korrekt brug af `camelCase`?
* [ ] Er navnene alle på samme sprog (engelsk)?
* [ ] 💣 Er der metoder, der gør to ting – fx både finder noget og udskriver det?

#### Kode

Kig derefter **ind** i hver metode og vurdér koden. I skal ikke besvare en sektion for hver eneste
metode, men kigge på metoderne som helhed og notere, hvis der er et sted, der afviger.

**Lokale variabler inde i metoder**

* [ ] Er variabler navngivet korrekt `camelCase`?
* [ ] Er variablernes navne i tilstrækkelig grad fra "problemdomænet"?
* [ ] Er variablerne erklæret, hvor de skal bruges?
* [ ] Har variablerne den korrekte datatype?
* [ ] Er navnene alle på samme sprog (engelsk)?

**Generel kodestil**

* [ ] Er koden pænt (og korrekt) formateret med indrykninger etc.?
* [ ] Er koden "skimbar" og umiddelbart let at overskue, uden at skulle nærlæse hver linje?
* [ ] 💣 Er der smarte "ninja-tricks", der gør koden kompakt, men måske sværere for nogle at læse?
* [ ] 💣 Er der kode, der er kopieret flere steder, og som burde være én metode?

**Exceptions**

* [ ] Bliver exceptions fanget dér, hvor man ved, hvad brugeren skal have at vide (typisk
      `UserInterface`)?
* [ ] Bliver ugyldige værdier afvist med en `throw` og en forklarende besked?
* [ ] 💣 Bliver exceptions blot håndteret med en tom `catch` eller en `catch` med `printStackTrace`?
* [ ] 💣 Bliver `NullPointerException` eller `IndexOutOfBoundsException` fanget, hvor en `if` burde
      have forhindret dem?
* [ ] Kan programmet fortsætte efter en exception?
* [ ] Er der lavet egne exception-typer – og giver de bedre mening end de indbyggede?

#### Kommentarer

* [ ] Er der kommentarer, der fx viser, hvor en fancy løsning er fundet på nettet?
* [ ] Er der kommentarer, der beskriver, hvordan/hvorfor en metode er opbygget anderledes end
      forventet?
* [ ] Er der kommentarer til udvikleren selv om ting, der skal rettes/ændres i fremtiden?
* [ ] 💣 Er der kommentarer, der ikke længere giver mening, og som burde have været slettet?
* [ ] 💣 Er der udkommenteret kode, der burde være slettet?
* [ ] Er der gode kommentarer i programmet?
* [ ] 💣 Er der overflødige kommentarer? Kunne fx være `i++; // lægger en til i`

---

## FileHandler – filen

Ud over spørgsmålene under *Klasser*:

* [ ] Er al læsning og skrivning af filen samlet i `FileHandler`?
* [ ] 💣 Ved andre klasser end `FileHandler` noget om filformatet (fx semikolon eller feltrækkefølge)?
* [ ] Bliver filen lukket (`close()`) efter læsning og skrivning?
* [ ] Overlever alle felter en tur gennem filen – også æ, ø, å og datoen?
* [ ] Går programmet videre, hvis en linje i filen er ødelagt?
* [ ] Skrives filen kun, når der er ændringer?

---

## Comparator-klasserne

Reviewes samlet. Hver comparator bør have et navn med den egenskab, den sammenligner, og ordet
`Comparator` – fx `YearComparator`.

* [ ] Er klasserne godt navngivet?
* [ ] Er der brugt `@Override` på `compare` (og på `compareTo` i `Movie`)?
* [ ] Er `compare` implementeret med `compareTo`/`compareToIgnoreCase` eller `Integer.compare` –
      i stedet for egne `if`-sætninger, der returnerer `-1`, `0` og `1`?
* [ ] Kan koden i `compare` simplificeres?
* [ ] Er der kun **ét** sted i koden, der vælger comparator ud fra brugerens valg?
* [ ] 💣 Ændrer sorteringen rækkefølgen i selve samlingen?

---

## Tests

* [ ] Er der en testklasse for hver klasse med logik (`Movie`, `MovieCollection`, `FileHandler`,
      comparatorerne)?
* [ ] Er testmetodernes navne sigende – kan man se, hvad der testes, uden at læse koden?
* [ ] Følger testene Arrange – Act – Assert?
* [ ] Er de "kedelige" tilfælde testet: tom samling, ingen match, en film, der ikke findes?
* [ ] Er grænserne testet – fx årstal 1887/1888, en dato præcis på grænsen?
* [ ] Er det testet, at ugyldige værdier afvises (`assertThrows`)?
* [ ] 💣 Afhænger testene af hinanden eller af den rækkefølge, de køres i?
* [ ] 💣 Afhænger testene af dagens dato, så de kan blive røde i morgen?
* [ ] 💣 Kan testene komme til at overskrive eller slette den rigtige `movies.csv`?
* [ ] 💣 Er der tests, der ikke tester noget (ingen `assert...`)?

---

## Yderligere kommentarer til koden fra reviewerne

*Her kan I skrive yderligere noter – ting, der ikke lige passer ind i skemaet ovenfor. Ros til
særligt elegant kode, spørgsmål til, hvordan programmørerne fandt på en løsning.*

**Det er særlig godt:**

**De tre vigtigste ting at rette:**

1.
2.
3.
