# Opgaver – Objekter og klasser


Du skal afprøve dine løsninger fra `main`-metoden.

Når du senere i opgaverne skal oprette dine egne klasser, skal de oprettes som separate Java-klasser i projektet.

---

# Del 1 – Brug eksisterende klasser

## Opgave 1 – Scanner som objekt

Du har allerede arbejdet med klassen `Scanner`.

Skriv følgende program:

```java
Scanner scanner = new Scanner(System.in);

System.out.print("Hvad hedder du? ");
String name = scanner.nextLine();

System.out.println("Hej " + name);
```

Besvar derefter følgende spørgsmål:

1. Hvad er navnet på klassen?
2. Hvad er navnet på variablen, der refererer til objektet?
3. Hvor bliver objektet oprettet?
4. Hvilken metode kaldes på objektet?

---
## Klassen `Random`

Java indeholder en række klasser, som vi kan bruge i vores programmer. En af dem er klassen `Random`, som kan bruges til at generere tilfældige tal.

For at bruge `Random` skal klassen først importeres:

```java
import java.util.Random;
```

Derefter kan vi oprette et objekt af klassen `Random`:

```java
Random random = new Random();
```

Her er:
* **`Random`**: navnet på klassen
* **`random`**: en variabel, som refererer til objektet
* **`new Random()`**: opretter et nyt objekt af klassen `Random`

Vi kan nu bruge objektet til at generere tilfældige tal.

### `nextInt()`

Metoden `nextInt()` kan bruges til at generere et tilfældigt heltal.

**Eksempel:**
```java
int number = random.nextInt(10);
```

`random.nextInt(10)` giver et tilfældigt heltal fra **0 til 9**. Tallet 10 er altså ikke med.

Generelt gælder det, at `random.nextInt(n)` giver et tilfældigt heltal fra **0 til n - 1**.

**Eksempel:**
```java
random.nextInt(6);
```
Kan give: `0`, `1`, `2`, `3`, `4` eller `5`.

**Et andet eksempel:**
```java
random.nextInt(100);
```
Kan give et heltal fra **0 til 99**.

### Tilfældigt tal fra 1 til 10

Hvis vi ønsker et tilfældigt tal fra 1 til 10, kan vi lægge 1 til resultatet:

```java
int number = random.nextInt(10) + 1;
```

* `random.nextInt(10)` giver: **0 til 9**
* Når vi lægger 1 til, får vi: **1 til 10**

### Eksempel – kast med en terning

En almindelig terning kan give værdierne: `1`, `2`, `3`, `4`, `5` eller `6`. Vi kan derfor simulere et terningekast med:

```java
int dice = random.nextInt(6) + 1;
System.out.println("Du slog " + dice);
```

Her giver `random.nextInt(6)` et tal fra 0 til 5. Når vi lægger 1 til, får vi et tal fra 1 til 6.

### Et andet starttal

Vi kan også lave tilfældige tal, som starter ved andre værdier end 1. Hvis vi eksempelvis ønsker et tal fra 10 til 19, kan vi skrive:

```java
int number = random.nextInt(10) + 10;
```

* `random.nextInt(10)` giver: **0 til 9**
* Når vi lægger 10 til, får vi: **10 til 19**

### Husk

* `random.nextInt(10)` giver: **0 - 9**
* `random.nextInt(10) + 1` giver: **1 - 10**

Tallet, der står inde i `nextInt()`, angiver altså hvor mange forskellige værdier der kan genereres, ikke det største tal.

> **Vigtigt for begyndere:** Argumentet til `nextInt()` angiver antallet af mulige værdier, når man bruger varianten med én parameter. Det er ofte lettere at forstå end kun at huske reglen "0 til n - 1".
Use 

## Opgave 2 – Random

Klassen `Random` kan bruges til at generere tilfældige tal.

Start med:

```java
import java.util.Random;
```

Opret derefter et `Random`-objekt:

```java
Random random = new Random();
```

Brug:

```java
random.nextInt(10);
```

til at generere et tilfældigt tal.

### a)

Udskriv et tilfældigt tal mellem `0` og `9`.

### b)

