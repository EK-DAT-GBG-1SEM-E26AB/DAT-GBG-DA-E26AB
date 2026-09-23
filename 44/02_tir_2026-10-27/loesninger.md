# Vejledende løsninger – Exceptions

Løsningerne til [opgaverne](opgaver.md). Al kode er kørt, og testene er grønne (JDK 21, JUnit
5.14.4).

---

# Del A – Forudsig output

## Opgave 1 – Opvarmning

Med `"1975"` bliver der ikke kastet noget, og `catch` springes over:

```text
A
B: 1975
D
```

Med `"19 75"` kaster `parseInt` – et mellemrum midt i er ikke et tal:

```text
A
C: For input string: "19 75"
D
```

## Opgave 2 – try inde i løkken

```text
Lagt til: 12
Sprunget over: tolv
Lagt til: 7
Sum: 19
```

`try` står **inde i** løkken, så en fejl springer kun den ene tekst over. Løkken fortsætter.

## Opgave 3 – løkken inde i try

```text
Lagt til: 12
Stoppet: For input string: "tolv"
Sum: 12
```

Nu står løkken **inde i** `try`. Når `"tolv"` kaster, springer Java til `catch` – ud af løkken – og
`"7"` bliver aldrig læst.

Opgave 2 er rigtig, når hver tekst er uafhængig af de andre (fx linjer i en fil: spring den ødelagte
over, læs resten). Opgave 3 er rigtig, når det hele er forkert, hvis én del er forkert – så skal man
stoppe.

## Opgave 4 – Den forkerte catch

`Færdig` bliver **ikke** skrevet. Programmet går ned:

```text
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 2 out of bounds for length 2
	at Predict4.main(Predict4.java:6)
```

`inputs[2]` findes ikke – arrayet har kun index 0 og 1. Det er en `ArrayIndexOutOfBoundsException`,
og `catch` fanger kun `NumberFormatException`. Så flyver den forbi, som om der ingen `try` var.

Rettelsen er **ikke** at fange `ArrayIndexOutOfBoundsException`. Det er en fejl i koden: man skal
tjekke `inputs.length`, før man bruger et index – eller bare bruge det rigtige index.

## Opgave 5 – Hvad er et tal?

```text
[42] -> 42
[ 42] -> For input string: " 42"
[+42] -> 42
[4.2] -> For input string: "4.2"
[] -> For input string: ""
[99999999999] -> For input string: "99999999999"
```

* `" 42"`: `parseInt` accepterer **ikke** mellemrum. Derfor `trim()` – brugeren kommer tit til at
  taste et mellemrum.
* `"+42"` er i orden.
* `"4.2"` er ikke et **helt** tal.
* `""`: brugeren trykkede bare Enter.
* `"99999999999"` er for stort til en `int` (højst 2.147.483.647).

---

# Del B – Læs en stack trace

## Opgave 6 – Billetkontoret

1. `ArrayIndexOutOfBoundsException` med beskeden `Index -1 out of bounds for length 2`.
2. I metoden `getPrice` i `PriceList.java`, linje 7: `return Integer.parseInt(prices[index]);`.
3. Nedefra: `Main.main` (linje 6) kaldte `TicketOffice.sell`, som (linje 5) kaldte
   `PriceList.getPrice`. Den øverste linje er dér, det skete; de næste er vejen dertil.
4. `findIndex("senior")` finder ikke `"senior"` i `types` og returnerer `-1`. `prices[-1]` findes
   ikke.
5. **Ikke** med en `try`/`catch` om `prices[index]`. Fejlen er, at `getPrice` bruger `-1`, som om
   det var et index. Enten skal `"senior"` med i listerne, eller `getPrice` skal **tjekke**
   `index == -1` og gøre noget fornuftigt – fx kaste en `IllegalArgumentException("Ukendt
   billettype: senior")`, som fortæller præcis, hvad der er galt.

Læg også mærke til, at `Jaws` og `Psycho` **blev** solgt, før programmet gik ned. En exception
stopper programmet dér, hvor den sker – det, der allerede er sket, er sket.

## Opgave 7 – Jeres egne exceptions

Eksempler på beskeder (JDK 21):

```text
java.lang.IndexOutOfBoundsException: Index 1 out of bounds for length 1
java.lang.NullPointerException: Cannot invoke "String.length()" because "title" is null
java.lang.ArithmeticException: / by zero
java.time.format.DateTimeParseException: Text 'i går' could not be parsed at index 0
```

`NullPointerException` er den mest hjælpsomme: den siger både, hvilken metode der blev kaldt, og
hvilken variabel der var `null`.

---

# Del C – Billetautomaten

## Opgave 8 – readInt

```java
    // Spørger igen, indtil brugeren skriver et helt tal
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\" er ikke et helt tal. Prøv igen.");
            }
        }
    }
```

