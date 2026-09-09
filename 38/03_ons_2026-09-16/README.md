# ArrayList

## Beskrivelse

I den forrige lektion arbejdede vi med arrays af objekter. Et array virker godt, når vi på forhånd ved, hvor mange elementer der skal være plads til.

I mange programmer ændrer antallet af elementer sig imidlertid, mens programmet kører. Et bibliotek får nye bøger, en indkøbskurv får flere varer, og spilleren i Adventure kan samle ting op og lægge dem fra sig.

Her kan vi bruge `ArrayList`. En `ArrayList` minder om et array, men den kan vokse og blive mindre efter behov.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

- forklare forskellen på et array og en `ArrayList`
- importere og oprette en `ArrayList`
- forklare typen mellem `<` og `>`
- tilføje elementer med `add()`
- aflæse og ændre elementer med `get()` og `set()`
- fjerne elementer med `remove()`
- finde antallet af elementer med `size()`
- undersøge en liste med `contains()`, `indexOf()` og `isEmpty()`
- gennemløbe en `ArrayList` med både et almindeligt og et enhanced for-loop
- bruge en `ArrayList` med egne objekter
- forklare hvorfor primitive typer som `int` skrives som `Integer` i en `ArrayList`

## Se denne video før undervisningen

[ArrayLists](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=8h55m38s) (til: 09:05:23)

## Læs nedenstående før undervisningen

## Fra array til ArrayList

Et array har en fast størrelse:

```java
String[] names = new String[3];
```

Der er plads til præcis tre tekster. Størrelsen kan ikke ændres senere.

En `ArrayList` kan derimod vokse:

```java
ArrayList<String> names = new ArrayList<>();

names.add("Anna");
names.add("Ali");
names.add("Sofie");
names.add("Noah");
```

Vi behøver ikke beslutte størrelsen på forhånd.

## Import

`ArrayList` er ikke automatisk tilgængelig på samme måde som eksempelvis `String`.

Øverst i filen skal vi derfor skrive:

```java
import java.util.ArrayList;
```

En hel klasse kan eksempelvis begynde sådan:

```java
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> names = new ArrayList<>();
    }
}
```

IntelliJ kan normalt tilføje importen automatisk.

## Typen mellem `<` og `>`

I denne linje:

```java
ArrayList<String> names = new ArrayList<>();
```

fortæller `String`, hvilken type objekter listen må indeholde.

Det betyder, at dette er tilladt:

```java
names.add("Anna");
```

men dette er ikke:

```java
names.add(42);
```

Java kan derfor opdage mange fejl, allerede inden programmet køres.

## Tilføj elementer med `add()`

Et element tilføjes normalt bagest i listen:

```java
ArrayList<String> fruits = new ArrayList<>();

fruits.add("Æble");
fruits.add("Banan");
fruits.add("Pære");
```

Man kan også indsætte et element på en bestemt plads:

```java
fruits.add(1, "Appelsin");
```

De efterfølgende elementer flyttes én plads mod højre.

## Indeks og `get()`

Ligesom i et array begynder indeks ved `0`.

```java
System.out.println(fruits.get(0));
System.out.println(fruits.get(1));
```

Forskellen i syntaks er:

```java
// Array
fruitsArray[0]

// ArrayList
fruits.get(0)
```

Hvis man forsøger at hente en plads, der ikke findes, får man en `IndexOutOfBoundsException`.

## Antal elementer med `size()`

Et array bruger attributten `length`:

```java
numbers.length
```

En `ArrayList` bruger metoden `size()`:

```java
fruits.size()
```

Eksempel:

```java
System.out.println("Antal frugter: " + fruits.size());
```

## Ændr et element med `set()`

Et eksisterende element kan udskiftes:

```java
fruits.set(1, "Mango");
```

`set()` ændrer elementet på en eksisterende plads. Den tilføjer ikke en ny plads.

## Fjern elementer med `remove()`

Et element kan fjernes ved hjælp af dets indeks:

```java
fruits.remove(0);
```

eller ved hjælp af selve værdien:

```java
fruits.remove("Banan");
```

Når et element fjernes, flyttes de efterfølgende elementer mod venstre. Listen får dermed én plads mindre.

## Søg i listen

`contains()` undersøger, om en værdi findes:

```java
if (fruits.contains("Pære")) {
    System.out.println("Pære findes i listen");
}
```

`indexOf()` returnerer elementets indeks:

```java
int index = fruits.indexOf("Pære");
```

Hvis værdien ikke findes, returneres `-1`.

## Er listen tom?

```java
if (fruits.isEmpty()) {
    System.out.println("Listen er tom");
}
```

Alle elementer kan fjernes med:

```java
fruits.clear();
```

## Gennemløb med et almindeligt for-loop

Når vi har brug for elementets indeks, kan vi skrive:

```java
for (int i = 0; i < fruits.size(); i++) {
    System.out.println(i + ": " + fruits.get(i));
}
```

