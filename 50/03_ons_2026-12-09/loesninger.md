# Vejledende løsninger – programmering som til eksamen

Her er vejledende løsninger til [opgaverne](opgaver.md). Alle er kørt med Java 21.

> **Vejledende** betyder: din løsning må gerne se anderledes ud. Det vigtige er, at den virker, at
> du kan forklare den, og at du kan begrunde dine valg. Svarene på *eksaminatorspørgsmålene* er
> korte – til eksamen skal du selv kunne sige dem med dine egne ord.

---

## Opgave 1 – Tekstlinjer

```java
import java.util.ArrayList;

public class TextLines {

    private ArrayList<String> lines = new ArrayList<>();

    public void add(String line) {
        lines.add(line);
    }

    public int countUnique() {
        ArrayList<String> seen = new ArrayList<>();
        for (String line : lines) {
            if (!seen.contains(line)) {
                seen.add(line);
            }
        }
        return seen.size();
    }

    // Returnerer den længste linje, eller null hvis der ingen linjer er
    public String getLongestLine() {
        String longest = null;
        for (String line : lines) {
            if (longest == null || line.length() > longest.length()) {
                longest = line;
            }
        }
        return longest;
    }

    // Udfordring: tæl unikke linjer uden at skelne mellem store og små bogstaver
    public int countUniqueIgnoreCase() {
        ArrayList<String> seen = new ArrayList<>();
        for (String line : lines) {
            String lower = line.toLowerCase();
            if (!seen.contains(lower)) {
                seen.add(lower);
            }
        }
        return seen.size();
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        TextLines text = new TextLines();
        text.add("hej");
        text.add("hej");
        text.add("med");
        text.add("dig");
        text.add("Hej");

        System.out.println(text.countUnique());             // 4
        System.out.println(text.countUniqueIgnoreCase());   // 3
        System.out.println(text.getLongestLine());          // hej

        TextLines empty = new TextLines();
        System.out.println(empty.getLongestLine());         // null
    }
}
```

**Eksaminatorspørgsmål**

* **Array eller `ArrayList`?** Vi ved ikke på forhånd, hvor mange linjer der kommer. Et array har
  en fast størrelse; en `ArrayList` vokser selv.
* **Tom liste?** `getLongestLine` returnerer `null`. Det er et rimeligt svar på "der er ingen" –
  men så skal den, der kalder metoden, huske at tjekke for `null`.

`contains` bruger `equals` til at sammenligne. Derfor virker `seen.contains(line)` på tekst – det
gør `==` ikke.

---

## Opgave 2 – Raflebæger

```java
import java.util.ArrayList;
import java.util.Random;

public class DiceCup {

    private int numberOfDice;
    private ArrayList<Integer> values = new ArrayList<>();
    private Random random = new Random();

    public DiceCup(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    // Ryster bægeret og returnerer summen af øjnene
    public int shake() {
        values.clear();
        int sum = 0;
        for (int i = 0; i < numberOfDice; i++) {
            int value = random.nextInt(6) + 1;   // 1 til 6
            values.add(value);
            sum += value;
        }
        return sum;
    }

    // Terningernes øjne fra sidste rystelse – uden at ryste igen
    public ArrayList<Integer> getValues() {
        return new ArrayList<>(values);
    }

    // Udfordring: hvor mange terninger viser value?
    public int countOf(int value) {
        int count = 0;
        for (int v : values) {
            if (v == value) {
                count++;
            }
        }
        return count;
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        DiceCup cup = new DiceCup(5);
        int sum = cup.shake();
        System.out.println("Sum: " + sum + " " + cup.getValues());
        System.out.println("Igen, uden at ryste: " + cup.getValues());
        System.out.println("Antal seksere: " + cup.countOf(6));
    }
}
```

**Eksaminatorspørgsmål**

* **Tal mellem 1 og 6:** `random.nextInt(6)` giver 0–5, så vi lægger 1 til.
* **Hvor gemmes øjnene?** I attributten `values`. Den bliver tømt og fyldt igen ved hver
  `shake`.
