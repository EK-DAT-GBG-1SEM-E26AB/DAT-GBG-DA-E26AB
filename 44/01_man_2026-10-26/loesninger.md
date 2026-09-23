# Vejledende løsninger – FURPS, datoer og design-review

Løsningerne til [opgaverne](opgaver.md). Al kode er kørt, og testene er grønne (JDK 21, JUnit
5.14.4).

---

# Del A – FURPS

## Opgave 1 – Hvilket bogstav?

| Krav | Bogstav | Hvorfor |
|---|---|---|
| 1. ledige tider 14 dage frem | **F** | noget, systemet kan |
| 2. aflyse op til to timer før | **F** | noget, systemet kan (med en regel) |
| 3. aldrig dobbeltbooking | **R** | man skal kunne stole på det |
| 4. under to sekunder | **P** | hastighed |
| 5. højst fire klik | **U** | let at bruge |
| 6. priser dækket af unit tests | **S** | let at ændre uden at ødelægge noget |
| 7. frisøren ser dagens bookinger | **F** | noget, systemet kan |
| 8. ingen bookinger tabt ved nedbrud | **R** | man skal kunne stole på det |
| 9. danske beskeder, der siger hvad man skal gøre | **U** | let at bruge |
| 10. 500 kunder på samme tid | **P** | kapacitet |
| 11. sms-klassen kan skiftes ud | **S** | let at ændre (lav kobling) |
| 12. sms dagen før | **F** | noget, systemet kan |

Nogle krav kan diskuteres – fx kan nr. 2 også ses som en regel, der gør systemet pålideligt for
frisøren. Det vigtige er ikke det rigtige bogstav, men at man har **tænkt over** alle fem.

## Opgave 2 – Gør kravet konkret

Forslag – der er mange gode svar:

| Vagt | Konkret | Bogstav |
|---|---|---|
| nem at bruge | Ved redigering kan man trykke Enter for at beholde den gamle værdi. | U |
| stabil | Skriver brugeren bogstaver, hvor der skal stå et tal, får hun en besked og kan prøve igen. | R |
| starte hurtigt | Programmet viser menuen på under ét sekund med 1.000 film i samlingen. | P |
| let at ændre | Al logik uden for `UserInterface` er dækket af unit tests, og alle tests er grønne. | S |
| søgningen skal virke godt | Søgning på en del af titlen finder alle film med den tekst, uanset store og små bogstaver. | F |

---

# Del B – LocalDate

## Opgave 4 – Forudsig output

```text
2026-10-26
2026-11-05
THURSDAY
true
-10
2026-02-28
2028
```

* Første linje: `date.plusDays(10)` ændrer ikke `date` – resultatet bliver smidt væk.
* `ChronoUnit.DAYS.between(later, date)` er **negativ**, fordi `date` ligger før `later`.
* 31. marts minus en måned ville være 31. februar. Den findes ikke, så `LocalDate` tager den sidste
  dag i februar.

## Opgave 5 – Dagens dato på dansk

```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Today {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate deadline = LocalDate.of(2026, 11, 4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("I dag er det " + today.format(formatter));
        long days = ChronoUnit.DAYS.between(today, deadline);
        System.out.println("Der er " + days + " dage til deadline " + deadline.format(formatter));
    }
}
```

Med `"dd-mm-yyyy"` går programmet ned med
`UnsupportedTemporalTypeException: Unsupported field: MinuteOfHour`: `mm` betyder **minutter**, og
en `LocalDate` har ingen minutter.

## Opgave 6 og 7 – DateTools

