# Vejledende løsninger – prøveopgaver

Vejledende løsninger til [prøveopgaverne](opgaver.md). Al kode er kørt med Java 21. Løsningerne
viser den **færdige** udgave efter delspørgsmål 3.

> Din løsning må gerne se anderledes ud. Til eksamen er det ikke afgørende, om din kode ligner
> denne – men om den virker, og om du kan **forklare og begrunde** den.

---

## Opgave 1 – Termometer

```java
import java.util.ArrayList;

public class Thermometer {

    private ArrayList<Double> readings = new ArrayList<>();

    public void addReading(double temperature) {
        readings.add(temperature);
    }

    // Gennemsnit – 0, hvis der ingen målinger er (undgår division med 0)
    public double getAverage() {
        if (readings.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (double reading : readings) {
            sum += reading;
        }
        return sum / readings.size();
    }

    public double getMax() {
        double max = readings.get(0);            // kaster en exception, hvis listen er tom
        for (double reading : readings) {
            if (reading > max) {
                max = reading;
            }
        }
        return max;
    }

    public int countFrostReadings() {
        int count = 0;
        for (double reading : readings) {
            if (reading < 0) {
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
        Thermometer thermometer = new Thermometer();
        thermometer.addReading(2.5);
        thermometer.addReading(-1.0);
        thermometer.addReading(4.0);
        thermometer.addReading(-3.5);

        System.out.println(thermometer.getAverage());         // 0.5
        System.out.println(thermometer.getMax());             // 4.0
        System.out.println(thermometer.countFrostReadings()); // 2
    }
}
```

**Eksaminatorspørgsmål**

* **Datatype:** `double` – en temperatur har decimaler. Listen er `ArrayList<Double>`, fordi en
  `ArrayList` kun kan indeholde objekter; Java laver selv `double` om til `Double` og tilbage.
* **Ingen målinger:** `getAverage` returnerer 0 for at undgå division med 0 – det kan diskuteres, om
  0 er et godt svar. `getMax` kaster en exception (`get(0)` på en tom liste). Begge dele er i orden,
  hvis du **ved** det og kan sige, hvorfor.
* **Start med den første måling:** starter man med 0, bliver svaret forkert, når alle målinger er
  under frysepunktet – så er den højeste fx −1, ikke 0.

---

## Opgave 2 – Parkeringsplads

```java
public class Car {

    private String licensePlate;

    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}
```

```java
public class ParkingLot {

    private Car[] spots;

    public ParkingLot(int numberOfSpots) {
        spots = new Car[numberOfSpots];
    }

    // Returnerer pladsens nummer, eller -1 hvis der ikke er plads
    public int park(Car car) {
        for (int i = 0; i < spots.length; i++) {
            if (spots[i] == null) {
                spots[i] = car;
                return i;
            }
        }
        return -1;
    }

    // Returnerer true, hvis bilen holdt der og nu er kørt
    public boolean leave(String licensePlate) {
        for (int i = 0; i < spots.length; i++) {
            if (spots[i] != null && spots[i].getLicensePlate().equals(licensePlate)) {
                spots[i] = null;
                return true;
            }
        }
        return false;
    }

    public int countFreeSpots() {
        int free = 0;
        for (Car car : spots) {
            if (car == null) {
                free++;
            }
        }
        return free;
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(3);
        System.out.println(lot.park(new Car("AB12345")));   // 0
        System.out.println(lot.park(new Car("CD67890")));   // 1
        System.out.println(lot.park(new Car("EF11111")));   // 2
        System.out.println(lot.park(new Car("GH22222")));   // -1
        System.out.println(lot.leave("CD67890"));           // true
        System.out.println(lot.leave("XX00000"));           // false
        System.out.println(lot.countFreeSpots());           // 1
        System.out.println(lot.park(new Car("GH22222")));   // 1
    }
}
```

**Eksaminatorspørgsmål**

