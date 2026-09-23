# Vejledende løsninger – Filer

Løsningerne til [opgaverne](opgaver.md). Al kode er kørt, og testene er grønne (JDK 21, JUnit
5.14.4).

---

# Del A – Den første fil

## Opgave 1 og 2 – Skriv og læs

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class FirstFile {
    public static void main(String[] args) throws FileNotFoundException {
        // Skriv
        PrintStream output = new PrintStream(new File("film.txt"));
        output.println("Jaws");
        output.println("Adams æbler");
        output.println("Psycho");
        output.close();

        // Læs
        Scanner fileScanner = new Scanner(new File("film.txt"));
        int number = 1;
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            System.out.println(number + ": " + line);
            number++;
        }
        fileScanner.close();

        System.out.println("Filen ligger her: " + new File("film.txt").getAbsolutePath());
    }
}
```

* Filen ligger i **projektets rodmappe** – samme mappe som `src` (og `pom.xml` i et Maven-projekt).
* Æ, ø og å står rigtigt: både `PrintStream` og IntelliJ bruger UTF-8.
* Efter to kørsler er der stadig **tre** linjer: `new PrintStream(new File(...))` **overskriver**
  filen hver gang. (Vil man tilføje til en fil, skal man åbne den på en anden måde – det har vi ikke
  brug for.)

## Opgave 3 – Filen findes ikke

Med `flim.txt` går programmet ned med en `FileNotFoundException` – `main` sender den bare videre.
Uden `throws` kompilerer koden ikke (`unreported exception FileNotFoundException`). Med try/catch:

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MissingFile {
    public static void main(String[] args) {
        try {
            Scanner fileScanner = new Scanner(new File("findes-ikke.txt"));
            System.out.println("Filen er åbnet.");
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Kunne ikke åbne filen: " + e.getMessage());
        }
    }
}
```

```text
Kunne ikke åbne filen: findes-ikke.txt (No such file or directory)
```

---

# Del B – Navnelisten

