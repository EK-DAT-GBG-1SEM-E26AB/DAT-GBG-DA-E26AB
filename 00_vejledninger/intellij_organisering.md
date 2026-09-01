# Organisering af Java-projekter, packages og opgaver i IntelliJ

## Overordnet idé

I de første uger kommer vi til at arbejde med mange små Java-programmer. Mange af dem skal bruges til at afprøve et bestemt begreb, for eksempel variable, betingelser, løkker, klasser eller objekter.

Derfor organiserer vi koden sådan:

* Vi laver **ét IntelliJ-projekt pr. uge**
* Inde i projektet laver vi **én package pr. undervisningsgang**
* Inde i dagens package laver vi **én klasse pr. opgave**
* Hver opgaveklasse har sin egen `main`-metode og kan køres separat
* En eventuel `Main`-klasse bruges til lærerens fælles eksempler eller små forsøg
* Det gennemgående projekt får sin egen package, når det giver mening

På den måde kan vi gemme alle opgaver og eksempler uden at overskrive tidligere kode. Samtidig kan hver opgave køres og afprøves for sig.

---

## Hvorfor bruger vi packages?

En package kan sammenlignes med en mappe, der hjælper os med at organisere Java-klasser.

Vi bruger én package pr. undervisningsgang. Det gør det let at finde dagens eksempler og opgaver.

Eksempel:

```text
dag3_variable_datatyper_aritmetik
dag4_logiske_operatorer_betingelser
dag5_io_scanner_print_git
```

En klasse får et fuldt navn, som består af package-navnet og klassenavnet:

```text
dag3_variable_datatyper_aritmetik.Opgave01
dag3_variable_datatyper_aritmetik.Opgave02
dag4_logiske_operatorer_betingelser.Opgave01
```

---

## Hvorfor laver vi én klasse pr. opgave?

Når mange opgaver skrives i den samme `main`-metode, kan koden hurtigt blive uoverskuelig. Flere opgaver bruger desuden ofte de samme variabelnavne, for eksempel `age`, `number`, `result` eller `score`.

Hvis hver opgave ligger i sin egen klasse:

* kan variabelnavne genbruges fra opgave til opgave
* kan hver opgave køres separat
* påvirker fejl i én opgave ikke arbejdet med de andre opgaver
* er det let at finde en bestemt opgave senere
* behøver vi ikke oprette ekstra metoder for at adskille opgaverne

Dette er især praktisk, før vi har lært at skrive vores egne metoder.

---

# Samlet oversigt over projekter og packages

Strukturen kan for eksempel se sådan ud:

```text
uge35-intro-java/
    src/
        dag1_introdag_studiegrupper/
            Main.java

        dag2_installation_java_notepad/
            Main.java

        dag3_variable_datatyper_aritmetik/
            Main.java
            Opgave01.java
            Opgave02.java
            Opgave03.java
            ...
            Opgave40.java

        dag4_logiske_operatorer_betingelser/
            Main.java
            Opgave01.java
            Opgave02.java
            ...

        dag5_io_scanner_print_git/
            Main.java
            Opgave01.java
            Opgave02.java
            ...


uge36-loops-arrays-strings/
    src/
        dag1_while_loops/
            Main.java
            Opgave01.java
            Opgave02.java
            ...

        dag2_for_loops_while_loops/
            Main.java
            Opgave01.java
            Opgave02.java
            ...

        dag3_arrays/
            Main.java
            Opgave01.java
            Opgave02.java
            ...


uge37-klasser-objekter-metoder/
    src/
        dag1_objekter_klasser_intro/
            Main.java
            Book.java
            Opgave01.java
            Opgave02.java

        dag2_objekter_klasser_indkapsling/
            Main.java
            Book.java
            Opgave01.java
            Opgave02.java

        bogsamling/
            Main.java
            Book.java


uge38-relationer-arraylist/
    src/
        dag1_aktivitetsdiagram_debugger/
            Main.java
            Opgave01.java
            Opgave02.java

        dag2_objekter_i_objekter_klassediagrammer/
            Main.java
            Book.java
            Library.java
            Opgave01.java

        bogsamling/
            Main.java
            Book.java
            Library.java
```

Antallet af opgaveklasser afhænger naturligvis af dagens opgaver.

---

# Sådan opretter du et Java-projekt i IntelliJ

## Trin 1: Opret et nyt projekt

Når du starter på en ny uge, skal du oprette et nyt IntelliJ-projekt.

Eksempel på projektnavne:

```text
uge35-intro-java
uge36-loops-arrays-strings
uge37-klasser-objekter-metoder
uge38-relationer-arraylist
```

Gør sådan:

1. Åbn IntelliJ
2. Vælg **New Project**
3. Vælg **Java**
4. Giv projektet et navn, fx `uge35-intro-java`
5. Vælg, hvor projektet skal gemmes
6. Klik **Create**

---

## Trin 2: Find `src`-mappen

Når projektet er oprettet, skal du finde mappen:

```text
src
```

Det er i `src`, at Java-koden skal ligge.

---

# Sådan opretter du en package

## Trin 3: Opret dagens package

Vi laver typisk én package pr. undervisningsgang.

Eksempel på package-navne:

```text
dag1_objekter_klasser_intro
dag2_objekter_klasser_indkapsling
dag3_variable_datatyper_aritmetik
bogsamling
```

