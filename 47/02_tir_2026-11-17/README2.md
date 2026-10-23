# Scrum: Fra kundekrav til Product Backlog

## Beskrivelse

I går arbejdede I med Delfinens domæne og begyndte at forstå kundens verden. I dag tager I næste
skridt: Kundens krav skal omsættes til en **Product Backlog**, som gruppen kan bruge til at
planlægge og styre projektet.

Dagens arbejde handler derfor både om:

- **Scrum-processen**
- **user stories**
- **acceptkriterier**
- **prioritering**
- **estimering**
- forberedelse til morgendagens **Sprint Planning**

I skal ikke begynde at programmere alle kravene i dag. I skal skabe et fælles overblik over,
**hvad produktet skal kunne**, og gøre de vigtigste dele klar til den første sprint.

Scrum-forløbet i Delfinen ser overordnet sådan ud:

```text
Kundens krav
    ↓
Product Backlog
    ↓
Sprint Planning
    ↓
Sprint Backlog og sprintmål
    ↓
Udvikling og Daily Scrum
    ↓
Færdigt produktinkrement
    ↓
Sprint Review og Retrospective
    ↓
Tilpasset Product Backlog og næste sprint
```

I dag arbejder I især med det andet trin: **Product Backlog**.

I morgen vælger I under Sprint Planning de vigtigste user stories fra Product Backlog og flytter
dem til sprintens **Sprint Backlog**.

---

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

### Scrum

- forklare formålet med Scrum i et udviklingsprojekt
- forklare forskellen på **Product Backlog** og **Sprint Backlog**
- beskrive sammenhængen mellem Product Backlog, Sprint Planning, sprinten og det færdige
  produktinkrement
- forklare ansvaret hos **Developers**, **Scrum Master** og **Product Owner**
- forklare, hvordan projektet tilpasses, når der ikke er en ekstern Product Owner
- forklare, hvordan gruppens **Definition of Done** bruges
- bruge et Scrum-board til at skabe fælles overblik over projektet

### User stories og backlog

- skrive en user story i formatet *Som ... vil jeg ... så ...*
- skrive konkrete og testbare acceptkriterier i formatet *Givet – når – så*
- finde grænsetilfælde og relevante fejltilfælde
- vurdere user stories ved hjælp af INVEST
- dele en for stor user story op i mindre stories
- kende forskel på en user story og en teknisk opgave
- prioritere en Product Backlog ud fra værdi og afhængigheder
- estimere user stories som S, M eller L ved hjælp af planning poker

---

## Forberedelse

### Se disse videoer før undervisningen