## Opgave 9 – readIntInRange

```java
    // Spørger igen, indtil brugeren skriver et helt tal mellem min og max (begge med)
    private int readIntInRange(String prompt, int min, int max) {
        int number = readInt(prompt);
        while (number < min || number > max) {
            System.out.println("Skriv et tal mellem " + min + " og " + max + ".");
            number = readInt(prompt);
        }
        return number;
    }
```

Ingen `try`/`catch` her: `readInt` sørger allerede for, at det er et tal. At tallet er uden for
intervallet, **kan** man se på forhånd – så det er en `if` (her en `while`). *Tjek, hvis du kan.*

## Opgave 10 – readYesNo

```java
    // Spørger igen, indtil brugeren svarer ja, j, nej eller n
    private boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("ja") || input.equals("j")) {
                return true;
            }
            if (input.equals("nej") || input.equals("n")) {
                return false;
            }
            System.out.println("Svar ja eller nej.");
        }
    }
```

Heller ingen `try`/`catch`: der er ingen metode, der kan kaste. Man sammenligner bare tekster.

## Opgave 11 – Automaten (og opgave 13)

Hele klassen, inklusive fangsten fra opgave 13:

```java
import java.util.Scanner;

public class TicketMachine {
    private Scanner scanner = new Scanner(System.in);
    private Cinema cinema = new Cinema();

    public void start() {
        System.out.println("Velkommen til billetautomaten!");
        int total = 0;
        boolean more = true;
        while (more) {
            int age = readInt("Alder: ");
            try {
                int price = cinema.getTicketPrice(age);
                int count = readIntInRange("Antal billetter (1-10): ", 1, 10);
                total += price * count;
                System.out.println(count + " billet(ter) à " + price + " kr.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            more = readYesNo("Flere billetter? (ja/nej): ");
        }
        System.out.println("I alt: " + total + " kr. Tak for i dag!");
    }

    // Spørger igen, indtil brugeren skriver et helt tal
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\" er ikke et helt tal. Prøv igen.");
            }
        }
    }

    // Spørger igen, indtil brugeren skriver et helt tal mellem min og max (begge med)
    private int readIntInRange(String prompt, int min, int max) {
        int number = readInt(prompt);
        while (number < min || number > max) {
            System.out.println("Skriv et tal mellem " + min + " og " + max + ".");
            number = readInt(prompt);
        }
        return number;
    }

    // Spørger igen, indtil brugeren svarer ja, j, nej eller n
    private boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("ja") || input.equals("j")) {
                return true;
            }
            if (input.equals("nej") || input.equals("n")) {
                return false;
            }
            System.out.println("Svar ja eller nej.");
        }
    }

    public static void main(String[] args) {
        new TicketMachine().start();
    }
}
```

`try` omslutter både prisen og antallet, fordi antallet kun skal spørges om, hvis alderen var
gyldig. Når `getTicketPrice` kaster, springes resten af `try` over, og automaten spørger om flere
billetter.

---

# Del D – Kast selv

## Opgave 12 – Klippekortet siger fra

```java
// Et klippekort til kaffe – nu med regler for, hvad der er gyldigt
public class CoffeeCard {
    private int clipsLeft;

    public CoffeeCard(int clips) {
        if (clips < 0) {
            throw new IllegalArgumentException("Et klippekort kan ikke have et negativt antal klip.");
        }
        clipsLeft = clips;
    }

    public int getClipsLeft() {
        return clipsLeft;
    }

    public boolean useClip() {
        if (clipsLeft == 0) {
            return false;
        }
        clipsLeft--;
        return true;
    }

    public void addClips(int clips) {
        if (clips <= 0) {
            throw new IllegalArgumentException("Der skal tilføjes mindst ét klip.");
        }
        clipsLeft += clips;
    }

    public boolean isEmpty() {
        return clipsLeft == 0;
    }
}
```

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeCardTest {

    private CoffeeCard card;

    @BeforeEach
    void setUp() {
        card = new CoffeeCard(10);
    }

    @Test
    void addingZeroClipsIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> card.addClips(0));
    }

    @Test
    void rejectedAddChangesNothing() {
        assertThrows(IllegalArgumentException.class, () -> card.addClips(-5));

        assertEquals(10, card.getClipsLeft());
    }

    @Test
    void addingOneClipIsAllowed() {
        card.addClips(1);

        assertEquals(11, card.getClipsLeft());
    }

    @Test
    void negativeClipsInConstructorIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new CoffeeCard(-1));
    }

    @Test
    void cardWithZeroClipsIsAllowed() {
        CoffeeCard emptyCard = new CoffeeCard(0);

        assertTrue(emptyCard.isEmpty());
    }
}
```

Grænserne: `new CoffeeCard(0)` **må** man (et tomt kort), `new CoffeeCard(-1)` må man ikke.
`addClips(1)` må man, `addClips(0)` må man ikke.

## Opgave 13 – Ingen negative aldre

```java
public class Cinema {
    private static final int CHILD_PRICE = 60;
    private static final int ADULT_PRICE = 110;
    private static final int SENIOR_PRICE = 80;
    private static final int MAX_AGE = 130;

