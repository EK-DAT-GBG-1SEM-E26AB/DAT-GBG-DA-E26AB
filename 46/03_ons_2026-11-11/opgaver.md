# Opgaver – Repetition 3: Robust og persistent

Opgaverne er delt op sådan:

* **Del A** – forudsig output
* **Del B** ★ – exceptions, én fil, én testklasse
* **Del C** ★★ – datoer, sortering, CSV og tests
* **Del D** ★★★ – blandede eksamensopgaver
* **Udfordring** – din egen checked exception

Lav opgaverne i projektet `uge46-repetition`, package `dag3_robust_persistent`, med én
under-package pr. opgave, fx `dag3_robust_persistent.bank`. **Testene** ligger i `src/test/java` i en
package med samme navn. Har du ikke JUnit i `pom.xml` endnu, så se
[mandagens README](../01_man_2026-11-09/README.md#ugens-projekt-i-intellij).

Filer, som programmerne skriver og læser, havner i projektets rodmappe – der, hvor `pom.xml`
ligger.

Der er [vejledende løsninger](loesninger.md) – men prøv selv først.

---

# Del A – Forudsig output

> **Skriv dit svar ned, før du kører koden.**

## Opgave A1 – try og catch

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

(Den står også i README'en – dæk den til, og prøv alligevel.)

## Opgave A2 – Hvilken exception?

Hvilken exception giver hver linje – eller giver den ingen? Skriv navnet, før du prøver.

| # | Kode |
| --- | --- |
| 1 | `Integer.parseInt("3.5")` |
| 2 | `"abc".charAt(5)` |
| 3 | `new ArrayList<String>().get(0)` |
| 4 | `String s = null; s.length();` |
| 5 | `int[] a = new int[3]; a[3] = 1;` |
| 6 | `System.out.println(10 / 0);` |
| 7 | `LocalDate.of(2026, 2, 30)` |
| 8 | `LocalDate.parse("30-02-2026")` |
| 9 | `LocalDate.parse("31-11-2026", DateTimeFormatter.ofPattern("dd-MM-yyyy"))` |
| 10 | `System.out.println(10.0 / 0);` |

Hvilke af dem skyldes en fejl i koden, som en `if` burde have forhindret?

## Opgave A3 – throw

```java
public class OpgaveA3 {

    public static void main(String[] args) {
        try {
            System.out.println("før");
            checkAge(-4);
            System.out.println("efter");
        } catch (IllegalArgumentException e) {
            System.out.println("fanget: " + e.getMessage());
        }
        System.out.println("slut");
    }

    public static void checkAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Alder kan ikke være negativ: " + age);
        }
        System.out.println("alder ok");
    }
}
```

## Opgave A4 – Fortegn

`true` eller `false` – og hvad giver linje 2?

```java
System.out.println("Bo".compareTo("Anna") > 0);
System.out.println(Integer.compare(5, 5));
System.out.println("anna".compareTo("Bo") < 0);
System.out.println("anna".compareToIgnoreCase("Bo") < 0);
```

## Opgave A5 – Datoer

```java
LocalDate start = LocalDate.of(2026, 11, 11);
LocalDate end = start.plusDays(30);
start.plusDays(1);
System.out.println(start);
System.out.println(end);
System.out.println(end.isAfter(start));
System.out.println(ChronoUnit.DAYS.between(start, LocalDate.of(2026, 12, 24)));
```

---

# Del B ★ – Exceptions, én fil, én testklasse

## Opgave 1 – Spørg igen

Skriv `int readInt(Scanner scanner, String prompt, int min, int max)`, der spørger igen og igen,
indtil brugeren skriver et helt tal mellem `min` og `max`. Ved bogstaver skriver den, at det ikke
er et tal. Ved et tal uden for grænserne skriver den grænserne.

For at slippe for at taste forkert input hver gang kan en `Scanner` læse fra en **tekst** i stedet
for tastaturet. `\n` er et tryk på Enter:

```java
Scanner scanner = new Scanner("sytten\n\n-3\n17\n");
int age = readInt(scanner, "Alder: ", 0, 120);
System.out.println("Du er " + age + " år.");
```

Fordi input ikke kommer fra tastaturet, kan `readInt` skrive det læste ud, så kørslen ligner en
rigtig kørsel:

```text
Alder: sytten
"sytten" er ikke et helt tal. Prøv igen.
Alder: 
"" er ikke et helt tal. Prøv igen.
Alder: -3
Tallet skal være mellem 0 og 120.
Alder: 17
Du er 17 år.
```

## Opgave 2 – Bankkonto

1. Lav en klasse `BankAccount` med ejer og saldo (starter på 0).
2. `deposit(int amount)` og `withdraw(int amount)` kaster en `IllegalArgumentException` med en
   dansk besked, hvis beløbet er 0 eller negativt – og `withdraw` også, hvis der ikke er penge nok.
3. I `main`: indsæt 500, og prøv at hæve 200, −50, 400 og 300. Fang exceptionen, og vis beskeden.

```text
Hævet 200 kr. Sara: 300 kr.
Afvist: Beløbet skal være positivt: -50
Afvist: Der er kun 300 kr. på kontoen
Hævet 300 kr. Sara: 0 kr.
```

4. Skriv en testklasse `BankAccountTest` med `@BeforeEach`, der opretter en konto med 500 kr.
   Test mindst:
   * at `withdraw(200)` giver saldoen 300
   * at man godt må hæve **alt** (grænsen)
   * at `withdraw(501)` bliver afvist – og at saldoen **stadig** er 500 bagefter
   * at `deposit(0)` og `deposit(-1)` bliver afvist

## Opgave 3 – Gem og indlæs linjer

Skriv to metoder:

* `saveLines(ArrayList<String> lines, String fileName)` – skriver hver tekst som én linje
* `ArrayList<String> loadLines(String fileName)` – læser linjerne igen

Begge sender `FileNotFoundException` videre med `throws`. `main` fanger den.

Gem en huskeliste i `todo.txt`, læs den igen, og udskriv den. Prøv til sidst at læse
`findes-ikke.txt`:

```text
Indlæst 3 linjer:
- Køb mælk
- Aflevér Filmsamling
- Læs op på interfaces
Filen findes ikke: findes-ikke.txt (No such file or directory)
```

(Teksten i parentesen kommer fra styresystemet og står på dansk eller engelsk alt efter din
computer.) Åbn `todo.txt` i IntelliJ, og se, hvordan den ser ud.

---

# Del C ★★ – Datoer, sortering, CSV og tests

## Opgave 4 – Drømmedagbog

1. Lav en enum `DreamType` med `PROBLEM_SOLVING`, `NEUTRAL` og `NIGHTMARE`.
2. Lav en klasse `Dream` med en dato (`LocalDate`), en varighed i minutter og en `DreamType`.
   Constructoren afviser en varighed under 1 minut.
3. `boolean isPleasant()`: et mareridt er **aldrig** behageligt. En problemløsende drøm kun, hvis
   den er **kortere** end 10 minutter. En neutral drøm kun, hvis den er **længere** end 10
   minutter.
4. Lad `Dream` implementere `Comparable<Dream>`, så drømmene sorteres efter dato, ældste først.
5. Lav en `DurationComparator`, der sorterer efter varighed, korteste først.

```java
dreams.add(new Dream(LocalDate.of(2026, 11, 3), 12, DreamType.NEUTRAL));
dreams.add(new Dream(LocalDate.of(2026, 10, 28), 4, DreamType.PROBLEM_SOLVING));
dreams.add(new Dream(LocalDate.of(2026, 11, 9), 7, DreamType.NIGHTMARE));
dreams.add(new Dream(LocalDate.of(2026, 11, 1), 10, DreamType.NEUTRAL));
```

```text
--- Efter dato ---
2026-10-28 PROBLEM_SOLVING 4 min – behagelig
2026-11-01 NEUTRAL 10 min
2026-11-03 NEUTRAL 12 min – behagelig
2026-11-09 NIGHTMARE 7 min
--- Efter varighed ---
2026-10-28 PROBLEM_SOLVING 4 min
2026-11-09 NIGHTMARE 7 min
2026-11-01 NEUTRAL 10 min
2026-11-03 NEUTRAL 12 min
Afvist: Varigheden skal være mindst 1 minut
```

6. Skriv `DreamTest`. Reglen om "under 10" og "over 10" skriger på **grænsetests**: hvad med
   præcis 10 minutter? Test også `compareTo` og en varighed på 0.

## Opgave 5 – Lagerfil

Opret filen `products.csv` i projektets rodmappe med dette indhold – fejlene er med vilje:

```text
name;price;stock
Kaffe;45;12
Te;30;fem
Kakao;35;4
Småkager;25
;20;3
Chai;40;-2
```

1. Lav en klasse `Product` med navn, pris og antal på lager. Constructoren afviser et tomt navn og
   negative tal. `getStockValue()` returnerer pris × antal.
2. Lav en `ProductFileHandler` med `ArrayList<Product> loadProducts(String fileName)`. Den springer
   overskriften over, laver hver linje om til et `Product` – og **springer** ødelagte linjer over
   og tæller dem.
3. Udskriv produkterne, den samlede lagerværdi og antallet af linjer, der blev sprunget over:

```text
Kaffe: 12 stk. à 45 kr.
Kakao: 4 stk. à 35 kr.
Lagerværdi: 680 kr.
Sprunget over: 4 linjer
```

Hvorfor blev hver af de fire linjer sprunget over?

## Opgave 6 – Medier til fil

Tag medierne fra [i går (opgave 7)](../02_tir_2026-11-10/opgaver.md#opgave-7--medier), og skriv
info om hvert medie som én linje i filen `mediainfo.txt`.

Klasserne ligger i en **anden** package (`dag2_objektorienteret.medier`). Brug dem **uden** at
kopiere dem – med `import`. Hvad skal der til, for at det virker?

```text
Gemt 4 medier i mediainfo.txt
```

Og i filen:

```text
Podcast: Java på 10 minutter [10:05] lyd, -16.0 dB
Introfilm [1:34] video, 16:9
Jingle [0:07] lyd, -10.4 dB
Gammel reklame [0:30] video, 4:3
```

---

# Del D ★★★ – Blandede eksamensopgaver

## Opgave 7 – Biblioteket

> Et lille bibliotek vil holde styr på sine udlån. Et lån har en **titel**, en **låner** og en
> **afleveringsdato**. Et lån er **overskredet**, når dagen er efter afleveringsdatoen. Et
> overskredet lån koster **5 kr. pr. dag**, dog højst **100 kr.** Biblioteket vil kunne se alle
> overskredne lån – det, der skulle have været afleveret først, øverst – og den samlede sum af
> gebyrer. Lånene skal gemmes i en fil, så de ikke forsvinder, når programmet lukkes.

Byg det. Et forslag til klasser: `Loan`, `LoanRegistry` og `LoanFileHandler`. Dagens dato skal
være en **parameter** til de metoder, der skal bruge den.

Med disse lån og datoen 11-11-2026:

```java
registry.addLoan(new Loan("Kaptajn Klo", "Ida", LocalDate.of(2026, 11, 20)));
registry.addLoan(new Loan("Java for begyndere", "Omar", LocalDate.of(2026, 11, 4)));
registry.addLoan(new Loan("Ternet Ninja", "Sara", LocalDate.of(2026, 10, 1)));
registry.addLoan(new Loan("Stuart Little", "Ida", LocalDate.of(2026, 11, 11)));
```

```text
Overskredne lån:
Ternet Ninja (Sara), afleveres 2026-10-01 – gebyr 100 kr.
Java for begyndere (Omar), afleveres 2026-11-04 – gebyr 35 kr.
Gebyrer i alt: 135 kr.
Indlæst igen: 4 lån
```

Skriv tests for gebyret: på afleveringsdagen, dagen efter, og omkring grænsen på 100 kr. Og en
test, der gemmer to lån – det ene med æ, ø og å – og læser dem igen. Testen skal bruge sin **egen**
fil og slette den bagefter.

> Tegn klassediagrammet først. Hvem har listen? Hvem har reglen om gebyret? Hvem ved noget om filen?

---

# Udfordring – Din egen checked exception

Lav en `InsufficientFundsException`, der arver fra `Exception` (ikke `RuntimeException`), og en
klasse `Account`, hvis `withdraw` kaster den, når der ikke er penge nok.

1. Hvad siger compileren, hvis du kalder `withdraw` uden `try`/`catch` og uden `throws`?
2. Skriv en `main`, der fanger den:

```text
Afvist: Mangler 50 kr.
Saldo: 200
```

(Kontoen starter med 300 kr.; der hæves først 100 og så 250.)

3. Diskutér med sidemanden: hvornår er en **checked** exception det rigtige valg – og hvornår er
   den bare besværlig?
