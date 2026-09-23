# Adventure del 3 – Food

> **Bunden forudsætning.** Del af det samlede [Adventure-projekt](readme.md).
>
> Det er her, vi tager **arv** i brug for første gang.

## Beskrivelse

I skal arbejde videre på adventure-spillet. Hvor version 2 medførte, at man kunne samle ting op og
efterlade dem igen, skal der nu være **forskellige typer ting**. I denne del er det **mad**, der kan
spises – våben kommer i del 4.

For at gøre det nemt for os selv, laver vi én ting ad gangen. Først mad! Altid først mad!!

## Læringsmål

Når du er færdig med denne del, skal du kunne:

* forklare hvad **arv** er, og hvad en subklasse arver fra sin superklasse
* skrive `class Food extends Item`
* forklare hvorfor et `Food`-objekt **også er** et `Item` (*is-a*-relationen)
* skrive en constructor i subklassen, der kalder `super(...)`
* skrive kode, der behandler subklasse-objekter gennem superklassens type
* håndtere flere forskellige udfald af én kommando

---

## Krav

### Spillet

<img src="images/mad-aeble.jpg" alt="Et halvspist æble" width="150" align="right">

De ting, der ligger i rummene, skal enten blot være "ting", eller de skal være **mad der kan
spises**.

Spilleren skal have en form for **health**, som kan øges ved at spise (sundt) mad, og mindskes ved
at spise gift eller usund mad – samt i senere udgaver blive angrebet af fjender.

### Brugerfladen

Spillet skal udvides med to kommandoer:

| Kommando | Betydning |
|---|---|
| `health` | Viser spillerens aktuelle health-status – både som tal og forklarende tekst |
| `eat <mad>` | Tager den nævnte mad enten fra rummet eller fra spillerens inventory og spiser den |

`health` er super-simpel, i hvert fald for brugerfladen: den skal blot udskrive spillerens
nuværende health. For eksempel:

```text
health: 50 - you are in good health, but avoid fighting right now
```

Spilleren starter med health **100**, og der er **intet maksimum** – spiser man sund mad ved fuld
health, kommer man over 100 (som `110` i eksemplet nedenfor). Teksten efter tallet afhænger af
health sådan her:

| health | Tekst |
|---|---|
| 100 og derover | `you are in perfect health` |
| 50–99 | `you are in good health, but avoid fighting right now` |
| 25–49 | `you are wounded - find something healthy to eat` |
| 1–24 | `you are barely alive` |
| 0 og derunder | `you should be dead` |

