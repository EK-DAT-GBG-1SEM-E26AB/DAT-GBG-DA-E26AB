# Delfinen – aflevering

## Beskrivelse

Sidste dag i sprint 2. **I aften kl. 23:59** er der deadline for Delfinen – semestrets
eksamensprojekt.

Der er ingen ny teori i dag. Dagen er jeres til at:

* rette de sidste fejl – **ingen nye features**
* gøre dokumentationen færdig
* tjekke, at alt ligger i repoet og virker fra et frisk klon
* aflevere repo-link og ITF-diasshow i itslearning – **én aflevering for hele gruppen**

> **Delfinen er obligatorisk.** Projektet er en bunden forudsætning: uden aflevering ingen
> indstilling til eksamen. Så aflevér – også selvom ikke alt virker.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* prioritere de sidste timer, så det vigtigste bliver færdigt
* tjekke jeres program og repo mod en kravliste
* afprøve, at et repo er offentligt, og at programmet kan køre fra et frisk klon
* aflevere et GitHub-link og en fil korrekt som gruppeaflevering i itslearning

## Se disse videoer før undervisningen:

Ingen video i dag. Læs i stedet afsnittet [Aflevering](../../projekter/delfinen/readme.md#aflevering)
i projektbeskrivelsen igennem, så I ved præcis, hvad der skal afleveres.

## Læs nedenstående før undervisningen

---

### Det skal afleveres i aften

Fra [projektbeskrivelsen](../../projekter/delfinen/readme.md#aflevering):

| | |
|---|---|
| **Hvad** | 1. ét **klikbart link** til jeres gruppes GitHub-repository – til repoet som et hele, ikke til en fil eller mappe<br>2. **diasshowet med jeres svar på ITF's opgaver**, uploadet som fil |
| **Hvor** | itslearning → jeres klasserum (E26A eller E26B) → afleveringsopgaven **"Delfinen – aflevering"** |
| **Hvornår** | **tirsdag 08-12-2026 kl. 23:59** |
| **Hvem** | **gruppeaflevering**: én afleverer link og diasshow på vegne af hele gruppen |
| **Obligatorisk** | ja – uden aflevering ingen indstilling til eksamen |

To ting i **samme** aflevering. Det er ikke to afleveringer, og diasshowet skal ikke sendes til
ITF-underviseren på anden vis. I præsenterer det i ITF-lektionen torsdag 10-12.

> **Alle fire skal være med i gruppen i itslearning.** "Delfinen – aflevering" er sat op som en
> gruppeopgave. Den, der afleverer, afleverer for dem, der er i gruppen i itslearning. **Er nogen ikke
> med i gruppen, har de ikke afleveret.** Tjek det, før I afleverer – og sig til underviseren med
> det samme, hvis gruppen i itslearning ikke passer.

---

### Tjekliste: repoet

Fra [Det skal ligge i repoet](../../projekter/delfinen/readme.md#det-skal-ligge-i-repoet):

- [ ] `README.md` i roden med **gruppens navn**, **medlemmer** (kun fornavn + GitHub-brugernavn),
      **link til boardet**, **hvordan programmet startes** og **hvilke krav** (F1–F14, U1–U9) der er
      lavet
- [ ] `docs/` med **domænemodel**, **`user-stories.md`** (hvilke er færdige) og
      **designklassediagram** over det færdige program
- [ ] koden i `src/`, delt i **packages**
- [ ] **JUnit 5-tests** i `test/` – mindst kontingentberegningen: alle fire takster og grænserne ved
      18 og 60 år
- [ ] **eksempeldata** med mindst 10 medlemmer af alle slags, heraf konkurrencesvømmere med tider
- [ ] en `.gitignore`, så `out/`, `.class`-filer og IntelliJ's personlige filer ikke er med
- [ ] **boardet** kan ses af underviserne (offentligt)
- [ ] alle fire har committet – historikken viser det

Bruger I Maven, hedder mapperne `src/main/java` og `src/test/java`. Det vigtige er, at alt ligger i
repoet.

### Tjekliste: programmet

Gå kravene igennem **ved at bruge programmet** – ikke ved at læse koden. Sæt kun kryds, når I har
set det ske. Det er de samme krav som i [projektbeskrivelsen](../../projekter/delfinen/readme.md#funktionelle-krav).

**Formanden**

- [ ] F1 opret medlem med navn, fødselsdato og aktivitetsform
- [ ] F2 en konkurrencesvømmer skal have mindst én disciplin
- [ ] F3 hvert nyt medlem får et unikt medlemsnummer
- [ ] F4 liste over alle medlemmer med nummer, navn, alder, junior/senior og aktivitetsform

**Kassereren**

- [ ] F5 hvert medlems kontingent – beregnet efter reglerne
- [ ] F6 forventet samlet kontingentindtægt
- [ ] F7 registrér, at et medlem har betalt
- [ ] F8 restanceliste med medlemsnummer, navn og beløb

**Træneren**

- [ ] F9 junior- og seniorholdet hver for sig, med discipliner
- [ ] F10 registrér træningstid med dato – ny bedste tid kun, hvis den er bedre, ellers besked
- [ ] F11 registrér stævneresultat: stævne, dato, disciplin, placering og tid
- [ ] F12 én svømmers resultater: bedste træningstid pr. disciplin og alle stævneresultater
- [ ] F13 top 5 for juniorer og for seniorer i en valgt disciplin

**Alle**

- [ ] F14 luk programmet, start det igen – intet er gået tabt
- [ ] programmet går ikke ned på forkert input (se listen fra [02-12](../../49/03_ons_2026-12-02/README.md#dagens-kvalitetstema-vælt-jeres-eget-program))
- [ ] `System.out` og `new Scanner(System.in)` findes kun i brugerflade-klasserne

Mangler noget, så skriv det i `README.md` og i `user-stories.md`. Det er bedre at sige, hvad der
mangler, end at lade læseren finde det.

---

### Tjek, før I afleverer

De fire tjek fra [projektbeskrivelsen](../../projekter/delfinen/readme.md#tjek-før-i-afleverer). Tag
dem i rækkefølge, og tag dem **i dag** – ikke kl. 23:50.

1. **Er repoet offentligt?** Åbn linket i et **privat browservindue**, hvor du ikke er logget ind
   på GitHub. Kan du se koden, er det offentligt. Får du "404", er det privat – ret det under
   **Settings → General → Danger Zone → Change visibility**, eller bed ejeren af repoet om det.
2. **Virker det fra et frisk klon?** Klon repoet i en **ny** mappe og kør programmet derfra. Virker
   det kun på én af jeres computere, er der noget, der ikke er committet – typisk eksempeldata, en
   ny klasse eller JUnit-opsætningen.
3. **Er alle tests grønne?** Kør dem alle fra det friske klon.
4. **Er alt merget til `main`?** Vi ser på `main`, som den er ved deadline. Arbejde, der ligger på
   en branch, bliver ikke set. Tjek listen under **Branches** på GitHub.

---

### Sådan afleverer I

1. **Merge og push** den sidste version til `main`.
2. Gennemfør [Tjek, før I afleverer](#tjek-før-i-afleverer).
3. Kopiér linket til **repoet som et hele** – fx `https://github.com/brugernavn/delfinen` – ikke til
   en fil, en mappe eller en branch.
4. Gem diasshowet som én fil, fx `.pptx` eller `.pdf`, og åbn den én gang for at tjekke, at den
   virker.
5. Tjek, at **alle fire** er med i jeres gruppe i itslearning.
6. **Én** af jer åbner **"Delfinen – aflevering"** i itslearning, indsætter repo-linket som
   **klikbar tekst**, uploader diasshowet og afleverer.
7. Åbn afleveringen igen, og tjek, at både link og fil er med – og at den står som afleveret for
   hele gruppen.

> **Vent ikke til 23:55.** itslearning og GitHub har det med at drille, når man har travlt.
> Aflevér hellere tidligt, og aflevér igen senere, hvis I når at rette noget.

**Bliver I ikke helt færdige**, så aflevér det, I har, inden deadline – og skriv i afleveringen og i
`README.md`, hvad der mangler. Opgaven i itslearning lukker kl. 23:59; der er ingen aflevering efter.
Et program, hvor formandens og kassererens del virker og er testet, er langt bedre end ingen
aflevering.

---

### Efter afleveringen

* **Vent med at rette i koden** til efter peer reviewet fredag 11-12. Reviewet og
  vejlederfeedbacken tager udgangspunkt i den commit, I afleverede, så alle ser den samme version. Filer i `docs/` må gerne
  komme til. Se [Peer review](../../projekter/delfinen/peer-review.md#obligatorisk-fremmøde).
* **Notér commit-hash'en** – de første 7 tegn af den nyeste commit på `main`, når I har afleveret.
  I skal bruge den 11-12.
* **Onsdag 09-12** er der [repetition](../03_ons_2026-12-09/README.md) – og tid til at øve
  ITF-præsentationen.
* **Torsdag 10-12** præsenterer I diasshowet i ITF-lektionen. Aftal, hvem der siger hvad.

---

## Det vigtigste at tage med

* deadline **i aften kl. 23:59** i itslearning → **"Delfinen – aflevering"**
* **to ting i én aflevering**: klikbart link til repoet og ITF-diasshowet som fil
* **gruppeaflevering**: én afleverer – men alle fire skal være i gruppen i itslearning
* repoet skal være **offentligt**, og alt skal være **merget til `main`**
* test fra et **frisk klon** – det, der kun virker hos én, er ikke afleveret
* aflevér hellere tidligt og igen senere end i sidste øjeblik
* vent med at rette i koden til efter peer review 11-12

## Aktiviteter i undervisningen

### 1. Status i gruppen (første kvarter)

Gå [tjeklisten over programmet](#tjekliste-programmet) igennem sammen. Skriv tre lister:

* det, der virker
* det, der mangler, og som **skal** være der
* det, der ville være rart, men som kan undværes

Arbejd **kun** på den midterste liste, indtil den er tom.

### 2. Ret fejl og gør færdigt

Ingen nye features. Underviseren går rundt – **sidder I fast i mere end et kvarter, så spørg**.

### 3. Repo-tjek

Gå [tjeklisten over repoet](#tjekliste-repoet) igennem, og gennemfør de fire
[tjek før aflevering](#tjek-før-i-afleverer).

### 4. Aflevér

Følg [Sådan afleverer I](#sådan-afleverer-i). Tjek, at afleveringen er registreret for hele gruppen,
før I går hjem.
