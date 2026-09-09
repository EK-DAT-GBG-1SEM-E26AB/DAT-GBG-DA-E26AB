# Opgaver – Metoders placering, synlighed og ansvar

I disse opgaver arbejder du videre med metoder.

Opgaverne bygger på principperne fra undervisningsmaterialet, men anvender dem på andre klasser og problemområder.

Fokus er på:

- forskellen på `static` metoder og instansmetoder
- placering af metoder i den relevante klasse
- `public` og `private` metoder
- private hjælpemetoder
- metoder, der kalder andre metoder
- beskyttelse af objekters tilstand
- tydelige metodenavne

## Kom i gang

Opret et nyt Java-projekt i IntelliJ.

Opret klassen `Main` med en `main`-metode:

```java
public class Main {
    public static void main(String[] args) {
    }
}
```

Når du opretter klasser som `LibraryBook`, `Course` og `CoffeeMachine`, skal hver klasse ligge i sin egen Java-fil.

Afprøv løbende dine løsninger fra `main`.

## Del 1 – Genkend metoderne

### Opgave 1 – Klasse eller objekt?

Se på følgende metodekald:

```java
Math.max(4, 9);
scanner.nextLine();
random.nextInt(6);
book.borrow();
course.hasAvailableSeats();
```

Besvar for hvert kald:

- Kaldes metoden på en klasse eller på et objekt?
- Er det en `static` metode eller en instansmetode?
- Modtager metoden argumenter?
- Ser metoden ud til at returnere en værdi eller udføre en handling?

### Opgave 2 – Undersøg synlighed

Opret klassen:

```java
public class Message {
    public void printMessage() {
        System.out.println("Hej fra Message");
    }

    private void printSecret() {
        System.out.println("Dette er en intern besked");
    }
}
```

Opret et objekt i `Main`:

```java
Message message = new Message();
```

Prøv derefter:

```java
message.printMessage();
message.printSecret();
```

Besvar:

- Hvilket kald virker?
- Hvilket kald giver en fejl?
- Hvad fortæller fejlmeddelelsen?
- Hvor i programmet kan `printSecret()` kaldes?

Tilføj nu denne metode til `Message`:

```java
public void printAll() {
    printMessage();
    printSecret();
}
```

Kald `printAll()` fra `Main`.

Forklar, hvorfor `printAll()` kan kalde begge metoder.

### Opgave 3 – Tekstrepræsentation af et objekt

Opret klassen:

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

I `Main` opretter du et objekt:

```java
Person person = new Person("Anna", 23);
System.out.println(person);
```

Besvar:

- Hvad vises i konsollen?
- Hvorfor bliver det ikke en brugbar tekstbeskrivelse?
- Hvilken metode bruges automatisk, når et objekt udskrives?
- Hvad beskriver `toString()`?

Løsningen er at overskrive `toString()` i `Person`:

```java
@Override
public String toString() {
    return name + " (" + age + " år)";
}
```

Kald nu igen:

```java
System.out.println(person);
```

Besvar herefter:

- Hvorfor er `toString()` en instansmetode?
- Hvilken information bruger den?
- Hvad er formålet med at skrive en egen `toString()`-metode?
- Er `@Override` nødvendigt for, at programmet virker?
- Hvad er fordelen ved `@Override`, selvom det teknisk set er valgfrit?

#### Tænk over

`@Override` er ikke nødvendigt for at få et program til at køre, men den er stadig nyttig.

Forklar:

- hvorfor Java ikke skal have `@Override` for at overskrive en metode
- hvorfor `@Override` gør koden mere sikker og lettere at forstå
- hvordan `@Override` kan hjælpe med at fange stavefejl i metodenavnet eller fejlagtige parametre

## Del 2 – En biblioteksbog

### Opgave 4 – Opret klassen LibraryBook

Opret klassen:

```java
public class LibraryBook {
    private String title;
    private boolean borrowed;

    public LibraryBook(String title) {
        this.title = title;
        this.borrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBorrowed() {
        return borrowed;
    }
}
```

Opret to bøger i `Main` og udskriv deres titel og udlånsstatus.

Eksempel:

```java
LibraryBook book1 = new LibraryBook("Clean Code");
LibraryBook book2 = new LibraryBook("The Pragmatic Programmer");
```

### Opgave 5 – Lån en bog

Tilføj en offentlig metode:

```java
public void borrow()
```

