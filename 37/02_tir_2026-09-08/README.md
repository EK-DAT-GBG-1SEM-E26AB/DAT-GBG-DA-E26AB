# Objekter og klasser

## Beskrivelse

I denne lektion arbejder vi videre med egne klasser og objekter. Vi ser på, hvordan objekter kan have forskellige værdier og dermed forskellig tilstand, og hvordan konstruktører kan bruges til at oprette objekter med startværdier.

## Læringsmål

Efter lektionen skal du kunne:

* oprette flere objekter af samme klasse
* forklare, at forskellige objekter har deres egen tilstand
* forklare forskellen på en klasse, en objektvariabel og et objekt
* lave en simpel konstruktør
* bruge en konstruktør til at give et objekt startværdier
* læse og ændre et objekts attributter - **this**
* anvende objekter sammen med betingelser og loops
* private / public synlighed
* set / get metoder

## Se disse videoer før undervisningen

[constructors](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=6h51m38s) (til: 07:01:45)  

## Læs nedenstående før undervisningen

### Klassen beskriver – objektet indeholder værdierne

I sidste lektion lavede vi eksempelvis klassen:

```java
public class Person {

    String name;
    int age;
}
```

Klassen beskriver, hvilke egenskaber en person har.

Når vi opretter objekter, får hvert objekt sine egne værdier:

```java
Person person1 = new Person();

person1.name = "Anna";
person1.age = 23;


Person person2 = new Person();

person2.name = "Ali";
person2.age = 31;
```

Objekterne er begge af typen `Person`, men de indeholder forskellige værdier.

Man siger, at objekterne har forskellig **tilstand**.

---

## Referencen og objektet

Se denne linje:

```java
Person person = new Person();
```

Der sker flere ting.

```java
Person
```

er typen.

```java
person
```

er en variabel.

```java
new Person()
```

opretter et nyt objekt.

Variablen `person` giver os adgang til objektet.

Vi kan derfor skrive:

```java
person.name = "Anna";
```

eller:

```java
System.out.println(person.name);
```

---

## En bedre måde at oprette objekter på

Indtil nu har vi skrevet:

```java
Person person = new Person();

person.name = "Anna";
person.age = 23;
```

Det virker, men det ville være praktisk, hvis vi kunne give oplysningerne allerede, når objektet bliver oprettet.

Det kan vi gøre med en **konstruktør**.

```java
public class Person {

    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

Nu kan vi skrive:

```java
Person person = new Person("Anna", 23);
```

---

## Hvad betyder `this`?

I konstruktøren står:

```java
this.name = name;
```

Der findes her to forskellige ting med navnet `name`.

```java
this.name
```

er attributten på objektet.

```java
name
```

er den værdi, konstruktøren modtager.

Derfor betyder:

```java
this.name = name;
```

i praksis:

> Gem den modtagne værdi `name` i dette objekts attribut `name`.

Det samme sker her:

```java
this.age = age;
```

---

## Flere objekter

Vi kan nu nemt oprette flere personer:

```java
Person person1 = new Person("Anna", 23);
Person person2 = new Person("Ali", 31);
Person person3 = new Person("Sofie", 19);
```

Alle tre objekter er lavet ud fra samme klasse:

```text
Person
```

men de har forskellig tilstand.

---

## Objekter kan bruges sammen med det, du allerede kender

Objekter erstatter ikke variable, betingelser og loops.

De bruges sammen.

Eksempel:

```java
Person person = new Person("Anna", 23);

if (person.age >= 18) {
    System.out.println(person.name + " er myndig");
}
```

Eller:

```java
Person person1 = new Person("Anna", 23);
Person person2 = new Person("Ali", 31);

if (person1.age > person2.age) {
    System.out.println(person1.name + " er ældst");
} else {
    System.out.println(person2.name + " er ældst");
}
```

På den måde kan vi begynde at samle oplysninger, der hører sammen, i objekter i stedet for at have mange løse variable.

---

## Hvorfor bruger vi klasser?

Uden en klasse kunne oplysninger om personer eksempelvis ligge i separate variable:

```java
String name1 = "Anna";
int age1 = 23;

