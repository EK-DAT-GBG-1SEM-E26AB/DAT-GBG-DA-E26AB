# Delfinen – kode review

## Beskrivelse

I dag er sidste dag i sprint 1. I har bygget på Delfinen i knap to uger, og nu skal en **anden
gruppe** kigge jeres kode igennem – og I kigger på deres.

I har prøvet det før: efter Adventure og efter Filmsamling. Men denne gang er der én stor forskel:
**projektet er ikke færdigt.** Alt, hvad reviewerne finder i dag, kan I nå at rette i sprint 2. Det
er derfor, reviewet ligger lige her – mellem de to sprints.

Det, I får at vide i dag, skal med på boardet, så det kommer med i
[sprint planning 2](../../49/02_tir_2026-12-01/README.md) i morgen.

> **Hele gruppe mod hele gruppe.** Underviseren parrer grupperne. To grupper sætter sig sammen, og
> hver gruppe reviewer den anden gruppes projekt – først det ene, så det andet. Resultatet bliver
> et **issue** i den anden gruppes repository på GitHub.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* klone en anden gruppes projekt, finde en bestemt commit og køre programmet og testene
* læse kode, du ikke selv har skrevet, og finde rundt i den ved hjælp af packages og klassenavne
* vurdere et program ud fra de krav, Delfinen stiller: packages, UI adskilt fra logik, test af
  kontingentet, filhåndtering, exceptions, navngivning og Git-historik
* give konkret og brugbar feedback – med klasse, metode og et forslag
* skrive et review som et issue på GitHub
* omsætte den feedback, I selv får, til opgaver på jeres board

## Se disse videoer før undervisningen:

Ingen video i dag. Læs i stedet:

