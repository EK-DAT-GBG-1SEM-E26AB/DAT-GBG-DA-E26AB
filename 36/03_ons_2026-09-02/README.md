# Arrays

## Beskrivelse

I dag lærer vi **arrays** i Java.

Et array er en samling af værdier af **samme type**, gemt i én variabel. I stedet for at skrive
`int number1 = 5; int number2 = 8; int number3 = 3;` kan vi samle alle tallene i ét array.

Vi ser på, hvordan man opretter et array, læser og ændrer elementer, løber et array igennem med et
loop, og hvad der sker, hvis man prøver at gå uden for arrayets grænser.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* oprette et array med en fast størrelse
* gemme og hente værdier ved hjælp af et **index**
* forklare at det første index er `0` og det sidste er `length - 1`
* løbe et array igennem med et `for`-loop
* bruge `foreach` til at gå gennem et array uden at arbejde med index
* bruge `array.length` til at styre et loop
* forklare hvad en `ArrayIndexOutOfBoundsException` er og hvornår den opstår
* tage imod arrayværdier fra brugeren via `Scanner`
* søge efter en bestemt værdi i et array

## Se disse videoer før undervisningen

[Arrays](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=5h3m26s) (til: 05:12:35)  
[Brugerinput i et array](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=5h12m35s) (til: 05:20:38)  
[Søg i et array](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=5h20m38s) (til: 05:28:07)  

## Læs nedenstående før undervisningen

Afprøv gerne eksemplerne i IntelliJ, mens du læser.

---

### Hvad er et array?

Indtil nu har vi brugt én variabel til én værdi:

```java
int number1 = 5;
int number2 = 8;
int number3 = 3;
```

Det fungerer, men hvis vi har mange værdier, bliver det hurtigt uoverskueligt. Et **array** samler
et bestemt antal værdier af **samme type** i én variabel:

```java
int[] numbers = {5, 8, 3};
```

Nu ligger alle tre tal i `numbers`. Vi kan hente dem ud enkeltvis med et **index** – et
nummer der angiver, hvilken plads i arrayet vi vil have.

---

### Index starter ved 0

Pladserne i et array nummereres fra `0`:

```text
int[] numbers = {5, 8, 3};

index:       0   1   2
værdier:     5   8   3
```

Det første element har index `0`, det andet index `1` og så videre.

Vi henter et element med firkantede parenteser:

```java
System.out.println(numbers[0]);   // 5
System.out.println(numbers[1]);   // 8
System.out.println(numbers[2]);   // 3
```

Vi kan også ændre en værdi:

```java
numbers[1] = 99;

System.out.println(numbers[1]);   // 99
```

---

### Oprette et tomt array

Kender man størrelsen på forhånd, men ikke værdierne endnu, kan man oprette et tomt array:

```java
int[] scores = new int[5];
```

Det giver et array med plads til fem `int`-værdier. Alle pladser starter med `0` (standard for
`int`). Tilsvarende starter `double[]` med `0.0`, `boolean[]` med `false` og `String[]` med
`null`.

---

### `length` – arrayets størrelse

Et array har en **`length`**-egenskab, der fortæller, hvor mange pladser der er:

```java
int[] numbers = {5, 8, 3};

System.out.println(numbers.length);   // 3
```

Bemærk: det skrives **uden** parenteser – i modsætning til `String`'s `length()`.

Det sidste gyldige index er altid `length - 1`. For et array med tre elementer er det index `2`.

---

### Løb arrayet igennem med et for-loop

Det klassiske mønster:

```java
int[] numbers = {5, 8, 3, 7, 1};

for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

Output:

```text
5
8
3
7
1
```

Betingelsen er `i < numbers.length` – **ikke** `i <= numbers.length`. Det sikrer, at vi ikke går
forbi det sidste element.

---

### Gennemløb et array med foreach

Når vi kun vil læse værdierne i et array, kan vi bruge en `foreach`-løkke. Den er kortere og
oftest lettere at læse:

```java
int[] numbers = {5, 8, 3, 7, 1};

for (int tal : numbers) {
    System.out.println(tal);
}
```

Output:

```text
5
8
3
7
1
```

Her får variablen `tal` værdien af hvert element i arrayet, én efter én.

`foreach` er især nyttig, når vi:

* kun vil læse værdierne
* ikke har brug for indexet
* vil gøre koden mere overskuelig

Eksempel med `String[]`:

```java
String[] names = {"Anna", "Bo", "Clara"};

for (String navn : names) {
    System.out.println("Hej, " + navn);
}
```

Output:

```text
Hej, Anna
Hej, Bo
Hej, Clara
```

Vigtigt: i en `foreach`-løkke har vi **ikke** adgang til indexet. Hvis vi vil ændre værdierne
eller bruge deres placering i arrayet, skal vi bruge et almindeligt `for`-loop:

```java
int[] numbers = {5, 8, 3};

