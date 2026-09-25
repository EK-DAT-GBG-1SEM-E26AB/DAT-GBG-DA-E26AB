# 4. Nødhjælp

[← Git i IntelliJ](README.md)

**Træk vejret.** Git sletter næsten aldrig noget af sig selv. Find din situation herunder og følg
trinene. Kan du ikke finde den – eller virker det ikke – så spring til
[H. Redningsplanen](#h-redningsplanen-når-intet-andet-virker).

| Det, du ser | Gå til |
| --- | --- |
| **Push Rejected** – "Remote changes need to be merged before pushing" | [A](#a-push-rejected) |
| Et vindue, der hedder **Conflicts** | [B](#b-vinduet-conflicts) |
| "Your local changes … would be overwritten" | [C](#c-your-local-changes--would-be-overwritten) |
| Jeg har rodet i koden og vil tilbage til sidste commit | [D](#d-jeg-vil-tilbage-til-sidste-commit) |
| Min kode er væk | [E](#e-min-kode-er-væk) |
| Jeg har committet noget forkert | [F](#f-jeg-har-committet-noget-forkert) |
| Koden kompilerer ikke efter Update Project, eller der står `<<<<<<<` i koden | [G](#g-koden-kompilerer-ikke-efter-update-project) |
| Jeg er helt fast | [H](#h-redningsplanen-når-intet-andet-virker) |

---

## A. Push Rejected

**Hvad er der sket?** En anden har pushet, siden du sidst hentede. GitHub vil ikke tage imod dine
commits, før du har de andres. Det er helt normalt.

1. Klik **Merge**. (Ikke *Rebase*.)
2. IntelliJ henter de andres commits og fletter dem ind i dine.
3. Tre muligheder:
   * **Det gik af sig selv**, og IntelliJ pusher. Færdig.
   * **Det gik af sig selv**, men intet blev pushet: kør programmet, og push igen
     (<kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>K</kbd>).
   * **Vinduet Conflicts** dukker op → [B](#b-vinduet-conflicts).

---

## B. Vinduet Conflicts

**Hvad er der sket?** Du og en anden har ændret de samme linjer. Git spørger, hvad der skal gælde.

Vinduet viser en liste over filer med konflikter og har tre knapper til højre:

| Knap | Gør |
| --- | --- |
| **Accept Yours** | Hele filen bliver *din* version. De andres ændringer i filen forsvinder. |
| **Accept Theirs** | Hele filen bliver versionen *fra GitHub*. Dine ændringer i filen forsvinder. |
| **Merge…** | Du vælger selv, ændring for ændring. **Brug denne til Java-filer.** |

**Er filen i mappen `.idea` eller hedder `*.iml`?** Det er IntelliJ's egne indstillinger, ikke
jeres kode. Vælg **Accept Theirs** – og se [gode vaner](gode-vaner.md#flere-gode-vaner) om, hvorfor
de ikke burde være committet.

### Sådan bruger du Merge…

1. Vælg filen, og klik **Merge…**.
2. Du ser **tre kolonner**:
   * **venstre:** din version
   * **midten:** resultatet – det, filen ender med at indeholde
   * **højre:** versionen fra GitHub
3. Ændringer, der ikke er i konflikt, er farvet grønt/blåt. Konflikter er **røde**.
4. Ved hver ændring er der små knapper:
   * **>>** eller **<<** – tag denne side med i resultatet
   * **×** – drop denne side
5. Skal **begge** med (fx to nye metoder)? Klik **>>** på den ene og **<<** på den anden. Du kan
   også skrive direkte i midterkolonnen.
6. Tip: øverst i vinduet kan du vælge **Apply non-conflicting changes** – så tages alle de nemme
   ændringer på én gang. Ofte har IntelliJ allerede gjort det selv. Tilbage er kun de røde.
7. Når der ikke er flere røde: klik **Apply**.
8. Gør det samme for de andre filer på listen.

### Bagefter – vigtigt

1. **Kør programmet.** Kompilerer det? Virker det? Et flet kan give kode, der ser rigtig ud, men
   ikke kompilerer (fx en metode, der findes to gange).
2. **Commit**, hvis IntelliJ ikke selv har gjort det (<kbd>Ctrl</kbd>+<kbd>K</kbd>). Beskeden er
   skrevet for dig: `Merge branch 'main' of …`. Det er fint.
3. **Push** (<kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>K</kbd>).
4. Sig til den, du havde konflikt med, hvad du valgte.

**Fortryder du midt i det hele?** Luk vinduerne, og vælg **Git → Abort Merge**. Så er du tilbage,
hvor du var før – med dine commits i behold.

---

## C. "Your local changes … would be overwritten"

**Hvad er der sket?** Du har ændringer, der ikke er committet, i filer, som Git skal opdatere.

* **Nemmest:** klik **Cancel**. Commit dine ændringer (<kbd>Ctrl</kbd>+<kbd>K</kbd>), og prøv igen.
* **Eller:** vælg **Smart …**-knappen (fx *Smart Merge*). IntelliJ lægger dine ændringer til side,
  henter, og lægger dem tilbage.

Brug **ikke** knappen, hvor der står *overwrite local changes* – den sletter dit arbejde.

Kommer det ofte? Brug Update Project (<kbd>Ctrl</kbd>+<kbd>T</kbd>) i stedet for Git → Pull. Den klarer
det selv.

---

## D. Jeg vil tilbage til sidste commit

Du har ikke committet endnu, og filen er blevet værre, end den var.

1. **Commit**-vinduet (<kbd>Alt</kbd>+<kbd>0</kbd>).
2. Højreklik på filen → **Rollback…** → **Rollback**.

Hele projektet: højreklik på **Changes** øverst i listen → **Rollback…**.

Rollback kan ikke fortrydes med Git – men Local History ([E](#e-min-kode-er-væk)) har stadig det,
du fjernede.

---

## E. Min kode er væk

Kode forsvinder næsten aldrig. Prøv i denne rækkefølge:

1. **<kbd>Ctrl</kbd>+<kbd>Z</kbd>** i filen – måske er det bare fortryd.
2. **Local History:** højreklik på filen (eller mappen, hvis filen er slettet) →
   **Local History → Show History…**. Find et tidspunkt, hvor koden var der. Højreklik → **Revert**.
3. **Git-historikken:** <kbd>Alt</kbd>+<kbd>9</kbd> → **Log**. Find den commit, hvor koden var der.
   Klik på den, find filen til højre, og dobbeltklik for at se og kopiere den gamle kode.
4. **GitHub:** gå ind på repositoriet i browseren. Har du pushet, ligger den der.

---

## F. Jeg har committet noget forkert

**Ikke pushet endnu:**

1. <kbd>Alt</kbd>+<kbd>9</kbd> → **Log**.
2. Højreklik på din nyeste commit → **Undo Commit…**.
3. Commit'en forsvinder, men ændringerne ligger stadig i dine filer. Ret og commit igen.

**Allerede pushet:**

1. <kbd>Alt</kbd>+<kbd>9</kbd> → **Log**.
2. Højreklik på commit'en → **Revert Commit**.
3. Git laver en ny commit, der fjerner ændringen igen. Push den.

Brug **aldrig** Force Push til at "fjerne" en commit, der er pushet.

---

## G. Koden kompilerer ikke efter Update Project

1. **Søg efter konfliktmærker:** <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>F</kbd> og søg efter
   `<<<<<<<`. Finder du noget, ser det sådan ud:

   ```text
   <<<<<<< HEAD
   System.out.println("Velkommen til Kims filmhylde!");
   =======
   System.out.println("Velkommen til Filmarkivet!");
   >>>>>>> origin/main
   ```

   Nogen har committet en konflikt uden at løse den. Behold den linje, der skal gælde. Slet de
   tre mærkelinjer (`<<<<<<<`, `=======`, `>>>>>>>`). Kør, commit, push.

2. **Ingen mærker?** Så har en i gruppen pushet kode, der ikke kompilerer. Se i Log
   (<kbd>Alt</kbd>+<kbd>9</kbd>), hvem der sidst ændrede filen med fejlen, og sig til. Ret den
   sammen.

3. **Maven-projekt, og IntelliJ kan ikke finde fx JUnit?** Åbn **Maven**-vinduet i højre side, og
   klik på **Reload All Maven Projects** (de to runde pile).

---

## H. Redningsplanen: når intet andet virker

Den virker altid, og du behøver ikke forstå, hvad der gik galt.

1. **Find dine ændringer.** Åbn Commit-vinduet (<kbd>Alt</kbd>+<kbd>0</kbd>), og skriv ned, hvilke
   filer du har ændret.
2. **Gem dem.** Højreklik på projektmappen → **Open In → Explorer** (Mac: *Finder*). Kopiér dine
   ændrede filer over i en mappe på skrivebordet, fx `mine-filer`.
3. **Luk projektet** i IntelliJ (**File → Close Project**).
4. **Omdøb** den gamle projektmappe, fx til `filmsamling-gammel`. Slet den ikke endnu.
5. **Clone** repositoriet på ny (se [bid 9](git-i-intellij.md#bid-9-hent-et-repository-første-gang--clone)).
   Nu har du præcis det, der ligger på GitHub.
6. **Læg dine filer tilbage**: kopiér dem fra `mine-filer` ind i det nye projekt.
   **Men:** har en anden også ændret en af filerne, overskriver du deres arbejde. Så sæt jer
   sammen, og flet filen i hånden. (Markér de to filer i IntelliJ, og tryk <kbd>Ctrl</kbd>+<kbd>D</kbd>
   for at sammenligne dem.)
7. **Kør programmet.** Commit og push.
8. Virker alt? Så kan du slette `filmsamling-gammel`.

---

## Stadig fast?

Spørg, i den her rækkefølge: en i gruppen → tutor → underviser.

Tag et **skærmbillede af hele IntelliJ-vinduet med beskeden**, før du klikker videre. Beskeden er
det vigtigste spor – og den forsvinder, når du klikker.
