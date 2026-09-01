# Variable, datatyper og aritmetiske operatorer

## Beskrivelse

I denne lektion arbejder vi med variable, datatyper og aritmetiske operatorer i Java. Du lærer at gemme og ændre værdier, vælge passende datatyper og udføre simple beregninger med operatorer som +, -, *, /, %, ++ og --.

## Læringsmål
- primitive datatyper
- artimetiske operatorer
- Strings 


## Se disse videoer før undervisningen:  
[Variables](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=10m58s) (til: 00:31:30)  
[Aritmetiske operatorer](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=54m8s) (til: 01:02:29 )  


## Læs nedenstående før undervisningen
I dag skal vi arbejde med **variable og datatyper i Java**.  
Afprøv gerne eksemplerne i IntelliJ.
Start med at oprette et nyt Java-projekt i IntelliJ.

Hvis du allerede har et projekt til undervisningen, kan du også bruge dette.

Opret en ny Java-klasse med navnet:

```text
Main
```

Din klasse skal se sådan ud:

```java
public class Main {

}
```

Inde i klassen skal du oprette en `main`-metode:

```java
public class Main {

    public static void main(String[] args) {

    }
}
```

Det er inde i `main`-metoden, du skal skrive og afprøve dine løsninger.

Eksempel:

```java
public class Main {

    public static void main(String[] args) {

        int age = 25;

        System.out.println(age);
    }
}
```

Når du kører programmet, bliver resultatet vist i konsollen nederst i IntelliJ.





---

### Variable
En variabel kan betragtes som en lille navngivet plads i computerens hukommelse, hvor vi kan gemme en værdi.

For eksempel:

```java
int age = 25;
```

Her sker der tre ting:

* `int` angiver hvilken **datatype** variablen har
* `age` er variablens **navn**
* `25` er den **værdi**, der gemmes i variablen

Vi kan efterfølgende bruge variablen:

```java
System.out.println(age);
```

Output:

```text
25
```

---

### Variable kan ændre værdi

Navnet *variabel* kommer af, at værdien kan variere.

```java
int age = 25;

System.out.println(age);

age = 26;

System.out.println(age);
```

Output:

```text
25
26
```

Når variablen allerede er oprettet, skriver vi ikke datatypen igen:

```java
int age = 25;

age = 26;
```

Ikke:

```java
int age = 25;

int age = 26;   // fejl
```

---

## Datatyper

Java skal vide, hvilken slags data en variabel skal indeholde.

Nogle af de mest almindelige datatyper er:

| Datatype  | Bruges til | Eksempel  |
| --------- | ---------- | --------- |
| `int`     | hele tal   | `42`      |
| `double`  | decimaltal | `12.5`    |
| `boolean` | sand/falsk | `true`    |
| `char`    | ét tegn    | `'A'`     |
| `String`  | tekst      | `"Hello"` |

Vi ser nærmere på dem nedenfor.

---

## `int` – hele tal

Datatypen `int` bruges til hele tal.

```java
int age = 23;
int numberOfStudents = 28;
int temperature = -4;
```

En `int` kan både være positiv, negativ eller nul.

```java
int a = 10;
int b = -5;
int c = 0;
```

Vi kan også regne med variable:

```java
int price = 20;
int number = 3;

int totalPrice = price * number;

System.out.println(totalPrice);
```

Output:

```text
60
```


---

## `double` – decimaltal

Hvis vi vil arbejde med tal med decimaler, kan vi bruge `double`.

```java
double height = 1.82;
double price = 19.95;
double temperature = 21.5;
```

Bemærk, at Java bruger **punktum** som decimaltegn:

```java
double price = 19.95;
```

og ikke:

```java
double price = 19,95;
```

Vi kan også regne med `double`:

```java
double price = 12.50;
int number = 4;

double totalPrice = price * number;

System.out.println(totalPrice);
```

Output:

```text
50.0
```

---
## Flere datatyper til tal: `long` og `float`

Indtil videre har vi brugt:

```java
int age = 25;
double price = 19.95;
```

I de fleste almindelige Java-programmer er:

* `int` et godt standardvalg til **hele tal**
* `double` et godt standardvalg til **decimaltal**

Java har dog også andre numeriske datatyper. To af dem er `long` og `float`.

---

## `long` – meget store hele tal

En `int` kan kun indeholde tal inden for et bestemt interval.

Den største værdi, en `int` kan indeholde, er cirka **2,1 milliarder**.

Hvis vi skal arbejde med større hele tal, kan vi bruge `long`.