* **Array:** pladserne har faste numre. Kører bilen på plads 1, skal plads 2 stadig være plads 2.
  I en `ArrayList` ville resten rykke sammen, når man fjerner et element.
* **`equals`:** nummerpladerne er to `String`-objekter. `==` tester, om det er det samme objekt;
  `equals` tester, om teksten er ens.
* **Findes ikke:** `leave` returnerer `false`. Tjekket `spots[i] != null` er nødvendigt – ellers
  kaster `getLicensePlate()` en `NullPointerException` på den første tomme plads.

---

## Opgave 3 – Biblioteket

```java
import java.time.LocalDate;

public class Loan {

    private static final int LOAN_DAYS = 30;

    private String bookTitle;
    private LocalDate borrowedDate;

    public Loan(String bookTitle, LocalDate borrowedDate) {
        this.bookTitle = bookTitle;
        this.borrowedDate = borrowedDate;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public LocalDate getDueDate() {
        return borrowedDate.plusDays(LOAN_DAYS);
    }

    // For sent: afleveringsdatoen er passeret
    public boolean isOverdue(LocalDate today) {
        return today.isAfter(getDueDate());
    }
}
```

```java
import java.util.Comparator;

public class BorrowedDateComparator implements Comparator<Loan> {

    @Override
    public int compare(Loan loan1, Loan loan2) {
        return loan1.getBorrowedDate().compareTo(loan2.getBorrowedDate());
    }
}
```

```java
import java.time.LocalDate;
import java.util.ArrayList;

public class Library {

    private ArrayList<Loan> loans = new ArrayList<>();

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    // De lån, der er for sent afleveret – ældste først
    public ArrayList<Loan> getOverdueLoans(LocalDate today) {
        ArrayList<Loan> overdue = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.isOverdue(today)) {
                overdue.add(loan);
            }
        }
        overdue.sort(new BorrowedDateComparator());
        return overdue;
    }
}
```

```java
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        LocalDate today = LocalDate.of(2026, 12, 15);

        Library library = new Library();
        library.addLoan(new Loan("Java for begyndere", LocalDate.of(2026, 11, 1)));
        library.addLoan(new Loan("Svømning for alle", LocalDate.of(2026, 12, 1)));
        library.addLoan(new Loan("Clean Code", LocalDate.of(2026, 10, 20)));
        library.addLoan(new Loan("Grænsetilfælde", LocalDate.of(2026, 11, 15)));   // skal afleveres i dag

        for (Loan loan : library.getOverdueLoans(today)) {
            System.out.println(loan.getBookTitle() + ", lånt " + loan.getBorrowedDate()
                    + ", skulle afleveres " + loan.getDueDate());
        }
    }
}
```

Udskrift:

```text
Clean Code, lånt 2026-10-20, skulle afleveres 2026-11-19
Java for begyndere, lånt 2026-11-01, skulle afleveres 2026-12-01
```

**Eksaminatorspørgsmål**

* **Datoen som parameter:** så kan man afprøve metoden på en bestemt dag – i `main` og i en unit
  test – og svaret ændrer sig ikke, fordi tiden går.
* **Afleveres i dag:** ikke for sent. `isAfter` er kun sand, når dagen er **efter**
  afleveringsdatoen. "Grænsetilfælde" skal afleveres 15-12, og `main` viser, at den ikke er med.
* **Sortering:** med en `Comparator`, fordi "ældste først" er én bestemt måde at se lånene på. Man
  kunne også lade `Loan implements Comparable<Loan>` – det er fint, hvis man kan begrunde, at dato er
  lånets naturlige rækkefølge.

---

## Opgave 4 – Løn

```java
public abstract class Employee {

    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateMonthlyPay();
}
```

```java
public class SalariedEmployee extends Employee {

    private double monthlySalary;

    public SalariedEmployee(String name, double monthlySalary) {
        super(name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateMonthlyPay() {
        return monthlySalary;
    }
}
```

