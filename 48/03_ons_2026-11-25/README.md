# Projektarbejde, sprint 1: check-in midt i sprinten

## Beskrivelse

Dagens undervisning er afsat til [Delfinen](../../projekter/delfinen/readme.md), sprint 1. Vi er midt i
sprinten: sprint 1 slutter mandag 30-11 med kode review.

I dag er **I i lokalet, og underviseren er online**, som 18-11. Hver gruppe har et kort
**check-in**. Hvornår jeres gruppe er på, og hvordan I kobler jer på, får I at vide af
underviseren.

Ved sprint planning viste I en **plan**. I dag viser I **software, der virker**, og I tager
stilling til, om planen holder. Det er dagens tema: at **se ærligt på, hvor I er**, og at skære
til, mens der stadig er tid.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* vise jeres program frem **fra `main`**, sådan som det er, ikke fra din egen branch
* gøre status over sprinten: hvad er **færdigt**, hvad er **i gang**, og hvad når I **ikke**
* **skære en user story til**, så den kan blive færdig, eller flytte den til sprint 2
* bruge et check-in til at få hjælp til det, I er i tvivl om

## Se disse videoer før undervisningen:

Ingen video i dag.

## Læs nedenstående før undervisningen

* [Scrum i Delfinen → Check-in med underviseren](../../projekter/delfinen/scrum.md#check-in-med-underviseren--18-11-25-11-og-02-12)
* [Sådan foregik check-in 18-11](../../47/03_ons_2026-11-18/README.md#check-in-med-underviseren):
  før, under og efter mødet. Det gælder også i dag.

---

### Hvad I skal vise

| Hav klar | Hvordan |
|---|---|
| **Det, der virker** | Kør programmet **fra `main`**, og vis de user stories, der er færdige, én ad gangen |
| **Boardet** | Åbent og opdateret: hvad er i *Færdig*, *I gang* og *Sprint Backlog*? |
| **Status på sprintmålet** | Når I det? Hvad mangler? |
| **Det, der driller** | Konkret: en klasse, I er i tvivl om; en konflikt, der bliver ved; en regel i casen |
| **Hvad I vil skære** | Er der user stories, der skal gøres mindre, eller flyttes til sprint 2? |

#### Vis det fra main

Det er fristende at vise programmet fra den computer, hvor det virker bedst. Men det er `main`,
der er jeres produkt. Det, der kun ligger på en branch, er ikke færdigt.

Før mødet: **klon repoet i en ny mappe**, og kør programmet og testene derfra. Det er den samme
test, som står i [Tjek, før I afleverer](../../projekter/delfinen/readme.md#tjek-før-i-afleverer):

1. **File → New → Project from Version Control**, jeres repos URL, en **ny** mappe.
2. Kør `Main`. Kør alle tests.
3. Virker det ikke, er der noget, der ikke er committet eller merget. Find ud af hvad, **før** I
   viser det frem.

---

### Status midt i sprinten

Sæt jer foran boardet, og gør status, **før** check-in. Tæl størrelserne sammen:

| | Stories | S/M/L |
|---|---|---|
| Færdig (opfylder Definition of Done) | | |
| I gang | | |
| Ikke startet i Sprint Backlog | | |

Der er **tre projektdage tilbage** af sprinten: i dag, fredag og lidt af mandag (torsdag er ITF).
Kig på, hvor meget I har nået fra 18-11 til i går. Kan I nå resten på de sidste tre?

Hvis ikke, så **beslut det nu**, ikke fredag eftermiddag. Der er tre muligheder, i den rækkefølge:

1. **Skær storyen til.** Kan en del af den blive færdig og give værdi? *"Træneren kan se top 5
   for én disciplin"* før *"... for alle discipliner, junior og senior"*. Skriv resten som en ny
   story i backloggen.
2. **Flyt den til sprint 2.** Tag den ud af Sprint Backlog og læg den øverst i Product Backlog.
3. **Byt den.** Er der en mindre story, der passer bedre til sprintmålet?

Det, der **ikke** er en mulighed: at gå på kompromis med Definition of Done, så flere stories
"næsten" bliver færdige.

> **At skære til er ikke at fejle.** Det er præcis det, en sprint er til: at opdage i tide, at
> planen var for optimistisk, og tilpasse den. Estimaterne i første sprint er altid for lave. Det,
> I lærer i dag om, hvor meget I kan nå, bruger I til sprint planning 01-12.

---

### Integration: få det ind i main

Midt i en sprint er der tit mange branches, der hver især virker, men som ikke er merget. Så ved
ingen, om de virker **sammen**. Det er dér, de store overraskelser gemmer sig, som med `Horse` og
`age` [19-11](../../47/04_tor_2026-11-19/loesninger.md#opgave-12--hele-arbejdsgangen).

Kig på jeres branches:

```bash
git fetch --prune
git branch -a
```

* **Er der en branch, der er ældre end to dage?** Så merge `main` ned i den i dag, også selvom
  storyen ikke er færdig. Så bliver konflikterne små.
* **Er der en branch, der er færdig, men ikke merget?** Merge den op i dag.
* **Er der branches, der er merget, men ikke slettet?** Slet dem.

---

### Begynd at samle til retrospektivet

Retrospektivet er tirsdag 01-12 (se
[Scrum i Delfinen → Retrospektiv](../../projekter/delfinen/scrum.md#retrospektiv--tirsdag-01-12)).
Det er svært at huske, hvad der skete for en uge siden. Lav en side i jeres noter, hvor hver især
skriver korte noter under **Start**, **Stop** og **Fortsæt**, når I opdager noget. Fx:

* *Stop: at merge uden at køre testene (ons).*
* *Fortsæt: stand-up kl. 9 præcis.*
* *Start: at sige til, når vi ændrer i `Member`, som alle bruger.*

---

## Det vigtigste at tage med

* vis programmet **fra `main`**, klonet i en ny mappe; det, der kun ligger på en branch, er ikke
  færdigt
* gør **status** før check-in: hvad er færdigt, i gang og ikke startet
* nås det ikke, så **skær til**, flyt eller byt, **nu**, og aldrig ved at slække på Definition of
  Done
* merge `main` ned i gamle branches, og merge færdige branches op, så I ved, at det virker sammen
* skriv noter til retrospektivet, mens I husker det

## Aktiviteter i undervisningen

### 1. Stand-up og status (30 min)

Hold stand-up, og gør så status på sprinten med tabellen ovenfor. Beslut, hvad I vil skære, og ret
boardet.

### 2. Gør klar til check-in

Klon `main` i en ny mappe, og kør program og tests. Aftal, hvem der viser hvad, og skriv jeres
spørgsmål ned.

### 3. Check-in

Når det er jeres tur. Skriv beslutningerne ned bagefter, på boardet og i `docs/user-stories.md`.

### 4. Arbejd videre på sprinten

Med det, I blev enige om.

### Tjekliste, før I går hjem

- [ ] Boardet passer med det, I besluttede ved check-in.
- [ ] Ingen branch er mere end to dage bagud i forhold til `main`.
- [ ] `main` kan køre fra en frisk klon, og alle tests er grønne.
- [ ] Alle har skrevet mindst én note til retrospektivet.
