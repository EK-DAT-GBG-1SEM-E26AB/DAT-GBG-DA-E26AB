# Enum og switch

## Beskrivelse

I denne lektion arbejder vi med `enum` og `switch`.

En `enum` bruges, når en variabel kun må have én værdi fra et på forhånd kendt sæt af muligheder.
En `switch` kan bruges til at vælge, hvilken kode der skal udføres, afhængigt af værdien af en variabel.

Vi skal blandt andet se på, hvordan `enum` og `switch` kan bruges sammen til at gøre programmer mere overskuelige og mindre fejlbehæftede.

---

## Læringsmål

Efter lektionen skal du kunne:

* forklare hvad en `enum` er
* oprette en simpel `enum`
* oprette variable med en enum-type
* bruge enum-værdier i `if`- og `switch`-udtryk
* skrive en simpel `switch`
* forklare hvornår en `enum` kan være bedre end eksempelvis en `String`

---

## Se disse videoer før undervisningen:

[enhanced switches](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=2h49m05s) (til: 02:57:42)  
[enums](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=11h02m38s) (til: 11:12:45)  

---

## Læs nedenstående før undervisningen

### Hvad er en enum?

Forestil dig, at vi skal gemme en ugedag.

Vi kunne bruge en `String`:

```java
String day = "MONDAY";
```

Men hvad forhindrer os i at skrive:

```java
String day = "MONDYA";
```

eller:

```java
String day = "pizza";
```

Java accepterer begge dele, fordi de blot er tekster.

Hvis vi på forhånd ved, hvilke værdier der er lovlige, kan vi i stedet oprette en **enum**.

```java
public enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}
```

En `enum` beskriver altså et **fast sæt af mulige værdier**.

Vi kan nu skrive:

```java
Day day = Day.MONDAY;
```

Variablen `day` kan kun indeholde en af værdierne fra `Day`.

Det betyder blandt andet, at dette ikke er muligt:

```java
Day day = "MONDAY";
```

og heller ikke:

```java
Day day = Day.PIZZA;
```

Java hjælper os dermed med at sikre, at vi kun bruger gyldige værdier.

---

### Hvornår kan enum være nyttigt?

En `enum` er især nyttig, når der findes et begrænset antal muligheder.

Eksempler:

```text
Ugedage
Mandag, tirsdag, onsdag ...

Trafiklys
RED, YELLOW, GREEN

Ordrestatus
NEW, PAID, SHIPPED, DELIVERED

Spillekort
HEARTS, DIAMONDS, CLUBS, SPADES
```

Et andet eksempel kunne være en kunde med forskellige kundetyper:

```java
public enum CustomerType {
    REGULAR,
    PREMIUM,
    VIP
}
```

Herefter kan vi eksempelvis skrive:

```java
CustomerType type = CustomerType.VIP;
```

---

### Sammenligning af enum-værdier

Enum-værdier kan sammenlignes med `==`.

```java
Day day = Day.SATURDAY;

if (day == Day.SATURDAY) {
    System.out.println("Det er lørdag");
}
```

Her sammenligner vi ikke tekst. Vi sammenligner to værdier af typen `Day`.

---

### switch

Nogle gange skal programmet udføre forskellig kode afhængigt af værdien af en variabel.

Det kan eksempelvis gøres med flere `if`-sætninger.

```java
if (day == Day.MONDAY) {
    System.out.println("Mandag");
} else if (day == Day.TUESDAY) {
    System.out.println("Tirsdag");
} else if (day == Day.WEDNESDAY) {
    System.out.println("Onsdag");
}
```

Hvis der er mange muligheder, kan det hurtigt blive svært at læse.

Her kan `switch` være et alternativ.

---

### En simpel switch

```java
Day day = Day.MONDAY;

switch (day) {
    case MONDAY:
        System.out.println("Det er mandag");
        break;

    case TUESDAY:
        System.out.println("Det er tirsdag");
        break;

    case WEDNESDAY:
        System.out.println("Det er onsdag");
        break;

    default:
        System.out.println("Det er en anden dag");
}
```

`switch` undersøger værdien af `day`.

Hvis værdien eksempelvis er:

```java
Day.MONDAY
```

udføres koden under:

```java
case MONDAY:
```

---

### Hvorfor står der break?

I en klassisk `switch` bruges `break` til at afslutte den aktuelle `case`.

```java
case MONDAY:
    System.out.println("Mandag");
    break;
```

Uden `break` fortsætter Java videre til den næste `case`.

Eksempel:

```java
int number = 1;

switch (number) {
    case 1:
        System.out.println("Et");

    case 2:
        System.out.println("To");
}
```

Resultatet bliver:

```text
Et
To
```

Det kaldes **fall-through**.

Vi vil normalt undgå dette, og derfor bruger vi `break`.

---

### default

En `switch` kan have en `default`.