* **Kan den, der kalder, ændre listen?** Returnerer vi `values` direkte, kan man skrive
  `cup.getValues().clear()` og tømme bægeret udefra. Derfor returnerer vi en **kopi**:
  `new ArrayList<>(values)`.

Udskriften er tilfældig – din sum og dine øjne vil være nogle andre.

---

## Opgave 3 – Spillekort

```java
// Rækkefølgen betyder noget: ordinal() er 0 for CLUBS og 3 for SPADES
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
    private int value;           // 1–13

    public Card(Suit suit, int value) {
        if (value < 1 || value > 13) {
            throw new IllegalArgumentException("value skal være mellem 1 og 13, var " + value);
        }
        this.suit = suit;
        this.value = value;
    }

    // Del 2: kun værdien tæller
    public boolean beatsByValue(Card other) {
        return value > other.value;
    }

    // Del 3: ved samme værdi afgør kuløren – spar > hjerter > ruder > klør
    public boolean beats(Card other) {
        if (value != other.value) {
            return value > other.value;
        }
        return suit.ordinal() > other.suit.ordinal();
    }

    @Override
    public String toString() {
        return suit + " " + value;
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        Card heartsTen = new Card(Suit.HEARTS, 10);
        Card spadesTen = new Card(Suit.SPADES, 10);
        Card clubsKing = new Card(Suit.CLUBS, 13);

        System.out.println(clubsKing.beatsByValue(heartsTen));   // true
        System.out.println(heartsTen.beatsByValue(spadesTen));   // false – lige høje
        System.out.println(spadesTen.beats(heartsTen));          // true – spar slår hjerter
        System.out.println(heartsTen.beats(spadesTen));          // false

        try {
            new Card(Suit.CLUBS, 14);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }
}
```

Udskrift:

```text
true
false
true
false
Fejl: value skal være mellem 1 og 13, var 14
```

**Eksaminatorspørgsmål**

* **Datatype til kuløren:** en `enum`. Med en `String` kunne man skrive `"hjerte"`, `"Hjerter"` og
  `"hearts"` – og compileren ville ikke sige noget. Med en `enum` findes der kun fire mulige værdier.
* **Rækkefølgen:** `ordinal()` giver konstantens plads i `enum`'en, 0 for den første. Står
  konstanterne i den rækkefølge, de slår hinanden, kan vi sammenligne dem med `>`. Det kræver en
  kommentar i koden – ellers bytter nogen en dag om på dem.

---

## Opgave 4 – Bil og trailer

```java
public class Trailer {

    private int weight;       // kg

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

    private int weight;
    private Trailer trailer;      // null = ingen trailer tilkoblet

    public Car(int weight) {
        this.weight = weight;
    }

    public int getTotalWeight() {
        if (trailer == null) {
            return weight;
        }
        return weight + trailer.getWeight();
    }

    // Returnerer true, hvis traileren blev koblet på
    public boolean attachTrailer(Trailer newTrailer) {
        if (weight + newTrailer.getWeight() > MAX_TOTAL_WEIGHT) {
            return false;
        }
        trailer = newTrailer;
        return true;
    }

    public void detachTrailer() {
        trailer = null;
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        Car car = new Car(1800);
        System.out.println(car.getTotalWeight());                     // 1800

        System.out.println(car.attachTrailer(new Trailer(2000)));     // false – 3800 kg
        System.out.println(car.getTotalWeight());                     // 1800

        System.out.println(car.attachTrailer(new Trailer(1700)));     // true – præcis 3500 kg
        System.out.println(car.getTotalWeight());                     // 3500
    }
}
```

**Eksaminatorspørgsmål**

* **Ingen trailer:** attributten er `null`.
* **Glemmer man tjekket,** kaster `trailer.getWeight()` en `NullPointerException`, så snart bilen
  ikke har en trailer.
* **"Ikke overstiger 3500"** betyder, at præcis 3500 er tilladt. Derfor afviser vi med `> 3500`.
  `main` tester netop grænsen: 1800 + 1700 = 3500 bliver godkendt.

---

## Opgave 5 – Navne

