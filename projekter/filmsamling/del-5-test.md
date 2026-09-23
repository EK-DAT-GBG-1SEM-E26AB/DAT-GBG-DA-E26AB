# Filmsamling del 5 – Test

> Del af det samlede [Filmsamling-projekt](readme.md). Starter **onsdag 21-10** (JUnit sættes op,
> første test) og fortsætter **fredag 23-10** (tests af `MovieCollection`). **Bør være færdig
> inden mandag 26-10.**

## Beskrivelse

Hvordan ved I, at søgningen virker? Indtil nu har I startet programmet, oprettet et par film,
søgt og kigget på resultatet. Det virker – men det tager tid, og når I om en uge ændrer i
`MovieCollection`, skal I huske at gøre det hele igen. Og især huske de kedelige tilfælde: den
tomme samling, søgningen uden resultat, filmen, der ikke findes.

En **unit test** er et lille stykke kode, der afprøver én metode og tjekker, at resultatet er det
forventede. Når testene først er skrevet, kan I køre dem alle sammen med ét klik – hver gang I har
ændret noget. Bliver en test rød, ved I præcis, hvad der gik i stykker.

Onsdag 21-10 lærer I JUnit på en øvelse. I projektet sætter I JUnit op i jeres fælles repository og
skriver de første tests af `Movie`. Fredag 23-10 tester I `MovieCollection`.

## Læringsmål

Når du er færdig med denne del, skal du kunne:

* forklare, hvad en unit test er, og hvorfor man skriver dem
* tilføje JUnit 5 til et Maven-projekt i `pom.xml`
* skrive en test efter mønstret **Arrange – Act – Assert**
* bruge `assertEquals`, `assertTrue`, `assertFalse` og `assertNull`
* bruge `@BeforeEach` til at give hver test den samme udgangssituation
* vælge testtilfælde: det normale, det tomme og det, der ikke findes

---

## User story

**US7 – Automatiske tests** *(technical story – brugeren ser den ikke, men udviklerne har brug
for den)*

> Som udvikler vil jeg have automatiske tests af programmets logik, så vi hurtigt kan se, om en
> ændring har ødelagt noget.

* Der er unit tests af `Movie` og af **alle** de offentlige metoder i `MovieCollection`.
* Hver metode er testet i det normale tilfælde **og** i de tilfælde, hvor der ikke er noget at
  finde, rette eller slette.
* Alle tests er **grønne**.

`UserInterface` tester vi ikke med unit tests – den læser fra tastaturet og skriver på skærmen.
Det er netop derfor, al logikken skal ligge i de andre klasser: **det, der ligger uden for
`UserInterface`, kan testes.**

---

## Krav

### JUnit i projektet

Åbn `pom.xml` (den ligger i roden af projektet). IntelliJ har lavet den i del 0, og den ender med
`</properties>` og `</project>`. Indsæt JUnit 5 **mellem** de to linjer – altså lige efter
`</properties>` og lige før `</project>`:

```xml
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.14.4</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

Der må kun være **én** `<dependencies>` i filen. `<scope>test</scope>` betyder, at JUnit kun
bruges af testene – ikke af selve programmet.

Når `pom.xml` er ændret, viser IntelliJ et lille Maven-ikon øverst til højre i editoren
(**Load Maven Changes**). Klik på det, så henter IntelliJ JUnit fra nettet. Ses ikonet ikke, så
åbn **Maven**-vinduet i højre side og klik på knappen **Reload All Maven Projects** (de to runde
pile).

> **Er `org.junit` rødt i testklassen?** Så har IntelliJ ikke hentet JUnit endnu. Klik på
> Maven-ikonet eller **Reload All Maven Projects** – og tjek, at der ikke er en tastefejl i
> `pom.xml` (IntelliJ markerer den med rødt).

> **Kun én person ændrer `pom.xml`**, committer og pusher. De andre puller – og klikker på
> Maven-ikonet, når `pom.xml` er ændret. Det er hele pointen med Maven: JUnit følger med
> repositoriet.

Testklasserne ligger i `src/test/java`, og der er én testklasse pr. klasse, der testes:
`MovieTest` tester `Movie`, `MovieCollectionTest` tester `MovieCollection`. Den nemmeste måde at
oprette dem på: stil markøren på klassens navn, tryk <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>T</kbd>
(Mac: <kbd>Cmd</kbd>+<kbd>Shift</kbd>+<kbd>T</kbd>) og vælg **Create New Test...** med
**JUnit5**. Se også [Create tests](https://www.jetbrains.com/help/idea/create-tests.html) hos
JetBrains.

### Tests af Movie (onsdag 21-10)

Mindst:

* en test af, at constructoren sætter **alle** seks attributter
* en test af mindst én setter

### Tests af MovieCollection (fredag 23-10)

Mindst disse tilfælde – I bestemmer selv testmetodernes navne:

| Metode | Test |
|---|---|
| `getNumberOfMovies` | en ny samling har 0 film |
| `addMovie` | tilføj én film – samlingen har 1 film, og det er den rigtige |
| `addMovie` | tilføj flere film – antallet passer |
| `searchMovies` | søgning uden match giver en **tom** liste (ikke `null`) |
| `searchMovies` | søgning med præcis ét match |
| `searchMovies` | søgning med flere match |
| `searchMovies` | søgning er ligeglad med store og små bogstaver |
| `editMovie` | redigering ændrer filmens oplysninger og returnerer `true` |
| `editMovie` | en film, der ikke er i samlingen, giver `false` og ændres ikke |
| `deleteMovie` | sletning fjerner filmen og returnerer `true` |
| `deleteMovie` | en film, der ikke er i samlingen, giver `false`, og antallet er uændret |

---

## Sådan ser en test ud

### Arrange – Act – Assert

Hver test har tre dele:

1. **Arrange** – gør klar: opret de objekter, testen skal bruge.
2. **Act** – kald den metode, der testes. Én metode.
3. **Assert** – tjek, at resultatet er det, I forventede.

```java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void setTitleChangesTitle() {
        // Arrange
        Movie movie = new Movie("Jaws", "Steven Spielberg", 1975, true, 124, "Thriller");

        // Act
        movie.setTitle("Dødens gab");

        // Assert
        assertEquals("Dødens gab", movie.getTitle());
    }
}
```

`assertEquals(forventet, faktisk)` – **forventet først**. Bytter I om, virker testen stadig, men
når den fejler, står der `expected: <...> but was: <...>` med værdierne byttet om – og så leder I
efter fejlen det forkerte sted.

Kør testen med den grønne pil ud for metoden eller klassen. Grøn = bestået. Rød = IntelliJ viser,
hvad der var forventet, og hvad der kom.

### @BeforeEach: den samme samling til alle tests

De fleste tests af `MovieCollection` skal bruge en samling med nogle film i. I stedet for at
skrive det i hver eneste test, gør man det i en metode med `@BeforeEach` – den kører **før hver
test**, så hver test får sin egen, friske samling:

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {

    private MovieCollection collection;

    @BeforeEach
    void setUp() {
        // Kører før HVER test – så starter alle tests med den samme, friske samling
        collection = new MovieCollection();
        collection.addMovie("The Godfather", "Francis Ford Coppola", 1972, true, 175, "Crime");
        collection.addMovie("Casablanca", "Michael Curtiz", 1942, false, 102, "Drama");
        collection.addMovie("Godzilla", "Ishirō Honda", 1954, false, 96, "Sci-fi");
    }

    @Test
    void searchWithSeveralMatches() {
        // "god" findes i både "The Godfather" og "Godzilla"
        ArrayList<Movie> result = collection.searchMovies("god");

        assertEquals(2, result.size());
    }

    @Test
    void deleteMovieRemovesIt() {
        // Arrange
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        // Act
        boolean deleted = collection.deleteMovie(casablanca);

        // Assert
        assertTrue(deleted);
        assertEquals(2, collection.getNumberOfMovies());
        assertTrue(collection.searchMovies("Casablanca").isEmpty());
    }
}
```