```java
switch (number) {
    case 1:
        System.out.println("Et");
        break;

    case 2:
        System.out.println("To");
        break;

    default:
        System.out.println("Et andet tal");
}
```

`default` udføres, hvis ingen af de andre `case` passer.

Det minder om den sidste `else` i en `if-else`-konstruktion.

---

### Hvilke datatyper kan bruges i en switch?

Selvom `enum` og `switch` er et perfekt par, kan en `switch` også bruges med flere andre almindelige datatyper i Java.

Du kan bruge en `switch` sammen med:
* **Primitive heltal:** `int`, `byte`, `short`, `char`
* **Wrapper-klasser:** `Integer`, `Byte`, `Short`, `Character`
* **Tekststrenge:** `String` (siden Java 7)

#### Eksempel med `int`:
```java
int month = 2;

switch (month) {
    case 1:
        System.out.println("Januar");
        break;
    case 2:
        System.out.println("Februar");
        break;
    default:
        System.out.println("En anden måned");
}
```

#### Eksempel med `String`:
Vær opmærksom på, at når du bruger en `String`, er Javas `switch` **case-sensitive** (der er forskel på store og små bogstaver).

```java
String role = "admin";

switch (role) {
    case "admin":
        System.out.println("Fuld adgang til systemet");
        break;
    case "user":
        System.out.println("Begrænset adgang");
        break;
    default:
        System.out.println("Ukendt rolle");
}
```

> ⚠️ **Pas på:** Hvis din `String`-variabel er `null` (altså ikke har nogen værdi), vil programmet kaste en `NullPointerException`. Sørg derfor altid for, at din `String` er valideret, før den rammer en `switch`.

---
### Ny switch-syntaks
I nyere java (java 12) er der lavet en mere kompakt syntaks for switch:
```java

TrafficLight light = TrafficLight.RED;

switch (light) {
    case RED -> System.out.println("Stop");
    case YELLOW -> System.out.println("Gør klar");
    case GREEN -> System.out.println("Kør");
}
```
Med den nye ->-syntaks er der ikke fall-through.  
### Enum og switch sammen

`enum` og `switch` passer godt sammen.

Eksempel:

```java
public enum TrafficLight {
    RED,
    YELLOW,
    GREEN
}
```

Vi kan nu skrive:

```java
TrafficLight light = TrafficLight.RED;

switch (light) {
    case RED:
        System.out.println("Stop");
        break;

    case YELLOW:
        System.out.println("Gør klar");
        break;

    case GREEN:
        System.out.println("Kør");
        break;
}
```

Her ved Java præcis, hvilke værdier `light` kan have.

---

### Et lidt større eksempel

Forestil dig, at vi har forskellige typer medlemskab:

```java
public enum Membership {
    BASIC,
    PREMIUM,
    VIP
}
```

Vi kan derefter beregne en rabat:

```java
Membership membership = Membership.PREMIUM;

double discount = 0;

switch (membership) {
    case BASIC:
        discount = 0;
        break;

    case PREMIUM:
        discount = 0.10;
        break;

    case VIP:
        discount = 0.20;
        break;
}

System.out.println("Rabat: " + discount);
```

Resultatet bliver:

```text
Rabat: 0.1
```

I den nye switch syntaks kan man skrive det samme mere kompakt og benytte expression syntaks:
``` java
double discount = switch (membership) {
    case BASIC -> 0;
    case PREMIUM -> 0.10;
    case VIP -> 0.20;
};
```

---

### String eller enum?

Man kunne også have skrevet:

```java
String membership = "PREMIUM";
```

Men så er følgende også muligt:

```java
String membership = "PREMIUN";
```

Java opdager ikke stavefejlen.

Med en enum:

```java
Membership membership = Membership.PREMIUM;
```

kan Java kontrollere, at værdien faktisk eksisterer.

Derfor giver `enum` ofte:

* færre fejl
* tydeligere kode
* bedre hjælp fra IntelliJ
* et klart overblik over de mulige værdier

---

### Opsamling

En `enum` bruges til at beskrive et fast antal mulige værdier.

Eksempel:

```java
public enum Size {
    SMALL,
    MEDIUM,
    LARGE
}
```

En variabel kan derefter have typen:

```java
Size size = Size.MEDIUM;
```

En `switch` kan bruges til at vælge mellem forskellige handlinger:

```java
switch (size) {
    case SMALL:
        System.out.println("Lille");
        break;

    case MEDIUM:
        System.out.println("Mellem");
        break;

    case LARGE:
        System.out.println("Stor");
        break;
}
```  

`enum` og `switch` bruges derfor ofte sammen.  

Mere kompakt med den nye syntaks:
```java
switch (size) {
    case SMALL  -> System.out.println("Lille");
    case MEDIUM -> System.out.println("Mellem");
    case LARGE  -> System.out.println("Stor");
}
```
hvor vi undgår problemer med fall-through.  

## Aktiviteter i undervisningen
Arbejd med disse [opgaver](opgaver.md)  


