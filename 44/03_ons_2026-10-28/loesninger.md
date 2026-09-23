# Vejledende løsninger – Kast, send videre og fang det rigtige sted

Løsningerne til [opgaverne](opgaver.md). Al kode er kørt, og testene er grønne (JDK 21, JUnit
5.14.4).

---

# Del A – Forudsig output

## Opgave 1 – Tre metoder

Med `"1975"` bliver der ikke kastet noget – alle linjer bliver kørt:

```text
main: før
outer: før
inner: før
inner: efter
outer: efter
main: efter outer
main: slut
```

Med `"nitten"` kaster `Integer.parseInt` en `NumberFormatException` allerede i `outer` – `inner`
bliver aldrig kaldt:

```text
main: før
outer: før
main: fanget – For input string: "nitten"
main: slut
```

Og ja, `catch (IllegalArgumentException e)` fanger den: `NumberFormatException` **er en**
`IllegalArgumentException`.

## Opgave 2 – finally

```text
try
finally
42
try
catch
finally
-1
```

`finally` kører, **før** metoden returnerer – derfor kommer `finally` før tallet, som `main`
udskriver.

## Opgave 3 – finally uden catch

```text
A
C
E
F
```

`risky` har ingen `catch`, så exceptionen rejser videre til `main` – men **først** kører `finally`
(`C`). `B` og `D` bliver aldrig skrevet.

## Opgave 4 – Rækkefølgen

Med `IllegalArgumentException` først kompilerer koden ikke:

```text
CatchWhich.java:13: error: exception NumberFormatException has already been caught
```

Uden `catch (NumberFormatException e)` fanger `catch (IllegalArgumentException e)` begge:

```text
sytten: For input string: "sytten"
1827: for tidligt
1975: ok
```

Programmet virker – men brugeren får Javas engelske besked i stedet for "ikke et tal".

---

# Del B og C – Bankkontoen

Her er den færdige udgave efter opgave 8, hvor `withdraw` kaster `InsufficientFundsException`:

```java
// Kastes, når der hæves flere penge, end der er på kontoen
public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String message) {
        super(message);
    }
}
```