- [Scrum in 20 mins... (with examples)](https://www.youtube.com/watch?v=SWDhGSZNF9M)  
  Videoen viser hele Scrum-forløbet: Product Backlog, Sprint Planning, sprint, Daily Scrum,
  Sprint Review og Retrospective.

- [How to Estimate User Stories with Planning Poker!](https://www.youtube.com/watch?v=_jYiuYtrpp8)  
  Videoen viser den metode, I skal bruge til at estimere jeres user stories.

### Læs før undervisningen

- [Scrum i Delfinen](../../projekter/delfinen/scrum.md)
- [User stories og acceptkriterier fra Bogsamling](../../39/02_tir_2026-09-22/README.md#user-stories-i-bogsamling)
- [User stories with examples and a template](https://www.atlassian.com/agile/project-management/user-stories)
- [The Scrum Guide](https://scrumguides.org/scrum-guide.html)

---

# Del 1: Scrum-processen i Delfinen

## Hvorfor bruger vi Scrum?

Delfinen er større end de projekter, I tidligere har arbejdet med. Fire personer skal arbejde på
det samme program over flere uger.

Uden en fælles proces opstår der let problemer:

- flere arbejder i de samme filer uden at vide det
- gruppen starter på for mange ting samtidig
- vigtige krav bliver glemt
- opgaver er næsten færdige, men ikke færdige nok til at kunne bruges
- gruppen opdager for sent, at planen ikke holder

Scrum hjælper gruppen med at:

1. skabe ét fælles overblik over arbejdet
2. prioritere det vigtigste
3. arbejde i korte, afgrænsede sprints
4. gøre små dele helt færdige
5. kontrollere resultatet undervejs
6. forbedre både produktet og samarbejdet

> **Grundtanken:** Det er bedre at have en lille del af programmet, der virker, end et stort
> program, der næsten virker.

---

## Roller og ansvar

Scrum beskriver tre centrale ansvarsområder:

| Ansvar | I Scrum | I Delfinen |
|---|---|---|
| **Product Owner** | Prioriterer Product Backlog og arbejder for størst mulig produktværdi | Der er ingen ekstern Product Owner. Gruppen prioriterer i fællesskab ud fra casen |
| **Scrum Master** | Hjælper gruppen med at anvende Scrum og fjerne forhindringer | Én studerende har rollen i hver sprint. Rollen går på skift |
| **Developers** | Planlægger og udfører arbejdet, så sprinten giver et færdigt produktinkrement | Alle gruppens medlemmer er udviklere, også den studerende, der er Scrum Master |

Scrum Master er ikke gruppens chef og fordeler ikke alle opgaverne. Scrum Master hjælper gruppen
med at:

- holde de aftalte Scrum-møder
- holde boardet opdateret
- synliggøre forhindringer
- holde fokus på sprintmålet
- sikre, at gruppen følger sin Definition of Done

### Når der ikke er en Product Owner

I Delfinen har I ikke en ekstern Product Owner, der prioriterer backloggen for jer.

Gruppen skal derfor blive enig om prioriteringen. Brug især disse spørgsmål:

1. Hvad skal fungere, før andre funktioner kan fungere?
2. Hvilke funktioner giver størst værdi for svømmeklubbens brugere?
3. Hvordan sikrer vi, at formand, kasserer og træner alle får noget brugbart?
4. Hvilke tekniske risici bør vi undersøge tidligt?
5. Hvad kan vi realistisk gøre helt færdigt?

Underviseren kan hjælpe med at afklare casen og udfordre jeres valg, men gruppen træffer
beslutningen og skal kunne begrunde den.

---

## Scrums artefakter

### Product Backlog

Product Backlog er den prioriterede liste over alt det, produktet forventes at skulle kunne.

Den indeholder blandt andet:

- user stories
- acceptkriterier
- fejltilfælde
- estimeringer
- eventuelle forbedringer og udvidelser

Product Backlog er ikke statisk. Den bliver løbende ændret, når gruppen lærer mere om produktet.

### Sprint Backlog

Sprint Backlog indeholder det arbejde, gruppen har valgt til den aktuelle sprint.

Den består af:

- sprintmålet
- de valgte user stories
- gruppens plan og tekniske opgaver

I må ikke flytte alle user stories til Sprint Backlog. I skal kun vælge det, I realistisk kan
gøre færdigt i sprinten.

### Produktinkrement

Et produktinkrement er den samlede del af produktet, der er færdig og anvendelig efter sprinten.

En samling ufærdige klasser er ikke et produktinkrement. Programmet skal bygge, kunne køre og
opfylde gruppens Definition of Done.

---

## Definition of Done

En user story er først færdig, når gruppens Definition of Done er opfyldt.

I Delfinen betyder det mindst:

- [ ] Alle acceptkriterier er opfyldt.
- [ ] En anden i gruppen har afprøvet funktionaliteten.
- [ ] Koden er merget til `main`.
- [ ] Projektet på `main` kan bygge og køre.
- [ ] Relevante beregninger er dækket af unit tests.
- [ ] Data bliver gemt og indlæst, hvis funktionaliteten ændrer data.
- [ ] Klassediagrammet er opdateret, hvis designet er ændret.

En user story, der er "næsten færdig", er ikke færdig. Den må ikke flyttes til kolonnen
*Færdig*.

---

## Scrums events i projektet

| Event | Formål | Hvornår i Delfinen? |
|---|---|---|
| **Sprint Planning** | Beslutte sprintmål, vælge stories og planlægge arbejdet | 18-11 og 01-12 |
| **Daily Scrum** | Kontrollere fremdriften mod sprintmålet og tilpasse dagens plan | Hver projektdag |
| **Sprint Review** | Gennemgå det færdige produkt og få feedback | Efter sprint 1 |
| **Sprint Retrospective** | Forbedre gruppens arbejdsproces | Efter sprint 1 |

Dagens arbejde med at skrive, opdele, prioritere og estimere user stories kaldes ofte
**backlog refinement**.

Backlog refinement er ikke et selvstændigt Scrum-event. Det er en aktivitet, som gør Product
Backlog klar til Sprint Planning.

---

# Del 2: Fra kundekrav til user stories

## User stories

En user story beskriver én ting, en bruger ønsker at kunne gøre, og hvorfor det har værdi:

> **Som** *[bruger]* **vil jeg** *[kunne gøre noget]*, **så** *[jeg opnår en værdi]*.

Eksempel:

> **Som** kasserer **vil jeg** kunne se medlemmer i restance, **så** jeg kan kontakte dem om
> deres manglende betaling.

En user story beskriver brugerens behov. Den beskriver ikke programmets klasser og metoder.

### De tre C'er

| Del | Betydning | I Delfinen |
|---|---|---|
| **Card** | Den korte user story | Kortet på boardet |
| **Conversation** | Samtalen om kravets betydning | Gruppens drøftelse og spørgsmål til underviseren |
| **Confirmation** | Betingelserne for at godkende resultatet | Acceptkriterierne |

User storyens tekst er derfor ikke hele kravet. Gruppen skal også tale om storyen og skrive
acceptkriterier.

---

## Acceptkriterier

Acceptkriterier beskriver, hvordan gruppen kan afgøre, om en user story virker.

Brug formatet:

> **Givet** *[en bestemt situation]*, **når** *[brugeren udfører en handling]*,  
> **så** *[kommer et konkret resultat]*.

Eksempel:

> **Givet** et aktivt seniormedlem på 25 år, **når** kontingentet beregnes,  
> **så** er det årlige kontingent 1.600 kr.

Et godt acceptkriterium:

- bruger konkrete værdier
- kan afprøves
- har ét forventet resultat
- kan besvares med ja eller nej
- kan senere omsættes til en test

### Grænsetilfælde

Når en regel indeholder ord som *under*, *over*, *mindst*, *højst*, *før* eller *efter*, skal I
skrive acceptkriterier på begge sider af grænsen.

Hvis kontingentet ændres ved 18 år, skal I eksempelvis undersøge:

- et medlem på 17 år
- et medlem på præcis 18 år
- eventuelt et medlem på 19 år

### Fejltilfælde

Overvej også:

- Hvad sker der, hvis medlemmet ikke findes?
- Hvad sker der ved ugyldigt input?
- Hvad sker der, hvis en handling ikke er tilladt?
- Hvad sker der, hvis data mangler?

Fejltilfældene skal beskrives ud fra det, brugeren oplever.

---

## INVEST

Brug INVEST til at vurdere, om en user story er egnet til backloggen:

| Bogstav | Betydning | Kontrolspørgsmål |
|---|---|---|
| **I** | Independent | Kan storyen så vidt muligt laves og demonstreres selvstændigt? |
| **N** | Negotiable | Beskriver den behovet uden at fastlåse den tekniske løsning? |
| **V** | Valuable | Giver den værdi for en bruger? |
| **E** | Estimable | Forstår gruppen den godt nok til at estimere den? |
| **S** | Small | Kan den gøres færdig på højst et par dage? |
| **T** | Testable | Kan gruppen afgøre, om den er færdig? |

En stor user story kaldes ofte en **epic**. Den skal deles op, før den vælges til en sprint.

Del efter funktionalitet eller brugerens handlinger – ikke efter programmets lag.

### Ikke en god opdeling

- lav brugerfladen
- lav domæneklassen
- lav filhåndteringen

Det er tekniske opgaver. Ingen af dem giver alene brugeren ny funktionalitet.

### Bedre opdeling

- opret et medlem
- vis medlemmer
- redigér et medlem
- registrér et træningsresultat
- vis top fem for en disciplin

Hver del giver brugeren noget, der kan demonstreres og godkendes.

---

# Del 3: Byg Product Backlog

## Boardet

Boardet skal som minimum have disse kolonner:

| Product Backlog | Sprint Backlog | I gang | Færdig |
|---|---|---|---|
| Alle prioriterede stories | Valgt til sprinten | Arbejdet er startet | Definition of Done er opfyldt |

Hvert kort skal som minimum indeholde:

- nummer og kort titel
- user story
- acceptkriterier
- estimering
- hvilke krav storyen dækker
- en tjekliste med tekniske opgaver

Eksempel:

```markdown
## US-03 Beregn kontingent

Som kasserer vil jeg kunne beregne et medlems kontingent, så medlemmet opkræves det korrekte beløb.

Dækker: F5 og F6  
Størrelse: M

Acceptkriterier:
- Givet et aktivt juniormedlem på 17 år, når kontingentet beregnes, så er beløbet ...
- Givet et aktivt seniormedlem på 18 år, når kontingentet beregnes, så er beløbet ...
- Givet et passivt medlem, når kontingentet beregnes, så er beløbet ...

Opgaver:
- [ ] Implementér beregningen i domænelaget
- [ ] Skriv unit tests til grænserne
- [ ] Tilføj funktionaliteten til brugergrænsefladen
- [ ] Sørg for, at data kan gemmes
- [ ] Opdatér klassediagrammet
```

De tekniske opgaver er ikke selvstændige user stories. De ligger under den user story, de hjælper
med at gøre færdig.

---

## Dæk alle krav

Delfinens projektbeskrivelse indeholder kravene F1–F14.

Lav en tabel i `docs/user-stories.md`:

| Krav | Dækkes af |
|---|---|
| F1 | US-01 |
| F2 | US-01 og US-02 |
| ... | ... |
| F14 | US-XX |

Kontrollér derefter:

- Har alle krav mindst én user story?
- Er der stories, som ikke dækker et krav?
- Er disse stories nødvendige, eller er de udvidelser?
- Er nogle stories så store, at de skal deles?

---

## Prioritér backloggen

Product Backlog skal være sorteret med det vigtigste øverst.

Prioritér efter:

1. **Værdi:** Hvad har svømmeklubben mest brug for?
2. **Afhængigheder:** Hvad skal eksistere, før noget andet kan fungere?
3. **Risiko:** Hvad er vi mest usikre på og bør undersøge tidligt?
4. **Helhed:** Får formand, kasserer og træner alle noget anvendeligt?
5. **Nødvendighed:** Er det et grundkrav eller en udvidelse?

> Hellere en enkel, gennemgående løsning for alle tre brugere end en perfekt løsning for kun én.

Prioritering handler ikke blot om at vælge de nemmeste stories først. Gruppen skal kunne forklare,
hvorfor den valgte rækkefølge giver mening.

---

## Estimér med planning poker

Estimér hver story som:

| Størrelse | Vejledende omfang |
|---|---|
| **S** | Under en halv dags arbejde for én person |
| **M** | Omkring én dag |
| **L** | To dage eller mere |

Fremgangsmåde:

1. Læs user story og acceptkriterier højt.
2. Stil afklarende spørgsmål.
3. Alle vælger S, M eller L uden at vise de andre.
4. Alle viser deres estimat samtidig.
5. Den højeste og laveste forklarer deres vurdering.
6. Gruppen estimerer igen.
7. Er storyen stadig L, forsøger I at dele den.

Planning poker er ikke en konkurrence om at gætte rigtigt. Samtalen skal afsløre forskelle i
gruppens forståelse af arbejdet.

---

## Hvornår er en story klar til Sprint Planning?

Inden morgendagens Sprint Planning bør de øverste stories:

- [ ] have en tydelig bruger og værdi
- [ ] have konkrete acceptkriterier
- [ ] indeholde relevante grænse- og fejltilfælde
- [ ] være små nok til at kunne færdiggøres i sprinten
- [ ] være estimeret
- [ ] have kendte afhængigheder
- [ ] være prioriteret
- [ ] kunne forbindes til et eller flere krav

Dette er ikke en ekstra officiel Scrum-status. Det er gruppens kontrol af, om de vigtigste stories
er forstået godt nok til Sprint Planning.

---

# Dagens Scrum-leverancer

Når dagen er slut, skal gruppen have:

- en oprettet og prioriteret **Product Backlog**
- user stories, der tilsammen dækker F1–F14
- acceptkriterier med konkrete værdier og grænsetilfælde
- størrelserne S, M eller L på de vigtigste stories
- store stories opdelt i mindre stories
- et opdateret `docs/user-stories.md`
- et synligt Scrum-board med de fire aftalte kolonner
- forslag til et **sprintmål** for sprint 1
- spørgsmål og uklarheder skrevet ned til morgendagens check-in

I skal endnu ikke flytte hele backloggen til Sprint Backlog. Det sker under Sprint Planning i
morgen.

---

# Aktiviteter i undervisningen

## 1. Scrum-processen – fælles introduktion

Vi placerer dagens arbejde i det samlede Scrum-forløb:

1. Hvad er Product Backlog?
2. Hvad er Sprint Backlog?
3. Hvad vælger gruppen under Sprint Planning?
4. Hvad skal være færdigt efter en sprint?
5. Hvordan bruges Definition of Done?
6. Hvad er forskellen på Sprint Review og Retrospective?

Gruppen vælger desuden, hvem der er Scrum Master i sprint 1.

## 2. Øvelser med user stories og acceptkriterier

Arbejd to og to med [dagens opgaver](opgaver.md).

Fokusér især på:

- brugerens værdi
- konkrete acceptkriterier
- grænsetilfælde
- fejltilfælde
- opdeling af store stories
- forskellen på stories og tekniske opgaver

Der findes [vejledende løsninger](loesninger.md), men forsøg selv først.

## 3. Opret Delfinens Product Backlog

Arbejd i projektgruppen:

1. Skriv user stories for formand, kasserer og træner.
2. Skriv acceptkriterier.
3. Forbind stories med kravene F1–F14.
4. Læg stories på boardet i kolonnen *Product Backlog*.
5. Prioritér backloggen.
6. Estimér de vigtigste stories med planning poker.
7. Del stories, der er vurderet til L.
8. Opdatér `docs/user-stories.md`.

Fordel gerne det første skrivearbejde mellem par, men gennemgå alle stories samlet bagefter.
Product Backlog tilhører hele gruppen.

## 4. Scrum-kontrol før Sprint Planning

Stå samlet foran boardet og kontrollér:

- Er det tydeligt, hvad der er vigtigst?
- Kan vi forklare rækkefølgen?
- Er de øverste stories forstået af alle?
- Er acceptkriterierne testbare?
- Er de øverste stories små nok?
- Har alle gruppens medlemmer deltaget i estimeringen?
- Har vi et forslag til sprintmålet?
- Hvilke spørgsmål skal vi stille underviseren?

## 5. Klar til i morgen

Gå tjeklisten
[Klar til onsdag 18-11?](../../projekter/delfinen/kom-i-gang.md#klar-til-onsdag-18-11)
igennem.

I morgen holder gruppen **Sprint Planning** og beslutter:

- hvorfor sprinten er værdifuld
- hvad sprintmålet er
- hvilke stories der skal i Sprint Backlog
- hvordan gruppen vil begynde arbejdet

---

## Det vigtigste at tage med

- Scrum skaber korte feedback-cyklusser og fælles overblik over projektet.
- Product Backlog indeholder det prioriterede arbejde for hele produktet.
- Sprint Backlog indeholder det arbejde, gruppen vælger til den aktuelle sprint.
- En user story beskriver brugerens behov og værdi – ikke en klasse eller teknisk opgave.
- Acceptkriterier gør en user story konkret og testbar.
- Store stories skal deles i mindre, anvendelige dele.
- Prioritering sker ud fra værdi, afhængigheder, risiko og projektets helhed.
- En story er først færdig, når Definition of Done er opfyldt.
- Dagens Product Backlog er udgangspunktet for morgendagens Sprint Planning.

