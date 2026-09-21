# Opgaver – Git og GitHub - introduktion til versionsstyring

## Kom i gang

Du skal kunne arbejde både lokalt og med GitHub. Brug enten terminalen eller IntelliJ’s Git-værktøjer, men hold dig til samme arbejdsproces gennem hele opgaveforløbet.

---

# Del 1 – Grundlæggende Git-koncepter

## Opgave 1 – Klon startprojektet via terminalen

I denne opgave skal du hente startprojektet ned på din computer ved hjælp af Git i terminalen.

### 1. Naviger til `IdeaProjects`

IntelliJ gemmer normalt dine projekter i mappen `IdeaProjects` i din brugermappe. Åbn din terminal:

- **Git Bash (Windows):**
  ```bash
  cd ~/IdeaProjects
  ```
  *(eller `cd /c/Users/<dit-brugernavn>/IdeaProjects`)*

- **Terminal (Mac):**
  ```bash
  cd ~/IdeaProjects
  ```

Tjek eventuelt med `pwd`, at du står i mappen `IdeaProjects`.

### 2. Klon projektet med Git

Klon repositoriet fra GitHub ved at køre:

```bash
git clone https://github.com/EK-DAT-GBG-1SEM-E26AB/Main.git
```

Naviger derefter ind i projektmappen:

```bash
cd Main
```

> **Bemærk:** Hvis du allerede har en mappe kaldet `Main`, får du muligvis en fejl (fordi mappen allerede eksisterer). Du kan tilføje et ekstra argument til `git clone`, hvor du angiver et nyt mappenavn som fx `Main-xyz`:
>
> ```bash
> git clone https://github.com/EK-DAT-GBG-1SEM-E26AB/Main.git Main-xyz
> cd Main-xyz
> ```

### 3. Diskussion og refleksion

Undersøg mappens indhold (f.eks. ved at køre `ls` i terminalen eller ved at inspicere mappen).

Diskuter derefter i gruppen:

- Hvad er det basalt set, du lige har downloadet fra GitHub?
- Hvilke filer og mapper har du fået ned på din computer?
- Hvad er formålet med de enkelte filer, du kan se?

*(Bemærk: Hvis du opdager en skjult mappe ved navn `.git`, kan du se bort fra den for nu – den kigger vi nærmere på i de næste opgaver).*

## Opgave 2 – Slet src-mappen og gendan med Git

I denne opgave undersøger du en af de mest basale styrker ved Git: muligheden for at genskabe tabte filer.

### 1. Slet `src`-mappen med bekræftelse

Slet nu `src`-mappen og dens indhold via terminalen. Vi bruger flaget `-i` (interactive) og `-r` (recursive), så du bliver bedt om at bekræfte hver sletning i stedet for at gennemtvinge den:

```bash
rm -ri src
```

Tast `y` (for *yes*) og tryk **Enter**, hver gang terminalen spørger, om en fil eller mappen skal slettes.

Når du er færdig, undersøg mappen (fx med `ls`). Se, at `src`-mappen er væk, og at mappen ellers er tom (kun den skjulte `.git`-mappe er tilbage).

### 2. Gendan med `git checkout`

Prøv nu at hente de slettede filer tilbage med kommandoen:

```bash
git checkout master
```

> **Tip til terminalen:** Du behøver ikke at skrive hele branch-navnet manuelt. Når du blot har skrevet `git checkout ` og derefter de første par bogstaver (fx `ma`), kan du trykke på **Tab-tasten** 1–3 gange. Terminalen vil så automatisk auto-udfylde branch-navnet for dig!

Den faktisk kommando er med `-f` efter `checkout`, da vi allerede står på den pågældende branch (´master´):

```bash
git checkout -f master
```
> **Vær varsom** med brugen `-f` på ´git´-kommandoer, da f'et står for *force*.

Kør derefter `ls` igen for at bekræfte, at `src`-mappen og alle filerne er vendt tilbage.

### 3. Diskussion og refleksion

