# Arbejde med Adventure-projekt

## Beskrivelse

I dag er sidste arbejdsdag på Adventure. **I aften kl. 23:59** er der deadline for den endelige
aflevering – [del 5 – Enemies](../../projekter/adventure/del-5-enemies.md) sammen med en pdf med
dokumentation.

Der er ingen ny teori i dag. Hele dagen er jeres til at:

* gøre attack-sekvensen og resten af spillet færdig
* rydde op i koden, så den kan tåle at blive læst af andre
* lave dokumentationen: klassediagram og aktivitetsdiagram i en pdf
* aflevere – **én aflevering for hele gruppen**
* forberede præsentationen i morgen

> **Gruppeaflevering:** én i gruppen afleverer i itslearning på hele gruppens vegne.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* prioritere det, der mangler, så det vigtigste bliver færdigt først
* tjekke dit eget program mod en kravliste
* gennemgå din egen kode med [review-skemaet](../../projekter/adventure/kode-review.md) og rette det,
  du finder
* tegne et klassediagram, der passer med det færdige program
* aflevere et GitHub-link og en pdf korrekt i itslearning

## Se disse videoer før undervisningen:

Ingen video i dag. Læs i stedet afsnittet
[Aflevering](../../projekter/adventure/del-5-enemies.md#aflevering) i del 5 igennem, så I ved
præcis, hvad der skal afleveres.

## Læs nedenstående før undervisningen

---

### Det skal afleveres i aften

Fra [del 5](../../projekter/adventure/del-5-enemies.md#aflevering):

> Der skal afleveres en **pdf** med følgende:
>
> * **Forside**, der indeholder:
>   * Navnet på spillet
>   * Et fængende cover-billede til spillets æske
>   * Link til GitHub-repository til koden – sørg for at linket både er klikbart og udskrevet, så
>     det kan printes ud og tastes ind. (Det GitHub-repository må gerne være det, som I har arbejdet
>     løbende med.)
>   * Navne på samtlige gruppemedlemmer og deres GitHub-brugernavn
> * **Klassediagram** over alle klasser, associationer (inkl. multiplicitet) og arveforhold
> * **Aktivitetsdiagram** over attack-sekvensen

Og om, **hvordan** det afleveres:

> Upload pdf'en som besvarelse på opgaven i itslearning, og indsæt desuden linket til jeres
> GitHub-repository som klikbar tekst i besvarelsen – som ved de tidligere dele.

To ting at hæfte sig ved:

* **Klassediagrammet skal passe med den færdige kode.** Det er dokumentation af det, I har bygget.
  Har I tilføjet en klasse eller en metode i dag, skal den med.
* **Aktivitetsdiagrammet er et designdokument.** Det er det, I tegnede i tirsdags, før I kodede. Det
  behøver ikke at blive rettet, hvis koden endte lidt anderledes.

Alle i gruppen skal kunne stå inde for og forklare både koden og diagrammerne.

> **Adventure er en bunden forudsætning** – alle fem dele. Fra
> [projektbeskrivelsen](../../projekter/adventure/readme.md#obligatorisk-opgave): *"Hvis det ikke
> afleveres, bliver man ikke indstillet til eksamen."* Så aflevér, også selvom ikke alt virker.

---

### Tjekliste: virker spillet?

Gå listen igennem **ved at spille jeres eget spil**. Sæt kun kryds, når I har set det ske.

**Fjender** (fra [del 5](../../projekter/adventure/del-5-enemies.md#krav)):

* [ ] Der ligger fjender i rummene, og de har et kort og et langt navn, en beskrivelse, health og ét
      våben
* [ ] Rumbeskrivelsen viser eventuelle fjender – og når man går ind i et nyt rum, får man som
      minimum at vide, **om** der er fjender
* [ ] `attack troll` angriber fjenden med det korte navn `troll`
* [ ] `attack` uden navn angriber den første fjende i rummet (eller en anden regel, I har valgt –
      bare den er entydig)
* [ ] `attack` i et rum **uden** fjender angriber den tomme luft
* [ ] `attack` med et navn, der **ikke** passer på en fjende i rummet, giver en besked – og der
      bruges **ikke** et skud
* [ ] Uden våben equipped får man at vide, at angrebet mislykkes
* [ ] Med et tømt skydevåben får man at vide, at angrebet mislykkes

**Attack-sekvensen:**

* [ ] Fjenden mister health svarende til våbnets damage, og våbnet bliver brugt
* [ ] Dør fjenden, dropper den sit våben i rummet og forsvinder fra rummet (evt. efterlader den et
      lig som et item)
* [ ] Man kan samle fjendens våben op og equippe det
* [ ] Overlever fjenden, slår den igen med det samme, og spilleren mister health
* [ ] Dør spilleren, er spillet slut, og programmet afsluttes – **også** hvis man spiser sig ihjel i
      giftig mad

**Alt det gamle virker stadig:**

* [ ] `go north`/`east`/`south`/`west`, `look`, `help`, `exit`
* [ ] `take`, `drop`, `inventory` (inkl. hvilket våben der er equipped)
* [ ] `eat` med alle tre udfald, og `health`
* [ ] `equip` – også når tingen ikke er et våben, og når man ikke har den
* [ ] `drop` af det equippede våben betyder, at man ikke længere har et våben equipped

**Koden** (fra [del 4](../../projekter/adventure/del-4-weapons.md#koden) og
[del 5](../../projekter/adventure/del-5-enemies.md#koden)):

* [ ] `Enemy` arver **ikke** fra `Item`
* [ ] `Room` har en særskilt liste til enemies med egne add- og get-metoder
* [ ] `Enemy` har `attack`- og `hit`-metoder
* [ ] `Enemy` opdager **selv**, at den er død, dropper sit våben og fjerner sig fra rummet
* [ ] `Weapon` er abstrakt, og der står ikke `new Weapon(` nogen steder
* [ ] Der står **ikke** `instanceof RangedWeapon` (eller `MeleeWeapon`) nogen steder – kun `Map`
      kender subklasserne af `Weapon`
* [ ] `System.out.println` og `Scanner` findes **kun** i `UserInterface`

> Det sidste punkt er det, der oftest glipper i del 5, fordi der er så mange beskeder. Søg efter
> `System.out` i hele projektet (<kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>F</kbd>, på Mac
> <kbd>Cmd</kbd>+<kbd>Shift</kbd>+<kbd>F</kbd>) og se, hvilke filer der dukker op.

---

### Tjekliste: kodekvalitet før I afleverer

I morgen skal andre kigge på jeres kode. Tag et hurtigt kig selv først, med de vigtigste spørgsmål
fra [review-skemaet](../../projekter/adventure/kode-review.md). Spørgsmål med 💣 skal helst besvares
med **nej**.

**GitHub**

* [ ] Er der en `.gitignore`-fil?
* [ ] 💣 Ligger der uvedkommende filer på GitHub, fx `.class`-filer eller `out`-mappen?
* [ ] Ligger dokumentation og andet, der ikke er kode, i en `docs`-mappe?

**Klasser og attributter**

* [ ] Er klasserne godt navngivet, så man kan regne ud, hvad hver enkelt gør?
* [ ] Er alle attributter `private`?
* [ ] 💣 Er der attributter, der aldrig bruges – eller som burde være lokale variable?
* [ ] 💣 Er der getters og setters, som ingen kalder?

**Metoder og kode**

* [ ] Siger metodernes navne, hvad de gør? Er de skrevet i `camelCase`?
* [ ] Er alle navne på samme sprog (engelsk)?
* [ ] Er koden pænt formateret? (<kbd>Ctrl</kbd>+<kbd>Alt</kbd>+<kbd>L</kbd>, på Mac
      <kbd>Cmd</kbd>+<kbd>Option</kbd>+<kbd>L</kbd>, formaterer den åbne fil i IntelliJ.)
* [ ] 💣 Er der kommentarer, der ikke længere passer, eller udkommenteret kode, der bare ligger og
      fylder?
* [ ] 💣 Er der overflødige kommentarer som `i++; // lægger en til i`?

> **Pas på med oprydning sidst på dagen.** Commit og push først, så I har en fungerende version at
> falde tilbage på. Kør spillet igen efter hver oprydning – det er en refaktorering, så opførslen
> må ikke ændre sig.

Har I `.class`-filer eller `out`-mappen på GitHub, så tilføj `out/` til `.gitignore`. Filer, der
allerede er committet, forsvinder ikke af sig selv – spørg om hjælp, hvis I vil have dem fjernet.

---

### Klassediagrammet

Tegn det **selv**, som I gjorde efter refactor-fasen – ikke autogenereret fra IntelliJ. Et
autogenereret diagram viser alt, men forklarer intet.

Tjek, at det har:

* **alle** klasser – også `Enemy`, `Weapon`, `MeleeWeapon`, `RangedWeapon`, `Food` og eventuelle
  enums
* **arv** med åben trekantpil fra subklasse til superklasse (`Food` → `Item`, `Weapon` → `Item`,
  `MeleeWeapon` → `Weapon`, …)
* **associationer** med **multiplicitet** i begge ender, fx `Room "1" --> "0..*" Enemy`
* `Weapon` markeret som abstrakt (`<<abstract>>` eller navnet i *kursiv*)

Sammenlign med diagrammet i [del 5](../../projekter/adventure/del-5-enemies.md#enemy), men husk, at
jeres skal vise **jeres** program.

---

### Pdf'en

Det er ligegyldigt, hvilket program I laver pdf'en i – Word, Google Docs eller noget helt tredje.
Det vigtige er:

* **Forsiden** har alle fire ting: spillets navn, cover-billede, GitHub-link og navne +
  GitHub-brugernavne
* Linket på forsiden er både **klikbart** og **skrevet helt ud**, så det kan tastes ind fra en
  udskrift
* Diagrammerne kan **læses**: zoom ind i pdf'en og tjek, at teksten i klassediagrammet ikke er
  sløret
* Er aktivitetsdiagrammet tegnet på papir, så tag et skarpt billede af det i godt lys

Læg gerne diagrammerne i en `docs`-mappe i repoet også.

---

### Sådan afleverer I

1. **Commit og push** den sidste version.
2. Åbn repoet på GitHub i browseren, og tjek, at den nyeste commit er der.
3. Kopiér linket til **repoet som helhed** – fx `https://github.com/brugernavn/adventure` – ikke til
   en enkelt fil eller mappe.
4. Åbn linket i et privat browservindue (hvor du ikke er logget ind på GitHub). Får du en fejlside,
   er repoet privat, og så kan underviseren heller ikke se det uden adgang – spørg, hvis I er i tvivl.
5. Upload **pdf'en** i itslearning, og indsæt **GitHub-linket som klikbar tekst** i besvarelsen.
6. **Én** i gruppen afleverer på hele gruppens vegne – det er en gruppeaflevering. Tjek først, at
   alle i gruppen er med i gruppen i itslearning.

> Vent ikke til 23:55. itslearning og GitHub har det med at drille, når man har travlt. Aflevér hellere
> tidligt, og aflevér igen senere, hvis I når at rette noget.

**Bliver I ikke helt færdige**, så aflevér det, I har – og skriv i besvarelsen i itslearning, hvad
der mangler. Et program, der håndterer de fleste udfald af `attack`, er langt bedre end ingen
aflevering.

---

### I morgen: præsentation

I morgen præsenterer grupperne deres færdige spil for holdet. Fra
[del 5](../../projekter/adventure/del-5-enemies.md#feedback) har hver gruppe **10 minutter** til
at:

* køre programmet (vis f.eks. en sjov feature) – brug maks. 2 minutter
* præsentere kode, f.eks. noget I er særligt stolte over
* tage spørgsmål fra resten af holdet (maks. 4 minutter)

Brug en halv time sidst på dagen på at forberede det – se
[fredagens side](../05_fre_2026-10-09/README.md) for, hvad I kan vise.

---

## Det vigtigste at tage med

* deadline **i aften kl. 23:59** – pdf og GitHub-link i itslearning
* afleveringen er en **gruppeaflevering**: én afleverer for hele gruppen
* pdf'en: forside (navn, billede, link klikbart **og** udskrevet, navne + GitHub-brugernavne),
  klassediagram, aktivitetsdiagram
* klassediagrammet skal passe med den **færdige** kode; aktivitetsdiagrammet er et designdokument
* test mod kravlisten ved at **spille** spillet, ikke ved at læse koden
* commit og push, før I rydder op – og kør spillet igen bagefter
* aflevér hellere tidligt og igen senere end i sidste øjeblik

## Aktiviteter i undervisningen

### 1. Status i gruppen (første kvarter)

Gå [tjeklisten over spillet](#tjekliste-virker-spillet) igennem sammen. Skriv tre lister:

* det, der virker
* det, der mangler, og som **skal** være der
* det, der ville være rart, men som kan undværes

Arbejd **kun** på den midterste liste, indtil den er tom.

### 2. Gør koden færdig (formiddag)

Arbejd med [Adventure del 5](../../projekter/adventure/del-5-enemies.md). Følg jeres
aktivitetsdiagram fra tirsdag, og tag én gren af attack-sekvensen ad gangen.

Underviseren går rundt. **Sidder I fast i mere end et kvarter, så spørg** – det er ikke dagen til at
kæmpe alene.

> **Sæt et stoppunkt.** Aftal i gruppen, hvornår I stopper med at lave nye ting – fx ved frokost.
> Derefter retter I kun fejl. Frivillige udvidelser kan vente til efter afleveringen.

### 3. Kvalitetstjek (efter frokost)

Byt skærm med en anden i gruppen, og gå [kodekvalitets-tjeklisten](#tjekliste-kodekvalitet-før-i-afleverer)
igennem på hinandens klasser. Ret det, I finder. Commit og push efter hver ting.

### 4. Dokumentation

Tegn klassediagrammet, find aktivitetsdiagrammet frem fra tirsdag, og saml pdf'en.

### 5. Aflevér

Følg [Sådan afleverer du](#sådan-afleverer-du). Tjek, at **alle** i gruppen har afleveret, før I
går hjem.

### 6. Forbered præsentationen (sidste halve time)

Aftal, hvad I vil vise i morgen, og hvem der siger hvad. Prøv demoen af én gang på den computer, I
vil præsentere fra. Se [fredagens side](../05_fre_2026-10-09/README.md#forbered-jer).
