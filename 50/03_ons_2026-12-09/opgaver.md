# Opgaver – programmering som til eksamen

Hver opgave har **tre delspørgsmål**, der bygger oven på hinanden. Løs dem i rækkefølge, og brug
omkring **20 minutter** pr. opgave. Nogle har en **udfordring** til sidst, hvis du når det.

Sådan arbejder du (se [Gode vaner](README.md#gode-vaner)):

* Opret et nyt projekt, fx `repetition`, og én package pr. opgave (`opgave01`, `opgave02`, …), så
  klasserne ikke støder sammen.
* Lav **altid** en `Main` med en `main`-metode, der afprøver det, du har lavet.
* Vælg selv datatyper, og vær klar til at begrunde dem.
* Arbejd i par: den ene løser og tænker højt, den anden er eksaminator, holder tiden og stiller
  *eksaminatorspørgsmålene*. Byt roller efter hver opgave.

Der er [vejledende løsninger](loesninger.md) – men prøv selv først. Din løsning må gerne se
anderledes ud.

---

## Opgave 1 – Tekstlinjer

1. Lav en klasse `TextLines` med en attribut, der er en liste af tekststrenge.
2. Tilføj en metode `add`, der tilføjer en tekststreng til listen.
3. Tilføj en metode `countUnique`, der returnerer antallet af **forskellige** tekstlinjer i listen.
   Tilføjer man `"hej"`, `"hej"`, `"med"` og `"dig"`, er svaret 3.

**Udfordring:** Lav `getLongestLine`, der returnerer den længste linje – og `countUniqueIgnoreCase`,
hvor `"hej"` og `"Hej"` tæller som den samme.

**Eksaminatorspørgsmål**

* Hvorfor en `ArrayList` og ikke et array?
* Hvad returnerer `getLongestLine`, hvis listen er tom? Hvad burde den returnere?

---

## Opgave 2 – Raflebæger

1. Lav en klasse `DiceCup` med en attribut for antallet af terninger og en konstruktør, der
   bestemmer antallet.
2. Tilføj en metode `shake`, der ryster bægeret og returnerer det samlede antal øjne.
3. Tilføj en metode `getValues`, der returnerer terningernes øjne fra **sidste** rystelse – uden at
   ryste igen.

**Udfordring:** Lav `countOf(int value)`, der returnerer, hvor mange terninger der viser `value`.

**Eksaminatorspørgsmål**

* Hvordan får du et tilfældigt tal mellem 1 og 6 med `Random`?
* Hvor gemmer du øjnene, så `getValues` kan returnere dem bagefter?
* Kan den, der kalder `getValues`, komme til at ændre i bægerets liste? Hvordan undgår du det?

---

## Opgave 3 – Spillekort

1. Lav en klasse `Card` med to attributter: kulør og værdi. Kulør er hjerter, ruder, klør eller
   spar – værdien er 1–13.
2. Tilføj en metode `beatsByValue`, der modtager et andet kort og returnerer, om **dette** kort har
   en højere værdi. Kuløren er ligegyldig.
3. Tilføj en metode `beats`, hvor kuløren afgør det, når værdierne er ens: spar slår hjerter, som
   slår ruder, som slår klør.

**Udfordring:** Lad konstruktøren afvise en værdi uden for 1–13 med en exception – og fang den i
`main`.

**Eksaminatorspørgsmål**

* Hvilken datatype valgte du til kuløren? Hvad havde alternativet været?
* Hvordan kan en `enum` hjælpe dig med rækkefølgen af kulørerne?

---

## Opgave 4 – Bil og trailer

1. Lav to klasser, `Car` og `Trailer`, og en attribut på `Car`, så den **eventuelt** kan have en
   trailer koblet på.
2. Giv begge klasser en vægt, og lav en metode på `Car`, der returnerer totalvægten – bilen selv
   plus en eventuel trailer.
3. Ret i `Car`, så en trailer kun kan kobles på, hvis totalvægten ikke overstiger 3500 kg. Metoden
   skal fortælle, om det lykkedes.

**Eksaminatorspørgsmål**

* Hvad er værdien af trailer-attributten, når der ingen trailer er?
* Hvad sker der i totalvægt-metoden, hvis du glemmer at tjekke for det?
* Er grænsen "højst 3500" eller "under 3500"? Hvordan ser det ud i din `if`?

---

## Opgave 5 – Navne

1. Lav en klasse `Name` med tre attributter: fornavn, mellemnavn og efternavn.
2. Tilføj en konstruktør, der modtager det **fulde** navn som én tekst og selv deler det op – men
   tager højde for, at der måske **ikke** er et mellemnavn.
3. Tilføj en `toString`, der returnerer det fulde navn – uden et ekstra mellemrum, når der ikke er
   et mellemnavn.

**Udfordring:** Lav `getInitials`, der returnerer forbogstaverne, fx `"IMH"` for Ida Marie Hansen.
Hvad med én, der har to mellemnavne?

**Eksaminatorspørgsmål**

* Hvad giver `"Ida Hansen".split(" ")`? Og `"Ida Marie Hansen".split(" ")`?
* Hvordan finder du efternavnet, uanset hvor mange dele navnet har?

---

## Opgave 6 – Brugernavne

1. Lav en klasse `User` med to attributter: fulde navn og bruger-id.
2. Tilføj en metode `isValidUserId`, der returnerer `true`, hvis bruger-id'et har det rigtige format:
   **fire små bogstaver efterfulgt af fire cifre**, fx `idha4821`.
3. Tilføj en metode `createUserId`, der laver bruger-id'et ud fra navnet: de to første bogstaver
   fra fornavnet, de to første fra efternavnet og fire **tilfældige** cifre.

**Eksaminatorspørgsmål**

* Hvordan tjekker du, om et tegn er et ciffer? Et lille bogstav?
* Hvordan sikrer du, at det tilfældige tal altid bliver til **fire** cifre – også når det er 42?

---

## Opgave 7 – Figurer

1. Lav et interface `Shape` med metoden `getArea`.
2. Lav to klasser, `Square` (med en sidelængde) og `Circle` (med en radius), der begge implementerer
   `Shape`. Areal: kvadrat `s * s`, cirkel `Math.PI * r * r`.
3. Lav en liste med blandede cirkler og kvadrater, og en løkke, der udskriver arealet af hver af dem
   og det samlede areal.

**Udfordring:** Sortér listen efter areal med en `Comparator`, og udskriv den mindste og den største
figur.

**Eksaminatorspørgsmål**

* Hvilken type har listen? Hvorfor ikke `ArrayList<Circle>`?
* Hvordan ved løkken, om den skal regne areal for en cirkel eller et kvadrat?
* Hvad er forskellen på et interface og en abstrakt klasse?

---

## Opgave 8 – Medier i en fil

1. Lav en **abstrakt** klasse `Media` med navn og varighed (i sekunder).
2. Lav to klasser, `Audio` og `Video`, der arver fra `Media`. `Audio` har en lydstyrke, fx `-10.4`
   dB. `Video` har et billedformat, fx `"16:9"`.
3. Skriv en metode, der tager en liste af blandede `Audio`- og `Video`-objekter og skriver én linje
   pr. medie til filen `mediainfo.txt` – med lydstyrke eller billedformat, alt efter hvad det er.

**Udfordring:** Læs filen igen med en `Scanner`, og beregn den samlede varighed.

**Eksaminatorspørgsmål**

* Hvordan får du lydstyrke og billedformat med i linjen **uden** at bruge `instanceof`?
* Hvad skal der ske, hvis filen ikke kan skrives? Hvor fanger du exceptionen?
* Hvorfor må man ikke skrive `new Media(...)`?

---

## Opgave 9 – Drømmedagbog

1. Lav en klasse `Dream` med en dato, en varighed i minutter og en type: problemløsende, neutral
   eller mareridt.
2. Tilføj en metode `isPleasant`, der fortæller, om drømmen var behagelig:
   * et mareridt er aldrig behageligt
   * en problemløsende drøm kun, hvis den er **kortere** end 10 minutter
   * en neutral drøm kun, hvis den er **længere** end 10 minutter
3. Lav en liste af drømme, og sortér den efter dato – ældste først.

**Eksaminatorspørgsmål**

* Hvilken datatype har datoen? Og typen?
* Brugte du `Comparable` eller en `Comparator`? Hvorfor?
* Hvad er en neutral drøm på præcis 10 minutter?

---

## Opgave 10 – Valg

1. Lav en klasse `Candidate` med navn, parti og antal stemmer.
2. Lav en klasse `Election`, der holder en liste af kandidater, og en metode `getTotalVotes`, der
   returnerer det samlede antal stemmer.
3. Tilføj en metode `getCandidatesFromParty`, der returnerer en liste af alle kandidater fra et
   bestemt parti.

**Udfordring:** Lav `getWinner`, der returnerer kandidaten med flest stemmer.

**Eksaminatorspørgsmål**

* Returnerer `getCandidatesFromParty` en ny liste eller den samme? Hvorfor betyder det noget?
* Hvordan sammenligner du partiets navn – med `==` eller med `equals`? Hvorfor?

---

## Opgave 11 – Bundkort

1. Lav en klasse `MotherBoard` og en klasse `SataDrive`. Et bundkort har **fire** porte, som hver
   kan have et drev tilsluttet.
2. Tilføj en metode på `MotherBoard`, der returnerer en oversigt over alle fire porte – hvilket drev
   der sidder i hver, eller at den er ledig.
3. Tilføj en metode, der tilslutter et drev: den skal **selv** finde en ledig port – eller fortælle,
   at bundkortet er fyldt.

**Udfordring:** Lav `disconnect(int port)`, og vis i `main`, at et nyt drev kommer i den port, der
blev ledig.

**Eksaminatorspørgsmål**

* Valgte du et array eller en `ArrayList`? Hvorfor passer det ene bedre her?
* Hvordan fortæller metoden, at bundkortet er fyldt – returværdi, udskrift eller exception? Hvad er
  fordele og ulemper?