```java
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateTools {

    // Antal dage fra today til næste juleaften (24. december). Er det juleaften i dag, er svaret 0.
    public long daysUntilChristmasEve(LocalDate today) {
        LocalDate christmasEve = LocalDate.of(today.getYear(), 12, 24);
        if (today.isAfter(christmasEve)) {
            christmasEve = christmasEve.plusYears(1);
        }
        return ChronoUnit.DAYS.between(today, christmasEve);
    }

    // Alder i hele år på dagen today
    public long getAge(LocalDate birthDate, LocalDate today) {
        return ChronoUnit.YEARS.between(birthDate, today);
    }
}
```

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateToolsTest {

    private DateTools tools;

    @BeforeEach
    void setUp() {
        tools = new DateTools();
    }

    @Test
    void daysUntilChristmasEveFromOctober() {
        assertEquals(59, tools.daysUntilChristmasEve(LocalDate.of(2026, 10, 26)));
    }

    @Test
    void daysUntilChristmasEveOnChristmasEve() {
        assertEquals(0, tools.daysUntilChristmasEve(LocalDate.of(2026, 12, 24)));
    }

    @Test
    void daysUntilChristmasEveDayAfterIsNextYear() {
        assertEquals(364, tools.daysUntilChristmasEve(LocalDate.of(2026, 12, 25)));
    }

    @Test
    void ageOnBirthday() {
        assertEquals(20, tools.getAge(LocalDate.of(2006, 10, 26), LocalDate.of(2026, 10, 26)));
    }

    @Test
    void ageDayBeforeBirthday() {
        assertEquals(19, tools.getAge(LocalDate.of(2006, 10, 27), LocalDate.of(2026, 10, 26)));
    }

    @Test
    void ageBornOnLeapDay() {
        // Født 29. februar 2008. 28. februar 2026 er man endnu ikke fyldt 18.
        assertEquals(17, tools.getAge(LocalDate.of(2008, 2, 29), LocalDate.of(2026, 2, 28)));
        assertEquals(18, tools.getAge(LocalDate.of(2008, 2, 29), LocalDate.of(2026, 3, 1)));
    }
}
```

* 25-12-2026: den næste juleaften er 24-12-**2027**, 364 dage væk. Uden `if`'en ville svaret blive
  `-1`.
* `ChronoUnit.YEARS.between` tæller **hele** år. Dagen før fødselsdagen er man stadig et år yngre.
* Født 29. februar: i et år uden 29. februar er man først fyldt år den 1. marts.

## Opgave 8 – Et udlån på biblioteket

```java
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Et udlån af en bog på biblioteket
public class Loan {
    private static final int LOAN_DAYS = 30;
    private static final int FINE_PER_DAY = 5;
    private static final int MAX_FINE = 200;

    private String title;
    private LocalDate loanDate;
    private LocalDate returnDate;   // null = bogen er ikke afleveret endnu

