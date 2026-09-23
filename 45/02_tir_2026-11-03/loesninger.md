# Vejledende løsninger – Interfaces og sortering

Her er vejledende løsninger til [opgaver.md](opgaver.md). Alle programmer er kørt, og outputtet er
det, de faktisk skriver.

> **Vejledende** betyder: din kode må gerne se anderledes ud. Det vigtige er, at du kan forklare,
> **hvorfor** listen ender i den rækkefølge, den gør.

`package`- og `import`-linjerne er med i de første klasser i hver del og udeladt i resten, hvor de
er de samme.

---

# Del A – Dit første interface

## Opgave 1 – Priced

```java
package dag2_sortering.festival;

// Alt, der kan lægges i kurven, kan fortælle sin pris og hvad det er
public interface Priced {

    int getPrice();

    String getDescription();
}
```

```java
package dag2_sortering.festival;

public class Ticket implements Priced {

    private String day;
    private boolean vip;

    public Ticket(String day, boolean vip) {
        this.day = day;
        this.vip = vip;
    }

    @Override
    public int getPrice() {
        if (vip) {
            return 1495;
        }
        return 895;
    }

    @Override
    public String getDescription() {
        if (vip) {
            return "VIP-billet, " + day;
        }
        return "Billet, " + day;
    }
}
```

```java
public class Merchandise implements Priced {

    private String name;
    private int price;

    public Merchandise(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return name;
    }
}
```

```java
package dag2_sortering.festival;

import java.util.ArrayList;

public class Opgave01 {

    public static void main(String[] args) {
        ArrayList<Priced> basket = new ArrayList<>();
        basket.add(new Ticket("fredag", false));
        basket.add(new Ticket("lørdag", true));
        basket.add(new Merchandise("T-shirt", 250));
        basket.add(new Merchandise("Regnponcho", 45));
        basket.add(new Locker(3));   // opgave 2

        printReceipt(basket);
    }

    public static void printReceipt(ArrayList<Priced> basket) {
        int total = 0;
        for (Priced item : basket) {
            System.out.println(item.getDescription() + ": " + item.getPrice() + " kr.");
            total += item.getPrice();
        }
        System.out.println("I alt: " + total + " kr.");
    }
}
```

Uden skabet (opgave 1) er totalen 2685 kr.

## Opgave 2 – En ny slags ting

```java
public class Locker implements Priced {

    private int days;

    public Locker(int days) {
        this.days = days;
    }

    @Override
    public int getPrice() {
        return days * 50;
    }

    @Override
    public String getDescription() {
        return "Skab i " + days + " dage";
    }
}
```

```text
Billet, fredag: 895 kr.
VIP-billet, lørdag: 1495 kr.
T-shirt: 250 kr.
Regnponcho: 45 kr.
Skab i 3 dage: 150 kr.
I alt: 2835 kr.
```

**Nul** linjer i `printReceipt`. Den kender kun `Priced`, og `Locker` holder løftet. Det er det
samme som med polymorfi i Adventure: en ny type kræver ingen ændringer i den kode, der bruger den.

## Opgave 3 – Kan det kompilere?

| # | Kode | Kompilerer? | Output / forklaring |
| --- | --- | --- | --- |
| 1 | `Priced q = new Priced();` | nej | `Priced is abstract; cannot be instantiated`. Et interface har ingen kode – man kan ikke lave et objekt af det. |
| 2 | `System.out.println(p.getPrice());` | ja | `895`. `Priced` har `getPrice()`, og objektet er en almindelig billet. |
| 3 | `Ticket t = new Merchandise("Kasket", 150);` | nej | `incompatible types: Merchandise cannot be converted to Ticket`. Begge er `Priced`, men en T-shirt er ikke en billet. |
| 4 | `Ticket t2 = p;` | nej | `incompatible types: Priced cannot be converted to Ticket`. Compileren ser kun variablens type, `Priced` – og ikke alle `Priced` er billetter. |
| 5 | `Priced[] items = new Priced[3];` | ja | Et **array** af typen `Priced` er fint – det indeholder bare tre `null`, indtil man lægger objekter i. Det er ikke det samme som `new Priced()`. |
| 6 | `ArrayList<Priced> list = ...; list.add(new Locker(2));` | ja | Et skab er `Priced`. |

`Free` uden `getDescription()`:

```text
error: Free is not abstract and does not override abstract method getDescription() in Priced
```

Klassen har lovet begge metoder med `implements Priced` og skal holde løftet.

---

# Del B – Sortér tal og tekst

## Opgave 4 – Forudsig rækkefølgen

