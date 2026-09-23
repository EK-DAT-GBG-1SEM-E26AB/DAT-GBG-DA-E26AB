# Opgaver – Repetition 2: Objektorienteret

Opgaverne er delt op sådan:

* **Del A** – forudsig output
* **Del B** ★ – én eller to klasser
* **Del C** ★★ – en klasse, der holder på andre objekter
* **Del D** ★★★ – arv, abstrakte klasser og interfaces

Lav opgaverne i projektet `uge46-repetition`, package `dag2_objektorienteret`. Fordi opgaverne har
mange klasser, får hver opgave sin egen under-package, fx `dag2_objektorienteret.terninger` – så
støder klassenavnene ikke sammen.

**Regel for hele dagen:** kun `main` må skrive ud. Klasserne **returnerer** tekst og tal – som
`UserInterface`-reglen i jeres projekter.

Der er [vejledende løsninger](loesninger.md) – men prøv selv først.

---

# Del A – Forudsig output

> **Skriv dit svar ned, før du kører koden.**

Opgaverne bruger disse klasser (package `dag2_objektorienteret.forudsig`):

```java
public class Counter {

    private int count;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
```

```java
public class Ticket {

    private static int nextNumber = 1;   // fælles for ALLE billetter
    private int number;                  // hver billet har sit eget

    public Ticket() {
        number = nextNumber;
        nextNumber++;
    }

    public int getNumber() {
        return number;
    }
}
```

```java
public abstract class Animal {

    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public abstract String getSound();

    public String describe() {
        return name + " siger " + getSound();
    }
}
```

```java
public class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public String getSound() {
        return "vov";
    }
}
```

```java
public class Puppy extends Dog {

    public Puppy(String name) {
        super(name);
    }

    @Override
    public String describe() {
        return super.describe() + " (men meget lavt)";
    }
}
```

```java
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
```

## Opgave A1 – To variable

```java
Counter c1 = new Counter();
Counter c2 = c1;
c2.increment();
System.out.println(c1.getCount());
c2 = new Counter();
c2.increment();
c2.increment();
System.out.println(c1.getCount() + " " + c2.getCount());
```

Tegn pilene på papir efter hver linje.

## Opgave A2 – static

```java
Ticket t1 = new Ticket();
Ticket t2 = new Ticket();
Ticket t3 = new Ticket();
System.out.println(t1.getNumber() + " " + t2.getNumber() + " " + t3.getNumber());
```

Hvad ville der ske, hvis `number` **også** var `static`?

## Opgave A3 – Hundene

```java
ArrayList<Animal> animals = new ArrayList<>();
animals.add(new Dog("Fido"));
animals.add(new Puppy("Bella"));
for (Animal animal : animals) {
    System.out.println(animal.describe());
}
```

`Puppy` har ingen `getSound()`. Hvor kommer hvalpens lyd fra?

## Opgave A4 – remove

```java
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(10);
numbers.add(20);
numbers.add(30);
numbers.add(40);
numbers.remove(1);
System.out.println(numbers);
numbers.remove(Integer.valueOf(30));
System.out.println(numbers);
```

## Opgave A5 – Ens eller det samme?

```java
Point p1 = new Point(1, 2);
Point p2 = new Point(1, 2);
Point p3 = p1;
System.out.println(p1 == p2);
System.out.println(p1.equals(p2));
System.out.println(p1 == p3);
```

Hvorfor giver `equals` ikke `true`, når den gør det for `String`?

## Opgave A6 – Mod nord

```java
public class Room {

    private String name;
    private Room north;

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Room getNorth() {
        return north;
    }
}
```

```java
Room hall = new Room("Hallen");
System.out.println(hall.getName());
System.out.println(hall.getNorth().getName());
System.out.println("Færdig");
```

Hvad bliver skrevet ud – og hvad sker der så? Hvordan skulle koden have tjekket det?

---

# Del B ★ – Én eller to klasser

## Opgave 1 – Raflebæger

1. Lav en klasse `Die` (en terning) med en attribut `value` og en metode `int roll()`, der slår et
   tilfældigt tal fra 1 til 6, gemmer det og returnerer det. Lav også `getValue()`.
2. Lav en klasse `DiceCup` med en `ArrayList<Die>` og en constructor, der får antallet af
   terninger.
3. Giv `DiceCup` en metode `int shake()`, der slår med alle terningerne og returnerer summen.
4. Giv `DiceCup` en metode `ArrayList<Integer> getValues()`, der returnerer terningernes øjne fra
   **sidste** rystelse – uden at ryste igen.

Test det med fem terninger og tre rystelser. Tallene er tilfældige, men summen skal passe med
øjnene:

```text
[4, 6, 1, 6, 1] = 18
[2, 3, 1, 3, 5] = 14
[1, 6, 5, 1, 3] = 16
```