```java
public class Name {

    private String firstName;
    private String middleName;     // null, hvis der ikke er et mellemnavn
    private String lastName;

    public Name(String fullName) {
        String[] parts = fullName.trim().split(" +");   // ét eller flere mellemrum
        firstName = parts[0];
        lastName = parts[parts.length - 1];

        if (parts.length > 2) {
            // Alt mellem fornavn og efternavn er mellemnavn(e)
            middleName = parts[1];
            for (int i = 2; i < parts.length - 1; i++) {
                middleName = middleName + " " + parts[i];
            }
        }
    }

    public String getInitials() {
        String initials = "" + firstName.charAt(0);
        if (middleName != null) {
            // Der kan være flere mellemnavne – tag forbogstavet fra hvert af dem
            for (String part : middleName.split(" ")) {
                initials += part.charAt(0);
            }
        }
        return initials + lastName.charAt(0);
    }

    @Override
    public String toString() {
        if (middleName == null) {
            return firstName + " " + lastName;
        }
        return firstName + " " + middleName + " " + lastName;
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        Name two = new Name("Ida Hansen");
        Name three = new Name("Ida Marie Hansen");
        Name four = new Name("  Ida  Marie Louise Hansen ");

        System.out.println(two);                   // Ida Hansen
        System.out.println(three);                 // Ida Marie Hansen
        System.out.println(four);                  // Ida Marie Louise Hansen
        System.out.println(three.getInitials());   // IMH
        System.out.println(four.getInitials());    // IMLH
    }
}
```

**Eksaminatorspørgsmål**

* `"Ida Hansen".split(" ")` giver `{"Ida", "Hansen"}` – længde 2. `"Ida Marie Hansen".split(" ")`
  giver længde 3.
* **Efternavnet** er altid det sidste element: `parts[parts.length - 1]`.

`split(" +")` deler ved **et eller flere** mellemrum, så et dobbelt mellemrum ikke giver en tom
del. `trim()` fjerner mellemrum i starten og slutningen. Almindelig `split(" ")` er fin, hvis du
antager, at navnet er skrevet pænt – men sig det højt.

---

## Opgave 6 – Brugernavne

```java
import java.util.Random;

public class User {

    private String fullName;
    private String userId;

    public User(String fullName) {
        this.fullName = fullName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Fire små bogstaver efterfulgt af fire cifre, fx "idha4821"
    public boolean isValidUserId() {
        if (userId == null || userId.length() != 8) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            char c = userId.charAt(i);
            if (c < 'a' || c > 'z') {
                return false;
            }
        }
        for (int i = 4; i < 8; i++) {
            if (!Character.isDigit(userId.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // To bogstaver fra fornavnet, to fra efternavnet og fire tilfældige cifre
    public void createUserId() {
        String[] parts = fullName.toLowerCase().split(" ");
        String first = parts[0].substring(0, 2);
        String last = parts[parts.length - 1].substring(0, 2);
        int digits = new Random().nextInt(10000);          // 0–9999
        userId = first + last + String.format("%04d", digits);
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        User user = new User("Ida Hansen");

        user.setUserId("idha4821");
        System.out.println(user.isValidUserId());    // true
        user.setUserId("IDHA4821");
        System.out.println(user.isValidUserId());    // false – store bogstaver
        user.setUserId("idh48211");
        System.out.println(user.isValidUserId());    // false – kun tre bogstaver
        user.setUserId("idha482");
        System.out.println(user.isValidUserId());    // false – for kort

        user.createUserId();
        System.out.println(user.getUserId() + " " + user.isValidUserId());   // fx idha0427 true
    }
}
```

**Eksaminatorspørgsmål**

* **Ciffer:** `Character.isDigit(c)`. **Lille bogstav:** `c >= 'a' && c <= 'z'` – `char` er et tal,
  så man kan sammenligne med `<` og `>`. (`Character.isLowerCase(c)` godkender også `æ`, `ø` og `å`.
  Om det er rigtigt, afhænger af, hvad opgaven mener med "små bogstaver".)
* **Altid fire cifre:** `String.format("%04d", 42)` giver `"0042"`.

Hvad sker der, hvis fornavnet kun har ét bogstav? Så kaster `substring(0, 2)` en exception. Det er
et godt spørgsmål at stille sig selv – og et godt svar at have klar.

