# Svar og hints – eksamensspørgsmål pr. emne

Korte svar og hints til [eksamensspørgsmålene](opgaver.md). Al kode er kørt med Java 21.

> Til en mundtlig eksamen er et **svar** ikke nok – du skal kunne **forklare** det. Brug svarene
> her til at tjekke dig selv, og øv så forklaringen højt med dine egne ord.

---

## 1. Variabler, datatyper og operatorer

**1.1**

```text
3        heltalsdivision: decimalerne smides væk
3.5      når ét af tallene er en double, bliver resultatet en double
7        casting til int skærer decimalerne af – den runder ikke
1        resten ved 7 / 3
35       "34" bliver parset til tallet 34
341      + med en String er sammensætning: "34" + "1"
3345     læses fra venstre: 1 + 2 = 3, så "3" + "3" = "33", så "334", så "3345"
```

**1.2** `int` er hele tal, `double` er kommatal. En alder er et `int`. En temperatur er en
`double`. Et beløb i kroner kan være et `int`, hvis der ikke er ører – ellers regner man ofte i
**øre** som `int`, fordi `double` kan give små afrundingsfejl (`0.1 + 0.2` er ikke præcis `0.3`).
Det er samme idé som at gemme en svømmetid i hundrededele.

**1.3** **Casting** laver én taltype om til en anden: `(int) 7.9` giver `7`. **Parsing** læser en
**tekst** og laver den om til et tal: `Integer.parseInt("34")` giver `34`. Parsing kan fejle –
`Integer.parseInt("tolv")` kaster en `NumberFormatException`.

**1.4** `n % 2 == 0` er sand, når `n` er lige. `n % 7 == 0` er sand, når 7 går op i `n`.

---

## 2. Strings

**2.1**

```text
s.length()                           22
s.charAt(0)                          'S'
s.indexOf("Delfinen")                14   (tæller fra 0)
s.split(" ")[1]                      "Delfinen"
s.toUpperCase().contains("DELFIN")   true
s.substring(0, 5)                    "Svømm"   (fra og med 0, til men ikke med 5)
```

**2.2** `false` og `true`. `==` sammenligner, om to variabler peger på **det samme objekt**.
`new String("hej")` laver et nyt objekt, så `a` og `b` er to forskellige objekter med samme
indhold. `equals` sammenligner **indholdet**. Sammenlign derfor altid tekst med `equals`.

**2.3**

```java
import java.util.ArrayList;

public class Tools {

    public static int countVowels(String text) {
        String vowels = "aeiouyæøå";
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));
            if (vowels.indexOf(c) >= 0) {
                count++;
            }
        }
        return count;
    }

    public static int findMax(ArrayList<Integer> numbers) {
        int max = numbers.get(0);
        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }
        return max;
    }

    public static double calculateAverage(ArrayList<Integer> numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return (double) sum / numbers.size();
    }
}
```

`countVowels("Svømmeklubben Delfinen")` giver `7`. `indexOf` returnerer `-1`, når tegnet ikke
findes. Man kan også skrive en `switch` eller en lang `if` – det vigtige er, at du kan forklare den,
du vælger. (Klassen indeholder også svarene på 4.4.)

---

## 3. Betingelser og løkker

**3.1** `10 7 4 1 ` – `i` starter på 10 og falder med 3, så længe den er over 0.

**3.2**

* `for`: når du ved, hvor mange gange – eller skal bruge indekset (`for (int i = 0; i < 4; i++)`).
* for-each: når du vil gennem **alle** elementer i en liste eller et array og ikke skal bruge
  indekset.
* `while`: når du ikke ved, hvor mange gange – fx *"spørg igen, indtil svaret er gyldigt"*.
* `do-while` kører kroppen **mindst én gang**, før betingelsen tjekkes – fx en menu, der skal vises
  mindst én gang.

**3.3** En `switch` sammenligner **én** værdi med en række faste muligheder – fx et menuvalg eller
en `enum`. `if – else if` kan teste hvad som helst, fx intervaller som `age < 18`. Med `switch` på en
`enum` fortæller compileren dig, hvis du har glemt en konstant.

---

## 4. Arrays og ArrayList

**4.1** `numbers[numbers.length - 1]` er `42` – det sidste element. `numbers[6]` kaster en
`ArrayIndexOutOfBoundsException`: arrayet har 6 elementer, med indeks 0–5. Summen af de lige tal
(4, 8, 16, 42) er `70`.

**4.2** Et array har en **fast** størrelse og kan indeholde primitive typer direkte (`int[]`). En
`ArrayList` **vokser** selv og har metoder som `add`, `remove`, `contains` og `size` – men kan kun
indeholde objekter, så tal bliver til `Integer`. Vælg et array, når størrelsen ligger fast (fire
porte på et bundkort, 12 måneder), og en `ArrayList`, når elementer kommer og går (medlemmer i en
klub).

**4.3**

```text
[10, 30]
[30]
```

`list.remove(1)` fjerner elementet på **indeks** 1, som er `20`. `list.remove(Integer.valueOf(10))`
fjerner **objektet** `10`. Med en `ArrayList<Integer>` er det en klassisk fælde: `remove(10)` ville
prøve at fjerne indeks 10.

