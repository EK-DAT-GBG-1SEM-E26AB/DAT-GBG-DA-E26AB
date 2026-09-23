# Opgaver – Interfaces og sortering

Dagens opgaver er delt op sådan:

* **Del A** – dit første interface
* **Del B** – sortér tal og tekst
* **Del C** – løbere: `Comparable`, `Comparator` og en sorteret kopi. **Den vigtigste del** i
  forhold til Filmsamling del 9.
* **Del D** – find fejlen i tre comparatorer
* **Udfordringer** – til dem, der vil videre

Lav opgaverne i et nyt IntelliJ-projekt `uge45-sortering` (et almindeligt projekt, ikke Maven), i
package `dag2_sortering`. Giv hver del sin egen under-package – `dag2_sortering.festival`,
`dag2_sortering.tekst`, `dag2_sortering.loeb` og `dag2_sortering.fejl` – så klassenavnene ikke
støder sammen.

Der er [vejledende løsninger](loesninger.md) – men prøv selv først.

> **Filmsamlingen først?** Når del C og D virker, kan du det, der skal til i
> [del 9](../../projekter/filmsamling/del-9-sortering.md). Del A, B og udfordringerne kan vente, hvis
> gruppen har brug for dig.

---

# Del A – Dit første interface

Til en musikfestival skal der laves en indkøbskurv. I kurven kan der ligge billetter og
festival-merchandise. De to har ikke noget med hinanden at gøre – en T-shirt er ikke en slags
billet – men de har **en pris** og **en beskrivelse**.

## Opgave 1 – Priced

Lav i package `dag2_sortering.festival`:

1. Et interface `Priced` med to metoder: `int getPrice()` og `String getDescription()`.
2. En klasse `Ticket`, der implementerer `Priced`. Den har attributterne `day` (`String`, fx
   `"fredag"`) og `vip` (`boolean`). En almindelig billet koster 895 kr., en VIP-billet 1495 kr.
   Beskrivelsen er `Billet, fredag` eller `VIP-billet, lørdag`.
3. En klasse `Merchandise`, der implementerer `Priced`. Den har `name` og `price`. Beskrivelsen er
   bare navnet.
4. En klasse `Opgave01` med en `main`, der lægger fire ting i en `ArrayList<Priced>` og kalder en
   metode `printReceipt(ArrayList<Priced> basket)`, der udskriver en kvittering:

```java
basket.add(new Ticket("fredag", false));
basket.add(new Ticket("lørdag", true));
basket.add(new Merchandise("T-shirt", 250));
basket.add(new Merchandise("Regnponcho", 45));
```

Forventet output:

```text
Billet, fredag: 895 kr.
VIP-billet, lørdag: 1495 kr.
T-shirt: 250 kr.
Regnponcho: 45 kr.
I alt: 2685 kr.
```

Husk `@Override` på metoderne i `Ticket` og `Merchandise`.

## Opgave 2 – En ny slags ting

Festivalen vil også udleje skabe: 50 kr. pr. dag.

1. Lav en klasse `Locker`, der implementerer `Priced`. Den har `days`. Beskrivelsen er
   `Skab i 3 dage`.
2. Læg `new Locker(3)` i kurven i `Opgave01`.

```text
...
Skab i 3 dage: 150 kr.
I alt: 2835 kr.
```

Hvor mange linjer i `printReceipt` skulle du ændre? Hvorfor?

## Opgave 3 – Kan det kompilere?

Antag, at `Priced p = new Ticket("fredag", false);` står først i `main`. For hver linje: **kan den
kompilere?** Hvis ja, hvad skriver den (hvis den skriver noget)? Hvis nej, hvorfor ikke?

| # | Kode | Kompilerer? | Output / forklaring |
| --- | --- | --- | --- |
| 1 | `Priced q = new Priced();` | | |
| 2 | `System.out.println(p.getPrice());` | | |
| 3 | `Ticket t = new Merchandise("Kasket", 150);` | | |
| 4 | `Ticket t2 = p;` | | |
| 5 | `Priced[] items = new Priced[3];` | | |
| 6 | `ArrayList<Priced> list = new ArrayList<>(); list.add(new Locker(2));` | | |