```java
long population = 8_100_000_000L;
```

Bemærk `L` efter tallet:

```java
8100000000L
```

Det fortæller Java, at tallet skal behandles som en `long`.

Underscores `_` kan bruges til at gøre store tal lettere at læse:

```java
long population = 8_100_000_000L;
```

Det svarer til:

```java
long population = 8100000000L;
```

### Hvornår bruger man `long`?

Brug typisk `long`, når du ved, at et helt tal kan blive større end det, en `int` kan indeholde.

Eksempler kunne være:

```java
long numberOfViews = 5_600_000_000L;
long distanceInMeters = 12_000_000_000L;
```

Til almindelige mindre hele tal bruger vi normalt stadig `int`:

```java
int age = 25;
int numberOfStudents = 30;
int numberOfBooks = 120;
```

---

## `float` – decimaltal med mindre præcision

Java har også datatypen `float` til decimaltal.

Eksempel:

```java
float temperature = 21.5F;
```

Bemærk `F` efter tallet.

Uden `F` opfatter Java normalt et decimaltal som en `double`.

Derfor virker dette:

```java
double temperature = 21.5;
```

mens en `float` typisk skrives:

```java
float temperature = 21.5F;
```

---

## `float` eller `double`?

Både `float` og `double` kan gemme decimaltal.

Forskellen er især, hvor præcist tallet kan gemmes.

`double` har større præcision end `float`.

For eksempel:

```java
float number1 = 1.23456789F;
double number2 = 1.23456789;

System.out.println(number1);
System.out.println(number2);
```

Du vil kunne opleve, at `float` ikke kan bevare lige så mange decimaler præcist som `double`.

Derfor bruger vi normalt:

```java
double
```

til decimaltal i vores programmer.

Brug kun `float`, hvis der er en særlig grund til det.

---

## Hvilken datatype skal jeg vælge?

Som udgangspunkt kan du bruge denne tommelfingerregel:

| Jeg vil gemme...                                      | Typisk datatype |
| ----------------------------------------------------- | --------------- |
| Et almindeligt helt tal                               | `int`           |
| Et meget stort helt tal                               | `long`          |
| Et decimaltal                                         | `double`        |
| Et decimaltal, hvor mindre præcision er tilstrækkelig | `float`         |

Eksempler:

```java
int age = 24;

long numberOfViews = 4_500_000_000L;

double height = 1.82;

float temperature = 21.5F;
```

På dette kursus vil du i langt de fleste tilfælde kunne bruge **`int` til hele tal og `double` til decimaltal**.

Det vigtigste er derfor ikke at kunne huske alle datatyper udenad, men at forstå, at valget af datatype afhænger af, **hvilken slags værdi vi vil gemme**.

---

## Heltalsdivision

Der er en vigtig forskel på `int` og `double`.

Se dette eksempel:

```java
int result = 5 / 2;

System.out.println(result);
```

Hvad tror du resultatet bliver?

Det bliver:

```text
2
```

Begge tal er hele tal, og derfor foretager Java **heltalsdivision**.

Decimaldelen bliver fjernet.

Hvis vi i stedet skriver:

```java
double result = 5.0 / 2.0;

System.out.println(result);
```

bliver resultatet:

```text
2.5
```

---

## `boolean` – sand eller falsk

En `boolean` kan kun indeholde én af to værdier:

```java
true
```

eller:

```java
false
```

Eksempel:

```java
boolean isStudent = true;
boolean hasDriversLicense = false;
```

Boolean-værdier bruges meget, når programmer senere skal træffe beslutninger.

For eksempel:

```java
int age = 20;

boolean isAdult = age >= 18;

System.out.println(isAdult);
```

Output:

```text
true
```

Udtrykket:

```java
age >= 18
```

er enten sandt eller falsk.

---

## `char` – ét tegn

Datatypen `char` bruges til **ét enkelt tegn**.

```java
char grade = 'A';
char letter = 'K';
char symbol = '?';
```

Et `char` skrives med **enkelt citationstegn**:

```java
'A'
```

---

## `String` – tekst

Når vi vil gemme tekst, bruger vi `String`.

```java
String name = "Anna";
String city = "København";
String message = "Hello world";
```

En `String` skrives med **dobbelte citationstegn**:

```java
"Hello"
```

Vi kan skrive en variabel ud:

```java
String name = "Anna";

System.out.println(name);
```

Output:

```text
Anna
```

Vi kan også kombinere tekst og variable:

```java
String name = "Anna";
int age = 22;

System.out.println("Hej " + name);
System.out.println("Du er " + age + " år gammel");
```

