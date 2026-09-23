# Vejledende løsninger – Test af samlingen

Løsningerne til [opgaverne](opgaver.md). Tabellerne i del A er forslag – jeres må gerne se
anderledes ud, bare grupperne og grænserne er med. Al kode herunder er kørt, og testene er grønne
(JDK 21, JUnit 5.14.4).

---

# Del A – Find testtilfældene

## Opgave 1 – Biografbilletten

Tre grupper: børn (under 12), voksne (12–64) og pensionister (65+). To grænser: mellem 11 og 12 og
mellem 64 og 65.

| Alder | Hvorfor | Forventet |
|---|---|---|
| 0 | den mindste mulige alder | 60 |
| 7 | et typisk barn | 60 |
| 11 | sidste år som barn | 60 |
| 12 | første år som voksen | 110 |
| 30 | en typisk voksen | 110 |
| 64 | sidste år som voksen | 110 |
| 65 | første år som pensionist | 80 |
| 90 | en typisk pensionist | 80 |

De fire vigtigste er **11, 12, 64 og 65**. Negative aldre giver ingen mening – hvad metoden skal
gøre ved dem, ser vi på i uge 44, når vi lærer at **afvise** ugyldige værdier.

## Opgave 2 – Lange film

| Kald | Hvorfor | Forventet |
|---|---|---|
| `getMoviesLongerThan(100)` | flere match | *The Godfather*, *Casablanca* |
| `getMoviesLongerThan(120)` | ét match | *The Godfather* |
| `getMoviesLongerThan(102)` | **grænsen**: Casablanca er præcis 102 | kun *The Godfather* |
| `getMoviesLongerThan(101)` | lige under grænsen | *The Godfather*, *Casablanca* |
| `getMoviesLongerThan(200)` | ingen match | tom liste |
| tom samling | ingenting at finde | tom liste |

## Opgave 3 – Årti

For at teste grænserne for 1970'erne skal der være film fra **1969, 1970, 1979 og 1980**: de to
første og de to sidste år på hver side af grænsen. Kun 1970 og 1979 skal med. Et årti uden film
skal give en tom liste.

## Opgave 4 – Biografbilletten, test-first

```java
public class Cinema {
    private static final int CHILD_PRICE = 60;
    private static final int ADULT_PRICE = 110;
    private static final int SENIOR_PRICE = 80;

    // Børn under 12 år: 60 kr. Fra 12 til og med 64 år: 110 kr. Fra 65 år: 80 kr.
    public int getTicketPrice(int age) {
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
    void childPrice() {
        assertEquals(60, cinema.getTicketPrice(7));
    }

    @Test
    void newbornPaysChildPrice() {
        assertEquals(60, cinema.getTicketPrice(0));
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
    void adultPrice() {
        assertEquals(110, cinema.getTicketPrice(30));
    }

    @Test
    void sixtyFourIsStillAnAdult() {
        assertEquals(110, cinema.getTicketPrice(64));
    }

    @Test
    void sixtyFiveIsASenior() {
        assertEquals(80, cinema.getTicketPrice(65));
    }

    @Test
    void seniorPrice() {
        assertEquals(80, cinema.getTicketPrice(90));
    }
}
```

Byttes `age < 12` ud med `age <= 12`, fanger `twelveIsAnAdult` det:

```text
expected: <110> but was: <60>
```

Byttes `age < 65` ud med `age <= 65`, fanger `sixtyFiveIsASenior` det. Testene med 7, 30 og 90
fanger ingen af fejlene – det er grænsetestene, der gør arbejdet.

---

# Del B – Test testene

Resultaterne herunder er fra et sæt tests med de elleve tilfælde i tabellen i
[del 5](../../projekter/filmsamling/del-5-test.md) og `setUp` derfra. Testen af `editMovie` tjekker
kun titel og genre. Jeres testnavne er nok andre.

## Opgave 5 – Søgning

| Sabotage | Røde tests |
|---|---|
| 1. ingen `toLowerCase()` på søgeteksten | søgning med store bogstaver (`"CASA"`) og med ét match (`"Casablanca"`) – og de tests, der **henter** en film med `searchMovies("Casablanca").get(0)`, går ned med `IndexOutOfBoundsException` |
| 2. `equals` i stedet for `contains` | søgning med flere match (`"god"`) og med store bogstaver |
| 3. `null` i stedet for tom liste | søgning uden match går ned med `NullPointerException` – og det samme gør testen af sletning, når den bagefter tjekker, at en søgning efter den slettede film er tom |

Læg mærke til sabotage 1: testen med `"casablanca"` med **små** bogstaver ville ikke opdage fejlen.
Den gør det kun, fordi søgeteksten i testen har et stort bogstav.

## Opgave 6 – Redigering

| Sabotage | Røde tests |
|---|---|
| 1. intet `contains`-tjek | redigering af en film, der ikke er i samlingen (`expected: <false> but was: <true>`) |
| 2. ingen `setGenre` | redigering ændrer felterne – **hvis** testen tjekker genren |
| 3. ingen `setDirector` | **ingen**, hvis testen kun tjekker titel og genre |
| 4. ingen `setYearCreated` | **ingen**, hvis testen kun tjekker titel og genre |

Sabotage 3 og 4 slipper igennem en test, der kun tjekker nogle af felterne – og de slipper også
igennem en test, der tjekker alle felter, men giver instruktør og årstal de **samme** værdier som
før. Testen skal give **alle** seks felter en ny værdi og tjekke dem alle:

```java
    @Test
    void editMovieChangesEveryField() {
        // Arrange
        Movie godzilla = collection.searchMovies("Godzilla").get(0);

        // Act: ALLE seks felter får en ny værdi
        boolean edited = collection.editMovie(godzilla, "Gojira", "Honda Ishirō", 1955, true, 97,
                "Monster");

        // Assert
        assertTrue(edited);
        assertEquals("Gojira", godzilla.getTitle());
        assertEquals("Honda Ishirō", godzilla.getDirector());
        assertEquals(1955, godzilla.getYearCreated());
        assertTrue(godzilla.isInColor());
        assertEquals(97, godzilla.getLengthInMinutes());
        assertEquals("Monster", godzilla.getGenre());
    }
```

Med den test bliver alle fire sabotager fanget.

## Opgave 7 – Sletning

Testen af at slette en film, der **ikke** er i samlingen, fanger den:
`expected: <false> but was: <true>`. Testen af at slette en film, der **er** der, fanger den ikke –
den forventer jo `true`.

---

# Del C – Coverage

## Opgave 9 – Røde linjer

Med de elleve tests fra del 5 er `MovieCollection` dækket 100 %. `Movie` mangler de settere, som
ingen test kalder – fx `setDirector`, hvis `editMovie`-testen ikke er med. `UserInterface` er 0 %
dækket: ingen test kalder den, fordi den læser fra tastaturet.

---

# Udfordringer

## Udfordring 1 – Søgning på ingenting

`searchMovies("")` returnerer **alle** film, fordi den tomme tekst findes i enhver tekst
(`"Jaws".contains("")` er `true`). Brugerfladen fjerner mellemrum med `trim()`, så også en søgning
på et mellemrum bliver til `""`. Om det er en fejl eller en feature, er jeres beslutning – begge dele
er i orden, når der er en test, der viser det.

## Udfordring 2 – getMovies

`collection.getMovies().clear()` tømmer **selve samlingen**: `getNumberOfMovies()` bliver 0.
`getMovies()` returnerer ikke en kopi, men samlingens egen liste – og så kan alle, der kalder den,
ændre samlingen uden om `MovieCollection`. I filmsamlingen er det kun `UserInterface`, der kalder
den (via `Controller`), og den læser bare listen. Men vil man være sikker, kan metoden returnere en
kopi: `return new ArrayList<>(movies);`.

## Udfordring 4 – Lange film og årti i koden

```java
    // Alle film, der er LÆNGERE end det angivne antal minutter
    public ArrayList<Movie> getMoviesLongerThan(int minutes) {
        ArrayList<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getLengthInMinutes() > minutes) {
                result.add(movie);
            }
        }
        return result;
    }

    // Alle film fra det årti, der starter med decade – fx 1970 giver 1970 til og med 1979
    public ArrayList<Movie> getMoviesFromDecade(int decade) {
        ArrayList<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            int year = movie.getYearCreated();
            if (year >= decade && year < decade + 10) {
                result.add(movie);
            }
        }
        return result;
    }
```

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ExtraCollectionTest {

    private MovieCollection collection;

    @BeforeEach
    void setUp() {
        collection = new MovieCollection();
        collection.addMovie("The Godfather", "Francis Ford Coppola", 1972, true, 175, "Crime");
        collection.addMovie("Casablanca", "Michael Curtiz", 1942, false, 102, "Drama");
        collection.addMovie("Godzilla", "Ishirō Honda", 1954, false, 96, "Sci-fi");
    }

    // ---------- getMoviesLongerThan ----------

    @Test
    void longerThanFindsSeveral() {
        assertEquals(2, collection.getMoviesLongerThan(100).size());
    }

    @Test
    void longerThanFindsOne() {
        ArrayList<Movie> result = collection.getMoviesLongerThan(120);

        assertEquals(1, result.size());
        assertEquals("The Godfather", result.get(0).getTitle());
    }

    @Test
    void movieOfExactlyThatLengthIsNotLonger() {
        // Casablanca er præcis 102 minutter
        ArrayList<Movie> result = collection.getMoviesLongerThan(102);

        assertEquals(1, result.size());
        assertEquals("The Godfather", result.get(0).getTitle());
    }

    @Test
    void movieOneMinuteLongerIsIncluded() {
        assertEquals(2, collection.getMoviesLongerThan(101).size());
    }

    @Test
    void longerThanWithNoMatchReturnsEmptyList() {
        assertTrue(collection.getMoviesLongerThan(200).isEmpty());
    }

    @Test
    void longerThanInEmptyCollectionReturnsEmptyList() {
        assertTrue(new MovieCollection().getMoviesLongerThan(0).isEmpty());
    }

    // ---------- getMoviesFromDecade ----------

    @Test
    void decadeIncludesFirstAndLastYear() {
        // Arrange: film på begge sider af begge grænser for 1970'erne
        MovieCollection decades = new MovieCollection();
        decades.addMovie("Easy Rider", "Dennis Hopper", 1969, true, 95, "Drama");
        decades.addMovie("M*A*S*H", "Robert Altman", 1970, true, 116, "Komedie");
        decades.addMovie("Apocalypse Now", "Francis Ford Coppola", 1979, true, 147, "Krig");
        decades.addMovie("The Shining", "Stanley Kubrick", 1980, true, 146, "Horror");

        // Act
        ArrayList<Movie> result = decades.getMoviesFromDecade(1970);

        // Assert
        assertEquals(2, result.size());
        assertEquals("M*A*S*H", result.get(0).getTitle());
        assertEquals("Apocalypse Now", result.get(1).getTitle());
    }

    @Test
    void decadeWithNoMoviesReturnsEmptyList() {
        assertTrue(collection.getMoviesFromDecade(1990).isEmpty());
    }
}
```
