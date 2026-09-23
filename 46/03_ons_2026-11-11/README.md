# Repetition 3: Robust og persistent – og blandede opgaver

## Beskrivelse

Sidste repetitionsdag. I dag samler vi op på det, der gjorde Filmsamlingen til et **rigtigt**
program: det går ikke ned ved forkert input (**exceptions**), det husker data, når det lukkes
(**filer**), det kan vise data i den rækkefølge, brugeren vil have (**sortering**), det kan regne
med **datoer** – og vi kan bevise, at det virker (**tests**).

Dagen slutter med **blandede opgaver**, der kombinerer det hele – som til eksamen og som i
Delfinen, hvor medlemmer skal gemmes i en fil, kontingenter afhænger af alder og datoer, og
resultater skal sorteres.

| Dag | Emne |
| --- | --- |
| [man 09-11](../01_man_2026-11-09/README.md) | Grundlæggende |
| [tir 10-11](../02_tir_2026-11-10/README.md) | Objektorienteret |
| **ons 11-11** | **Robust og persistent – og blandede opgaver** |

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* fange en exception med `try`/`catch` og lade programmet fortsætte
* kaste en `IllegalArgumentException` med en forklarende besked – og vælge, hvor den skal fanges
* forklare forskellen på **checked** og **unchecked** exceptions
* skrive til og læse fra en tekstfil med `PrintStream` og `Scanner`, og lave en linje om til
  felter med `split`
* sortere med `Comparable` og `Comparator`
* regne med `LocalDate` og give datoen som parameter, så koden kan testes
* skrive JUnit-tests, også for grænsetilfælde og for exceptions

## Se disse videoer før undervisningen:

Se kun de videoer, hvor du er usikker på emnet – brug [selvtjekket](#selvtjek).

* [exception handling](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=9h5m29s) (til: 09:13:28)
* [write files](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=9h13m28s) (til: 09:21:58)
* [read files](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=9h21m58s) (til: 09:28:50)
* [dates & times](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=10h11m42s) (til: 10:20:24)

> Videoerne om filer kan bruge andre klasser end dem, vi har brugt (`PrintStream` og `Scanner`).
> Idéen er den samme: åbn filen, skriv eller læs linje for linje, luk filen.

## Læs nedenstående før undervisningen

---

### Exceptions

En **exception** er Javas måde at sige: *det her kunne ikke lade sig gøre*. Fanger ingen den, går
programmet ned med en rød tekst.

```java
String[] inputs = {"12", "tolv", "7"};
int sum = 0;
for (String input : inputs) {
    try {
        sum += Integer.parseInt(input);
        System.out.println("ok: " + input);
    } catch (NumberFormatException e) {
        System.out.println("ikke et tal: " + input);
    }
}
System.out.println(sum);
```

```text
ok: 12
ikke et tal: tolv
ok: 7
19
```

Når `parseInt` kaster, springer Java **direkte** til `catch` – resten af `try`-blokken springes
over. Bagefter fortsætter programmet efter `catch`.

**At kaste selv.** En klasse, der får en ugyldig værdi, skal sige fra – ikke gemme den:

```java
public void withdraw(int amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("Beløbet skal være positivt: " + amount);
    }
    if (amount > balance) {
        throw new IllegalArgumentException("Der er kun " + balance + " kr. på kontoen");
    }
    balance -= amount;
}
```

Den, der **ved, hvad brugeren skal have at vide**, fanger – typisk `UserInterface` – og viser
`e.getMessage()`.

| | Unchecked | Checked |
| --- | --- | --- |
| Eksempler | `NumberFormatException`, `IllegalArgumentException`, `NullPointerException` | `FileNotFoundException` |
| Skyldes typisk | en fejl, der kan undgås med en `if` | noget uden for programmet – filen er væk |
| Compileren | tvinger dig ikke | tvinger dig: fang den, eller skriv `throws` |

> **Tjek, hvis I kan. Fang, hvis I må.** En `catch` for `NullPointerException` eller
> `IndexOutOfBoundsException` skjuler næsten altid en fejl, som en `if` burde have forhindret. Se
> [Filmsamling del 6](../../projekter/filmsamling/del-6-exceptions.md#fang-eller-tjek).

---

### Filer

**Skriv** med en `PrintStream` – præcis som `System.out`:

```java
public static void saveLines(ArrayList<String> lines, String fileName) throws FileNotFoundException {
    PrintStream output = new PrintStream(new File(fileName));
    for (String line : lines) {
        output.println(line);
    }
    output.close();
}
```

**Læs** med en `Scanner` på en `File` i stedet for `System.in`:

```java
public static ArrayList<String> loadLines(String fileName) throws FileNotFoundException {
    ArrayList<String> lines = new ArrayList<>();
    Scanner fileScanner = new Scanner(new File(fileName));
    while (fileScanner.hasNextLine()) {
        lines.add(fileScanner.nextLine());
    }
    fileScanner.close();
    return lines;
}
```

Et **objekt** gemmes som én linje med felterne adskilt af `;` – og læses tilbage med
`line.split(";", -1)`. Husk:

* `close()`, ellers bliver det sidste måske aldrig skrevet
* datoer gemmes som `2026-11-11` (`LocalDate`'s egen `toString`) og læses med `LocalDate.parse`
* en ødelagt linje springes over – den må ikke vælte hele indlæsningen
* filen ligger i projektets rodmappe, der hvor `pom.xml` ligger

Det hele står i [Filmsamling del 7](../../projekter/filmsamling/del-7-filer.md).

---

### Sortering

| | `Comparable<T>` | `Comparator<T>` |
| --- | --- | --- |
| Implementeres af | klassen selv | en separat klasse |
| Metode | `compareTo(T other)` | `compare(T a, T b)` |
| Giver | den naturlige rækkefølge | alle andre rækkefølger |
| Bruges med | `Collections.sort(list)` | `list.sort(comparator)` |

Svaret er **negativt** (før), **0** (lige) eller **positivt** (efter). Brug `Integer.compare`,
`compareToIgnoreCase` – og for datoer `date.compareTo(other.date)`, fordi `LocalDate` selv er
`Comparable`. Sortér en **kopi**, hvis den oprindelige rækkefølge skal bevares. Se
[03-11](../../45/02_tir_2026-11-03/README.md).

---

### Datoer

```java
LocalDate today = LocalDate.of(2026, 11, 20);
LocalDate due = LocalDate.of(2026, 11, 11);
LocalDate later = due.plusDays(30);                        // 2026-12-11 – en NY dato
boolean overdue = today.isAfter(due);
long days = ChronoUnit.DAYS.between(due, today);           // antal dage fra due til today
```

**Giv dagens dato som parameter** – `isOverdue(LocalDate today)` i stedet for at kalde
`LocalDate.now()` inde i metoden. Så kan en test give en fast dato, og testen er grøn i morgen
også. Se [Filmsamling del 5½](../../projekter/filmsamling/del-5-datoer.md#hvorfor-får-movie-datoen-som-parameter).

---

### Tests

```java
class LoanTest {

    private static final LocalDate DUE = LocalDate.of(2026, 11, 11);

    private final Loan loan = new Loan("Kaptajn Klo", "Ida", DUE);

    @Test
    void notOverdueOnDueDate() {
        assertFalse(loan.isOverdue(DUE));
        assertEquals(0, loan.getFee(DUE));
    }

    @Test
    void emptyTitleIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new Loan("", "Ida", DUE));
    }
}
```

* Testklassen ligger i `src/test/java` i en package med **samme navn** som klassen, den tester.
* **Arrange – Act – Assert**: lav objekterne, gør det, der skal testes, tjek resultatet.
* `assertEquals(forventet, faktisk)` – **forventet først**.
* Test **grænserne**: dagen før, på og efter en frist; 9, 10 og 11 minutter, når reglen er "under
  10".
* `assertThrows` tjekker, at der **bliver** kastet.

Det hele står i [Filmsamling del 5](../../projekter/filmsamling/del-5-test.md).

---

### De klassiske fælder

| Fælde | Hvad sker der | I stedet |
| --- | --- | --- |
| Tom `catch` | fejlen forsvinder, og programmet kører videre med forkerte data | vis en besked, eller lad være med at fange |
| `catch` for `NullPointerException` | skjuler en fejl i koden | tjek for `null` med en `if` |
| Glemt `close()` | filen bliver tom eller mangler de sidste linjer | `close()` efter hver skrivning |
| `split(";")` uden `-1` | tomme felter til sidst forsvinder | `split(";", -1)` |
| `LocalDate.now()` inde i metoden | testen bliver rød en anden dag | datoen som parameter |
| Tests, der skriver til den rigtige fil | testene sletter jeres data | et eget testfilnavn, og slet filen i `@AfterEach` |
| `assertEquals(faktisk, forventet)` | forvirrende besked, når testen fejler | forventet først |
| `a - b` i `compare` | forkert ved meget store tal | `Integer.compare(a, b)` |

---

### Selvtjek

| Emne | Kan du ...? | Hvor |
| --- | --- | --- |
| Unit test | skrive en test med `@BeforeEach` og Arrange – Act – Assert | [del 5](../../projekter/filmsamling/del-5-test.md) |
| Datoer | beregne antal dage mellem to datoer | [del 5½](../../projekter/filmsamling/del-5-datoer.md) |
| Fang exceptions | skrive en `readInt`, der spørger igen ved forkert input | [del 6](../../projekter/filmsamling/del-6-exceptions.md) |
| Kast exceptions | afvise en ugyldig værdi og teste det med `assertThrows` | [del 6](../../projekter/filmsamling/del-6-exceptions.md) |
| Filer | gemme en liste af objekter og læse den igen | [del 7](../../projekter/filmsamling/del-7-filer.md) |
| Packages | forklare, hvorfor en metode uden `public` ikke kan kaldes fra en anden package | [02-11](../../45/01_man_2026-11-02/README.md) |
| Sortering | skrive en `Comparator` og sortere en kopi | [03-11](../../45/02_tir_2026-11-03/README.md) |

---

### Til eksamensopgaverne

Del D i dagens opgaver er skrevet som små eksamensopgaver: en kort beskrivelse, og du bestemmer
selv, hvordan det skal bygges. Sådan griber du dem an:

1. **Læs hele opgaven**, og streg navneordene under – det er kandidater til klasser og
   attributter. Udsagnsordene er kandidater til metoder.
2. **Tegn et lille klassediagram**, før du koder. Hvem har listen? Hvem har reglen?
3. **Start med den mindste klasse**, og få den til at virke – med en test eller en lille `main`.
4. **Én metode ad gangen.** Kør koden efter hver metode.
5. **Til sidst**: fil, sortering og pæn udskrift.

Du skal kunne **forklare** hver linje. Det er vigtigere end at nå det hele.

---

## Det vigtigste at tage med

* `try`/`catch` lader programmet fortsætte; `throw` afviser en ugyldig værdi
* den, der ved, hvad brugeren skal have at vide, fanger – typisk `UserInterface`
* **checked** exceptions (fx `FileNotFoundException`) skal fanges eller sendes videre med `throws`
* skriv med `PrintStream`, læs med `Scanner` – og husk `close()`
* ét objekt = én linje; `split(";", -1)` den anden vej
* `Comparable` = den naturlige rækkefølge; `Comparator` = alle andre
* giv datoen som parameter – så kan det testes
* test grænserne og exceptions – ikke kun det "normale" tilfælde

## Aktiviteter i undervisningen

### 1. Forudsig output

Start med [opgaver.md](opgaver.md), **del A**, alene. Skriv dit gæt ned, før du kører koden.

### 2. Opgaver

Fortsæt med [opgaver.md](opgaver.md):

* **del B** ★ – exceptions, én fil, én test
* **del C** ★★ – datoer, sortering, CSV og tests
* **del D** ★★★ – blandede eksamensopgaver

Der er [vejledende løsninger](loesninger.md), men prøv selv først.

### 3. Afslutning på repetitionsugen

Brug det sidste kvarter på at skrive tre ting ned til dig selv:

* det emne fra semestret, du er **mest sikker** på
* det emne, du er **mest usikker** på – og hvad du vil gøre ved det
* én ting, du vil gøre fra **starten** i Delfinen på mandag

På fredag er der [semesterevaluering](../05_fre_2026-11-13/README.md), og på mandag starter
[Delfinen](../../projekter/delfinen/readme.md).
