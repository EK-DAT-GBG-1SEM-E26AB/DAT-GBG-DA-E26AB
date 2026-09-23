# Vejledende løsninger – Repetition 2: Objektorienteret

Her er vejledende løsninger til [opgaver.md](opgaver.md). Alle programmer er kørt, og outputtet er
det, de faktisk skriver.

> **Vejledende** betyder: din kode må gerne se anderledes ud. Det vigtige er, at ansvaret ligger det
> rigtige sted, og at du kan forklare, hvorfor.

`package`-linjen er udeladt herunder. Hver opgave ligger i sin egen under-package af
`dag2_objektorienteret`.

---

# Del A – Forudsig output

## Opgave A1 – To variable

```text
1
1 2
```

Efter `Counter c2 = c1;` er der **ét** objekt og to pile. `c2.increment()` tæller det fælles
objekt op, så `c1.getCount()` er `1`. `c2 = new Counter();` flytter kun `c2`'s pil hen på et nyt
objekt – `c1` peger stadig på det gamle.

## Opgave A2 – static

```text
1 2 3
```

`nextNumber` er `static`: der er **én** fælles for alle billetter, og den tæller op, hver gang en
billet laves. `number` er ikke `static`, så hver billet husker sit eget nummer. Var `number` også
`static`, ville alle tre billetter dele den samme – og alle ville skrive `3`.

## Opgave A3 – Hundene

```text
Fido siger vov
Bella siger vov (men meget lavt)
```

`Puppy`'s `describe()` kalder `super.describe()` – altså `Animal`'s. Inde i den kaldes
`getSound()` på **objektet**, som er en `Puppy`. `Puppy` har ingen `getSound()`, så Java leder
opad og finder `Dog`'s. Loopet kender kun `Animal`.

## Opgave A4 – remove

```text
[10, 30, 40]
[10, 40]
```

`numbers.remove(1)` fjerner elementet på **index** 1 (tallet 20). Et `int` som argument betyder
altid index. Vil man fjerne **tallet**, skal man give et `Integer`-objekt med:
`Integer.valueOf(30)`.

## Opgave A5 – Ens eller det samme?

```text
false
false
true
```

`p1` og `p2` er to forskellige objekter – to pile til to steder – så `==` er `false`. `Point` har
ingen `equals`, så den arver `Object`'s, der gør det samme som `==`. `String` har sin **egen**
`equals`, der sammenligner bogstaverne. `p3` peger på det samme objekt som `p1`.

## Opgave A6 – Mod nord

```text
Hallen
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "dag2_objektorienteret.forudsig.Room.getName()" because the return value of "dag2_objektorienteret.forudsig.Room.getNorth()" is null
	at dag2_objektorienteret.forudsig.ForudsigA6.main(ForudsigA6.java:8)
```

`north` er aldrig sat, så den er `null`. `"Færdig"` bliver aldrig skrevet – programmet stopper.
Beskeden siger præcis, hvad der var `null`, og linjenummeret siger hvor. Tjek først:

```java
Room north = hall.getNorth();
if (north != null) {
    System.out.println(north.getName());
} else {
    System.out.println("Der er ingen vej mod nord");
}
```

Det er præcis det, `go north` gjorde i Adventure.

---

# Del B ★ – Én eller to klasser

## Opgave 1 – Raflebæger

```java
import java.util.Random;

public class Die {

    private Random random = new Random();
    private int value;

    public Die() {
        roll();                       // en ny terning viser allerede et tal
    }

    public int roll() {
        value = random.nextInt(6) + 1;    // 0-5 + 1 = 1-6
        return value;
    }

    public int getValue() {
        return value;
    }
}
```

```java
import java.util.ArrayList;

public class DiceCup {

    private ArrayList<Die> dice = new ArrayList<>();

    public DiceCup(int numberOfDice) {
        for (int i = 0; i < numberOfDice; i++) {
            dice.add(new Die());
        }
    }

    // Ryster bægeret og returnerer det samlede antal øjne
    public int shake() {
        int sum = 0;
        for (Die die : dice) {
            sum += die.roll();
        }
        return sum;
    }

    // Terningernes øjne fra sidste rystelse – uden at ryste igen
    public ArrayList<Integer> getValues() {
        ArrayList<Integer> values = new ArrayList<>();
        for (Die die : dice) {
            values.add(die.getValue());
        }
        return values;
    }
}
```

```java
public class Opgave01 {

    public static void main(String[] args) {
        DiceCup cup = new DiceCup(5);

        for (int i = 0; i < 3; i++) {
            int sum = cup.shake();
            System.out.println(cup.getValues() + " = " + sum);
        }
    }
}
```

