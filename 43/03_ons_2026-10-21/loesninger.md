# Vejledende løsninger – Unit test med JUnit 5

Løsningerne til [opgaverne](opgaver.md). Alle tests herunder er kørt og grønne (JDK 21, JUnit
5.14.4). Jeres testnavne og testdata må gerne være anderledes – det vigtige er, at **tilfældene**
er med.

---

# Del A – Opsætning

## Opgave 2 – Den første test

Med `clipsLeft -= 2;` bliver testen rød:

```text
org.opentest4j.AssertionFailedError: expected: <9> but was: <8>
```

Testen forventede 9 klip tilbage, men der var 8. Det er præcis den besked, der fører én hen til
fejlen: der bliver trukket for meget.

## Opgave 3 – Flere tests af klippekortet

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
    void useClipRemovesOneClip() {
        // Act
        boolean used = card.useClip();

        // Assert
        assertTrue(used);
        assertEquals(9, card.getClipsLeft());
    }

    @Test
    void addClipsAddsToTheCard() {
        // Act
        card.addClips(5);

        // Assert
        assertEquals(15, card.getClipsLeft());
    }

    @Test
    void emptyCardCannotBeUsed() {
        // Arrange
        CoffeeCard emptyCard = new CoffeeCard(0);

        // Act
        boolean used = emptyCard.useClip();

        // Assert
        assertFalse(used);
        assertEquals(0, emptyCard.getClipsLeft());
    }

    @Test
    void cardIsEmptyAfterLastClip() {
        // Arrange
        CoffeeCard oneClipCard = new CoffeeCard(1);

        // Act
        oneClipCard.useClip();

        // Assert
        assertTrue(oneClipCard.isEmpty());
    }

    @Test
    void newCardWithClipsIsNotEmpty() {
        assertFalse(card.isEmpty());
    }

    @Test
    void eleventhClipCannotBeUsed() {
        // Arrange: brug alle 10 klip
        for (int i = 0; i < 10; i++) {
            card.useClip();
        }

        // Act
        boolean used = card.useClip();

        // Assert
        assertFalse(used);
        assertEquals(0, card.getClipsLeft());
    }
}
```

`emptyCardCannotBeUsed` og `cardIsEmptyAfterLastClip` laver deres eget kort, fordi de skal starte
med et andet antal klip end de ti fra `setUp`. Det er helt i orden – `@BeforeEach` er til det, de
fleste tests har til fælles, ikke en tvangstrøje.

---

# Del B – Spillelisten

## De to fejl

**Fejl 1 – `getTotalDurationText`** (opgave 6). Sekunder under 10 bliver skrevet med ét ciffer:
185 sekunder giver `"3:5"` i stedet for `"3:05"`, og 600 sekunder giver `"10:0"`. Testene fejler
med fx:

```text
expected: <3:05> but was: <3:5>
```

Rettelsen:

```java
    // Den samlede spilletid som minutter og sekunder, fx 185 sekunder = "3:05"
    public String getTotalDurationText() {
        int total = getTotalSeconds();
        int minutes = total / 60;
        int seconds = total % 60;
        if (seconds < 10) {
            return minutes + ":0" + seconds;   // 5 sekunder skal skrives "05"
        }
        return minutes + ":" + seconds;
    }
```

**Fejl 2 – `findSongsByArtist`** (opgave 7). Kun kunstnerens navn bliver lavet om til små
bogstaver – ikke søgeteksten. `"abba"` finder ABBA, men `"ABBA"` finder ingenting:

```text
expected: <2> but was: <0>
```

Testen med "flere match" opdagede det ikke, fordi den søgte med små bogstaver – præcis det tilfælde,
koden håndterede. Det er derfor, "store og små bogstaver" er sit **eget** testtilfælde. Rettelsen:

```java
    // Alle sange, hvor kunstnerens navn indeholder søgeteksten – uanset store og små bogstaver
    public ArrayList<Song> findSongsByArtist(String searchText) {
        ArrayList<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getArtist().toLowerCase().contains(searchText.toLowerCase())) {
                result.add(song);
            }
        }
        return result;
    }