Diskuter følgende i gruppen:

- Hvad skete der helt præcist, da du kørte `git checkout master`?
- Hvor kom filerne fra, når de lige var blevet slettet fra harddisken?
- Hvilken rolle spiller `.git`-mappen i denne sammenhæng?
- Hvorfor giver versionsstyring som Git en tryghed, når man arbejder på et projekt, sammenlignet med en almindelig mappe?

## Opgave 3 – Åbn projektet i IntelliJ og se Git-status

I denne opgave åbner du det klonede repository som et IntelliJ-projekt og undersøger, hvilke filer der er registreret som uversionsstyrede.

### 1. Åbn projektet i IntelliJ

1. Åbn IntelliJ.
2. Vælg **File → Open...**
3. Naviger til den mappe, du klonede i `IdeaProjects`.
4. Vælg projektmappen og åbn den som et IntelliJ-projekt.

### 2. Gå til Change-vinduet

Når projektet er åbnet, skal du finde **Git / Change**-vinduet i IntelliJ.

Se herefter efter sektionen **Unversioned Files**.

> **Vigtigt:** Denne sektion kan være foldet sammen. Klik på den for at udvide den, så du kan se hvilke filer der ligger der.

### 3. Diskussion og refleksion

Diskuter følgende i gruppen:

- Hvilke filer vises under `Unversioned Files`?
- Hvorfor er de ikke allerede versioneret i Git?
- Hvad betyder det, at en fil er "unversioned"?
- Hvem eller hvad har oprettet disse ekstra filer?
- Hvorfor er dette et nyttigt sted at se, når man lige har klonet et projekt eller har lavet nye filer lokalt?

## Opgave 4 – Omdøb projektet i IntelliJ og undersøg Git-status

I denne opgave ændrer du navnet på projektmappen i IntelliJ for at få erfaring med, hvordan fil- og projektnavne påvirker et Git-repositorie.

### 1. Omdøb projektet i IntelliJ

1. I IntelliJ skal du gå til **Project-vinduet**.
2. Find projektmappen, der hedder `Main`.
3. Højreklik på mappen og vælg en mulighed for at omdøbe den.
4. Giv projektet et mere beskrivende navn, fx `Main-xyz`, `GitDemo` eller et andet passende navn.

### 2. Undersøg, hvad der sker

Når du har omdøbt projektet, skal du se nærmere på **Git / Change**-vinduet igen.

Diskuter følgende i gruppen:

- Hvad skete der med projektet, da du omdøbte mappen?
- Er Git opmærksom på den nye mappe-navngivning?
- Hvad sker der med kildekoden, når du omdøber projektet lokalt?
- Kan du stadig køre programmet efter omdøbningen?

### 3. Kør programmet igen

Prøv at køre projektet efter omdøbningen.

Besvar:

- Kørte programmet stadig uden problemer?
- Var der nogen ekstra trin nødvendige for at få det til at køre igen?

### 4. Er det nødvendigt at gemme ændringen i Git?

Tænk over:

- Er omdøbningen af en mappe en ændring i projektets indhold?
- Skal denne ændring gemmes i Git, eller er det blot en lokal ændring i din arbejdsmappe?
- Hvad er forskellen på at omdøbe en mappe lokalt og at committe en ændring til GitHub?

### 5. Lav et commit over ændringen

Hvis du har gjort en lokal ændring, fx omdøbt projektmappen, kan du også gemme den i Git.

I IntelliJ skal du:

1. Gå til **Git / Commit**
2. Vælg de filer, der er ændret
3. Skriv en passende commit-besked, fx:

```text
Omdøb projektmappe fra Main til et mere passende navn
```

4. Tryk på **Commit**-knappen.

Diskuter herefter:

- Hvad betyder det at "committe" en ændring?
- Hvorfor er det vigtigt at skrive en tydelig commit-besked?
- Hvordan kan en commit gøre det nemmere at holde styr på projektets historie?