---

## Opgave 7 – Figurer

```java
public interface Shape {

    double getArea();
}
```

```java
public class Square implements Shape {

    private double width;

    public Square(double width) {
        this.width = width;
    }

    @Override
    public double getArea() {
        return width * width;
    }

    @Override
    public String toString() {
        return "Kvadrat med siden " + width;
    }
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
    public String toString() {
        return "Cirkel med radius " + radius;
    }
}
```

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
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Square(3));
        shapes.add(new Circle(1));
        shapes.add(new Square(1.5));
        shapes.add(new Circle(2));

        double total = 0;
        for (Shape shape : shapes) {
            System.out.printf("%s: %.2f%n", shape, shape.getArea());
            total += shape.getArea();
        }
        System.out.printf("Samlet areal: %.2f%n", total);

        shapes.sort(new AreaComparator());
        System.out.println("Mindste: " + shapes.get(0));
        System.out.println("Største: " + shapes.get(shapes.size() - 1));
    }
}
```

Udskrift:

```text
Kvadrat med siden 3.0: 9.00
Cirkel med radius 1.0: 3.14
Kvadrat med siden 1.5: 2.25
Cirkel med radius 2.0: 12.57
Samlet areal: 26.96
Mindste: Kvadrat med siden 1.5
Største: Cirkel med radius 2.0
```

På en computer med danske indstillinger skriver `printf` komma i stedet for punktum: `9,00`.

**Eksaminatorspørgsmål**

* **Listens type:** `ArrayList<Shape>`, så den kan indeholde **både** cirkler og kvadrater.
* **Hvordan ved løkken det?** Det gør den ikke. Den kalder `getArea()`, og Java kalder den udgave,
  der hører til objektets **faktiske** klasse – polymorfi.
* **Interface eller abstrakt klasse?** Et interface har ingen attributter, og en klasse kan
  implementere flere. En abstrakt klasse kan have attributter og færdig kode, men man kan kun arve
  fra én. Se [05-10](../../41/01_man_2026-10-05/README.md#abstrakt-klasse-eller-interface).

---

## Opgave 8 – Medier i en fil

```java
public abstract class Media {

    private String name;
    private int durationSeconds;

    public Media(String name, int durationSeconds) {
        this.name = name;
        this.durationSeconds = durationSeconds;
    }

    public String getName() {
        return name;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    // Hver slags medie ved selv, hvilke ekstra oplysninger den har
    public abstract String getDetails();

    public String getInfoLine() {
        return name + ";" + durationSeconds + ";" + getDetails();
    }
}
```

```java
public class Audio extends Media {

    private double loudness;     // dB, fx -10.4

    public Audio(String name, int durationSeconds, double loudness) {
        super(name, durationSeconds);
        this.loudness = loudness;
    }

    @Override
    public String getDetails() {
        return "loudness " + loudness + " dB";
    }
}
```

```java
public class Video extends Media {

    private String aspectRatio;  // fx "16:9"

    public Video(String name, int durationSeconds, String aspectRatio) {
        super(name, durationSeconds);
        this.aspectRatio = aspectRatio;
    }

