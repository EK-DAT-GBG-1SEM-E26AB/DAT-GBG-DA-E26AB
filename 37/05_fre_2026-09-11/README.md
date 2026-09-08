# Metoders placering, synlighed og ansvar

## Beskrivelse

I går arbejdede vi grundigt med, hvordan metoder skrives og kaldes. Vi så blandt andet på parametre, argumenter, returværdier, `void`, scope, overloading og `static`.

I dag flytter vi fokus fra **hvordan en metode skrives** til **hvordan metoder bruges til at designe klasser og programmer**.

Vi undersøger især:

- hvornår en metode skal være `static`
- hvornår en metode skal høre til et objekt
- om en metode skal være `public` eller `private`
- hvilken klasse en metode bør placeres i
- hvordan metoder kan samarbejde
- hvordan metoder kan beskrive et objekts adfærd

Målet er ikke blot at skrive kode, der virker. Målet er at skrive kode, hvor ansvar og funktionalitet er placeret tydeligt.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

- forklare forskellen på en `static` metode og en instansmetode
- afgøre, om en metode bør ligge i `Main` eller i en anden klasse
- forklare, hvornår en metode arbejder med et bestemt objekts tilstand
- forklare forskellen på `public` og `private` metoder
- bruge private hjælpemetoder til at skjule interne detaljer
- skrive metoder, der kalder andre metoder
- refaktorisere statiske hjælpemetoder til instansmetoder
- vælge metodenavne, der beskriver meningsfulde handlinger
- skelne mellem en generel setter og en metode, der beskriver adfærd
- læse og forstå et simpelt klassediagram for en enkelt klasse

> Et klassediagram er en del af UML (Unified Modeling Language). UML er et grafisk modelleringssprog, der bruges til at beskrive softwarestrukturer, ansvar og relationer mellem klasser. Et klassediagram viser derfor ikke hele koden, men en visuel oversigt over, hvordan klassen er bygget op.

## Se disse videoer før undervisningen

[static](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=7h14m07s) (til: 07:22:04)
[toString](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=7h46m08s) (til: 07:51:58)
[setters and getters](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=8h19m35s) (til: 08:29:39)

## Læs nedenstående før undervisningen

### Repetition: Vi har allerede brugt forskellige typer metoder

Du har allerede mødt metodekald som disse:

```java
scanner.nextLine();
random.nextInt(6);
Math.max(3, 7);
person.getAge();
```

De ligner hinanden, men metoderne kaldes ikke på samme måde.

```java
Math.max(3, 7);
```

`max()` kaldes direkte på klassen `Math`. Det er en `static` metode.

```java
random.nextInt(6);
```

`nextInt()` kaldes på objektet `random`. Det er en instansmetode.

```java
person.getAge();
```

`getAge()` kaldes på objektet `person`. Det er også en instansmetode.

Kort sagt:

- en `static` metode hører til en klasse
- en instansmetode hører til et objekt

I dag skal vi arbejde med, hvordan vi vælger mellem de to.

### Hvornår giver static mening?

En metode kan være `static`, når den ikke har brug for tilstanden i et bestemt objekt.

Se denne metode:

```java
public static int max(int a, int b) {
    if (a > b) {
        return a;
    }
    return b;
}
```

Metoden har alle de nødvendige værdier i parametrene `a` og `b`. Den behøver ikke kende et bestemt objekt.

Derfor kan den kaldes sådan:

```java
int largest = max(5, 9);
```

Andre eksempler på generelle hjælpemetoder kunne være:

```java
public static int square(int number) {
    return number * number;
}

public static boolean isEven(int number) {
    return number % 2 == 0;
}
```

Begge metoder får de nødvendige værdier gennem deres parametre.

### Hvornår bør en metode høre til et objekt?

Se denne klasse:

```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
```

Vi kunne skrive en statisk metode i `Main`, som undersøger, om en person er myndig:

```java
public static boolean isAdult(Person person) {
    return person.getAge() >= 18;
}
```

Metoden kaldes sådan:

```java
boolean adult = isAdult(person);
```

Men spørgsmålet om myndighed handler om en bestemt person og personens alder. Derfor kan metoden i stedet placeres i klassen `Person`:

```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean isAdult() {
        return age >= 18;
    }
}
```

Nu kan metoden kaldes sådan:

```java
boolean adult = person.isAdult();
```

Metoden behøver ikke modtage en `Person` som parameter. Når metoden kaldes på `person`, arbejder den allerede med netop dette objekts attributter.

