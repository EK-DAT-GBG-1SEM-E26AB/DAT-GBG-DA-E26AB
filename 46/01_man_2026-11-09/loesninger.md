# Vejledende løsninger – Repetition 1: Grundlæggende

Her er vejledende løsninger til [opgaver.md](opgaver.md). Alle programmer er kørt, og outputtet er
det, de faktisk skriver.

> **Vejledende** betyder: din kode må gerne se anderledes ud – der er mange rigtige løsninger. Det
> vigtige er, at den giver det rigtige resultat, og at du kan forklare hver linje.

Alle klasser ligger i package `dag1_grundlaeggende`. `package`-linjen er udeladt herunder.

---

# Del A – Forudsig output

## Opgave A1 – Division

```text
3
1
3.5
6.0
```

`7 / 2` er heltalsdivision: `3`. `7 % 2` er resten: `1`. Med `(double)` bliver `a` et kommatal
**før** divisionen, så resultatet bliver `3.5`. I den sidste linje sker `a / b` først (`3`), og
**derefter** ganges med `2.0` – så `6.0`, ikke `7.0`.

## Opgave A2 – Hvor mange gange?

```text
4
```

`i` er 10, 7, 4 og 1. Næste gang er `i` −2, og så er `i > 0` falsk.

## Opgave A3 – Strings

```text
java!
```

`word.toUpperCase()` laver en ny tekst, men den bliver ikke gemt. `word = word + "!"` gemmer
derimod resultatet.

## Opgave A4 – Tegn og index

```text
3 1 3
```

Der er tre `a`'er. `"an"` findes første gang på index 1 og sidste gang på index 3
(b**an**ana og ban**an**a).

## Opgave A5 – Parametre

```text
5
99
```

Java sender altid en **kopi** af værdien med. For `int` er værdien selve tallet, så `change` ændrer
sin egen kopi, og `x` er stadig `5`. For et array er værdien en **reference** til arrayet – kopien
peger på det **samme** array, så ændringen kan ses bagefter. Det er det samme, der sker, når I
sender et objekt, fx en `Movie`, med til en metode.

## Opgave A6 – Plus

```text
3345
```

`+` læses fra venstre: `1 + 2` er `3` (tal). `3 + "3"` er `"33"` (tekst). Derefter er alt tekst:
`"334"`, `"3345"`.

## Opgave A7 – Rækkefølgen

`bestået`. `"godt"` kan **aldrig** blive skrevet ud: alle tal over 70 er også over 50, så den
første betingelse fanger dem. Tjek det mest specifikke først:

```java
public class OpgaveA7 {

    public static void main(String[] args) {
        int[] scores = {95, 75, 60, 30};
        for (int score : scores) {
            System.out.println(score + ": " + evaluate(score));
        }
    }

    public static String evaluate(int score) {
        if (score > 70) {               // den mest specifikke først
            return "godt";
        } else if (score > 50) {
            return "bestået";
        } else {
            return "ikke bestået";
        }
    }
}
```

```text
95: godt
75: godt
60: bestået
30: ikke bestået
```

## Opgave A8 – Logik

```text
true
```

`&&` binder stærkere end `||`, så det læses `(sunny && !warm) || warm` = `(true && true) || false`
= `true`. Skriv parenteserne, når du blander `&&` og `||` – så er der ingen tvivl.

---

# Del B ★ – Grundopgaver

## Opgave 1 – Temperatur

```java
public class Opgave01 {

    public static void main(String[] args) {
        for (int celsius = 0; celsius <= 100; celsius += 20) {
            System.out.println(celsius + " °C = " + celsiusToFahrenheit(celsius) + " °F");
        }
    }

    public static double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;
    }
}
```

```text
0 °C = 32.0 °F
20 °C = 68.0 °F
40 °C = 104.0 °F
60 °C = 140.0 °F
80 °C = 176.0 °F
100 °C = 212.0 °F
```

