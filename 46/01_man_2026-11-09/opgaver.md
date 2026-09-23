# Opgaver – Repetition 1: Grundlæggende

Opgaverne er delt op sådan:

* **Del A** – forudsig output. Ingen kode at skrive, kun at læse og tænke.
* **Del B** ★ – grundopgaver: én metode ad gangen
* **Del C** ★★ – lidt større opgaver
* **Del D** ★★★ – små eksamensopgaver: en kort beskrivelse, og du bestemmer selv, hvordan det skal
  løses

Lav opgaverne i projektet `uge46-repetition`, package `dag1_grundlaeggende`, med én klasse pr.
opgave (se [README](README.md#ugens-projekt-i-intellij)).

**Regel for hele dagen:** skriv logikken i en `static`-metode, der **returnerer** resultatet, og
lad `main` udskrive det. Så kan metoden genbruges – og testes onsdag.

Der er [vejledende løsninger](loesninger.md) – men prøv selv først.

---

# Del A – Forudsig output

> **Skriv dit svar ned, før du kører koden.** Det er hele øvelsen.

## Opgave A1 – Division

```java
int a = 7;
int b = 2;
System.out.println(a / b);
System.out.println(a % b);
System.out.println((double) a / b);
System.out.println(a / b * 2.0);
```

## Opgave A2 – Hvor mange gange?

```java
int count = 0;
for (int i = 10; i > 0; i -= 3) {
    count++;
}
System.out.println(count);
```

## Opgave A3 – Strings

```java
String word = "java";
word.toUpperCase();
word = word + "!";
System.out.println(word);
```

## Opgave A4 – Tegn og index

```java
String text = "banana";
int n = 0;
for (int i = 0; i < text.length(); i++) {
    if (text.charAt(i) == 'a') {
        n++;
    }
}
System.out.println(n + " " + text.indexOf("an") + " " + text.lastIndexOf("an"));
```

## Opgave A5 – Parametre

```java
public class OpgaveA5 {

    public static void main(String[] args) {
        int x = 5;
        change(x);
        System.out.println(x);

        int[] numbers = {5, 5, 5};
        change(numbers);
        System.out.println(numbers[0]);
    }

    public static void change(int number) {
        number = 99;
    }

    public static void change(int[] numbers) {
        numbers[0] = 99;
    }
}
```

Hvorfor bliver de to udskrifter ikke ens?

## Opgave A6 – Plus

```java
System.out.println(1 + 2 + "3" + 4 + 5);
```

## Opgave A7 – Rækkefølgen

```java
int score = 75;
if (score > 50) {
    System.out.println("bestået");
} else if (score > 70) {
    System.out.println("godt");
} else {
    System.out.println("ikke bestået");
}
```

Kan `"godt"` overhovedet blive skrevet ud? Ret koden, så en score over 70 giver `"godt"`.

## Opgave A8 – Logik

```java
boolean sunny = true;
boolean warm = false;
System.out.println(sunny && !warm || warm);
```

---

# Del B ★ – Grundopgaver

## Opgave 1 – Temperatur

Skriv en metode `double celsiusToFahrenheit(double celsius)`. Formlen er `F = C × 9 / 5 + 32`.
Brug den i et `for`-loop, der udskriver en tabel fra 0 til 100 grader i spring på 20:

```text
0 °C = 32.0 °F
20 °C = 68.0 °F
40 °C = 104.0 °F
60 °C = 140.0 °F
80 °C = 176.0 °F
100 °C = 212.0 °F
```

## Opgave 2 – FizzBuzz

Skriv en metode `String fizzBuzz(int number)`, der returnerer `"Fizz"`, hvis tallet går op i 3,
`"Buzz"`, hvis det går op i 5, `"FizzBuzz"`, hvis det går op i begge – og ellers tallet selv som
tekst. Udskriv resultatet for 1 til 15:

```text
1
2
Fizz
4
Buzz
Fizz
7
8
Fizz
Buzz
11
Fizz
13
14
FizzBuzz
```

## Opgave 3 – Karakterstatistik

```java
int[] grades = {12, 7, 4, 10, 2, 7, -3};
```

Skriv fire metoder, der hver tager et `int[]`: `min`, `max`, `average` (returnerer `double`) og
`countOccurrences(int[] numbers, int value)`.

```text
Laveste: -3
Højeste: 12
Gennemsnit: 5.571428571428571
Antal 7-taller: 2
```

Virker `min` og `max`, hvis **alle** tallene er negative? Prøv med `{-5, -12, -3}`.

## Opgave 4 – Vokaler

Skriv `int countVowels(String text)`, der tæller vokalerne (a, e, i, o, u, y, æ, ø, å) – både
store og små.

```java
System.out.println(countVowels("Erhvervsakademi København"));   // 9
System.out.println(countVowels("xyz"));                         // 1
System.out.println(countVowels(""));                            // 0
```

> **Tip:** `"aeiouyæøå".indexOf(c)` giver `-1`, hvis tegnet `c` **ikke** er en vokal.

## Opgave 5 – Palindrom

Et palindrom er et ord, der staves ens forfra og bagfra. Skriv `boolean isPalindrome(String word)`,
der er ligeglad med store og små bogstaver.

```java
System.out.println(isPalindrome("regninger"));   // true
System.out.println(isPalindrome("Anna"));        // true
System.out.println(isPalindrome("Java"));        // false
System.out.println(isPalindrome("x"));           // true
```

Kan du gøre det **uden** at bygge en omvendt kopi af ordet?

---

# Del C ★★ – Lidt større

## Opgave 6 – Karakterer i ord

Skriv `String gradeToText(int grade)` med en `switch`, der oversætter 7-trinsskalaen: 12
fremragende, 10 fortrinlig, 7 god, 4 jævn, 02 tilstrækkelig, 00 utilstrækkelig, −3 ringe – og
`"ugyldig karakter"` for alt andet.

```java
int[] grades = {12, 10, 7, 4, 2, 0, -3, 5};
```

```text
12: fremragende
10: fortrinlig
7: god
4: jævn
2: tilstrækkelig
0: utilstrækkelig
-3: ringe
5: ugyldig karakter
```

## Opgave 7 – Vend et array

Skriv `int[] reverse(int[] numbers)`, der returnerer et **nyt** array med tallene i omvendt
rækkefølge. Det oprindelige array må ikke ændres.

```java
int[] numbers = {1, 2, 3, 4, 5};
int[] reversed = reverse(numbers);
System.out.println(Arrays.toString(numbers));
System.out.println(Arrays.toString(reversed));
System.out.println(Arrays.toString(reverse(new int[0])));
```

```text
[1, 2, 3, 4, 5]
[5, 4, 3, 2, 1]
[]
```

`Arrays.toString` (fra `java.util.Arrays`) laver et array om til pæn tekst.

## Opgave 8 – Initialer

Skriv `String initials(String fullName)`, der returnerer forbogstaverne med store bogstaver. Brug
`split(" ")`.

```java
System.out.println(initials("Hans Christian Andersen"));   // HCA
System.out.println(initials("karen blixen"));              // KB
System.out.println(initials("Madonna"));                   // M
```

## Opgave 9 – Det længste ord

Skriv `String longestWord(String sentence)` og `int countWords(String sentence)`. Er to ord lige
lange, vinder det første.

```java
String sentence = "Programmering er sjovt når koden kompilerer";
System.out.println(longestWord(sentence));      // Programmering
System.out.println(countWords(sentence));       // 6
System.out.println(longestWord("en to ti"));    // en
```

## Opgave 10 – Unikke linjer

Skriv `int countUnique(String[] lines)`, der returnerer, hvor mange **forskellige** tekster der er.

```java
String[] lines = {"hej", "med", "hej", "dig", "med", "hej"};
System.out.println(countUnique(lines));   // 3
```

Lav den først med en `ArrayList` og `contains`. Lav den derefter **uden** – kun med arrayet og to
loops inden i hinanden.

---

# Del D ★★★ – Små eksamensopgaver

Her er beskrivelsen kort, som til eksamen. Find selv ud af, hvilke metoder du skal bruge.

## Opgave 11 – Bruger-id

Et bruger-id er gyldigt, hvis det består af **præcis fire små bogstaver** (a–z) efterfulgt af
**præcis fire cifre**. Skriv `boolean isValidUserId(String userId)`.

```text
abcd1234: true
ABCD1234: false
abc1234: false
abcd12345: false
ab1d1234: false
kage2026: true
```

> **Tip:** tegn kan sammenlignes med `<` og `>`: `c >= 'a' && c <= 'z'` er sand for de små
> bogstaver a–z.

## Opgave 12 – Kodeord

Skriv `String checkPassword(String password)`, der returnerer:

* `"for kort"`, hvis kodeordet er under 8 tegn
* `"mangler et tal"`, hvis der ikke er mindst ét ciffer
* `"mangler store og små bogstaver"`, hvis der ikke er både store og små bogstaver (A–Z og a–z)
* `"ok"` ellers

Reglerne tjekkes i den rækkefølge.

```text
kode: for kort
kodeord123: mangler store og små bogstaver
Kodeord123: ok
Kort1: for kort
LANGTKODEORD: mangler et tal
```

## Opgave 13 – Mønstre med loops

Skriv to metoder med loops inden i loops:

* `printTable(int size)` – den lille tabel: hvert tal højrestillet i to tegn, med et mellemrum foran
* `printTriangle(int height)` – en trekant af stjerner

```text
  1  2  3  4  5
  2  4  6  8 10
  3  6  9 12 15
  4  8 12 16 20
  5 10 15 20 25

   *
  ***
 *****
*******
```

(Her er det i orden, at metoderne udskriver – det er selve opgaven.)

## Udfordring – CodingBat

Mangler du flere opgaver, så har [CodingBat](https://codingbat.com/java) hundredvis af små
Java-opgaver med automatisk retning. Start med *String-1*, *Array-1* og *Logic-1*.
