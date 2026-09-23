# Sprint planning og check-in

## Beskrivelse

I dag starter **sprint 1**. Den varer fra i dag til og med **mandag 30-11**, hvor der er kode
review. I dag skal I beslutte, **hvad** I vil have færdigt til den tid, og **hvordan** I kommer
i gang. Det kaldes **sprint planning**.

I dag er **I i lokalet, og underviseren er online**. Hver gruppe har et kort **check-in** med
underviseren, hvor I viser, hvad I har lavet i mandags og i går, og jeres forslag til sprinten.
Hvornår jeres gruppe er på, og hvordan I kobler jer på, får I at vide af underviseren.

Dagen i korte træk:

1. Gør det sidste færdigt fra mandag og tirsdag (tjeklisten
   [Klar til onsdag 18-11?](../../projekter/delfinen/kom-i-gang.md#klar-til-onsdag-18-11)).
2. Hold **sprint planning** i gruppen.
3. **Check-in** med underviseren, når det er jeres tur.
4. Gør projektet klar, så I kan begynde at kode i branches i morgen.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* forklare, hvad en **sprint** er, og hvad der skal komme ud af den
* formulere et **sprintmål** i én sætning
* vælge user stories til en sprint ud fra prioritering, afhængigheder og **kapacitet**
* forklare, hvorfor man hellere tager **for lidt** end for meget med i første sprint
* dele en user story op i **opgaver** og fordele dem, så fire personer kan arbejde samtidig
* forberede et check-in, så den korte tid bliver brugt på det vigtige

## Se disse videoer før undervisningen:

Ingen ny video. Har du ikke set den, så se
[Scrum in 20 mins... (with examples)](https://www.youtube.com/watch?v=SWDhGSZNF9M) fra i går.

## Læs nedenstående før undervisningen

* [Scrum i Delfinen](../../projekter/delfinen/scrum.md), især *Definition of Done*,
  *Sprintkalenderen*, *Sprint planning* og *Check-in med underviseren*.
* [Sprint Planning](https://scrumguides.org/scrum-guide.html#sprint-planning) i The Scrum Guide –
  et halvt skærmbillede, der siger det vigtigste.
* [Sprint planning](https://www.atlassian.com/agile/scrum/sprint-planning) (Atlassian) – med en kort
  video.

---

### Hvad er en sprint?

En **sprint** er en fast periode, hvor gruppen arbejder mod ét mål. I slutningen af sprinten skal
der være et **stykke software, der virker**: ikke *næsten* virker, men virker, og som man kan
vise frem.

I Delfinen er der to sprints:

| Sprint | Fra | Til | Slutter med |
|---|---|---|---|
| **1** | ons 18-11 | man 30-11 | kode review 30-11, sprint review og retrospektiv 01-12 |
| **2** | tir 01-12 | tir 08-12 | aflevering 08-12 kl. 23:59 |

Hvorfor to sprints og ikke bare "lav projektet"? Fordi I **stopper op og kigger** efter sprint 1.
Hvor meget nåede I? Hvad drillede? Hvad gør I anderledes? Det, I lærer i sprint 1, bruger I i
sprint 2. Uden den pause opdager I først problemerne, når det er for sent.

> **Tanken i Scrum:** hellere et lille program, der virker, efter hver sprint, end et stort
> program, der næsten virker, på afleveringsdagen.

---

### Sprint planning: tre spørgsmål

The Scrum Guide deler sprint planning i tre spørgsmål. Oversat til Delfinen:

| Spørgsmål | Det kommer der ud af |
|---|---|
| **Hvorfor** er denne sprint værdifuld? | Et **sprintmål** i én sætning |
| **Hvad** kan blive færdigt i denne sprint? | User stories flyttet fra *Product Backlog* til *Sprint Backlog* |
| **Hvordan** får vi det lavet? | Opgaver under hver story, og hvem der starter på hvad |

#### 1. Sprintmålet

Sprintmålet siger, hvad **programmet skal kunne** efter sprinten, set fra brugernes side. Ikke hvor
mange stories I laver, og ikke hvilke klasser I skriver.

| Dårligt sprintmål | Hvorfor | Bedre |
|---|---|---|
| *Lave 8 user stories* | Siger ikke, hvad programmet kan | *Programchefen kan oprette og se events, og billetsælgeren kan sælge forsalgsbilletter til den rigtige pris. Alt bliver gemt.* |
| *Lave Event, Ticket og FileHandler* | Klasser, ikke værdi | (samme) |
| *Lave så meget som muligt* | Man kan aldrig afgøre, om det er nået | (samme) |

Sprintmålet hjælper jer undervejs. Når I midt i sprinten skal vælge, hvad der skal droppes, spørger
I: *hvad skal vi bruge for at nå målet?*

#### 2. Vælg user stories: hvor meget kan I nå?

Tag stories **fra toppen af backloggen**, der passer til sprintmålet, og stop, når I har nok. Men
hvornår er det nok? Regn groft på det:

**Tæl dagene.** Sprint 1 har disse arbejdsdage. Undervisningen er en halv dag (formiddag eller
eftermiddag, se skemaet), og resten af dagen er jeres egen arbejdstid på projektet:

| Dag | Tid til projektet |
|---|---|
| ons 18-11 | lidt (planning og check-in) |
| tor 19-11 | halv dag (Git branching først) |
| fre 20-11, man 23-11, tir 24-11 | hele arbejdsdage |
| ons 25-11 | næsten hel arbejdsdag (check-in) |
| tor 26-11 | ITF (se ITF's plan) |
| fre 27-11 | hel arbejdsdag |
| man 30-11 | kode review |

Det er omkring **5–6 arbejdsdage med kode**. Med fire personer er det op til 20–24 *persondage*.

**Tag så højst halvdelen.** Estimaterne er næsten altid for lave i første sprint, og der går tid
til ting, der ikke er user stories: at lære branching, møder, en merge-konflikt, der tager en time,
en bug, der tager en eftermiddag, klassediagrammet. Omregnet med størrelserne fra
[Estimering](../../projekter/delfinen/scrum.md#estimering) (S ≈ ½ dag, M ≈ 1 dag, L ≈ 2 dage) er
**10–12 persondage** et fornuftigt loft for sprint 1.

> **Hellere færre stories, der bliver færdige, end mange, der er halvt lavet.** En story, der ikke
> opfylder Definition of Done ved sprintens slutning, tæller ikke med. Den ryger tilbage i
> backloggen. Bliver I færdige før tid, tager I bare den næste fra toppen af backloggen.

Et bud på et realistisk mål står i
[Scrum i Delfinen](../../projekter/delfinen/scrum.md#sprint-planning--18-11-og-01-12): de fleste
krav for **formand og kasserer**, med **kontingentberegning og unit test** og **gem i fil**. Så er
træneren sprint 2. Men det er jeres beslutning.

#### 3. Del op og fordel

For hver story i Sprint Backlog: skriv **opgave-tjeklisten** i kortets beskrivelse (se
[i går](../02_tir_2026-11-17/README.md#opgaver-under-en-user-story)), og aftal, hvem der **starter**
på hvad. Tre råd:

* **Undgå, at alle starter i de samme filer.** Fire personer, der alle retter i menuen på dag
  ét, giver fire merge-konflikter. Aftal, at **én** laver menuens skelet først, og at de andre
  starter på domæneklasser, tests eller filformat.
* **Arbejd i par**, især de første dage. Et par på den sværeste story (fx kontingentberegningen
  med tests) og et par på den første gennemgående skive (fx *opret medlem* + *se medlemmer*).
  Skift, hvem der sidder ved tastaturet, så alle committer.
* **Aftal de fælles navne** fra domænemodellen, før I koder: hvad hedder klasserne, og hvad hedder
  de vigtigste metoder? Skriv det i Team Canvas under kodestil.

#### Definition of Done

Læs [Definition of Done](../../projekter/delfinen/scrum.md#definition-of-done) højt i gruppen, og
bliv enige om, at I mener det samme. *"Merget til `main`"* betyder fra i morgen: den er lavet i en
branch, merget ind og **main kan stadig køre**. Vil I tilføje noget til jeres egen DoD (fx *"en
anden i gruppen har læst koden"*), så skriv det ned.

---

### Check-in med underviseren

Check-in er et **kort møde** mellem gruppen og underviseren. I er i lokalet, underviseren er
online. Formålet er, at underviseren kan se, hvor I er, stille spørgsmål og være sparringspartner
på jeres valg, **før** I har brugt en uge på dem.

#### Før mødet

* **Sid samlet ved én skærm**, så alle fire er med i samme opkald. Prøv lyden, før det er jeres
  tur: én computer med mikrofon, som alle kan tale ind i, er bedre end fire, der hyler.
* **Hav det hele åbent i faner:** boardet, repoet på GitHub, domænemodellen og
  `docs/user-stories.md`. Del skærmen.
* **Vælg en ordstyrer** (fx Scrum Master), men alle fire skal kunne svare.

#### Det skal I have klar (fra [Scrum i Delfinen](../../projekter/delfinen/scrum.md#check-in-med-underviseren--18-11-25-11-og-02-12))

* Team Canvas
* repoet, med alle fire som collaborators, og en commit fra hver
* domænemodellen
* product backlog med user stories og acceptkriterier, prioriteret og estimeret
* jeres **forslag** til sprintmål og sprint backlog
* jeres **spørgsmål** til casen, skrevet ned

#### Under mødet

Mødet er kort. Brug tiden på det, I er i tvivl om. Underviseren vil typisk spørge ind til ting
som:

* *Hvorfor ligger den story øverst?*
* *Hvordan har I tænkt jer at teste kontingentberegningen?*
* *Hvem gør hvad de første dage?*
* *Er der noget i casen, I har tolket anderledes end reglerne i projektbeskrivelsen?*

Det er ikke en eksamen. Kan I ikke svare, er det netop det, mødet er til.

#### Efter mødet

**Skriv det ned med det samme.** Beslutninger om casen skrives ved den user story, det gælder, i
`docs/user-stories.md`. Ændringer i sprinten rettes på boardet. Om fem dage husker ingen, hvad der
blev sagt.

---

### Gør projektet klar til i morgen

Fra i morgen arbejder I i **branches**. Det går meget lettere, hvis `main` allerede har et
projekt, som alle kan køre. Når planning og check-in er overstået, så sørg for dette **på
`main`**, én person ad gangen, som I plejer:

* IntelliJ-projektet (eller Maven-projektet) med **JUnit 5**, som i Filmsamling
* en `.gitignore`, så `out/`, `target/` og IntelliJ's personlige filer ikke kommer med
* de **packages**, I har aftalt, fx `ui`, `domain` og `data`
* en `Main`, der kan køre, og en test, der er grøn
* **alle fire** har pullet, kørt `Main` og kørt testen på deres egen computer

Så er der et fælles udgangspunkt at lave branches fra i morgen.

---

## Det vigtigste at tage med

* en **sprint** er en fast periode med ét mål, og den slutter med software, der **virker**
* sprint planning svarer på **hvorfor** (sprintmål), **hvad** (stories) og **hvordan** (opgaver)
* sprintmålet beskriver, hvad **programmet kan** efter sprinten, ikke hvor meget I har kodet
* tæl dagene, og tag **højst halvdelen** af jeres persondage med i første sprint
* en story, der ikke opfylder **Definition of Done**, tæller ikke
* fordel arbejdet, så I **ikke starter i de samme filer**
* forbered check-in: **boardet åbent, alle fire med, spørgsmålene skrevet ned**, og skriv
  beslutningerne ned bagefter

## Aktiviteter i undervisningen

### 1. Gør mandag og tirsdag færdig (højst en time)

Gå [Klar til onsdag 18-11?](../../projekter/delfinen/kom-i-gang.md#klar-til-onsdag-18-11) igennem.
Mangler der noget, så fordel det og gør det færdigt nu.

### 2. Sprint planning (ca. en time)

Scrum Master styrer tiden:

1. **Sprintmål** (10 min): foreslå hver især et mål i én sætning. Bliv enige om ét.
2. **Vælg stories** (20 min): tag fra toppen af backloggen, læg størrelserne sammen, og stop ved
   loftet. Flyt dem til *Sprint Backlog* på boardet.
3. **Opgaver** (20 min): skriv opgave-tjeklisten på hver story i Sprint Backlog.
4. **Fordel** (10 min): hvem starter på hvad i morgen og fredag? Sæt jer selv som **Assignees**
   på kortene.

### 3. Check-in

Når det er jeres tur. Mens I venter, arbejder I videre med punkt 2 eller 4.

### 4. Gør projektet klar

Se [Gør projektet klar til i morgen](#gør-projektet-klar-til-i-morgen) ovenfor. Og læs
[siden om Git branching](../04_tor_2026-11-19/README.md) før i morgen.

### 5. Første daily stand-up

Slut dagen med jeres første **daily stand-up**, stående foran boardet (se
[Scrum i Delfinen](../../projekter/delfinen/scrum.md#daily-stand-up--hver-projektdag)). Tre
spørgsmål hver, højst et kvarter. Det er en god øvelse at gøre det, mens der endnu ikke er meget
at sige.