`celsius` er en `int` i loopet, men bliver automatisk til `double`, når den sendes til metoden.
Derfor giver `celsius * 9 / 5` ingen heltalsdivision.

## Opgave 2 – FizzBuzz

```java
public class Opgave02 {

    public static void main(String[] args) {
        for (int i = 1; i <= 15; i++) {
            System.out.println(fizzBuzz(i));
        }
    }

    public static String fizzBuzz(int number) {
        if (number % 15 == 0) {      // både 3 og 5 – skal tjekkes først
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0) {
            return "Buzz";
        }
        return "" + number;
    }
}
```

Tjekket for 15 (begge dele) **skal** stå først – ellers fanger `% 3` tallet 15 og returnerer
`"Fizz"`. `"" + number` laver tallet om til tekst.

## Opgave 3 – Karakterstatistik

```java
public class Opgave03 {

    public static void main(String[] args) {
        int[] grades = {12, 7, 4, 10, 2, 7, -3};

        System.out.println("Laveste: " + min(grades));
        System.out.println("Højeste: " + max(grades));
        System.out.println("Gennemsnit: " + average(grades));
        System.out.println("Antal 7-taller: " + countOccurrences(grades, 7));
    }

    public static int min(int[] numbers) {
        int min = numbers[0];          // start med det første – ikke 0
        for (int number : numbers) {
            if (number < min) {
                min = number;
            }
        }
        return min;
    }

    public static int max(int[] numbers) {
        int max = numbers[0];
        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }
        return max;
    }

    public static double average(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return (double) sum / numbers.length;
    }

    public static int countOccurrences(int[] numbers, int value) {
        int count = 0;
        for (int number : numbers) {
            if (number == value) {
                count++;
            }
        }
        return count;
    }
}
```

```text
Laveste: -3
Højeste: 12
Gennemsnit: 5.571428571428571
Antal 7-taller: 2
```

`min` og `max` starter med det **første** tal i arrayet. Startede `max` på `0`, ville
`{-5, -12, -3}` give `0` – et tal, der slet ikke er i arrayet. Med `numbers[0]` som start giver den
`-12` og `-3`, som den skal.

## Opgave 4 – Vokaler

```java
public class Opgave04 {

    public static void main(String[] args) {
        System.out.println(countVowels("Erhvervsakademi København"));
        System.out.println(countVowels("xyz"));
        System.out.println(countVowels(""));
    }

    public static int countVowels(String text) {
        String vowels = "aeiouyæøå";
        String lower = text.toLowerCase();
        int count = 0;
        for (int i = 0; i < lower.length(); i++) {
            if (vowels.indexOf(lower.charAt(i)) != -1) {
                count++;
            }
        }
        return count;
    }
}
```

```text
9
1
0
```

`y` er en vokal på dansk, så `"xyz"` giver 1. `indexOf` findes også i en udgave, der tager et
`char`, så `vowels.indexOf(lower.charAt(i))` virker direkte.

## Opgave 5 – Palindrom

```java
public class Opgave05 {

    public static void main(String[] args) {
        System.out.println(isPalindrome("regninger"));
        System.out.println(isPalindrome("Anna"));
        System.out.println(isPalindrome("Java"));
        System.out.println(isPalindrome("x"));
    }

    public static boolean isPalindrome(String word) {
        String lower = word.toLowerCase();
        for (int i = 0; i < lower.length() / 2; i++) {
            if (lower.charAt(i) != lower.charAt(lower.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
```

```text
true
true
false
true
```