```text
[-5, 9, 10, 100]
[-5, 10, 100, 9]
[Appelsin, ananas, banan, citron, Æble]
```

1. Den første liste er **tal** (`Integer`), og tal sorteres efter størrelse. Den anden er **tekst**,
   og tekst sorteres tegn for tegn fra venstre – som i en ordbog. `"10"` og `"100"` starter med
   `1`, som kommer før `9`. Derfor står `"9"` sidst. (Det er grunden til, at årstal og længder i
   Filmsamlingen skal sammenlignes som `int` – ikke som tekst.)
2. `Æble` ender **sidst**. Tekst sorteres efter tegnenes nummer i Unicode, og `Æ` har et højere
   nummer end alle a–z. Store bogstaver A–Z har lavere numre end små a–z, så `Appelsin` kommer før
   `ananas`. Javas `compareTo` kender ikke den danske alfabetiske rækkefølge.

## Opgave 5 – Din første Comparator

```java
package dag2_sortering.tekst;

import java.util.Comparator;

public class IgnoreCaseComparator implements Comparator<String> {

    @Override
    public int compare(String text1, String text2) {
        return text1.compareToIgnoreCase(text2);
    }
}
```

```java
public class ShortestFirstComparator implements Comparator<String> {

    @Override
    public int compare(String text1, String text2) {
        return Integer.compare(text1.length(), text2.length());
    }
}
```

```java
fruits.sort(new IgnoreCaseComparator());
System.out.println(fruits);

fruits.sort(new ShortestFirstComparator());
System.out.println(fruits);
```

```text
[ananas, Appelsin, banan, citron, kiwi]
[kiwi, banan, ananas, citron, Appelsin]
```

`ananas` og `citron` er begge 6 tegn, så `ShortestFirstComparator` svarer `0` for dem. Når to er
lige, beholder `sort` dem i den rækkefølge, de **havde** – og listen var lige blevet sorteret
alfabetisk. Sorterer du efter længde uden at have sorteret alfabetisk først, står de i
tilføjelsesrækkefølgen: `citron` før `ananas`.

---

# Del C – Løbere

## Opgave 6 – Resultatlisten (Comparable)

I `Runner`:

```java
public class Runner implements Comparable<Runner> {

    // ... som før

    // Løberens naturlige rækkefølge: hurtigste tid først
    @Override
    public int compareTo(Runner other) {
        return Integer.compare(timeInSeconds, other.timeInSeconds);
    }
}
```

```java
package dag2_sortering.loeb;

import java.util.ArrayList;
import java.util.Collections;

public class Opgave06 {

    public static void main(String[] args) {
        ArrayList<Runner> runners = new ArrayList<>();
        runners.add(new Runner("Mette Hansen", "Amager Løb", 17, 2291));
        runners.add(new Runner("Ali Hassan", "Frederiksberg AK", 3, 2104));
        runners.add(new Runner("ida Mortensen", "Amager Løb", 42, 2230));
        runners.add(new Runner("Jonas Berg", "Sparta", 8, 2475));
        runners.add(new Runner("Sofie Lund", "Sparta", 25, 2180));
        runners.add(new Runner("Karim Ali", "Frederiksberg AK", 11, 2291));

        Collections.sort(runners);

        for (int i = 0; i < runners.size(); i++) {
            System.out.println((i + 1) + ". " + runners.get(i));
        }
    }
}
```

```text
1. #3 Ali Hassan (Frederiksberg AK) 35:04
2. #25 Sofie Lund (Sparta) 36:20
3. #42 ida Mortensen (Amager Løb) 37:10
4. #17 Mette Hansen (Amager Løb) 38:11
5. #11 Karim Ali (Frederiksberg AK) 38:11
6. #8 Jonas Berg (Sparta) 41:15
```

Mette og Karim har samme tid. `compareTo` svarer `0`, så de står i tilmeldingsrækkefølgen. I et
rigtigt løb ville de dele 4.-pladsen – prøv selv at få det til at stå sådan, hvis du har tid.

## Opgave 7 – Andre rækkefølger (Comparator)

```java
package dag2_sortering.loeb;

import java.util.Comparator;

public class NameComparator implements Comparator<Runner> {

    @Override
    public int compare(Runner runner1, Runner runner2) {
        return runner1.getName().compareToIgnoreCase(runner2.getName());
    }
}
```

```java
public class BibNumberComparator implements Comparator<Runner> {

    @Override
    public int compare(Runner runner1, Runner runner2) {
        return Integer.compare(runner1.getBibNumber(), runner2.getBibNumber());
    }
}
```