En nyttig tommelfingerregel er:

- Hvis metoden udfører en generel beregning ud fra sine parametre, kan `static` give mening.
- Hvis metoden arbejder med et bestemt objekts data eller beskriver noget, objektet kan gøre, bør den ofte være en instansmetode.

### Metoder beskriver et objekts adfærd

En klasse beskriver ikke kun, hvilke data et objekt indeholder. Den kan også beskrive, hvad objektet kan gøre.

Se denne klasse:

```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void birthday() {
        age++;
    }
}
```

Metoden kaldes sådan:

```java
Person person = new Person("Anna", 23);
person.birthday();
```

Sammenlign det med:

```java
person.setAge(24);
```

Begge kald kan ændre alderen til 24, men de fortæller ikke det samme.

- `setAge(24)` beskriver en teknisk ændring af en værdi.
- `birthday()` beskriver en meningsfuld handling.

Et godt metodenavn fortæller, hvad der sker, uden at læseren behøver at kende alle detaljerne.

#### Alle objekter har allerede nogle metoder

Når vi opretter vores egne klasser, får objekterne ikke kun de metoder, vi selv skriver.

I Java arver alle klasser direkte eller indirekte fra klassen `Object`. Det betyder, at alle objekter automatisk har adgang til en række metoder, blandt andet:

- `toString()`
- `equals()`
- `hashCode()`

Du har måske allerede set noget lignende:

```java
Person person = new Person("Anna", 23);
System.out.println(person);
```

Når et objekt bruges sammen med `System.out.println()`, kalder Java automatisk objektets `toString()`-metode.

Hvis vi ikke selv skriver en `toString()`, vil Java bruge den version, der kommer fra `Object`. Resultatet kan eksempelvis se sådan ud:

```text
Person@6d06d69c
```

Det er sjældent særlig informativt.

Vi kan derfor overskrive metoden i vores egen klasse:

```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " (" + age + " år)";
    }
}
```

`@Override` fortæller, at metoden overskriver en metode, som klassen har arvet. Java kan dermed kontrollere, at metoden har det korrekte navn, de korrekte parametre og den korrekte returtype.

Nu bliver resultatet:

```text
Anna (23 år)
```

Bemærk, at `toString()` er en instansmetode. Den arbejder med det konkrete objekts attributter og kan derfor bruge `name` og `age` direkte.

`toString()` beskriver ikke nødvendigvis en handling, som objektet udfører. I stedet beskriver den, hvordan objektet skal repræsenteres som tekst.

#### Klassen visualiseret som et klassediagram

Når vi designer en klasse, kan vi bruge et klassediagram til hurtigt at få overblik over klassens attributter og metoder.

`Person`-klassen kan illustreres sådan:

```text
+----------------------------+
|           Person           |
+----------------------------+
| - name : String            |
| - age : int                |
+----------------------------+
| + birthday() : void        |
+----------------------------+
```

Diagrammet består af tre dele:

- øverst står klassens navn
- i midten står klassens attributter
- nederst står klassens metoder

Tegnene foran navne har en betydning:

- `+` betyder public
- `-` betyder private

Vi kan se, at et `Person`-objekt har attributterne `name` og `age`, og at andre klasser må kalde metoden `birthday()`.

Klassediagrammet viser ikke implementeringen af metoderne. Det viser klassens struktur og ansvar.

### Hvilken klasse skal metoden ligge i?

Se dette eksempel:

```java
public static void deposit(BankAccount account, double amount) {
    account.setBalance(account.getBalance() + amount);
}
```

Metoden ændrer en bankkontos saldo, men ligger uden for klassen `BankAccount`. Det betyder, at andre dele af programmet skal kende detaljerne om, hvordan saldoen ændres.

En bedre placering kan være i selve klassen:

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
            balance += amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}