Output:

```text
Hej Anna
Du er 22 år gammel
```

Operatoren `+` bruges her til at sætte tekst sammen.

---

## `char` eller `String`?

Bemærk forskellen:

```java
char letter = 'A';
```

og:

```java
String letter = "A";
```

Begge indeholder umiddelbart bogstavet A, men de har forskellige datatyper.

`char` indeholder **ét tegn**, mens `String` bruges til **tekst**.

```java
char firstLetter = 'M';

String firstName = "Mads";
```

---

## Deklaration og tildeling

Når vi skriver:

```java
int age = 25;
```

foretager vi både en **deklaration** og en **tildeling**.

Vi kan også gøre det i to trin.

Først deklarerer vi variablen:

```java
int age;
```

Derefter tildeler vi den en værdi:

```java
age = 25;
```

Det svarer altså til:

```java
int age = 25;
```

---

## Variable skal have den rigtige datatype

Java kontrollerer, at værdien passer til variablens datatype.

Dette er gyldigt:

```java
int age = 25;
```

Dette er ikke gyldigt:

```java
int age = "25";
```

`"25"` er nemlig tekst og ikke et helt tal.

På samme måde er dette forkert:

```java
boolean isStudent = "true";
```

mens dette er korrekt:

```java
boolean isStudent = true;
```

Bemærk forskellen på:

```java
true
```

og:

```java
"true"
```

Den første er en `boolean`.

Den anden er en `String`.

---

## Giv dine variable gode navne

Variable bør have navne, der fortæller, hvad de indeholder.

Undgå eksempelvis:

```java
int x = 25;
String s = "Peter";
double d = 199.95;
```

Skriv hellere:

```java
int age = 25;
String name = "Peter";
double price = 199.95;
```

Det gør programmet meget lettere at læse.

I Java skriver man normalt variabelnavne med **camelCase**:

```java
int numberOfStudents = 25;
double averageTemperature = 18.5;
boolean hasDriversLicense = true;
```

Det første ord starter med lille bogstav. De efterfølgende ord starter med stort bogstav.

---

## Variable kan beregnes ud fra andre variable

En variabel behøver ikke få en fast værdi.

Den kan også få sin værdi fra en beregning:

```java
int width = 10;
int height = 5;

int area = width * height;

System.out.println(area);
```

Output:

```text
50
```

Et andet eksempel:

```java
double price = 100.0;
double discount = 20.0;

double finalPrice = price - discount;

System.out.println(finalPrice);
```

---

## Et samlet eksempel

Her er et lille program, der bruger flere forskellige datatyper:

```java
public class Main {

    public static void main(String[] args) {

        String name = "Amalie";
        int age = 21;
        double height = 1.72;
        boolean isStudent = true;
        char classLetter = 'A';

        System.out.println("Navn: " + name);
        System.out.println("Alder: " + age);
        System.out.println("Højde: " + height);
        System.out.println("Studerende: " + isStudent);
        System.out.println("Hold: " + classLetter);
    }
}
```

Output:

```text
Navn: Amalie
Alder: 21
Højde: 1.72
Studerende: true
Hold: A
```

---

## Prøv selv

Lav et lille Java-program med variable, der beskriver dig selv.

Opret eksempelvis variable til:

* dit navn
* din alder
* din højde
* om du er studerende
* dit første bogstav

Eksempel:

```java
String name = "Sofie";
int age = 23;
double height = 1.68;
boolean isStudent = true;
char firstLetter = 'S';
```

Skriv derefter værdierne ud med:

```java
System.out.println(...);
```

---

## Kan du forudsige resultatet?

Prøv først at finde svaret **uden at køre programmerne**.

### Eksempel 1

```java
int a = 4;
int b = 3;

int result = a + b * 2;

System.out.println(result);
```

Hvad bliver skrevet ud?

### Eksempel 2

```java
int number = 10;

number = number + 5;

System.out.println(number);
```

Hvad bliver skrevet ud?

### Eksempel 3

```java
String firstName = "Ada";
String lastName = "Lovelace";

System.out.println(firstName + " " + lastName);
```

Hvad bliver skrevet ud?

### Eksempel 4

```java
int x = 5;
int y = 2;

System.out.println(x / y);
```

Hvad bliver skrevet ud?

---
## Aritmetiske operatorer

Når vi har tal gemt i variable, kan vi udføre beregninger på dem ved hjælp af **aritmetiske operatorer**.

De mest almindelige er:

| Operator | Betydning         | Eksempel |
| -------- | ----------------- | -------- |
| `+`      | Addition          | `a + b`  |
| `-`      | Subtraktion       | `a - b`  |
| `*`      | Multiplikation    | `a * b`  |
| `/`      | Division          | `a / b`  |
| `%`      | Rest ved division | `a % b`  |

Eksempel:

```java
int a = 10;
int b = 3;

System.out.println(a + b);
System.out.println(a - b);
System.out.println(a * b);
System.out.println(a / b);
System.out.println(a % b);
```

Output:

```text
13
7
30
3
1
```

Bemærk især:

```java
10 / 3
```

giver `3`, fordi både `10` og `3` er heltal (`int`).

Og:

```java
10 % 3
```

giver `1`, fordi der er **1 til rest**, når 10 divideres med 3.

---

## Modulo-operatoren `%`

Operatoren `%` kaldes ofte **modulo** og finder resten efter en division.

Eksempel:

```java
int rest = 17 % 5;

System.out.println(rest);
```

Output:

```text
2
```

Modulo kan eksempelvis bruges til at undersøge, om et tal er lige:

```java
int number = 8;

System.out.println(number % 2);
```

Output:

```text
0
```

Et lige tal giver altid resten `0`, når det divideres med 2.

---

## Operatorprioritet

Java udfører ikke nødvendigvis beregninger fra venstre mod højre.

Ligesom i matematik har operatorerne forskellig **prioritet**.

Se dette eksempel:

```java
int result = 2 + 3 * 4;

System.out.println(result);
```

Resultatet bliver:

```text
14
```

og ikke `20`.

Det skyldes, at multiplikation udføres før addition:

```text
2 + 3 * 4
    -----
      12

2 + 12 = 14
```

En forenklet prioritering er:

1. Parenteser `()`
2. Multiplikation `*`, division `/` og modulo `%`
3. Addition `+` og subtraktion `-`

---

## Brug parenteser

Parenteser kan bruges til at ændre rækkefølgen.

```java
int result = (2 + 3) * 4;

System.out.println(result);
```

Nu bliver resultatet:

```text
20
```

Fordi parentesen beregnes først:

```text
(2 + 3) * 4
    5     * 4

= 20
```

Selv når parenteser ikke er nødvendige, kan de nogle gange gøre et udtryk lettere at forstå.

Sammenlign:

```java
double total = price * number + delivery;
```

med:

```java
double total = (price * number) + delivery;
```

De giver samme resultat, men den sidste kan være lettere at læse.

---

## Tildeling kombineret med beregning

Forestil dig, at vi har:

```java
int score = 10;
```

og vil lægge 5 til værdien.

Vi kan skrive:

```java
score = score + 5;
```

Nu indeholder `score` værdien:

```text
15
```

Java har en kortere måde at skrive det på:

```java
score += 5;
```

De to udtryk betyder altså det samme:

```java
score = score + 5;
```

```java
score += 5;
```

Der findes tilsvarende operatorer for de andre regnearter:

| Lang version | Kort version |
| ------------ | ------------ |
| `x = x + 5`  | `x += 5`     |
| `x = x - 5`  | `x -= 5`     |
| `x = x * 5`  | `x *= 5`     |
| `x = x / 5`  | `x /= 5`     |
| `x = x % 5`  | `x %= 5`     |

Eksempel:

```java
int points = 10;

points += 5;
points *= 2;

System.out.println(points);
```

Output:

```text
30
```

---

## `++` – læg 1 til en variabel

Når vi programmerer, har vi meget ofte brug for at lægge præcis `1` til en variabel.

Vi kunne skrive:

```java
int count = 5;

count = count + 1;
```

eller den kortere version:

```java
count += 1;
```

Men Java har en særlig operator til dette:

```java
count++;
```

Alle tre udtryk betyder i dette tilfælde det samme:

```java
count = count + 1;

count += 1;

count++;
```

Eksempel:

```java
int count = 5;

count++;

System.out.println(count);
```

Output:

```text
6
```

`++` kaldes **increment-operatoren**.

---

## `--` – træk 1 fra en variabel

På samme måde kan vi trække `1` fra en variabel med `--`.

```java
int lives = 3;

lives--;

System.out.println(lives);
```

Output:

```text
2
```

Disse tre udtryk betyder i dette tilfælde det samme:

```java
lives = lives - 1;

lives -= 1;

lives--;
```

`--` kaldes **decrement-operatoren**.

---

## `count++` eller `++count`?

Du kan møde `++` både **efter** og **før** variablen:

```java
count++;
```