Og til sidst: hvad sker der, hvis du laver en klasse `Free implements Priced`, der kun har
`getPrice()` – ikke `getDescription()`?

Tjek dine svar i IntelliJ.

---

# Del B – Sortér tal og tekst

Lav opgaverne i package `dag2_sortering.tekst`.

## Opgave 4 – Forudsig rækkefølgen

**Skriv dit gæt ned, før du kører koden.**

```java
import java.util.ArrayList;
import java.util.Collections;

public class Opgave04 {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(9);
        numbers.add(100);
        numbers.add(-5);
        Collections.sort(numbers);
        System.out.println(numbers);

        ArrayList<String> texts = new ArrayList<>();
        texts.add("10");
        texts.add("9");
        texts.add("100");
        texts.add("-5");
        Collections.sort(texts);
        System.out.println(texts);

        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("banan");
        fruits.add("Æble");
        fruits.add("citron");
        fruits.add("Appelsin");
        fruits.add("ananas");
        Collections.sort(fruits);
        System.out.println(fruits);
    }
}
```

1. Hvorfor bliver de to første lister ikke sorteret ens?
2. Hvor ender `Æble`? Hvorfor?

## Opgave 5 – Din første Comparator

`String`'s naturlige rækkefølge sætter store bogstaver før små. Det vil vi ikke altid.

1. Lav en klasse `IgnoreCaseComparator implements Comparator<String>`, der sammenligner to tekster
   uden hensyn til store og små bogstaver.
2. Lav en klasse `ShortestFirstComparator implements Comparator<String>`, der sætter den korteste
   tekst først.
3. Sortér denne liste med dem begge – først den ene, så den anden – og udskriv den efter hver
   sortering:

```java
fruits.add("banan");
fruits.add("kiwi");
fruits.add("citron");
fruits.add("Appelsin");
fruits.add("ananas");
```

Forventet output:

```text
[ananas, Appelsin, banan, citron, kiwi]
[kiwi, banan, ananas, citron, Appelsin]
```

`ananas` og `citron` er lige lange. Hvorfor står `ananas` først?

---

# Del C – Løbere

Et motionsløb skal have en resultatliste. Lav opgaverne i package `dag2_sortering.loeb`.

Her er `Runner` – skriv den af, eller kopiér den:

```java
public class Runner {

    private String name;
    private String club;
    private int bibNumber;        // startnummer
    private int timeInSeconds;

    public Runner(String name, String club, int bibNumber, int timeInSeconds) {
        this.name = name;
        this.club = club;
        this.bibNumber = bibNumber;
        this.timeInSeconds = timeInSeconds;
    }

    public String getName() {
        return name;
    }

    public String getClub() {
        return club;
    }

    public int getBibNumber() {
        return bibNumber;
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    // Fx 2291 sekunder -> "38:11"
    public String getFormattedTime() {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        if (seconds < 10) {
            return minutes + ":0" + seconds;
        }
        return minutes + ":" + seconds;
    }

    @Override
    public String toString() {
        return "#" + bibNumber + " " + name + " (" + club + ") " + getFormattedTime();
    }
}
```

Og løberne – de bruges i alle opgaverne i del C:

```java
runners.add(new Runner("Mette Hansen", "Amager Løb", 17, 2291));
runners.add(new Runner("Ali Hassan", "Frederiksberg AK", 3, 2104));
runners.add(new Runner("ida Mortensen", "Amager Løb", 42, 2230));
runners.add(new Runner("Jonas Berg", "Sparta", 8, 2475));
runners.add(new Runner("Sofie Lund", "Sparta", 25, 2180));
runners.add(new Runner("Karim Ali", "Frederiksberg AK", 11, 2291));
```

(Ja, `ida` er skrevet med lille – hun har selv tastet sit navn ind ved tilmeldingen.)

## Opgave 6 – Resultatlisten (Comparable)

1. Lad `Runner` implementere `Comparable<Runner>`, så den **naturlige rækkefølge** er efter tid,
   **hurtigste først**. Brug `Integer.compare`.