```

## Opgave 4–9 – PlaylistTest

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {

    private Playlist playlist;

    @BeforeEach
    void setUp() {
        playlist = new Playlist("Fredagsbar");
        playlist.addSong("Dancing Queen", "ABBA", 231);
        playlist.addSong("Waterloo", "ABBA", 169);
        playlist.addSong("Kvinde min", "Gasolin'", 200);
    }

    // ---------- getNumberOfSongs og addSong ----------

    @Test
    void newPlaylistIsEmpty() {
        Playlist empty = new Playlist("Tom");

        assertEquals(0, empty.getNumberOfSongs());
    }

    @Test
    void addOneSong() {
        // Arrange
        Playlist list = new Playlist("Ny");

        // Act
        list.addSong("Smuk og dejlig", "Lars Lilholt", 245);

        // Assert
        assertEquals(1, list.getNumberOfSongs());
        assertEquals("Smuk og dejlig", list.getSongs().get(0).getTitle());
    }

    @Test
    void addSeveralSongs() {
        playlist.addSong("Hvor ska' vi sove i nat", "Laban", 190);

        assertEquals(4, playlist.getNumberOfSongs());
    }

    // ---------- getTotalSeconds ----------

    @Test
    void totalSecondsOfEmptyPlaylistIsZero() {
        Playlist empty = new Playlist("Tom");

        assertEquals(0, empty.getTotalSeconds());
    }

    @Test
    void totalSecondsAddsAllSongs() {
        // 231 + 169 + 200
        assertEquals(600, playlist.getTotalSeconds());
    }

    // ---------- getTotalDurationText ----------

    @Test
    void durationTextWholeMinutes() {
        // setUp: 600 sekunder = 10 minutter
        assertEquals("10:00", playlist.getTotalDurationText());
    }

    @Test
    void durationTextWithFewSeconds() {
        Playlist list = new Playlist("Kort");
        list.addSong("Kort sang", "Nogen", 185);

        assertEquals("3:05", list.getTotalDurationText());
    }

    @Test
    void durationTextOfEmptyPlaylist() {
        Playlist empty = new Playlist("Tom");

        assertEquals("0:00", empty.getTotalDurationText());
    }

    // ---------- findSongsByArtist ----------

    @Test
    void findWithNoMatchReturnsEmptyList() {
        ArrayList<Song> result = playlist.findSongsByArtist("Queen");

        assertTrue(result.isEmpty());
    }

    @Test
    void findWithOneMatch() {
        ArrayList<Song> result = playlist.findSongsByArtist("gasolin");

        assertEquals(1, result.size());
        assertEquals("Kvinde min", result.get(0).getTitle());
    }

    @Test
    void findWithSeveralMatches() {
        ArrayList<Song> result = playlist.findSongsByArtist("abba");

        assertEquals(2, result.size());
    }

    @Test
    void findIgnoresUpperAndLowerCase() {
        ArrayList<Song> result = playlist.findSongsByArtist("ABBA");

        assertEquals(2, result.size());
    }

    // ---------- getLongestSong ----------

    @Test
    void longestSongOfEmptyPlaylistIsNull() {
        Playlist empty = new Playlist("Tom");

        assertNull(empty.getLongestSong());
    }

    @Test
    void longestSongIsFound() {
        Song longest = playlist.getLongestSong();

        assertEquals("Dancing Queen", longest.getTitle());
    }

    @Test
    void longestSongWhenTwoAreEquallyLongIsTheFirst() {
        Playlist list = new Playlist("Lige lange");
        list.addSong("Første", "A", 200);
        list.addSong("Anden", "B", 200);

        assertEquals("Første", list.getLongestSong().getTitle());
    }

    // ---------- removeSong ----------

    @Test
    void removeSongRemovesIt() {
        // Arrange
        Song waterloo = playlist.getSongs().get(1);

        // Act
        boolean removed = playlist.removeSong(waterloo);

        // Assert
        assertTrue(removed);
        assertEquals(2, playlist.getNumberOfSongs());
        assertFalse(playlist.getSongs().contains(waterloo));
    }

    @Test
    void removeSongNotOnPlaylistReturnsFalse() {
        Song other = new Song("Waterloo", "ABBA", 169);   // samme oplysninger – men et andet objekt

        boolean removed = playlist.removeSong(other);

        assertFalse(removed);
        assertEquals(3, playlist.getNumberOfSongs());
    }
}
```

**Opgave 9, den nye `Song`:** `removeSong` returnerer `false`. Den nye sang har de samme oplysninger
som *Waterloo*, men den er et **andet objekt**. `ArrayList.remove` leder efter objektet med
`equals`, og `Song` har ikke sin egen `equals` – så to objekter er kun "ens", hvis det er det
**samme** objekt. (Man kan give `Song` en `equals`, der sammenligner titel, kunstner og længde. Så
ville `removeSong` returnere `true`. Det er ikke forkert – men det er en beslutning, man skal tage med
vilje.)

---

# Del C – Test-first

## Opgave 10 – Korte sange

Testene (tilføjet i `PlaylistTest`):