Læg mærke til `size()` og `get(i)`.

## Gennemløb med enhanced for-loop

Hvis vi kun skal bruge elementerne, er et enhanced for-loop ofte nemmere at læse:

```java
for (String fruit : fruits) {
    System.out.println(fruit);
}
```

Det kan læses som:

> For hver `fruit` i `fruits`, udskriv `fruit`.

## ArrayList med tal

En `ArrayList` kan kun indeholde objekter. Derfor kan vi ikke skrive:

```java
ArrayList<int> numbers = new ArrayList<>();
```

Til heltal bruger vi wrapper-klassen `Integer`:

```java
ArrayList<Integer> numbers = new ArrayList<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
```

Java sørger automatisk for omregningen mellem `int` og `Integer` i de fleste almindelige situationer.

Andre eksempler er:

| Primitiv type | Wrapper-klasse |
|---|---|
| `int` | `Integer` |
| `double` | `Double` |
| `boolean` | `Boolean` |
| `char` | `Character` |

## Pas på `remove()` med Integer

For en liste med tekster er forskellen tydelig:

```java
fruits.remove(0);        // fjern elementet på indeks 0
fruits.remove("Pære");  // fjern værdien "Pære"
```

Med `Integer` kan tallet blive opfattet som et indeks:

```java
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(10);
numbers.add(20);
numbers.add(30);

numbers.remove(1);                 // fjerner elementet på indeks 1
numbers.remove(Integer.valueOf(10)); // fjerner værdien 10
```

## ArrayList med egne objekter

En liste kan også indeholde objekter fra en klasse, vi selv har lavet:

```java
public class Book {

    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void printInfo() {
        System.out.println(title + " af " + author);
    }
}
```

Listen kan derefter oprettes sådan:

```java
ArrayList<Book> books = new ArrayList<>();

books.add(new Book("The Hobbit", "J.R.R. Tolkien"));
books.add(new Book("1984", "George Orwell"));
books.add(new Book("Dune", "Frank Herbert"));
```

Og gennemløbes sådan:

```java
for (Book book : books) {
    book.printInfo();
}
```

## Søg blandt objekter

Vi kan genbruge søgemønstret fra arrays:

```java
Book found = null;

for (Book book : books) {

    if (book.getTitle().equals("Dune")) {
        found = book;
    }
}

if (found != null) {
    found.printInfo();
}
else {
    System.out.println("Bogen blev ikke fundet");
}
```

Vi bruger stadig `.equals()` til at sammenligne tekster.

## ArrayList som attribut i en klasse

Et bibliotek kan selv holde styr på sin liste af bøger:

```java
import java.util.ArrayList;

public class Library {

    private String name;
    private ArrayList<Book> books;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public int getNumberOfBooks() {
        return books.size();
    }

    public void printBooks() {
        for (Book book : books) {
            book.printInfo();
        }
    }
}
```

Læg mærke til, at listen oprettes i konstruktøren. Hvis vi glemmer det, er `books` lig med `null`, og `books.add(...)` giver en `NullPointerException`.

Fra `Main` kan klassen bruges sådan:

```java
Library library = new Library("Byens bibliotek");

library.addBook(new Book("The Hobbit", "J.R.R. Tolkien"));
library.addBook(new Book("Dune", "Frank Herbert"));

System.out.println("Antal bøger: " + library.getNumberOfBooks());
library.printBooks();
```

Det samme princip kan senere bruges i Adventure:

- et `Room` kan have en liste af `Item`
- en spiller kan have en liste af ting i sit inventory
- når en ting samles op, fjernes den fra rummet og tilføjes til spilleren

## Array og ArrayList sammenlignet

| Array | ArrayList |
|---|---|
| Fast størrelse | Kan vokse og blive mindre |
| `items.length` | `items.size()` |
| `items[i]` | `items.get(i)` |
| `items[i] = value` | `items.set(i, value)` |
| Kan indeholde primitive typer | Indeholder objekter; brug wrapper-klasser til tal |
| Ingen `add()` eller `remove()` | Har blandt andet `add()` og `remove()` |

Et array er ikke dårligt. Brug et array, når antallet af pladser er fast og kendt. Brug ofte en `ArrayList`, når antallet ændrer sig.

## Det vigtigste at tage med

- importér med `import java.util.ArrayList;`
- angiv elementtypen, eksempelvis `ArrayList<String>` eller `ArrayList<Book>`
- opret listen med `new ArrayList<>()`
- `add()` tilføjer, `get()` henter, `set()` ændrer, og `remove()` fjerner
- `size()` giver det aktuelle antal elementer
- indeks begynder ved `0`, ligesom i arrays
- brug `Integer`, `Double` og andre wrapper-klasser i stedet for primitive typer
- en `ArrayList` kan indeholde objekter fra dine egne klasser
- en `ArrayList`-attribut skal oprettes, før den kan bruges

## Aktiviteter i undervisningen

Arbejd med disse [opgaver](opgaver.md).