```java
public class BankAccount {
    private String name;
    private int balance;   // i hele kroner

    public BankAccount(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Kontoen skal have et navn.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Beløbet skal være mindst 1 kr.");
        }
        balance += amount;
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Beløbet skal være mindst 1 kr.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Der er kun " + balance + " kr. på " + name + ".");
        }
        balance -= amount;
    }

    // Flytter amount fra denne konto til en anden – alt eller intet
    public void transferTo(BankAccount other, int amount) {
        if (other == null || other == this) {
            throw new IllegalArgumentException("Vælg en anden konto at overføre til.");
        }
        withdraw(amount);         // kaster, hvis beløbet er ugyldigt eller for stort
        other.deposit(amount);    // kan ikke fejle nu: beløbet er allerede tjekket
    }
}
```

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount salary;
    private BankAccount savings;

    @BeforeEach
    void setUp() {
        salary = new BankAccount("Løn");
        salary.deposit(1000);
        savings = new BankAccount("Opsparing");
    }

    // ---------- constructor ----------

    @Test
    void blankNameIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("  "));
    }

    // ---------- deposit ----------

    @Test
    void depositAddsToBalance() {
        salary.deposit(250);

        assertEquals(1250, salary.getBalance());
    }

    @Test
    void depositOfZeroIsRejectedAndChangesNothing() {
        assertThrows(IllegalArgumentException.class, () -> salary.deposit(0));

        assertEquals(1000, salary.getBalance());
    }

    // ---------- withdraw ----------

    @Test
    void withdrawEverythingIsAllowed() {
        salary.withdraw(1000);

        assertEquals(0, salary.getBalance());
    }

    @Test
    void withdrawMoreThanBalanceIsRejected() {
        InsufficientFundsException e = assertThrows(InsufficientFundsException.class,
                () -> salary.withdraw(1001));

        assertEquals("Der er kun 1000 kr. på Løn.", e.getMessage());
        assertEquals(1000, salary.getBalance());
    }

    @Test
    void negativeWithdrawIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> salary.withdraw(-50));
    }

    // ---------- transferTo ----------

    @Test
    void transferMovesMoney() {
        salary.transferTo(savings, 300);

        assertEquals(700, salary.getBalance());
        assertEquals(300, savings.getBalance());
    }

    @Test
    void transferTooMuchChangesNothing() {
        assertThrows(InsufficientFundsException.class, () -> salary.transferTo(savings, 5000));

        assertEquals(1000, salary.getBalance());
        assertEquals(0, savings.getBalance());
    }

    @Test
    void transferToNullChangesNothing() {
        assertThrows(IllegalArgumentException.class, () -> salary.transferTo(null, 300));

        assertEquals(1000, salary.getBalance());
    }

    @Test
    void transferToSameAccountIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> salary.transferTo(salary, 300));

        assertEquals(1000, salary.getBalance());
    }
}
```

## Opgave 6 – Overførslen

Med kollegaens `transferTo`:

* Test 1 (almindelig overførsel) og test 2 (for meget) er **grønne** – `withdraw` kaster, **før**
  noget ændres.
* Test 3 (til `null`) er **rød**: `withdraw` trækker 300 kr. fra `Løn`, og **derefter** går
  `other.deposit(...)` ned med en `NullPointerException`. De 300 kr. er **forsvundet** – trukket fra
  den ene konto og aldrig sat ind på den anden:

  ```text
  Unexpected exception type thrown, expected: <java.lang.IllegalArgumentException> but was: <java.lang.NullPointerException>
  ```

* Test 4 (til samme konto) er **rød**: pengene hæves og sættes ind igen på samme konto, og der
  bliver ikke kastet noget:

  ```text
  Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
  ```

Rettelsen er at tjekke **alt**, før noget ændres:

```java
    // Flytter amount fra denne konto til en anden – alt eller intet
    public void transferTo(BankAccount other, int amount) {
        if (other == null || other == this) {
            throw new IllegalArgumentException("Vælg en anden konto at overføre til.");
        }
        withdraw(amount);         // kaster, hvis beløbet er ugyldigt eller for stort
        other.deposit(amount);    // kan ikke fejle nu: beløbet er allerede tjekket
    }
```

Efter `withdraw` er beløbet tjekket, så `deposit` kan ikke fejle – overførslen er alt eller intet.

## Opgave 7 – Test beskeden

Se `withdrawMoreThanBalanceIsRejected` ovenfor.

## Opgave 8 – InsufficientFundsException

* Kun de tests, der forventede en `IllegalArgumentException` for **for stor** en hævning eller
  overførsel, skal rettes til `InsufficientFundsException`. Testene af beløb på 0 og negative beløb
  forventer stadig `IllegalArgumentException` – den regel er ikke ændret.
* En `catch (IllegalArgumentException e)` fanger **ikke** længere "ikke penge nok":
  `InsufficientFundsException` arver fra `RuntimeException`, ikke fra `IllegalArgumentException`.
  Den skal have sin egen `catch`.

## Opgave 9 – Bankmenuen

```java
import java.util.Scanner;

public class BankApp {
    private Scanner scanner = new Scanner(System.in);
    private BankAccount salary = new BankAccount("Løn");
    private BankAccount savings = new BankAccount("Opsparing");