```java
    // ---------- getSongsShorterThan (test-first) ----------

    @Test
    void shorterThanFindsOnlyShorterSongs() {
        ArrayList<Song> result = playlist.getSongsShorterThan(210);

        assertEquals(2, result.size());
    }

    @Test
    void shorterThanDoesNotIncludeSongOfExactlyThatLength() {
        // Kvinde min er præcis 200 sekunder – den er IKKE kortere end 200
        ArrayList<Song> result = playlist.getSongsShorterThan(200);

        assertEquals(1, result.size());
        assertEquals("Waterloo", result.get(0).getTitle());
    }

    @Test
    void shorterThanWithNoMatchReturnsEmptyList() {
        assertTrue(playlist.getSongsShorterThan(60).isEmpty());
    }
```

Med den tomme metode, der returnerer `new ArrayList<>()`, er `shorterThanWithNoMatchReturnsEmptyList`
allerede **grøn** – en tom liste er jo det rigtige svar dér. De to andre er røde. Det er en god
påmindelse om, at én grøn test ikke beviser meget: man skal bruge flere tilfælde.

Metoden:

```java
    // Alle sange, der er KORTERE end det angivne antal sekunder (ikke lig med)
    public ArrayList<Song> getSongsShorterThan(int seconds) {
        ArrayList<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getSeconds() < seconds) {
                result.add(song);
            }
        }
        return result;
    }
```

`<` og ikke `<=`: sangen på præcis 200 sekunder er ikke *kortere* end 200. Det er testen med
grænsen, der sikrer det.

## Opgave 11 – Sabotage

Har I skrevet testene i del B, fanger de de fleste små fejl. Typiske huller:

* `>=` i stedet for `>` i `getLongestSong` fanges kun af testen med to lige lange sange.
* En fejl i `getName()` fanges ikke – den har ingen test.

---

# Del D – Filmsamlingen

En test af, at constructoren sætter alle seks attributter (seks assertions i én test er fint her –
det er **én** ting, der testes: constructoren), og testen af en setter fra
[del 5](../../projekter/filmsamling/del-5-test.md#arrange--act--assert):

```java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void constructorSetsAllFields() {
        // Arrange + Act
        Movie movie = new Movie("Casablanca", "Michael Curtiz", 1942, false, 102, "Drama");

        // Assert
        assertEquals("Casablanca", movie.getTitle());
        assertEquals("Michael Curtiz", movie.getDirector());
        assertEquals(1942, movie.getYearCreated());
        assertFalse(movie.isInColor());
        assertEquals(102, movie.getLengthInMinutes());
        assertEquals("Drama", movie.getGenre());
    }

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

---

# Udfordringer

## Udfordring 1 – Gennemsnit

```java
    // Gennemsnitlig længde i sekunder – 0, hvis spillelisten er tom
    public double getAverageSeconds() {
        if (songs.isEmpty()) {
            return 0;
        }
        return (double) getTotalSeconds() / songs.size();
    }
```

```java
    // ---------- getAverageSeconds (udfordring) ----------

    @Test
    void averageOfThreeSongs() {
        // (231 + 169 + 200) / 3 = 200
        assertEquals(200.0, playlist.getAverageSeconds(), 0.001);
    }

    @Test
    void averageIsNotRoundedDown() {
        Playlist list = new Playlist("To sange");
        list.addSong("A", "X", 100);
        list.addSong("B", "Y", 101);

        assertEquals(100.5, list.getAverageSeconds(), 0.001);
    }

    @Test
    void averageOfEmptyPlaylistIsZero() {
        assertEquals(0.0, new Playlist("Tom").getAverageSeconds(), 0.001);
    }
```

Regner metoden med `int` (`return getTotalSeconds() / songs.size();`), bliver 201 / 2 til 100 –
heltalsdivision smider decimalerne væk – og testen fejler:

```text
expected: <100.5> but was: <100.0>
```

Testen med de tre sange fra `setUp` ville **ikke** have fanget det, fordi gennemsnittet der er et helt
tal. Vælg testdata, der kan afsløre fejlen.

## Udfordring 2 – Hvad er der galt med testene?

* **`test1`** har ingen assertion. Den er grøn, uanset hvad `getTotalSeconds` returnerer – den
  tester kun, at metoden ikke går ned. Og navnet siger ingenting.
* **`totalSeconds`** har byttet om på forventet og faktisk. Den virker, men fejler den en dag, står
  der `expected: <den forkerte værdi> but was: <100>`, og man leder det forkerte sted.
* **`everything`** tester seks metoder i én test. Fejler den, ved man ikke, hvilken metode der er
  galt, før man har læst den – og alt efter den første fejlede assertion bliver aldrig kørt. Den
  bruger desuden testdata, der **ikke** kan finde de to fejl (`"1:40"` og `"b"`), så den er grøn på
  den fejlbehæftede kode. Del den op i én test pr. metode, og vælg data, der rammer kanterne.

## Udfordring 3 – Hvor meget er testet?

Med testene ovenfor er det kun `getName()` i `Playlist`, der aldrig bliver kørt. Én linje:
`assertEquals("Fredagsbar", playlist.getName());`.