```

Nu kan vi skrive:

```java
BankAccount account = new BankAccount("Anna", 1000);
account.deposit(500);
System.out.println(account.getBalance());
```

`BankAccount` har selv ansvaret for at kontrollere og ændre sin saldo.

Når du skal placere en metode, kan du spørge:

1. Hvilke data arbejder metoden med?
2. Hvilken klasse har disse data?
3. Beskriver metoden noget, et objekt af denne klasse kan gøre?
4. Kan metoden arbejde direkte med objektets attributter i stedet for at modtage objektet som parameter?

### public og private metoder

Du har tidligere brugt `private` til attributter:

```java
private double balance;
```

Det betyder, at attributten ikke kan tilgås direkte fra andre klasser.

Metoder kan også være `public` eller `private`.

En `public` metode kan kaldes fra andre klasser:

```java
public void deposit(double amount) {
    // ...
}
```

Fra `Main` kan vi derfor skrive:

```java
account.deposit(500);
```

En `private` metode kan kun kaldes inde fra den klasse, den er erklæret i:

```java
private boolean isValidAmount(double amount) {
    return amount > 0;
}
```

Fra `Main` kan vi ikke skrive:

```java
account.isValidAmount(500); // fejl
```

Metoden er en intern del af klassens arbejde.

En enkel huskeregel er:

- public bruges til funktionalitet, som andre klasser skal kunne anvende.
- private bruges til interne hjælpemetoder, som kun klassen selv har brug for.

#### Synlighed i et klassediagram

Når vi tegner klassediagrammer, kan vi også vise synligheden af attributter og metoder.

Se dette eksempel:

```text
+-------------------------------------+
|             BankAccount             |
+-------------------------------------+
| - balance : double                  |
+-------------------------------------+
| + deposit(double) : void            |
| + getBalance() : double             |
+-------------------------------------+
```

Her kan vi se:

- `balance` er privat
- `deposit()` er offentlig
- `getBalance()` er offentlig

Klassediagrammet giver dermed hurtigt overblik over, hvad andre klasser må bruge, og hvad der er skjult inde i klassen.

### Private hjælpemetoder

En metode kan blive lettere at forstå, hvis dele af arbejdet flyttes ud i tydeligt navngivne hjælpemetoder.

```java
public class BankAccount {
    private double balance;

    public void deposit(double amount) {
        if (isValidAmount(amount)) {
            balance += amount;
        }
    }

    private boolean isValidAmount(double amount) {
        return amount > 0;
    }
}
```

Her er:

- `deposit()` offentlig, fordi andre klasser skal kunne indsætte penge
- `isValidAmount()` privat, fordi den kun er et internt hjælpetrin

Klassen viser dermed kun den funktionalitet, som andre dele af programmet har brug for.

#### Klassediagram for BankAccount

Vi kan også vise private hjælpemetoder i et klassediagram.

```text
+--------------------------------------+
|             BankAccount              |
+--------------------------------------+
| - balance : double                   |
+--------------------------------------+
| + deposit(double) : void             |
| - isValidAmount(double) : boolean    |
+--------------------------------------+
```

Diagrammet viser tydeligt forskellen mellem klassens offentlige funktionalitet og dens interne implementering.

Andre klasser må kalde:

```java
account.deposit(500);
```

Men andre klasser må ikke kalde:

```java
account.isValidAmount(500);
```

fordi metoden er private.

### Metoder kan kalde andre metoder

Se denne klasse:

```java
public class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) {
        if (canWithdraw(amount)) {
            balance -= amount;
        }
    }

    private boolean canWithdraw(double amount) {
        return amount > 0 && amount <= balance;
    }

    public double getBalance() {
        return balance;
    }
}
```

Når `withdraw()` kaldes, kalder den selv `canWithdraw()`.

```java
BankAccount account = new BankAccount(1000);
account.withdraw(300);
System.out.println(account.getBalance());
```

`withdraw()` beskriver den handling, som andre klasser må bede kontoen om at udføre. `canWithdraw()` beskriver et internt tjek.

Det giver en tydelig fordeling af ansvar:

- den offentlige metode beskriver, hvad objektet tilbyder
- den private metode hjælper objektet med at udføre arbejdet korrekt

### Setter eller meningsfuld handling?

Det er ikke alle private attributter, der behøver en setter.

Se denne klasse:

```java
public class BankAccount {
    private double balance;

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
```

Hvis `setBalance()` er offentlig, kan andre klasser frit erstatte saldoen:

```java
account.setBalance(-1000000);
```

I stedet kan klassen tilbyde handlinger, der passer til en bankkonto:

```java
public void deposit(double amount) {
    if (amount > 0) {
        balance += amount;
    }
}

public void withdraw(double amount) {
    if (amount > 0 && amount <= balance) {
        balance -= amount;
    }
}
```

Kald som disse er mere præcise:

```java
account.deposit(500);
account.withdraw(300);
```

Metoderne beskriver både intentionen og reglerne for ændringen.

### Refaktorisering: fra `Main` til den relevante klasse

Antag, at vi begynder med denne kode i `Main`:

```java
public static void main(String[] args) {
    Person person = new Person("Anna", 23);

    if (person.getAge() >= 18) {
        System.out.println("Personen er myndig");
    } else {
        System.out.println("Personen er ikke myndig");
    }
}
```

Første trin kan være at flytte kontrollen til en statisk hjælpemetode:

```java
public static boolean isAdult(Person person) {
    return person.getAge() >= 18;
}
```

Men da kontrollen bruger personens egen alder, kan metoden placeres i `Person`:

```java
public boolean isAdult() {
    return age >= 18;
}
```

`Main` bliver nu kortere:

```java
public static void main(String[] args) {
    Person person = new Person("Anna", 23);

    if (person.isAdult()) {
        System.out.println("Personen er myndig");
    } else {
        System.out.println("Personen er ikke myndig");
    }
}
```

Refaktorisering betyder, at vi forbedrer kodens struktur uden at ændre det, programmet gør.

## Kan du forklare forskellen?

### Eksempel 1

```java
public static int square(int number) {
    return number * number;
}
```

Spørgsmål:

- Hvorfor kan denne metode være `static`?
- Hvilke oplysninger har metoden brug for?
- Hvor kommer oplysningerne fra?

### Eksempel 2

```java
public class Person {
    private int age;

