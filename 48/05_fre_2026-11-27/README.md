# Projektarbejde, sprint 1: gør det færdigt

## Beskrivelse

Dagens undervisning er afsat til [Delfinen](../../projekter/delfinen/readme.md). Det er den sidste hele
arbejdsdag i sprint 1. På mandag er der **kode review**, og tirsdag er der **sprint review**,
**retrospektiv** og **sprint planning 2**.

Dagens tema er at **gøre færdigt**. Ikke at starte på nyt, men at få det, I er i gang med, til at
opfylde **Definition of Done**, merget til `main`, så det tæller med ved sprint review. Og at gøre
koden og dokumentationen klar til, at andre skal se på den på mandag.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* afgøre, om en user story er **færdig** efter Definition of Done
* prioritere at **gøre færdigt** frem for at starte på noget nyt
* gøre et repo klar til, at **andre skal læse og køre** koden
* forberede en **demo** af hver færdig user story til sprint review

## Se disse videoer før undervisningen:

Ingen video i dag.

## Læs nedenstående før undervisningen

* [Scrum i Delfinen → Definition of Done](../../projekter/delfinen/scrum.md#definition-of-done)
* [Scrum i Delfinen → Sprint review](../../projekter/delfinen/scrum.md#sprint-review--tirsdag-01-12)
  og [Retrospektiv](../../projekter/delfinen/scrum.md#retrospektiv--tirsdag-01-12)

---

### Stop med at starte, begynd at gøre færdigt

Sidst i en sprint er det fristende at tage en ny user story fra backloggen, når man er færdig med
sin egen. Lad være, hvis der stadig er stories *I gang*. Hjælp i stedet dem, der er i gang:

* Skriv de tests, der mangler.
* Afprøv en andens story ud fra acceptkriterierne. Definition of Done kræver, at **en anden** har
  prøvet den.
* Opdatér klassediagrammet eller `docs/user-stories.md`.
* Løs den merge-konflikt, som ingen har lyst til.

Tre færdige stories er mere værd end fem, der næsten er færdige. Kun de færdige kan vises ved
sprint review, og kun de færdige tæller.

---

### Er den færdig?

Gå hver story i *I gang* og *Færdig* igennem med Definition of Done fra
[Scrum i Delfinen](../../projekter/delfinen/scrum.md#definition-of-done):

- [ ] Alle acceptkriterier er opfyldt, og **en anden** i gruppen har afprøvet det i programmet.
- [ ] Koden er **merget til `main`**, og `main` kan stadig bygge og køre.
- [ ] Der er **unit tests**, hvor der er beregninger (kontingent, top 5, restance).
- [ ] Data bliver **gemt og indlæst**, hvis storyen ændrer data.
- [ ] **Klassediagrammet** er opdateret, hvis der er kommet nye klasser.

Står en story i *Færdig* og mangler ét punkt, så flyt den tilbage til *I gang*. Bliver den ikke
færdig i dag eller mandag før kode review, så lad den stå. Tirsdag kommer den tilbage i backloggen og med
i sprint planning 2.

> **Ufærdig kode hører ikke hjemme på `main`.** Den bliver på sin branch, committet og pushet, og
> I arbejder videre på den i sprint 2. Merge ned i den, så den ikke kommer for langt bagud.

---

### Gør repoet klar til kode review

På mandag reviewer en anden gruppe jeres kode, og I reviewer deres. Hvordan det foregår, står på
[siden for 30-11](../../49/01_man_2026-11-30/README.md), og tjeklisten
[Gør jeres eget repo klar](../../49/01_man_2026-11-30/README.md#gør-jeres-eget-repo-klar--før-i-møder-op)
skal være gjort, **før** I møder op. Reviewerne ser kun på `main`. De skal kunne **køre**
programmet og **finde rundt** i koden. Gør det klar i dag:

**Kør det**

- [ ] `main` kan klones i en ny mappe og køre derfra, med eksempeldata.
- [ ] Alle tests er grønne på `main`.
- [ ] `README.md` i roden siger, hvordan programmet startes, og hvilke krav der er lavet (se
  [Det skal ligge i repoet](../../projekter/delfinen/readme.md#det-skal-ligge-i-repoet)).

**Find rundt**

- [ ] Klasserne ligger i de aftalte **packages**.
- [ ] Navnene følger jeres kodestil fra Team Canvas, og domænemodellens begreber kan genkendes.
- [ ] Der er ingen udkommenteret kode, gamle `TODO`'er om noget, der er lavet, eller
  `System.out.println` uden for `UserInterface`.
- [ ] Der er ingen gamle branches, der er merget, men ikke slettet.
- [ ] Repoet er **offentligt**, så den anden gruppe kan klone det.
- [ ] I har skrevet ned, **hvad I gerne vil have reviewerne til at se på**, fx *"Vi er i tvivl om,
  hvor restancelisten skal ligge."*

**Kend jeres kode**

Aftal, hvem der kan forklare hvad. Alle fire skal kunne svare på spørgsmål om **hele** programmet,
ikke kun deres egen del. En god øvelse: hver person forklarer en klasse, som **en anden** har
skrevet, til resten af gruppen.

Læs [review-skemaet til 30-11](../../49/01_man_2026-11-30/opgaver.md) igennem. Det er det, reviewerne
kigger efter. Gå det selv igennem på jeres egen kode, og ret det, I kan nå.

---

### Gør klar til sprint review og retrospektiv (tirsdag 01-12)

**Sprint review** handler om **produktet**. I viser hver færdig user story i programmet og går
dens acceptkriterier igennem. Forbered en lille **demo-plan**:

| User story | Hvad vi viser | Data, vi bruger |
|---|---|---|
| Opret medlem | Opret et aktivt senior-medlem, vis det på listen | Et nyt medlem, født 01-03-1990 |
| Kontingent | Vis kontingentet for et medlem på 17, 18, 59 og 60 år | Medlemmerne i eksempeldata |
| ... | ... | ... |

Sørg for, at **eksempeldata** har de medlemmer, I skal bruge til at vise grænserne. Så skal I
ikke taste dem ind foran alle.

Opdatér `docs/user-stories.md`: hvilke stories er **færdige**, og hvilke er **ikke** nået.

**Retrospektivet** handler om **samarbejdet**. Hvis I er begyndt at skrive noter under Start, Stop
og Fortsæt (se [25-11](../03_ons_2026-11-25/README.md#begynd-at-samle-til-retrospektivet)), så
tilføj dagens. Hvis ikke, så skriv hver især tre noter i dag, mens sprinten er frisk.

---

## Det vigtigste at tage med

* sidst i sprinten: **gør færdigt** i stedet for at starte på nyt
* en story er kun færdig, når **hele Definition of Done** er opfyldt
* ufærdig kode bliver på sin branch; **`main` skal kunne køre**
* repoet skal kunne **klones og køres** af andre: README, eksempeldata, grønne tests
* alle fire skal kunne forklare **hele** programmet
* forbered en **demo-plan** til sprint review, og skriv noter til retrospektivet

## Aktiviteter i undervisningen

### 1. Stand-up (15 min)

Gå boardet igennem kort for kort: hvad kan blive færdigt i dag? Hvem hjælper hvem?

### 2. Gør færdigt

Arbejd på de stories, der er i gang. Ingen nye stories, før alle i *I gang* er færdige eller er
flyttet tilbage i backloggen.

### 3. Oprydning og klar til review (sidst på dagen, ca. en time)

Gå tjeklisterne ovenfor igennem sammen. Klon `main` i en ny mappe og kør den som det sidste, I
gør.

### Tjekliste, før I går hjem

- [ ] Alt, der er færdigt, er merget til `main` og pushet.
- [ ] `main` kører fra en frisk klon, og alle tests er grønne.
- [ ] Boardet og `docs/user-stories.md` viser, hvad der er færdigt, og hvad der ikke er.
- [ ] Domænemodellen er rettet, hvis I er blevet klogere, og der er en første udgave af
  klassediagrammet.
- [ ] Alt, der ikke er færdigt, er committet og pushet på sin branch.
- [ ] Alle har skrevet noter til retrospektivet.