    public Loan(String title, LocalDate loanDate) {
        this.title = title;
        this.loanDate = loanDate;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    // Bogen skal afleveres senest 30 dage efter udlånet
    public LocalDate getDueDate() {
        return loanDate.plusDays(LOAN_DAYS);
    }

    // For sent = EFTER afleveringsdatoen. På selve dagen er det ikke for sent.
    public boolean isOverdue(LocalDate today) {
        return today.isAfter(getDueDate());
    }

    public long getDaysOverdue(LocalDate today) {
        if (!isOverdue(today)) {
            return 0;
        }
        return ChronoUnit.DAYS.between(getDueDate(), today);
    }

    // 5 kr. pr. dag for sent, højst 200 kr.
    public long getFine(LocalDate today) {
        long fine = getDaysOverdue(today) * FINE_PER_DAY;
        if (fine > MAX_FINE) {
            return MAX_FINE;
        }
        return fine;
    }
}
```

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    private Loan loan;

    @BeforeEach
    void setUp() {
        // Lånt 1. oktober 2026 – skal afleveres senest 31. oktober 2026
        loan = new Loan("Hobbitten", LocalDate.of(2026, 10, 1));
    }

    @Test
    void dueDateIsThirtyDaysLater() {
        assertEquals(LocalDate.of(2026, 10, 31), loan.getDueDate());
    }

    @Test
    void dueDateCrossesMonthAndYear() {
        Loan decemberLoan = new Loan("Klit", LocalDate.of(2026, 12, 15));

        assertEquals(LocalDate.of(2027, 1, 14), decemberLoan.getDueDate());
    }

    @Test
    void notOverdueBeforeDueDate() {
        assertFalse(loan.isOverdue(LocalDate.of(2026, 10, 20)));
    }

    @Test
    void notOverdueOnDueDate() {
        // Grænsen: på selve afleveringsdagen er det IKKE for sent
        assertFalse(loan.isOverdue(LocalDate.of(2026, 10, 31)));
    }

    @Test
    void overdueDayAfterDueDate() {
        assertTrue(loan.isOverdue(LocalDate.of(2026, 11, 1)));
    }

    @Test
    void daysOverdueIsZeroWhenNotOverdue() {
        assertEquals(0, loan.getDaysOverdue(LocalDate.of(2026, 10, 5)));
    }

    @Test
    void daysOverdueCountsFromDueDate() {
        assertEquals(3, loan.getDaysOverdue(LocalDate.of(2026, 11, 3)));
    }

    @Test
    void noFineWhenOnTime() {
        assertEquals(0, loan.getFine(LocalDate.of(2026, 10, 31)));
    }

    @Test
    void fineIsFiveKronerPerDay() {
        assertEquals(15, loan.getFine(LocalDate.of(2026, 11, 3)));
    }

    @Test
    void fineAtExactlyTheMaximum() {
        // 40 dage for sent = 200 kr. – præcis loftet
        assertEquals(200, loan.getFine(LocalDate.of(2026, 12, 10)));
    }

    @Test
    void fineNeverExceedsTheMaximum() {
        assertEquals(200, loan.getFine(LocalDate.of(2027, 6, 1)));
    }
}
```

* **Grænsen:** `isOverdue` bruger `isAfter`, så afleveringsdagen selv (31-10) ikke er for sent.
  Testene af 31-10 og 01-11 sikrer det.
* **Loftet:** 200 kr. / 5 kr. = **40 dage** for sent (fra 31-10 til 10-12). Testen med præcis 40
  dage fanger en forkert dagspris eller et forkert loft; testen langt over loftet sikrer, at loftet
  overhovedet virker. (Om der står `fine > MAX_FINE` eller `fine >= MAX_FINE`, giver i øvrigt det
  samme resultat – ved præcis 200 returneres 200 begge veje.)
* Ingen metode kalder `LocalDate.now()`. Derfor giver testene det samme resultat, uanset hvilken
  dag de køres.

---

# Del C – Design-review

## Opgave 9 – Review af MovieList

**1. Problemerne:**

| Problem | Hvor |
|---|---|
| `public` attribut – alle kan ændre listen uden om klassen | `list2` |
| uklare navne | `list2`, `s`, `m`, `x()`, `temp` |
| `System.out` uden for `UserInterface` | `search`, `delete` |
| `Scanner` uden for `UserInterface` – og der laves en ny ved hvert kald | `delete` |
| en metode gør to ting: søger **og** udskriver | `search` |
| en metode gør to ting: spørger brugeren **og** sletter | `delete` |
| magisk tal | `120` i `x()` |
| søgningen skelner mellem store og små bogstaver | `search` |
| `delete` sletter på titel – findes to film med samme titel, kan man kun ramme den første | `delete` |
| klassens navn siger ikke, at det er en **samling** – og den hedder noget andet end i klassediagrammet | `MovieList` |

**2. Kan ikke unit-testes:** `search` returnerer ingenting – den udskriver. En test kan ikke se
resultatet. `delete` læser fra tastaturet – en test ville stå og vente på input. Kun `x()` kan testes.

**3. US4:** "Søgningen er **ligeglad med store og små bogstaver**." `contains` uden `toLowerCase()`
finder ikke *Casablanca*, når man søger på `casa`.

**4. Feedback – et eksempel:**

> * `search` udskriver resultatet. Hvis den returnerede en `ArrayList<Movie>` i stedet, kunne I teste
>   den, og `UserInterface` kunne bestemme, hvordan resultatet vises.
> * Spørgsmålet "Er du sikker?" i `delete` hører til i `UserInterface`. Så kan `delete` nøjes med at
>   slette – og den kan testes.
> * Navnene `list2` og `x()` siger ikke, hvad de er. Hvad med `movies` og `countLongMovies()`?
> * Godt: `delete` returnerer `true`/`false`, så den, der kalder, kan se, om det lykkedes.

**5. Omskrevet:**

```java
import java.util.ArrayList;

public class MovieList {
    private static final int LONG_MOVIE_MINUTES = 120;

    private ArrayList<Movie> movies = new ArrayList<>();

    // Returnerer resultatet – UserInterface bestemmer, hvordan det vises
    public ArrayList<Movie> searchMovies(String searchText) {
        ArrayList<Movie> matches = new ArrayList<>();
        String text = searchText.toLowerCase();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(text)) {
                matches.add(movie);
            }
        }
        return matches;
    }

    // Spørgsmålet "Er du sikker?" hører til i UserInterface – her slettes bare
    public boolean deleteMovie(Movie movie) {
        return movies.remove(movie);
    }

    public int countLongMovies() {
        int count = 0;
        for (Movie movie : movies) {
            if (movie.getLengthInMinutes() > LONG_MOVIE_MINUTES) {
                count++;
            }
        }
        return count;
    }
}
```

"Er du sikker?" flytter til `UserInterface`, som spørger, **før** den kalder `deleteMovie`. Og
`deleteMovie` får det `Movie`-objekt, brugeren har valgt fra en søgning – ligesom i filmsamlingen.

---

# Udfordringer

## Udfordring 1 – Aflevering

En ny attribut, `private LocalDate returnDate;` – `null`, indtil bogen er afleveret:

```java
    // ---------- Udfordring: aflevering ----------

    public boolean isReturned() {
        return returnDate != null;
    }

    public void returnBook(LocalDate date) {
        returnDate = date;
    }

    // Er bogen afleveret, regnes bøden ud fra afleveringsdagen – ellers ud fra i dag
    public long getFineWhenReturnedOrToday(LocalDate today) {
        if (isReturned()) {
            return getFine(returnDate);
        }
        return getFine(today);
    }
```

```java
    // ---------- Udfordring: aflevering ----------

    @Test
    void newLoanIsNotReturned() {
        assertFalse(loan.isReturned());
    }

    @Test
    void fineStopsWhenBookIsReturned() {
        // Afleveret 2 dage for sent – bøden vokser ikke, selvom der går en måned mere
        loan.returnBook(LocalDate.of(2026, 11, 2));

        assertTrue(loan.isReturned());
        assertEquals(10, loan.getFineWhenReturnedOrToday(LocalDate.of(2026, 12, 2)));
    }

    @Test
    void fineUsesTodayWhenNotReturned() {
        assertEquals(10, loan.getFineWhenReturnedOrToday(LocalDate.of(2026, 11, 2)));
    }
```

## Udfordring 2 – Ugedag på dansk

```java
LocalDate date = LocalDate.of(2026, 10, 26);
String weekday = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.of("da", "DK"));
System.out.println(weekday);                    // mandag

DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd-MM-yyyy", Locale.of("da", "DK"));
System.out.println(date.format(formatter));     // mandag 26-10-2026
```

Importér `java.time.format.TextStyle` og `java.util.Locale`.

## Udfordring 3 – 31. februar

`LocalDate.parse("31-02-2026", ...)` giver **2026-02-28** – ingen fejl. En `DateTimeFormatter` er som
standard "smart" og retter en dag, der er for stor til måneden, til den sidste dag i måneden. Det er
nok ikke det, brugeren mente. Del 6 har en frivillig udvidelse om, hvordan man får den til at sige
fra.
