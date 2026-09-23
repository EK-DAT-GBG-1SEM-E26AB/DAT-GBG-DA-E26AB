# Kode review – Delfinen, sprint 1

> Skema til dagens kode review. Hvordan det foregår, står på [dagens side](README.md). Del af
> [Delfinen-projektet](../../projekter/delfinen/readme.md).

Skemaet er bygget som Filmsamlingens [kode-review.md](../../projekter/filmsamling/kode-review.md),
men tilpasset Delfinen og et program, der **ikke er færdigt endnu**.

> **Der er ikke fokus på brugerfladen.** Om menuen er pæn, er ikke det, vi kigger efter. Vi kigger
> på, hvor let koden er at læse, forstå, teste og bygge videre på – **S**'et i FURPS.

## Sådan bruger I skemaet

1. Åbn denne fil på GitHub, og klik **Raw** – så får I Markdown-teksten.
2. Kopiér den ind i en fil hos jer selv, og udfyld den under reviewet.
3. Opret den udfyldte tekst som et **issue** i den reviewede gruppes repository (se
   [dagens side](README.md#4-issuet--og-jeres-eget-board)).

Sådan læses spørgsmålene:

* Spørgsmål markeret med 💣 er **dårlige** – de skal helst kunne besvares med **nej**.
* Sæt kryds (`[x]`), hvis der kan svares **ja**.
* Skriv eksempler og uddybninger på linjen lige under spørgsmålet – gerne med klasse, metode og
  linjenummer.
* Er noget ikke lavet endnu (fx træner-delen), så skriv *"ikke lavet endnu"* under overskriften og
  spring afsnittet over. Er et spørgsmål ikke relevant, så **slet** det.

---

| | |
|---|---|
| **Gruppen, der reviewes** | *gruppens navn* |
| **GitHub repository** | *url* |
| **Commit hash** (7 tegn) | |
| **Reviewer-gruppen** | *gruppens navn* |
| **Dato** | 30-11-2026 |
| **Gruppen bad os se særligt på** | |

---

## 1. GitHub og Git

* [ ] Er der en `.gitignore`-fil?
* [ ] 💣 Ligger der uvedkommende filer på GitHub (`out/`, `target/`, `.class`-filer, IntelliJ's
      personlige filer)?
* [ ] Er der en `README.md` med gruppens medlemmer (fornavn og GitHub-brugernavn) og en forklaring
      på, hvordan man starter programmet?
* [ ] Er der en `docs`-mappe med domænemodel og `user-stories.md`?
* [ ] Viser historikken commits fra **alle fire** i gruppen? *(Insights → Contributors)*
* [ ] Er commit-beskederne til at forstå – kan man se, hvad hver commit gjorde?
* [ ] Er der brugt **branches** til user stories, og er de merget til `main`?
* [ ] 💣 Er der branches med arbejde, der har ligget i mere end en uge uden at blive merget?

---

## 2. Kør programmet og testene

* [ ] Kan projektet åbnes og kompileres uden fejl fra et frisk klon?
* [ ] Starter programmet med **eksempeldata**, så man kan prøve det med det samme?
* [ ] Starter programmet med en **tom klub**, hvis datafilen mangler? *(omdøb filen og prøv)*
* [ ] Er **alle** tests grønne? *Antal tests:*
* [ ] 💣 Går programmet ned, hvis man skriver bogstaver, hvor der skal stå et tal?
* [ ] 💣 Går programmet ned ved en ugyldig dato, fx `31-13-2010`?
* [ ] 💣 Går programmet ned ved et medlemsnummer, der ikke findes?
* [ ] Virker de user stories, gruppen selv siger er færdige?

*Hvilke user stories prøvede vi, og hvad skete der?*

---

## 3. Packages

Der bør være mindst tre packages: én til brugerfladen, én til domænet og én til filhåndteringen
(fx `ui`, `domain` og `data` – navnene er op til gruppen).

* [ ] Er der packages med sigende navne?
* [ ] Er det let at regne ud, hvilken rolle hver package har?
* [ ] Ligger hver klasse i den package, der passer til dens ansvar?
* [ ] 💣 Importerer en klasse i domæne- eller data-packagen noget fra brugerflade-packagen?
* [ ] 💣 Er der `System.out` eller `new Scanner(System.in)` uden for brugerflade-klasserne?
      *(søg i hele projektet)*
* [ ] Ligger testene i packages med samme navne som de klasser, de tester?

---

## 4. Domæneklasserne

Gentag afsnittet for de vigtigste domæneklasser – fx den, der beskriver et medlem, den, der
beskriver en konkurrencesvømmer, og den, der holder på alle medlemmerne.

### Klassen: `-navn-`

* [ ] Kan man genkende et begreb fra **domænemodellen** i klassens navn?
* [ ] Er det let at regne ud, hvilken rolle klassen har?
* [ ] Er der brugt tydelig ental/flertal (`Member` / `members`)?
* [ ] Er alle attributter `private`?
* [ ] 💣 Er der attributter eller getters/setters, der aldrig bruges?
* [ ] 💣 Er der attributter, der burde være lokale variable?

**Delfinen-specifikt**

* [ ] Gemmes **fødselsdatoen** – og regnes alder og junior/senior ud fra den?
* [ ] 💣 Gemmes alderen eller junior/senior som en attribut, der bliver forkert, når medlemmet har
      fødselsdag?
* [ ] Tildeler **systemet** medlemsnummeret – og kan to medlemmer aldrig få det samme?
* [ ] Er der brugt **enum** til det, der kun kan have faste værdier (fx discipliner)?
* [ ] Er konkurrencesvømmeren løst med arv eller på en anden måde – og kan gruppen forklare
      hvorfor?
* [ ] Ligger beregninger hos den klasse, der har data til dem (**Information Expert**)?
* [ ] 💣 Er der kæder som `club.getMembers().get(0).getResults().get(2)` (**Law of Demeter**)?

---

## 5. Kontingent og tests

**Beregningen**

* [ ] Ligger kontingentberegningen i en domæneklasse – ikke i brugerfladen eller filhåndteringen?
* [ ] Tager beregningen **datoen som parameter**, så den kan testes på en bestemt dag?
* [ ] Er beløbene samlet ét sted (fx konstanter), så de ikke står flere steder i koden?

**Testene**

* [ ] Er alle **fire takster** testet: junior (1000), senior (1600), senior 60+ (1200) og passiv
      (500)?
* [ ] Er grænsen ved **18 år** testet på selve fødselsdagen – og dagen før?
* [ ] Er grænsen ved **60 år** testet på selve fødselsdagen – og dagen før?
* [ ] Er det testet, at et **passivt** medlem over 60 betaler 500 – ikke 375?
* [ ] Er testmetodernes navne sigende – kan man se, hvad der testes, uden at læse koden?
* [ ] Følger testene **Arrange – Act – Assert**?
* [ ] Er andet end kontingentet testet (fx restance, forventet indtægt, top 5)?
* [ ] 💣 Afhænger testene af dagens dato (`LocalDate.now()`), så de kan blive røde en anden dag?
* [ ] 💣 Kan testene komme til at overskrive den rigtige datafil?
* [ ] 💣 Er der tests uden `assert...`?

---

## 6. Filhåndtering

* [ ] Er al læsning og skrivning af filer samlet i én klasse (eller én package)?
* [ ] 💣 Ved andre klasser noget om filformatet (fx semikolon eller feltrækkefølge)?
* [ ] Bliver filerne lukket efter læsning og skrivning?
* [ ] Overlever et medlem en tur gennem filen – med æ, ø, å, fødselsdato, betaling og
      discipliner?
* [ ] Går programmet videre, hvis en linje i filen er ødelagt?
* [ ] Er det tydeligt, **hvornår** der gemmes – og kan gruppen forklare hvorfor?
* [ ] 💣 Går data tabt, hvis programmet lukkes uden at vælge "afslut"?

---

## 7. Exceptions og robusthed

* [ ] Bliver exceptions fanget dér, hvor man ved, hvad brugeren skal have at vide (typisk
      brugerfladen)?
* [ ] Bliver ugyldige værdier afvist med `throw` og en forklarende besked?
* [ ] Bliver brugeren spurgt igen efter forkert input, i stedet for at blive sendt tilbage til
      hovedmenuen?
* [ ] 💣 Er der tomme `catch`-blokke eller `catch`, der kun kalder `printStackTrace()`?
* [ ] 💣 Bliver `NullPointerException` eller `IndexOutOfBoundsException` fanget, hvor en `if`
      burde have forhindret dem?

---

## 8. Navngivning og kodestil

* [ ] Er alle klasser, metoder og variabler navngivet på **engelsk**?
* [ ] Følger navnene `camelCase` (metoder og variabler) og `PascalCase` (klasser)?
* [ ] Kan man se ud fra en `public` metodes navn, hvad den gør, og om den returnerer noget?
* [ ] Er der en fælles stil, fx hedder alle visninger `showXxx`?
* [ ] 💣 Er der metoder, der gør to ting – fx både finder noget og udskriver det?
* [ ] 💣 Er der lange metoder, der ville blive lettere at forstå, hvis de blev delt op?
* [ ] 💣 Er der kode, der er kopieret flere steder, og som burde være én metode?
* [ ] 💣 Er der udkommenteret kode eller kommentarer, der ikke længere passer?
* [ ] Er koden pænt formateret (<kbd>Ctrl</kbd>+<kbd>Alt</kbd>+<kbd>L</kbd>)?

---

## 9. Træner-delen (hvis den er lavet)

* [ ] Kan en tid registreres – og afvises den, hvis svømmeren ikke er aktiv i disciplinen?
* [ ] Gemmes tider på en måde, der kan sorteres korrekt (fx som et helt antal hundrededele)?
* [ ] Er top 5 lavet med en `Comparator` (eller `Comparable`)?
* [ ] Står hver svømmer højst én gang pr. liste?
* [ ] 💣 Ændrer sorteringen rækkefølgen i selve medlemslisten?

---

## Yderligere kommentarer fra reviewerne

*Ros til særligt god kode, spørgsmål til hvordan gruppen fandt på en løsning, og ting, der ikke
passede ind ovenfor.*

**Det er særlig godt:**

**Det lærte vi af jeres kode:**

**De tre vigtigste ting at rette i sprint 2:**

1.
2.
3.

**Spørgsmål, vi ikke fik svar på:**