og:

```java
++count;
```

Hvis de står alene på en linje, er resultatet det samme.

```java
int count = 5;

count++;

System.out.println(count);
```

giver:

```text
6
```

og:

```java
int count = 5;

++count;

System.out.println(count);
```

giver også:

```text
6
```

Der er dog en forskel, hvis `++` bruges som en del af et større udtryk.

---

### Post-increment: `count++`

Se dette eksempel:

```java
int count = 5;

int result = count++;

System.out.println(result);
System.out.println(count);
```

Output:

```text
5
6
```

Ved:

```java
count++
```

bruges den **gamle værdi først**, og derefter lægges 1 til variablen.

Vi kan tænke på det som:

```text
Brug count
derefter:
count = count + 1
```

---

### Pre-increment: `++count`

Se nu dette eksempel:

```java
int count = 5;

int result = ++count;

System.out.println(result);
System.out.println(count);
```

Output:

```text
6
6
```

Ved:

```java
++count
```

lægges `1` til variablen **før værdien bruges**.

Vi kan tænke på det som:

```text
count = count + 1
derefter:
Brug count
```

---

## Sammenligning af `count++` og `++count`

Hvis vi starter med:

```java
int count = 5;
```

så giver:

```java
int result = count++;
```

følgende værdier:

```text
result = 5
count  = 6
```

Mens:

```java
int result = ++count;
```

giver:

```text
result = 6
count  = 6
```

Det samme gælder for `--`:

```java
count--;
```

og:

```java
--count;
```

---

> **Tommelfingerregel:**
> Når `++` eller `--` står alene på en linje, behøver du normalt ikke bekymre dig om forskellen mellem prefix og postfix.
>
> På dette tidspunkt i kurset er det ofte lettest at skrive:
>
> ```java
> count++;
> ```
>
> når du blot vil lægge 1 til en variabel.

---

## Pas på med komplicerede udtryk

Det er muligt at skrive kode som:

```java
int x = 5;
int y = x++ + ++x;
```

Men sådan kode er svær at læse og let at misforstå.

Skriv hellere beregninger i flere tydelige trin.

Kode skal ikke kun kunne forstås af computeren – den skal også kunne forstås af mennesker.

---

## Kan du forudsige resultatet?

Prøv at finde svaret **før du kører koden**.

### Eksempel 1

```java
int x = 10;

x++;

System.out.println(x);
```

Hvad bliver skrevet ud?

---

### Eksempel 2

```java
int x = 10;

x--;
x--;

System.out.println(x);
```

Hvad bliver skrevet ud?

---

### Eksempel 3

```java
int x = 5;

int y = x++;

System.out.println(x);
System.out.println(y);
```

Hvad bliver skrevet ud?

---

### Eksempel 4

```java
int x = 5;

int y = ++x;

System.out.println(x);
System.out.println(y);
```

Hvad bliver skrevet ud?

---

### Eksempel 5

```java
int result = 10 + 2 * 3;

System.out.println(result);
```

Hvad bliver skrevet ud?

---

### Eksempel 6

```java
int result = (10 + 2) * 3;

System.out.println(result);
```

Hvad bliver skrevet ud?

---

### Eksempel 7

```java
int number = 17;

System.out.println(number % 5);
```

Hvad bliver skrevet ud?

---

## Det vigtigste at tage med

Efter denne forberedelse skal du især kunne:

* forklare, hvad en **variabel** er
* deklarere en variabel og tildele den en værdi
* kende forskel på de mest almindelige datatyper:

  * `int`
  * `long`
  * `double`
  * `float`
  * `boolean`
  * `char`
  * `String`
* vælge en passende datatype til en værdi
* bruge de aritmetiske operatorer:

  * `+`
  * `-`
  * `*`
  * `/`
  * `%`
* forstå **heltalsdivision**
* forstå **operatorprioritet**
* bruge parenteser `()` til at styre rækkefølgen i beregninger
* bruge sammensatte operatorer som `+=` og `-=`
* bruge `++` til at lægge 1 til en variabel
* bruge `--` til at trække 1 fra en variabel
* kende den grundlæggende forskel på `x++` og `++x`
* kunne forudsige resultatet af simple Java-udtryk

## Aktiviteter i undervisningen  
Inden vi starter med at løse dagens opgaver skal vi se på hvordan vi kan organisere indhold i de første uger i IntelliJ i projekter og packages.    
[organisering i IntelliJ](../../00_vejledninger/intellij_organisering.md)  

Arbejd med disse [opgaver](opgaver.md)  