```java
public class HourlyEmployee extends Employee {

    private static final int NORMAL_HOURS = 160;
    private static final double OVERTIME_FACTOR = 1.5;

    private double hourlyWage;
    private int hours;

    public HourlyEmployee(String name, double hourlyWage, int hours) {
        super(name);
        this.hourlyWage = hourlyWage;
        this.hours = hours;
    }

    @Override
    public double calculateMonthlyPay() {
        if (hours <= NORMAL_HOURS) {
            return hours * hourlyWage;
        }
        int overtime = hours - NORMAL_HOURS;
        return NORMAL_HOURS * hourlyWage + overtime * hourlyWage * OVERTIME_FACTOR;
    }
}
```

```java
import java.util.ArrayList;

public class Payroll {

    private ArrayList<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public double calculateTotal() {
        double total = 0;
        for (Employee employee : employees) {
            total += employee.calculateMonthlyPay();     // polymorfi: hver ved selv hvordan
        }
        return total;
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        Payroll payroll = new Payroll();
        payroll.addEmployee(new SalariedEmployee("Anna", 32000));
        payroll.addEmployee(new HourlyEmployee("Bo", 200, 100));     // 20000
        payroll.addEmployee(new HourlyEmployee("Carl", 200, 170));   // 32000 + 10 * 300 = 35000

        System.out.println(payroll.calculateTotal());                // 87000.0
    }
}
```

**Eksaminatorspørgsmål**

* **Abstrakt:** en medarbejder er altid enten fastlønnet eller timelønnet – "bare en medarbejder"
  findes ikke. Var klassen ikke abstrakt, kunne man ikke have en metode uden krop, og man ville
  kunne skrive `new Employee(...)`.
* **Hvordan ved `Payroll` det?** Det gør den ikke. Den kalder `calculateMonthlyPay()`, og hver
  subklasse har sin egen udgave – polymorfi. Kommer der en tredje slags medarbejder, skal `Payroll`
  ikke ændres.
* **160 og 1,5** står som konstanter i `HourlyEmployee`, fordi det kun er timelønnede, de gælder
  for. Så står hvert tal ét sted, med et navn, der forklarer det.

Carl arbejder 170 timer: 160 × 200 + 10 × 200 × 1,5 = 32.000 + 3.000 = 35.000. I alt:
32.000 + 20.000 + 35.000 = **87.000**.

---

## Opgave 5 – Adgangskode

```java
public class Password {

    private String text;

    public Password(String text) {
        String problems = findProblems(text);
        if (!problems.isEmpty()) {
            throw new IllegalArgumentException("Ugyldig adgangskode:" + problems);
        }
        this.text = text;
    }

    public static boolean isValid(String text) {
        return findProblems(text).isEmpty();
    }

    // Returnerer en tekst med alt, der er galt – eller "" hvis intet er galt
    private static String findProblems(String text) {
        String problems = "";
        if (text.length() < 8) {
            problems += " Mindst 8 tegn.";
        }

        boolean hasDigit = false;
        boolean hasUpperCase = false;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            }
        }
        if (!hasDigit) {
            problems += " Mindst ét ciffer.";
        }
        if (!hasUpperCase) {
            problems += " Mindst ét stort bogstav.";
        }
        return problems;
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        System.out.println(Password.isValid("Delfin2026"));   // true
        System.out.println(Password.isValid("delfin2026"));   // false
        System.out.println(Password.isValid("Kort1"));        // false

        String[] attempts = {"kort", "Delfin2026"};
        for (String attempt : attempts) {
            try {
                new Password(attempt);
                System.out.println(attempt + " er godkendt");
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
```

Udskrift:

```text
true
false
false
Ugyldig adgangskode: Mindst 8 tegn. Mindst ét ciffer. Mindst ét stort bogstav.
Delfin2026 er godkendt
```

**Eksaminatorspørgsmål**

* **Gennem tegnene:** en `for`-løkke fra 0 til `text.length() - 1` og `text.charAt(i)`.
  `Character.isDigit(c)` og `Character.isUpperCase(c)` tjekker tegnet.