Metoden skal ændre `borrowed` til `true`, hvis bogen ikke allerede er udlånt.

Afprøv:

```java
book1.borrow();
System.out.println(book1.isBorrowed());
```

Kald derefter `borrow()` endnu en gang på samme objekt.

Tilføj en relevant besked, hvis bogen allerede er udlånt.

### Opgave 6 – Privat kontrolmetode

Flyt kontrollen af, om bogen kan lånes, til en privat metode:

```java
private boolean canBeBorrowed()
```

Metoden skal returnere `true`, hvis bogen ikke er udlånt.

Lad `borrow()` kalde `canBeBorrowed()`.

Prøv at kalde `canBeBorrowed()` fra `Main`.

Besvar:

- Hvorfor kan `borrow()` kaldes fra `Main`?
- Hvorfor kan `canBeBorrowed()` ikke kaldes fra `Main`?
- Hvorfor er det passende, at kontrollen er intern i klassen?

### Opgave 7 – Aflever en bog

Tilføj en offentlig metode:

```java
public void returnBook()
```

Metoden skal:

- ændre `borrowed` til `false`, hvis bogen er udlånt
- udskrive en relevant besked, hvis bogen ikke er udlånt

Flyt eventuelt kontrollen til en privat hjælpemetode:

```java
private boolean canBeReturned()
```

Afprøv følgende rækkefølge:

1. Aflever en bog, der ikke er udlånt.
2. Lån bogen.
3. Lån den samme bog igen.
4. Aflever bogen.
5. Aflever den samme bog igen.

## Del 3 – Et valgfag med begrænset antal pladser

### Opgave 8 – Opret klassen Course

Opret klassen:

```java
public class Course {
    private String name;
    private int numberOfStudents;
    private int maximumNumberOfStudents;

    public Course(String name, int maximumNumberOfStudents) {
        this.name = name;
        this.maximumNumberOfStudents = maximumNumberOfStudents;
        this.numberOfStudents = 0;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }
}
```

Opret et valgfag med plads til tre studerende.

Udskriv fagets navn og det aktuelle antal tilmeldte.

### Opgave 9 – Er der en ledig plads?

Tilføj metoden:

```java
public boolean hasAvailableSeats()
```

Metoden skal returnere `true`, når antallet af studerende er mindre end det maksimale antal.

Afprøv resultatet fra `Main`.

Forklar:

- Hvorfor er `hasAvailableSeats()` en instansmetode?
- Hvilket objekts oplysninger bruger metoden?
- Hvorfor behøver antal studerende ikke blive sendt ind som argument?

### Opgave 10 – Tilmeld en studerende

Tilføj metoden:

```java
public void enrollStudent()
```

Metoden skal:

- kalde `hasAvailableSeats()`
- øge `numberOfStudents` med 1, hvis der er plads
- udskrive en besked, hvis holdet er fyldt

Kald metoden fire gange på et fag med tre pladser.

Kontrollér efter hvert kald, hvor mange studerende der er tilmeldt.

#### Tænk over

Sammenlign:

```java
course.enrollStudent();
```

med en tænkt setter:

```java
course.setNumberOfStudents(3);
```

- Hvilket kald beskriver bedst handlingen?
- Hvilken løsning sørger selv for at kontrollere kapaciteten?
- Hvorfor bør andre klasser ikke frit kunne ændre antallet af tilmeldte?

### Opgave 11 – Afmeld en studerende

Tilføj metoden:

```java
public void removeStudent()
```

Antallet af studerende må aldrig blive negativt.

Flyt kontrollen til en privat metode:

```java
private boolean hasStudents()
```

Lad `removeStudent()` kalde `hasStudents()`.

Afprøv både gyldige og ugyldige afmeldinger.

## Del 4 – Refaktorisering af CoffeeMachine

### Opgave 12 – Kode med uhensigtsmæssigt ansvar

Opret følgende klasser:

```java
public class CoffeeMachine {
    public int waterInMilliliters;

    public CoffeeMachine(int waterInMilliliters) {
        this.waterInMilliliters = waterInMilliliters;
    }
}
```

```java
public class Main {
    public static void brewCoffee(CoffeeMachine machine) {
        if (machine.waterInMilliliters >= 200) {
            machine.waterInMilliliters -= 200;
            System.out.println("Kaffen er klar");
        } else {
            System.out.println("Der er ikke vand nok");
        }
    }

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine(500);
        brewCoffee(machine);
        System.out.println(machine.waterInMilliliters);
    }
}
```

