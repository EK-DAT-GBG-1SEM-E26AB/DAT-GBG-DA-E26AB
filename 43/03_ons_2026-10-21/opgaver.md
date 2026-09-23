# Opgaver – Unit test med JUnit 5

Dagens opgaver er delt op sådan:

* **Del A** – sæt JUnit op i et nyt Maven-projekt, og skriv de første tests.
* **Del B** – test en spilleliste. Der er **to fejl** gemt i koden. Find dem med tests.
* **Del C** – test-first: skriv testene, **før** metoden findes.
* **Del D** – filmsamlingen: JUnit i gruppens repository og de første tests af `Movie`.
* **Udfordringer** – til dem, der vil videre.

Der er [vejledende løsninger](loesninger.md) – men prøv selv først. Især i del B: kig ikke efter
fejlene i koden. Lad testene finde dem.

---

# Del A – Opsætning

## Opgave 1 – Et Maven-projekt med JUnit

1. I IntelliJ: **File → New → Project**. Navn: `junit-oevelse`. Language: **Java**. Build
   system: **Maven**. JDK: **21**. **Fjern** fluebenet i **Add sample code**. Klik **Create**.
2. Åbn `pom.xml`, og indsæt JUnit lige efter `</properties>`:

   ```xml
       <dependencies>
           <dependency>
               <groupId>org.junit.jupiter</groupId>
               <artifactId>junit-jupiter</artifactId>
               <version>5.14.4</version>
               <scope>test</scope>
           </dependency>
       </dependencies>
   ```