At spilleren **dør**, når health kommer ned på 0 eller derunder, kommer først i
[del 5](del-5-enemies.md#attack-sekvensen). I del 3 kan health bare blive ved med at falde.

`eat` er noget mere kompliceret end `take` og `drop`. **Der er tre forskellige udfald:**

```mermaid
flowchart TD
    A["eat <navn>"] --> B{"Findes tingen i<br/>rummet eller inventory?"}
    B -- nej --> C["'There is nothing like ... to eat around here'"]
    B -- ja --> D{"Er tingen spiselig?<br/>(er den et Food?)"}
    D -- nej --> E["'You cannot eat the ...'"]
    D -- ja --> F["Spis den:<br/>health ændres,<br/>maden fjernes"]
```

* Hvis man skriver `eat` efterfulgt af en ting, som **hverken er i rummet eller i inventory**,
  skal programmet skrive `There is nothing like ... to eat around here` – på samme måde som ved
  `take` og `drop`.
* Hvis man skriver `eat` efterfulgt af en ting, der **ikke er spiselig**, skal programmet skrive
  `You cannot eat the ...` med tingens lange navn.
* **Kun** hvis tingen findes *og* er spiselig, bliver den spist.

Når maden er spist, **holder den op med at eksistere**, og spilleren får en mængde health fra den.

Eksempel:

```text
> health
health: 100 - you are in perfect health

> eat lamp
You cannot eat the shiny brass lamp

> eat sandwich
There is nothing like sandwich to eat around here

> eat bread
You eat the loaf of stale bread. You feel a little better.

> health
health: 110 - you are in perfect health

> eat mushroom
You eat the pale glowing mushroom. That was a mistake.

> health
health: 60 - you are in good health, but avoid fighting right now
```

### Koden

I skal have én klasse, **`Food`**, der **arver fra `Item`**.

> Men det skal være muligt at behandle den som et helt almindeligt item, så det kan tilføjes,
> fjernes, takes og droppes ligesom alle andre items, **uden at koden dertil skal ændres det
> mindste.**

I `Map`, hvor rum og items oprettes, opretter I også `Food`-objekter og tilføjer dem til rooms, som
var de almindelige items.

#### Food

<img src="images/gift-flaske.jpg" alt="Grøn apotekerflaske med riller, mærket gift" width="120" align="right">

`Food`-objekter skal have et antal **`healthPoints`**, som er det, player optager, når den spiser
et food-objekt. Det kan også være et **negativt tal**, hvis det f.eks. er gift!

> Riflede sider og farvet glas var apotekets måde at gøre giftflasker genkendelige i mørke – man
> kunne mærke forskel uden at læse etiketten. Jeres spillere har ikke den luksus.

Lav en constructor, der udover det korte og det lange navn også tager `healthPoints`, og som
sender de to navne videre til `Item`s constructor med `super(shortName, longName)` – så
`healthPoints` bliver fastlagt, når `Map` opretter de `Food`-objekter, der skal være i spillet:

```java
Food bread = new Food("bread", "a loaf of stale bread", 10);
Food mushroom = new Food("mushroom", "a pale glowing mushroom", -50);
```

```mermaid
classDiagram
    class Item {
        -String shortName
        -String longName
        +Item(String shortName, String longName)
        +getShortName() String
        +getLongName() String
    }
    class Food {
        -int healthPoints
        +Food(String shortName, String longName, int healthPoints)
        +getHealthPoints() int
    }
    class Player {
        -int health
        -ArrayList~Item~ inventory
        +eat(String shortName) EatResult
        +getHealth() int
    }

    Item <|-- Food : extends
    Player "1" --> "0..*" Item : bærer
```

Bemærk at `Player` stadig har en liste af **`Item`** – ikke af `Food`. Et `Food`-objekt kan ligge i
den liste, fordi et `Food` **er et** `Item`.

`eat` skal fortælle brugerfladen, hvilket af de tre udfald der skete. En `boolean` kan kun to ting,
så brug en enum:

```java
public enum EatResult { NOT_FOUND, NOT_FOOD, EATEN }
```

`Player.eat` finder tingen (i inventory eller i `currentRoom`), tjekker med `instanceof Food`,
ændrer `health`, fjerner maden fra listen og returnerer det passende `EatResult`. Brugerfladen
`switch`er på resultatet og skriver beskeden.

> **Tip:** Når maden er spist, er den væk – men brugerfladen skal bruge dens lange navn i beskeden.
> Find derfor tingen, **før** den bliver spist (fx med en metode på `Player`, der leder i både
> inventory og rummet), så navnet kan skrives bagefter. Om det var sund mad eller gift, kan
> brugerfladen se på health før og efter – eller I kan give enummen flere værdier, fx
> `EATEN_HEALTHY` og `EATEN_POISONOUS`. Begge dele er fine.

---

## Anbefalet procedure

1. **Start med `health`-kommandoen** – den er forholdsvis ligetil, og så er I i gang.

2. **Arbejd derefter med `Food`:**

   1. Start med at oprette klassen, og tilføj nogle `Food`-objekter til mappet. Test at man kan
      samle dem op og droppe dem, som almindelige items.
   2. Lav derefter `eat`-kommandoen, og vær især opmærksom på **de tre forskellige udfald**, og
      sørg for at alt output er i `UserInterface`.
   3. Tilføj i `eat`-metoden, at health forandres med madens `healthPoints`, og husk at fjerne
      `Food`-objektet fra rummet eller inventory, så det ikke kan spises igen!

Efter hvert trin skal I selvfølgelig teste, at spillet stadig virker som forventet.

---

## Frivillige udvidelser

Der er ikke mange udvidelsesmuligheder til `Food`, men her er et par forslag:

### Klogere håndtering af giftig mad (i brugerfladen)

`Food` kan have negative `healthPoints`, og selv om spilleren ikke får `healthPoints` at se, før
maden er spist, så kunne programmet give en advarsel, inden man prøver at spise noget giftigt.

Måske et spørgsmål i stil med: *"This doesn't look healthy, are you sure you want to eat it?"* hvor
spilleren så skal svare yes eller no.

Det kræver endnu en returværdi fra `eat`-metoden, og måske en opdeling i en `tryToEat` og
`reallyEat` metode …

### Flere typer "consumables"

I stedet for at alt spiseligt er `Food`, kunne der være både `Food` og `Liquid`, der hver især
arver fra `Consumable`, der så arver fra `Item`.

De to klasser skal fungere ens mht. `healthPoints`, og den eneste forskel er, at brugeren skal skrive
`eat` for food-objekter, og `drink` for liquid-objekter.

```mermaid
classDiagram
    class Item
    class Consumable {
        -int healthPoints
    }
    class Food
    class Liquid
    Item <|-- Consumable
    Consumable <|-- Food
    Consumable <|-- Liquid
```

---

## Aflevering

Del 3 afleveres som de øvrige dele – det er sådan, vi kan se, at I er med, og I får feedback, før
I kaster jer over Weapons.

**Hvordan:** Push til samme repository som hidtil, og gen-aflevér linket.

**Hvornår:** torsdag 01-10 kl. 23:59 – se
[deadlines i projektoversigten](../../README.md#afleveringer-og-deadlines).

I er velkomne til at spørge ind til jeres løsning eller tanker om jeres løsning i dagens vejledning.

---

## Øvelser til arv

Til undervisningen hører disse to øvelser, som træner arv isoleret fra Adventure-projektet:

* **Arv-øvelsen** – klasserne `Konto`, `NemKonto` og `OpsparingsKonto`.
  Klon [DAT24_InheritanceExercise](https://github.com/ETALATE/DAT24_InheritanceExercise) og udfyld
  klasserne ud fra klassediagrammet.
* **Abstrakte klasser** – lav en abstrakt klasse `Animal` med en alder og en abstrakt metode
  `makeSound()`. Lav `Dog` og `Cat`, der arver fra `Animal` og implementerer `makeSound()`. `Dog`
  skal desuden have en metode `dogYears()`, der skriver hundens alder ud i både år og hundeår
  (alder gange 7).

---

**Næste:** [Del 4 – Weapons](del-4-weapons.md)