    // Børn under 12 år: 60 kr. Fra 12 til og med 64 år: 110 kr. Fra 65 år: 80 kr.
    public int getTicketPrice(int age) {
        if (age < 0 || age > MAX_AGE) {
            throw new IllegalArgumentException("Alderen skal være mellem 0 og " + MAX_AGE + ".");
        }
        if (age < 12) {
            return CHILD_PRICE;
        }
        if (age < 65) {
            return ADULT_PRICE;
        }
        return SENIOR_PRICE;
    }
}
```

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CinemaTest {

    private Cinema cinema;

    @BeforeEach
    void setUp() {
        cinema = new Cinema();
    }

    @Test
    void elevenIsStillAChild() {
        assertEquals(60, cinema.getTicketPrice(11));
    }

    @Test
    void twelveIsAnAdult() {
        assertEquals(110, cinema.getTicketPrice(12));
    }

    @Test
    void sixtyFiveIsASenior() {
        assertEquals(80, cinema.getTicketPrice(65));
    }

    @Test
    void zeroIsAllowed() {
        assertEquals(60, cinema.getTicketPrice(0));
    }

    @Test
    void negativeAgeIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> cinema.getTicketPrice(-1));
    }

    @Test
    void maxAgeIsAllowed() {
        assertEquals(80, cinema.getTicketPrice(130));
    }

    @Test
    void ageAboveMaxIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> cinema.getTicketPrice(131));
    }
}
```

Reglen skal stå i `Cinema`, fordi det er `Cinema`, der ved, hvad en gyldig alder er (*Information
Expert*). Står den kun i automaten, gælder den kun, når brugeren taster – ikke når en anden klasse,
en test eller en fil kalder `getTicketPrice`. Automaten kunne **også** bruge
`readIntInRange("Alder: ", 0, 130)` for at spørge igen med det samme – men så står reglen to steder.
Det er en afvejning; i filmsamlingen står reglerne i `Movie`.

---

# Udfordringer

## Udfordring 1 – nextInt

Uden `scanner.nextLine()` i `catch` bliver `abc` liggende i `Scanner`, og `nextInt()` prøver den
samme tekst igen – programmet skriver fejlbeskeden igen og igen i en uendelig løkke. Rettet:

```java
import java.util.InputMismatchException;
import java.util.Scanner;

public class NextIntReader {
    private Scanner scanner = new Scanner(System.in);

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int number = scanner.nextInt();
                scanner.nextLine();   // læs resten af linjen (linjeskiftet) væk
                return number;
            } catch (InputMismatchException e) {
                String wrong = scanner.nextLine();   // læs den forkerte linje væk
                System.out.println("\"" + wrong + "\" er ikke et helt tal. Prøv igen.");
            }
        }
    }

    public static void main(String[] args) {
        NextIntReader reader = new NextIntReader();
        int number = reader.readInt("Tal: ");
        System.out.println("Du skrev " + number);
    }
}
```

Læg mærke til, at der **også** skal læses en linje væk, når det lykkes: `nextInt()` læser kun
tallet, ikke linjeskiftet efter det.

## Udfordring 2 – Komma eller punktum

```java
import java.util.Scanner;

public class DoubleReader {
    private Scanner scanner = new Scanner(System.in);

    // Accepterer både komma og punktum som decimaltegn
    public double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\" er ikke et tal. Prøv igen.");
            }
        }
    }

    public static void main(String[] args) {
        DoubleReader reader = new DoubleReader();
        double rating = reader.readDouble("Karakter (fx 3,5): ");
        System.out.println("Du gav " + rating);
    }
}
```

```text
Karakter (fx 3,5): abc
"abc" er ikke et tal. Prøv igen.
Karakter (fx 3,5): 3,5
Du gav 3.5
```

## Udfordring 3 – Hvor mange catch?

Lukker brugeren for input (**Send EOF**, <kbd>Ctrl</kbd>+<kbd>D</kbd> i IntelliJ's konsol), er der
ikke flere linjer at læse, og `nextLine()` kaster:

```text
java.util.NoSuchElementException: No line found
```

Det er et hjørne, de fleste programmer lader være – brugeren har selv lukket for input. Ellers er der
ingen andre steder: al indlæsning går gennem `readInt` og `readYesNo`, og den eneste metode, der
kaster med vilje, er `getTicketPrice`.