3. Klik på **Load Maven Changes** (Maven-ikonet øverst til højre i editoren).
4. Opret klassen `CoffeeCard` i `src/main/java` – kopiér den fra
   [dagens læsestof](README.md#den-første-test).

## Opgave 2 – Den første test

1. Stil markøren på klassenavnet `CoffeeCard`, og tryk <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>T</kbd>
   (Mac: <kbd>Cmd</kbd>+<kbd>Shift</kbd>+<kbd>T</kbd>) → **Create New Test...**. Vælg **JUnit5** som
   testing library. Klik **OK**.
2. Tjek, at `CoffeeCardTest` er havnet i `src/test/java` (grøn mappe) – ikke i `src/main/java`.
3. Skriv testen `useClipRemovesOneClip` fra læsestoffet, og kør den. Grøn?
4. Ret `useClip`, så den trækker **to** klip (`clipsLeft -= 2;`). Kør testen igen. Hvad står der?
   Ret koden tilbage.

## Opgave 3 – Flere tests af klippekortet

Skriv tests af disse tilfælde – ét tilfælde pr. test, med Arrange – Act – Assert:

1. `addClips(5)` på et kort med 10 klip giver 15 klip.
2. `useClip()` på et tomt kort returnerer `false`, og kortet har stadig 0 klip.
3. Et kort med ét klip er tomt (`isEmpty()`), efter klippet er brugt.
4. Et nyt kort med 10 klip er **ikke** tomt.
5. Brug alle 10 klip i en løkke. Det 11. kald af `useClip()` returnerer `false`.

Flyt til sidst `new CoffeeCard(10)` op i en `@BeforeEach`-metode, og brug den i de tests, der
starter med ti klip. Kør alle tests – stadig grønne?

---

# Del B – Spillelisten

Opret de to klasser herunder i `src/main/java`. Koden **kompilerer og ser rigtig ud** – men der er
to fejl i den. Skriv tests efter opgaverne, og lad testene finde fejlene. Når en test bliver rød:
find fejlen, ret **koden**, og kør alle tests igen.

```java
public class Song {
    private String title;
    private String artist;
    private int seconds;

    public Song(String title, String artist, int seconds) {
        this.title = title;
        this.artist = artist;
        this.seconds = seconds;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getSeconds() {
        return seconds;
    }
}
```

```java
import java.util.ArrayList;

public class Playlist {
    private String name;
    private ArrayList<Song> songs;

    public Playlist(String name) {
        this.name = name;
        songs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addSong(String title, String artist, int seconds) {
        songs.add(new Song(title, artist, seconds));
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public int getNumberOfSongs() {
        return songs.size();
    }

    // Den samlede spilletid i sekunder
    public int getTotalSeconds() {
        int total = 0;
        for (Song song : songs) {
            total += song.getSeconds();
        }
        return total;
    }

    // Den samlede spilletid som minutter og sekunder, fx 185 sekunder = "3:05"
    public String getTotalDurationText() {
        int total = getTotalSeconds();
        int minutes = total / 60;
        int seconds = total % 60;
        return minutes + ":" + seconds;
    }

    // Alle sange, hvor kunstnerens navn indeholder søgeteksten – uanset store og små bogstaver
    public ArrayList<Song> findSongsByArtist(String searchText) {
        ArrayList<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getArtist().toLowerCase().contains(searchText)) {
                result.add(song);
            }
        }
        return result;
    }

    // Den længste sang – eller null, hvis spillelisten er tom.
    // Er flere sange lige lange, returneres den første af dem.
    public Song getLongestSong() {
        Song longest = null;
        for (Song song : songs) {
            if (longest == null || song.getSeconds() > longest.getSeconds()) {
                longest = song;
            }
        }
        return longest;
    }

    // Fjerner sangen. Returnerer false, hvis den ikke var på spillelisten.
    public boolean removeSong(Song song) {
        return songs.remove(song);
    }
}
```

Lav testklassen `PlaylistTest` med en `@BeforeEach`, der opretter en spilleliste med tre sange:

```java
@BeforeEach
void setUp() {
    playlist = new Playlist("Fredagsbar");
    playlist.addSong("Dancing Queen", "ABBA", 231);
    playlist.addSong("Waterloo", "ABBA", 169);
    playlist.addSong("Kvinde min", "Gasolin'", 200);
}
```

## Opgave 4 – Antal sange

* En ny, tom spilleliste har 0 sange.
* Tilføj én sang til en tom spilleliste: der er 1 sang, og det er den rigtige (tjek titlen).
* Tilføj en sang til spillelisten fra `setUp`: der er 4.

## Opgave 5 – Samlet spilletid

* En tom spilleliste varer 0 sekunder.
* Spillelisten fra `setUp` varer 600 sekunder. (Regn efter!)

## Opgave 6 – Spilletid som tekst

`getTotalDurationText()` skal skrive minutter og sekunder med **to cifre** efter kolonet – som et
ur. Tænk over, hvilke tilfælde der kan gå galt, og skriv mindst tre tests. Et par forslag:

* En spilleliste med én sang på 185 sekunder skal give `"3:05"`.
* Spillelisten fra `setUp` (600 sekunder)?
* En tom spilleliste?

Bliver en test rød? Find fejlen, og ret den.

## Opgave 7 – Søg efter kunstner

Skriv tests af `findSongsByArtist` – mindst:

* ingen match giver en **tom** liste
* præcis ét match (tjek også titlen)
* flere match
* søgningen er ligeglad med store og små bogstaver

Bliver en test rød? Find fejlen, og ret den. Hvorfor opdagede testen med "flere match" den ikke?

## Opgave 8 – Den længste sang

* En tom spilleliste har ingen længste sang (`assertNull`).
* Den længste sang på spillelisten fra `setUp` er *Dancing Queen*.
* Er to sange lige lange, er det den **første**, der returneres. (Læs kommentaren over metoden.)

## Opgave 9 – Fjern en sang

* Fjern *Waterloo*: `removeSong` returnerer `true`, der er 2 sange tilbage, og *Waterloo* er væk.
  (Hent `Song`-objektet fra `getSongs()`.)
* Fjern en sang, der **ikke** er på spillelisten: `removeSong` returnerer `false`, og der er stadig
  3 sange.

Prøv i den sidste test at lave en **ny** `Song` med præcis de samme oplysninger som *Waterloo* og
fjerne den. Hvad returnerer `removeSong`? Hvorfor?

---

# Del C – Test-first

## Opgave 10 – Korte sange

Spillelisten skal have en ny metode:

```java
// Alle sange, der er KORTERE end det angivne antal sekunder (ikke lig med)
public ArrayList<Song> getSongsShorterThan(int seconds)
```

Denne gang skriver I testene **først**:

1. Skriv tre tests i `PlaylistTest` – **før** metoden findes:
   * sange kortere end 210 sekunder (hvor mange er der i `setUp`?)
   * en sang på **præcis** grænsen er ikke med: kortere end 200
   * ingen sange kortere end 60 giver en tom liste
2. Testene kompilerer ikke – metoden findes jo ikke. Tryk <kbd>Alt</kbd>+<kbd>Enter</kbd> på det røde
   metodenavn, og lad IntelliJ lave en tom metode i `Playlist`. Lad den returnere
   `new ArrayList<>()`.
3. Kør testene. Hvilke er røde, og hvilke er grønne? Hvorfor er én af dem allerede grøn?
4. Skriv metoden, så alle tre bliver grønne.

Hvad var anderledes ved at skrive testene først?

## Opgave 11 – Sabotage

Byt computer med sidemanden. Lav **én** lille fejl i den andens `Playlist` – fx `>=` i stedet for
`>`, `+ 1` et sted, eller slet et `toLowerCase()`. Sig ikke hvor.

Byt tilbage, og kør alle tests. Fanger testene fejlen? Hvis ikke: hvilken test mangler?

---

# Del D – Filmsamlingen

## Opgave 12 – JUnit i gruppens repository

Følg [del 5, onsdag](../../projekter/filmsamling/del-5-test.md#onsdag-21-10):

1. **Én** person tilføjer JUnit til `pom.xml` i gruppens projekt og opretter `MovieTest` med én
   test. Kør den. Commit og push.
2. De andre puller, klikker **Load Maven Changes** og kører testen på deres egen computer.
3. Skriv resten af testene af `Movie`: constructoren sætter alle seks attributter, og mindst én
   setter virker.

---

# Udfordringer

## Udfordring 1 – Gennemsnit

Tilføj en metode, der returnerer den gennemsnitlige længde i sekunder – og 0, hvis spillelisten er
tom:

```java
public double getAverageSeconds()
```

Skriv tests af den, **også** en med to sange på 100 og 101 sekunder. Brug
`assertEquals(forventet, faktisk, 0.001)`. Skriv metoden, så testene er grønne. Hvad sker der med
testen, hvis metoden regner med `int` i stedet for `double`?

## Udfordring 2 – Hvad er der galt med testene?

Her er tre tests, der alle er **grønne**. Hvad er der galt med hver af dem?

```java
@Test
void test1() {
    Playlist playlist = new Playlist("X");
    playlist.addSong("A", "B", 100);
    playlist.getTotalSeconds();
}
```

```java
@Test
void totalSeconds() {
    Playlist playlist = new Playlist("X");
    playlist.addSong("A", "B", 100);

    assertEquals(playlist.getTotalSeconds(), 100);
}
```

```java
@Test
void everything() {
    Playlist playlist = new Playlist("X");
    assertEquals(0, playlist.getNumberOfSongs());
    playlist.addSong("A", "B", 100);
    assertEquals(1, playlist.getNumberOfSongs());
    assertEquals(100, playlist.getTotalSeconds());
    assertEquals("1:40", playlist.getTotalDurationText());
    assertNotNull(playlist.getLongestSong());
    assertEquals(1, playlist.findSongsByArtist("b").size());
}
```

## Udfordring 3 – Hvor meget er testet?

Højreklik på `src/test/java` → **More Run/Debug → Run 'All Tests' with Coverage**. IntelliJ viser,
hvor stor en del af hver klasse der blev kørt af testene, og farver linjerne i koden grønne (kørt)
og røde (aldrig kørt). Er der linjer i `Playlist`, som ingen test rører? Skriv en test, der gør.

> 100 % dækning betyder ikke, at koden er fejlfri – kun at hver linje er kørt mindst én gang.
> Testen i udfordring 2, `test1`, kører linjer uden at tjekke noget.
