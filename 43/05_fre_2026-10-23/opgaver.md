# Opgaver – Test af samlingen

Dagens hovedopgave er [Filmsamling del 5](../../projekter/filmsamling/del-5-test.md#tests-af-moviecollection-fredag-23-10):
`MovieCollectionTest`. Opgaverne her træner det, der gør testene **gode**:

* **Del A** – find testtilfældene. På papir, før der skrives kode.
* **Del B** – test testene: sabotér jeres egen `MovieCollection`, og se, om testene opdager det.
* **Del C** – coverage.
* **Udfordringer**.

Der er [vejledende løsninger](loesninger.md) til del A og B.

---

# Del A – Find testtilfældene

Lav del A **sammen** i gruppen, på papir eller en tavle. Skriv for hver metode en tabel med
testtilfælde: input, og hvad der forventes. Brug de tre kilder fra læsestoffet: acceptkriterier,
grupper af input og grænser.

## Opgave 1 – Biografbilletten

```java
// Børn under 12 år: 60 kr. Fra 12 til og med 64 år: 110 kr. Fra 65 år: 80 kr.
public int getTicketPrice(int age)
```

* Hvilke grupper af alder er der?
* Hvor ligger grænserne? Hvilke aldre skal testes på hver side af dem?
* Hvad med alderen 0?

## Opgave 2 – Lange film

```java
// Alle film i samlingen, der er LÆNGERE end det angivne antal minutter
public ArrayList<Movie> getMoviesLongerThan(int minutes)
```

Samlingen indeholder *The Godfather* (175 min.), *Casablanca* (102 min.) og *Godzilla* (96 min.).
Find mindst fem testtilfælde. Husk den tomme samling og grænsen.

## Opgave 3 – Årti

```java
// Alle film fra det årti, der starter med decade – fx 1970 giver film fra 1970 til og med 1979
public ArrayList<Movie> getMoviesFromDecade(int decade)
```

Hvilke årstal skal der være film med i samlingen, for at grænserne bliver testet ordentligt?

## Opgave 4 – Biografbilletten, test-first

Lav opgave 1 i kode, **test-first**, i jeres `junit-oevelse`-projekt fra onsdag:

1. Opret klassen `Cinema` med metoden `getTicketPrice(int age)`, der bare returnerer `0`.
2. Skriv testene fra jeres tabel i `CinemaTest`. Kør dem – de fleste er røde.
3. Skriv metoden, så alle tests bliver grønne.
4. Byt `<` ud med `<=` et sted i metoden. Hvilken test fanger det?

---

# Del B – Test testene

Lav del B, **når** `MovieCollectionTest` er skrevet og grøn.

Hver opgave er en lille fejl, som I laver **med vilje** i jeres egen `MovieCollection`. For hver:

1. Skriv ned, **før** I kører: hvilken test forventer I bliver rød?
2. Lav fejlen, og kør **alle** tests.
3. Blev en test rød? Hvilken?
4. **Fortryd fejlen** (<kbd>Ctrl</kbd>+<kbd>Z</kbd>, eller højreklik på filen → **Git → Rollback**),
   og tjek, at alt er grønt igen.

Bliver **ingen** test rød, har I fundet et hul i testene. Skriv den test, der mangler – og prøv så
fejlen igen.

> **Commit aldrig en sabotage.** Tjek, at alt er grønt, før I går videre.

## Opgave 5 – Søgning

Lav én fejl ad gangen i `searchMovies`:

1. Fjern `toLowerCase()` på **søgeteksten** (men behold den på titlen).
2. Brug `equals` i stedet for `contains`.
3. Returnér `null` i stedet for en tom liste, når intet matcher:

   ```java
   if (matches.isEmpty()) {
       return null;   // SABOTAGE
   }
   return matches;
   ```

## Opgave 6 – Redigering

Lav én fejl ad gangen i `editMovie`:

1. Fjern tjekket `if (!movies.contains(movie))`, så metoden altid retter og returnerer `true`.
2. Slet linjen `movie.setGenre(genre);`.
3. Slet linjen `movie.setDirector(director);`.
4. Slet linjen `movie.setYearCreated(yearCreated);`.

Fangede testene alle fire? Hvis ikke – hvad skal testen af `editMovie` tjekke, som den ikke gør?

## Opgave 7 – Sletning

Lav fejlen i `deleteMovie`:

```java
public boolean deleteMovie(Movie movie) {
    movies.remove(movie);
    return true;   // SABOTAGE
}
```

## Opgave 8 – Byt med en anden gruppe

Byt computer med en anden gruppe i fem minutter. Lav **én** lille fejl i deres `MovieCollection`
uden at sige hvor. Byt tilbage: finder jeres tests den?

---

# Del C – Coverage

## Opgave 9 – Røde linjer

Kør alle tests med coverage: højreklik på `src/test/java` → **More Run/Debug → Run 'All Tests'
with Coverage**.

* Hvor mange procent af `MovieCollection` og `Movie` er dækket?
* Find de røde linjer. Hvilket testtilfælde mangler?
* Hvor mange procent af `UserInterface` er dækket? Hvorfor?

---

# Udfordringer

## Udfordring 1 – Søgning på ingenting

Skriv en test af, hvad `searchMovies("")` returnerer. Kør den. Er det det, I vil have? Tal om det i
gruppen – og ret enten koden eller skriv testen, så den dokumenterer jeres beslutning.

## Udfordring 2 – getMovies

`getMovies()` returnerer samlingens egen `ArrayList`. Skriv en test, der kalder
`collection.getMovies().clear()` og derefter tjekker `getNumberOfMovies()`. Hvad sker der? Er det
et problem? (Hint: hvem kan nu ændre samlingen uden om `MovieCollection`?)

## Udfordring 3 – Test Controller

Skriv `ControllerTest` med tests af `addMovie`, `searchMovies` og `deleteMovie` gennem `Controller`.
Hvad er forskellen fra `MovieCollectionTest`? Er der noget, der **ikke** kan testes?

## Udfordring 4 – Lange film og årti i koden

Skriv `getMoviesLongerThan` og `getMoviesFromDecade` fra opgave 2 og 3 i jeres `MovieCollection` –
test-first, med testtilfældene fra jeres tabeller. (Frivilligt – de er ikke en del af projektets
krav.)