```text
[4, 6, 1, 6, 1] = 18
[2, 3, 1, 3, 5] = 14
[1, 6, 5, 1, 3] = 16
```

(Tallene er tilfældige og bliver nogle andre hos dig.)

* `Die` kalder `roll()` i sin constructor, så en ny terning viser et tal fra start – ellers ville
  `getValues()` give `0`, hvis man kaldte den før første rystelse.
* `shake()` og `getValues()` er to metoder, fordi de gør to ting: den ene **ændrer** terningerne,
  den anden **kigger** bare.
* `ArrayList<Integer>` udskrives pænt med `println` – `ArrayList` har sin egen `toString`.

## Opgave 2 – Bil med trailer

```java
public class Trailer {

    private int weight;

    public Trailer(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
```

```java
public class Car {

    private static final int MAX_TOTAL_WEIGHT = 3500;

    private String registration;
    private int weight;
    private Trailer trailer;          // null = ingen trailer

    public Car(String registration, int weight) {
        this.registration = registration;
        this.weight = weight;
    }

    public int getTotalWeight() {
        if (trailer == null) {
            return weight;
        }
        return weight + trailer.getWeight();
    }

    // Kobler traileren på, hvis totalvægten ikke bliver for høj
    public boolean attachTrailer(Trailer newTrailer) {
        if (trailer != null) {
            return false;             // der sidder allerede en
        }
        if (weight + newTrailer.getWeight() > MAX_TOTAL_WEIGHT) {
            return false;
        }
        trailer = newTrailer;
        return true;
    }

    public void detachTrailer() {
        trailer = null;
    }

    public boolean hasTrailer() {
        return trailer != null;
    }

    @Override
    public String toString() {
        return registration + ": " + getTotalWeight() + " kg";
    }
}
```

```text
AB 12 345: 1800 kg
Stor trailer: false
Lille trailer: true
AB 12 345: 2550 kg
Stor trailer oveni: false
AB 12 345: 1800 kg, trailer: false
```

`trailer` er `null`, når der ingen trailer er. Derfor tjekker `getTotalWeight()` for `null`,
**før** den kalder `trailer.getWeight()`. Grænsen 3500 er en konstant (`static final`), så den kun
står ét sted – som `FIRST_MOVIE_YEAR` i Filmsamlingen.

## Opgave 3 – Kort

```java
// Rækkefølgen betyder noget: den sidste slår dem før den
public enum Suit {
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES
}
```

```java
public class Card {

    private Suit suit;
    private int value;               // 1-13

    public Card(Suit suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    // Del 1: kun værdien tæller
    public boolean beats(Card other) {
        return value > other.value;
    }

    // Del 2: ved samme værdi afgør kuløren: spar > hjerter > ruder > klør
    public boolean beatsWithSuit(Card other) {
        if (value != other.value) {
            return value > other.value;
        }
        return suit.compareTo(other.suit) > 0;
    }

    @Override
    public String toString() {
        return suit + " " + value;
    }
}
```

```text
CLUBS 13 slår HEARTS 10: true
SPADES 10 slår HEARTS 10: false
SPADES 10 slår HEARTS 10 (med kulør): true
HEARTS 10 slår SPADES 10 (med kulør): false
HEARTS 10 slår CLUBS 13 (med kulør): false
```

Enum-værdier har en rækkefølge – den, de står i – og `compareTo` giver et positivt tal, hvis den
første står **efter** den anden. Derfor står `SPADES` sidst i enum'en. Alternativet er en lang
`if`-kæde med fire kulører – prøv at skrive den, og sammenlign.

`other.value` virker, selv om `value` er `private`: `private` betyder "kun inde i klassen `Card`" –
ikke "kun inde i dette objekt".

---

# Del C ★★ – Objekter, der holder på objekter

## Opgave 4 – Valget

```java
import java.util.ArrayList;

public class Election {

    private ArrayList<Candidate> candidates = new ArrayList<>();

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    public int getTotalVotes() {
        int total = 0;
        for (Candidate candidate : candidates) {
            total += candidate.getNumberOfVotes();
        }
        return total;
    }

    public ArrayList<Candidate> getCandidatesFromParty(String party) {
        ArrayList<Candidate> result = new ArrayList<>();
        for (Candidate candidate : candidates) {
            if (candidate.getParty().equalsIgnoreCase(party)) {
                result.add(candidate);
            }
        }
        return result;
    }

    public int getVotesForParty(String party) {
        int total = 0;
        for (Candidate candidate : getCandidatesFromParty(party)) {
            total += candidate.getNumberOfVotes();
        }
        return total;
    }

    // Returnerer null, hvis der ingen kandidater er
    public Candidate getWinner() {
        Candidate winner = null;
        for (Candidate candidate : candidates) {
            if (winner == null || candidate.getNumberOfVotes() > winner.getNumberOfVotes()) {
                winner = candidate;
            }
        }
        return winner;
    }
}
```