```java
public class ClubComparator implements Comparator<Runner> {

    @Override
    public int compare(Runner runner1, Runner runner2) {
        return runner1.getClub().compareToIgnoreCase(runner2.getClub());
    }
}
```

```java
package dag2_sortering.loeb;

import java.util.ArrayList;

public class Opgave07 {

    public static void main(String[] args) {
        ArrayList<Runner> runners = new ArrayList<>();
        // ... de seks løbere

        System.out.println("--- Navn ---");
        runners.sort(new NameComparator());
        printAll(runners);

        System.out.println("--- Startnummer ---");
        runners.sort(new BibNumberComparator());
        printAll(runners);

        System.out.println("--- Klub ---");
        runners.sort(new ClubComparator());
        printAll(runners);
    }

    public static void printAll(ArrayList<Runner> runners) {
        for (Runner runner : runners) {
            System.out.println(runner);
        }
    }
}
```

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
--- Klub ---
#17 Mette Hansen (Amager Løb) 38:11
#42 ida Mortensen (Amager Løb) 37:10
#3 Ali Hassan (Frederiksberg AK) 35:04
#11 Karim Ali (Frederiksberg AK) 38:11
#8 Jonas Berg (Sparta) 41:15
#25 Sofie Lund (Sparta) 36:20
```

Inden for hver klub står løberne efter **startnummer**. Det er ikke `ClubComparator`, der gør det –
den svarer `0` for to løbere i samme klub. Det er, fordi listen lige var sorteret efter startnummer,
og `sort` beholder den gamle rækkefølge for dem, der er lige. Vil man bestemme rækkefølgen inden for
klubben, skal man bruge `thenComparing` (udfordring 1).

## Opgave 8 – Sortér en kopi

```java
package dag2_sortering.loeb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Race {

    private ArrayList<Runner> runners = new ArrayList<>();

    public void addRunner(Runner runner) {
        runners.add(runner);
    }

    public ArrayList<Runner> getRunners() {
        return runners;
    }

    // Resultatlisten: en sorteret KOPI, hurtigste først
    public ArrayList<Runner> getResults() {
        ArrayList<Runner> results = new ArrayList<>(runners);
        Collections.sort(results);
        return results;
    }

    public ArrayList<Runner> getRunnersSortedBy(Comparator<Runner> comparator) {
        ArrayList<Runner> sorted = new ArrayList<>(runners);
        sorted.sort(comparator);
        return sorted;
    }

    public Runner getWinner() {
        return getResults().get(0);
    }
}
```

```text
Vinder: #3 Ali Hassan (Frederiksberg AK) 35:04
Først på resultatlisten: Ali Hassan
Først på tilmeldingslisten: Mette Hansen
Lavest startnummer: Ali Hassan
Først på tilmeldingslisten: Mette Hansen
```

Parameteren er `Comparator<Runner>`, så man kan give **alle** comparatorer for løbere med –
`NameComparator`, `ClubComparator`, `BibNumberComparator` og dem, der bliver skrevet i morgen.
`Race` behøver ikke kende dem. Det er præcis det, `getMoviesSorted(Comparator<Movie> comparator)`
gør i Filmsamlingen.

`getWinner()` bruger `getResults()` i stedet for at gentage sorteringen. Er løbet tomt, giver
`get(0)` en `IndexOutOfBoundsException` – i et rigtigt program skulle det tjekkes først.

---

# Del D – Find fejlen

## Opgave 9 – Hvad er der galt?

```text
--- Hurtigste først ---
#8 Jonas Berg (Sparta) 41:15
#17 Mette Hansen (Amager Løb) 38:11
#42 ida Mortensen (Amager Løb) 37:10
#3 Ali Hassan (Frederiksberg AK) 35:04
--- Navn ---
#3 Ali Hassan (Frederiksberg AK) 35:04
#8 Jonas Berg (Sparta) 41:15
#17 Mette Hansen (Amager Løb) 38:11
#42 ida Mortensen (Amager Løb) 37:10
--- Klub ---
#3 Ali Hassan (Frederiksberg AK) 35:04
#42 ida Mortensen (Amager Løb) 37:10
#8 Jonas Berg (Sparta) 41:15
#17 Mette Hansen (Amager Løb) 38:11
```

**`FastestFirstComparator`** sætter den **langsomste** først. `runner2` og `runner1` er byttet om i
`Integer.compare`. Ret til:

```java
return Integer.compare(runner1.getTimeInSeconds(), runner2.getTimeInSeconds());
```

Husk reglen: `compare(a, b)` skal være negativ, når `a` skal stå først. Står `runner1` først i
`Integer.compare`, bliver det stigende rækkefølge.

**`NameComparator`** sætter `ida Mortensen` sidst, fordi `compareTo` sætter alle store bogstaver før
små. Ret `compareTo` til `compareToIgnoreCase`.

**`ClubComparator`** sammenligner **navne**, ikke klubber. Den er næsten sikkert kopieret fra
`NameComparator`, og så er `getName()` ikke blevet rettet til `getClub()`. Den er den farligste,
fordi resultatet **ser rigtigt ud** – listen er pænt sorteret, bare efter noget andet. Man opdager
den kun, hvis man tjekker resultatet mod det, man forventede. Det er derfor, Filmsamling del 9
kræver en test for **hver** comparator.

---

# Udfordringer

## Udfordring 1 – Klubmesterskabet

```java
public class TimeComparator implements Comparator<Runner> {

