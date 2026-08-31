# Opgaver – Arrays

I disse opgaver skal du arbejde med **arrays** i Java.

## Kom i gang

Opret et nyt Java-projekt i IntelliJ.

Opret herefter en klasse med navnet:

```java
Main
```

Lav en `main`-metode i klassen:

```java
public class Main {

    public static void main(String[] args) {

    }
}
```

Lav løsningerne på opgaverne i `main`-metoden, og afprøv dem undervejs.

---

## Opgave 1 – Dit første array

Opret et `int`-array med fem værdier: `10`, `20`, `30`, `40`, `50`.

Udskriv alle fem værdier på hver sin linje.

Forventet output:

```text
10
20
30
40
50
```

---

## Opgave 2 – Ændring af elementer

Opret et `int`-array med tre værdier: `5`, `10`, `15`.

Ændre andet element (index `1`) til `99`, og udskriv hele arrayet.

Forventet output:

```text
5
99
15
```

---

## Opgave 3 – Sidste element

Opret et `int`-array med værdier: `1`, `2`, `3`, `4`, `5`.

Udskriv kun det **sidste** element ved at bruge `array.length - 1`.

Forventet output:

```text
5
```

---

## Opgave 4 – Løb arrayet igennem

Opret et `int`-array med værdier: `7`, `14`, `21`, `28`, `35`.

Brug et **for-loop** til at udskrive alle elementer.

Forventet output:

```text
7
14
21
28
35
```

---

## Opgave 5 – Baglæns gennem arrayet

Opret et `int`-array med værdier: `10`, `20`, `30`, `40`, `50`.

Brug et **for-loop** til at udskrive alle elementer **baglæns** (fra sidste til første).

Forventet output:

```text
50
40
30
20
10
```

> **Hint:** Start løkken ved `array.length - 1` og tæl ned med `i--`.

---

## Opgave 6 – Summer af tal

Opret et `int`-array med værdier: `5`, `10`, `15`, `20`, `25`.

Brug et **for-loop** til at beregne summen af alle elementer.

Udskriv:

```text
Summen er [sum]
```

Eksempel:

```text
Summen er 75
```

> **Tip:** Opret en variabel `sum = 0` før loopet og læg hver værdi til.

---

## Opgave 7 – Største værdi

Opret et `int`-array med værdier: `23`, `5`, `48`, `12`, `39`.

Find den **største værdi** i arrayet og udskriv den.

Forventet output:

```text
48
```

> **Hint:** Start med at sætte `max = array[0]`. Løb så gennem resten af arrayet og opdater `max`, hver gang du finder en større værdi.

---

## Opgave 8 – Bruger fylder arrayet

Opret et tomt `int`-array med plads til tre elementer:

```java
int[] numbers = new int[3];
```

Opret en `Scanner`, og bed brugeren om at indtaste tre tal – ét ad gangen.

Gem hver værdi i arrayet, og udskriv til sidst alle tre tal.

Eksempel:

```text
Indtast tal 1: 42
Indtast tal 2: 17
Indtast tal 3: 88

Du indtastede:
42
17
88
```

---

## Opgave 9 – Søgning i arrayet

Opret et `int`-array med værdier: `3`, `7`, `2`, `9`, `7`, `1`.

Bed brugeren om at indtaste et tal, som du skal søge efter i arrayet.

Hvis tallet findes, skal du udskrive hvilket index det står på.

Hvis det ikke findes, skal du skrive `"Tallet blev ikke fundet"`.

Eksempel 1:

```text
Hvilket tal skal jeg søge efter? 9
Tallet blev fundet på index 3
```

Eksempel 2:

```text
Hvilket tal skal jeg søge efter? 5
Tallet blev ikke fundet
```

> **Hint:** Brug `-1` som startværdi for `foundAt`. Hvis den stadig er `-1` efter løkket, blev tallet ikke fundet.

---

## Opgave 10 – Tæl elementer under en grænse

Opret et `int`-array med værdier: `15`, `8`, `25`, `6`, `19`, `3`, `22`.

Tæl hvor mange elementer der er **mindre end 15**.

Udskriv:

```text
Der er [antal] elementer mindre end 15
```

Forventet output:

```text
Der er 3 elementer mindre end 15
```

---

## Opgave 11 – Tjek om værdien er i arrayet

Opret et `int`-array med værdier: `10`, `22`, `35`, `44`, `51`.

Bed brugeren om at indtaste et tal.

Udskriv `true` hvis tallet er i arrayet, ellers `false`.

Eksempel:

```text
Hvilket tal skal jeg søge efter? 35
true
```

---

## Opgave 12 – Gennemsnit af elementer

Opret et `int`-array med værdier: `12`, `18`, `15`, `22`, `13`.

Beregn og udskriv **gennemsnittet** af alle elementer.

Forventet output:

```text
16.0
```

> **Tip:** Brug cast `(double)` til at sikre, at divisionen giver et decimaltal.

---

## Opgave 13 – Erstat værdier

Opret et `int`-array med værdier: `5`, `10`, `15`, `20`, `25`.

Erstat alle værdier, der er **større end 15**, med værdien `99`.

Udskriv hele arrayet efter ændringen.

Forventet output:

```text
5
10
15
99
99
```

---

## Opgave 14 – Nested loops – Gangetabel med arrays

Opret to `int`-arrays:

```java
int[] factor1 = {2, 3, 4};
int[] factor2 = {1, 2, 3};
```

Brug **nested loops** (løkke i løkke) til at udskrive et lille multiplikationsbord.

Forventet output:

```text
2 * 1 = 2
2 * 2 = 4
2 * 3 = 6
3 * 1 = 3
3 * 2 = 6
3 * 3 = 9
4 * 1 = 4
4 * 2 = 8
4 * 3 = 12
```

---

## Opgave 15 – Interaktiv søgning med brugerinput

Opret et `int`-array med værdier: `11`, `23`, `35`, `42`, `56`, `68`, `79`.

Lav et program, der:

1. Beder brugeren om at søge efter et tal
2. Hvis tallet findes, udskriv dets index
3. Hvis det ikke findes, spørg brugeren igen
4. Brugeren kan skrive `0` for at stoppe

Eksempel:

```text
Søg efter et tal (eller 0 for at stoppe): 35
Fundet på index 2!

Søg efter et tal (eller 0 for at stoppe): 50
Ikke fundet

Søg efter et tal (eller 0 for at stoppe): 0
Farvel!
```

> **Tip:** Brug et `while`-loop som hele programmet og et `for`-loop til søgningen.

---

## Opgave 16 – Minimum og maksimum

Opret et `int`-array med værdier: `34`, `12`, `56`, `8`, `45`, `23`, `67`, `15`.

Find både det **mindste** og det **største** tal i arrayet.

Udskriv begge værdier:

```text
Mindste: 8
Største: 67
```

---

## Opgave 17 – Sortér arrayet

Opret et `int`-array med værdier: `64`, `34`, `25`, `12`, `22`, `11`, `90`.

Sorter arrayet i **stigende rækkefølge** og udskriv det sorterede array.

Forventet output:

```text
11
12
22
25
34
64
90
```

> **Hint:** Implementer en enkel sorteringsalgoritme, f.eks. "bubble sort" eller "selection sort", se filerne til dagens lektion.

---

## Opgave 18 – Invert arrayet

Opret et `int`-array med værdier: `1`, `2`, `3`, `4`, `5`.

Lav en **ny version** af arrayet, hvor alle elementer er i omvendt rækkefølge.

Udskriv det inverterede array.

Forventet output:

```text
5
4
3
2
1
```

> **Tip:** Opret et nyt array, og læs fra det oprindelige baglæns.

---

## Opgave 19 – Sammenlæg to arrays

Opret to `int`-arrays:

```java
int[] array1 = {1, 2, 3};
int[] array2 = {4, 5, 6};
```

Lav et **tredje array**, der indeholder alle elementer fra både `array1` og `array2`.

Udskriv det nye array.

Forventet output:

```text
1
2
3
4
5
6
```

---

## Opgave 20 – Quiz-spil med arrays

Lav et enkelt quiz-spil med arrays.

Du skal bruge:

- Et array med **spørgsmål** (String)
- Et array med **rigtige svar** (String)
- Scanner til brugerens svar

Eksempel:

```text
Spørgsmål 1: Hvad er hovedstaden i Danmark?
Dit svar: København
Korrekt!

Spørgsmål 2: Hvad er 2 + 2?
Dit svar: 3
Forkert. Det rigtige svar er 4

Antal rigtige: 1 ud af 2
```

> **Tip:** Brug en løkke til at stille alle spørgsmål, en tæller til resultat, og sammenlign brugerens svar med de rigtige.

---

## Udfordring – 2D-arrays (Multiplikationstabel)

Lav et program, der tegner en **multiplikationstabel** som et 2D-array.

Start simpelt:

```java
int[][] table = new int[5][5];
```

Fyld tabellen, så `table[i][j] = (i+1) * (j+1)`.

Udskriv tabellen i et pænt format:

```text
   1  2  3  4  5
1  1  2  3  4  5
2  2  4  6  8 10
3  3  6  9 12 15
4  4  8 12 16 20
5  5 10 15 20 25
```

---

## Udfordring – Bubble Sort med tæller

Implementer **bubble sort** på et array, og tæl hvor mange **swaps** (ombytninger) der skal til.

Udskriv arrayet før og efter, samt antal swaps.

Eksempel:

```text
Før: 64 34 25 12 22
Efter: 12 22 25 34 64
Antal swaps: 8
```

---

## Udfordring – Mini-ordbog

Lav et program, der fungerer som en mini-ordbog.

Du skal have:

- Et array med **engelske ord**
- Et array med **danske oversættelser** (samme rækkefølge)

Programmet skal:

1. Spørge brugeren om at skrive et engelsk ord
2. Søge efter ordet i det engelske array
3. Hvis det findes, udskriv den danske oversættelse
4. Hvis det ikke findes, skriv `"Ordet blev ikke fundet"`
5. Brugeren kan skrive `stop` for at afslutte

Eksempel:

```text
Skriv et engelskord (eller 'stop' for at afslutte): hello
Dansk oversættelse: hej

Skriv et engelskord (eller 'stop' for at afslutte): world
Dansk oversættelse: verden

Skriv et engelskord (eller 'stop' for at afslutte): xyz
Ordet blev ikke fundet

Skriv et engelskord (eller 'stop' for at afslutte): stop
Farvel!
```

> **Tip:** Brug indekset fra søgningen i det engelske array til at finde den rigtige danske oversættelse.
