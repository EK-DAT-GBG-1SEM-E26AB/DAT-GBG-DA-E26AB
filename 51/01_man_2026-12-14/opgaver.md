# Eksamensspørgsmål pr. emne

Spørgsmål af den slags, du kan få til en mundtlig eksamen i programmering – ordnet efter
semestrets emner. De er til at øve med, ikke en liste over de rigtige eksamensspørgsmål.

Sådan bruger du dem:

* **Svar højt.** Arbejd to og to: den ene læser spørgsmålet op, den anden svarer – uden at kigge i
  noterne. Byt efter hvert emne.
* **"Hvad udskriver ...?"** – svar **først**, kør koden bagefter. Forklar forskellen, hvis du tog fejl.
* **"Skriv ..."** – skriv koden i IntelliJ, og vis, at den virker, med en `main`.
* **"Forklar ..."** – brug et eksempel. Et svar med et stykke kode er næsten altid bedre end et
  svar uden.

Der er [svar og hints](loesninger.md) – men svar selv først. Sæt et kryds ved de spørgsmål, du ikke
kunne svare på. Det er dem, der skal på din repetitionsliste.

---

## 1. Variabler, datatyper og operatorer

**1.1** Hvad udskriver hver linje?

```java
System.out.println(7 / 2);
System.out.println(7 / 2.0);
System.out.println((int) 7.9);
System.out.println(7 % 3);
System.out.println(Integer.parseInt("34") + 1);
System.out.println("34" + 1);
System.out.println(1 + 2 + "3" + 4 + 5);
```

**1.2** Forklar forskellen på `int` og `double`. Hvornår vil du bruge hver af dem til et beløb, en
alder og en temperatur?

**1.3** Forklar forskellen på **casting** og **parsing**. Giv et eksempel på hver.

**1.4** Hvordan tjekker du med `%`, om et tal er lige? Om det går op i 7?

---

## 2. Strings

**2.1** Hvad giver hvert udtryk?

```java
String s = "Svømmeklubben Delfinen";

s.length()
s.charAt(0)
s.indexOf("Delfinen")
s.split(" ")[1]
s.toUpperCase().contains("DELFIN")
s.substring(0, 5)
```

**2.2** Hvad udskriver koden – og hvorfor er svarene forskellige?

```java
String a = "hej";
String b = new String("hej");
System.out.println(a == b);
System.out.println(a.equals(b));
```

**2.3** Skriv en metode `countVowels(String text)`, der returnerer antallet af vokaler (a, e, i, o,
u, y, æ, ø, å) – både store og små.

---

## 3. Betingelser og løkker

**3.1** Hvad udskriver koden?

```java
for (int i = 10; i > 0; i -= 3) {
    System.out.print(i + " ");
}
```

**3.2** Hvornår bruger du en `while`-løkke, en `for`-løkke og en for-each-løkke? Giv et eksempel på
hver. Hvad er særligt ved `do-while`?

**3.3** Hvad er forskellen på en række `if – else if` og en `switch`? Hvornår er `switch` det
bedste valg?

---

## 4. Arrays og ArrayList

**4.1** Hvad giver det?

```java
int[] numbers = {4, 8, 15, 16, 23, 42};

numbers[numbers.length - 1]      // ?
numbers[6]                       // ?
```

Skriv en løkke, der lægger de **lige** tal i `numbers` sammen. Hvad bliver summen?

**4.2** Forklar forskellen på et array og en `ArrayList`. Hvornår vil du vælge hvad?

**4.3** Hvad udskriver koden? Forklar de to kald af `remove`.

```java
ArrayList<Integer> list = new ArrayList<>();
list.add(10);
list.add(20);
list.add(30);
list.remove(1);
System.out.println(list);
list.remove(Integer.valueOf(10));
System.out.println(list);
```

**4.4** Skriv to metoder, `findMax(ArrayList<Integer> numbers)` og
`calculateAverage(ArrayList<Integer> numbers)`. Hvad skal der ske, hvis listen er tom?

---

## 5. Klasser og objekter

**5.1** `Member` har en `private String name` med getter og setter. Hvad udskriver koden – og
hvorfor?

```java
Member m1 = new Member("Ida");
Member m2 = m1;
m2.setName("Bo");
System.out.println(m1.getName());
```

**5.2** Hvorfor gør man attributter `private`? Hvad betyder *indkapsling*?

**5.3** Hvad betyder `static`? Giv et eksempel, hvor en `static` attribut eller metode giver mening
– og et, hvor den ikke gør.

**5.4** Hvad er `this`, og hvornår er det nødvendigt at skrive det?

**5.5** Hvad er en konstruktør? Hvad vil det sige, at en klasse har to konstruktører (overloading)?

