# Præsentation af færdige projekter

## Beskrivelse

Adventure er afleveret. Tre uger, fem dele og jeres første rigtige objektorienterede program:
rum, der kender deres naboer, ting i lister, mad og våben med arv og polymorfi, og fjender, der slår
igen.

I dag viser I det frem – og ser, hvordan de andre har løst de samme problemer. Det er en af de
bedste måder at lære på: alle har bygget det samme spil ud fra de samme krav, men ingen har gjort
det helt ens.

Dagen har tre dele:

1. **Præsentationer** – hver gruppe viser sit spil og et stykke kode for holdet
2. **Kode-review** – grupperne kigger hinandens kode igennem med
   [review-skemaet](../../projekter/adventure/kode-review.md)
3. **Refleksion** – hvad tager du med videre?

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* præsentere dit eget program kort og præcist: hvad det gør, og hvordan det er bygget
* forklare et designvalg i jeres kode – fx arv, polymorfi eller ansvarsfordeling – med
  klassediagrammet som støtte
* stille spørgsmål til og give konstruktiv feedback på andres kode
* modtage feedback og vælge, hvad du vil gøre med den
* sætte ord på, hvad du har lært i projektet, og hvad du vil gøre anderledes næste gang

## Se disse videoer før undervisningen:

Ingen video i dag. Kig i stedet
[review-skemaet](../../projekter/adventure/kode-review.md) igennem, så I ved, hvad I skal kigge
efter hos de andre.

## Læs nedenstående før undervisningen

---

### Dagens forløb

Planen herunder er et **forslag**. Underviseren fortæller ved dagens start, hvordan det bliver:
rækkefølgen, hvor lang tid der er, og om holdet deles i mindre runder.

| Del | Hvad | Forslag til tid |
| --- | --- | --- |
| 1 | Præsentationer – 10 minutter pr. gruppe | formiddagen |
| 2 | Kode-review – to grupper reviewer hinanden | efter frokost |
| 3 | Refleksion – individuelt, derefter i gruppen | sidste halve time |

Er der mange grupper, kan holdet deles i to eller tre mindre runder, der præsenterer for hinanden
på samme tid. Så bliver dagen ikke til tre timers lytning, og der bliver bedre tid til spørgsmål.

---

### Præsentationen: 10 minutter

