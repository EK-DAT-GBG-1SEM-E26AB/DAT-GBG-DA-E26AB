# Repetition 1: Grundlæggende programmering

## Beskrivelse

Filmsamlingen er afleveret og reviewet. Før Delfinen starter mandag 16-11, har vi tre dage til at samle
op på semestrets programmering.

Det er ikke spildtid. I Delfinen skal I bygge et større program i en gruppe på fire, og til eksamen
skal du kunne forklare og skrive kode **selv**. Begge dele går meget lettere, når det grundlæggende
sidder på rygraden – så man kan bruge kræfterne på designet i stedet for på at huske, om det hedder
`length` eller `length()`.

Ugen er bygget op sådan:

| Dag | Emne | Hvad |
| --- | --- | --- |
| **man 09-11** | **Grundlæggende** | datatyper, betingelser, loops, arrays, Strings, metoder |
| [tir 10-11](../02_tir_2026-11-10/README.md) | Objektorienteret | klasser, `ArrayList`, arv, polymorfi, abstrakte klasser, interfaces |
| [ons 11-11](../03_ons_2026-11-11/README.md) | Robust og persistent | exceptions, filer, sortering, `LocalDate`, test – og blandede opgaver |

Hver dag har en kort genopfriskning, en liste over de **klassiske fælder** og en masse
[opgaver](opgaver.md) i stigende sværhedsgrad. Du bestemmer selv, hvor du starter: kan du del A
og B i søvne, så spring videre.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* vælge den rigtige datatype og forklare heltalsdivision og `%`
* skrive betingelser med `if`/`else if`/`else`, `&&`, `||`, `!` og `switch`
* vælge mellem `for`, `while` og for-each – og undgå *off-by-one*
* bruge de fire mønstre: gennemløb, opsamling, tælling og søgning
* løbe et array og en `String` igennem og bruge de vigtigste String-metoder
* skrive metoder med parametre og returværdi – og forklare forskellen på `return` og `println`
* forudsige output af et lille program, før du kører det

## Se disse videoer før undervisningen:

Se kun de videoer, hvor du er usikker på emnet. Brug [selvtjekket](#selvtjek) herunder til at
finde ud af det.

* [variables](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=10m58s) (til: 00:31:30)
* [logical operators](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=3h9m58s) (til: 03:21:23)
* [for loops](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=3h43m33s) (til: 03:53:33)
* [methods](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=4h4m27s) (til: 04:19:51)
* [arrays](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=5h3m26s) (til: 05:12:35)
* [string methods](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=2h10m20s) (til: 02:18:55)

## Læs nedenstående før undervisningen

---

### Ugens projekt i IntelliJ

Lav **ét** IntelliJ-projekt til hele ugen: `uge46-repetition`. Lav det som et **Maven**-projekt
(som Filmsamlingen, se [del 0](../../projekter/filmsamling/del-0-github.md#1-én-person-opretter-projektet)),
så I kan skrive JUnit-tests onsdag. Tilføj JUnit til `pom.xml` med det samme – præcis som i
[del 5](../../projekter/filmsamling/del-5-test.md#junit-i-projektet).

Inde i `src/main/java` laver I én package pr. dag – `dag1_grundlaeggende`, `dag2_objektorienteret`
og `dag3_robust_persistent` – og én klasse pr. opgave (`Opgave01`, `Opgave02`, …), som I plejer.

---

### Datatyper og operatorer

| Type | Til | Eksempel |
| --- | --- | --- |
| `int` | hele tal | `int age = 21;` |
| `double` | kommatal | `double price = 49.95;` |
| `boolean` | sandt/falsk | `boolean done = false;` |
| `char` | ét tegn – **enkelte** anførselstegn | `char grade = 'A';` |
| `String` | tekst – **dobbelte** anførselstegn | `String name = "Anna";` |

**Heltalsdivision** er den klassiske fælde. Når begge tal er `int`, bliver resultatet `int` – og
alt efter kommaet smides væk:

```java
int a = 7;
int b = 2;
System.out.println(a / b);             // 3
System.out.println(a % b);             // 1 – resten
System.out.println((double) a / b);    // 3.5
```

`%` (modulo) giver resten ved division. Det bruges hele tiden: `number % 2 == 0` betyder "lige",
`seconds % 60` giver sekunderne, når minutterne er trukket fra.

---

### Betingelser

```java
if (score >= 90) {
    System.out.println("fremragende");
} else if (score >= 50) {
    System.out.println("bestået");
} else {
    System.out.println("ikke bestået");
}
```

En `else if`-kæde stopper ved den **første** betingelse, der er sand. Derfor er rækkefølgen
vigtig: tjek den mest specifikke først.

Når én værdi skal sammenlignes med mange faste værdier, er `switch` pænere:

```java
String day = switch (dayNumber) {
    case 1 -> "mandag";
    case 2 -> "tirsdag";
    case 6, 7 -> "weekend";
    default -> "en anden dag";
};
```

---

### Loops og de fire mønstre

| Loop | Brug det, når ... |
| --- | --- |
| `for (int i = 0; i < n; i++)` | du kender antallet – eller skal bruge `i` |
| `for (String name : names)` | du skal igennem **alle** elementer og ikke skal bruge indexet |
| `while (betingelse)` | du ikke ved, hvor mange gange – fx "indtil brugeren skriver noget gyldigt" |

Næsten al kode med loops er en variation af de [fire mønstre fra 04-09](../../36/05_fre_2026-09-04/README.md#de-fire-mønstre-du-skal-kunne-udenad):
**gennemløb**, **opsamling** (`sum += ...`), **tælling** (`if (...) count++`) og **søgning**
(`foundAt = -1` og `break`). Når du sidder med en opgave, så spørg: *hvilket af de fire mønstre er
det her?*

---

### Arrays og Strings

```java
int[] numbers = {5, 10, 15};
String text = "Java";

System.out.println(numbers.length);    // 3 – et felt, uden parenteser
System.out.println(text.length());     // 4 – en metode, med parenteser
System.out.println(numbers[0]);        // 5 – første index er 0
System.out.println(text.charAt(3));    // a – sidste index er length() - 1
```

De String-metoder, der oftest skal bruges: `length()`, `charAt(i)`, `substring(a, b)`,
`indexOf(s)`, `contains(s)`, `toUpperCase()`, `toLowerCase()`, `trim()`, `equals(s)`,
`equalsIgnoreCase(s)` og `split(";")` (fra [Filmsamling del 7](../../projekter/filmsamling/del-7-filer.md#filformatet)).
Oversigten står på [04-09](../../36/05_fre_2026-09-04/README.md#flere-nyttige-metoder).

---

### Metoder

```java
public static double average(int[] numbers) {
    int sum = 0;
    for (int number : numbers) {
        sum += number;
    }
    return (double) sum / numbers.length;
}
```

* **Parametre** er det, metoden får med ind. **Returtypen** er det, den giver tilbage – eller
  `void`, hvis den ikke giver noget.
* En metode, der **returnerer**, kan bruges til mere end en metode, der **udskriver**. Resultatet
  kan gemmes, sammenlignes, testes og vises på forskellige måder. Derfor skal de fleste metoder i
  opgaverne returnere – og `main` udskriver.
* Variable, der er erklæret **inde** i en metode, findes kun dér (*scope*).

---

### De klassiske fælder

| Fælde | Eksempel | I stedet |
| --- | --- | --- |
| Heltalsdivision | `7 / 2` giver `3` | `(double) 7 / 2` |
| Off-by-one | `i <= text.length()` → exception | `i < text.length()` |
| Tekst med `==` | `answer == "ja"` kan give `false` | `answer.equals("ja")` |
| Strings ændres ikke | `word.toUpperCase();` gør ingenting | `word = word.toUpperCase();` |
| `max` starter på 0 | forkert svar, når alle tal er negative | start med `numbers[0]` |
| Tekst og tal med `+` | `"Sum: " + 1 + 2` giver `Sum: 12` | parenteser: `"Sum: " + (1 + 2)` |
| `else if` i forkert rækkefølge | `score > 50` fanger også 90 | tjek det mest specifikke først |
| Tom `;` efter `if` | `if (x > 0); { ... }` kører altid | ingen `;` før `{` |

Den sidste er værd at se én gang:

```java
int x = -5;
if (x > 0); {
    System.out.println("positiv");    // skrives – semikolonet afsluttede if'en
}
```

---

### Selvtjek

Kan du svare **ja** til spørgsmålet, så spring emnet over i dag. Er du i tvivl, så læs dagen igen,
og start med de tilsvarende opgaver.

| Emne | Kan du ...? | Dag |
| --- | --- | --- |
| Variable og datatyper | forklare, hvorfor `7 / 2` er `3` | [26-08](../../35/03_ons_2026-08-26/README.md) |
| Betingelser | skrive en `else if`-kæde med `&&` og `\|\|` | [27-08](../../35/04_tor_2026-08-27/README.md) |
| Scanner | læse et tal og en tekst fra brugeren | [28-08](../../35/05_fre_2026-08-28/README.md) |
| Loops | skrive et `while` og et `for`, der gør det samme | [31-08](../../36/01_man_2026-08-31/README.md), [01-09](../../36/02_tir_2026-09-01/README.md) |
| Arrays | finde det største tal i et array med negative tal | [02-09](../../36/03_ons_2026-09-02/README.md) |
| Strings | vende en tekst om og tælle et bestemt tegn | [04-09](../../36/05_fre_2026-09-04/README.md) |
| Enum og switch | skrive en `switch` med `->` | [09-09](../../37/03_ons_2026-09-09/README.md) |
| Metoder | skrive en metode, der **returnerer** et resultat i stedet for at udskrive det | [10-09](../../37/04_tor_2026-09-10/README.md), [11-09](../../37/05_fre_2026-09-11/README.md) |
| Debugger | sætte et breakpoint og gå linje for linje | [14-09](../../38/01_man_2026-09-14/README.md) |

---

## Det vigtigste at tage med

* `int / int` er heltalsdivision – cast til `double`, når du vil have decimaler
* `array.length` uden parenteser, `text.length()` med – sidste index er længden minus 1
* tekst sammenlignes med `equals`, aldrig `==`
* String-metoder **returnerer** en ny tekst – de ændrer ikke den gamle
* de fire mønstre: gennemløb, opsamling, tælling, søgning
* metoder, der **returnerer**, kan genbruges og testes; lad `main` udskrive
* forudsig output **før** du kører koden – det er dér, du opdager, hvad du ikke forstår

## Aktiviteter i undervisningen

### 1. Forudsig output

Start med [opgaver.md](opgaver.md), **del A**, alene. Skriv dit gæt ned, før du kører koden.
Sammenlign derefter med sidemanden – hvor var I uenige?

### 2. Opgaver

Fortsæt med [opgaver.md](opgaver.md) i dit eget tempo:

* **del B** ★ – grundopgaver
* **del C** ★★ – lidt større
* **del D** ★★★ – som små eksamensopgaver: en kort beskrivelse, og du bestemmer selv, hvordan det
  skal løses

Der er [vejledende løsninger](loesninger.md), men prøv selv først. Sidder du fast i mere end ti
minutter, så kig på løsningen, luk den – og skriv koden selv bagefter.

### 3. Forklar det højt

Tag én af dine løsninger fra del C eller D, og forklar den linje for linje til sidemanden – uden at
se i løsningen. Det er præcis det, du skal kunne til eksamen.