```text
Stemmer i alt: 3890
Grønt Parti: [Maja (Grønt Parti): 1200, Lise (Grønt Parti): 430]
Stemmer til Grønt Parti: 1630
Flest personlige stemmer: Peter (Frihedslisten): 1310
Tomt valg: null
```

* `getVotesForParty` genbruger `getCandidatesFromParty` i stedet for at gentage loopet.
* `getWinner` starter med `null` og tager den første kandidat, den møder. Så virker den også, når
  listen er tom – og når alle har 0 stemmer. At starte med `candidates.get(0)` ville give en
  `IndexOutOfBoundsException` ved et tomt valg.

## Opgave 5 – Opslagsord

```java
import java.util.ArrayList;

public class Keyword {

    private String name;
    private String description;
    private ArrayList<Keyword> seeAlso = new ArrayList<>();

    public Keyword(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void addSeeAlso(Keyword keyword) {
        seeAlso.add(keyword);
    }

    public boolean matches(String searchText) {
        return name.toLowerCase().contains(searchText.toLowerCase());
    }

    public String describe() {
        String text = name + ": " + description;
        if (!seeAlso.isEmpty()) {
            text += "\n  Se også: ";
            for (int i = 0; i < seeAlso.size(); i++) {
                if (i > 0) {
                    text += ", ";
                }
                text += seeAlso.get(i).getName();
            }
        }
        return text;
    }
}
```

```text
Arv: En klasse kan arve fra en anden med extends.
  Se også: Polymorfi, Abstrakt klasse
Polymorfi: Det samme metodekald kan gøre forskellige ting.
  Se også: Arv
Abstrakt klasse: En klasse, man ikke kan lave objekter af.
true
```

`seeAlso` er en liste af **samme** klasse, som den står i. Det er helt i orden – ligesom et `Room`
i Adventure kender sine naboer, der også er `Room`s. Og to opslagsord kan godt henvise til
hinanden: det er bare to pile.

Kommaerne: det første ord har intet komma foran, resten har – derfor `if (i > 0)`.

## Opgave 6 – Bundkortet

```java
public class MotherBoard {

    private SataDrive[] ports = new SataDrive[4];    // null = ledig plads

    // Sætter drevet i den første ledige port og returnerer portens nummer (0-3),
    // eller -1, hvis boardet er fyldt
    public int connect(SataDrive drive) {
        for (int i = 0; i < ports.length; i++) {
            if (ports[i] == null) {
                ports[i] = drive;
                return i;
            }
        }
        return -1;
    }

    public void disconnect(int port) {
        ports[port] = null;
    }

    public int getTotalSizeInGb() {
        int total = 0;
        for (SataDrive drive : ports) {
            if (drive != null) {
                total += drive.getSizeInGb();
            }
        }
        return total;
    }

    public String describe() {
        String text = "";
        for (int i = 0; i < ports.length; i++) {
            text += "Port " + i + ": ";
            if (ports[i] == null) {
                text += "ledig";
            } else {
                text += ports[i];
            }
            text += "\n";
        }
        return text + "I alt: " + getTotalSizeInGb() + " GB";
    }
}
```

```text
0
1
2
1
3
-1
Port 0: Samsung 870 (1000 GB)
Port 1: Seagate (4000 GB)
Port 2: Crucial MX500 (500 GB)
Port 3: Kingston (250 GB)
I alt: 5750 GB
```

`connect` er søgemønsteret: find den **første** ledige plads og stop (her med `return`). Efter
`disconnect(1)` er port 1 ledig igen, så *Seagate* havner dér – ikke i port 3.

---

# Del D ★★★ – Arv, abstrakte klasser og interfaces

## Opgave 7 – Medier

```java
public abstract class Media {

    private String name;
    private int durationInSeconds;

    public Media(String name, int durationInSeconds) {
        this.name = name;
        this.durationInSeconds = durationInSeconds;
    }

    public String getName() {
        return name;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    // Det, der er særligt for hver slags medie
    public abstract String getDetails();

    // Fælles for alle: navn og varighed – og så subklassens detaljer
    public String getInfo() {
        int minutes = durationInSeconds / 60;
        int seconds = durationInSeconds % 60;
        String time = minutes + ":" + seconds;
        if (seconds < 10) {
            time = minutes + ":0" + seconds;
        }
        return name + " [" + time + "] " + getDetails();
    }
}
```