* [Definition of Done](../../projekter/delfinen/scrum.md#definition-of-done) i Scrum i Delfinen
* [Ikke-funktionelle krav](../../projekter/delfinen/readme.md#ikke-funktionelle-krav) i
  projektbeskrivelsen – det er dem, reviewet især handler om
* [review-skemaet](opgaver.md), som I skal udfylde i dag

## Læs nedenstående før undervisningen

---

### Hvorfor review nu?

En fejl i designet er billig at rette tidligt og dyr at rette sent. Ligger al beregning af
kontingent i `UserInterface` efter sprint 1, er det en eftermiddags arbejde at flytte den. Ligger
den der efter sprint 2, er det en eftermiddag **plus** alt det, der er bygget oven på i mellemtiden.

Et review udefra fanger især de ting, man selv er blevet blind for:

* **Navne, der kun giver mening for jer.** I ved, hvad `handle()` gør. Det gør reviewerne ikke.
* **Ting, der kun virker på én computer.** Kan reviewerne ikke køre programmet fra et frisk klon,
  er der noget, der ikke er committet – eller en sti, der kun findes hos jer.
* **Huller i testene.** Tester I kontingentet for en 17-årig og en 30-årig, men ikke for en, der
  fylder 18 i dag?

Og så lærer I mindst lige så meget af at læse **deres** kode. Alle grupper har løst de samme
problemer – ingen har gjort det helt ens.

### Forskellen fra Filmsamling

| | Filmsamling (06-11) | Delfinen (i dag) |
|---|---|---|
| Programmet er | færdigt og afleveret | halvvejs – sprint 1 er ved at slutte |
| Formålet er | at lære til næste projekt | at blive bedre **i sprint 2** |
| Forberedelse | en hel dag uden underviser | samme dag som reviewet |
| Skemaet | Filmsamlingens [kode-review.md](../../projekter/filmsamling/kode-review.md) | [dagens skema](opgaver.md), tilpasset Delfinen |

Fremgangsmåden er den samme som i Filmsamling. Har I glemt, hvordan det foregik, så kig
[Sådan gør I](../../projekter/filmsamling/kode-review.md#sådan-gør-i) igennem.

> **I er ikke færdige – og det ved alle.** Reviewet handler ikke om, hvor meget I har nået, men om
> det, der **er** lavet, er bygget, så resten kan bygges ovenpå. Mangler træner-delen, springer
> reviewerne den over.

---

### Gør jeres eget repo klar – før I møder op

Scrum-kalenderen siger [*"Gør klar til kode review"*](../../projekter/delfinen/scrum.md#sprint-1-onsdag-18-11--mandag-30-11)
fredag 27-11. Har I ikke nået det, så gør det som det første i dag:

* [ ] Alt, der virker, er **merget til `main`** – og `main` kan bygge og køre.
* [ ] `README.md` fortæller, **hvordan programmet startes**, og hvem I er (fornavn og
      GitHub-brugernavn).
* [ ] Der ligger **eksempeldata** i repoet, så reviewerne ikke skal oprette ti medlemmer i hånden.
* [ ] Repoet er **offentligt**, så den anden gruppe kan klone det.
* [ ] I har skrevet ned, **hvad I gerne vil have reviewerne til at se på** – fx *"Vi er i tvivl om,
      hvor restancelisten skal ligge"*. Giv det til reviewerne, før de går i gang.

Branches, der ikke er merget, bliver **ikke** reviewet. Reviewerne ser på `main`.

---

### Dagens forløb

Planen er et **forslag**. Underviseren fortæller ved dagens start, hvilke grupper der er sammen,
og hvor lang tid der er til hver del.

| Del | Hvad | Forslag til tid |
|---|---|---|
| 1 | Find den anden gruppes repo og commit, klon, kør programmet og testene | 20 min |
| 2 | Læs koden og udfyld skemaet – hver gruppe for sig | 60 min |
| 3 | Reviewmødet: runde 1 og runde 2 | 2 × 30 min |
| 4 | Skriv issuet – og læg det, I selv har fået, på jeres board | 20 min |

#### 1. Find, klon og kør

1. Find den anden gruppes repository (linket står på deres board og i deres `README.md` – eller
   spørg dem).
2. Gå til **Commits** på GitHub, og notér de første 7 tegn af hash'en på den **nyeste commit på
   `main`**. Det er den version, I reviewer. Pusher de under reviewet, reviewer I stadig den
   version, I har noteret.
3. Klon repoet i IntelliJ (**File → New → Project from Version Control**). Er der kommet nye
   commits, siden I noterede hash'en, så gå til den: **Git**-vinduet, fanen **Log**, højreklik på
   commit'en → **Checkout Revision**.
4. **Kør programmet.** Prøv det, der står i deres README, at de har lavet.
5. **Kør alle tests.** Skriv antallet ned, og hvor mange der er grønne.

> **Kan programmet ikke køre?** Så er det reviewets første og vigtigste fund. Skriv præcis ned, hvad
> der sker (fejlbeskeden), og læs så koden alligevel. Det er bedre at få det at vide i dag end
> 08-12.

#### 2. Læs koden og udfyld skemaet

Kopiér [skemaet](opgaver.md) ind i en fil (på GitHub: åbn filen og klik **Raw**, så får I
Markdown-teksten). Del afsnittene mellem jer – fx:

| Hvem | Afsnit |
|---|---|
| 1 | GitHub og Git · Kør programmet og testene |
| 2 | Packages · Filhåndtering |
| 3 | Domæneklasserne · Kontingent og tests |
| 4 | Exceptions og robusthed · Navngivning og kodestil |

Gå fundene igennem **sammen**, før mødet. Bliv enige om de **tre vigtigste ting at rette** og om
det, der er **særlig godt**.

#### 3. Reviewmødet

De to grupper sidder sammen. Alle fra begge grupper er med hele tiden.

* **Runde 1:** gruppe A reviewer gruppe B's projekt. Gruppe B er **programmører** og forklarer.
* **Runde 2:** rollerne byttes.

I hver runde har én fra reviewer-gruppen projektet åbent på en skærm, som alle kan se, og en anden
skriver noter i skemaet. Reviewerne går skemaet igennem, spørger *"hvorfor gjorde I sådan?"*, og
slutter med de tre vigtigste ting og det, der er godt.

**Programmørerne svarer og tager noter.** Man behøver ikke være enig i alt – men forstå det, før I
afviser det.

#### 4. Issuet – og jeres eget board

Reviewer-gruppen afleverer skemaet som et **issue** i den anden gruppes repository:

1. I deres repository: fanen **Issues → New issue**.
2. Titel: `Kode review fra gruppe ...` (jeres gruppenavn).
3. Indsæt det udfyldte skema, og klik **Create**.

Afkrydsningsfelterne `- [ ]` og `- [x]` bliver vist som rigtige checkbokse. Se GitHubs vejledning
[Creating an issue](https://docs.github.com/en/issues/tracking-your-work-with-issues/using-issues/creating-an-issue).

> **Kan I ikke oprette issues?** Så har ejeren af repoet slået *Issues* fra. Ejeren slår dem til
> under **Settings → General → Features → Issues**.

Når **I selv** har fået et issue:

* Læs det i gruppen.
* Lav et kort på boardet for hver ting, I vil rette – i hvert fald de tre vigtigste. I GitHub
  Projects kan I tilføje selve issuet til boardet (i issuets højre side: **Projects**).
* Er I uenige i et punkt, så skriv det som en kommentar på issuet med en begrundelse. Det er også
  et svar.

I morgen [prioriterer I kortene](../../49/02_tir_2026-12-01/README.md) sammen med resten af
backloggen.

---

### Hvad kigger I især efter?

Skemaet er langt. Her er de punkter, der betyder mest i Delfinen – og **hvorfor**.

#### Packages og UI adskilt fra logik

Kravet er, at kun brugerflade-klasserne læser fra tastaturet og skriver i konsollen. Søg efter
`System.out` og `new Scanner(System.in)` i hele projektet
(<kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>F</kbd>, på Mac <kbd>Cmd</kbd>+<kbd>Shift</kbd>+<kbd>F</kbd>)
og se, hvilke filer der dukker op. Dukker en domæneklasse op, er det et fund.

Hvorfor det betyder noget: en metode, der **udskriver** restancelisten, kan ikke testes, og den kan
ikke bruges, hvis klubben en dag vil have en hjemmeside i stedet for en konsol. En metode, der
**returnerer** restancelisten, kan begge dele.

Kig også i `import`-linjerne øverst i domæneklasserne: importerer de noget fra `ui`-packagen,
kender domænet brugerfladen, og så går afhængigheden den forkerte vej.

#### Kontingent og tests

Kontingentet er Delfinens vigtigste beregning, og kravet er unit tests af **alle fire takster og
grænserne ved 18 og 60 år**. Kig efter:

* **Hvor ligger beregningen?** Hos den klasse, der har fødselsdato og aktivitetsform (Information
  Expert) – ikke i `UserInterface` og ikke i `FileHandler`.
* **Kan den testes på en bestemt dato?** Sammenlign de to udgaver:

```java
// Svær at teste: svaret afhænger af, hvilken dag testen bliver kørt
public int calculateFee() {
    long age = ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    // ...
}

// Let at teste: testen bestemmer selv datoen
public int calculateFee(LocalDate date) {
    long age = ChronoUnit.YEARS.between(birthDate, date);
    // ...
}
```

Med den første udgave kan en test, der er grøn i dag, blive rød, når et testmedlem har fødselsdag.
Det er [tippet i projektbeskrivelsen](../../projekter/delfinen/readme.md#ikke-funktionelle-krav).

* **Er grænserne testet på selve fødselsdagen?** Dagen før 18-års fødselsdagen er man junior, på
  dagen er man senior. En test med en 17-årig og en 30-årig fanger ikke en `>` i stedet for `>=`.

#### Filhåndtering

* Er al læsning og skrivning samlet i **én** klasse – og ved resten af programmet intet om
  semikolon og feltrækkefølge?
* Overlever et medlem en tur gennem filen – med æ, ø, å, fødselsdato, betalt/ikke betalt og
  discipliner?
* Starter programmet med en tom klub, hvis filen mangler? (Omdøb filen, og prøv.)
* **Hvornår** gemmes der? Kun ved afslutning betyder, at alt er væk, hvis programmet går ned
  undervejs. Det er ikke nødvendigvis forkert – men gruppen skal kunne forklare valget.

#### Exceptions og robusthed

Prøv at vælte programmet: bogstaver, hvor der skal stå et tal, `31-13-2010` som fødselsdato, et
medlemsnummer, der ikke findes. Går programmet ned, så skriv ned, **hvad** I tastede, og **hvor**
det skete.

Kig også i koden efter tomme `catch`-blokke og `catch`, der kun kalder `printStackTrace()`. Så
forsvinder fejlen uden at brugeren får noget at vide.

#### Navngivning

* Kan man genkende **domænemodellens** begreber i klassenavnene – eller hedder klasserne `Data`,
  `Manager` og `Type2`?
* Er navnene på **engelsk** hele vejen igennem, som kravet siger?
* Hedder UI-metoderne ens, fx alle `showXxx`, eller er der både `show`, `print` og `display`?

#### Git-historikken

* **Committer alle fire?** Se fanen **Insights → Contributors** på GitHub
  ([vejledning](https://docs.github.com/en/repositories/viewing-activity-and-data-for-your-repository/viewing-a-projects-contributors)),
  eller listen over commits. Kravet er, at alle fire committer.
* **Bruger de branches?** Fra 19-11 skulle arbejdet foregå i branches, én pr. user story. Se listen
  under **Branches** – og i commit-historikken, om der bliver merget.
* **Siger commit-beskederne noget?** *"Beregn kontingent for passive medlemmer"* er brugbart. *"fix"*
  og *"asdf"* er ikke.

---

### Sådan giver I god feedback

Det samme som til [Adventure-reviewet](../../41/05_fre_2026-10-09/README.md#sådan-giver-i-god-feedback):

* **Vær konkret.** *"`calc()` i `Member` burde hedde `calculateFee()`"* – ikke *"navnene er
  dårlige"*.
* **Tal om koden, ikke om personerne.** *"Metoden er lang"* – ikke *"I skriver lange metoder"*.
* **Spørg, før I dømmer.** *"Hvorfor gemmer I alderen og ikke fødselsdatoen?"* åbner en samtale.
* **Ros det, der er godt.** Det er lige så vigtigt at vide, hvad man skal blive ved med.
* **Prioritér.** Tre ting, der gør en forskel, er mere værd end tyve småting. Det er derfor, skemaet
  slutter med *de tre vigtigste*.

---

### Sprint 1 slutter i dag

Når reviewet er slut, er sprint 1 slut. Brug den sidste tid på at gøre klar til i morgen:

* Opdatér boardet: det, der opfylder [Definition of Done](../../projekter/delfinen/scrum.md#definition-of-done),
  står i *Færdig*. Resten står **ikke** i *Færdig* – heller ikke "den, der næsten virker".
* Aftal, hvad I vil **vise** til sprint review i morgen: én user story ad gangen, med
  acceptkriterierne ved siden af.
* Tag Team Canvas'en frem. I skal bruge den til retrospektivet.

---

## Det vigtigste at tage med

* reviewet ligger **mellem** sprintene, så I kan nå at rette det, I finder
* reviewerne ser på den **commit på `main`**, de har noteret – ikke på jeres branches
* kan programmet ikke køre fra et frisk klon, er det dagens vigtigste fund
* søg efter `System.out` og `Scanner` – de hører kun hjemme i brugerfladen
* kontingentet skal kunne testes på en **bestemt dato** og være testet **på** fødselsdagene
* god feedback er konkret, handler om koden og slutter med **de tre vigtigste ting**
* feedbacken bliver til **kort på boardet** – ellers bliver den ikke til noget

## Aktiviteter i undervisningen

### 1. Gør jeres eget repo klar (første kvarter)

Gå [tjeklisten](#gør-jeres-eget-repo-klar--før-i-møder-op) igennem. Merge det, der virker, til
`main`, og skriv ned, hvad I gerne vil have reviewerne til at se på.

### 2. Review af den anden gruppes kode

Underviseren parrer grupperne. Følg [Dagens forløb](#dagens-forløb) og udfyld
[skemaet](opgaver.md).

### 3. Reviewmødet

To runder – én for hver gruppes projekt. Del tiden ligeligt.

### 4. Issue og board

Opret issuet i den anden gruppes repository. Læs det issue, I selv har fået, og lav kort på
boardet.

### 5. Afslut sprint 1

Opdatér boardet, og gør klar til sprint review og retrospektiv i morgen.
