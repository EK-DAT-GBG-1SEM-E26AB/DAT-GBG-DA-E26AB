# Delfinen – peer review og vejlederfeedback

## Beskrivelse

Delfinen er afleveret, og ITF-diasshowet er præsenteret. I dag får I de sidste to blikke udefra på
jeres projekt:

1. **Peer review** – et par fra en anden gruppe kloner jeres program og læser koden, som om de
   skulle overtage det. Og et par fra jeres gruppe gør det samme hos dem.
2. **Vejlederfeedback** – en samtale med en underviser om programmet og processen.

Hvordan det foregår, står i [Peer review af Delfinen](../../projekter/delfinen/peer-review.md).
Den side er dagens opskrift. Her får I overblikket og det, I skal have klar.

> **Obligatorisk fremmøde.** Peer review og vejlederfeedback er **obligatorisk**, og **alle fire i
> gruppen skal være til stede**. Underviserne registrerer fremmødet. Der er **ingen aflevering i
> itslearning** i dag. Kan en af jer ikke deltage, så kontakt underviseren **i god tid**.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* læse og vurdere et andet teams program ud fra, hvor let det er at **vedligeholde og ændre**
* give konkret feedback på navngivning, struktur, kode og kommentarer
* modtage feedback uden at forsvare dig – og vælge de vigtigste forslag
* forklare og begrunde jeres egne valg i projektet over for en underviser
* sætte ord på, hvad du har lært i projektet, og hvad du ville gøre anderledes

## Se disse videoer før undervisningen:

Ingen video i dag. Læs i stedet hele [Peer review af Delfinen](../../projekter/delfinen/peer-review.md)
– også afsnittet om [selvrefleksion](../../projekter/delfinen/peer-review.md#selvrefleksion).

## Læs nedenstående før undervisningen

---

### Dagen kort

| Del | Hvem | Hvad |
|---|---|---|
| **Peer review** | gruppen deler sig i to par | Ét par bliver hjemme og tager imod reviewerne; det andet par reviewer en anden gruppe. Bagefter deler I noterne. |
| **Vejlederfeedback** | alle fire | En samtale med en underviser om programmet, processen og jeres selvrefleksion. |

**Underviseren fortæller på dagen**, hvilke grupper der reviewer hinanden, og hvornår jeres
vejlederfeedback er. Hold øje med det, så alle fire er der, når det er jeres tur.

---

### Før I møder op

Fra [Før 11-12: forbered jer](../../projekter/delfinen/peer-review.md#før-11-12-forbered-jer):

- [ ] **Commit-hash** på den afleverede version: de første 7 tegn af den nyeste commit på `main` ved
      deadline 08-12. Reviewet sker på den version.
- [ ] `README.md` fortæller, hvordan programmet startes, og hvilke krav der er lavet.
- [ ] Eksempeldata ligger i repoet, så reviewerne kan prøve programmet med det samme.
- [ ] **Selvrefleksion** i `docs/selvrefleksion.md` – ikke et krav, men **anbefalet**. Den er jeres
      bedste forberedelse til både vejlederfeedbacken og eksamen.
- [ ] Et par ord om, hvad I **gerne vil have reviewerne til at se på**. Det er spørgsmål 10 i
      selvrefleksionen.

> **Selvrefleksionen er ikke en rapport.** Korte, ærlige svar på de elleve spørgsmål – med en
> begrundelse ved hvert. *"Vi nåede ikke at teste top 5, fordi ..."* er et fint svar. En god
> tommelfingerregel: hvis I kan svare på spørgsmålene højt uden at kigge i koden, er I godt
> forberedt.

---

### Peer review: overtag deres program

Denne gang lader reviewerne, **som om de skal overtage projektet**: Delfinen har købt programmet,
og jeres gruppe skal vedligeholde det fremover. Det handler om **S'et i FURPS** – *Supportability*.

Spørgsmålene til inspiration står i
[Hvad kigger reviewerne på?](../../projekter/delfinen/peer-review.md#hvad-kigger-reviewerne-på)
– navngivning, struktur, kode og algoritmer, kommentarer. De er ikke et skema, der skal udfyldes
punkt for punkt.

Et godt sted at starte er at **forestille sig konkrete ændringer** og finde ud af, hvor i koden de
skulle laves. Reviewerne skal ikke kode dem – bare finde stederne:

| Klubben vil ... | Spørgsmål til koden |
|---|---|
| hæve juniorkontingentet til 1100 kr. | Hvor mange steder skal beløbet ændres? Og testene? |
| have en femte disciplin, *medley* | Hvilke klasser skal røres? Er det kun én linje i en `enum`? |
| have top 10 i stedet for top 5 | Står tallet 5 ét sted – eller mange? |
| have et nyt menupunkt til kassereren | Hvor mange klasser skal ændres for at tilføje det? |
| gemme data i en database i stedet for CSV-filer | Hvor mange klasser ved, at data ligger i en fil? |
| vise hele programmet på en hjemmeside | Hvor meget af koden kan genbruges? Hvilke klasser skriver selv i konsollen? |

Jo **færre** steder, jo lettere er programmet at vedligeholde. Det er ikke en konkurrence – men
svarene fortæller meget om designet, og de er gode at kende til eksamen.

> **Reviewerne styrer.** For at det bliver et review og ikke en demonstration, kloner en af
> reviewerne projektet på sin egen maskine. Den reviewede gruppe svarer på spørgsmål, men viser
> ikke rundt. Kan programmet ikke køre, er det i sig selv en vigtig observation.

#### Feedback – at give og at få

Fra [Sådan giver man feedback](../../projekter/delfinen/peer-review.md#sådan-giver-man-feedback):
vær **konkret** (klasse og metode), sig også, hvad der er **godt**, **foreslå** i stedet for at
dømme, og snak om **koden**, ikke om personerne.

Den reviewede gruppe skal ikke forsvare sig, men **forstå**. Spørg ind, og skriv ned.

#### Noterne

Parret, der bliver hjemme, skriver noterne efter [skabelonen](../../projekter/delfinen/peer-review.md#noterne)
og slutter med **de tre vigtigste forslag**. Bagefter deler de to par noterne, så alle fire får hele
feedbacken. Læg dem gerne i `docs/peer-review.md` i jeres repo.

---

### Vejlederfeedback

Samtalen handler om **jeres** projekt – programmet og processen. Alle fire skal være der, og
underviseren kan spørge hver af jer. Regn med spørgsmål som:

* *Vis, hvor kontingentet bliver beregnet. Hvorfor ligger det der?*
* *Hvordan finder I de fem hurtigste? Hvilken klasse har ansvaret?*
* *Hvad har I unit-testet – og hvad har I kun testet ved at køre programmet?*
* *Hvordan brugte I branches? Hvor fik I konflikter?*
* *Hvad ville I gøre anderledes, hvis I startede forfra?*

Har I skrevet en selvrefleksion, så tag den med – den er et godt udgangspunkt for samtalen. Og
skriv ned, hvad underviseren siger. Det er feedback, I ikke får igen før eksamen.

---

### Efter i dag

* **Nu må I gerne rette i koden.** Peer review og vejlederfeedback har set den afleverede version.
  De tre vigtigste forslag er et godt sted at starte – også som forberedelse til eksamen.
* **Spørgsmål, I ikke kunne svare på i dag**, er gode at tænke over, inden I går til eksamen.
* På mandag og tirsdag er der [prøveeksamen](../../51/01_man_2026-12-14/README.md).

---

## Det vigtigste at tage med

* **obligatorisk fremmøde** – alle fire skal være der; ingen aflevering i itslearning
* reviewerne lader, som om de skal **overtage** programmet: hvor let er det at ændre?
* forestil jer **konkrete ændringer**, og find stederne i koden – jo færre, jo bedre
* reviewerne kloner og **styrer**; den reviewede gruppe svarer, men viser ikke rundt
* feedback er konkret, handler om koden og slutter med **de tre vigtigste forslag**
* selvrefleksionen er anbefalet – den er den bedste forberedelse til vejlederfeedback og eksamen

## Aktiviteter i undervisningen

### 1. Sidste forberedelse (første kvarter)

Find commit-hash'en frem, og tjek [listen](#før-i-møder-op). Aftal, hvem der er det par, der bliver
hjemme, og hvem der reviewer.

### 2. Peer review

Underviseren fortæller, hvilke grupper der reviewer hinanden. Følg
[Fredag 11-12: sådan foregår det](../../projekter/delfinen/peer-review.md#fredag-11-12-sådan-foregår-det).
Brug tabellen [Overtag deres program](#peer-review-overtag-deres-program) som start.

### 3. Del noterne

De to par fortæller hinanden, hvad de hørte og så. Bliv enige om **de tre vigtigste forslag** til
jeres eget projekt.

### 4. Vejlederfeedback

Når det er jeres tur: alle fire, selvrefleksionen og noterne fra peer reviewet.
