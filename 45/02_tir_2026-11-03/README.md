# Sortering (interfaces)

## Beskrivelse

"Vis alle film" viser filmene i den rækkefølge, de blev oprettet. Med tyve film er det rodet. Med
to hundrede kan man ikke bruge det. Brugeren vil have dem i alfabetisk orden – eller efter årstal,
eller efter instruktør.

Java kan sortere en liste med én linje. Men Java ved ikke, hvad det vil sige, at én **film** kommer
før en anden. Det skal I fortælle den, og det gør man med et **interface**.

I dag møder I interfaces for alvor. I kender idéen fra
[abstrakte klasser](../../41/01_man_2026-10-05/README.md): en type, der lover, at bestemte metoder
findes, uden at sige, hvordan de virker. Et interface er den samme idé, bare renere – og det er det,
Java selv bruger til at sortere.

Dagen slutter med [Filmsamling del 9 – Sortering](../../projekter/filmsamling/del-9-sortering.md),
den sidste del af projektet.

> **Deadline for Filmsamling er i morgen, onsdag 04-11, kl. 23:59.** Del 9 er den sidste del. Se
> [Aflevering](../../projekter/filmsamling/readme.md#aflevering).

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* forklare, hvad et **interface** er, og hvad `implements` betyder
* skrive dit eget interface og to klasser, der implementerer det
* forklare forskellen på et interface og en abstrakt klasse – og vælge mellem dem
* sortere en `ArrayList` af tal og tekst med `Collections.sort`
* give en klasse en **naturlig rækkefølge** med `Comparable` og `compareTo`
* skrive en `Comparator` til en anden rækkefølge og bruge den med `list.sort(...)`
* forklare, hvad det negative tal, 0 og det positive tal fra `compare` betyder
* sortere en **kopi**, så den oprindelige liste beholder sin rækkefølge
* *(ekstra)* sortere efter to ting med `thenComparing`

## Se disse videoer før undervisningen:

* [interfaces](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=8h1m30s) (til: 08:07:44)

Til genopfriskning – abstrakte klasser og polymorfi:

* [abstraction](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=7h51m58s) (til: 08:01:30)
* [polymorphism](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=8h7m44s) (til: 08:14:27)

Kursusrækken har ingen video om sortering. Læs i stedet – ud over teksten herunder –
[Filmsamling del 9](../../projekter/filmsamling/del-9-sortering.md). Vil du have det fra kilden, så
står det i Oracles [Object Ordering](https://docs.oracle.com/javase/tutorial/collections/interfaces/order.html).

## Læs nedenstående før undervisningen

---

### Hvad er et interface?

Et **interface** er en liste over metoder, som en klasse **lover** at have. Der er ingen
attributter og ingen kode i metoderne – kun overskrifterne:

```java
// Alt, der kan betales, kan svare på, hvor meget der skal betales
public interface Payable {

    int getAmountToPay();
}
```

En klasse **implementerer** interfacet med `implements`. Så **skal** den have metoden:

```java
public class Invoice implements Payable {

    private String description;
    private int quantity;
    private int pricePerItem;

    public Invoice(String description, int quantity, int pricePerItem) {
        this.description = description;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }

    @Override
    public int getAmountToPay() {
        return quantity * pricePerItem;
    }
}
```

```java
public class Employee implements Payable {

    private String name;
    private int monthlySalary;

    public Employee(String name, int monthlySalary) {
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    @Override
    public int getAmountToPay() {
        return monthlySalary;
    }
}
```

En faktura og en medarbejder har ikke noget med hinanden at gøre. De er ikke "en slags" det samme.
Men de **kan** begge noget fælles: de skal betales. Og så kan de ligge i den samme liste:

```java
ArrayList<Payable> bills = new ArrayList<>();
bills.add(new Invoice("Printerpapir", 10, 45));
bills.add(new Employee("Sofie", 42000));
bills.add(new Invoice("Kaffe", 3, 120));

int total = 0;
for (Payable bill : bills) {
    total += bill.getAmountToPay();
}
System.out.println("Der skal betales " + total + " kr.");
```

```text
Der skal betales 42810 kr.
```

Det er **polymorfi**, præcis som med `Weapon` i Adventure: loopet kender kun `Payable`, og hvert
objekt svarer på sin egen måde.

Compileren holder øje med løftet. Glemmer en klasse metoden:

```text
error: Broken is not abstract and does not override abstract method getAmountToPay() in Payable
```

Og man kan ikke lave et objekt af et interface – der er jo ingen kode i det:

```text
error: Payable is abstract; cannot be instantiated
```

Begge fejl kender I fra abstrakte klasser. Det er ikke tilfældigt: metoderne i et interface er
automatisk `public` og `abstract`, så I skal ikke skrive det.

#### Interface eller abstrakt klasse?

Tabellen fra [05-10](../../41/01_man_2026-10-05/README.md#abstrakt-klasse-eller-interface) – nu med
et eksempel til hver række:

| | Abstrakt klasse | Interface |
| --- | --- | --- |
| Kan have attributter | ja – `Weapon` har `damage` | nej (kun konstanter) |
| Kan have færdig kode | ja – `getDamage()` | sjældent |
| Hvor mange kan en klasse have | **én** (`extends`) | **mange** (`implements A, B`) |
| Udtrykker | "er en slags" – et sværd **er et** våben | "kan noget" – en faktura **kan** betales |

En klasse kan både arve og implementere på én gang:
`public class Invoice extends Document implements Payable, Comparable<Invoice>` – én superklasse,
to interfaces (`Comparable` kommer lige om lidt). Det er derfor,
interfaces er så nyttige: de kan sættes på **alle** klasser, uanset hvilken familie de hører til.

> I har måske allerede set `implements` i den frivillige vejledning
> [Lav dit eget tekstspil](../../00_vejledninger/tekstspil/README.md). Nu ved I, hvad det betyder.

---

### Java kan sortere tal og tekst

`Collections` (fra `java.util`) har en metode, der sorterer en `ArrayList`:

```java
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(42);
numbers.add(7);
numbers.add(19);
numbers.add(-3);

Collections.sort(numbers);
System.out.println(numbers);
```

```text
[-3, 7, 19, 42]
```

Det virker også med tekst:

```java
ArrayList<String> names = new ArrayList<>();
names.add("mads");
names.add("Anna");
names.add("Zenia");
names.add("bo");

Collections.sort(names);
System.out.println(names);
```

```text
[Anna, Zenia, bo, mads]
```

Bemærk: `Zenia` kommer **før** `bo`. Tekst sorteres efter tegnenes nummer, og alle store bogstaver
har lavere numre end de små. Det vender vi tilbage til.

---

### Men ikke egne objekter

Lad os sortere en spilleliste:

```java
public class Song {

    private String title;
    private String artist;
    private int lengthInSeconds;

    public Song(String title, String artist, int lengthInSeconds) {
        this.title = title;
        this.artist = artist;
        this.lengthInSeconds = lengthInSeconds;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getLengthInSeconds() {
        return lengthInSeconds;
    }

    @Override
    public String toString() {
        return title + " – " + artist + " (" + lengthInSeconds + " sek.)";
    }
}
```

```java
Collections.sort(playlist);    // playlist er en ArrayList<Song>
```

Compileren svarer med en lang fejl, der starter sådan:

```text
error: no suitable method found for sort(ArrayList<Song>)
```

og længere nede står nøgleordet: `Comparable`. Java ved ikke, om en sang skal sorteres efter
titel, kunstner eller længde. Det kan Java ikke gætte – det skal vi fortælle den.

`Integer` og `String` kunne sorteres, fordi de **implementerer interfacet `Comparable`**. Det skal
`Song` også.

---

### Comparable – den naturlige rækkefølge

`Comparable` ligger i `java.lang` (så den skal ikke importeres) og har én metode:

```java
int compareTo(T other)
```

`T` er den type, der sammenlignes med – her `Song`. Derfor skriver man `Comparable<Song>`, ligesom
`ArrayList<Song>`.

```java
public class Song implements Comparable<Song> {

    // ... attributter, constructor, getters og toString som før

    // Sangens naturlige rækkefølge: alfabetisk efter titel, uanset store og små bogstaver
    @Override
    public int compareTo(Song other) {
        return title.compareToIgnoreCase(other.title);
    }
}
```

Metoden sammenligner **dette** objekt (`this`) med et **andet** og svarer med et tal:

| Svar | Betyder |
| --- | --- |
| **negativt** | `this` skal stå **før** `other` |
| **0** | de er lige – rækkefølgen er ligegyldig |
| **positivt** | `this` skal stå **efter** `other` |

Det er kun **fortegnet**, der tæller. `"Under Pressure".compareToIgnoreCase("africa")` giver `20`,
men det kunne lige så godt have været `1` – det betyder bare "efter".

`String` har `compareTo` og `compareToIgnoreCase` i forvejen, så vi sender bare spørgsmålet videre
til titlerne. Nu virker det:

```java
ArrayList<Song> songs = new ArrayList<>();
songs.add(new Song("Take On Me", "a-ha", 225));
songs.add(new Song("Bohemian Rhapsody", "Queen", 354));
songs.add(new Song("Hotel California", "Eagles", 391));
songs.add(new Song("africa", "Toto", 295));
songs.add(new Song("Under Pressure", "Queen", 248));

Collections.sort(songs);
for (Song song : songs) {
    System.out.println(song);
}
```

```text
africa – Toto (295 sek.)
Bohemian Rhapsody – Queen (354 sek.)
Hotel California – Eagles (391 sek.)
Take On Me – a-ha (225 sek.)
Under Pressure – Queen (248 sek.)
```

Med `compareTo` i stedet for `compareToIgnoreCase` ville `africa` være havnet **sidst** –
`"Under Pressure".compareTo("africa")` giver `-12`, fordi `U` har et lavere nummer end `a`.

> **Hvem kalder `compareTo`?** Det gør `sort` – mange gange, med forskellige par af sange – og
> bruger svarene til at bygge den sorterede liste. I skriver kun, hvordan **to** sange
> sammenlignes. Selve sorteringen har Java skrevet for jer.

> `other.title` virker, selv om `title` er `private`: `private` betyder "kun inde i klassen `Song`" –
> ikke "kun inde i dette objekt".

---

### Comparator – alle de andre rækkefølger

En klasse har kun **én** `compareTo` – én naturlig rækkefølge. Men brugeren vil også sortere efter
kunstner eller længde. Til det laver man en **separat klasse**, der implementerer `Comparator`
(fra `java.util`):

```java
import java.util.Comparator;

public class LengthComparator implements Comparator<Song> {

    @Override
    public int compare(Song song1, Song song2) {
        return Integer.compare(song1.getLengthInSeconds(), song2.getLengthInSeconds());
    }
}
```

```java
import java.util.Comparator;

public class ArtistComparator implements Comparator<Song> {

    @Override
    public int compare(Song song1, Song song2) {
        return song1.getArtist().compareToIgnoreCase(song2.getArtist());
    }
}
```

`compare` får **to** sange – den står jo ikke inde i `Song` – og svarer med det samme slags tal som
`compareTo`. `Integer.compare(a, b)` giver negativt, 0 eller positivt for tal.

> Man ser tit `return a - b;` i stedet for `Integer.compare(a, b)`. Det virker for små tal, men
> kan give forkerte svar for meget store tal. `Integer.compare` virker altid.

Comparatoren gives til `sort` som et **objekt**:

```java
songs.sort(new LengthComparator());
```

```text
Take On Me – a-ha (225 sek.)
Under Pressure – Queen (248 sek.)
africa – Toto (295 sek.)
Bohemian Rhapsody – Queen (354 sek.)
Hotel California – Eagles (391 sek.)
```

`Collections.sort(songs, new LengthComparator());` gør det samme – vælg den, I synes er nemmest at
læse.

Man kan også kalde `compare` selv. Det er sådan, man tester en comparator:

```java
Song pressure = new Song("Under Pressure", "Queen", 248);
Song africa = new Song("africa", "Toto", 295);
LengthComparator byLength = new LengthComparator();

System.out.println(byLength.compare(pressure, africa));    // -1: pressure er kortest, står før
System.out.println(byLength.compare(africa, pressure));    // 1: africa står efter
System.out.println(byLength.compare(pressure, pressure));  // 0: lige lange
```

#### Comparable eller Comparator?

| | `Comparable<Song>` | `Comparator<Song>` |
| --- | --- | --- |
| Ligger i | `java.lang` (ingen import) | `java.util` |
| Implementeres af | **`Song` selv** | en **separat** klasse, fx `LengthComparator` |
| Metode | `int compareTo(Song other)` | `int compare(Song song1, Song song2)` |
| Hvor mange | én – den **naturlige** rækkefølge | lige så mange, I vil |
| Bruges med | `Collections.sort(list)` | `list.sort(comparator)` |

Tommelfingerregel: **Comparable** til den rækkefølge, man næsten altid vil have (film efter titel,
løbere efter tid). **Comparator** til alt det andet.

`Comparator` er et fint eksempel på, hvorfor interfaces er nyttige: `sort` er skrevet af Javas
udviklere for mange år siden. Den kender ikke `LengthComparator` – men den ved, at alt, der
implementerer `Comparator`, har en `compare`-metode. Det er nok.

---

### Sortér en kopi

`sort` ændrer rækkefølgen **i selve listen**. Tit vil man ikke det – i Filmsamlingen er samlingens
rækkefølge den, der gemmes i filen, og den, testene regner med. Så sortér en kopi:

```java
ArrayList<Song> sorted = new ArrayList<>(songs);
Collections.sort(sorted);

System.out.println(songs.get(0).getTitle());     // Take On Me – uændret
System.out.println(sorted.get(0).getTitle());    // africa
```

`new ArrayList<>(songs)` laver en **ny liste** med de **samme** sang-objekter. Sangene kopieres
ikke – kun listen.

---

### Når to er lige

Sorterer vi efter kunstner, er der to sange af Queen. Tag listen, som den var fra starten – i den
rækkefølge, sangene blev tilføjet i (listen fra før har jo lige fået ændret sin rækkefølge af
`sort`):

```java
songs.sort(new ArtistComparator());
```

```text
Take On Me – a-ha (225 sek.)
Hotel California – Eagles (391 sek.)
Bohemian Rhapsody – Queen (354 sek.)
Under Pressure – Queen (248 sek.)
africa – Toto (295 sek.)
```

`ArtistComparator` svarer `0` for de to Queen-sange. Så beholder `sort` dem i den rækkefølge, de
havde i forvejen – her den rækkefølge, de blev tilføjet i.

**Ekstra:** vil man bestemme rækkefølgen blandt dem, der er lige, har alle `Comparator`-objekter en
metode `thenComparing`. Den laver en **ny** comparator, der først spørger den første – og kun hvis
den svarer 0, spørger den anden:

```java
Comparator<Song> artistThenLength = new ArtistComparator().thenComparing(new LengthComparator());
songs.sort(artistThenLength);
```

```text
Take On Me – a-ha (225 sek.)
Hotel California – Eagles (391 sek.)
Under Pressure – Queen (248 sek.)
Bohemian Rhapsody – Queen (354 sek.)
africa – Toto (295 sek.)
```

Nu står Queens korteste sang først. Tilsvarende giver `reversed()` en comparator med den omvendte
rækkefølge: `new LengthComparator().reversed()` sætter den længste sang først.

> **Du vil møde en kortere skrivemåde på nettet:** `Comparator.comparing(Song::getArtist)`. Den gør
> det samme som `ArtistComparator`, men bruger en *method reference* (`::`), som vi ikke har haft.
> I Filmsamlingen skriver I comparatorerne som klasser – så kan I se præcis, hvad der sker, og I
> kan forklare hver linje.

---

### Sådan hænger det sammen med Filmsamlingen

| Det du lærte | Sådan bruges det i [del 9](../../projekter/filmsamling/del-9-sortering.md) |
| --- | --- |
| `Comparable` og `compareTo` | `Movie implements Comparable<Movie>` – efter titel (US16) |
| `Comparator` i en separat klasse | `TitleComparator`, `YearComparator` og tre til (US17) |
| Sortér en kopi | `getMoviesSortedByTitle()` og `getMoviesSorted(comparator)` i `MovieCollection` |
| Variablens type er interfacet | `comparatorFor(SortField)` returnerer `Comparator<Movie>` |
| `thenComparing` | ekstraopgaven US18 – sortér efter to ting |
| Test med fortegnet | `assertTrue(comparator.compare(casablanca, psycho) < 0)` |

---

## Det vigtigste at tage med

* et **interface** er et løfte om metoder; en klasse holder løftet med `implements`
* abstrakt klasse = "er en slags" (én); interface = "kan noget" (mange)
* `Collections.sort(list)` virker, når elementerne implementerer `Comparable`
* `compareTo` og `compare` svarer **negativt** (før), **0** (lige) eller **positivt** (efter) – kun
  fortegnet tæller
* brug `compareToIgnoreCase` til tekst og `Integer.compare` til tal
* **Comparable** = den naturlige rækkefølge i klassen selv; **Comparator** = alle andre, i hver sin
  klasse
* `sort` ændrer listen – sortér en **kopi**, når den oprindelige rækkefølge skal bevares
* *(ekstra)* `thenComparing` sorterer efter en ting mere, når to er lige

## Aktiviteter i undervisningen

### 1. Opgaver (formiddag)

Arbejd med [opgaver.md](opgaver.md):

* **del A** – dit første interface
* **del B** – sortér tal og tekst
* **del C** – løbere: `Comparable`, `Comparator` og en sorteret kopi
* **del D** – find fejlen i tre comparatorer
* **udfordringer** – til dem, der vil videre

Der er [vejledende løsninger](loesninger.md), men prøv selv først.

> Bliv ikke hængende i udfordringerne. Når del C og D virker, kan du det, der skal til i del 9.

### 2. Filmsamling del 9 (resten af dagen)

Arbejd med [Filmsamling del 9 – Sortering](../../projekter/filmsamling/del-9-sortering.md), og følg
den [anbefalede procedure](../../projekter/filmsamling/del-9-sortering.md#anbefalet-procedure). De
fem comparatorer ligger i hver sin fil, så de kan skrives parallelt uden konflikter.

US16 og US17 er kravene. US18 – sortér efter to ting – er en frivillig ekstraopgave. Tag den kun,
når resten virker og er testet.

### 3. Status før i morgen

Slut dagen af med at gå [tjeklisten under Aflevering](../../projekter/filmsamling/readme.md#krav-for-at-få-afleveringen-godkendt)
igennem i gruppen. Skriv ned, hvad der mangler, og hvem der gør hvad i morgen.

> **Deadline i morgen, onsdag 04-11, kl. 23:59.** I morgen er der projektvejledning online – se
> [onsdagens side](../03_ons_2026-11-04/README.md).