> **Tip:** `Random random = new Random();` og `random.nextInt(6)` giver et tal fra 0 til 5. Husk
> `import java.util.Random;`.

## Opgave 2 – Bil med trailer

1. Lav klasserne `Car` (nummerplade og vægt) og `Trailer` (vægt). En bil kan **have** en trailer –
   eller ingen.
2. Lav `int getTotalWeight()` på `Car`: bilens vægt plus en eventuel trailers vægt.
3. Lav `boolean attachTrailer(Trailer trailer)`, der kun kobler traileren på, hvis totalvægten
   **ikke** kommer over 3500 kg – og kun, hvis der ikke allerede sidder en trailer. Den returnerer,
   om det lykkedes.
4. Lav `detachTrailer()`, `hasTrailer()` og en `toString`.

```java
Car car = new Car("AB 12 345", 1800);
Trailer small = new Trailer(750);
Trailer big = new Trailer(2000);

System.out.println(car);
System.out.println("Stor trailer: " + car.attachTrailer(big));
System.out.println("Lille trailer: " + car.attachTrailer(small));
System.out.println(car);
System.out.println("Stor trailer oveni: " + car.attachTrailer(big));
car.detachTrailer();
System.out.println(car + ", trailer: " + car.hasTrailer());
```

```text
AB 12 345: 1800 kg
Stor trailer: false
Lille trailer: true
AB 12 345: 2550 kg
Stor trailer oveni: false
AB 12 345: 1800 kg, trailer: false
```

## Opgave 3 – Kort

1. Lav en enum `Suit` med `CLUBS`, `DIAMONDS`, `HEARTS` og `SPADES` (klør, ruder, hjerter, spar) –
   i den rækkefølge.
2. Lav en klasse `Card` med en `Suit` og en værdi fra 1 til 13, og en `toString`, der skriver fx
   `HEARTS 10`.
3. Lav `boolean beats(Card other)`, der returnerer `true`, hvis dette korts værdi er højere end det
   andets. Kuløren er ligegyldig.
4. Lav `boolean beatsWithSuit(Card other)`: ved **samme** værdi afgør kuløren – spar slår hjerter,
   som slår ruder, som slår klør.

```text
CLUBS 13 slår HEARTS 10: true
SPADES 10 slår HEARTS 10: false
SPADES 10 slår HEARTS 10 (med kulør): true
HEARTS 10 slår SPADES 10 (med kulør): false
HEARTS 10 slår CLUBS 13 (med kulør): false
```

> **Tip:** Enum-værdier kan sammenlignes med `compareTo` – ligesom tekst. Rækkefølgen er den, de
> står i, i enum'en.

---

# Del C ★★ – Objekter, der holder på objekter

## Opgave 4 – Valget

1. Lav en klasse `Candidate` med navn, parti og antal stemmer.
2. Lav en klasse `Election`, der holder på en liste af kandidater, med metoderne:
   * `addCandidate(Candidate candidate)`
   * `int getTotalVotes()`
   * `ArrayList<Candidate> getCandidatesFromParty(String party)` – uanset store og små bogstaver
   * `int getVotesForParty(String party)` – brug metoden ovenfor
   * `Candidate getWinner()` – kandidaten med flest stemmer, eller `null`, hvis der ingen er

```java
election.addCandidate(new Candidate("Maja", "Grønt Parti", 1200));
election.addCandidate(new Candidate("Omar", "Byens Liste", 950));
election.addCandidate(new Candidate("Lise", "Grønt Parti", 430));
election.addCandidate(new Candidate("Peter", "Frihedslisten", 1310));
```

```text
Stemmer i alt: 3890
Grønt Parti: [Maja (Grønt Parti): 1200, Lise (Grønt Parti): 430]
Stemmer til Grønt Parti: 1630
Flest personlige stemmer: Peter (Frihedslisten): 1310
Tomt valg: null
```

## Opgave 5 – Opslagsord

Et opslagsværk har opslagsord, der kan henvise til hinanden ("se også").

1. Lav en klasse `Keyword` med `name` og `description` og en `ArrayList<Keyword>` med henvisninger.
2. Lav `addSeeAlso(Keyword keyword)`.
3. Lav `boolean matches(String searchText)`, der er sand, hvis søgeteksten er en del af navnet –
   uanset store og små bogstaver.
4. Lav `String describe()`, der returnerer navn og beskrivelse – og, **hvis** der er henvisninger,
   en linje med dem, adskilt af komma.

```text
Arv: En klasse kan arve fra en anden med extends.
  Se også: Polymorfi, Abstrakt klasse
Polymorfi: Det samme metodekald kan gøre forskellige ting.
  Se også: Arv
Abstrakt klasse: En klasse, man ikke kan lave objekter af.
true
```