    public void start() {
        boolean running = true;
        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> deposit();
                    case "2" -> withdraw();
                    case "3" -> transfer();
                    case "0" -> running = false;
                    default -> System.out.println("Ukendt valg: " + choice);
                }
            } catch (InsufficientFundsException e) {
                System.out.println("Ikke dækning: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Det gik ikke: " + e.getMessage());
            }
        }
        System.out.println("Farvel!");
    }

    private void showMenu() {
        System.out.println();
        System.out.println("Løn: " + salary.getBalance() + " kr. | Opsparing: " + savings.getBalance() + " kr.");
        System.out.println("1. Indsæt på Løn");
        System.out.println("2. Hæv fra Løn");
        System.out.println("3. Overfør fra Løn til Opsparing");
        System.out.println("0. Afslut");
        System.out.print("Vælg: ");
    }

    private void deposit() {
        int amount = readInt("Beløb: ");
        salary.deposit(amount);
        System.out.println(amount + " kr. er indsat.");
    }

    private void withdraw() {
        int amount = readInt("Beløb: ");
        salary.withdraw(amount);
        System.out.println(amount + " kr. er hævet.");
    }

    private void transfer() {
        int amount = readInt("Beløb: ");
        salary.transferTo(savings, amount);
        System.out.println(amount + " kr. er overført.");
    }

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

    public static void main(String[] args) {
        new BankApp().start();
    }
}
```

De to `catch`-blokke står rundt om hele `switch`'en, så de gælder for alle tre handlinger. Rækkefølgen
er ligegyldig her, fordi de to typer ikke arver fra hinanden.

---

# Udfordringer

## Udfordring 1 – Checked

Med `extends Exception` skal der rettes **seks** steder, før alt kompilerer:

1. `withdraw` – `throws InsufficientFundsException`
2. `transferTo` – den kalder `withdraw`, så den skal også have `throws`
3. og 4. `BankApp.withdraw()` og `BankApp.transfer()` – de kalder `withdraw` og `transferTo`
5. og 6. de to tests, der kalder `withdraw` og `transferTo` **direkte** (ikke inde i
   `assertThrows`) – fx `void withdrawEverythingIsAllowed() throws InsufficientFundsException`

Fordelen: ingen kan glemme, at en hævning kan blive afvist. Ulempen: `throws` spreder sig til alle
metoder på vejen, også dem, der ikke kan gøre noget ved det. For en regel som "ikke penge nok" vælger
de fleste unchecked. For "filen findes ikke" (fredag) har Java valgt checked.

## Udfordring 2 – Dato, der ikke findes

```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Strict {
    public static void main(String[] args) {
        DateTimeFormatter smart = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter strict = DateTimeFormatter.ofPattern("dd-MM-uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        DateTimeFormatter strictWrong = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                .withResolverStyle(ResolverStyle.STRICT);

        System.out.println(LocalDate.parse("31-02-2026", smart));
        System.out.println(LocalDate.parse("28-02-2026", strict));
        try {
            LocalDate.parse("31-02-2026", strict);
        } catch (DateTimeParseException e) {
            System.out.println("strict: " + e.getMessage());
        }
        try {
            LocalDate.parse("28-02-2026", strictWrong);
        } catch (DateTimeParseException e) {
            System.out.println("strict med yyyy: " + e.getMessage());
        }
    }
}
```

```text
2026-02-28
2026-02-28
strict: Text '31-02-2026' could not be parsed: Invalid date 'FEBRUARY 31'
strict med yyyy: Text '28-02-2026' could not be parsed: Unable to obtain LocalDate from TemporalAccessor: ...
```

Standarden (`SMART`) retter 31-02 til den sidste dag i februar. `STRICT` afviser den. Men med `STRICT`
skal året skrives `uuuu`: `yyyy` betyder "år i den nuværende tidsregning" (*year of era*), og uden en
tidsregning (e.Kr./f.Kr.) i teksten kan `STRICT` ikke lave det om til en dato – så **alle** datoer
bliver afvist.

## Udfordring 3 – Kvittering med finally

```java
    try {
        switch (choice) {
            case "1" -> deposit();
            case "2" -> withdraw();
            case "3" -> transfer();
            case "0" -> running = false;
            default -> System.out.println("Ukendt valg: " + choice);
        }
    } catch (InsufficientFundsException e) {
        System.out.println("Ikke dækning: " + e.getMessage());
    } catch (IllegalArgumentException e) {
        System.out.println("Det gik ikke: " + e.getMessage());
    } finally {
        if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
            attempts++;
        }
    }
}
System.out.println("Du forsøgte " + attempts + " handlinger. Farvel!");
```

`finally` kører, uanset om handlingen lykkedes, blev afvist med en exception eller var et ukendt valg
– derfor tjekker den selv, om valget var en handling.
