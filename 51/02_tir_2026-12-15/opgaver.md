# Prøveopgaver

Seks opgaver i samme form: **tre delspørgsmål**, **15 minutter**, et **tomt projekt**. Hvordan I
øver dem to og to, står under [Øv en hel prøve i par](README.md#øv-en-hel-prøve-i-par).

Til hver opgave er der **eksaminatorspørgsmål**, som eksaminatoren stiller, når de 15 minutter er
gået. Den studerende skal **ikke** læse dem på forhånd.

Husk: der står ikke, hvilke datatyper du skal bruge, om attributterne er `private`, eller at du skal
lave en `main`. Det skal du selv vide. Der er [vejledende løsninger](loesninger.md).

---

## Opgave 1 – Termometer

1. Lav en klasse `Thermometer`, der kan gemme en række temperaturmålinger, og en metode, der
   tilføjer en måling.
2. Tilføj en metode, der returnerer **gennemsnittet** af målingerne, og en, der returnerer den
   **højeste** måling.
3. Tilføj en metode, der returnerer, hvor mange målinger der var **under frysepunktet**.

**Eksaminatorspørgsmål**

* Hvilken datatype har en måling? Hvorfor?
* Hvad sker der i dine metoder, hvis der ingen målinger er?
* Hvorfor starter du din "højeste" med den første måling og ikke med 0?

---

## Opgave 2 – Parkeringsplads

1. Lav en klasse `Car` med en nummerplade og en klasse `ParkingLot` med et **fast** antal pladser,
   der bestemmes, når parkeringspladsen oprettes.
2. Tilføj en metode `park`, der sætter en bil på den første ledige plads og returnerer pladsens
   nummer – eller fortæller, at der ikke er plads.
3. Tilføj en metode `leave`, der fjerner bilen med en bestemt nummerplade, og en metode, der
   returnerer antallet af ledige pladser.

**Eksaminatorspørgsmål**

* Valgte du et array eller en `ArrayList`? Hvad sker der med de andre biler, når én kører?
* Hvordan sammenligner du nummerplader – og hvorfor ikke med `==`?
* Hvad sker der, hvis `leave` får en nummerplade, der ikke holder der?

---

## Opgave 3 – Biblioteket

1. Lav en klasse `Loan` med en bogtitel og den dato, bogen blev lånt.
2. Tilføj en metode, der returnerer, om lånet er **for sent** afleveret på en given dato. Lånetiden
   er 30 dage.
3. Lav en klasse `Library` med en liste af lån og en metode, der returnerer de lån, der er for
   sent – **ældste først**.

**Eksaminatorspørgsmål**

* Hvorfor tager metoden datoen som parameter i stedet for at bruge `LocalDate.now()`?
* Er en bog, der skal afleveres i dag, for sent? Hvordan ser det ud i din kode?
* Hvordan sorterede du – og hvorfor sådan?

---

## Opgave 4 – Løn

1. Lav en **abstrakt** klasse `Employee` med et navn og en abstrakt metode, der beregner
   månedslønnen.
2. Lav to subklasser: `SalariedEmployee` med en fast månedsløn og `HourlyEmployee` med en timeløn
   og et antal timer. Lav en klasse `Payroll`, der kan holde begge slags og returnere den samlede
   løn.
3. Timer ud over 160 om måneden er **overarbejde** og betales med 1,5 gange timelønnen. Ret
   beregningen.

**Eksaminatorspørgsmål**

* Hvorfor er `Employee` abstrakt? Hvad ville der ske, hvis den ikke var?
* Hvordan ved `Payroll`, hvordan lønnen skal beregnes for hver medarbejder?
* Hvor står tallene 160 og 1,5 i din kode – og hvorfor dér?

---

## Opgave 5 – Adgangskode

1. Lav en klasse `Password` med en metode, der returnerer, om en tekst er en **gyldig**
   adgangskode: mindst 8 tegn og mindst ét ciffer.
2. Udvid reglerne: der skal også være mindst ét **stort** bogstav.
3. Lad konstruktøren **afvise** en ugyldig adgangskode med en exception, der forklarer, hvad der er
   galt. Vis i `main`, at den kan fanges.

**Eksaminatorspørgsmål**

* Hvordan løber du gennem tegnene i en `String`? Hvordan tjekker du, om et tegn er et ciffer?
* Hvilken exception valgte du, og hvorfor?
* Hvorfor er det bedre at kaste en exception i konstruktøren end at udskrive en fejlbesked?

---

## Opgave 6 – Playliste

1. Lav en klasse `Song` med titel, kunstner og varighed i sekunder, og en klasse `Playlist`, der
   holder en liste af sange og kan returnere den samlede varighed.
2. Tilføj en metode, der returnerer den samlede varighed som tekst på formen `m:ss`, fx `12:34` –
   og en metode, der returnerer alle sange af en bestemt kunstner.
3. Gør det muligt at få sangene sorteret **efter titel** og **efter varighed**.

**Eksaminatorspørgsmål**

* Hvordan fik du `12:04` til ikke at blive `12:4`?
* Hvilken sortering er den *naturlige* – og hvordan lavede du den anden?
* Ændrer sorteringen rækkefølgen i selve playlisten? Burde den?
