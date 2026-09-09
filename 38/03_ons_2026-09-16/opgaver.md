# Opgaver – ArrayList

Brug en klasse med en `main`-metode til hver opgave. Husk:

```java
import java.util.ArrayList;
```

## Del 1 – Opret og brug en ArrayList

### Opgave 1 – Navne

Opret en `ArrayList<String>` med navnet `names`.

Tilføj navnene:

- Anna
- Ali
- Sofie
- Noah

Udskriv hele listen.

Udskriv derefter det første og det tredje navn med `get()`.

### Opgave 2 – Størrelsen ændrer sig

Byg videre på listen med navne.

Udskriv først antallet af navne med `size()`.

Tilføj endnu et navn, og udskriv størrelsen igen.

Fjern derefter et navn, og udskriv størrelsen en sidste gang.

Forklar, hvorfor en `ArrayList` er praktisk her sammenlignet med et array.

### Opgave 3 – Ændr et element

Opret listen:

```java
ArrayList<String> colors = new ArrayList<>();
colors.add("Rød");
colors.add("Grøn");
colors.add("Blå");
```

Brug `set()` til at ændre `Grøn` til `Gul`.

Udskriv listen før og efter ændringen.

### Opgave 4 – Indsæt på en bestemt plads

Opret en liste med:

```text
Mandag
Onsdag
Torsdag
```

Brug `add(index, value)` til at indsætte `Tirsdag` på den rigtige plads.

Udskriv alle elementer og deres indeks.

## Del 2 – Gennemløb og søgning

### Opgave 5 – Enhanced for-loop

Opret en liste med mindst fem bynavne.

Gennemløb listen med et enhanced for-loop, og udskriv hvert bynavn.

Udskriv derefter kun de bynavne, der har mere end fem bogstaver.

### Opgave 6 – Almindeligt for-loop

Brug den samme liste med bynavne.

Gennemløb den med et almindeligt for-loop, og udskriv eksempelvis:

```text
0: Køge
1: Roskilde
2: Næstved
```

Hvorfor er det almindelige for-loop praktisk i denne opgave?

### Opgave 7 – Find en værdi

Opret en liste med forskellige frugter.

Undersøg med `contains()`, om listen indeholder `Banan`.

Find derefter bananens placering med `indexOf()`.

Programmet skal også kunne håndtere en frugt, der ikke findes. Husk at `indexOf()` i dette tilfælde returnerer `-1`.

### Opgave 8 – Fjern en værdi

Opret en liste med fem dyr.

Fjern:

1. ét dyr ved hjælp af dets indeks
2. ét dyr ved hjælp af dets navn

Udskriv listen efter hver ændring.

Prøv også at fjerne et navn, der ikke findes. Gem resultatet af `remove()` i en `boolean`, og udskriv, om fjernelsen lykkedes.

## Del 3 – Tal og beregninger

### Opgave 9 – Sum og gennemsnit

Opret en `ArrayList<Integer>` med mindst fem tal.

Brug et loop til at beregne:

- summen
- gennemsnittet
- det største tal

Udskriv alle tre resultater.

### Opgave 10 – Fjern det rigtige tal

Opret listen:

```java
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(10);
numbers.add(20);
numbers.add(30);
numbers.add(40);
```

Afprøv og forklar forskellen på:

```java
numbers.remove(1);
```

og:

```java
numbers.remove(Integer.valueOf(10));
```

Hvilken linje fjerner en placering, og hvilken linje fjerner en værdi?

## Del 4 – Brugerinput

### Opgave 11 – Indkøbsliste

Opret en tom `ArrayList<String>` og en `Scanner`.

Spørg først brugeren, hvor mange varer der skal indtastes.

Brug et loop til at læse varerne og tilføje dem til listen.

Udskriv til sidst hele indkøbslisten, én vare pr. linje.

Eksempel:

```text
Hvor mange varer? 3
Vare 1: Mælk
Vare 2: Brød
Vare 3: Kaffe

Din indkøbsliste:
- Mælk
- Brød
- Kaffe
```

### Opgave 12 – Fortsæt indtil stop

Lav en ny udgave af indkøbslisten.

Brugeren skal kunne indtaste varer, indtil vedkommende skriver:

```text
stop
```

Ordet `stop` skal ikke tilføjes til listen.

Programmet skal til sidst udskrive listen og antallet af varer.

## Del 5 – ArrayList med egne objekter

### Opgave 13 – Bøger

Opret klassen `Book` med:

```java
private String title;
private String author;
private int publicationYear;
```

Tilføj:

- en konstruktør
- getters
- en metode `printInfo()`

Opret derefter en `ArrayList<Book>` med mindst tre bøger.

Gennemløb listen, og kald `printInfo()` på hver bog.

Find og udskriv derefter den ældste bog.

### Opgave 14 – Søg efter en bog

Byg videre på den forrige opgave.

Spørg brugeren efter en titel, og søg efter den i listen.

Når du sammenligner titlerne, skal du bruge `.equalsIgnoreCase()`.

Hvis bogen findes, skal dens oplysninger udskrives. Ellers skal programmet skrive:

```text
Bogen blev ikke fundet
```

### Opgave 15 – Library

Opret klassen `Library` med:

```java
private String name;
private ArrayList<Book> books;
```

Listen skal oprettes i konstruktøren.

Tilføj metoderne:

```java
public void addBook(Book book)
public int getNumberOfBooks()
public void printBooks()
public Book findBookByTitle(String title)
```

`findBookByTitle()` skal returnere den fundne bog eller `null`, hvis den ikke findes.

Opret et bibliotek i `main`, tilføj mindst tre bøger, og afprøv alle metoderne.

## Udfordring – Et lille inventory

Opret klassen `Item` med:

```java
private String name;
private double weight;
```

Opret derefter klassen `Inventory`, som har:

```java
private ArrayList<Item> items;
```

Tilføj metoder, så man kan:

- tilføje en ting
- fjerne en ting ud fra dens navn
- udskrive alle ting
- få antallet af ting
- beregne den samlede vægt
- undersøge, om inventory er tomt

Afprøv klassen med mindst fire `Item`-objekter.

Ekstra: Giv inventory en maksimal samlet vægt. En ting må kun tilføjes, hvis grænsen ikke overskrides.

## Opsamling

Når du er færdig, bør du kunne forklare:

- hvorfor `ArrayList` skal importeres
- hvad elementtypen i `ArrayList<Book>` betyder
- forskellen på `length` og `size()`
- forskellen på `get()`, `set()`, `add()` og `remove()`
- hvordan indeks fungerer i en `ArrayList`
- hvorfor vi skriver `Integer` i stedet for `int`
- hvordan man gennemløber en liste
- hvordan en klasse kan have en `ArrayList` som attribut
- hvorfor listen normalt oprettes i klassens konstruktør