Gør sådan:

1. Højreklik på `src`
2. Vælg **New**
3. Vælg **Package**
4. Skriv package-navnet, fx `dag3_variable_datatyper_aritmetik`
5. Tryk **Enter**

---

# Sådan opretter du en klasse til en opgave

## Trin 4: Opret opgaveklassen

Hver opgave skal ligge i sin egen Java-klasse.

Gør sådan:

1. Højreklik på dagens package
2. Vælg **New**
3. Vælg **Java Class**
4. Skriv klassens navn, fx `Opgave01`
5. Tryk **Enter**

IntelliJ opretter nu filen `Opgave01.java`:

```java
package dag3_variable_datatyper_aritmetik;

public class Opgave01 {
}
```

Bemærk, at filnavnet og klassens navn skal være ens:

```text
Opgave01.java  →  public class Opgave01
```

---

## Trin 5: Tilføj `main`-metoden

Hver opgaveklasse skal have sin egen `main`-metode:

```java
package dag3_variable_datatyper_aritmetik;

public class Opgave01 {

    public static void main(String[] args) {
        int age = 20;
        System.out.println(age);
    }
}
```

Du kan køre opgaven ved at trykke på den grønne pil ud for `main`-metoden.

Den næste opgave oprettes i en ny fil:

```java
package dag3_variable_datatyper_aritmetik;

public class Opgave02 {

    public static void main(String[] args) {
        String name = "Anna";
        int age = 22;

        System.out.println(name);
        System.out.println(age);
    }
}
```

Begge klasser må gerne have en variabel med navnet `age`, fordi de er to separate programmer.

---

# Hvad bruges `Main.java` til?

En `Main`-klasse kan bruges til lærerens fælles eksempler, demonstrationer og små forsøg fra undervisningen:

```java
package dag3_variable_datatyper_aritmetik;

public class Main {

    public static void main(String[] args) {
        System.out.println("Fælles eksempel fra undervisningen");
    }
}
```

Opgavebesvarelserne skal stadig ligge i `Opgave01`, `Opgave02` osv. `Main` skal derfor ikke indeholde alle dagens opgaver.

Hvis der ikke er brug for en fælles `Main`-klasse på en undervisningsdag, kan den udelades.

---

# Regler for navngivning

## Projektnavne

Projektnavne må gerne være beskrivende og kan indeholde bindestreger:

```text
uge35-intro-java
uge36-loops-arrays-strings
```

## Package-navne

Package-navne skrives med små bogstaver og uden mellemrum:

```text
dag3_variable_datatyper_aritmetik
dag3_enum_switch
bogsamling
```

Undgå æ, ø og å i package-navne. Brug for eksempel `oevelse` i stedet for `øvelse`.

## Klassenavne

Klassenavne skrives med stort begyndelsesbogstav:

```text
Main
Opgave01
Opgave02
Book
Library
```

Brug gerne to cifre i opgavenummeret. Så sorterer IntelliJ filerne i den rigtige rækkefølge:

```text
Opgave01
Opgave02
Opgave09
Opgave10
```

---

# Hvad skal du gøre hver undervisningsgang?

Når vi starter en ny undervisningsgang:

1. Åbn ugens IntelliJ-projekt
2. Opret en ny package til dagens kode
3. Opret eventuelt `Main.java` til fælles eksempler
4. Opret én Java-klasse pr. opgave: `Opgave01`, `Opgave02` osv.
5. Tilføj en `main`-metode i hver opgaveklasse
6. Kør og afprøv hver opgave separat
7. Gem koden, så du kan finde den igen senere

Eksempel:

```text
src/
    dag3_variable_datatyper_aritmetik/
        Main.java
        Opgave01.java
        Opgave02.java
        Opgave03.java
```

---

# Hvad skal du gøre, når du arbejder med projektet?

Når du arbejder med det gennemgående projekt **Min bogsamling**, skal du bruge pakken:

```text
bogsamling
```

Her ligger de klasser, som tilsammen udgør programmet:

```text
src/
    bogsamling/
        Main.java
        Book.java
        Library.java
```

Du skal ikke oprette en ny `Book`-klasse for hver opgave, hvis du arbejder videre på det samme projekt. Reglen om én klasse pr. opgave gælder de selvstændige øvelser. I et samlet projekt bestemmes klasserne i stedet af programmets model og ansvar.

---

# Samlet progression

```text
uge35-intro-java
    ↓
IntelliJ, Main, output, variable, datatyper og simple beregninger

uge36-loops-arrays-strings
    ↓
while-løkker, for-løkker, arrays, strings og repetition

uge37-klasser-objekter-metoder
    ↓
Book som simpel klasse, objekter, metoder, enum og switch

uge38-relationer-arraylist
    ↓
Library med mange Book-objekter, ArrayList og 1:mange-relation
```

---

# Kort opsummering

Vi bruger denne struktur:

```text
Ét projekt pr. uge
Én package pr. undervisningsgang
Én klasse med egen main-metode pr. opgave
En valgfri Main-klasse til fælles eksempler
En separat package til det gennemgående projekt, når det giver mening
```

Det gør det lettere at arbejde med små opgaver, genbruge variabelnavne og køre hver løsning separat. Samtidig gemmer vi tidligere eksempler og opgaver, så de er lette at finde igen.