    @Override
    public String getDetails() {
        return "aspect ratio " + aspectRatio;
    }
}
```

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MediaWriter {

    public static void writeInfo(ArrayList<Media> mediaList, String fileName) throws FileNotFoundException {
        PrintStream output = new PrintStream(fileName);
        for (Media media : mediaList) {
            output.println(media.getInfoLine());
        }
        output.close();
    }

    // Udfordring: læs filen igen og læg varigheden sammen
    public static int readTotalDuration(String fileName) throws FileNotFoundException {
        Scanner input = new Scanner(new File(fileName));
        int total = 0;
        while (input.hasNextLine()) {
            String[] fields = input.nextLine().split(";");
            total += Integer.parseInt(fields[1]);
        }
        input.close();
        return total;
    }
}
```

```java
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Media> mediaList = new ArrayList<>();
        mediaList.add(new Audio("Podcast afsnit 1", 1820, -10.4));
        mediaList.add(new Video("Svømmestævne", 600, "16:9"));
        mediaList.add(new Audio("Jingle", 12, -6.0));

        try {
            MediaWriter.writeInfo(mediaList, "mediainfo.txt");
            System.out.println("Samlet varighed: " + MediaWriter.readTotalDuration("mediainfo.txt") + " sek.");
        }
        catch (FileNotFoundException e) {
            System.out.println("Kunne ikke skrive eller læse filen: " + e.getMessage());
        }
    }
}
```

`mediainfo.txt` bliver:

```text
Podcast afsnit 1;1820;loudness -10.4 dB
Svømmestævne;600;aspect ratio 16:9
Jingle;12;loudness -6.0 dB
```

Og programmet skriver `Samlet varighed: 2432 sek.`

**Eksaminatorspørgsmål**

* **Uden `instanceof`:** `Media` har en abstrakt metode, `getDetails()`, som hver subklasse
  implementerer. `getInfoLine()` kalder den uden at vide, hvilken slags medie det er.
* **Hvis filen ikke kan skrives:** `new PrintStream(...)` kaster en `FileNotFoundException`. Den er
  *checked*, så compileren tvinger os til at gøre noget. Her sender `MediaWriter` den videre med
  `throws`, og `main` fanger den og giver en besked – fordi det er `main`, der ved, hvad brugeren
  skal have at vide.
* **`new Media(...)`** giver fejlen `Media is abstract; cannot be instantiated`. Et "medie", der
  hverken er lyd eller video, findes ikke.

---

## Opgave 9 – Drømmedagbog

```java
public enum DreamType {
    PROBLEM_SOLVING,
    NEUTRAL,
    NIGHTMARE
}
```

```java
import java.time.LocalDate;

public class Dream implements Comparable<Dream> {

    private LocalDate date;
    private int durationMinutes;
    private DreamType type;

    public Dream(LocalDate date, int durationMinutes, DreamType type) {
        this.date = date;
        this.durationMinutes = durationMinutes;
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isPleasant() {
        return switch (type) {
            case NIGHTMARE -> false;
            case PROBLEM_SOLVING -> durationMinutes < 10;
            case NEUTRAL -> durationMinutes > 10;
        };
    }

    // Den naturlige rækkefølge: ældste drøm først
    @Override
    public int compareTo(Dream other) {
        return date.compareTo(other.date);
    }

    @Override
    public String toString() {
        return date + " " + type + " (" + durationMinutes + " min)";
    }
}
```

```java
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        ArrayList<Dream> dreams = new ArrayList<>();
        dreams.add(new Dream(LocalDate.of(2026, 12, 8), 12, DreamType.NEUTRAL));
        dreams.add(new Dream(LocalDate.of(2026, 12, 1), 5, DreamType.PROBLEM_SOLVING));
        dreams.add(new Dream(LocalDate.of(2026, 12, 5), 30, DreamType.NIGHTMARE));
        dreams.add(new Dream(LocalDate.of(2026, 12, 3), 10, DreamType.NEUTRAL));

        Collections.sort(dreams);
        for (Dream dream : dreams) {
            System.out.println(dream + " behagelig: " + dream.isPleasant());
        }
    }
}
```

Udskrift:

```text
2026-12-01 PROBLEM_SOLVING (5 min) behagelig: true
2026-12-03 NEUTRAL (10 min) behagelig: false
2026-12-05 NIGHTMARE (30 min) behagelig: false
2026-12-08 NEUTRAL (12 min) behagelig: true
```

**Eksaminatorspørgsmål**

* **Datatyper:** `LocalDate` til datoen – så kan den sammenlignes og sorteres. En `enum` til typen,
  fordi der kun er tre muligheder.
* **`Comparable` eller `Comparator`?** Dato er den oplagte, **naturlige** rækkefølge for en
  dagbog, så `Dream implements Comparable<Dream>`. Skulle den også kunne sorteres efter varighed,
  ville det være en `Comparator`.
* **Neutral på præcis 10 minutter:** ikke behagelig – "længere end 10" betyder `> 10`. `main`
  tester netop det tilfælde.

`switch` med pile (`->`) kan returnere en værdi direkte, og compileren brokker sig, hvis man glemmer
en af konstanterne i `enum`'en.

---

## Opgave 10 – Valg

```java
public class Candidate {

    private String name;
    private String party;
    private int numberOfVotes;

    public Candidate(String name, String party, int numberOfVotes) {
        this.name = name;
        this.party = party;
        this.numberOfVotes = numberOfVotes;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }
}
```

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