    public boolean isAdult() {
        return age >= 18;
    }
}
```

Spørgsmål:

- Hvorfor er `isAdult()` en instansmetode?
- Hvilket objekts alder bruges?
- Hvorfor behøver metoden ikke modtage `age` som parameter?

### Eksempel 3

```java
public void withdraw(double amount) {
    if (canWithdraw(amount)) {
        balance -= amount;
    }
}

private boolean canWithdraw(double amount) {
    return amount > 0 && amount <= balance;
}
```

Spørgsmål:

- Hvorfor er `withdraw()` offentlig?
- Hvorfor kan `canWithdraw()` være privat?
- Hvilken metode kan kaldes fra `Main`?

### Eksempel 4

```java
public void setSpeed(int speed) {
    this.speed = speed;
}
```

Sammenlign med:

```java
public void accelerate() {
    speed += 10;
}

public void brake() {
    if (speed >= 10) {
        speed -= 10;
    }
}
```

Spørgsmål:

- Hvilke metoder beskriver tydeligst bilens adfærd?
- Hvilken løsning giver klassen mest kontrol over hastigheden?
- Skal en bil nødvendigvis have en offentlig `setSpeed()`?

### Eksempel 5

Se dette klassediagram:

```text
+---------------------------+
|            Car            |
+---------------------------+
| - speed : int             |
+---------------------------+
| + accelerate() : void     |
| + brake() : void          |
+---------------------------+
```

Spørgsmål:

- Hvilke attributter har klassen?
- Hvilke metoder kan kaldes fra andre klasser?
- Hvad betyder tegnet `+` foran metoderne?
- Hvad betyder tegnet `-` foran attributten?
- Hvordan kunne en Java-klasse se ud, hvis den skulle passe til diagrammet?

## Det vigtigste at tage med

- en static metode hører til klassen
- en instansmetode hører til et objekt
- en instansmetode kan arbejde direkte med objektets attributter
- generelle beregninger ud fra parametre kan ofte være `static`
- adfærd, der hører til et bestemt objekt, bør ofte placeres i objektets klasse
- en `public` metode kan kaldes fra andre klasser
- en `private` metode kan kun bruges internt i sin egen klasse
- private hjælpemetoder kan skjule interne detaljer
- metoder kan kalde andre metoder
- mange objekter bør kun tilbyde meningsfulde handlinger frem for generelle setters. 
- metodenavne som `birthday()`, `deposit()` og `withdraw()` beskriver tydeligere handlinger end generelle setters
- en klasse samler både data og den adfærd, der arbejder med dataene
- et klassediagram kan bruges til at visualisere en klasses struktur
- et klassediagram viser typisk klasse, attributter og metoder
- `+` betyder public
- `-` betyder private
- klassediagrammer gør det lettere at se ansvar og synlighed i en klasse

## Aktiviteter i undervisningen

Arbejd med disse [opgaver](opgaver.md).

I opgaverne skal du især være opmærksom på:

- om en metode bør være `static` eller en instansmetode
- hvilken klasse metoden naturligt hører til
- om metoden skal være `public` eller `private`
- om objektet selv kan arbejde med sine data
- om metodenavnet beskriver en tydelig handling
- om `Main` kan gøres kortere ved at flytte ansvar til andre klasser
