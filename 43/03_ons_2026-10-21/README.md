# Test – unit test med JUnit 5

## Beskrivelse

Hvordan ved I, at jeres program virker? Indtil nu har svaret været: *vi har prøvet det*. I har
startet programmet, tastet noget ind og kigget på resultatet.

Det er en test – en **manuel** test. Den har tre problemer:

* Den tager tid, og derfor gør man det sjældent.
* Man glemmer de kedelige tilfælde: den tomme liste, søgningen uden resultat, den sidste film på listen.
* Når koden ændres om en uge, skal det hele gøres igen. Det gør ingen.

I dag lærer I at skrive **automatiske** tests: små stykker kode, der kalder jeres metoder og
tjekker, at resultatet er det forventede. Når de først er skrevet, kører I dem alle sammen med ét
klik – hver eneste gang, I har ændret noget.

Værktøjet hedder **JUnit** og er standard i Java-verdenen. I øver jer først på et lille program med
en spilleliste, og til sidst sætter I JUnit op i filmsamlingen og skriver de første tests af
`Movie` ([Filmsamling del 5](../../projekter/filmsamling/del-5-test.md)).

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* forklare, hvad en **unit test** er, og hvad den kan, som en manuel test ikke kan
* tilføje JUnit 5 til et Maven-projekt og placere testklasser i `src/test/java`
* skrive en test med `@Test` efter mønstret **Arrange – Act – Assert**
* bruge `assertEquals`, `assertTrue`, `assertFalse`, `assertNull` og `assertNotNull`
* køre tests i IntelliJ og læse en fejlet test: *expected ... but was ...*
* bruge `@BeforeEach` til at give hver test den samme, friske udgangssituation
* vælge testtilfælde: det normale, det tomme, grænsen og det, der ikke findes
* forklare forskellen på **test-first** og **test-last**

## Se disse videoer før undervisningen:

* [Unit Testing Introduction and JUnit](https://www.youtube.com/watch?v=1X1LDY2W0qA) (6:47) – hvad
  unit test er, og hvorfor man tester tidligt
* [Unit Testing with JUnit and IntelliJ – 01 – Setting up our first unit test](https://www.youtube.com/watch?v=vC_y0KvBVmU)
  (12:52) – den første test i IntelliJ
* [Unit Testing with JUnit and IntelliJ – 02 – Additional Annotations](https://www.youtube.com/watch?v=gQ9qRxCVo08)
  (6:49) – bl.a. `@BeforeEach`

> **Bemærk:** Videoerne er lavet til et tidligere hold og sætter måske JUnit op på en anden måde,
> end vi gør. Vi bruger **Maven**: JUnit står i `pom.xml` (se nedenfor). Selve testene skrives
> på samme måde.

## Læs nedenstående før undervisningen

---

### Hvad er en unit test?

En **unit** er den mindste del af et program, der giver mening at afprøve for sig: én metode eller
én klasse. En **unit test** kalder metoden med kendte værdier og tjekker, at den returnerer (eller
ændrer) det, den skal.

Der findes andre slags tests, som I møder senere på uddannelsen:

| Slags test | Tester | Eksempel i filmsamlingen |
|---|---|---|
| **Unit test** | én metode eller klasse for sig | `searchMovies("god")` finder to film |
| Integrationstest | flere dele sammen | gem samlingen i en fil og læs den igen |
| Systemtest (manuel) | hele programmet, som brugeren ser det | start programmet, opret en film, søg |

Unit tests er de hurtigste og de nemmeste at skrive – og det er dem, vi bruger nu.

Det, en unit test giver jer:

* **Tryghed ved ændringer.** Ændrer I noget i `MovieCollection` om en uge, fortæller testene med
  det samme, om noget andet gik i stykker. (Det hedder en *regressionstest*.)
* **Fejl, der bliver fundet tidligt.** Når man skriver en test af "søgning uden resultat", tænker
  man over det tilfælde – og opdager tit, at koden ikke håndterer det.
* **Dokumentation.** En test viser præcis, hvordan metoden skal bruges, og hvad den returnerer.

> **Unit tests tester logikken – ikke brugerfladen.** En metode, der læser fra tastaturet og
> skriver på skærmen, er svær at teste automatisk. Det er endnu en grund til reglen fra Adventure:
> `System.out` og `Scanner` hører til i `UserInterface`, og **alt andet** returnerer værdier.
> Det, der returnerer værdier, kan testes.

---

### JUnit i et Maven-projekt

JUnit er et **bibliotek** – kode, som andre har skrevet – og det følger ikke med Java. I et
Maven-projekt skriver man, hvilke biblioteker projektet bruger, i `pom.xml`. Maven henter dem selv.

Til JUnit 5 indsættes dette i `pom.xml`, lige efter `</properties>`:

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

Klik derefter på Maven-ikonet **Load Maven Changes**, som IntelliJ viser øverst til højre. Den
præcise vejledning – og hvad I gør, hvis `org.junit` bliver rødt – står i
[del 5, afsnittet *JUnit i projektet*](../../projekter/filmsamling/del-5-test.md#junit-i-projektet).

Et Maven-projekt har to kodemapper:

| Mappe | Indhold |
|---|---|
| `src/main/java` | programmet: `CoffeeCard`, `Movie`, ... |
| `src/test/java` | testene: `CoffeeCardTest`, `MovieTest`, ... |

Testene ligger for sig, så de ikke kommer med i det færdige program. Der er én testklasse pr.
klasse, der testes, og den hedder det samme med `Test` bagefter.

---

### Den første test

Her er en klasse, der skal testes – et klippekort til kaffe:

```java
// Et klippekort til kaffe: hvert klip giver én kop
public class CoffeeCard {
    private int clipsLeft;

    public CoffeeCard(int clips) {
        clipsLeft = clips;
    }

    public int getClipsLeft() {
        return clipsLeft;
    }

    // Bruger et klip. Returnerer false, hvis kortet er tomt.
    public boolean useClip() {
        if (clipsLeft == 0) {
            return false;
        }
        clipsLeft--;
        return true;
    }

    public void addClips(int clips) {
        clipsLeft += clips;
    }

    public boolean isEmpty() {
        return clipsLeft == 0;
    }
}
```

Og her er en test af `useClip`:

```java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeCardTest {

    @Test
    void useClipRemovesOneClip() {
        // Arrange
        CoffeeCard card = new CoffeeCard(10);

        // Act
        boolean used = card.useClip();

        // Assert
        assertTrue(used);
        assertEquals(9, card.getClipsLeft());
    }
}
```

Linje for linje:

* `@Test` fortæller JUnit, at metoden er en test. Uden den bliver metoden aldrig kørt.
* Testmetoden er `void` og har ingen parametre. Navnet siger, **hvad** der testes – det er det, I
  ser, når testen fejler. `test1` fortæller ingenting; `useClipRemovesOneClip` fortæller det hele.
* `import static org.junit.jupiter.api.Assertions.*;` gør, at man kan skrive `assertEquals(...)`
  i stedet for `Assertions.assertEquals(...)`.
* Klassen og metoderne behøver ikke være `public` – JUnit finder dem alligevel.

### Arrange – Act – Assert

Næsten alle tests har de samme tre dele:

1. **Arrange** – gør klar: opret de objekter, testen skal bruge.
2. **Act** – kald den metode, der testes. Helst kun én.
3. **Assert** – tjek, at resultatet er det forventede.

Skriv gerne de tre kommentarer ind i hver test, indtil mønstret sidder på rygraden. Er en test svær
at dele op på den måde, tester den som regel for meget på én gang.

### Assertions

En *assertion* er et tjek. Fejler et tjek, stopper testen og er **rød**. De vigtigste:

| Assertion | Er grøn, når ... |
|---|---|
| `assertEquals(forventet, faktisk)` | de to værdier er ens |
| `assertTrue(betingelse)` | betingelsen er `true` |
| `assertFalse(betingelse)` | betingelsen er `false` |
| `assertNull(værdi)` | værdien er `null` |
| `assertNotNull(værdi)` | værdien **ikke** er `null` |

> **Forventet først:** `assertEquals(9, card.getClipsLeft())`. Bytter I om, virker testen stadig,
> men når den fejler, står der *expected* og *but was* med værdierne byttet om – og så leder I efter
> fejlen det forkerte sted.

`assertEquals` sammenligner `String`s med `equals` – så `assertEquals("3:05", text)` virker, som man
håber. For decimaltal (`double`) skal man angive, hvor meget de må afvige:
`assertEquals(100.5, average, 0.001)` – fordi decimaltal i computeren sjældent er helt præcise.

Senere, når I skal teste, at en metode **afviser** en ugyldig værdi, kommer der én til:
`assertThrows`. Den venter vi med til [del 6](../../projekter/filmsamling/del-6-exceptions.md).

---

### Kør testene i IntelliJ

Ud for testklassen og hver testmetode står en grøn pil. Klik på den, og vælg **Run**. Resultatet
vises nederst:

* **Grønt flueben:** testen bestod.
* **Rødt kryds:** testen fejlede. Klik på den, og IntelliJ viser, hvad der var forventet, og hvad
  der kom.

Alle tests i projektet køres ved at højreklikke på mappen `src/test/java` → **Run 'All Tests'**.

### Når en test bliver rød

Lad os sige, at nogen kommer til at skrive `clipsLeft -= 2;` i stedet for `clipsLeft--;` i
`useClip`. Så fejler testen:

```text
org.opentest4j.AssertionFailedError: expected: <9> but was: <8>
	at org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
	...
	at CoffeeCardTest.useClipRemovesOneClip(CoffeeCardTest.java:17)
```

Første linje siger det vigtigste: der blev forventet 9 klip, men der var 8 – der blev trukket to.
Linjerne derunder viser, hvor det skete. Spring JUnits egne linjer (`org.junit...`) over, og find den
første linje med **jeres** klasse: `CoffeeCardTest.java:17` er linjen med `assertEquals`. Klik på
den i IntelliJ for at hoppe dertil.

> **En rød test er ikke et nederlag.** Det er testen, der gør sit arbejde: den fandt en fejl, før
> brugeren gjorde. Ret koden – ikke testen – medmindre det er testen, der tager fejl.

---

### @BeforeEach: den samme start til alle tests

Mange tests skal bruge det samme objekt. I stedet for at oprette det i hver test lægger man det i en
metode med `@BeforeEach`. Den kører **før hver eneste test**:

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeCardTest {

    private CoffeeCard card;

    @BeforeEach
    void setUp() {
        // Kører før HVER test: hver test får sit eget, friske kort med 10 klip
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
        card.addClips(5);

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
        CoffeeCard card = new CoffeeCard(1);

        card.useClip();

        assertTrue(card.isEmpty());
    }
}
```

Læg mærke til `addClipsAddsToTheCard`: den forventer 15 – ikke 14 – selvom
`useClipRemovesOneClip` har brugt et klip. Det er fordi `setUp` laver et **nyt** kort før hver
test.

> **Tests må aldrig afhænge af hinanden** – eller af den rækkefølge, de køres i. JUnit lover ikke
> nogen bestemt rækkefølge.

De to sidste tests laver deres **eget** kort, fordi de skal bruge et andet udgangspunkt end ti
klip. Det er helt i orden.

---

### Hvilke tilfælde skal testes?

Det er fristende kun at teste det tilfælde, man lige har fået til at virke. Men fejlene gemmer sig i
kanterne. For hver metode, spørg:

| Spørgsmål | `CoffeeCard` | En søgning i en liste |
|---|---|---|
| Det **normale** tilfælde? | brug et klip af ti | søgning med ét match |
| Når der er **ingenting**? | brug et klip af et tomt kort | søgning i en tom liste, søgning uden match |
| Når der er **flere**? | – | søgning med flere match |
| Ved **grænsen**? | det sidste klip | store og små bogstaver, præcis den længde |
| Når det **ikke findes**? | – | fjern noget, der ikke er i listen |

Det er de samme tilfælde, som del 5 beder jer om at teste i `MovieCollection` på fredag.

---

### Test-first eller test-last?

Man kan skrive testen **efter** koden (*test-last*) – som I gør i dag. Eller man kan skrive den
**før** (*test-first*):

1. Skriv en test af en metode, der ikke findes endnu. Den kompilerer ikke – eller den er rød.
2. Skriv lige præcis nok kode til, at testen bliver grøn.
3. Ryd op i koden. Testene fortæller, om oprydningen ødelagde noget.

Test-first tvinger en til at tænke over, **hvad** metoden skal gøre – navn, parametre,
returværdi, kanttilfælde – før man tænker over **hvordan**. Test-last er lettere at komme i gang
med, men man tester lettere det, koden gør, i stedet for det, den **skulle** gøre.

Ingen af dem er "den rigtige". I prøver begge i dagens opgaver. Læs evt.
[Test-first vs. test-last approaches](https://khorikov.org/posts/2022-01-24-test-first-vs-test-last-approaches/)
(Vladimir Khorikov).

---

### Sådan hænger det sammen med filmsamlingen

| Det lærer I i dag | Sådan bruges det i del 5 |
|---|---|
| JUnit i `pom.xml` | én person tilføjer det i gruppens repository – de andre puller |
| `src/test/java`, én testklasse pr. klasse | `MovieTest` og `MovieCollectionTest` |
| Arrange – Act – Assert | alle tests |
| `@BeforeEach` | en `MovieCollection` med tre film før hver test (fredag) |
| Tomt, grænse, ikke fundet | søgning uden match, redigér en film, der ikke er i samlingen |

---

## Det vigtigste at tage med

* en **unit test** kalder én metode med kendte værdier og tjekker resultatet – automatisk og hver
  gang, I vil
* JUnit står i `pom.xml`; testene ligger i `src/test/java`, én testklasse pr. klasse
* hver test: `@Test`, et navn, der siger hvad, og **Arrange – Act – Assert**
* `assertEquals(forventet, faktisk)` – **forventet først**
* `@BeforeEach` giver hver test en frisk start – tests må aldrig afhænge af hinanden
* test det normale, det tomme, grænsen og det, der ikke findes
* en rød test har fundet en fejl – ret koden, ikke testen
* det, der ligger uden for `UserInterface`, kan testes

## Aktiviteter i undervisningen

### 1. Opsamling på videoerne

Hvad er forskellen på at teste ved at køre programmet og at skrive en unit test? Hvilke tilfælde
glemte I at prøve af i jeres egen filmsamling i går?

### 2. Øvelse: spillelisten

Lav [dagens opgaver](opgaver.md) – et lille Maven-projekt med en spilleliste, hvor I sætter JUnit op,
skriver tests og finder de **to fejl**, der er gemt i koden. Arbejd **hver for sig** eller i par –
alle skal selv have sat JUnit op og kørt en test.

### 3. Filmsamling del 5 – første skridt

Følg [del 5, onsdag](../../projekter/filmsamling/del-5-test.md#onsdag-21-10): én person tilføjer
JUnit til gruppens `pom.xml` og opretter `MovieTest` med én test. Commit og push. Alle puller og
kører testen på deres egen computer. Skriv så resten af testene af `Movie`.

Fredag skriver I testene af `MovieCollection`.