## Opgave 4 – Husk navnene

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class NameList {
    private static final String FILE_NAME = "names.txt";

    private Scanner scanner = new Scanner(System.in);
    private ArrayList<String> names = new ArrayList<>();

    public void start() {
        loadNames();
        System.out.println("Navne på listen: " + names);
        System.out.println("Skriv nye navne. Tom linje = færdig.");
        while (true) {
            System.out.print("Navn: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                break;
            }
            names.add(name);
        }
        saveNames();
    }

    private void loadNames() {
        try {
            Scanner fileScanner = new Scanner(new File(FILE_NAME));
            while (fileScanner.hasNextLine()) {
                names.add(fileScanner.nextLine());
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ingen gemt liste endnu – du starter med en tom liste.");
        }
    }

    private void saveNames() {
        try {
            PrintStream output = new PrintStream(new File(FILE_NAME));
            for (String name : names) {
                output.println(name);
            }
            output.close();
            System.out.println(names.size() + " navne er gemt.");
        } catch (FileNotFoundException e) {
            System.out.println("Navnene kunne ikke gemmes: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new NameList().start();
    }
}
```

## Opgave 5 – throws i stedet

Nu sender `loadNames` og `saveNames` exceptionen videre, og `start()` fanger den – to forskellige
steder med to forskellige beskeder:

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class NameList {
    private static final String FILE_NAME = "names.txt";

    private Scanner scanner = new Scanner(System.in);
    private ArrayList<String> names = new ArrayList<>();

    public void start() {
        try {
            loadNames();
        } catch (FileNotFoundException e) {
            System.out.println("Ingen gemt liste endnu – du starter med en tom liste.");
        }
        System.out.println("Navne på listen: " + names);
        System.out.println("Skriv nye navne. Tom linje = færdig.");
        while (true) {
            System.out.print("Navn: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                break;
            }
            names.add(name);
        }
        try {
            saveNames();
            System.out.println(names.size() + " navne er gemt.");
        } catch (FileNotFoundException e) {
            System.out.println("ADVARSEL: navnene kunne ikke gemmes – " + e.getMessage());
        }
    }

    private void loadNames() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(FILE_NAME));
        while (fileScanner.hasNextLine()) {
            names.add(fileScanner.nextLine());
        }
        fileScanner.close();
    }

    private void saveNames() throws FileNotFoundException {
        PrintStream output = new PrintStream(new File(FILE_NAME));
        for (String name : names) {
            output.println(name);
        }
        output.close();
    }

    public static void main(String[] args) {
        new NameList().start();
    }
}
```

De to fejl er **ikke** det samme:

* Findes filen ikke ved **indlæsning**, er det helt normalt første gang – en venlig besked er nok.
* Kan filen ikke **gemmes**, mister brugeren sine navne. Det skal hun have tydeligt at vide.

Det er et godt argument for at sende exceptionen videre: det er kun den, der kalder, der ved, hvor
alvorligt det er.

---

# Del C – Løbeture i en CSV-fil

## Opgave 6 – RunFileHandler

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

// Gemmer og indlæser løbeture i en CSV-fil: date;kilometers;minutes
public class RunFileHandler {
    private static final String HEADER = "date;kilometers;minutes";

    private String fileName;
    private int skippedLineCount;

    public RunFileHandler(String fileName) {
        this.fileName = fileName;
    }

    public void saveRuns(ArrayList<Run> runs) throws FileNotFoundException {
        PrintStream output = new PrintStream(new File(fileName));
        output.println(HEADER);
        for (Run run : runs) {
            output.println(run.getDate() + ";" + run.getKilometers() + ";" + run.getMinutes());
        }
        output.close();
    }

    public ArrayList<Run> loadRuns() throws FileNotFoundException {
        ArrayList<Run> runs = new ArrayList<>();
        skippedLineCount = 0;
        Scanner fileScanner = new Scanner(new File(fileName));
        if (fileScanner.hasNextLine()) {
            fileScanner.nextLine();   // spring overskriften over
        }
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            if (!line.isBlank()) {
                Run run = parseRun(line);
                if (run == null) {
                    skippedLineCount++;
                } else {
                    runs.add(run);
                }
            }
        }
        fileScanner.close();
        return runs;
    }

    // Returnerer null, hvis linjen ikke er en gyldig løbetur
    private Run parseRun(String line) {
        String[] fields = line.split(";", -1);
        if (fields.length != 3) {
            return null;
        }
        try {
            LocalDate date = LocalDate.parse(fields[0]);
            double kilometers = Double.parseDouble(fields[1]);
            int minutes = Integer.parseInt(fields[2]);
            return new Run(date, kilometers, minutes);
        } catch (IllegalArgumentException e) {
            // Også NumberFormatException, som er en slags IllegalArgumentException
            return null;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public int getSkippedLineCount() {
        return skippedLineCount;
    }
}
```

## Opgave 7 – Tests

```java
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RunFileHandlerTest {

    private static final String TEST_FILE = "test-runs.csv";

    @AfterEach
    void deleteTestFile() {
        new File(TEST_FILE).delete();
    }

    @Test
    void savedRunsCanBeLoadedAgain() throws FileNotFoundException {
        // Arrange
        RunFileHandler fileHandler = new RunFileHandler(TEST_FILE);
        ArrayList<Run> runs = new ArrayList<>();
        runs.add(new Run(LocalDate.of(2026, 10, 25), 5.2, 31));
        runs.add(new Run(LocalDate.of(2026, 10, 28), 10.0, 58));

        // Act
        fileHandler.saveRuns(runs);
        ArrayList<Run> loaded = fileHandler.loadRuns();

        // Assert
        assertEquals(2, loaded.size());
        Run first = loaded.get(0);
        assertEquals(LocalDate.of(2026, 10, 25), first.getDate());
        assertEquals(5.2, first.getKilometers(), 0.001);
        assertEquals(31, first.getMinutes());
        assertEquals(10.0, loaded.get(1).getKilometers(), 0.001);
    }

    @Test
    void emptyListGivesEmptyFile() throws FileNotFoundException {
        RunFileHandler fileHandler = new RunFileHandler(TEST_FILE);

        fileHandler.saveRuns(new ArrayList<>());

        assertTrue(fileHandler.loadRuns().isEmpty());
    }

    @Test
    void missingFileThrowsFileNotFoundException() {
        RunFileHandler fileHandler = new RunFileHandler("findes-ikke.csv");

        assertThrows(FileNotFoundException.class, () -> fileHandler.loadRuns());
    }

    @Test
    void brokenLinesAreSkippedAndCounted() throws FileNotFoundException {
        // Arrange: en fil med to gode og tre ødelagte linjer
        PrintStream output = new PrintStream(new File(TEST_FILE));
        output.println("date;kilometers;minutes");
        output.println("2026-10-25;5.2;31");
        output.println("25-10-2026;5.2;31");     // forkert datoformat
        output.println("2026-10-26;fem;31");     // ikke et tal
        output.println("2026-10-27;5.2");        // for få felter
        output.println("2026-10-28;10.0;58");
        output.close();
        RunFileHandler fileHandler = new RunFileHandler(TEST_FILE);

        // Act
        ArrayList<Run> loaded = fileHandler.loadRuns();

        // Assert
        assertEquals(2, loaded.size());
        assertEquals(3, fileHandler.getSkippedLineCount());
    }

    @Test
    void invalidRunInFileIsSkipped() throws FileNotFoundException {
        PrintStream output = new PrintStream(new File(TEST_FILE));
        output.println("date;kilometers;minutes");
        output.println("2026-10-25;0;31");       // 0 km – Run afviser den
        output.close();
        RunFileHandler fileHandler = new RunFileHandler(TEST_FILE);

        assertTrue(fileHandler.loadRuns().isEmpty());
        assertEquals(1, fileHandler.getSkippedLineCount());
    }
}
```

## Opgave 8 – Løbedagbogen

```java
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class RunLog {
    private Scanner scanner = new Scanner(System.in);
    private RunFileHandler fileHandler = new RunFileHandler("runs.csv");
    private ArrayList<Run> runs = new ArrayList<>();
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void start() {
        loadRuns();
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("1. Tilføj en tur");
            System.out.println("2. Vis alle ture");
            System.out.println("0. Afslut");
            System.out.print("Vælg: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addRun();
                case "2" -> showRuns();
                case "0" -> running = false;
                default -> System.out.println("Ukendt valg: " + choice);
            }
        }
        saveRuns();
    }

    private void addRun() {
        LocalDate date = readDate("Dato (dd-mm-åååå, Enter = i dag): ");
        double kilometers = readDouble("Distance i km: ");
        int minutes = readInt("Tid i minutter: ");
        try {
            runs.add(new Run(date, kilometers, minutes));
            System.out.println("Turen er tilføjet.");
        } catch (IllegalArgumentException e) {
            System.out.println("Turen blev ikke tilføjet: " + e.getMessage());
        }
    }

    private void showRuns() {
        if (runs.isEmpty()) {
            System.out.println("Ingen ture endnu.");
            return;
        }
        for (Run run : runs) {
            double speed = run.getKilometers() / (run.getMinutes() / 60.0);
            System.out.printf("%s: %.1f km på %d min. (%.1f km/t)%n",
                    run.getDate().format(dateFormatter), run.getKilometers(), run.getMinutes(), speed);
        }
    }

    private void loadRuns() {
        try {
            runs = fileHandler.loadRuns();
            System.out.println(runs.size() + " ture er indlæst.");
            if (fileHandler.getSkippedLineCount() > 0) {
                System.out.println("Advarsel: " + fileHandler.getSkippedLineCount()
                        + " linje(r) kunne ikke læses.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ingen gemte ture endnu.");
        }
    }

    private void saveRuns() {
        try {
            fileHandler.saveRuns(runs);
            System.out.println("Turene er gemt.");
        } catch (FileNotFoundException e) {
            System.out.println("Turene kunne IKKE gemmes: " + e.getMessage());
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\" er ikke et helt tal. Prøv igen.");
            }
        }
    }

    // Accepterer både komma og punktum
    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\" er ikke et tal. Prøv igen.");
            }
        }
    }

    private LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return LocalDate.now();
            }
            try {
                return LocalDate.parse(input, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("\"" + input + "\" er ikke en dato på formen dd-mm-åååå. Prøv igen.");
            }
        }
    }

    public static void main(String[] args) {
        new RunLog().start();
    }
}
```

En kørsel (anden gang programmet startes, er de to ture der stadig):

```text
Vælg: 1
Dato (dd-mm-åååå, Enter = i dag): i går
"i går" er ikke en dato på formen dd-mm-åååå. Prøv igen.
Dato (dd-mm-åååå, Enter = i dag): 28-10-2026
Distance i km: ti
"ti" er ikke et tal. Prøv igen.
Distance i km: 10
Tid i minutter: 58
Turen er tilføjet.
...
Vælg: 2
25-10-2026: 5.2 km på 31 min. (10.1 km/t)
28-10-2026: 10.0 km på 58 min. (10.3 km/t)
```

`printf` skriver decimaltal efter computerens sprogindstilling – på en dansk computer står der
`5,2 km` og `10,1 km/t`. **Filen** skrives altid med punktum (`5.2`), fordi `Double.toString` ikke
afhænger af sproget – og det er vigtigt, for `Double.parseDouble` læser kun punktum.

---

# Del D – Tegnkodning

## Opgave 9 – En fil fra en anden verden

1. IntelliJ forventer UTF-8, så æ, ø og å vises som mærkelige tegn (typisk `�`). Nogle gange
   opdager IntelliJ selv, at filen har en anden kodning, og viser en besked om det. Statuslinjen
   viser den kodning, IntelliJ bruger til at **vise** filen.
2. En almindelig `Scanner` læser **0 linjer** – og kaster ingen exception. Den stopper stille, så
   snart den møder en byte, der ikke er gyldig UTF-8. Man kan se det med `scanner.ioException()`,
   som her returnerer en `MalformedInputException` – men det er der ingen, der husker at tjekke.
3. Med `StandardCharsets.ISO_8859_1` bliver begge linjer læst rigtigt. Den constructor kaster
   `IOException` (overklassen til `FileNotFoundException`), fordi den også kan fejle af andre grunde
   end en manglende fil.

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Latin1 {
    public static void main(String[] args) throws IOException {
        System.out.println(Charset.defaultCharset());
        PrintStream output = new PrintStream(new File("latin1.txt"), StandardCharsets.ISO_8859_1);
        output.println("Adams æbler");
        output.println("Blå mænd");
        output.close();

        Scanner utf8 = new Scanner(new File("latin1.txt"));
        int count = 0;
        while (utf8.hasNextLine()) {
            System.out.println("UTF-8: " + utf8.nextLine());
            count++;
        }
        System.out.println("Linjer læst som UTF-8: " + count);
        System.out.println("ioException: " + utf8.ioException());
        utf8.close();

        Scanner latin = new Scanner(new File("latin1.txt"), StandardCharsets.ISO_8859_1);
        while (latin.hasNextLine()) {
            System.out.println("ISO-8859-1: " + latin.nextLine());
        }
        latin.close();
    }
}
```