String name2 = "Ali";
int age2 = 31;
```

Når programmer bliver større, bliver dette hurtigt svært at holde styr på.

Med objekter kan oplysninger, der hører sammen, samles:

```java
Person person1 = new Person("Anna", 23);
Person person2 = new Person("Ali", 31);
```

Det er en af de vigtigste idéer i objektorienteret programmering:

> Data, der beskriver den samme ting, kan samles i et objekt.

---

## `public` og `private`

Indtil nu har vores klasse set sådan ud:

```java
public class Person {

    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

Her kan vi fra `Main` direkte ændre attributterne:

```java
Person person = new Person("Anna", 23);

person.name = "Sofie";
person.age = -100;
```

Det betyder også, at andre dele af programmet kan sætte værdier, som måske ikke giver mening.

For at kontrollere adgangen til et objekts data kan vi bruge `private`.

```java
public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

Nu kan vi ikke længere skrive:

```java
person.name = "Sofie";
```

fra `Main`.

Attributterne kan kun bruges direkte inde i klassen `Person`.

---

## Hvad betyder `public`?

Når noget er `public`, kan det bruges fra andre klasser.

Eksempel:

```java
public class Person {

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void printName() {
        System.out.println(name);
    }
}
```

Fra `Main` kan vi skrive:

```java
Person person = new Person("Anna");

person.printName();
```

Metoden `printName()` er `public` og kan derfor kaldes udefra.

Attributten `name` er derimod `private`:

```java
private String name;
```

og kan derfor ikke tilgås direkte fra `Main`.

---

## En simpel huskeregel

I de klasser, vi selv laver, vil vi ofte bruge:

```java
private
```

til objektets attributter og:

```java
public
```

til konstruktører og de metoder, som andre klasser skal kunne bruge.

Eksempel:

```java
public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void printInfo() {
        System.out.println(name + " er " + age + " år");
    }
}
```

---

## Objektet skal selv arbejde med sine data

Tidligere skrev vi måske:

```java
Person person = new Person("Anna", 23);

if (person.age >= 18) {
    System.out.println(person.name + " er myndig");
}
```

Det virker ikke længere, når attributterne er `private`.

I stedet kan vi lade objektet selv udføre arbejdet:

```java
public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void printAdultStatus() {
        if (age >= 18) {
            System.out.println(name + " er myndig");
        } else {
            System.out.println(name + " er ikke myndig");
        }
    }
}
```

I `Main` skriver vi:

```java
Person person = new Person("Anna", 23);

person.printAdultStatus();
```

Her er det objektet selv, der arbejder med sine egne data.

---

## Get-metoder

Nogle gange har en anden klasse brug for at læse en værdi.

Hvis `name` er `private`, kan vi ikke skrive:

```java
System.out.println(person.name);
```

I stedet kan klassen tilbyde en `public` metode:

```java
public String getName() {
    return name;
}
```

Hele klassen kan eksempelvis se sådan ud:

```java
public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
```

Nu kan vi skrive:

```java
Person person = new Person("Anna", 23);

System.out.println(person.getName());
System.out.println(person.getAge());
```

Attributterne er stadig `private`, men klassen bestemmer selv, hvordan andre får adgang til dem.

---

## Set-metoder

Vi kan også vælge at give mulighed for at ændre en værdi.

```java
public void setAge(int age) {
    this.age = age;
}
```

Så kan vi skrive:

```java
person.setAge(24);
```

En fordel ved en metode er, at vi kan kontrollere værdien.

```java
public void setAge(int age) {

    if (age >= 0) {
        this.age = age;
    }
}
```

Nu kan objektet forhindre en negativ alder.

```java
person.setAge(-10);
```

vil ikke ændre alderen.

---

## Hvorfor ikke bare gøre alt `public`?

Vi kunne skrive:

```java
public String name;
public int age;
```

Så kunne alle dele af programmet direkte læse og ændre værdierne.

Men det giver klassen meget lidt kontrol over sine egne data.

Ved i stedet at skrive:

```java
private String name;
private int age;
```

kan klassen kontrollere, hvordan værdierne læses og ændres.

Denne idé kaldes **indkapsling**.

På engelsk bruges ordet **encapsulation**.

---

## Eksempel: BankAccount

Et eksempel, hvor `private` er særligt vigtigt, er en bankkonto.

```java
public class BankAccount {

    private String owner;
    private double balance;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {

        if (amount > 0) {
            balance = balance + amount;
        }
    }
}
```

Fra `Main` kan vi skrive:

```java
BankAccount account = new BankAccount("Anna", 1000);

account.deposit(500);

System.out.println(account.getBalance());
```

Men vi kan ikke skrive:

```java
account.balance = -100000;
```

fordi `balance` er `private`.

Objektet bestemmer altså selv, hvordan saldoen må ændres.

---

## Klasse og objekt samlet

Vi kan nu se på en klasse som en beskrivelse af både:

* hvilke **data** objekterne indeholder
* hvilke **handlinger** objekterne kan udføre

Eksempel:

```java
public class BankAccount {

    private String owner;
    private double balance;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
        }
    }

    public void printInfo() {
        System.out.println(owner + ": " + balance + " kr.");
    }
}
```

Og i `Main`:

```java
public class Main {

    public static void main(String[] args) {

        BankAccount account1 =
                new BankAccount("Anna", 1000);

        BankAccount account2 =
                new BankAccount("Ali", 2500);

        account1.deposit(500);

        account1.printInfo();
        account2.printInfo();
    }
}
```

Vi har her:

* én klasse: `BankAccount`
* to objekter: `account1` og `account2`
* private attributter
* en konstruktør
* public metoder
* forskellige værdier i de to objekter

Det er de grundlæggende byggesten i objektorienteret programmering.

---

## Aktiviteter i undervisningen
Arbejd med disse [opgaver](opgaver.md)  