Bemærk, at `deleteMovieRemovesIt` sletter *Casablanca*, men det påvirker ikke de andre tests:
`setUp` bygger en ny samling før hver test. **Tests må aldrig afhænge af hinanden** eller af den
rækkefølge, de køres i.

### Hvilke tilfælde skal testes?

Det er fristende kun at teste det, der virker. Men fejlene gemmer sig i kanterne. For hver metode,
spørg:

* Hvad sker der i det **normale** tilfælde?
* Hvad sker der, når der er **ingenting**: en tom samling, en søgning uden match?
* Hvad sker der, når der er **flere**: flere match, flere film med næsten samme titel?
* Hvad sker der, når objektet **ikke findes**: rette eller slette en film, der ikke er i
  samlingen?

> **Find I en fejl, mens I skriver tests? Godt!** Det er det, de er til. Ret koden, og behold
> testen – så opdager I det med det samme, hvis fejlen kommer igen.

---

## Anbefalet procedure

### Onsdag 21-10

1. Lav dagens JUnit-øvelse (se [dagens side](../../43/03_ons_2026-10-21/README.md)).
2. Én person tilføjer JUnit til `pom.xml` og opretter `MovieTest` med én test. Kør den – grøn?
   Commit og push. Alle puller og kører testen på deres egen computer.
3. Skriv resten af testene af `Movie`.

### Fredag 23-10

Fordel `MovieCollectionTest`, så I ikke skriver i samme fil på samme tid – fx (er I to, tager den
første også punkt 3):

1. Den første skriver `setUp` og testene af `addMovie` og `getNumberOfMovies`, og pusher.
2. Den næste puller og skriver testene af `searchMovies`, og pusher.
3. Den tredje puller og skriver testene af `editMovie` og `deleteMovie`.

Den, der venter, læser imens kode. En god vane er at skrive tests til en metode, **en anden** har skrevet –
man ser andre ting, når man ikke selv har tænkt koden.

Kør **alle** tests, før I pusher (højreklik på `src/test/java` → **Run 'All Tests'**). Push aldrig
røde tests uden at skrive i commit-beskeden hvorfor.

---

## Frivillige udvidelser

* Test `Controller` på samme måde som `MovieCollection`. Hvad er forskellen – og er der noget, I
  ikke kan teste?
* Skriv testen **før** koden, næste gang I laver en ny metode i `MovieCollection` (fx en
  søgning på instruktør fra de frivillige udvidelser i del 1–4). Det kaldes *test-first*. Læs om
  forskellen i [Test-first vs. test-last approaches](https://khorikov.org/posts/2022-01-24-test-first-vs-test-last-approaches/)
  (Vladimir Khorikov).
* Slå **Run with Coverage** til (højreklik på testmappen). Hvilke linjer i `MovieCollection`
  bliver aldrig kørt af jeres tests?

**Læs mere:** [Assertions](https://docs.junit.org/5.14.4/writing-tests/assertions.html) i JUnits
brugervejledning.

---

**Næste:** [Del 5½ – Datoer og FURPS](del-5-datoer.md)