Loopet sammenligner det første tegn med det sidste, det andet med det næstsidste osv. – og behøver
kun at gå til midten. Så snart ét par er forskelligt, er svaret `false`. Ellers kunne man bygge den
omvendte tekst (som på [04-09](../../36/05_fre_2026-09-04/README.md#byg-en-string-op-i-et-loop)) og
sammenligne med `equals`.

---

# Del C ★★ – Lidt større

## Opgave 6 – Karakterer i ord

```java
public class Opgave06 {

    public static void main(String[] args) {
        int[] grades = {12, 10, 7, 4, 2, 0, -3, 5};
        for (int grade : grades) {
            System.out.println(grade + ": " + gradeToText(grade));
        }
    }

    public static String gradeToText(int grade) {
        return switch (grade) {
            case 12 -> "fremragende";
            case 10 -> "fortrinlig";
            case 7 -> "god";
            case 4 -> "jævn";
            case 2 -> "tilstrækkelig";
            case 0 -> "utilstrækkelig";
            case -3 -> "ringe";
            default -> "ugyldig karakter";
        };
    }
}
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

Karaktererne 02 og 00 skrives `2` og `0` i Java – et tal med `0` foran betyder noget andet (et
oktalt tal), så det skal man undgå.

## Opgave 7 – Vend et array

```java
import java.util.Arrays;

public class Opgave07 {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        int[] reversed = reverse(numbers);

        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(reversed));
        System.out.println(Arrays.toString(reverse(new int[0])));
    }

    // Returnerer et NYT array – det oprindelige ændres ikke
    public static int[] reverse(int[] numbers) {
        int[] result = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            result[i] = numbers[numbers.length - 1 - i];
        }
        return result;
    }
}
```

```text
[1, 2, 3, 4, 5]
[5, 4, 3, 2, 1]
[]
```

Nøglen er index-regningen: det første i `result` er det sidste i `numbers`, altså
`numbers.length - 1 - i`. Et tomt array giver et tomt array uden fejl, fordi loopet aldrig kører.

## Opgave 8 – Initialer

```java
public class Opgave08 {

    public static void main(String[] args) {
        System.out.println(initials("Hans Christian Andersen"));
        System.out.println(initials("karen blixen"));
        System.out.println(initials("Madonna"));
    }

    public static String initials(String fullName) {
        String[] names = fullName.split(" ");
        String result = "";
        for (String name : names) {
            result += name.charAt(0);
        }
        return result.toUpperCase();
    }
}
```

```text
HCA
KB
M
```

`result += name.charAt(0)` lægger et `char` til en `String` – det giver en ny `String`.
`toUpperCase()` til sidst klarer både `"karen blixen"` og `"Karen Blixen"`.

## Opgave 9 – Det længste ord

```java
public class Opgave09 {

    public static void main(String[] args) {
        String sentence = "Programmering er sjovt når koden kompilerer";
        System.out.println(longestWord(sentence));
        System.out.println(countWords(sentence));
        System.out.println(longestWord("en to ti"));
    }

    public static String longestWord(String sentence) {
        String[] words = sentence.split(" ");
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) {   // > og ikke >= : ved lige lange vinder den første
                longest = word;
            }
        }
        return longest;
    }

    public static int countWords(String sentence) {
        return sentence.split(" ").length;
    }
}
```

```text
Programmering
6
en
```

Med `>=` i stedet for `>` ville det **sidste** af de lige lange ord vinde (`"ti"`).

## Opgave 10 – Unikke linjer

```java
import java.util.ArrayList;

public class Opgave10 {

    public static void main(String[] args) {
        String[] lines = {"hej", "med", "hej", "dig", "med", "hej"};
        System.out.println(countUnique(lines));
        System.out.println(countUniqueWithoutList(lines));
    }

    public static int countUnique(String[] lines) {
        ArrayList<String> seen = new ArrayList<>();
        for (String line : lines) {
            if (!seen.contains(line)) {
                seen.add(line);
            }
        }
        return seen.size();
    }