Programmet kan brygge kaffe, men ansvaret er placeret uhensigtsmæssigt.

Besvar først:

- Hvilken klasse har oplysningerne om vandmængden?
- Hvilken klasse ændrer vandmængden?
- Kan `Main` give maskinen en negativ vandmængde?
- Beskriver kaffebrygning noget, `Main` gør, eller noget kaffemaskinen gør?

### Opgave 13 – Flyt adfærden til objektet

Foretag følgende ændringer:

1. Gør `waterInMilliliters` privat.
2. Flyt `brewCoffee()` fra `Main` til `CoffeeMachine`.
3. Gør `brewCoffee()` til en offentlig instansmetode.
4. Tilføj en getter til vandmængden.
5. Ændr kaldet i `Main` til:

```java
machine.brewCoffee();
```

Afprøv programmet igen.

Forklar:

- Hvorfor skal `brewCoffee()` ikke længere modtage en `CoffeeMachine` som parameter?
- Hvorfor skal metoden ikke være `static`?
- Hvorfor er det bedre, at vandmængden er privat?

### Opgave 14 – Privat kontrol af vandmængden

Tilføj en privat metode:

```java
private boolean hasEnoughWater()
```

Metoden skal kontrollere, om der er mindst 200 ml vand.

Lad `brewCoffee()` kalde `hasEnoughWater()`.

Tilføj derefter en offentlig metode:

```java
public void refillWater(int amount)
```

Kun positive mængder må tilføjes.

Flyt kontrollen til en privat metode:

```java
private boolean isValidWaterAmount(int amount)
```

Afprøv:

- brygning med vand nok
- brygning uden vand nok
- opfyldning med en positiv værdi
- opfyldning med 0
- opfyldning med en negativ værdi

## Del 5 – Sammenlign objekter

### Opgave 15 – Opret GameCharacter

Opret klassen:

```java
public class GameCharacter {
    private String name;
    private int strength;

    public GameCharacter(String name, int strength) {
        this.name = name;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }
}
```

Opret mindst to figurer med forskellige styrker.

### Opgave 16 – Sammenlign to figurer

Tilføj metoden:

```java
public boolean isStrongerThan(GameCharacter other)
```

Metoden skal returnere `true`, hvis dette objekt har en højere styrke end `other`.

Afprøv eksempelvis:

```java
GameCharacter warrior = new GameCharacter("Warrior", 15);
GameCharacter wizard = new GameCharacter("Wizard", 10);

if (warrior.isStrongerThan(wizard)) {
    System.out.println(warrior.getName() + " er stærkest");
}
```

Besvar:

- Hvilket objekt er `this` i kaldet?
- Hvilket objekt modtages gennem parameteren `other`?
- Hvorfor er metoden ikke `static`?
- Hvilke to objekters tilstand sammenlignes?

### Opgave 17 – Gør figuren stærkere

Tilføj metoden:

```java
public void train(int amount)
```

Figurens styrke må kun øges med en positiv værdi.

Flyt kontrollen til en privat metode:

```java
private boolean isValidTrainingAmount(int amount)
```

Afprøv træning med:

- en positiv værdi
- 0
- en negativ værdi

Sammenlign derefter figurerne igen.

## Del 6 – Et hotelværelse

### Opgave 18 – Opret HotelRoom

Opret klassen:

```java
public class HotelRoom {
    private int roomNumber;
    private boolean occupied;

    public HotelRoom(int roomNumber) {
        this.roomNumber = roomNumber;
        this.occupied = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }
}
```

Opret to hotelværelser og udskriv deres status.

### Opgave 19 – Indtjekning og udtjekning

Tilføj metoderne:

```java
public void checkIn()
public void checkOut()
```

Regler:

- der kan kun tjekkes ind på et ledigt værelse
- der kan kun tjekkes ud fra et optaget værelse
- `occupied` må ikke kunne ændres direkte fra `Main`

Opret private hjælpemetoder efter behov, eksempelvis:

```java
private boolean canCheckIn()
private boolean canCheckOut()
```

Afprøv:

1. Status på et nyt værelse.
2. En gyldig indtjekning.
3. Et nyt indtjekningsforsøg på samme værelse.
4. En gyldig udtjekning.
5. Et nyt udtjekningsforsøg.

### Opgave 20 – Beskriv værelset

Tilføj metoden:

```java
public String describe()
```

Den skal eksempelvis returnere:

```text
Værelse 101 er ledigt
```

eller:

```text
Værelse 101 er optaget
```

Lad `describe()` bruge `isOccupied()`.

Udskriv beskrivelsen før og efter indtjekning.

## Del 7 – Find fejlene

### Opgave 21 – Forkert brug af static

Se på følgende klasse:

```java
public class Counter {
    private int value;

    public static void increase() {
        value++;
    }
}
```

Besvar:

- Hvorfor giver `value++` en fejl?
- Hører `value` til klassen eller til et bestemt objekt?
- Skal `increase()` være `static` eller en instansmetode?

Ret klassen, opret to objekter og kald `increase()` forskelligt antal gange på dem.

Vis, at objekterne har hver sin tilstand.

### Opgave 22 – For bred synlighed

Se på følgende klasse:

```java
public class PasswordChecker {
    public boolean isLongEnough(String password) {
        return password.length() >= 8;
    }

    public void registerPassword(String password) {
        if (isLongEnough(password)) {
            System.out.println("Adgangskoden er registreret");
        } else {
            System.out.println("Adgangskoden er for kort");
        }
    }
}
```

Overvej:

- Skal andre klasser kunne registrere en adgangskode?
- Skal andre klasser nødvendigvis kende den interne kontrolmetode?
- Hvilken metode er klassens offentlige handling?
- Hvilken metode kan betragtes som en intern hjælpemetode?

Ændr synligheden, så kun den nødvendige funktionalitet er offentlig.

### Opgave 23 – En setter omgår reglerne

Se på følgende klasse:

```java
public class Thermostat {
    private double temperature;

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void increaseTemperature() {
        if (temperature < 30) {
            temperature++;
        }
    }
}
```

Den ønskede regel er, at temperaturen altid skal være mellem 5 og 30 grader.

Besvar:

- Hvordan kan `setTemperature()` omgå reglen?
- Skal setteren fjernes, gøres privat eller have validering?
- Hvilke handlinger skal andre klasser have lov til at udføre?

Ret klassen, så temperaturen ikke kan komme uden for intervallet.

Tilføj eventuelt:

```java
public void decreaseTemperature()
private boolean isValidTemperature(double temperature)
```

Afprøv begge grænser.

## Udfordring – En CoffeeCard

### Opgave 24 – Design og implementér klassen

Et kaffekort indeholder et antal klip. Et nyt kort oprettes med et bestemt antal klip.

Opret klassen:

```java
public class CoffeeCard {
    private String owner;
    private int clips;
}
```

Klassen skal have:

- en konstruktør
- en getter til ejerens navn
- en getter til antal klip
- en offentlig metode til at bruge et klip
- en offentlig metode til at tilføje klip
- en privat metode, der kontrollerer, om et klip kan bruges
- en privat metode, der validerer det antal klip, der tilføjes
- en metode, der returnerer en beskrivelse af kortet

Regler:

- antal klip må aldrig være negativt
- der kan kun bruges et klip, hvis kortet har mindst ét klip
- der må kun tilføjes et positivt antal klip
- andre klasser må ikke sætte antal klip direkte

Afprøv mindst:

1. Et nyt kort.
2. Brug af et klip.
3. Gentagne kald, indtil kortet er tomt.
4. Forsøg på at bruge et tomt kort.
5. Tilføjelse af nye klip.
6. Forsøg på at tilføje 0 eller et negativt antal.

#### Tænk over

- Hvilke metoder skal være `public`?
- Hvilke metoder skal være `private`?
- Hvorfor er handlingerne instansmetoder?
- Hvorfor bør klassen ikke have en almindelig `setClips()`?
- Hvilke metoder kalder andre metoder?

## Opsamling

Når du er færdig med opgaverne, skal du kunne forklare:

- forskellen på en `static` metode og en instansmetode
- hvorfor en instansmetode kan arbejde direkte med objektets attributter
- hvordan man vurderer, hvilken klasse en metode bør ligge i
- forskellen på en `public` og en `private` metode
- hvorfor private hjælpemetoder kan være nyttige
- hvordan en offentlig metode kan kalde en privat metode
- hvorfor et objekts attributter normalt ikke ændres direkte fra `Main`
- hvorfor metoder som `borrow()`, `enrollStudent()`, `brewCoffee()` og `checkIn()` beskriver objektets adfærd
- hvorfor en meningsfuld handling i nogle tilfælde er bedre end en generel setter
