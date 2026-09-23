# Scrum i Delfinen

> Sådan arbejder I i sprints i Delfinen. Del af [Delfinen-projektet](readme.md).

## Hvorfor Scrum?

I Adventure og Filmsamling var arbejdet delt op for jer på forhånd. I Delfinen er I fire personer
med ét stort program og tre uger. Uden en plan sker der typisk to ting: alle koder på det samme, og
ingen ved, hvor langt I er, før det er for sent.

**Scrum** er en måde at styre det på. I deler arbejdet op i små, færdige stykker (user stories),
arbejder i korte perioder (sprints) og stopper op med faste mellemrum for at se, om det virker.

> **Tanken i Scrum:** Hellere et lille program, der virker, efter hver sprint, end et stort
> program, der næsten virker, på afleveringsdagen.

Scrum er beskrevet i [The Scrum Guide](https://scrumguides.org/scrum-guide.html)
([dansk udgave som pdf](https://scrumguides.org/docs/scrumguide/v2020/2020-Scrum-Guide-Danish.pdf)).
Den er kort, men I behøver ikke læse den hele. Det, I skal bruge, står på denne side.

---

## Roller

| Rolle | Hvem | Opgave |
|---|---|---|
| **Udviklere** | Alle fire | Skriver kode, tests og diagrammer. Alle er udviklere, også Scrum Master. |
| **Scrum Master** | Én i gruppen pr. sprint | Sørger for, at møderne bliver holdt, at boardet er opdateret, og at forhindringer bliver løst. Er ikke chef og bestemmer ikke mere end de andre. |

I skifter Scrum Master mellem sprint 1 og sprint 2, så to af jer prøver rollen.

### Hvem prioriterer?

I Scrum er det normalt en **Product Owner**, der bestemmer rækkefølgen i backloggen på kundens
vegne. **I Delfinen er der ingen Product Owner udefra.** I prioriterer selv, i fællesskab, ud fra
casen. Stil jer selv to spørgsmål for hver user story:

1. **Hvad skal virke, før noget andet kan virke?** Uden medlemmer ingen kontingenter, og uden
   konkurrencesvømmere ingen top 5.
2. **Hvad har klubben mest brug for?** Formand, kasserer og træner skal alle tre have noget, der
   virker, så hellere en simpel udgave af det hele end en perfekt udgave af én del.

Er I uenige, eller er I i tvivl om, hvad casen betyder, så tag det med til næste **check-in**.
Underviseren er sparringspartner og hjælper jer med at se konsekvenserne, men **beslutningen er
jeres**, og I skal kunne begrunde den.

---

## Backlog og user stories

**Product backlog** er listen over alt, programmet skal kunne: alle user stories, sorteret med det
vigtigste øverst. Den laver I 17-11 ud fra casen og [kravene](readme.md#krav-til-programmet).

**Sprint backlog** er de user stories, I har valgt til den aktuelle sprint.

En user story skrives som I lærte 22-09:

> **Som** kasserer **vil jeg** kunne se, hvem der er i restance, **så** jeg kan rykke dem for
> betaling.

Til hver user story hører **acceptkriterier**, der gør den testbar:

> **Givet** at et medlem ikke har betalt, **når** kassereren åbner restancelisten, **så** står
> medlemmet på listen med medlemsnummer, navn og beløb.

Små user stories er bedre end store. Kan en user story ikke laves af én til to personer på et par
dage, så del den op.

### Opgaver under en user story

En user story kræver ofte flere slags arbejde: kode i domænet, menupunkt i brugerfladen, gem i fil,
unit test, opdatering af diagram. Skriv dem som en tjekliste i user storyens beskrivelse på
boardet. Så kan to personer arbejde på den samme story uden at træde hinanden over tæerne.

### Definition of Done

En user story er først **færdig**, når alt dette er sandt:

* [ ] Alle acceptkriterier er opfyldt, og en anden i gruppen har afprøvet det i programmet.
* [ ] Koden er merget til `main`, og `main` kan stadig bygge og køre.
* [ ] Der er unit tests, hvor der er beregninger (kontingent, top 5, restance).
* [ ] Data bliver gemt og indlæst, hvis storyen ændrer data.
* [ ] Klassediagrammet er opdateret, hvis der er kommet nye klasser.

"Den virker næsten" er ikke færdig. Den bliver stående i *I gang*.

---

## Boardet

Boardet er jeres fælles overblik. Alle skal kunne se, hvad der mangler, hvad der er i gang, og hvem
der laver hvad, uden at spørge.

### GitHub Projects (anbefalet)

Vi anbefaler **GitHub Projects**, fordi det ligger lige ved jeres repo, og I allerede bruger
GitHub.

1. Den, der ejer gruppens repo, går til sin **profil på GitHub → Projects → New project** og vælger
   skabelonen **Board**. Kald projektet noget med Delfinen og jeres gruppenavn.
2. Under **Settings → Manage access** inviteres de tre andre med skriveadgang.
3. Under **Settings** sættes **Visibility** til **Public**, så underviserne kan se boardet.
4. I repoet: **Projects → Link a project**, så boardet kan findes fra repoet.
5. Sørg for, at boardet har disse kolonner (tilføj eller omdøb dem):

   | Product Backlog | Sprint Backlog | I gang | Færdig |
   |---|---|---|---|
   | Alle user stories, vigtigste øverst | Valgt til denne sprint | Nogen arbejder på den nu | Opfylder Definition of Done |

6. Opret hver user story som et kort. Skriv acceptkriterierne og opgave-tjeklisten i kortets
   beskrivelse.
7. Når nogen går i gang med en user story, sætter de sig selv på (**Assignees**) og flytter kortet
   til *I gang*.

Vil I, kan I også tilføje et felt til estimatet (se nedenfor) og et felt til sprinten. GitHubs
egen vejledning er [Quickstart for Projects](https://docs.github.com/en/issues/planning-and-tracking-with-projects/learning-about-projects/quickstart-for-projects).

### Trello

[Trello](https://trello.com/) er også fint. Lav de samme fire lister, gør boardet synligt for
underviserne, og læg linket i repoets `README.md`.

---

## Estimering

Før I vælger user stories til en sprint, skal I have en idé om, hvor store de er. Hold det enkelt:

| Størrelse | Betyder |
|---|---|
| **S** | Under en halv dag for én person |
| **M** | Omkring en dag |
| **L** | To dage eller mere. **Del den op**, hvis I kan. |

Gæt hver for sig, og vis jeres gæt på samme tid. Er I langt fra hinanden, så snak om hvorfor. Det
er sådan, I finder ud af, at én har tænkt på filhåndteringen, og en anden ikke har. Metoden hedder
*planning poker* ([kort video](https://www.youtube.com/watch?v=_jYiuYtrpp8)), og man kan bruge
point i stedet for S/M/L.

Estimaterne bliver forkerte, især i sprint 1. Det gør ikke noget. Til sprint 2 ved I mere om, hvor
meget I kan nå.

---

## Sprintkalenderen

### Sprint 1: onsdag 18-11 – mandag 30-11

| Dag | Hvad |
|---|---|
| man 16-11 | Opstart: grupper, Team Canvas, repo, domænemodel ([Kom i gang](kom-i-gang.md)) |
| tir 17-11 | User stories med acceptkriterier. Product backlog på boardet. |
| **ons 18-11** | **Sprint planning 1** og **check-in** med underviseren (online). Sprinten starter. |
| tor 19-11 | Git branching. Fra i dag arbejder I i branches. |
| fre 20-11, man 23-11, tir 24-11 | Sprint 1. Daily stand-up hver dag. |
| **ons 25-11** | Sprint 1, **check-in** med underviseren (online). |
| tor 26-11 | ITF: projektvejledning (se ITF's plan i itslearning) |
| fre 27-11 | Sprint 1. Gør klar til kode review. |
| **man 30-11** | **Kode review.** Sidste dag i sprint 1. |

### Sprint 2: tirsdag 01-12 – tirsdag 08-12

| Dag | Hvad |
|---|---|
| **tir 01-12** | **Sprint review** af sprint 1, **retrospektiv** og **sprint planning 2** |
| **ons 02-12** | Sprint 2, **check-in** med underviseren (online). |
| tor 03-12, fre 04-12, man 07-12 | Sprint 2. Daily stand-up hver dag. |
| **tir 08-12** | Sidste dag. **Aflevering kl. 23:59** (repo-link og ITF-diasshow). |
| tor 10-12 | ITF: I præsenterer diasshowet for ITF-underviseren |
| fre 11-12 | [Peer review og vejlederfeedback](peer-review.md) – obligatorisk fremmøde |

---

## Møderne

### Daily stand-up – hver projektdag

Hver dag, når I mødes, 10–15 minutter, **stående foran boardet**. Hver af jer svarer på tre
spørgsmål:

1. Hvad lavede jeg siden sidst?
2. Hvad laver jeg nu?
3. Er der noget, der forhindrer mig?

Det er ikke et problemløsningsmøde. Kræver noget en længere snak, tager de berørte den bagefter.
Scrum Master sørger for, at mødet bliver holdt, og at forhindringer bliver fulgt op.

> Er nogen ikke til stede, så skriv de tre svar i gruppens chat. Stand-up er også for den, der
> arbejder hjemme.

### Sprint planning – 18-11 og 01-12

1. **Sprintmål:** Hvad skal programmet kunne efter sprinten, i én sætning? Fx *"Formanden kan
   oprette medlemmer, og kassereren kan se kontingenter. Alt bliver gemt i fil."*
2. **Vælg user stories** fra toppen af product backlog, der passer til målet, og flyt dem til
   *Sprint Backlog*.
3. **Estimér** dem, og stop, når I har nok til sprinten. Hellere færre, der bliver færdige.
4. **Del op** i opgaver, og aftal, hvem der starter på hvad.

Et bud på et realistisk mål for sprint 1 er de fleste krav for **formand og kasserer**, med
**kontingentberegning og unit test** og **gem i fil**. Så er træneren sprint 2. Men det er jeres
beslutning.

### Check-in med underviseren – 18-11, 25-11 og 02-12

På check-in-dagene er **I i lokalet og underviseren online**. Hver gruppe har et kort møde med
underviseren. Hvornår jeres gruppe er på, får I at vide af underviseren. Sid samlet ved én skærm, så alle
fire er med.

Mødet er kort, så vær forberedt. Hav boardet åbent.

| Check-in | Hav klar |
|---|---|
| **18-11** (sprint planning) | Team Canvas. Repoet med alle fire som collaborators. Domænemodellen. Product backlog med user stories og acceptkriterier. Jeres forslag til sprintmål og sprint backlog. Spørgsmål til casen. |
| **25-11** (midt i sprint 1) | Vis det, der virker, i programmet. Hvor er I i forhold til sprintmålet? Hvad driller? Er der user stories, der skal skæres ned eller ud? |
| **02-12** (starten af sprint 2) | Sprintmål og sprint backlog for sprint 2. Hvad I ændrer efter kode review og retrospektiv. Hvad I dropper, hvis tiden bliver knap. |

### Kode review – mandag 30-11

Gruppernes kode bliver gennemgået, så I kan nå at rette op i sprint 2. Hvordan det foregår, står på
[dagens side](../../49/01_man_2026-11-30/README.md). Skriv det, I får at vide, ind som opgaver på
boardet, så det kommer med i sprint planning 2.

### Sprint review – tirsdag 01-12

Hvad blev færdigt i sprint 1? Kør programmet og **vis** det, én user story ad gangen, og gå dens
acceptkriterier igennem. Opfylder den ikke Definition of Done, ryger den tilbage i backloggen.

Sprint 2 slutter med afleveringen 08-12. Peer review 11-12 er det sidste review af det færdige
program.

### Retrospektiv – tirsdag 01-12

Sprint review handler om **produktet**. Retrospektivet handler om **samarbejdet**: hvordan I
arbejder sammen, ikke hvad I har bygget.

Brug 30–45 minutter. En enkel form er **Start – Stop – Fortsæt**:

1. **Hver for sig** (5 min): skriv sedler i tre kolonner:
   * **Start:** hvad skal vi begynde at gøre?
   * **Stop:** hvad skal vi holde op med?
   * **Fortsæt:** hvad virker, og skal vi blive ved med?
2. **Læs op** (10 min): hver læser sine sedler. Ingen diskussion endnu.
3. **Snak** (15 min): hvad går igen? Hvad betyder mest?
4. **Beslut** (5 min): vælg **højst tre konkrete ændringer** til sprint 2, fx *"Vi merger til
   `main` hver dag inden kl. 15"* eller *"Vi holder stand-up, også når nogen er hjemme"*. Skriv
   dem ind i jeres Team Canvas eller øverst på boardet.

Scrum Master styrer tiden. **Snak om arbejdsmåden, ikke om personer**: *"Vi fik ikke merget før
fredag"* i stedet for *"Du mergede ikke"*.

> **Kommer retrospektivet til at gå ud over nogen?** Så tag fat i underviseren. Det er lettere at
> hjælpe, jo tidligere det sker.

---

## Git i sprinten

Fra 19-11 arbejder I sådan:

* **Én branch pr. user story**, fx `opret-medlem` eller `top-fem`.
* Når storyen virker: merge **`main` ind i jeres branch** først, test, og merge så jeres branch
  ind i **`main`**. Så er det jer, der løser konflikterne, og `main` virker altid.
* **Merge ofte.** En branch, der har levet en uge, giver store konflikter.
* Skriv commit-beskeder, der siger, hvad der er sket: *"Beregn kontingent for passive medlemmer"*,
  ikke *"fix"*.

Detaljerne og øvelserne får I på [dagen om Git branching](../../47/04_tor_2026-11-19/README.md).
