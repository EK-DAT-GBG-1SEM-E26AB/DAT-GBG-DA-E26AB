# Opgaver – Enum og switch

Brug `main`-metoden til at afprøve dine løsninger.

---

# Opgave 1 – Ugedage

Opret en enum med navnet `Day`.

Den skal indeholde:

```text
MONDAY
TUESDAY
WEDNESDAY
THURSDAY
FRIDAY
SATURDAY
SUNDAY
```

Opret derefter en variabel:

```java
Day day = Day.WEDNESDAY;
```

Udskriv værdien af `day`.

### Prøv også

Ændr værdien til `Day.SATURDAY` og kør programmet igen.

---

# Opgave 2 – Sammenlign enum-værdier

Brug din `Day` enum fra opgave 1.

Opret:

```java
Day day = Day.SATURDAY;
```

Skriv en `if`-sætning, der undersøger om dagen er `SATURDAY`.

Hvis det er tilfældet, skal programmet skrive:

```text
Det er lørdag
```

### Ekstra

Udvid programmet, så det skriver:

```text
Det er weekend
```

hvis dagen er enten `SATURDAY` eller `SUNDAY`.

---

# Opgave 3 – Trafiklys med switch

Opret denne enum:

```java
public enum TrafficLight {
    RED,
    YELLOW,
    GREEN
}
```

Opret derefter:

```java
TrafficLight light = TrafficLight.RED;
```

Brug en moderne `switch` med `->`.

Programmet skal skrive:

| Værdi    | Udskrift   |
| -------- | ---------- |
| `RED`    | `Stop`     |
| `YELLOW` | `Gør klar` |
| `GREEN`  | `Kør`      |

Eksempel:

```java
switch (light) {
    // Din kode
}
```

Prøv programmet med alle tre værdier.

---

# Opgave 4 – Størrelser

Opret en enum:

```java
Size
```

med værdierne:

```text
SMALL
MEDIUM
LARGE
```

Opret en variabel:

```java
Size size = Size.MEDIUM;
```

Brug `switch` til at skrive:

```text
Lille størrelse
Mellem størrelse
Stor størrelse
```

afhængigt af værdien.

---

# Opgave 5 – switch som expression

Brug din `Size` enum fra den forrige opgave.

En størrelse skal have følgende pris:

| Størrelse | Pris |
| --------- | ---: |
| `SMALL`   |   25 |
| `MEDIUM`  |   35 |
| `LARGE`   |   45 |

Brug en `switch` expression til at beregne prisen.

Start eksempelvis med:

```java
Size size = Size.MEDIUM;

int price = switch (size) {
    // Din kode
};

System.out.println("Pris: " + price);
```

Programmet skal i dette tilfælde skrive:

```text
Pris: 35
```

---

# Opgave 6 – Karakter

Opret en enum:

```java
Grade
```

med værdierne:

```text
A
B
C
D
E
F
```

Opret derefter:

```java
Grade grade = Grade.B;
```

Brug en `switch` til at udskrive en tekst.

Eksempel:

| Karakter | Tekst               |
| -------- | ------------------- |
| `A`      | `Fremragende`       |
| `B`      | `Meget godt`        |
| `C`      | `Godt`              |
| `D`      | `Tilfredsstillende` |
| `E`      | `Bestået`           |
| `F`      | `Ikke bestået`      |

---

# Opgave 7 – Flere cases med samme resultat

Brug enum'en `Day`.

Programmet skal skrive:

```text
Weekend
```

for:

```text
SATURDAY
SUNDAY
```

og:

```text
Hverdag
```

for alle andre dage.

Prøv at gøre det med så lidt gentaget kode som muligt.

Hint: Flere værdier kan angives i samme `case`:

```java
case SATURDAY, SUNDAY -> ...
```

---

# Opgave 8 – OrderStatus

Opret en enum:

```java
OrderStatus
```

med værdierne:

```text
NEW
PAID
SHIPPED
DELIVERED
```

Opret:

```java
OrderStatus status = OrderStatus.PAID;
```

Brug `switch` til at vise en passende besked:

| Status      | Besked               |
| ----------- | -------------------- |
| `NEW`       | `Ordren er oprettet` |
| `PAID`      | `Ordren er betalt`   |
| `SHIPPED`   | `Ordren er sendt`    |
| `DELIVERED` | `Ordren er leveret`  |

Afprøv alle fire værdier.

---

# Opgave 9 – Enum som attribut i en klasse

Opret en enum:

```java
TaskStatus
```

med værdierne:

```text
TODO
IN_PROGRESS
DONE
```

Opret derefter en klasse:

```java
Task
```

Klassen skal have attributterne:

```java
private String description;
private TaskStatus status;
```

Lav en constructor, så begge værdier kan sættes, når objektet oprettes.

Eksempel:

```java
Task task = new Task("Lav Java-opgaver", TaskStatus.TODO);
```

Lav getters til begge attributter.

Udskriv derefter taskens beskrivelse og status i `main`.

---

# Opgave 10 – Task og switch

Byg videre på `Task` fra den forrige opgave.

Lav en metode:

```java
public void printStatus()
```

Metoden skal bruge `switch` på taskens `status`.

Den skal fx skrive:

| Status        | Udskrift                  |
| ------------- | ------------------------- |
| `TODO`        | `Opgaven er ikke startet` |
| `IN_PROGRESS` | `Opgaven er i gang`       |
| `DONE`        | `Opgaven er færdig`       |

Afprøv metoden med mindst tre forskellige `Task`-objekter.

---

# Opgave 11 – Skift status

Byg videre på `Task`.

Lav en metode:

```java
public void setStatus(TaskStatus status)
```

Opret derefter en task:

```java
Task task = new Task("Læs om enum", TaskStatus.TODO);
```

Udskriv status.

Skift derefter status til:

```java
TaskStatus.IN_PROGRESS
```

og udskriv igen.

Skift til sidst status til:

```java
TaskStatus.DONE
```

og udskriv igen.

Overvej:

* Hvorfor er `TaskStatus` bedre end en `String`?
* Hvad ville der kunne gå galt, hvis status i stedet var en tekst?

---

# Opgave 12 – Lidt sværere: Leveringspris

Opret en enum:

```java
DeliveryType
```

med:

```text
PICKUP
STANDARD
EXPRESS
```

Lav derefter en `switch` expression, som returnerer leveringsprisen:

| Levering   | Pris |
| ---------- | ---: |
| `PICKUP`   |    0 |
| `STANDARD` |   49 |
| `EXPRESS`  |   99 |

Eksempel:

```java
DeliveryType delivery = DeliveryType.EXPRESS;

int deliveryPrice = switch (delivery) {
    // Din kode
};
```

Udskriv:

```text
Leveringspris: 99 kr.
```

---

# Opgave 13 – Udfordring

Lav et lille program til en café.

Opret en enum:

```java
CoffeeSize
```

med:

```text
SMALL
MEDIUM
LARGE
```

En kaffe koster:

```text
SMALL  = 25 kr.
MEDIUM = 35 kr.
LARGE  = 45 kr.
```

Opret eksempelvis:

```java
CoffeeSize size = CoffeeSize.LARGE;
```

Brug en `switch` expression til at finde prisen.

Programmet skal derefter skrive:

```text
Du har valgt LARGE
Pris: 45 kr.
```

### Ekstra udfordring

Opret en klasse `Coffee` med attributterne:

```java
private String name;
private CoffeeSize size;
```

Opret eksempelvis:

```java
Coffee coffee = new Coffee("Caffe Latte", CoffeeSize.LARGE);
```

Lav en metode:

```java
public int getPrice()
```

som bruger en `switch` expression til at returnere prisen ud fra størrelsen.

---
# Enum med konstruktør og værdier

Indtil nu har vores enums kun bestået af navngivne værdier:

```java
public enum CoffeeSize {
    SMALL,
    MEDIUM,
    LARGE
}
```

Men en enum kan også have **attributter, en konstruktør og metoder**.

Det betyder, at vi eksempelvis kan lade hver kaffestørrelse kende sin egen pris.

## Eksempel

Vi ønsker følgende sammenhæng:

| Størrelse |   Pris |
| --------- | -----: |
| `SMALL`   | 25 kr. |
| `MEDIUM`  | 35 kr. |
| `LARGE`   | 45 kr. |

En enum kan skrives sådan:

```java
public enum CoffeeSize {
    SMALL(25),
    MEDIUM(35),
    LARGE(45);

    private int price;

    CoffeeSize(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
```

Læg mærke til værdierne:

```java
SMALL(25),
MEDIUM(35),
LARGE(45);
```

Når enum-værdierne oprettes, kaldes konstruktøren:

```java
CoffeeSize(int price) {
    this.price = price;
}
```

Det betyder eksempelvis, at `LARGE` får værdien `45` gemt i attributten `price`.

Vi kan derefter skrive:

```java
CoffeeSize size = CoffeeSize.LARGE;

System.out.println(size);
System.out.println(size.getPrice());
```

Resultatet bliver:

```text
LARGE
45
```

---

## Din opgave

Opret en enum med navnet:

```java
DeliveryType
```

Den skal indeholde tre typer levering og deres priser:

| Leveringstype |   Pris |
| ------------- | -----: |
| `PICKUP`      |  0 kr. |
| `STANDARD`    | 49 kr. |
| `EXPRESS`     | 99 kr. |

Start med:

```java
public enum DeliveryType {
    PICKUP(0),
    STANDARD(49),
    EXPRESS(99);

    // resten af koden
}
```

Tilføj:

1. en attribut `price`
2. en konstruktør, der modtager prisen
3. en metode `getPrice()`

Opret derefter i `main`:

```java
DeliveryType delivery = DeliveryType.EXPRESS;
```

Udskriv leveringstypen og prisen.

Programmet skal fx skrive:

```text
Leveringstype: EXPRESS
Pris: 99 kr.
```

### Prøv selv

Skift:

```java
DeliveryType.EXPRESS
```

til:

```java
DeliveryType.STANDARD
```

og derefter:

```java
DeliveryType.PICKUP
```

Kontrollér, at den rigtige pris bliver vist.

---

## Enum eller almindelig klasse?

En `enum` og en almindelig klasse kan ligne hinanden.

Begge kan have:

* attributter
* konstruktører
* metoder

Men der er en vigtig forskel.

Med en almindelig klasse bestemmer vi selv, hvor mange objekter der skal oprettes.

Hvis vi har:

```java
public class Coffee {
    private String name;

    public Coffee(String name) {
        this.name = name;
    }
}
```

kan vi oprette lige så mange objekter, vi ønsker:

```java
Coffee coffee1 = new Coffee("Latte");
Coffee coffee2 = new Coffee("Espresso");
Coffee coffee3 = new Coffee("Cappuccino");
```

Med en enum er objekterne derimod **bestemt på forhånd**.

Hvis vi har:

```java
public enum CoffeeSize {
    SMALL(25),
    MEDIUM(35),
    LARGE(45);

    // ...
}
```

findes der kun de tre værdier:

```text
SMALL
MEDIUM
LARGE
```

Vi kan fx bruge:

```java
CoffeeSize size = CoffeeSize.SMALL;
```

men vi kan ikke skrive:

```java
CoffeeSize size = new CoffeeSize(30);
```

Vi kan altså ikke selv oprette nye enum-værdier.

### Kort sagt

| Almindelig klasse                        | Enum                                        |
| ---------------------------------------- | ------------------------------------------- |
| Kan bruges til at oprette mange objekter | Har et fast antal værdier                   |
| Objekter oprettes normalt med `new`      | Enum-værdier er defineret på forhånd        |
| Kan have attributter                     | Kan have attributter                        |
| Kan have konstruktører                   | Kan have konstruktører                      |
| Kan have metoder                         | Kan have metoder                            |
| Bruges til generelle objekter            | Bruges når mulighederne er kendt på forhånd |

En god tommelfingerregel er:

> Brug en `enum`, når du på forhånd kender det begrænsede antal værdier, der skal være mulige.

---

## Ekstra opgave

Udvid `DeliveryType`, så den også indeholder et forventet antal leveringsdage:

| Leveringstype |   Pris | Leveringstid |
| ------------- | -----: | -----------: |
| `PICKUP`      |  0 kr. |       0 dage |
| `STANDARD`    | 49 kr. |       3 dage |
| `EXPRESS`     | 99 kr. |        1 dag |

Enum-værdierne kan derfor starte sådan:

```java
PICKUP(0, 0),
STANDARD(49, 3),
EXPRESS(99, 1);
```

Tilføj:

```java
private int price;
private int deliveryDays;
```

Tilpas konstruktøren, og lav getters til begge attributter.

Programmet skal eksempelvis kunne skrive:

```text
Leveringstype: EXPRESS
Pris: 99 kr.
Leveringstid: 1 dag
```

### Overvej

* Hvorfor passer `DeliveryType` godt som en enum?
* Hvorfor ville en almindelig klasse være mere passende, hvis brugeren selv skulle kunne oprette nye leveringstyper?
* Hvor bliver værdien `99` gemt, når `EXPRESS(99)` oprettes?

# Opsamling

Når du har løst opgaverne, bør du kunne forklare:

1. Hvad er en `enum`?
2. Hvornår giver det mening at bruge en `enum`?
3. Hvordan opretter man en variabel med en enum-type?
4. Hvordan sammenligner man enum-værdier?
5. Hvordan bruges `switch` sammen med en enum?
6. Hvad er forskellen på den klassiske `switch` og den nye `->`-syntaks?
7. Hvorfor behøver den nye syntaks ikke `break`?
8. Hvordan kan en `switch` returnere en værdi?
9. Hvordan udvider vi Enum med konstruktør og værdier?