    // Udfordring: kandidaten med flest stemmer, eller null hvis der ingen er
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

```java
public class Main {

    public static void main(String[] args) {
        Election election = new Election();
        election.addCandidate(new Candidate("Anna", "Blå", 1200));
        election.addCandidate(new Candidate("Bo", "Grøn", 800));
        election.addCandidate(new Candidate("Carla", "Blå", 450));

        System.out.println(election.getTotalVotes());                          // 2450
        for (Candidate candidate : election.getCandidatesFromParty("blå")) {
            System.out.println(candidate.getName());                           // Anna, Carla
        }
        System.out.println(election.getWinner().getName());                    // Anna
    }
}
```

**Eksaminatorspørgsmål**

* **Ny liste:** `getCandidatesFromParty` laver en ny `ArrayList` og fylder den. Den, der kalder,
  kan gøre, hvad hun vil med den, uden at ændre valget.
* **`equals`, ikke `==`:** `==` sammenligner, om det er **det samme objekt**; `equals` sammenligner
  **indholdet**. To tekster med de samme bogstaver kan godt være to forskellige objekter.
  `equalsIgnoreCase` ignorerer desuden store og små bogstaver.

---

## Opgave 11 – Bundkort

```java
public class SataDrive {

    private String model;
    private int sizeGb;

    public SataDrive(String model, int sizeGb) {
        this.model = model;
        this.sizeGb = sizeGb;
    }

    @Override
    public String toString() {
        return model + " (" + sizeGb + " GB)";
    }
}
```

```java
public class MotherBoard {

    private SataDrive[] ports = new SataDrive[4];    // null = ledig port

    // Returnerer porten (0–3), drevet blev sat i, eller -1 hvis alle porte er optaget
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

    public String getDriveList() {
        String list = "";
        for (int i = 0; i < ports.length; i++) {
            if (ports[i] == null) {
                list += "Port " + i + ": ledig\n";
            }
            else {
                list += "Port " + i + ": " + ports[i] + "\n";
            }
        }
        return list;
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        MotherBoard board = new MotherBoard();
        for (int i = 1; i <= 5; i++) {
            int port = board.connect(new SataDrive("Disk" + i, 500 * i));
            if (port == -1) {
                System.out.println("Disk" + i + ": bundkortet er fyldt");
            }
            else {
                System.out.println("Disk" + i + " sat i port " + port);
            }
        }
        board.disconnect(1);
        System.out.println(board.connect(new SataDrive("Ny disk", 2000)));     // 1
        System.out.print(board.getDriveList());
    }
}
```

Udskrift:

```text
Disk1 sat i port 0
Disk2 sat i port 1
Disk3 sat i port 2
Disk4 sat i port 3
Disk5: bundkortet er fyldt
1
Port 0: Disk1 (500 GB)
Port 1: Ny disk (2000 GB)
Port 2: Disk3 (1500 GB)
Port 3: Disk4 (2000 GB)
```

**Eksaminatorspørgsmål**

* **Array eller `ArrayList`?** Et bundkort har præcis fire porte, og en port kan være **tom** midt
  i rækken. Et array med fast størrelse og `null` for "ledig" passer til det. I en `ArrayList`
  rykker elementerne sammen, når man fjerner et – så ville drevet i port 3 pludselig sidde i port 2.
* **Fyldt – hvordan siger man det?** Her returnerer `connect` portens nummer eller `-1`. Det er
  enkelt, men den, der kalder, skal huske at tjekke for `-1`. En exception kan ikke overses, men er
  tungere at bruge til noget, der er helt normalt. En udskrift i `MotherBoard` ville blande
  brugerflade ind i klassen – og kan ikke testes.