**4.4** Se `findMax` og `calculateAverage` i `Tools` under 2.3. `findMax` kaster en exception, hvis
listen er tom (`numbers.get(0)`) – det er rimeligt, for en tom liste **har** ikke et største tal.
`calculateAverage` returnerer 0 for en tom liste for at undgå division med 0. Læg mærke til
`(double) sum / numbers.size()` – uden castingen ville det være heltalsdivision.

---

## 5. Klasser og objekter

**5.1** `Bo`. `Member m2 = m1;` kopierer **referencen**, ikke objektet. Der er kun ét
`Member`-objekt, og både `m1` og `m2` peger på det.

**5.2** `private` betyder, at kun klassen selv kan læse og ændre attributten. Andre klasser må gå
gennem metoder. Så kan klassen selv **sikre**, at data er gyldige (fx afvise en negativ vægt), og man
kan ændre, hvordan data gemmes indeni, uden at resten af programmet skal ændres. Det er
**indkapsling**.

**5.3** `static` betyder, at noget hører til **klassen** og ikke til det enkelte objekt. Eksempler:
en konstant som `private static final int MAX_TOTAL_WEIGHT = 3500;`, eller en hjælpemetode som
`TimeFormat.format(6532)`, der ikke bruger nogen attributter. Et medlems navn skal **ikke** være
`static` – så ville alle medlemmer have det samme navn.

**5.4** `this` er det objekt, metoden bliver kaldt på. Det er nødvendigt, når en parameter har samme
navn som en attribut: `this.name = name;`.

**5.5** En konstruktør kører, når objektet oprettes med `new`, og giver attributterne deres
startværdier. To konstruktører med forskellige parametre (overloading) giver to måder at oprette
objektet på – fx med og uden en bestemt værdi.

---

## 6. Arv, abstrakte klasser og polymorfi

**6.1** `Jeg siger Vuf`. `describe()` er arvet fra `Animal`, men når den kalder `makeSound()`, er
det objektets **faktiske** klasse, der afgør, hvilken udgave der kører – og objektet er en `Dog`.
Det hedder dynamic dispatch eller runtime polymorfi.

**6.2** `super(...)` kalder superklassens konstruktør, så de arvede attributter bliver sat.
Superklassens del af objektet skal være på plads, før subklassen bygger videre på den – derfor
skal kaldet stå først.

**6.3** En abstrakt klasse kan man ikke lave objekter af (`new Weapon()` giver
`Weapon is abstract; cannot be instantiated`). En abstrakt metode har ingen krop; subklasserne
**skal** implementere den – ellers kompilerer de ikke, medmindre de selv er abstrakte. Se
[05-10](../../41/01_man_2026-10-05/README.md).

**6.4** Hver gang der kommer en ny subklasse, skal man finde **alle** steder med `instanceof` og
tilføje en gren – glemmer man ét, opfører programmet sig forkert uden fejl. I stedet lægger man en
metode på superklassen og lader hver subklasse implementere den (polymorfi).

**6.5** `protected` betyder: synlig i klassen selv, i dens subklasser og i samme package. `private`
er kun klassen selv; `public` er alle.

---

## 7. Interfaces og sortering

**7.1** Et interface er en liste over metoder, en klasse **lover** at have (`implements`). En klasse
kan implementere mange interfaces, men kun arve fra én klasse. Et interface har ingen attributter.
Abstrakt klasse: *"er en slags"*. Interface: *"kan noget"*.