Ændr programmet, så det udskriver et tilfældigt tal mellem `1` og `10`.

### c)

Generer to tilfældige tal og udskriv det største.

Eksempel:

```text
Første tal: 7
Andet tal: 3
Det største tal er 7
```

---

## Opgave 3 – Kast en terning

Brug `Random` til at simulere et kast med en almindelig seks-sidet terning.

Programmet skal eksempelvis kunne udskrive:

```text
Du slog 4
```

Kør programmet flere gange.

### Ekstra

Lav et loop, der kaster terningen 10 gange.

Eksempel:

```text
6
2
4
1
6
3
5
2
1
4
```

---

## Opgave 4 – To terninger

Lav et program, der kaster to terninger.

Udskriv begge terninger og summen.

Eksempel:

```text
Terning 1: 4
Terning 2: 6
Sum: 10
```

### Ekstra

Hvis begge terninger viser det samme, skal programmet også skrive:

```text
Du slog to ens!
```

---

# Del 2 – Math

## Opgave 5 – Math

Undersøg resultatet af følgende:

```java
System.out.println(Math.max(10, 25));
System.out.println(Math.min(10, 25));
System.out.println(Math.sqrt(81));
System.out.println(Math.pow(2, 3));
```

Besvar:

1. Hvad gør `Math.max()`?
2. Hvad gør `Math.min()`?
3. Hvad gør `Math.sqrt()`?
4. Hvad gør `Math.pow()`?

Læg mærke til, at vi ikke skriver:

```java
new Math();
```

før vi bruger metoderne.

---

## Opgave 6 – Afstand mellem to tal

Lav to `int`-variable:

```java
int number1 = 10;
int number2 = 25;
```

Beregn forskellen mellem tallene.

Brug derefter:

```java
Math.abs(...)
```

så resultatet altid bliver positivt. 

Metoden `Math.abs()` returnerer den **absolutte værdi** af et tal.

Det betyder, at resultatet altid er positivt eller `0`.

Eksempler:

```java
System.out.println(Math.abs(10));   // 10
System.out.println(Math.abs(-10));  // 10
System.out.println(Math.abs(0));    // 0
```

I vores tilfælde kan vi skrive:  
```java
Math.abs(number1 - number2)
```


Prøv både:

```java
number1 = 10;
number2 = 25;
```

og:

```java
number1 = 25;
number2 = 10;
```

---

# Del 3 – Din første klasse

## Opgave 7 – Person

Nu skal du for første gang selv oprette en klasse.

### Opret klassen `Person` i IntelliJ

1. Find mappen `src` i **Project**-vinduet i venstre side af IntelliJ.
2. Højreklik på `src`.
3. Vælg **New → Java Class**.
4. Skriv navnet:

```text
Person
```

5. Tryk **Enter**.

IntelliJ opretter nu filen `Person.java` med:

```java
public class Person {

}
```

> Klassen `Person` skal ligge i sin egen fil `Person.java`. Du skal altså ikke skrive den inde i `Main`-klassen.

### Tilføj attributter

Tilføj følgende attributter til klassen:

```java
public class Person {

    String name;
    int age;
}
```

`name` og `age` er **attributter** i klassen `Person`.

Du har nu lavet din første klasse.

### Opret et objekt

Gå tilbage til `Main.java`.

Inde i `main`-metoden skal du oprette et objekt af klassen `Person`:

```java
Person person = new Person();
```

Giv derefter objektet et navn og en alder:

```java
person.name = "Anna";
person.age = 23;
```

Udskriv personens oplysninger, så programmet skriver:

```text
Anna er 23 år
```

### Tænk over

Se på denne linje:

```java
Person person = new Person();
```

Kan du udpege:

* klassens navn?
* variablens navn?
* hvor objektet bliver oprettet?
* hvilket ord der fortæller Java, at der skal oprettes et nyt objekt?



---

## Opgave 8 – To personer

Opret to forskellige `Person`-objekter.

Eksempel:

```java
Person person1 = new Person();
Person person2 = new Person();
```

Giv dem forskellige navne og aldre.

Udskriv oplysningerne om begge personer.

Eksempel:

```text
Anna er 23 år
Ali er 31 år
```

