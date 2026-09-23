# Projektvejledning og aflevering af Filmsamling

## Beskrivelse

I dag er sidste arbejdsdag på Filmsamlingen. **I aften kl. 23:59** er der deadline for
afleveringen.

Undervisningen foregår **online** i dag. Der er ingen ny teori – undervisningen er jeres til at:

* gøre [del 9 – Sortering](../../projekter/filmsamling/del-9-sortering.md) og eventuelle rester
  færdige
* få vejledning, når I sidder fast
* tjekke, at programmet virker, og at de **fem krav** til afleveringen er opfyldt
* aflevere – **én aflevering for hele gruppen** i itslearning

> **Filmsamling er obligatorisk.** Adventure, Filmsamling og Delfinen skal alle tre være afleveret,
> for at I kan indstilles til eksamen. Aflevér i aften – også selvom ikke alt virker.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* prioritere det, der mangler, så kravene bliver opfyldt først
* tjekke dit eget program mod user stories og en kravliste
* stille et præcist spørgsmål til en vejleder: hvad du prøver, hvad der sker, og hvad du har forsøgt
* kontrollere, at et GitHub-repository er komplet: README, dokumentation, tests og commits fra alle
* aflevere et GitHub-link korrekt som gruppeaflevering i itslearning

## Se disse videoer før undervisningen:

Ingen video i dag. Læs i stedet afsnittet
[Aflevering](../../projekter/filmsamling/readme.md#aflevering) i projektbeskrivelsen igennem, så I
ved præcis, hvad der skal afleveres.

## Læs nedenstående før undervisningen

---

### Sådan foregår dagen online

Underviseren lægger **link og tidspunkter** til dagens online-vejledning i itslearning eller Teams.
Hold øje med det om morgenen – planen for dagen, fx en fælles start og tider til vejledning, står
der.

Mellem vejledningerne arbejder I i gruppen. Sæt jer sammen, eller lav et fælles opkald i Teams, hvor
I kan dele skærm. Det er svært at arbejde på samme projekt, når man ikke kan se hinandens skærm.

#### Få mest ud af vejledningen

Online-vejledning er kortere og mere koncentreret end at række hånden op i lokalet. Forbered jer:

1. **Push jeres nyeste kode**, før I får vejledning – også selvom den ikke virker. Så kan
   underviseren åbne den på GitHub.
2. **Formulér spørgsmålet.** Hvad prøver I at gøre? Hvad sker der i stedet? Hvad har I prøvet?
   *"Vores sortering virker ikke"* er svært at hjælpe med. *"`getMoviesSortedBy(YEAR)` giver
   filmene i oprettelsesrækkefølge – vi tror, `sort` bliver kaldt på en kopi, vi ikke returnerer"*
   kan løses på to minutter.
3. **Hav fejlbeskeden klar.** Kopiér den røde tekst fra IntelliJ – den **første** linje med
   `Exception` eller `error` og linjen med jeres egen klasse og linjenummer.
4. **Vær klar til at dele skærm**, med IntelliJ åbent det rigtige sted.

> **Sidder I fast i mere end et kvarter, så spørg.** I dag er ikke dagen til at kæmpe alene.

---

### De fem krav

Fra [Aflevering](../../projekter/filmsamling/readme.md#krav-for-at-få-afleveringen-godkendt):
programmet skal kompilere og kunne køre, og **alle fem** punkter herunder skal være opfyldt. Mangler
ét af dem, er afleveringen **ikke godkendt**.

- [ ] **`README.md`** i roden af repositoriet med **fornavn og GitHub-brugernavn** på hvert
      gruppemedlem og **hvordan man kører programmet** (hvilken klasse har `main`, hvilken JDK)
- [ ] **Klassediagram** over det færdige program i mappen `docs` – som Mermaid i
      `docs/klassediagram.md` eller som billede/pdf, tegnet af jer selv, ikke autogenereret
- [ ] **`docs/furps.md`** fra [del 5½](../../projekter/filmsamling/del-5-datoer.md) – mindst ét
      krav pr. FURPS-bogstav
- [ ] **Tests, der kører grønt** – jeres JUnit-tests under `src/test/java`
- [ ] **Commits fra hvert eneste medlem** af gruppen, synlige i historikken på GitHub

Sådan tjekker I dem:

**README.md.** Åbn repositoriet på GitHub – README'en vises under fillisten. Står alle med fornavn
og GitHub-brugernavn? Kan en, der aldrig har set projektet, køre det ud fra teksten? Skriv også,
hvilke user stories I har lavet, og ærligt, hvilke I ikke nåede.

**Klassediagrammet.** Passer det med den **færdige** kode? Efter del 8 og 9 skal det vise de tre
packages, `FileHandler`, `SortField` og comparatorerne. Sammenlign med
[oversigten i projektbeskrivelsen](../../projekter/filmsamling/readme.md#klasserne--samlet-oversigt) –
men husk, at jeres skal vise **jeres** program.

**Tests.** Højreklik på `src/test/java` i IntelliJ → **Run 'All Tests'**. Alle skal være grønne. En
test, der er rød, fordi koden er forkert, skal rettes. En test, der er rød, fordi testen er forkert,
skal også rettes – ikke slettes.

**Commits fra alle.** På GitHub: klik på antallet af **commits** over fillisten. Står alle
gruppens medlemmer som forfattere? Står nogen med et navn, der ikke er koblet til deres
GitHub-bruger, så sig det til underviseren i dag – ikke i morgen.

> **Er I ikke nået alle user stories, så aflevér alligevel.** De fem krav skal være opfyldt, men
> skriv i README'en, hvilke user stories der mangler. Et link, der er afleveret til tiden, er langt
> bedre end intet link.

---

### Tjekliste: virker programmet?

Gå listen igennem **ved at køre jeres eget program**. Sæt kun kryds, når I har set det ske.

**Menuen** (se [samlet oversigt](../../projekter/filmsamling/readme.md#menuen--samlet-oversigt)):

- [ ] `1` opretter en film, og den er med, når man vælger `2`
- [ ] `2` viser alle film **sorteret efter titel**, uanset store og små bogstaver (US16)
- [ ] `3` finder film, hvis titel **indeholder** søgeteksten – også med små bogstaver
- [ ] `4` retter en film, og ændringen kan ses med `2`
- [ ] `5` sletter en film
- [ ] `6` markerer en film som set, og `7` finder film, man ikke har set længe
- [ ] `8` sorterer efter titel, instruktør, årstal, længde og genre (US17)
- [ ] `0` gemmer og afslutter – og filmene er der igen, når programmet startes

**Robusthed** (fra [del 6](../../projekter/filmsamling/del-6-exceptions.md)):

- [ ] Bogstaver, hvor der skal stå et tal, vælter **ikke** programmet
- [ ] Et nummer, der ikke findes på listen, vælter ikke programmet
- [ ] Et årstal før 1888 eller en længde på 0 minutter bliver afvist med en forklaring
- [ ] Programmet starter, også når der **ingen** `movies.csv` er

**Koden:**

- [ ] Klasserne ligger i `ui`, `domainmodel` og `datasource`
- [ ] `System.out` og `Scanner` findes **kun** i `UserInterface` – søg med
      <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>F</kbd> (Mac: <kbd>Cmd</kbd>+<kbd>Shift</kbd>+<kbd>F</kbd>)
- [ ] Sorteringen ændrer **ikke** rækkefølgen i selve samlingen
- [ ] Der er kun **ét** sted, der vælger comparator ud fra brugerens valg

---

### Sidste kvalitetstjek

På fredag læser en anden gruppe jeres kode efter [review-skemaet](../../projekter/filmsamling/kode-review.md).
Brug en halv time på de spørgsmål, der er lettest at rette:

- [ ] Er der en `.gitignore`, og er `target`-mappen og `movies.csv` **ikke** på GitHub?
- [ ] Er alle attributter `private`?
- [ ] 💣 Er der udkommenteret kode eller kommentarer, der ikke længere passer?
- [ ] 💣 Er der en tom `catch` eller en `catch` med kun `printStackTrace()`?
- [ ] Er koden formateret? (<kbd>Ctrl</kbd>+<kbd>Alt</kbd>+<kbd>L</kbd>, på Mac
      <kbd>Cmd</kbd>+<kbd>Option</kbd>+<kbd>L</kbd>)

> **Pas på med oprydning sidst på dagen.** Commit og push først, så I har en version at falde
> tilbage på. Kør alle tests efter hver oprydning – det er en refaktorering, så opførslen må ikke
> ændre sig.

Er `target` eller `movies.csv` allerede kommet med på GitHub, forsvinder de ikke af sig selv, bare
fordi de kommer i `.gitignore`. Spørg ved vejledningen, hvis I vil have dem fjernet.

---

### Sådan afleverer I

Afleveringsopgaven hedder **Filmsamling – aflevering** og ligger i **jeres klasses rum** i
itslearning (E26A og E26B har hver sit).

1. **Commit og push** den sidste version. Tjek på GitHub, at den nyeste commit er der.
2. Kopiér linket til **repositoriet som helhed** – fx `https://github.com/brugernavn/filmsamling` –
   ikke til en fil eller en mappe.
3. Åbn linket i et **privat browservindue**, hvor I ikke er logget ind på GitHub. Kan I se koden
   der, kan underviseren og den gruppe, der skal reviewe jer, også. Får I en fejlside, er
   repositoriet ikke **public**.
4. Tjek, at **alle** gruppens medlemmer er med i jeres gruppe i itslearning. Den, der ikke er med i
   gruppen, har ikke afleveret.
5. **Ét** medlem afleverer på hele gruppens vegne: indsæt linket som **klikbar** tekst i
   besvarelsen.

> **Vent ikke til 23:55.** itslearning og GitHub har det med at drille, når man har travlt.
> Aflevér, så snart de fem krav er opfyldt – og push gerne rettelser bagefter, så længe det er
> **før** kl. 23:59.

---

### Efter deadline

Fredagens [code review](../../projekter/filmsamling/kode-review.md) foregår på den **seneste commit
før deadline**. Det, I pusher efter kl. 23:59, tæller ikke med – hverken i afleveringen eller i
reviewet. I må gerne arbejde videre på projektet bagefter, men så ved I det.

I morgen, torsdag, er der ingen underviser. I forbereder fredagens review ved at læse den anden
gruppes kode – se [torsdagens side](../04_tor_2026-11-05/README.md).

---

## Det vigtigste at tage med

* deadline **i aften kl. 23:59** – ét klikbart link til hele repositoriet i itslearning
* **gruppeaflevering**: alle skal være med i gruppen i itslearning, og ét medlem afleverer
* **fem krav**: README, klassediagram i `docs`, `docs/furps.md`, grønne tests og commits fra alle
* repositoriet skal være **public** – test linket i et privat browservindue
* push jeres kode, **før** I får vejledning, og formulér et præcist spørgsmål
* aflevér hellere tidligt end i sidste øjeblik; efter 23:59 tæller intet med

## Aktiviteter i undervisningen

### 1. Status i gruppen (første halve time)

Gå [de fem krav](#de-fem-krav) og [tjeklisten over programmet](#tjekliste-virker-programmet)
igennem sammen. Skriv tre lister:

* det, der virker
* det, der mangler, og som **skal** være der – de fem krav først
* det, der ville være rart, men som kan undværes (fx US18)

Arbejd **kun** på den midterste liste, indtil den er tom.

### 2. Gør det færdigt – med vejledning

Arbejd med [del 9](../../projekter/filmsamling/del-9-sortering.md) og det, der ellers mangler.
Vejledningen foregår online efter den plan, underviseren har lagt i itslearning eller Teams.

> **Sæt et stoppunkt.** Aftal i gruppen, hvornår I stopper med at lave nye ting – fx kl. 14. Derefter
> retter I kun fejl, skriver dokumentation og afleverer.

### 3. Dokumentation og kvalitetstjek

Opdatér `README.md` og klassediagrammet, og gå [det sidste kvalitetstjek](#sidste-kvalitetstjek)
igennem. Commit og push efter hver ting.

### 4. Aflevér

Følg [Sådan afleverer I](#sådan-afleverer-i). Tjek, at afleveringen er registreret for **hele**
gruppen, før I slutter dagen.