for (int i = 0; i < numbers.length; i++) {
    numbers[i] = numbers[i] * 2;
}
```

---

### ArrayIndexOutOfBoundsException

Forsøger vi at bruge et index, der ikke eksisterer, kaster Java en fejl:

```java
int[] numbers = {5, 8, 3};

System.out.println(numbers[3]);   // fejl!
```

```text
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
```

Fejlen opstår **når programmet kører** (ikke ved kompilering). Den fortæller præcis, hvilket
index der var ulovligt og hvad arrayets faktiske størrelse er.

Årsagen er som regel en af disse:

* man har glemt at `length - 1` er det sidste gyldige index
* man har brugt `<=` i stedet for `<` i loop-betingelsen
* man har hardkodet et index, der er for stort

---

### Brugeren udfylder et array

Vi kan kombinere et array med en `Scanner`:

```java
Scanner scanner = new Scanner(System.in);

int[] scores = new int[3];

for (int i = 0; i < scores.length; i++) {
    System.out.print("Indtast score " + (i + 1) + ": ");
    scores[i] = scanner.nextInt();
}

System.out.println("Du indtastede:");

for (int i = 0; i < scores.length; i++) {
    System.out.println(scores[i]);
}
```

Bemærk `i + 1` i prompten – det er kun for at vise "score 1", "score 2" osv. til brugeren.
Selve arrayet bruger stadig index `0`, `1` og `2`.

---

### Søg i et array

For at finde en bestemt værdi gennemløber vi arrayet og sammenligner:

```java
int[] numbers = {5, 8, 3, 7, 1};
int target = 7;
int foundAt = -1;

for (int i = 0; i < numbers.length; i++) {
    if (numbers[i] == target) {
        foundAt = i;
        break;
    }
}

if (foundAt != -1) {
    System.out.println("Fandt " + target + " på index " + foundAt);
} else {
    System.out.println(target + " findes ikke i arrayet");
}
```

Vi bruger `-1` som startværdi for `foundAt` – konventionen for "ikke fundet".
`break` stopper loopet, så snart vi har fundet det vi søger.

---

### Nyttige mønstre med arrays

**Find den største værdi:**

```java
int[] numbers = {5, 8, 3, 7, 1};
int max = numbers[0];

for (int i = 1; i < numbers.length; i++) {
    if (numbers[i] > max) {
        max = numbers[i];
    }
}

System.out.println("Største: " + max);
```

Vi starter `max` med det første element og opdaterer den, hver gang vi finder noget større.

**Beregn gennemsnit:**

```java
int[] numbers = {5, 8, 3, 7, 1};
int sum = 0;

for (int i = 0; i < numbers.length; i++) {
    sum += numbers[i];
}

double average = (double) sum / numbers.length;

System.out.println("Gennemsnit: " + average);
```

`(double) sum` sikrer, at divisionen bliver decimaltal og ikke heltalsdivision.

---

### Kan du forudsige resultatet?

Svar **uden** at køre koden. Skriv dine svar ned, og tjek dem bagefter.

#### Eksempel 1

```java
int[] values = {10, 20, 30, 40, 50};

System.out.println(values[0]);
System.out.println(values[4]);
System.out.println(values.length);
```

#### Eksempel 2

```java
int[] values = {10, 20, 30};

values[1] = 99;

for (int i = 0; i < values.length; i++) {
    System.out.println(values[i]);
}
```

#### Eksempel 3

```java
int[] values = {10, 20, 30};

System.out.println(values[3]);
```

Hvad sker der, og hvad hedder fejlen?

#### Eksempel 4

```java
int[] values = {4, 7, 2, 9, 1};
int sum = 0;

for (int i = 0; i < values.length; i++) {
    sum += values[i];
}

System.out.println(sum);
```

#### Eksempel 5

```java
String[] names = {"Anna", "Bo", "Clara"};

for (int i = 0; i < names.length; i++) {
    System.out.println("Hej, " + names[i]);
}
```

---

## Det vigtigste at tage med

* et array samler værdier af **samme type** i én variabel
* index starter ved `0`, det sidste gyldige index er `length - 1`
* `array.length` (uden parenteser) giver arrayets størrelse
* mønsteret `for (int i = 0; i < array.length; i++)` bruges til at løbe et array igennem
* `foreach` bruges, når vi kun vil læse værdierne i et array
* i en `foreach`-løkke har vi ikke adgang til indexet
* går du uden for arrayet, får du en `ArrayIndexOutOfBoundsException`
* brug `-1` som startværdi, når du søger efter noget

## Aktiviteter i undervisningen

Arbejd med disse [opgaver](opgaver.md)