**7.2** `Comparable` implementeres af klassen **selv** (`compareTo`) og giver den **naturlige**
rækkefølge – der er kun én. `Comparator` er en **separat** klasse (`compare`) og giver en **anden**
rækkefølge – der kan være mange. Se [Filmsamling del 9](../../projekter/filmsamling/del-9-sortering.md#interfaces).

**7.3** Et negativt tal: `person1` skal stå først. 0: lige. Positivt: `person2` skal stå først.
Comparatoren sorterer **yngste først**. Byt om på argumenterne for at vende den:
`Integer.compare(person2.getAge(), person1.getAge())`.

**7.4**

```java
import java.util.Comparator;

public class NameComparator implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        return person1.getName().compareToIgnoreCase(person2.getName());
    }
}
```

Med `Person` og en `AgeComparator` som i 7.3 giver det:

```java
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println(Tools.countVowels("Svømmeklubben Delfinen"));   // 7

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(4);
        numbers.add(15);
        numbers.add(8);
        System.out.println(Tools.findMax(numbers));                        // 15
        System.out.println(Tools.calculateAverage(numbers));               // 9.0

        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Carla", 30));
        people.add(new Person("anna", 17));
        people.add(new Person("Bo", 61));

        people.sort(new AgeComparator());
        System.out.println(people);
        people.sort(new NameComparator());
        System.out.println(people);
    }
}
```

```text
7
15
9.0
[anna (17), Carla (30), Bo (61)]
[anna (17), Bo (61), Carla (30)]
```

Uden `IgnoreCase` ville `"anna"` komme **efter** `"Carla"`, fordi små bogstaver kommer efter store.

---

## 8. Enum

**8.1** En `enum` er en type med et fast sæt værdier, fx `BUTTERFLY`, `CRAWL`, `BACKSTROKE`,
`BREASTSTROKE`. Med en `String` kan man stave forkert (`"crawl"`, `"Crawl"`, `"krawl"`), og
compileren siger ingenting. Med en `enum` er det kun de fire, der findes.

**8.2** `for (Discipline discipline : Discipline.values()) { ... }`. I en `switch` skriver man
konstanterne uden klassenavn: `case CRAWL -> ...`.

---

## 9. Exceptions

**9.1**

```text
parse("12")      parse("tolv")
A                A
B                C
D                D
12               -1
```

`finally` kører **altid** – også når `try` eller `catch` rammer `return`. Ved `"tolv"` springer Java
direkte fra `parseInt` til `catch`, så `B` bliver aldrig udskrevet.

**9.2** En **checked** exception (fx `FileNotFoundException`) skal håndteres – compileren tvinger
dig. En **unchecked** (fx `NumberFormatException`, `NullPointerException`) behøver ikke.
Compileren siger:

```text
error: unreported exception FileNotFoundException; must be caught or declared to be thrown
```

Ret det med `try`/`catch` i metoden – eller med `throws FileNotFoundException` i metodens signatur,
så den, der kalder, skal håndtere den.

**9.3** `throw` **kaster** en exception: `throw new IllegalArgumentException("...")`. `throws` står i
metodens signatur og **fortæller**, at metoden kan kaste en exception.

**9.4** Dér, hvor man ved, hvad der skal ske – typisk i brugerfladen, hvor brugeren kan få en
besked og prøve igen. En tom `catch` gemmer fejlen: programmet fortsætter, som om alt gik godt, og
ingen ved, hvad der skete.

---

## 10. Filer

**10.1**

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class NameFile {

    public static void save(ArrayList<String> names, String fileName) throws FileNotFoundException {
        PrintStream output = new PrintStream(fileName);
        for (String name : names) {
            output.println(name);
        }
        output.close();
    }

    public static ArrayList<String> load(String fileName) throws FileNotFoundException {
        ArrayList<String> names = new ArrayList<>();
        Scanner input = new Scanner(new File(fileName));
        while (input.hasNextLine()) {
            names.add(input.nextLine());
        }
        input.close();
        return names;
    }
}
```

`PrintStream` skriver, `Scanner` med en `File` læser. Begge kan kaste `FileNotFoundException`, som
her sendes videre med `throws` til den, der kalder.

**10.2** Del linjen med `split(";")`, og lav hvert felt om til den rigtige type:

```java
import java.time.LocalDate;

public class CsvLine {

    public static void main(String[] args) {
        String line = "17;Ida Hansen;2010-05-03;true";

        String[] fields = line.split(";");
        int number = Integer.parseInt(fields[0]);
        String name = fields[1];
        LocalDate birthDate = LocalDate.parse(fields[2]);     // formatet åååå-mm-dd
        boolean hasPaid = Boolean.parseBoolean(fields[3]);

        System.out.println(number + " " + name + " " + birthDate + " " + hasPaid);
    }
}
```

Hvad kan gå galt: for få felter (`ArrayIndexOutOfBoundsException`), et felt, der ikke er et tal
(`NumberFormatException`), en ugyldig dato (`DateTimeParseException`) – eller et navn, der selv
indeholder `;`.

**10.3** Mange klasser, der skriver til filer – fx `PrintWriter` og `FileWriter` – samler data i
en buffer og skriver først det hele, når filen lukkes. Glemmer man `close()`, kan filen ende med at
være tom eller mangle det sidste. (`PrintStream` skriver med det samme, men det er en god vane altid
at lukke.) Og en åben fil optager en ressource i styresystemet – på Windows kan man fx ikke slette
en fil, som et program har åben.

---

## 11. Datoer

**11.1** `2026-12-14`. `LocalDate` er **immutable**: `plusDays` ændrer ikke datoen, men returnerer en
**ny**. Man skal gemme resultatet: `date = date.plusDays(1);`.

**11.2** `17` og `18`. Den første person fylder 18 den 15-12-2026 – dagen efter. Den anden fylder 18
netop den 14-12-2026. `Period.between(...).getYears()` tæller **hele** år.

**11.3** En alder passer kun indtil næste fødselsdag. En fødselsdato ændrer sig aldrig, og alderen
kan altid regnes ud fra den på en hvilken som helst dato.

---

## 12. Dit eget projekt

Her er der ikke ét svar – det er **jeres** kode. Men et godt svar har tre dele:

1. **Vis** stedet i koden.
2. **Forklar**, hvad koden gør.
3. **Begrund**, hvorfor I gjorde sådan – og nævn gerne et alternativ, I valgte fra.

Brug jeres selvrefleksion og noterne fra [peer review](../../projekter/delfinen/peer-review.md) –
spørgsmålene der ligner dem her.
