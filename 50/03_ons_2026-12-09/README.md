# Repetition – programmering som til eksamen

## Beskrivelse

Delfinen er afleveret. Fire projekter, et semester og en hel masse Java ligger bag jer – og snart
er der eksamen.

Semestrets eksamen er en **individuel, mundtlig prøve i programmering**. I et projekt kan man læne
sig op ad gruppen. Til eksamen er det dig, der skal skrive koden og forklare, hvorfor du gjorde,
som du gjorde. Hvordan eksamen præcis foregår, gennemgår vi
[onsdag 16-12](../../51/03_ons_2026-12-16/README.md).

I dag øver vi den del, der er sværest at øve i et projekt: at få **en lille opgave med få
linjers beskrivelse** og løse den **alene**, på kort tid – og tænke højt imens. Opgaverne ligger i
[opgaver.md](opgaver.md) og er bygget over samme skabelon: to–tre klasser, tre delspørgsmål, 20
minutter.

Sidst på dagen gør I klar til i morgen, hvor I præsenterer **ITF-diasshowet** i ITF-lektionen.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* læse en kort opgavebeskrivelse og vælge klasser, attributter og datatyper selv
* skrive en klasse med konstruktør, attributter og metoder, der **returnerer** resultater
* teste din egen kode med en `main`-metode
* bruge semestrets værktøjer, når opgaven kalder på dem – `ArrayList`, `enum`, arv, interfaces,
  `LocalDate`, exceptions, filer og sortering
* forklare din kode og dine valg højt, mens du skriver den

## Se disse videoer før undervisningen:

Ingen ny video. Brug kursusrækken til at genopfriske de emner, du er mest usikker på – fx:

* [arraylists](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=8h55m51s) (til: 09:05:29)
* [interfaces](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=8h1m30s) (til: 08:07:44)
* [exception handling](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=9h5m29s) (til: 09:13:28)
* [write files](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=9h13m28s) (til: 09:21:58) og
  [read files](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=9h21m58s) (til: 09:28:50)
* [enums](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=11h02m38s) (til: 11:12:45)

## Læs nedenstående før undervisningen

---

### Fra projekt til lille opgave

I projekterne har I lært at dele et program op i `UserInterface`, `Controller`, domæneklasser og
`FileHandler`. Det er rigtigt i et program, der skal leve længe. I en opgave på 20 minutter er det
spild af tid.

En typisk opgave i dag ser sådan ud:

> * Lav en klasse `Car` og en klasse `Trailer`, og giv `Car` mulighed for at få en trailer koblet på.
> * Giv begge klasser en vægt, og lav en metode på `Car`, der returnerer totalvægten.
> * Ret i `Car`, så en trailer kun kan kobles på, hvis totalvægten ikke overstiger 3500 kg.

Der står ikke, hvilke datatyper der skal bruges, om attributterne er `private`, eller at der skal
være en `main`. Det skal du selv vide.

### Gode vaner

Øv dem i dag, så de sidder på rygraden:

* **Lav altid en `main`**, der opretter objekter og kalder metoderne – også selvom opgaven ikke
  beder om det. Så kan du vise, at koden virker.
* **Lav konstruktører, getters og `toString`, hvor det giver mening** – også når opgaven ikke
  nævner dem. De gør koden lettere at teste.
* **Brug `private` på attributterne.** Opgaven siger det sjældent, men det er det korrekte.
* **Lav ikke flere klasser end opgaven beder om.** Ingen `UserInterface`, `Controller` eller
  `FileHandler`, medmindre opgaven handler om det.
* **Vælg datatyperne selv – og begrund dem.** Er en vægt `int` eller `double`? Er "kulør" en
  `String` eller en `enum`? Der er ikke altid ét rigtigt svar, men der er altid en begrundelse.
* **Lad metoderne returnere – og udskriv i `main`.** En metode, der returnerer totalvægten, kan
  bruges og testes. En metode, der skriver den ud, kan kun skrive den ud. Undtagelsen er, når
  opgaven siger, at metoden skal udskrive noget.
* **Test-udskrifter undervejs er fine** – bare sig, at det er det, du gør.
* **Når du ikke når at teste**, så forklar, hvad koden gør, og hvordan du ville teste den.

### Tænk højt

Det sværeste er ikke at skrive koden – det er at **skrive og forklare på samme tid**. Det kan
trænes. I dag arbejder I i par:

* **Den ene løser opgaven** og siger hele tiden, hvad hun gør og hvorfor: *"Jeg laver trailer som en
  attribut på `Car`, og den er `null`, når der ikke er nogen trailer."*
* **Den anden er eksaminator**: læser opgaven op, holder tiden og stiller spørgsmål undervejs –
  især *"hvorfor?"*. Hver opgave i [opgaver.md](opgaver.md) har et par *eksaminatorspørgsmål* til
  det.

Byt roller efter hver opgave.

### Hvilke opgaver dækker hvad?

| Emne | Opgaver |
|---|---|
| Strings: `length`, `charAt`, `split`, `substring`, `toLowerCase` | 1, 5, 6 |
| `ArrayList` – tilføj, søg, tæl, find største | 1, 2, 10 |
| Arrays | 11 |
| `Random` | 2, 6 |
| Objekter i objekter, `null` | 4, 11 |
| `enum` og `switch` | 3, 9 |
| Exceptions: `throw`, `try`/`catch` | 3, 8 |
| Interfaces og polymorfi | 7 |
| Arv og abstrakte klasser | 8 |
| Filer: `PrintStream` og `Scanner` | 8 |
| `LocalDate` | 9 |
| Sortering: `Comparable` og `Comparator` | 7, 9 |

Du behøver ikke lave dem i rækkefølge. Start med et emne, du er usikker på.

---

### I morgen: ITF-præsentationen

Torsdag 10-12 præsenterer hver gruppe sit diasshow for ITF-underviseren i ITF-lektionen. Hvor
lang tid I har, og hvordan det foregår, er ITF-underviserens beslutning – følg ITF's anvisninger i
itslearning.

Brug den sidste halve time i dag på at gøre klar:

* **Øv præsentationen én gang højt** i gruppen – med ur på.
* **Aftal, hvem der siger hvad.** Alle fire skal kunne svare på spørgsmål om hele diasshowet.
* **Åbn filen** fra itslearning-afleveringen og tjek, at det er den rigtige version.
* **Skriv de spørgsmål ned**, I selv ville stille, hvis I var ITF-underviseren – og svar på dem.

---

## Det vigtigste at tage med

* eksamen er **individuel** og handler om **programmering** – øv dig i at løse opgaver alene
* lav altid en **`main`**, der viser, at koden virker
* `private` attributter, konstruktører og `toString`, også når opgaven ikke nævner dem
* metoder **returnerer**; `main` udskriver
* vælg datatyper selv – og **begrund** dem
* **tænk højt** – det kan og skal trænes

## Aktiviteter i undervisningen

### 1. Opvarmning

Lav [opgave 1 og 4](opgaver.md) alene, uden tidspres. Sammenlign bagefter med sidemanden: Valgte I
de samme datatyper? Hvorfor, hvorfor ikke?

### 2. Som til eksamen

Arbejd i par efter [Tænk højt](#tænk-højt). 20 minutter pr. opgave, og byt roller efter hver
opgave. Tjek bagefter med de [vejledende løsninger](loesninger.md) – men først **efter** I selv har
prøvet.

### 3. Forbered ITF-præsentationen (sidste halve time)

Gå listen under [I morgen: ITF-præsentationen](#i-morgen-itf-præsentationen) igennem i gruppen.
