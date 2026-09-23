# Opgaver – Exceptions

Dagens opgaver er delt op sådan:

* **Del A** – forudsig output. Hvilke linjer bliver kørt, når der kastes?
* **Del B** – læs en stack trace.
* **Del C** – en billetautomat, der ikke kan væltes.
* **Del D** – kast selv, og test det med `assertThrows`.
* **Udfordringer**.

Lav del A og B i et almindeligt Java-projekt (eller jeres `junit-oevelse`-projekt), del C og D i
`junit-oevelse`, hvor I har JUnit – og hvor `CoffeeCard` og `Cinema` allerede ligger fra sidste uge.

Der er [vejledende løsninger](loesninger.md) – men prøv selv først.

---

# Del A – Forudsig output

> **Skriv dit svar ned, før du kører koden.** Det er hele øvelsen.

## Opgave 1 – Opvarmning

Kør `TryCatchFlow` fra [læsestoffet](README.md#fang-den-try-og-catch) – men ret først `input` til
`"1975"`. Hvad bliver skrevet ud? Og med `input = "19 75"`?

## Opgave 2 – try inde i løkken

```java
public class Predict2 {
    public static void main(String[] args) {
        String[] inputs = {"12", "tolv", "7"};
        int sum = 0;
        for (String input : inputs) {
            try {
                sum += Integer.parseInt(input);
                System.out.println("Lagt til: " + input);
            } catch (NumberFormatException e) {
                System.out.println("Sprunget over: " + input);
            }
        }
        System.out.println("Sum: " + sum);
    }
}
```

## Opgave 3 – løkken inde i try

Det samme – men `try` står nu **uden om** løkken:

```java
public class Predict3 {
    public static void main(String[] args) {
        String[] inputs = {"12", "tolv", "7"};
        int sum = 0;
        try {
            for (String input : inputs) {
                sum += Integer.parseInt(input);
                System.out.println("Lagt til: " + input);
            }
        } catch (NumberFormatException e) {
            System.out.println("Stoppet: " + e.getMessage());
        }
        System.out.println("Sum: " + sum);
    }
}
```

Hvad er forskellen på opgave 2 og 3? Hvornår vil man have den ene, og hvornår den anden?

## Opgave 4 – Den forkerte catch

```java
public class Predict4 {
    public static void main(String[] args) {
        String[] inputs = {"12", "7"};
        try {
            int first = Integer.parseInt(inputs[0]);
            int third = Integer.parseInt(inputs[2]);
            System.out.println(first + third);
        } catch (NumberFormatException e) {
            System.out.println("Ikke et tal");
        }
        System.out.println("Færdig");
    }
}
```

Bliver `Færdig` skrevet ud? Hvorfor – eller hvorfor ikke? Hvad burde koden have gjort i stedet for
at fange noget?

## Opgave 5 – Hvad er et tal?

Hvilke af disse kan `Integer.parseInt` lave om til et tal? Skriv dit gæt ud for hver – og kør så
programmet.

```java
public class Predict5 {
    public static void main(String[] args) {
        String[] inputs = {"42", " 42", "+42", "4.2", "", "99999999999"};
        for (String input : inputs) {
            try {
                System.out.println("[" + input + "] -> " + Integer.parseInt(input));
            } catch (NumberFormatException e) {
                System.out.println("[" + input + "] -> " + e.getMessage());
            }
        }
    }
}
```

Hvad fortæller `" 42"` jer om, hvorfor filmsamlingen skriver `scanner.nextLine().trim()`?

---

# Del B – Læs en stack trace

## Opgave 6 – Billetkontoret

Tre klasser:

```java
public class Main {
    public static void main(String[] args) {
        TicketOffice office = new TicketOffice();
        office.sell("Jaws", "voksen");
        office.sell("Psycho", "barn");
        office.sell("Godzilla", "senior");
    }
}
```

```java
public class TicketOffice {
    private PriceList priceList = new PriceList();

    public void sell(String title, String type) {
        int price = priceList.getPrice(type);
        System.out.println(title + ": " + price + " kr.");
    }
}
```

```java
public class PriceList {
    private String[] types = {"voksen", "barn"};
    private String[] prices = {"110", "60"};

    public int getPrice(String type) {
        int index = findIndex(type);
        return Integer.parseInt(prices[index]);
    }

    private int findIndex(String type) {
        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(type)) {
                return i;
            }
        }
        return -1;
    }
}
```

Kørslen giver:

```text
Jaws: 110 kr.
Psycho: 60 kr.
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 2
	at PriceList.getPrice(PriceList.java:7)
	at TicketOffice.sell(TicketOffice.java:5)
	at Main.main(Main.java:6)
```

Svar – **uden** at køre koden:

1. Hvilken type exception er det? Hvad er beskeden?
2. I hvilken metode, fil og linje skete det?
3. Hvem kaldte den metode – og hvem kaldte den igen? Læs de tre `at`-linjer nedefra og op.
4. Hvorfor er index `-1`?
5. Hvordan skal fejlen rettes? Med en `try`/`catch` – eller på en anden måde?

## Opgave 7 – Jeres egne exceptions

Skriv et lille program, der går ned med hver af disse – og læs stack trace'en hver gang:

* `IndexOutOfBoundsException` (fra en `ArrayList`)
* `NullPointerException`
* `ArithmeticException`
* `DateTimeParseException`

Hvor hjælpsom er beskeden i hver af dem? Hvilken er mest hjælpsom?

---

# Del C – Billetautomaten

Lav klassen `TicketMachine` med en `main`-metode og en `Scanner`. Biografbilletten fra fredag
(`Cinema.getTicketPrice(int age)`) beregner prisen.

## Opgave 8 – readInt

Skriv metoden `private int readInt(String prompt)`, der spørger igen og igen, indtil brugeren har
skrevet et helt tal. Brug `nextLine()`, `trim()` og `Integer.parseInt`.

## Opgave 9 – readIntInRange

Skriv `private int readIntInRange(String prompt, int min, int max)`, der spørger igen, indtil
brugeren har skrevet et tal **mellem `min` og `max`** (begge med). Genbrug `readInt`. Skal der være
en `try`/`catch` i den nye metode?

## Opgave 10 – readYesNo

Skriv `private boolean readYesNo(String prompt)`, der returnerer `true` for `ja` og `j`, `false` for
`nej` og `n` – uanset store og små bogstaver – og spørger igen ved alt andet. Skal der være en
`try`/`catch` her?

## Opgave 11 – Automaten

Saml det i en metode `start()`:

1. Spørg om alderen, og find prisen.
2. Spørg om antal billetter (1–10).
3. Spørg, om der skal købes flere billetter. Hvis ja, så forfra.
4. Til sidst: skriv den samlede pris.

En kørsel kunne se sådan ud:

```text
Velkommen til billetautomaten!
Alder: tyve
"tyve" er ikke et helt tal. Prøv igen.
Alder: 20
Antal billetter (1-10): 0
Skriv et tal mellem 1 og 10.
Antal billetter (1-10): 11
Skriv et tal mellem 1 og 10.
Antal billetter (1-10): 2
2 billet(ter) à 110 kr.
Flere billetter? (ja/nej): måske
Svar ja eller nej.
Flere billetter? (ja/nej): j
Alder: 70
Antal billetter (1-10): 1
1 billet(ter) à 80 kr.
Flere billetter? (ja/nej): nej
I alt: 300 kr. Tak for i dag!
```

Byt computer med sidemanden, og prøv at få den andens automat til at gå ned.

---

# Del D – Kast selv

## Opgave 12 – Klippekortet siger fra

Ret `CoffeeCard` fra onsdag:

* Constructoren kaster en `IllegalArgumentException`, hvis antallet af klip er **negativt**. (0 er
  i orden – et tomt kort.)
* `addClips` kaster en `IllegalArgumentException`, hvis antallet er **0 eller negativt**.
* Beskederne skal forklare, hvad der er galt – på dansk.

Skriv tests med `assertThrows` – og test grænserne: hvad **må** man, og hvad må man ikke? Test også,
at et afvist `addClips` ikke har ændret antallet af klip.

## Opgave 13 – Ingen negative aldre

Ret `Cinema.getTicketPrice`, så den kaster en `IllegalArgumentException`, hvis alderen er under 0
eller over 130. Skriv tests af begge grænser – både den side, der er gyldig, og den, der ikke er.

Prøv så automaten fra del C med alderen `-3`. Hvad sker der? Fang exceptionen i `start()`, og skriv
beskeden til brugeren, så automaten fortsætter:

```text
Alder: -3
Alderen skal være mellem 0 og 130.
Flere billetter? (ja/nej):
```

Hvorfor skal reglen stå i `Cinema` – og ikke kun i automaten?

---

# Udfordringer

## Udfordring 1 – nextInt

Skriv `readInt` med `scanner.nextInt()` i stedet for `nextLine()` og `parseInt`. Fang
`InputMismatchException`. Glem med vilje `scanner.nextLine()` i `catch`, og skriv `abc`. Hvad sker
der? (Stop programmet med den røde firkant.) Ret det.

## Udfordring 2 – Komma eller punktum

Skriv `readDouble`, der læser et decimaltal med `Double.parseDouble`. Hvad sker der, når en dansk
bruger skriver `3,5`? Få metoden til at acceptere både `3,5` og `3.5`.

## Udfordring 3 – Hvor mange catch?

I `TicketMachine.start()` står der én `try`/`catch`. Hvor mange **andre** steder i programmet kan
der opstå en exception, som ingen fanger? Er der nogen? Hvad med `Scanner`, hvis brugeren lukker for
input – i IntelliJ's konsol med <kbd>Ctrl</kbd>+<kbd>D</kbd> (**Send EOF**)?