```java
public class Audio extends Media {

    private double loudness;      // i dB, fx -10.4

    public Audio(String name, int durationInSeconds, double loudness) {
        super(name, durationInSeconds);
        this.loudness = loudness;
    }

    @Override
    public String getDetails() {
        return "lyd, " + loudness + " dB";
    }
}
```

```java
public class Video extends Media {

    private String aspectRatio;   // fx "16:9"

    public Video(String name, int durationInSeconds, String aspectRatio) {
        super(name, durationInSeconds);
        this.aspectRatio = aspectRatio;
    }

    @Override
    public String getDetails() {
        return "video, " + aspectRatio;
    }
}
```

```java
import java.util.ArrayList;

public class Opgave07 {

    public static void main(String[] args) {
        ArrayList<Media> library = new ArrayList<>();
        library.add(new Audio("Podcast: Java på 10 minutter", 605, -16.0));
        library.add(new Video("Introfilm", 94, "16:9"));
        library.add(new Audio("Jingle", 7, -10.4));
        library.add(new Video("Gammel reklame", 30, "4:3"));

        int totalSeconds = 0;
        for (Media media : library) {
            System.out.println(media.getInfo());
            totalSeconds += media.getDurationInSeconds();
        }
        System.out.println("Samlet varighed: " + totalSeconds + " sekunder");
    }
}
```

```text
Podcast: Java på 10 minutter [10:05] lyd, -16.0 dB
Introfilm [1:34] video, 16:9
Jingle [0:07] lyd, -10.4 dB
Gammel reklame [0:30] video, 4:3
Samlet varighed: 736 sekunder
```

`getInfo()` er skrevet **én gang** i `Media` og kalder `getDetails()` – som kører subklassens
udgave. Det er samme mønster som `getPayslip()` fra [polymorfi-dagen](../../40/05_fre_2026-10-02/README.md#også-inde-i-superklassen).
Ingen `instanceof`: en ny slags medie (fx `Image`) kræver kun en ny klasse.

## Opgave 8 – Figurer

```java
public interface Shape {

    double getArea();

    String getName();
}
```

```java
public class Circle implements Shape {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String getName() {
        return "cirkel med radius " + radius;
    }
}
```

`Square` og `Triangle` ligner `Circle`.

```java
import java.util.ArrayList;
import java.util.Locale;

public class Opgave08 {

    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Square(3));
        shapes.add(new Circle(2));
        shapes.add(new Square(1.5));
        shapes.add(new Circle(0.5));
        shapes.add(new Triangle(4, 5));   // udvidelsen

        double total = 0;
        for (Shape shape : shapes) {
            System.out.println(shape.getName() + ": " + format(shape.getArea()));
            total += shape.getArea();
        }
        System.out.println("Samlet areal: " + format(total));
        System.out.println("Største figur: " + findLargest(shapes).getName());
    }

    public static Shape findLargest(ArrayList<Shape> shapes) {
        Shape largest = shapes.get(0);
        for (Shape shape : shapes) {
            if (shape.getArea() > largest.getArea()) {
                largest = shape;
            }
        }
        return largest;
    }

    public static String format(double number) {
        return String.format(Locale.US, "%.2f", number);
    }
}
```

```text
kvadrat med side 3.0: 9.00
cirkel med radius 2.0: 12.57
kvadrat med side 1.5: 2.25
cirkel med radius 0.5: 0.79
trekant 4.0 x 5.0: 10.00
Samlet areal: 34.60
Største figur: cirkel med radius 2.0
```

**Ingen** linjer i `main` skulle ændres for trekanten – kun den, der lægger den i listen.

**Interface eller abstrakt klasse?** Figurerne har ingen fælles **data** – et kvadrat har en side,
en cirkel en radius – kun fælles **evner** (areal og navn). Derfor er et interface det naturlige.
Det kunne godt have været en abstrakt klasse med to abstrakte metoder, men så kunne figurerne ikke
arve fra noget andet.

## Udfordring – Sortér figurerne

```java
import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {

    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}
```

```java
shapes.sort(new AreaComparator());
```

```text
cirkel med radius 0.5: 0.79
kvadrat med side 1.5: 2.25
kvadrat med side 3.0: 9.00
trekant 4.0 x 5.0: 10.00
cirkel med radius 2.0: 12.57
```