    // Uden ArrayList: en linje er "ny", hvis den ikke står tidligere i arrayet
    public static int countUniqueWithoutList(String[] lines) {
        int count = 0;
        for (int i = 0; i < lines.length; i++) {
            boolean seenBefore = false;
            for (int j = 0; j < i; j++) {
                if (lines[j].equals(lines[i])) {
                    seenBefore = true;
                }
            }
            if (!seenBefore) {
                count++;
            }
        }
        return count;
    }
}
```

```text
3
3
```

Versionen uden liste tæller en linje, hvis den **ikke** står tidligere i arrayet – det indre loop
går kun op til `i`. Læg mærke til `equals`: med `==` kunne svaret blive forkert.

---

# Del D ★★★ – Små eksamensopgaver

## Opgave 11 – Bruger-id

```java
public class Opgave11 {

    public static void main(String[] args) {
        String[] tests = {"abcd1234", "ABCD1234", "abc1234", "abcd12345", "ab1d1234", "kage2026"};
        for (String userId : tests) {
            System.out.println(userId + ": " + isValidUserId(userId));
        }
    }

    // Gyldigt: præcis fire små bogstaver a-z efterfulgt af præcis fire cifre
    public static boolean isValidUserId(String userId) {
        if (userId.length() != 8) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            char c = userId.charAt(i);
            if (c < 'a' || c > 'z') {
                return false;
            }
        }
        for (int i = 4; i < 8; i++) {
            char c = userId.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
```

```text
abcd1234: true
ABCD1234: false
abc1234: false
abcd12345: false
ab1d1234: false
kage2026: true
```

Længden tjekkes **først**. Ellers ville `charAt(7)` give en `StringIndexOutOfBoundsException` for
fx `"abcd123"` (kun syv tegn), og `"abcd12345"` ville blive godkendt, fordi ingen kigger på det
niende tegn. Metoden returnerer `false`, så snart ét tegn er forkert – der er ingen grund til at
kigge videre.

## Opgave 12 – Kodeord

```java
public class Opgave12 {

    public static void main(String[] args) {
        String[] passwords = {"kode", "kodeord123", "Kodeord123", "Kort1", "LANGTKODEORD"};
        for (String password : passwords) {
            System.out.println(password + ": " + checkPassword(password));
        }
    }

    public static String checkPassword(String password) {
        if (password.length() < 8) {
            return "for kort";
        }
        boolean hasDigit = false;
        boolean hasUpper = false;
        boolean hasLower = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (c >= '0' && c <= '9') {
                hasDigit = true;
            } else if (c >= 'A' && c <= 'Z') {
                hasUpper = true;
            } else if (c >= 'a' && c <= 'z') {
                hasLower = true;
            }
        }
        if (!hasDigit) {
            return "mangler et tal";
        }
        if (!hasUpper || !hasLower) {
            return "mangler store og små bogstaver";
        }
        return "ok";
    }
}
```

```text
kode: for kort
kodeord123: mangler store og små bogstaver
Kodeord123: ok
Kort1: for kort
LANGTKODEORD: mangler et tal
```

Tre `boolean`-variable, der starter som `false` og sættes til `true`, når noget bliver fundet – det
er søgemønsteret tre gange i ét loop.

## Opgave 13 – Mønstre med loops

```java
public class Opgave13 {

    public static void main(String[] args) {
        printTable(5);
        System.out.println();
        printTriangle(4);
    }

    public static void printTable(int size) {
        for (int row = 1; row <= size; row++) {
            String line = "";
            for (int column = 1; column <= size; column++) {
                int product = row * column;
                if (product < 10) {
                    line += " ";            // så tallene står under hinanden
                }
                line += " " + product;
            }
            System.out.println(line);
        }
    }

    public static void printTriangle(int height) {
        for (int row = 1; row <= height; row++) {
            String line = "";
            for (int i = 0; i < height - row; i++) {
                line += " ";
            }
            for (int i = 0; i < 2 * row - 1; i++) {
                line += "*";
            }
            System.out.println(line);
        }
    }
}
```

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

I trekanten har række nr. `row` først `height - row` mellemrum og derefter `2 * row - 1` stjerner.
Den slags formler finder man lettest ved at skrive en lille tabel på papir: række 1 → 3 mellemrum
og 1 stjerne, række 2 → 2 og 3, osv.