Besvar:

1. Hvor mange klasser har du lavet?
2. Hvor mange `Person`-objekter har du oprettet?
3. Har de to objekter de samme værdier?

---

## Opgave 9 – Hvem er ældst?

Brug de to `Person`-objekter fra den forrige opgave.

Sammenlign deres alder med en `if`-sætning.

Programmet skal udskrive navnet på den ældste person.

Eksempel:

```text
Ali er ældst
```

---

# Del 4 – Lav dine egne klasser

## Opgave 10 – Book

Opret klassen:

```java
Book
```

En bog skal have:

```java
String title;
String author;
int numberOfPages;
```

Opret et objekt og giv attributterne værdier.

Eksempel:

```text
Titel: The Hobbit
Forfatter: J.R.R. Tolkien
Sider: 310
```

---

## Opgave 11 – Flere bøger

Opret tre forskellige `Book`-objekter.

Giv dem forskellige værdier.

Udskriv oplysninger om alle tre bøger.

Find derefter den bog, der har flest sider.

---

## Opgave 12 – Car

Opret klassen:

```java
Car
```

Den skal have følgende attributter:

```java
String brand;
String model;
int year;
```

Opret mindst to biler.

Eksempel:

```java
Car car1 = new Car();
car1.brand = "Toyota";
car1.model = "Yaris";
car1.year = 2020;
```

Udskriv oplysninger om bilerne.

---

## Opgave 13 – Hvilken bil er ældst?

Brug de to biler fra den forrige opgave.

Sammenlign deres `year`.

Udskriv, hvilken bil der er ældst.

---

# Del 5 – Lidt sværere

## Opgave 14 – Product

Opret klassen:

```java
Product
```

med attributterne:

```java
String name;
double price;
int quantity;
```

Opret et produkt:

```java
Product product = new Product();
```

Beregn den samlede værdi af varerne:

```text
price * quantity
```

Eksempel:

```text
Produkt: Kaffe
Pris: 45.95
Antal: 3
Samlet værdi: 137.85
```

---

## Opgave 15 – Sammenlign produkter

Opret to `Product`-objekter.

Find ud af:

* hvilket produkt der er dyrest pr. stk.
* hvilket produkt der har den største samlede værdi

---

## Opgave 16 – Et lille spil

Lav klassen:

```java
Player
```

med attributterne:

```java
String name;
int score;
```

Opret to spillere.

Brug derefter `Random` til at give hver spiller et tilfældigt antal point mellem `1` og `10`.

Eksempel:

```text
Anna fik 7 point
Ali fik 4 point

Anna vandt!
```

Hvis spillerne får samme antal point, skal programmet skrive:

```text
Uafgjort!
```

---

# Udfordringer

## Udfordring 1 – Terningespil med spillere

Brug din `Player`-klasse.

Hver spiller skal slå med en terning tre gange.

Læg resultaterne sammen og gem summen i spillerens `score`.

Eksempel:

```text
Anna:
Kast 1: 4
Kast 2: 6
Kast 3: 3
Score: 13

Ali:
Kast 1: 2
Kast 2: 5
Kast 3: 1
Score: 8

Anna vandt!
```

---

## Udfordring 2 – Find fejlen

Se på følgende kode:

```java
Person person1 = new Person();
person1.name = "Anna";
person1.age = 25;

Person person2 = person1;

person2.name = "Sofie";

System.out.println(person1.name);
System.out.println(person2.name);
```

Inden du kører programmet:

1. Hvad tror du, programmet udskriver?
2. Kør programmet og se resultatet.
3. Prøv at forklare resultatet.

**Hint:** Bliver der oprettet et nyt objekt, når vi skriver:

```java
Person person2 = person1;
```

?

Du behøver ikke kunne forklare dette fuldstændigt endnu. Det vigtige er at opdage, at objektvariable opfører sig lidt anderledes end eksempelvis `int`.

---

# Opsamling

Når du er færdig med opgaverne, skal du kunne forklare følgende kode:

```java
Person person = new Person();

person.name = "Anna";
person.age = 23;
```

Brug ordene:

* klasse
* objekt
* variabel
* `new`
* attribut