(Den sidste linje er `abstractClass.matches("KLASSE")`.)

`"\n"` i en `String` giver et linjeskift.

## Opgave 6 – Bundkortet

Et bundkort har plads til **fire** harddiske (SATA-porte). Brug et **array** – ikke en liste – for
der er et fast antal pladser.

1. Lav en klasse `SataDrive` med model og størrelse i GB.
2. Lav en klasse `MotherBoard` med et `SataDrive[]` med fire pladser, hvor `null` er en ledig
   plads.
3. `int connect(SataDrive drive)` sætter drevet i den **første ledige** port og returnerer portens
   nummer – eller `-1`, hvis boardet er fyldt.
4. `disconnect(int port)` gør porten ledig igen.
5. `int getTotalSizeInGb()` og `String describe()`.

```java
System.out.println(board.connect(new SataDrive("Samsung 870", 1000)));
System.out.println(board.connect(new SataDrive("WD Blue", 2000)));
System.out.println(board.connect(new SataDrive("Crucial MX500", 500)));
board.disconnect(1);
System.out.println(board.connect(new SataDrive("Seagate", 4000)));
System.out.println(board.connect(new SataDrive("Kingston", 250)));
System.out.println(board.connect(new SataDrive("Toshiba", 1000)));
System.out.println(board.describe());
```

```text
0
1
2
1
3
-1
Port 0: Samsung 870 (1000 GB)
Port 1: Seagate (4000 GB)
Port 2: Crucial MX500 (500 GB)
Port 3: Kingston (250 GB)
I alt: 5750 GB
```

---

# Del D ★★★ – Arv, abstrakte klasser og interfaces

## Opgave 7 – Medier

Lav en **abstrakt** klasse `Media` med navn og varighed i sekunder, og to subklasser:

* `Audio` har en lydstyrke i dB, fx `-10.4`
* `Video` har et billedformat, fx `"16:9"` eller `"4:3"`

`Media` skal have en **abstrakt** metode `String getDetails()`, som hver subklasse implementerer,
og en **almindelig** metode `String getInfo()`, der returnerer navn, varighed som `m:ss` og
detaljerne. Læg fire medier i en `ArrayList<Media>`, udskriv info om hvert, og læg varighederne
sammen:

```java
library.add(new Audio("Podcast: Java på 10 minutter", 605, -16.0));
library.add(new Video("Introfilm", 94, "16:9"));
library.add(new Audio("Jingle", 7, -10.4));
library.add(new Video("Gammel reklame", 30, "4:3"));
```

```text
Podcast: Java på 10 minutter [10:05] lyd, -16.0 dB
Introfilm [1:34] video, 16:9
Jingle [0:07] lyd, -10.4 dB
Gammel reklame [0:30] video, 4:3
Samlet varighed: 736 sekunder
```

**Krav:** der må ikke stå `instanceof` nogen steder. I morgen skriver vi listen til en fil.

## Opgave 8 – Figurer

1. Lav et **interface** `Shape` med `double getArea()` og `String getName()`.
2. Lav `Square` (sidelængde) og `Circle` (radius), der implementerer det. Arealet af en cirkel er
   `Math.PI * r * r`.
3. Læg figurerne i en `ArrayList<Shape>`. Udskriv navn og areal for hver, det samlede areal og
   navnet på den største figur.
4. Tilføj en `Triangle` (grundlinje og højde, areal = g × h / 2). Hvor mange linjer i `main`
   skulle du ændre – ud over den linje, der tilføjer trekanten?

```java
shapes.add(new Square(3));
shapes.add(new Circle(2));
shapes.add(new Square(1.5));
shapes.add(new Circle(0.5));
shapes.add(new Triangle(4, 5));
```

```text
kvadrat med side 3.0: 9.00
cirkel med radius 2.0: 12.57
kvadrat med side 1.5: 2.25
cirkel med radius 0.5: 0.79
trekant 4.0 x 5.0: 10.00
Samlet areal: 34.60
Største figur: cirkel med radius 2.0
```

> **Tip:** `String.format(Locale.US, "%.2f", area)` giver to decimaler med punktum (fra
> [28-08](../../35/05_fre_2026-08-28/opgaver.md)).

Hvorfor er `Shape` et interface og ikke en abstrakt klasse? Kunne det have været en abstrakt
klasse?

## Udfordring – Sortér figurerne

Lav en `AreaComparator implements Comparator<Shape>`, og sortér figurerne efter areal, mindste
først. `Double.compare(a, b)` virker som `Integer.compare` – bare for kommatal.

```text
cirkel med radius 0.5: 0.79
kvadrat med side 1.5: 2.25
kvadrat med side 3.0: 9.00
trekant 4.0 x 5.0: 10.00
cirkel med radius 2.0: 12.57
```