---

## 6. Arv, abstrakte klasser og polymorfi

**6.1** Hvad udskriver koden?

```java
public class Animal {
    public String makeSound() { return "..."; }
    public String describe()  { return "Jeg siger " + makeSound(); }
}

public class Dog extends Animal {
    @Override
    public String makeSound() { return "Vuf"; }
}

Animal animal = new Dog();
System.out.println(animal.describe());
```

Forklar, hvorfor det ikke bliver `"Jeg siger ..."`.

**6.2** Hvad gør `super(...)` i en konstruktør? Hvorfor skal det stå som det første?

**6.3** Hvad er en abstrakt klasse? Hvad er en abstrakt metode? Hvad sker der, hvis en subklasse
ikke implementerer den?

**6.4** Hvorfor er `instanceof` spredt ud over et program et tegn på dårligt design? Hvad gør man i
stedet?

**6.5** Hvad betyder `protected`? Hvordan er det forskelligt fra `private` og `public`?

---

## 7. Interfaces og sortering

**7.1** Hvad er et interface? Hvad er forskellen på et interface og en abstrakt klasse?

**7.2** Hvad er forskellen på `Comparable` og `Comparator`? Hvornår bruger du hvad?

**7.3** Hvad returnerer `compare`, og hvad betyder tallet? I hvilken rækkefølge sorterer denne
comparator – og hvordan vender man den om?

```java
public int compare(Person person1, Person person2) {
    return Integer.compare(person1.getAge(), person2.getAge());
}
```

**7.4** Skriv en `NameComparator`, der sorterer personer efter navn uden at skelne mellem store og
små bogstaver.

---

## 8. Enum

**8.1** Hvad er en `enum`, og hvorfor er den bedre end en `String` til fx svømmediscipliner?

**8.2** Hvordan løber man alle værdierne i en `enum` igennem? Hvordan bruger man en `enum` i en
`switch`?

---

## 9. Exceptions

**9.1** Hvad udskriver `parse("12")` – og `parse("tolv")`?

```java
static int parse(String text) {
    try {
        System.out.println("A");
        int n = Integer.parseInt(text);
        System.out.println("B");
        return n;
    }
    catch (NumberFormatException e) {
        System.out.println("C");
        return -1;
    }
    finally {
        System.out.println("D");
    }
}
```

**9.2** Hvad er forskellen på en *checked* og en *unchecked* exception? Hvorfor kan denne metode
ikke kompilere – og hvad er de to måder at rette den på?

```java
public void save(String text) {
    PrintStream output = new PrintStream("data.txt");
    output.println(text);
    output.close();
}
```

**9.3** Hvad er forskellen på `throw` og `throws`?

**9.4** Hvor i et program skal en exception fanges? Hvorfor er en tom `catch`-blok en dårlig idé?

---

## 10. Filer

**10.1** Skriv en metode, der gemmer en liste af navne i en tekstfil, ét navn pr. linje – og en
metode, der læser dem ind igen.

**10.2** En linje i en CSV-fil ser sådan ud: `17;Ida Hansen;2010-05-03;true`. Hvordan laver du den
om til et objekt? Hvad kan gå galt?

**10.3** Hvorfor skal man lukke en fil efter brug?

---

## 11. Datoer

**11.1** Hvad udskriver koden?

```java
LocalDate date = LocalDate.of(2026, 12, 14);
date.plusDays(1);
System.out.println(date);
```

**11.2** Hvad giver hvert udtryk – og hvorfor er de forskellige?

```java
Period.between(LocalDate.of(2008, 12, 15), LocalDate.of(2026, 12, 14)).getYears()
Period.between(LocalDate.of(2008, 12, 14), LocalDate.of(2026, 12, 14)).getYears()
```

**11.3** Hvorfor gemmer man en fødselsdato og ikke en alder?

---

## 12. Dit eget projekt

Svar med Delfinen-koden åben – og vis i koden, mens du svarer.

**12.1** Vis, hvor kontingentet bliver beregnet. Hvorfor ligger det i den klasse?

**12.2** Hvordan finder programmet de fem hurtigste i en disciplin? Hvilken klasse har ansvaret?

**12.3** Hvordan bliver et medlem gemt i filen – og læst ind igen? Hvad sker der, hvis filen mangler?

**12.4** Hvor har I brugt `enum`, et interface og exceptions – og arv, hvis I har brugt det? Hvorfor lige der?

**12.5** Hvad sker der, hvis brugeren skriver bogstaver, hvor der skal stå et tal? Vis, hvor det
bliver håndteret.

**12.6** Hvilken del af koden er du mest tilfreds med? Hvilken ville du skrive om – og hvordan?