## Opgave 5 – Gør det én ændring ad gangen

I denne opgave skal du ændre programmet, så det udskriver dit navn i stedet for `World`.

Men her gælder en ekstra regel: for hvert bogstav eller tegn, du ændrer, laver du et nyt Git-commit med en kort og tydelig commit-besked.

Eksempel:

```java
System.out.println("Hello World!");
```

skal efterhånden blive til:

```java
System.out.println("Hello Mica!");
```

### Hvor mange commits er nødvendige?

Hvis du bruger navnet `Mica`, så skal du sammenligne teksten `Hello World!` med teksten `Hello Mica!`.

Her skal `Hello` stå uændret, så det er kun delen `World!` der ændres til `Mica!`.

Det betyder, at du kun skal ændre eller slette de tegn, der er nødvendige for at få `World!` til `Mica!`.

Hvis du vil gøre det systematisk, kan du sammenligne tegn for tegn og tælle hver ændring som et eget commit.

### Opgave

1. Lav en lille ændring i teksten.
2. Commit med en kort tekst som fx:

```text
Ændr W til M
```

3. Fortsæt indtil programmet udskriver dit navn.
4. Diskuter, hvorfor det er en god øvelse at arbejde i små trin.

## Opgave 6 – Gå tilbage til et tidligere commit

I denne opgave skal du undersøge, hvordan Git viser historikken og hvordan du kan gå tilbage til et tidligere punkt i projektet.

### 1. Åbn Git-vinduet i IntelliJ

I IntelliJ skal du finde **Git-vinduet** nederst i editoren. Der ser du typisk tre områder:

- til venstre: **branches**
- i midten: **commits**
- til højre: information om det valgte commit

### 2. Vælg et tidligere commit

Vælg et commit, der ligger cirka midt i rækken af de commits, du lige har lavet.

Højreklik på det valgte commit og vælg:

```text
Checkout Revision
```

### 3. Diskussion og refleksion

Diskuter følgende i gruppen:

- Hvad sker der, når du checker ud af et tidligere commit?
- Hvordan kan du se, at du har flyttet dig tilbage i historikken?
- Hvilke informationer ser du i det valgte commit til højre?
- Hvorfor kan det være nyttigt at kunne gå tilbage til et tidligere punkt i projektet?
- Hvorfor er det måske for mange små commits i denne øvelse, når man kigger på et samlet projektforløb?
- Hvad er forskellen mellem at gå tilbage til en tidligere revision og at ændre den nuværende version permanent?

## Opgave 7 – Forbind lokalt repo med GitHub og push første commit

I denne opgave forbinder du dit lokale Git-repositorie med GitHub og pusher dine lokale commits til det remote repositorie.

### 1. Opret tomt repositorie på GitHub