* **Exception:** `IllegalArgumentException` – den findes i forvejen og betyder præcis *"du har
  givet mig en ugyldig værdi"*. Den er unchecked, så den, der kalder, ikke er tvunget til at fange
  den.
* **Hvorfor ikke udskrive?** En klasse, der udskriver, kan kun bruges i en konsol – og den, der
  kalder, får ikke at vide, at noget gik galt. Med en exception bestemmer den, der kalder, hvad der
  skal ske – og objektet bliver aldrig oprettet med en ugyldig værdi.

`findProblems` samler reglerne ét sted, så `isValid` og konstruktøren ikke hver har deres kopi af
dem.

---

## Opgave 6 – Playliste

```java
public class Song implements Comparable<Song> {

    private String title;
    private String artist;
    private int durationSeconds;

    public Song(String title, String artist, int durationSeconds) {
        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    // Naturlig rækkefølge: alfabetisk efter titel
    @Override
    public int compareTo(Song other) {
        return title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        return title + " – " + artist + " (" + Playlist.formatDuration(durationSeconds) + ")";
    }
}
```

```java
import java.util.Comparator;

public class DurationComparator implements Comparator<Song> {

    @Override
    public int compare(Song song1, Song song2) {
        return Integer.compare(song1.getDurationSeconds(), song2.getDurationSeconds());
    }
}
```

```java
import java.util.ArrayList;
import java.util.Collections;

public class Playlist {

    private ArrayList<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        songs.add(song);
    }

    public int getTotalDurationSeconds() {
        int total = 0;
        for (Song song : songs) {
            total += song.getDurationSeconds();
        }
        return total;
    }

    // 754 -> "12:34"
    public static String formatDuration(int seconds) {
        return String.format("%d:%02d", seconds / 60, seconds % 60);
    }

    public ArrayList<Song> getSongsBy(String artist) {
        ArrayList<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getArtist().equalsIgnoreCase(artist)) {
                result.add(song);
            }
        }
        return result;
    }

    public ArrayList<Song> getSortedByTitle() {
        ArrayList<Song> sorted = new ArrayList<>(songs);
        Collections.sort(sorted);
        return sorted;
    }

    public ArrayList<Song> getSortedByDuration() {
        ArrayList<Song> sorted = new ArrayList<>(songs);
        sorted.sort(new DurationComparator());
        return sorted;
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.addSong(new Song("Vandmand", "Bølgerne", 215));
        playlist.addSong(new Song("Crawl", "Bassinet", 187));
        playlist.addSong(new Song("Butterfly", "Bølgerne", 352));

        System.out.println(Playlist.formatDuration(playlist.getTotalDurationSeconds()));   // 12:34
        System.out.println(playlist.getSongsBy("bølgerne"));
        System.out.println(playlist.getSortedByTitle());
        System.out.println(playlist.getSortedByDuration());
    }
}
```

Udskrift:

```text
12:34
[Vandmand – Bølgerne (3:35), Butterfly – Bølgerne (5:52)]
[Butterfly – Bølgerne (5:52), Crawl – Bassinet (3:07), Vandmand – Bølgerne (3:35)]
[Crawl – Bassinet (3:07), Vandmand – Bølgerne (3:35), Butterfly – Bølgerne (5:52)]
```

**Eksaminatorspørgsmål**

* **`12:04`:** `%02d` i `String.format` skriver et heltal med mindst to cifre og fylder op med 0.
* **Naturlig rækkefølge:** titel – `Song implements Comparable<Song>`. Varighed er en anden
  rækkefølge, så den er en `Comparator`.
* **Selve playlisten:** nej – begge metoder sorterer en **kopi** (`new ArrayList<>(songs)`). En
  playliste har en rækkefølge, som brugeren har valgt, og den skal ikke forsvinde, fordi nogen vil
  se sangene sorteret.

`toString` i `Song` bruger `Playlist.formatDuration`, som er `static`, fordi den ikke bruger nogen
attributter – den regner kun på det tal, den får.