2. Lav `Opgave06` med en `main`, der lægger løberne i en `ArrayList<Runner>`, sorterer den med
   `Collections.sort` og udskriver den med placering foran:

```text
1. #3 Ali Hassan (Frederiksberg AK) 35:04
2. #25 Sofie Lund (Sparta) 36:20
3. #42 ida Mortensen (Amager Løb) 37:10
4. #17 Mette Hansen (Amager Løb) 38:11
5. #11 Karim Ali (Frederiksberg AK) 38:11
6. #8 Jonas Berg (Sparta) 41:15
```

> **Hjælp:** Placeringen er ikke en del af `Runner`. Brug et almindeligt `for`-loop med `i`, og
> skriv `(i + 1) + ". "` foran.

## Opgave 7 – Andre rækkefølger (Comparator)

Lav tre comparatorer, hver i sin egen fil:

* `NameComparator` – alfabetisk efter navn, uanset store og små bogstaver
* `BibNumberComparator` – efter startnummer, laveste først
* `ClubComparator` – alfabetisk efter klub

Lav `Opgave07`, der sorterer listen med hver af dem og udskriver den. Lav en hjælpemetode
`printAll(ArrayList<Runner> runners)`, så du ikke skriver det samme loop tre gange.

Forventet output for navn og startnummer:

```text
--- Navn ---
#3 Ali Hassan (Frederiksberg AK) 35:04
#42 ida Mortensen (Amager Løb) 37:10
#8 Jonas Berg (Sparta) 41:15
#11 Karim Ali (Frederiksberg AK) 38:11
#17 Mette Hansen (Amager Løb) 38:11
#25 Sofie Lund (Sparta) 36:20
--- Startnummer ---
#3 Ali Hassan (Frederiksberg AK) 35:04
#8 Jonas Berg (Sparta) 41:15
#11 Karim Ali (Frederiksberg AK) 38:11
#17 Mette Hansen (Amager Løb) 38:11
#25 Sofie Lund (Sparta) 36:20
#42 ida Mortensen (Amager Løb) 37:10
```

Kig på klub-sorteringen: i hvilken rækkefølge står løberne **inden for** hver klub? Hvad afgør det?

## Opgave 8 – Sortér en kopi

I Filmsamlingen må sorteringen ikke ændre samlingens egen rækkefølge. Det samme gælder her:
tilmeldingslisten skal blive, som den er.

Lav en klasse `Race` med:

* en `private ArrayList<Runner> runners` og metoden `addRunner(Runner runner)`
* `getRunners()` – returnerer listen, som den er
* `getResults()` – returnerer en **sorteret kopi**, hurtigste først
* `getRunnersSortedBy(Comparator<Runner> comparator)` – returnerer en sorteret kopi efter den
  comparator, der gives med
* `getWinner()` – returnerer vinderen

Test den i `Opgave08`:

```java
System.out.println("Vinder: " + race.getWinner());
System.out.println("Først på resultatlisten: " + race.getResults().get(0).getName());
System.out.println("Først på tilmeldingslisten: " + race.getRunners().get(0).getName());
System.out.println("Lavest startnummer: " + race.getRunnersSortedBy(new BibNumberComparator()).get(0).getName());
System.out.println("Først på tilmeldingslisten: " + race.getRunners().get(0).getName());
```

```text
Vinder: #3 Ali Hassan (Frederiksberg AK) 35:04
Først på resultatlisten: Ali Hassan
Først på tilmeldingslisten: Mette Hansen
Lavest startnummer: Ali Hassan
Først på tilmeldingslisten: Mette Hansen
```

Den sidste linje er den vigtige: tilmeldingslisten er **ikke** blevet ændret af sorteringerne.

Læg mærke til typen på parameteren i `getRunnersSortedBy`: `Comparator<Runner>` – ikke
`NameComparator`. Hvad betyder det for, hvilke comparatorer man kan give med?

---

# Del D – Find fejlen

Lav opgaven i package `dag2_sortering.fejl` (kopiér `Runner` derover, med `compareTo`).

Her er tre comparatorer. **Alle tre kompilerer**, men de gør ikke det, kommentaren siger:

```java
// Skal sortere efter tid, hurtigste først
public class FastestFirstComparator implements Comparator<Runner> {

    @Override
    public int compare(Runner runner1, Runner runner2) {
        return Integer.compare(runner2.getTimeInSeconds(), runner1.getTimeInSeconds());
    }
}
```

```java
// Skal sortere alfabetisk efter navn, uanset store og små bogstaver
public class NameComparator implements Comparator<Runner> {

    @Override
    public int compare(Runner runner1, Runner runner2) {
        return runner1.getName().compareTo(runner2.getName());
    }
}
```

```java
// Skal sortere alfabetisk efter klub
public class ClubComparator implements Comparator<Runner> {

    @Override
    public int compare(Runner runner1, Runner runner2) {
        return runner1.getName().compareToIgnoreCase(runner2.getName());
    }
}
```

## Opgave 9 – Hvad er der galt?

Brug disse fire løbere:

```java
runners.add(new Runner("Mette Hansen", "Amager Løb", 17, 2291));
runners.add(new Runner("Ali Hassan", "Frederiksberg AK", 3, 2104));
runners.add(new Runner("ida Mortensen", "Amager Løb", 42, 2230));
runners.add(new Runner("Jonas Berg", "Sparta", 8, 2475));
```

For hver comparator:

1. Skriv ned, hvilken rækkefølge du **forventer**.
2. Sortér listen og udskriv den. Hvad bliver det?
3. Find fejlen, og ret den.

Den sidste fejl er den farligste. Hvorfor? (Tip: hvor kommer den slags fejl typisk fra, når man
laver fem næsten ens comparatorer?)

---

# Udfordringer

Lav dem i `dag2_sortering.loeb`.

## Udfordring 1 – Klubmesterskabet (thenComparing)

Lav en `TimeComparator` (samme rækkefølge som `compareTo`). Sortér så løberne efter klub – og
**inden for hver klub** efter tid – med `thenComparing`:

```text
#42 ida Mortensen (Amager Løb) 37:10
#17 Mette Hansen (Amager Løb) 38:11
#3 Ali Hassan (Frederiksberg AK) 35:04
#11 Karim Ali (Frederiksberg AK) 38:11
#25 Sofie Lund (Sparta) 36:20
#8 Jonas Berg (Sparta) 41:15
```

## Udfordring 2 – Hvem kom sidst i mål?

Brug `reversed()` til at sortere efter tid med den **langsomste** først.

## Udfordring 3 – Din egen thenComparing

Skriv en klasse `ThenComparator implements Comparator<Runner>`, der får **to** comparatorer i
constructoren og gør det samme som `first.thenComparing(second)`. Test, at den giver samme resultat
som i udfordring 1.

Så ved du præcis, hvad der sker inde i `thenComparing`.

## Udfordring 4 – Arrays

`Arrays.sort` (fra `java.util`) sorterer et **array** på samme måde som `Collections.sort`
sorterer en liste – med eller uden en comparator. Sortér dette array efter tid og derefter efter
navn, og udskriv det første element hver gang:

```java
Runner[] podium = {
        new Runner("Jonas Berg", "Sparta", 8, 2475),
        new Runner("Mette Hansen", "Amager Løb", 17, 2291),
        new Runner("Sofie Lund", "Sparta", 25, 2180)
};
```

```text
Sofie Lund
Jonas Berg
```

## Udfordring 5 – Efternavn

Lav en `LastNameComparator`, der sorterer efter **efternavn** – det sidste ord i navnet. Brug
`lastIndexOf(" ")` og `substring` (fra [04-09](../../36/05_fre_2026-09-04/README.md)).

```text
#11 Karim Ali (Frederiksberg AK) 38:11
#8 Jonas Berg (Sparta) 41:15
#17 Mette Hansen (Amager Løb) 38:11
#3 Ali Hassan (Frederiksberg AK) 35:04
#25 Sofie Lund (Sparta) 36:20
#42 ida Mortensen (Amager Løb) 37:10
```

Hvad sker der med et navn **uden** mellemrum, fx `"Madonna"`?
