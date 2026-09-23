# Projektets krav: user stories og product backlog

## Beskrivelse

I går tegnede I kundens verden: domænemodellen. I dag beskriver I, **hvad programmet skal kunne**
i den verden. Det gør I med **user stories**, som I kender fra 22-09, og **acceptkriterier**, der
gør dem så præcise, at man kan afgøre, om de er færdige.

Til Bogsamling skrev I et par user stories for at øve jer, og i Filmsamling stod de færdige i
opgaven. I Delfinen skriver I dem alle selv, ud fra kundens tekst. Og I skal bruge dem til noget: de bliver til kortene på jeres board,
til jeres **product backlog**, og i morgen vælger I fra toppen af den, hvad I laver i sprint 1.

Dagens spørgsmål er derfor:

* Hvad er en **god** user story, og hvordan skriver man acceptkriterier, der kan testes?
* Hvad gør man med en user story, der er **for stor**?
* Hvordan bliver en bunke user stories til en **prioriteret backlog**, når der ikke er nogen
  Product Owner, der bestemmer rækkefølgen?

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* skrive en user story i formatet *Som ... vil jeg ... så ...* ud fra en kundes tekst
* skrive **acceptkriterier** i formatet *Givet – når – så*, med konkrete tal og **grænsetilfælde**
* skrive acceptkriterier for det, der kan **gå galt**
* vurdere en user story med **INVEST** og forklare, hvad der er galt med en dårlig
* **dele en for stor user story op** i mindre, der hver især giver værdi
* forklare forskellen på en **user story** og en **opgave** (task)
* bygge en **product backlog** og **prioritere** den med begrundelser
* give user stories en grov **størrelse** (S, M, L)

## Se disse videoer før undervisningen:

* [Scrum in 20 mins... (with examples)](https://www.youtube.com/watch?v=SWDhGSZNF9M) (Codex
  Community) – hele Scrum-forløbet på 20 minutter: backlog, sprint planning, stand-up, review og
  retrospektiv. I skal bruge det hele de næste tre uger.