    @Override
    public int compare(Runner runner1, Runner runner2) {
        return Integer.compare(runner1.getTimeInSeconds(), runner2.getTimeInSeconds());
    }
}
```

```java
Comparator<Runner> clubThenTime = new ClubComparator().thenComparing(new TimeComparator());
Opgave07.printAll(race.getRunnersSortedBy(clubThenTime));
```

```text
#42 ida Mortensen (Amager Løb) 37:10
#17 Mette Hansen (Amager Løb) 38:11
#3 Ali Hassan (Frederiksberg AK) 35:04
#11 Karim Ali (Frederiksberg AK) 38:11
#25 Sofie Lund (Sparta) 36:20
#8 Jonas Berg (Sparta) 41:15
```

`TimeComparator` er nødvendig, fordi `thenComparing` skal have en `Comparator` – og `Runner`'s
`compareTo` er ikke en comparator.

## Udfordring 2 – Hvem kom sidst i mål?

```java
Opgave07.printAll(race.getRunnersSortedBy(new TimeComparator().reversed()));
```

```text
#8 Jonas Berg (Sparta) 41:15
#17 Mette Hansen (Amager Løb) 38:11
#11 Karim Ali (Frederiksberg AK) 38:11
#42 ida Mortensen (Amager Løb) 37:10
#25 Sofie Lund (Sparta) 36:20
#3 Ali Hassan (Frederiksberg AK) 35:04
```

## Udfordring 3 – Din egen thenComparing

```java
package dag2_sortering.loeb;

import java.util.Comparator;

// Gør det samme som first.thenComparing(second)
public class ThenComparator implements Comparator<Runner> {

    private Comparator<Runner> first;
    private Comparator<Runner> second;

    public ThenComparator(Comparator<Runner> first, Comparator<Runner> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compare(Runner runner1, Runner runner2) {
        int result = first.compare(runner1, runner2);
        if (result != 0) {
            return result;
        }
        return second.compare(runner1, runner2);
    }
}
```

```java
race.getRunnersSortedBy(new ThenComparator(new ClubComparator(), new TimeComparator()))
```

giver præcis samme liste som i udfordring 1. Læg mærke til, at `ThenComparator` **selv** er en
comparator, som indeholder to andre. Den kan altså gives med som `first` til en ny
`ThenComparator` – så har man sortering efter tre ting.

## Udfordring 4 – Arrays

```java
Arrays.sort(podium);
System.out.println(podium[0].getName());
Arrays.sort(podium, new NameComparator());
System.out.println(podium[0].getName());
```

```text
Sofie Lund
Jonas Berg
```

`Arrays.sort(podium)` bruger `compareTo` (tid), `Arrays.sort(podium, comparator)` bruger
comparatoren. Husk `import java.util.Arrays;`.

## Udfordring 5 – Efternavn

```java
public class LastNameComparator implements Comparator<Runner> {

    @Override
    public int compare(Runner runner1, Runner runner2) {
        return lastName(runner1).compareToIgnoreCase(lastName(runner2));
    }

    // Efternavnet er det sidste ord i navnet
    private String lastName(Runner runner) {
        String name = runner.getName();
        return name.substring(name.lastIndexOf(" ") + 1);
    }
}
```

```text
#11 Karim Ali (Frederiksberg AK) 38:11
#8 Jonas Berg (Sparta) 41:15
#17 Mette Hansen (Amager Løb) 38:11
#3 Ali Hassan (Frederiksberg AK) 35:04
#25 Sofie Lund (Sparta) 36:20
#42 ida Mortensen (Amager Løb) 37:10
```

Et navn uden mellemrum virker også: `lastIndexOf(" ")` giver `-1`, så `substring(0)` giver hele
navnet – `"Madonna"` sorteres som `"Madonna"`. Hjælpemetoden er `private`, fordi kun comparatoren
selv skal bruge den.