```text
UTF-8
Linjer læst som UTF-8: 0
ioException: java.nio.charset.MalformedInputException: Input length = 1
ISO-8859-1: Adams æbler
ISO-8859-1: Blå mænd
```

---

# Udfordringer

## Udfordring 1 – Semikolon i titlen

Titlen `Kærlighed; og andre katastrofer` bliver til **to** felter, så linjen får 8 felter i stedet
for 7, og `parseMovie` springer den over – filmen er væk næste gang, programmet starter. To
løsninger står i [del 7](../../projekter/filmsamling/del-7-filer.md#semikolon-i-titlen): lad `Movie`
afvise semikolon i tekstfelterne (en regel, som del 6), eller lad `FileHandler` erstatte det med et
andet tegn, når der skrives, og tilbage igen, når der læses.

## Udfordring 2 – Ordtæller

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordCount {
    public static void main(String[] args) {
        try {
            Scanner fileScanner = new Scanner(new File("tekst.txt"));
            int lines = 0;
            int words = 0;
            int characters = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lines++;
                characters += line.length();
                String trimmed = line.trim();
                if (!trimmed.isEmpty()) {
                    // \\s+ = ét eller flere mellemrum (eller tabulatorer) i træk
                    words += trimmed.split("\\s+").length;
                }
            }
            fileScanner.close();
            System.out.println(lines + " linjer, " + words + " ord, " + characters + " tegn");
        } catch (FileNotFoundException e) {
            System.out.println("Kunne ikke finde filen: " + e.getMessage());
        }
    }
}
```

Med filen

```text
Der var engang  en film

   om en haj
```

skriver programmet `3 linjer, 8 ord, 35 tegn`. `split(" ")` ville have talt for mange "ord": to
mellemrum efter hinanden giver et tomt ord imellem. `split("\\s+")` deler ved ét **eller flere**
mellemrum.

## Udfordring 3 – try-with-resources

```java
    public ArrayList<Run> loadRuns() throws FileNotFoundException {
        ArrayList<Run> runs = new ArrayList<>();
        skippedLineCount = 0;
        // Scanneren lukkes automatisk, når try-blokken slutter – også ved en exception
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();   // spring overskriften over
            }
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (!line.isBlank()) {
                    Run run = parseRun(line);
                    if (run == null) {
                        skippedLineCount++;
                    } else {
                        runs.add(run);
                    }
                }
            }
        }
        return runs;
    }
```

Filen bliver lukket automatisk, når blokken slutter – også hvis en linje får koden til at kaste en
exception undervejs. Man kan ikke glemme `close()`. Der er ingen `catch`: `FileNotFoundException`
sendes stadig videre med `throws`.
