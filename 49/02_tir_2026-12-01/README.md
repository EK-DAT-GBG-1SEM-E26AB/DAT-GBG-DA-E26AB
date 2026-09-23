# Delfinen – sprint review, retrospektiv og sprint planning 2

## Beskrivelse

Sprint 1 sluttede i går med kode review. I dag holder I de tre møder, der binder to sprints
sammen:

1. **Sprint review** – hvad har vi faktisk bygget? Kør programmet, og tjek det mod
   acceptkriterierne.
2. **Retrospektiv** – hvordan har vi arbejdet sammen? Hvad skal vi gøre anderledes?
3. **Sprint planning 2** – hvad bygger vi i den sidste sprint, frem til afleveringen tirsdag 08-12?

Møderne er beskrevet kort i [Scrum i Delfinen](../../projekter/delfinen/scrum.md#møderne). Her får
I en opskrift på hvert af dem – og hvordan I tager gårsdagens kode review, afleveringskravene og
ITF-diasshowet med ind i planen.

> **Ny Scrum Master.** I skifter Scrum Master mellem sprintene. Den gamle Scrum Master styrer
> sprint review og retrospektiv – den nye overtager ved sprint planning.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* holde et sprint review, hvor hver user story afprøves mod sine acceptkriterier og Definition of
  Done
* skelne mellem sprint review (produktet) og retrospektiv (samarbejdet)
* holde et retrospektiv efter Start – Stop – Fortsæt og ende med få, konkrete ændringer
* bruge erfaringen fra sprint 1 til at vurdere, hvor meget I kan nå i sprint 2
* formulere et sprintmål og vælge en sprint backlog, der ender med en færdig aflevering
* omsætte fund fra kode review og krav til afleveringen til opgaver på boardet

## Se disse videoer før undervisningen:

Tre korte videoer (på engelsk) fra Scrum-verdenen. De handler om rigtige Scrum-teams, men
pointerne gælder også for jer:

* [How to Facilitate the Sprint Review](https://www.youtube.com/watch?v=Fbp9eLbcf7A) (Scrum.org, 6 min)
* [Sprint Review: More Than Just a Demo](https://www.youtube.com/watch?v=9WzoYrtXR-A) (Mountain Goat
  Software, 3 min)
* [How to Facilitate the Sprint Retrospective](https://www.youtube.com/watch?v=TD-XsdD2n3s) (Scrum.org, 8 min)

## Læs nedenstående før undervisningen

---

### Tre møder, tre spørgsmål

| Møde | Spørgsmålet | Handler om | Resultat |
|---|---|---|---|
| **Sprint review** | *Hvad har vi bygget?* | produktet | et ærligt billede af, hvad der er færdigt |
| **Retrospektiv** | *Hvordan har vi arbejdet?* | samarbejdet | højst tre ændringer til sprint 2 |
| **Sprint planning 2** | *Hvad bygger vi nu?* | planen | sprintmål og sprint backlog |

Rækkefølgen er ikke tilfældig. Man kan ikke planlægge sprint 2, før man ved, hvor man står
(review), og hvad man vil gøre anderledes (retrospektiv).

Et forslag til, hvordan tiden kan fordeles:

| Møde | Forslag til tid | Styres af |
|---|---|---|
| Sprint review | 45 min | Scrum Master fra sprint 1 |
| Retrospektiv | 30–45 min | Scrum Master fra sprint 1 |
| Sprint planning 2 | 60–75 min | Scrum Master for sprint 2 |

---

### Sprint review

I rigtig Scrum viser teamet produktet til Product Owner og kunden. I Delfinen er der
[ingen Product Owner udefra](../../projekter/delfinen/scrum.md#hvem-prioriterer), så I reviewer
jeres eget produkt – så ærligt, som hvis kunden sad ved siden af. Underviseren kommer måske forbi og
kigger med.

#### Sådan gør I

1. **Åbn boardet** på én skærm og **programmet** på en anden (eller skift mellem dem).
2. Tag user stories i *Færdig* **én ad gangen**. Læs acceptkriterierne højt.
3. **En anden end den, der har kodet storyen, betjener programmet.** Så opdager I, om
   den virker for andre end den, der ved, hvor man skal trykke.
4. Tjek hvert acceptkriterium: *ja* eller *nej*.
5. Tjek [Definition of Done](../../projekter/delfinen/scrum.md#definition-of-done): merget til
   `main`? Tests, hvor der er beregninger? Gemmes data?
6. Opfylder storyen ikke det hele, **ryger den tilbage** i backloggen – med en note om, hvad der
   mangler. Det er ikke et nederlag; det er det, sprint review er til.

> **Kør fra `main`.** Det, der kun virker på én persons branch, er ikke bygget endnu. Hent den
> nyeste `main` (*Git → Pull*), og kør derfra.

#### Status på kravene

Slut af med at gå [kravene F1–F14](../../projekter/delfinen/readme.md#funktionelle-krav) igennem.
Skriv status i `docs/user-stories.md` eller på en tavle:

| Krav | Status | Bemærkning |
|---|---|---|
| F1 Opret medlem | færdig | |
| F5 Se kontingent | delvis | mangler test af 60-års grænsen |
| F9 Holdoversigt | ikke startet | |
| ... | | |

Det er jeres **udgangspunkt for sprint 2**. Alt, der ikke står som *færdig*, skal med i planen – eller
aktivt vælges fra.

#### Hvor meget nåede vi?

Tæl, hvor mange user stories I **planlagde** i sprint 1, og hvor mange der blev **færdige** – gerne
med størrelserne S, M og L fra [estimeringen](../../projekter/delfinen/scrum.md#estimering). Nåede I
halvdelen, så planlæg ikke dobbelt så meget i sprint 2. Det er den vigtigste lære fra sprint 1.

---

### Retrospektiv

Sprint review handlede om **produktet**. Retrospektivet handler om **samarbejdet**. Brug formen fra
[Scrum i Delfinen](../../projekter/delfinen/scrum.md#retrospektiv--tirsdag-01-12):
**Start – Stop – Fortsæt**.

1. **Hver for sig (5 min):** skriv sedler i tre kolonner.
   * **Start:** hvad skal vi begynde at gøre?
   * **Stop:** hvad skal vi holde op med?
   * **Fortsæt:** hvad virker, og skal vi blive ved med?
2. **Læs op (10 min):** hver læser sine sedler. Ingen diskussion endnu.
3. **Snak (15 min):** hvad går igen? Hvad betyder mest?
4. **Beslut (5 min):** **højst tre konkrete ændringer** til sprint 2.

#### Spørgsmål, der kan sætte gang i sedlerne

* Holdt vi daily stand-up hver dag? Var den kort – eller blev den til et langt møde?
* Var boardet opdateret, så alle kunne se, hvem der lavede hvad?
* Mergede vi ofte, eller sad vi med store merge-konflikter?
* Var **alle fire** inde over kode, test, diagrammer og planlægning – eller begyndte nogen at
  blive "den, der laver diagrammer"?
* Holdt vi de aftaler, vi skrev i Team Canvas'en? Skal nogen af dem ændres?
* Hvad sagde gårsdagens kode review om vores **arbejdsmåde** – fx commit-beskeder eller
  ubrugte branches?

#### Gode og dårlige beslutninger

En god beslutning er **konkret**, så man kan se, om den bliver holdt:

| Uklar | Konkret |
|---|---|
| *"Vi skal kommunikere bedre."* | *"Vi holder stand-up kl. 9.00 foran boardet, også når nogen er hjemme – de skriver i chatten."* |
| *"Vi skal merge oftere."* | *"Vi merger til `main` hver dag inden kl. 15."* |
| *"Alle skal lave lidt af det hele."* | *"Den, der ikke har skrevet tests endnu, tager test af top 5."* |

Skriv de tre ændringer ind i Team Canvas'en eller øverst på boardet, så I ser dem hver dag.

> **Snak om arbejdsmåden, ikke om personerne:** *"Vi fik ikke merget før fredag"* – ikke *"Du
> mergede ikke"*. Går samarbejdet skævt på en måde, I ikke kan løse selv, så tag det med til
> check-in i morgen – eller tag fat i underviseren i dag.

---

### Sprint planning 2

Sprint 2 er **kort**: fra i dag til afleveringen tirsdag 08-12 kl. 23:59.

| Dag | |
|---|---|
| tir 01-12 | sprint planning (i dag) – går I i gang bagefter, er I godt på vej |
| ons 02-12 | projektarbejde, **check-in** med underviseren |
| tor 03-12, fre 04-12 | projektarbejde |
| man 07-12 | projektarbejde – sidste dag med nye features |
| tir 08-12 | ret fejl, gør dokumentationen færdig, **aflevér** |

Det er omkring fem arbejdsdage. Og i modsætning til sprint 1 skal sprint 2 ende med **noget, der
kan afleveres**: kode, tests, dokumentation, eksempeldata **og** ITF-diasshowet.

#### 1. Sprintmål

Hvad skal programmet kunne, når sprinten slutter – i én sætning? Fx:

> *"Træneren kan registrere tider og se top 5, alle krav F1–F14 virker og er gemt i fil, og
> afleveringen er klar tirsdag middag."*

#### 2. Hvad skal med? Saml alt på boardet

Før I vælger, skal alt det, der **kunne** komme med, stå på boardet som kort:

* **De krav, der ikke er færdige** – fra statustabellen i sprint review.
* **Fundene fra kode review** – de tre vigtigste fra issuet i hvert fald.
* **Det, afleveringen kræver** – se [Aflevering](../../projekter/delfinen/readme.md#aflevering):
  * domænemodel (rettet til), `user-stories.md` (opdateret) og **designklassediagram** i `docs/`
  * `README.md` med medlemmer, link til boardet, hvordan programmet startes, og hvilke krav der er
    lavet
  * eksempeldata med **mindst 10 medlemmer** af alle slags
  * unit tests af kontingentberegningen – grønne
* **ITF-diasshowet** – se [IT- og Forretningsudvikling](../../projekter/delfinen/readme.md#it--og-forretningsudvikling).
  Det afleveres **sammen med** programmet 08-12 og præsenteres i ITF-lektionen torsdag 10-12. Læg
  det på boardet som kort på linje med resten, fx ét kort pr. del af analysen, ét til at samle
  diasshowet og ét til at forberede præsentationen. Indholdet af ITF-opgaverne følger ITF's egne
  anvisninger.

Dokumentation og diasshow er **ikke** noget, man klarer tirsdag aften. De skal have en plads i
sprinten ligesom koden.

#### 3. Prioritér

Brug de samme to spørgsmål som i sprint 1 ([Hvem prioriterer?](../../projekter/delfinen/scrum.md#hvem-prioriterer)):
*Hvad skal virke, før noget andet kan virke?* og *Hvad har klubben mest brug for?* Og så et tredje,
nyt spørgsmål:

> **Hvad kræver afleveringen?** Et færdigt program med F1–F14 og gode tests er bedre end et halvt
> program med alle udvidelser. Udvidelserne U1–U9 kommer **sidst** – hvis der er tid.

#### 4. Estimér og vælg

Estimér kortene (S/M/L), og træk dem fra toppen ind i *Sprint Backlog*, **indtil I har brugt den
tid, sprint 1 viste, at I har**. Resten bliver i backloggen.

#### 5. Beslut, hvad der ryger først

Skriv ned, hvad I **dropper**, hvis tiden bliver knap – i den rækkefølge, I dropper det. Så skal I
ikke diskutere det mandag aften. Det er også et af spørgsmålene til check-in i morgen.

#### 6. Del op, og aftal et stoppunkt

* Del hver story op i opgaver, og aftal, hvem der starter på hvad. Sørg for, at alle fire er inde
  over kode **og** dokumentation.
* **Aftal et stoppunkt for nye features** – fx mandag 07-12 kl. 15. Derefter retter I kun fejl,
  gør dokumentationen færdig og afleverer.

---

### Klar til check-in i morgen?

Fra [scrum.md](../../projekter/delfinen/scrum.md#check-in-med-underviseren--18-11-25-11-og-02-12)
skal I have klar til check-in onsdag 02-12:

- [ ] Sprintmål og sprint backlog for sprint 2 – på boardet
- [ ] Hvad I ændrer efter kode review og retrospektiv
- [ ] Hvad I dropper, hvis tiden bliver knap
- [ ] Hvem der er Scrum Master i sprint 2

---

## Det vigtigste at tage med

* **sprint review** = produktet: kør programmet fra `main`, og tjek hver story mod
  acceptkriterierne og Definition of Done
* det, der ikke opfylder Definition of Done, ryger **tilbage** i backloggen
* **retrospektiv** = samarbejdet: Start – Stop – Fortsæt, og højst **tre konkrete** ændringer
* brug det, I **nåede** i sprint 1, til at vurdere, hvor meget I kan nå i sprint 2
* sprint 2 skal ende med en **aflevering**: kode, tests, dokumentation, eksempeldata og
  ITF-diasshow – alt sammen på boardet
* skriv ned, hvad der ryger først, og aftal et **stoppunkt** for nye features

## Aktiviteter i undervisningen

### 1. Sprint review

Følg [Sådan gør I](#sådan-gør-i). Slut med statustabellen over F1–F14, og tæl, hvor meget I
nåede i sprint 1.

### 2. Retrospektiv

Start – Stop – Fortsæt, som beskrevet [ovenfor](#retrospektiv). Tag Team Canvas'en frem, og ret den,
hvis jeres aftaler har ændret sig.

### 3. Sprint planning 2

Den nye Scrum Master styrer. Følg de seks trin under [Sprint planning 2](#sprint-planning-2).
Tjek til sidst [listen til check-in](#klar-til-check-in-i-morgen).

### 4. Kom i gang

Er der tid tilbage, så gå i gang med sprint 2 – start med den øverste story i sprint backloggen.