Fra [del 5](../../projekter/adventure/del-5-enemies.md#feedback) har hver gruppe **10 minutter**
til at:

* køre programmet (vis f.eks. en sjov feature) – brug maks. 2 minutter
* præsentere kode, f.eks. noget I er særligt stolte over
* tage spørgsmål fra resten af holdet (maks. 4 minutter)

Et forslag til, hvordan de 10 minutter kan fordeles:

| Tid | Indhold |
| --- | --- |
| 2 min | **Demo.** Spil jeres spil – vis en sjov feature eller en kamp mod en fjende. |
| 1 min | **Design.** Vis klassediagrammet, og peg på ét arveforhold og én association. |
| 3 min | **Kode.** Ét stykke kode, I er stolte af – og, hvis tiden rækker, ét sted, I ville refaktorere. |
| 4 min | **Spørgsmål** fra holdet. |

#### Hvad kan I vise?

Fra del 5 – lidt inspiration:

* demo af programmet og evt. interessante features
* udvalgte kodedele, der løser et specifikt problem, f.eks. `eat`- eller `attack`-kommandoen
* særlige elementer i jeres løsning, f.eks. arv, polymorfi eller enum
* programmets overordnede design, f.eks. ansvarsfordelingen mellem klasserne

**Det, I er stolte af.** Vælg noget, der var **svært** at få til at virke. Det er næsten altid det
mest interessante for de andre, fordi de selv har kæmpet med det samme. Gode kandidater:

* `attack` – hvordan fik I styr på alle udfaldene? Lignede koden jeres aktivitetsdiagram?
* hvordan `Enemy` selv opdager, at den er død, og fjerner sig fra rummet
* hvordan `Player` bruger et våben uden at vide, om det er et sværd eller en revolver
* hvordan beskederne kommer fra `Player` og `Enemy` ud til `UserInterface`

**Det, I ville refaktorere.** Det er ikke en tilståelse – det viser, at I kan se jeres egen kode
udefra. En metode, der er blevet for lang? En klasse med for mange ansvar? Et navn, der ikke længere
passer? Sig kort, hvad I ville ændre, og **hvorfor**.

#### Forbered jer

* **Kør programmet** fra den computer, I præsenterer fra – også i dag, selvom det virkede i går.
* **Skriv demoen ned** som en liste af kommandoer, fx `go east`, `take sword`, `equip sword`,
  `attack troll`. Så improviserer I ikke, og I når det, I vil vise, på 2 minutter.
* **Gør skriften stor** i IntelliJ, så bagerste række kan læse koden. *View → Appearance → Enter
  Presentation Mode* gør det hele på én gang.
* **Hav klassediagrammet klar** – fx pdf'en fra afleveringen i et vindue ved siden af.
* **Aftal, hvem der siger hvad.** Alle i gruppen skal kunne svare på spørgsmål om koden – det var
  også kravet til den endelige aflevering.

---

### Når de andre præsenterer: stil spørgsmål

Der er 4 minutter til spørgsmål pr. gruppe. Brug dem. Gode spørgsmål handler om **hvorfor**, ikke
bare **hvad**:

* *"Hvorfor ligger den metode i `Player` og ikke i `Room`?"*
* *"Hvordan ved `attack`, om våbnet kan bruges?"*
* *"Hvad skete der, da I prøvede at tilføje … ?"*
* *"Hvis I skulle tilføje en ny slags fjende – hvor mange klasser skulle I så ændre?"*

Det sidste spørgsmål er værd at stille til alle. Svaret siger meget om, hvor godt polymorfien virker.

---

### Kode-review

Fra [projektbeskrivelsen](../../projekter/adventure/readme.md#afleveringer-og-deadlines): i dag
laver grupperne **kode-review** af hinandens projekter efter
[review-skemaet](../../projekter/adventure/kode-review.md).

Skemaet beskriver et møde mellem **programmøren** og **to uvildige reviewere**:

1. Én af reviewerne cloner projektet fra GitHub og åbner det i IntelliJ.
2. Den anden reviewer skriver noter i en kopi af skemaet.
3. Både reviewere og programmør følger med på skærmen.

I praksis: to grupper sætter sig sammen og reviewer **hinanden** – først den ene gruppes kode, så
den andens. Den gruppe, der bliver reviewet, er "programmøren".

Et par ting, så det går glat:

* **Commit-hash.** Skriv de første 7 tegn af den commit, I reviewer, i skemaet. På GitHub står den
  ved siden af den nyeste commit. Så er det tydeligt, præcis hvilken version reviewet handler om.
* **Vælg klasser.** Skemaet skal gentages for hver klasse, og det når I ikke for hele programmet.
  Vælg 2–3 klasser, fx `Player`, `Enemy` og `Weapon` eller én af dens subklasser.
* **Spring over, hvad der ikke passer.** Skemaet siger selv, at irrelevante spørgsmål skal slettes –
  fx afsnittet om exceptions, som I ikke har haft endnu.
* **Ikke brugerfladen.** Der er *"ikke fokus på brugergrænseflade og brugeroplevelsen"* – det er
  koden, I kigger på.
* **Giv skemaet til programmøren** bagefter. Det er deres feedback.

#### Sådan giver I god feedback

Feedback skal gøre koden bedre – ikke vise, hvor kloge reviewerne er.

* **Vær konkret.** *"`doStuff()` i `Player` fortæller ikke, hvad den gør"* er brugbart. *"Navnene er
  lidt dårlige"* er ikke.
* **Tal om koden, ikke om personen.** *"Metoden er lang"* – ikke *"du skriver lange metoder"*.
* **Spørg, før du dømmer.** Der kan være en god grund. *"Hvorfor er `health` `public`?"* åbner en
  samtale; *"`health` skal være `private`"* lukker den.
* **Ros det, der er godt.** Skemaet har et felt til det – *"Ros til særligt elegant kode"*. Brug
  det. Det er lige så vigtigt at vide, hvad man skal blive ved med.

Kig især efter det, I har arbejdet med i projektet:

| Kig efter | Hvor i skemaet |
| --- | --- |
| Navne, der siger, hvad klasser, metoder og variable gør | *Er navnene selvforklarende?* |
| Attributter, der er `private`, med getters kun hvor de bruges | *Har attributter korrekt access?* |
| `System.out.println` kun i `UserInterface` | *Yderligere kommentarer* |
| Ingen `instanceof` på våben | *Yderligere kommentarer* |
| Døde kommentarer og udkommenteret kode | *Kommentarer* 💣 |

**Når I modtager feedback:** Lyt, og spørg ind, hvis noget er uklart. I behøver ikke være enige i
alt – men skriv det ned, og tænk over det, før I afviser det.

---

### Refleksion

Skriv svarene til dig selv – 5–10 linjer i alt. Gem dem; de kan bruges, når vi senere på semestret
evaluerer, og når I går i gang med næste projekt.

1. Hvad i jeres Adventure er du **mest stolt af**?
2. Hvilket begreb forstår du nu, som du ikke forstod i uge 39? (Objektreferencer, `ArrayList` af
   objekter, arv, polymorfi, abstrakte klasser, …)
3. Hvad var **sværest**, og hvad hjalp dig videre?
4. Hvilken feedback fik I i dag, som du vil tage med dig?
5. Hvis I startede forfra, hvad ville I så gøre **anderledes**? Tegne mere, før I kodede? Committe
   oftere? Dele arbejdet anderledes op i gruppen?

Tal derefter kort om svarene i gruppen. Er der noget, I vil gøre anderledes som gruppe i næste
projekt?

---

### Efter efterårsferien

Efter efterårsferien (uge 42) går vi i gang med næste obligatoriske projekt, **Filmsamling**, i
uge 43–45. Vi starter mandag 19-10 med GitHub i grupper.

God ferie!

---

## Det vigtigste at tage med

* 10 minutter: kort demo, kode I er stolte af, og spørgsmål
* vælg noget, der var **svært** – det er det mest interessante for de andre
* forbered demoen som en liste af kommandoer, og test den på den computer, I præsenterer fra
* god feedback er **konkret**, handler om **koden** og spørger **hvorfor**
* ros er også feedback
* skriv refleksionen ned – den er til dig selv

## Aktiviteter i undervisningen

### 1. Sidste forberedelse (første kvarter)

Gruppen gennemgår [Forbered jer](#forbered-jer): kør programmet, find demo-kommandoerne frem, åbn
klassediagrammet, og aftal, hvem der siger hvad.

### 2. Præsentationer

Underviseren styrer rækkefølgen og tiden. Når I ikke selv præsenterer: lyt, og stil mindst ét
spørgsmål i løbet af dagen.

### 3. Kode-review

To og to grupper reviewer hinandens kode efter [review-skemaet](../../projekter/adventure/kode-review.md),
som beskrevet i [Kode-review](#kode-review) ovenfor. Brug ca. lige lang tid på hver gruppes kode.

### 4. Refleksion

Skriv svarene på [refleksionsspørgsmålene](#refleksion) hver for sig, og tal dem derefter igennem i
gruppen.