1. Log ind på [GitHub](https://github.com)
2. Klik på `+` (plus-ikonet) øverst til højre → `New repository`
3. Giv repositoriet et navn, fx `lucas-numbers` eller `git-practice`
4. Vælg `Private` (hvis du ønsker det privat) eller `Public`
5. **Vigtig:** Undlad at tilvælge "Initialize this repository with README, .gitignore or license"
6. Klik `Create repository`

GitHub viser nu instruktioner til at forbinde dit lokale repo.

### 2. Forbind lokalt repo med GitHub via IntelliJ

I IntelliJ skal du gå til `Git` → `Manage Remotes...`:

> Hvis du har klonet repositoriet fra et sted, hvor du ikke her skrive adgang til, så kan du fjerne `origin` ved at markere den oprindelige og trykke på `-`.

1. Klik på `+` (tilføj remote)
2. Sæt navn til `origin`
3. Indsæt GitHub repositoriets URL (kopier fra GitHub-siden)
   - Eksempel: `https://github.com/dit-brugernavn/lucas-numbers.git`
4. Klik `OK`

### 3. Push til GitHub

1. Gå til `Git` → `Push` (eller brug `Ctrl+Shift+K` / `⌘⇧K`)
2. Vælg `main`-branch
3. Klik `Push`

Dine lokale commits er nu sendt til GitHub.

### 4. Inspicér repositoriet på GitHub

1. Gå til GitHub i browser og opdater siden
2. Du skal nu kunne se dine filer i repositoriet
3. Klik på commit-antallet for at se din commit-historik

### 5. Diskussion og refleksion

Diskuter følgende:

- Hvad er forskellen på et lokalt repositorie og et remote repositorie på GitHub?
- Hvad betyder det at `origin` er forbundet til GitHub-adressen?
- Hvorfor er det vigtigt at push'e commits til GitHub?
- Hvornår ville du bruge `git push` vs. `git pull`?

---

## Opgave 8 – Klone Lucas-tal-repositoriet

I denne opgave kloner du et eksisterende Lucas-tal-projekt for at undersøge en tidligere implementering.

### 1. Klon repositoriet

Åbn terminal eller Git Bash og kør:

```bash
cd ~/IdeaProjects
git clone https://github.com/EK-DAT-GBG-1SEM-E26AB/lucas-number.git
cd lucas-number
```

Eller via IntelliJ:

1. Gå til `File` → `New` → `Project from Version Control`
2. Vælg `Git`
3. Indsæt URL: `https://github.com/EK-DAT-GBG-1SEM-E26AB/lucas-number.git`
4. Klik `Clone`

### 2. Undersøg eksisterende kode

1. Åbn projektet i IntelliJ
2. Find den fil, der indeholder Lucas-beregning
3. Læs koden nøje — hvilken struktur bruges?
4. Notér dig:
   - Hvordan beregnes Lucas-tallene i dag?
   - Bruges der rekursion, løkke eller anden struktur?
   - Hvor kan implementeringen blive mere elegant?

### 3. Diskussion og refleksion

Diskuter med din gruppe:

- Hvordan var Lucas-beregningen implementeret i den originale kode?
- Hvad er fordele og ulemper ved den nuværende løsning?
- Hvordan kunne en løkke (for, while, eller et tredje valg) gøre implementeringen bedre?
- Hvad betyder det at kunne læse og forstå andres kode?

---

## Opgave 9 – Opret branch og implementer Lucas-beregning med løkke

I denne opgave arbejder du på en feature-branch og refaktorerer Lucas-beregningen til at bruge en løkke.

### 1. Opret en ny branch

I IntelliJ skal du gå til `Git` → `New Branch`:

1. Navngiv branchen: `lucas-loop` (eller `lucas-while`, `lucas-for` osv.)
2. Sørg for at "Checkout branch" er valgt
3. Klik `Create`

Alternativt i terminalen:

```bash
git checkout -b lucas-loop
```

Du er nu på din nye branch og ikke på `main`.

### 2. Implementer Lucas-beregning som løkke

1. Åbn den fil, der indeholder Lucas-funktionen
2. Refaktorér implementeringen så den bruger en løkke (for, while eller et tredje valg)
3. Eksempler:
   - **For-løkke:** `for (int i = 0; i < n; i++) { ... }`
   - **While-løkke:** `while (i < n) { ... }`
4. Behold samme funktionalitet — outputtet skal være identisk
5. Test at koden stadig virker

### 3. Lav commit af ændringen

1. Gå til `Git` → `Commit` (eller `Ctrl+K` / `⌘K`)
2. Vælg den fil du ændrede
3. Skriv commit-besked: `Implementer Lucas-beregning med løkke` eller `Refaktor: brug loop i stedet for rekursion`
4. Klik `Commit`

### 4. Push branchen til GitHub

1. Gå til `Git` → `Push` (eller `Ctrl+Shift+K`)
2. IntelliJ spørger om at pushe til remote — klik `OK`
3. Branchen `lucas-loop` er nu på GitHub

### 5. Inspicér branchen på GitHub

1. Gå til GitHub i browser
2. Klik på "Branch"-dropdown (normalt viser `main` eller `master`)
3. Vælg din `lucas-loop`-branch
4. Du kan nu se din ændring i filen direkte på GitHub

### 6. Diskussion og refleksion

Diskuter følgende:

- Hvad er en branch, og hvorfor er det nyttigt at bruge separate branches?
- Hvordan brugte du branchen til at arbejde uden at påvirke `main`?
- **Note om branch-navngivning:** I større projekter bruges ofte `feature/lucas-loop` eller `refactor/lucas-loop` (med skråstreg for at dele branches efter type), men simple navne som `lucas-loop` virker fint for små projekter.
- Hvornår ville du merge branchen tilbage til `main`?

---

## Opgave 10 – Pair programming — samarbejde med push/pull-cyklus

I denne opgave arbejder du sammen med en anden studerende i et sekventielt push/pull-workflow. Én person laver en lille kodeændring, pusher den, og den anden puller og fortsætter.

### 1. Opsætning — tilføj collaborator

**Person A (repository-ejer):**

1. Gå til GitHub-repositoriet (fx `lucas-numbers` fra opgave 7)
2. Gå til `Settings` → `Collaborators`
3. Klik `Add people` (eller `Invite a collaborator`)
4. Indsæt GitHub-brugernavnet på Person B
5. Send invitation

**Person B (medsamarbejer):**

1. Åbn invitation-emailen fra GitHub
2. Acceptér invitationen
3. Klon repositoriet lokalt:
   ```bash
   git clone https://github.com/PersonA-brugernavn/lucas-numbers.git
   cd lucas-numbers
   ```

### 2. Pair Programming-cyklus

Nu arbejder I sammen på samme branch (`main`) med dette flow:

**Runde 1 — Person A:**

1. Lav en lille kodeændring (fx tilføj kommentar, eller forbedre et variabelnavn)
2. Commit: `git add .` → `Git` → `Commit` med besked fx `Tilføj kommentar til Lucas-funktion`
3. Push: `Git` → `Push` (eller `git push`)

**Runde 1 — Person B:**

1. Pull ændringen: `Git` → `Pull` (eller `git pull`)
2. Læs og forstå Person A's ændring
3. Lav din egen lille ændring/tilføjelse (fx forbedring af variabelnavn eller ny hjælpefunktion)
4. Commit og push

**Runde 2 — Person A:**

1. Pull Person B's ændring: `Git` → `Pull`
2. Læs koden
3. Lav ny ændring, commit og push

**Fortsæt således minimum 4–6 gange** (så hver person laver 2–3 commits)

### 3. Se commit-historikken

Når I er færdige:

1. Åbn `Git` → `Show History` for at se alle commits i rækkefølge
2. Gå til GitHub og klik `commits` for at se den fuldstændige historie
3. Bemærk hvordan hver commit viser forfatter, tidspunkt og besked

### 4. Konflikt-design — hvorfor ingen konflikter?

Fordi I arbejder sekventielt (én ad gangen) og på samme branch uden at dele samme kodelinjer, opstår der ingen merge-konflikter. Git kan automatisk merge indre ændringer.

### 5. Diskussion og refleksion

Diskuter følgende i gruppen:

- Hvordan fungerede kommunikationen mellem jer to under pair programming?
- Hvad var udfordringerne ved at arbejde på samme branch uden branches?
- Hvorfor ville man bruge separate branches (som i opgave 9) hvis man arbejder i grupper?
- Hvis to personer ændrer samme kodelinje samtidig — hvad ville der ske? (Svar: merge-konflikt, som vi undgår her)
- Hvordan kan denne sekventielle workflow bruges til kodereview?

---

## Opgave 11 – Udforsk GitHub webinterface — opret fil direkte

I denne opgave arbejder du direkte på GitHub uden at bruge terminalen eller IntelliJ. Du opretter en dokumentationsfil direkte i browseren.

### 1. Gå til dit repositorie på GitHub

1. Log ind på GitHub
2. Gå til dit `lucas-numbers`-repositorie (eller et af dine øvelse-repositorier)

### 2. Opret ny fil via GitHub

1. Klik `Add File` → `Create new file` (eller klik på `Create new file`-knappen)
2. I feltet "Name your file..." skal du skrive: `LUCAS_INFO.md`
3. I det store tekstfelt skriver du indhold om Lucas-talserien:

```markdown
# Lucas-talserien

Lucas-tallene er en sekvens af heltal, der ligner Fibonacci-tallene men med forskellige startværdier.

## Definition

L(1) = 1
L(2) = 3
L(n) = L(n-1) + L(n-2) for n ≥ 3

## Eksempler

De første Lucas-tal er: 1, 3, 4, 7, 11, 18, 29, 47, ...

## Interessante egenskaber

- Lucas-tallene er relateret til Fibonacci gennem flere matematiske identiteter
- De optræder i matematik, kunst og natur
- De bruges i kryptografi og talteori
```

4. Skrold ned til "Commit new file"
5. Skriv commit-besked: `Tilføj dokumentation om Lucas-talserien`
6. Klik `Commit new file`

GitHub committer nu filen direkte til `main`-branch.

### 3. Pull ændringen lokalt

Nu skal du hente filen ned på din computer:

1. I IntelliJ: `Git` → `Pull` (eller `Ctrl+Alt+L` / `⌘⌥L`)
2. Eller i terminalen: `git pull`

Du kan nu se `LUCAS_INFO.md` i dit projekt lokalt.

### 4. Inspicér filen lokalt

1. Åbn `LUCAS_INFO.md` i IntelliJ
2. Bemærk at det er en almindelig markdown-fil
3. Diskuter: hvad er fordelen ved at have dokumentation i repositoriet?

### 5. Diskussion og refleksion

Diskuter følgende:

- Hvornår er det praktisk at redigere direkte på GitHub (i browser)?
- Hvornår er det bedre at arbejde lokalt (terminal/IntelliJ)?
- Kan GitHub bruges til samarbejde uden at bruge Git lokalt?
- Hvad er fordele og ulemper ved at committe direkte på GitHub?

---

## Opgave 12 – Rediger, introducer og ret stavefejl på GitHub

I denne opgave lærer du at redigere filer direkte på GitHub, og du undersøger hvordan Git holder styr på hver ændring.

### 1. Introducer stavefejl på GitHub

1. Gå til `LUCAS_INFO.md` på GitHub
2. Klik på blyant-ikonet (✏️ "Edit this file")
3. Find en af disse linjer:
   - `Lucas-tallene er en sekvens` → skift til `Luccas-tallene er en sekvans`
   - `L(n) = L(n-1) + L(n-2)` → skift til `L(n) = L(n-1) + L(n-2` (slet sidste parentes)
   - `De første Lucas-tal er` → skift til `De forste Luccas-tall er`
4. Skrold ned til "Commit changes"
5. Skriv commit-besked: `Introducer stavefejl (testformål)`
6. Klik `Commit changes`

### 2. Se commit-historikken

1. Gå til filens historik ved at klikke på `History` eller uret-ikonet
2. Du ser nu to commits:
   - Det oprindelige fra opgave 11
   - Det nye med stavefejlen
3. Klik på det nye commit for at se hvad der blev ændret (diff)

### 3. Ret stavefejlen på GitHub

1. Gå tilbage til `LUCAS_INFO.md`
2. Klik blyant-ikonet igen
3. Ret stavefejlene tilbage til den originale tekst
4. Commit med besked: `Ret stavefejl i LUCAS_INFO.md`

### 4. Inspicér komplet commit-historik

1. Gå til "Commits" eller "History" for `LUCAS_INFO.md`
2. Du ser nu tre commits:
   - Oprettelse (opgave 11)
   - Stavefejl-introduktion
   - Stavefejl-rettelse
3. Klik på hvert commit for at se hvad der blev ændret (rødt og grønt "diff"-visning)

### 5. Pull til din lokale computer

1. I IntelliJ: `Git` → `Pull`
2. Åbn `LUCAS_INFO.md` og bekræft at stavefejlene er rettet

### 6. Diskussion og refleksion

Diskuter følgende:

- Hvad viste Git-historikken dig? Hvordan kan man se præcis hvad der blev ændret?
- Hvorfor er det nyttigt at have hver stavefejl-rettelse som et eget commit?
- Hvis du ville gå tilbage til commit 2 (med stavefejlen), hvordan ville du gøre det?
- Hvordan kan denne "patch by commit"-tilgang bruges til kodereview?
- Hvad lærte du om Git's evne til at spore ændringer?

---

## Ekstra

## Udfordring – Find fejl i lucas-number repositoriet og send Pull Request

I denne udfordring skal du undersøge lucas-number repositoriet fra GitHub, finde en autentisk implementeringsfejl, og lære hvordan du bidrager til projekter uden direkte skrive adgang ved at bruge fork og pull request.

### 1. Klon lucas-number repositoriet

Hvis du ikke allerede har gjort det fra opgave 8, kloner du nu repositoriet:

```bash
cd ~/IdeaProjects
git clone https://github.com/EK-DAT-GBG-1SEM-E26AB/lucas-number.git lucas-number-debug
cd lucas-number-debug
```

Eller åbn det som et nyt projekt i IntelliJ via `File` → `New` → `Project from Version Control`.

### 2. Undersøg koden nøje — diskuter i gruppen

1. Åbn projektet i IntelliJ
2. Find den fil, der indeholder Lucas-implementeringen
3. Læs koden nøje og test programmet
4. Diskuter med gruppen:
   - Hvad virker underligt eller ineffektivt ved implementeringen?
   - Hvad sker der, hvis du prøver at beregne Lucas-tal for større værdier (fx n = 50)?
   - Hvorfor kan det være et problem at arbejde på denne måde?
   - Er der noget, der bliver gjort som ikke burde gøres sådan?

> **Tip:** Prøv at køre programmet og bemærk performance eller anden mærkelig adfærd. Sammenlign også med den loop-baserede løsning fra opgave 9.

### 3. Dokumentér fejlen

Når du har identificeret problemet, skal du dokumentere det:

- Hvad er fejlen eller anti-pattern'et?
- Hvorfor er det et problem?
- Hvordan kan det løses bedre?

Skriv dine svar ned — du bruger dem senere i pull request-beskrivelsen.

### 4. Fork repositoriet på GitHub

Fordi du ikke har skrive adgang til det originale repositorie, må du arbejde via en "fork" (en personlig kopi).

1. Gå til [https://github.com/EK-DAT-GBG-1SEM-E26AB/lucas-number](https://github.com/EK-DAT-GBG-1SEM-E26AB/lucas-number)
2. Klik "Fork"-knappen øverst til højre
3. GitHub opretter nu en personlig kopi af repositoriet på din konto

Du kan nu se repositoriet på: `https://github.com/<dit-brugernavn>/lucas-number`

### 5. Klon din fork lokalt

Nu skal du arbejde med din egen kopi:

```bash
cd ~/IdeaProjects
git clone https://github.com/<dit-brugernavn>/lucas-number.git lucas-number-fork
cd lucas-number-fork
```

> **Bemærk:** Erstat `<dit-brugernavn>` med dit faktiske GitHub-brugernavn.

### 6. Opret en fix-branch

Opret en ny branch til fejlfixingen:

```bash
git checkout -b fix/lucas-performance
```

Eller i IntelliJ: `Git` → `New Branch` → navngiv den `fix/lucas-performance`.

> **Branch-navngivning:** Skråstregen (`fix/`) grupperer branches efter deres formål. Andre eksempler: `feature/`, `refactor/`, `bugfix/`.

### 7. Ret fejlen

1. Åbn filen med Lucas-implementeringen
2. Refaktorér eller ret implementeringen baseret på din analyse fra punkt 3
3. Test at den nye implementering virker korrekt
4. Sørg for at output stadig er det samme (eller bedre/hurtigere hvis det er et performance-problem)

### 8. Lav commit af rettelsen

Når fejlen er rettet:

1. Gå til `Git` → `Commit`
2. Vælg filerne du ændrede
3. Skriv en klar commit-besked, fx:
   - `Fix: refaktor Lucas-beregning for bedre performance`
   - `Refactor: erstat rekursion med løkke for Lucas-sekvens`
   - `Improve: optimér Lucas-talberegning`
4. Klik `Commit`

### 9. Push til din fork

Push ændringerne til din personlige fork på GitHub:

```bash
git push origin fix/lucas-performance
```

Eller i IntelliJ: `Git` → `Push`.

### 10. Opret en Pull Request på GitHub

Nu sender du din rettelse til det originale repositorie:

1. Gå til dit fork-repositorie på GitHub: `https://github.com/<dit-brugernavn>/lucas-number`
2. Du bør se en knap som siger "Compare & pull request" — klik på den
   - Hvis du ikke ser knappen, klik på "Pull requests" og derefter "New pull request"
3. Vælg:
   - **Base repository:** `EK-DAT-GBG-1SEM-E26AB/lucas-number` (det originale)
   - **Base branch:** `main` (eller `master`)
   - **Head repository:** `<dit-brugernavn>/lucas-number` (din fork)
   - **Head branch:** `fix/lucas-performance`
4. Skriv en beskrivelse af din pull request:

```markdown
## Hvad ændrer denne PR?

Denne PR refaktorerer Lucas-talberegningen for at løse [dit problem].

## Problem
[Beskriv fejlen eller problemet her]

## Løsning
[Beskriv hvordan du løste det her]

## Testing
Programmet virker stadig korrekt og output er uændret.
```

5. Klik "Create pull request"

### 11. Undersøg pull request processen

Nu er din rettelse sendt til forfatteren af det originale repositorie:

1. Gå tilbage til det originale repositorie: [https://github.com/EK-DAT-GBG-1SEM-E26AB/lucas-number](https://github.com/EK-DAT-GBG-1SEM-E26AB/lucas-number)
2. Klik på "Pull requests"-tab
3. Du bør se din PR i listen
4. Bemærk:
   - Hvilke oplysninger indeholder PR'en?
   - Hvordan kan repository-ejeren se præcis hvad du ændrede?
   - Hvad ville der ske hvis repository-ejeren accepterer eller afviser den?

### 12. Diskussion og refleksion

Diskuter følgende i gruppen:

- **Om fejlfinding:**
  - Hvad var fejlen eller problemet i den originale implementering?
  - Hvordan opdagede I den?
  - Kunne den have været undgået?

- **Om fork og pull request:**
  - Hvorfor er fork/pull request nyttigt når man arbejder på open source?
  - Hvad er fordelen ved at repository-ejeren *ikke* skal give dig skrive adgang?
  - Hvordan beskytter fork/pull request processen det originale projekt?
  - Hvad kunne repository-ejeren gøre med din PR? (Acceptere, give feedback, afvise?)

- **Om kodereview:**
  - Hvis du var repository-ejer, hvad ville du kigge efter i en pull request?
  - Hvordan sikrer du dig at en PR er god før du accepterer den?
  - Hvilke spørgsmål ville du stille?

- **Om samarbejde uden direkte adgang:**
  - Er det en fordel eller ulempe at skulle bruge fork i stedet for direkte at kunne redigere?
  - Hvordan bruges denne workflow i større open source-projekter?

### 13. Ekstra — peer review

*Hvis I har tid:*

1. **Vælg en anden studerendes PR** og giv feedback
2. **Stil spørgsmål** på GitHub direkte på PR'en (under "Conversation")
3. **Foreslå ændringer** (GitHub har en "Review changes" knap)
4. **Diskutér** hvordan feedback gjøres konstruktiv og hjælpsom