* [How to Estimate User Stories with Planning Poker!](https://www.youtube.com/watch?v=_jYiuYtrpp8)
  – den metode til estimering, der står i [Scrum i Delfinen](../../projekter/delfinen/scrum.md#estimering).

## Læs nedenstående før undervisningen

* Genopfrisk [user stories og acceptkriterier fra 22-09](../../39/02_tir_2026-09-22/README.md#user-stories-i-bogsamling).
* Læs [Scrum i Delfinen](../../projekter/delfinen/scrum.md), især *Backlog og user stories* og
  *Hvem prioriterer?*
* [User stories with examples and a template](https://www.atlassian.com/agile/project-management/user-stories)
  (Atlassian) – kort og godt om, hvad en user story er, og hvad den ikke er.

---

### Genopfriskning: hvad er en user story?

En user story beskriver **én ting, en bruger vil kunne**, og **hvorfor**:

> **Som** *[bruger]* **vil jeg** *[kunne gøre noget]*, **så** *[jeg får denne værdi]*.

Den er skrevet i **brugerens ord**, ikke udviklerens. Der står intet om klasser, filer eller
menuer, kun om det, brugeren vil opnå.

En user story er ikke hele kravet. Den består af tre dele, som ofte kaldes **de tre C'er**:

| | Hvad | I Delfinen |
|---|---|---|
| **Card** | Den korte sætning, der kan stå på et kort | Titlen på kortet på boardet |
| **Conversation** | Snakken om, hvad der egentlig menes | Jeres snak i gruppen og spørgsmål ved check-in |
| **Confirmation** | Acceptkriterierne: hvornår er den færdig? | Beskrivelsen på kortet og i `docs/user-stories.md` |

Kortet er altså en **påmindelse om en samtale**. Det er acceptkriterierne, der gør det præcist.

---

### Eksemplet: Kulturhuset

I går lavede vi domænemodellen af et kulturhus. Nu vil huset have et program til billetlugen:

> *Programchefen opretter events med navn, dato og sal og vil gerne kunne se en liste over de
> kommende events. Billetsælgeren sælger billetter i lugen: i døren på dagen (150 kr.), i forsalg
> (120 kr.) og i forsalg med studierabat (90 kr.). Køber man i forsalg 10 dage eller mere før
> eventet, er der 20 % rabat. Til en studiebillet skal man oplyse sit studiekort-id. Der må ikke
> sælges flere billetter, end der er pladser i salen. Programchefen vil gerne se, hvor mange
> billetter der er solgt til hvert event, og hvor meget eventet har indbragt. Intet må gå tabt,
> når programmet lukkes.*

Nogle af de user stories, man kan skrive:

> **Som** programchef **vil jeg** kunne oprette et event med navn, dato og sal, **så** vi kan
> begynde at sælge billetter til det.

> **Som** billetsælger **vil jeg** kunne se prisen på en billet, før jeg sælger den, **så** jeg
> kan sige den til kunden.

> **Som** programchef **vil jeg** kunne se, hvor meget hvert event har indbragt, **så** jeg kan se,
> hvilke slags events der kan betale sig.

Læg mærke til, at hver user story har **én bruger** og **én ting**, og at *så*-delen siger,
**hvorfor** det er vigtigt. Det er *så*-delen, der hjælper jer, når I skal prioritere.

---

### Acceptkriterier: hvornår er den færdig?

Et acceptkriterium beskriver **én situation** og **det forventede resultat**:

> **Givet** *[en situation]*, **når** *[brugeren gør noget]*, **så** *[sker dette]*.

Et godt acceptkriterium kan besvares med **ja eller nej**, når man afprøver programmet. Derfor
skal det være **konkret**: rigtige tal, rigtige datoer, rigtige beskeder.

| Uklart | Konkret |
|---|---|
| *Så får studerende rabat.* | *Givet et event 10-12-2026, når der sælges en studiebillet 01-12-2026, så er prisen 90 kr.* |
| *Så vises en fejl.* | *Givet at salen har 200 pladser og 200 billetter er solgt, når billetsælgeren prøver at sælge én til, så afvises salget med beskeden "Udsolgt".* |

#### Find grænserne

Reglen *"10 dage eller mere før eventet giver 20 % rabat"* har en **grænse** ved 10 dage. Fejl
gemmer sig næsten altid ved grænserne: skrev programmøren `>` eller `>=`? Derfor skal der være et
acceptkriterium på **hver side** af grænsen:

> **Givet** et event 10-12-2026, **når** der sælges en forsalgsbillet 01-12-2026 (9 dage før),
> **så** er prisen 120 kr.
>
> **Givet** et event 10-12-2026, **når** der sælges en forsalgsbillet 30-11-2026 (10 dage før),
> **så** er prisen 96 kr.

> **Kig efter ord som *over*, *under*, *mindst*, *før*, *efter* og *fra og med* i kundens tekst.**
> Hver af dem er en grænse, og hver grænse skal have et acceptkriterium på begge sider. I Delfinen
> er der grænser ved 18 år og 60 år. Hvor præcist grænserne ligger, står i
> [Reglerne gjort præcise](../../projekter/delfinen/readme.md#reglerne-gjort-præcise).

#### Glem ikke det, der går galt

De fleste user stories har et par **fejltilfælde**, som skal afgøres, før I koder. Spørg:

* Hvad nu, hvis det, brugeren leder efter, **ikke findes**? (Et event-nummer, der ikke findes.)
* Hvad nu, hvis brugeren **taster forkert**? (Bogstaver i stedet for tal, 31-11-2026.)
* Hvad nu, hvis reglerne siger **nej**? (Dørbillet dagen før, udsolgt.)

Skriv kun fejltilfælde, der giver mening for **brugeren**. "Givet at filen er låst ..." er sjældent
et acceptkriterium, men "Givet at programmet har været lukket, når det startes igen, så er alle
events der stadig" er.

#### Acceptkriterier bliver til tests

Et konkret acceptkriterium med tal er næsten en unit test i forvejen:

| Acceptkriterium | Test |
|---|---|
| Givet event 10-12-2026, forsalg 30-11-2026, så 96 kr. | `assertEquals(96, event.calculatePrice(TicketType.PRESALE, LocalDate.of(2026, 11, 30)))` |

Det er derfor, det betaler sig at skrive dem ordentligt nu. Den 23-11 skriver I testene ud fra dem.

---

### INVEST: er det en god user story?

I så INVEST 22-09. Her er det kort, med det, der oftest går galt:

| | Betyder | Typisk fejl |
|---|---|---|
| **I**ndependent | Kan laves uden at vente på andre stories | To stories, der kun virker sammen |
| **N**egotiable | Siger *hvad*, ikke *hvordan* | "... vil jeg have en `ArrayList` af events" |
| **V**aluable | Giver en bruger noget | "Som udvikler vil jeg lave en FileHandler" |
| **E**stimable | Man kan gætte størrelsen | "Programmet skal være brugervenligt" |
| **S**mall | Kan laves af 1–2 personer på et par dage | "Som billetsælger vil jeg kunne administrere billetter" |
| **T**estable | Man kan afgøre, om den er færdig | Ingen acceptkriterier, eller kun vage |

"Independent" er det sværeste i praksis. Man kan ikke sælge billetter til et event, der ikke
findes. Det er i orden: I skal ikke gøre alle stories uafhængige, men undgå, at to stories **kun**
giver mening sammen. Afhængighederne bruger I, når I prioriterer.

---

### Når en user story er for stor

En user story, der er for stor til at blive færdig på et par dage, kaldes en **epic**. Den skal
deles op. Men den skal deles på den **rigtige** måde.

**Forkert: del efter lag i programmet.**

> ~~Lav brugerfladen til billetsalg~~ · ~~Lav Ticket-klassen~~ · ~~Gem billetter i fil~~

Ingen af de tre giver brugeren noget alene. De er **opgaver**, ikke user stories. Hvis kun to af
dem bliver færdige, kan billetsælgeren stadig ikke sælge en billet.

**Rigtigt: del, så hver del stadig er en skive af programmet, som brugeren kan bruge.** Nogle
måder at gøre det på:

| Del efter ... | Eksempel: *"Som billetsælger vil jeg kunne sælge billetter"* |
|---|---|
| **Regler eller tilfælde** | ... sælge en dørbillet · ... sælge en forsalgsbillet med og uden rabat · ... sælge en studiebillet med studiekort-id |
| **Det enkle først** | ... sælge en billet til fast pris · derefter: ... få rabat ved tidligt køb |
| **Glad vej først, fejl bagefter** | ... sælge en billet · derefter: ... få besked, når eventet er udsolgt |
| **Handling** | ... oprette · ... se · ... rette · ... slette |
| **Bruger** | billetsælgerens salg · programchefens overblik over salget |

Hver af de nye stories har sin egen *Som – vil jeg – så* og sine egne acceptkriterier.

> **En god test:** Hvis kun denne ene story bliver færdig i sprinten, kan en bruger så **gøre
> noget**, de ikke kunne før? Hvis ja, er det en user story. Hvis nej, er det en opgave.

#### Opgaver under en user story

Opgaverne forsvinder ikke. De bliver en **tjekliste i user storyens beskrivelse**, som der står
i [Scrum i Delfinen](../../projekter/delfinen/scrum.md#opgaver-under-en-user-story):

```markdown
Som billetsælger vil jeg kunne sælge en forsalgsbillet, så kunden kan sikre sig en plads.

Acceptkriterier:
- Givet event 10-12-2026, når der sælges forsalg 01-12-2026, så er prisen 120 kr.
- Givet event 10-12-2026, når der sælges forsalg 30-11-2026, så er prisen 96 kr.
- Givet et event-nummer, der ikke findes, når der sælges, så får billetsælgeren besked, og intet sælges.

Opgaver:
- [ ] Prisberegning i Event + unit tests
- [ ] Menupunkt "Sælg billet" i UserInterface
- [ ] Gem billetten i filen
- [ ] Opdatér klassediagrammet
```

Så kan to personer arbejde på den samme story: én på beregningen og testene, én på menuen.

---

### Krav, som ingen bruger beder om

Nogle krav er ikke noget, en bruger siger med de ord. *"Intet må gå tabt, når programmet lukkes"*
er et eksempel. I Delfinen er det F14. Der er to måder at få den slags med:

1. **Som en user story** fra den bruger, der mærker det: *"Som programchef vil jeg, at mine events
   stadig er der, når programmet har været lukket, så jeg ikke skal taste dem igen."*
2. **I Definition of Done**, hvis det gælder for alle stories: *"Data bliver gemt og indlæst, hvis
   storyen ændrer data"* (det står allerede i [Delfinens DoD](../../projekter/delfinen/scrum.md#definition-of-done)).

De **ikke-funktionelle krav** (packages, UI adskilt fra logik, robusthed) er ikke user stories.
De gælder for al koden og hører til Definition of Done og til kode review.

---

### Fra user stories til product backlog

**Product backlog** er listen over alle user stories, sorteret med **det vigtigste øverst**. Den
ligger på boardet i kolonnen *Product Backlog*.

På GitHub Projects kan I skrive titlen på et nyt kort i den nederste række i kolonnen og trykke
<kbd>Enter</kbd>. Det bliver et *draft issue*. Klik på titlen for at skrive beskrivelsen, dvs.
acceptkriterierne og opgaverne, og tryk **Save**. Se
[Adding items to your project](https://docs.github.com/en/issues/planning-and-tracking-with-projects/managing-items-in-your-project/adding-items-to-your-project)
hos GitHub.

Skriv også alle user stories i `docs/user-stories.md` i repoet. Den fil skal være opdateret ved
afleveringen, og den er nem at læse for dem, der ikke har adgang til jeres board. Giv hver story
et **nummer** og skriv, hvilke krav den dækker:

```markdown
## US-03 Sælg forsalgsbillet  (dækker: pris, forsalg)   Størrelse: M   Status: Færdig

Som billetsælger vil jeg kunne sælge en forsalgsbillet, så kunden kan sikre sig en plads.

- Givet event 10-12-2026, når der sælges forsalg 01-12-2026, så er prisen 120 kr.
- ...
```

#### Tjek, at alle krav er dækket

Delfinens projektbeskrivelse har kravene **F1–F14**. Lav en lille tabel, der viser, hvilken user
story der dækker hvert krav:

| Krav | User story |
|---|---|
| F1 | US-01 |
| F2 | US-01, US-02 |
| ... | ... |

Står der et krav uden en story, mangler I en. Står der en story uden krav, er det måske en
udvidelse (U1–U9), og så hører den længere nede i backloggen.

---

### Prioritering uden Product Owner

I rigtig Scrum er det **Product Owner**, der bestemmer rækkefølgen i backloggen på kundens vegne.
**I Delfinen er der ingen Product Owner.** I prioriterer selv, i fællesskab. Brug de to spørgsmål
fra [Scrum i Delfinen](../../projekter/delfinen/scrum.md#hvem-prioriterer):

1. **Hvad skal virke, før noget andet kan virke?**
2. **Hvad har kunden mest brug for?**

I Kulturhuset kunne det se sådan ud:

| Plads | User story | Hvorfor her? |
|---|---|---|
| 1 | Opret event | Uden events kan man intet andet |
| 2 | Se listen over events | Hurtig at lave, og man kan se, at oprettelsen virker |
| 3 | Events er der stadig efter genstart | Billig at lave tidligt, dyr at bygge ind til sidst |
| 4 | Sælg forsalgsbillet med pris | Kernen i billetsalget |
| 5 | Sælg dørbillet og studiebillet | Bygger videre på 4 |
| 6 | Se solgte billetter og indtægt pr. event | Programchefens overblik |
| 7 | Afvis salg, når eventet er udsolgt | Vigtig regel, men salget virker uden |
| 8 | Ret et event (udvidelse) | Rart at have |

Læg mærke til plads 3. Gem i fil er ikke det første, en bruger beder om, men det er klogt at lave
det tidligt, **mens der kun er få klasser at gemme**. Jo længere I venter, jo mere skal filformatet
kunne på én gang.

> **Hellere en simpel udgave af det hele end en perfekt udgave af én del.** Programchefen og
> billetsælgeren skal begge have noget, der virker. I Delfinen gælder det formanden, kassereren
> **og** træneren.

Er I uenige om rækkefølgen, så **skriv argumenterne ned** og tag dem med til check-in i morgen.
Underviseren er sparringspartner, men beslutningen er jeres.

---

### Størrelse: S, M eller L

Til sidst giver I hver story en størrelse, som beskrevet i
[Scrum i Delfinen → Estimering](../../projekter/delfinen/scrum.md#estimering): **S** (under en halv
dag), **M** (omkring en dag) eller **L** (to dage eller mere – del den op).

Brug **planning poker**: hver giver sit gæt på samme tid, og hvis I er langt fra hinanden, snakker
I om hvorfor. Det er i den snak, I opdager, at én har tænkt på fejltilfældene og filen, og en anden
ikke har.

---

## Det vigtigste at tage med

* en user story: **Som ... vil jeg ... så ...**, én bruger, én ting, i brugerens ord
* **acceptkriterier** gør den testbar: *Givet – når – så*, med **konkrete tal**
* hver **grænse** i reglerne skal have et acceptkriterium på **begge sider**
* husk det, der kan **gå galt**: findes ikke, forkert input, reglerne siger nej
* en for stor story deles efter **regler, tilfælde og handlinger**, ikke efter lag i koden
* **opgaver** (UI, klasse, fil, test) er en tjekliste **under** en story, ikke stories i sig selv
* backloggen prioriteres efter **afhængigheder** og **værdi**, og I skal kunne begrunde rækkefølgen
* alle krav F1–F14 skal være dækket af mindst én user story

## Aktiviteter i undervisningen

### 1. Opsamling på domænemodellen

Et par grupper viser deres første udgave af Delfinens domænemodel. Hvad er I uenige om? Hvad
spørger I om til check-in i morgen?

### 2. Øvelser

Arbejd med [opgaverne](opgaver.md) to og to. Opgave 1–5 tager ca. halvanden time. Der er
[vejledende løsninger](loesninger.md).

### 3. Delfinens user stories

Saml gruppen og følg [Kom i gang → Tirsdag 17-11](../../projekter/delfinen/kom-i-gang.md#tirsdag-17-11-user-stories):

1. **Skriv user stories**, én bruger ad gangen: formanden, kassereren, træneren.
2. **Skriv acceptkriterier** til hver, med konkrete datoer og beløb, og med grænserne.
3. **Tjek dækningen:** er alle krav F1–F14 dækket?
4. **Læg dem på boardet** i *Product Backlog*, og skriv dem i `docs/user-stories.md`.
5. **Prioritér og giv størrelse** med planning poker.

> **Fordel arbejdet:** Skriv stories to og to (fx ét par til formand og kasserer, ét til træneren),
> og byt så, så det andet par læser og retter jeres acceptkriterier. Man finder flere fejl i
> andres acceptkriterier end i sine egne.

### 4. Klar til i morgen?

Gå tjeklisten [Klar til onsdag 18-11?](../../projekter/delfinen/kom-i-gang.md#klar-til-onsdag-18-11)
igennem, inden I går hjem. I morgen er der sprint planning og det første check-in med
underviseren.
