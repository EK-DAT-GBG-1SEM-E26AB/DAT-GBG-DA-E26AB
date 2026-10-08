# Præsentation af færdige Adventure-projekter

## Beskrivelse

Adventure-projektet er afsluttet. Gennem de seneste uger har I udviklet jeres første større objektorienterede program med blandt andet rum, objektreferencer, ArrayList, arv, polymorfi, abstrakte klasser, våben og fjender.

I dag skal grupperne præsentere deres færdige spil i plenum.

Præsentationerne gennemføres i den rækkefølge, der fremgår af klassens præsentationsplan (faneblad: Projekt Adventure fremlæggelse):

- [Præsentationsplan – A-klassen](https://erhvervsakademikbenhavn.sharepoint.com/:x:/r/sites/Team-E26AB/_layouts/15/Doc.aspx?action=edit&sourcedoc=%7B3b88b7b6-20c0-4cac-b2cf-18449771f9ca%7D&wdExp=TEAMS-TREATMENT&web=1)
- [Præsentationsplan – B-klassen](https://erhvervsakademikbenhavn.sharepoint.com/:x:/r/sites/Team-E26AB/_layouts/15/Doc.aspx?action=edit&sourcedoc=%7B6fbbf830-8885-4e0d-ba00-f8e15f06e3b6%7D&wdExp=TEAMS-TREATMENT&web=1)

Der gennemføres ikke kode-review mellem grupperne. I stedet får hver gruppe mulighed for at vise sit spil og gennemgå en selvvalgt sekvens i koden for resten af klassen.

## Læringsmål

Når du har deltaget i dagens aktiviteter, skal du kunne:

- demonstrere et færdigt program og forklare dets vigtigste funktioner
- gennemføre en struktureret walkthrough af en sekvens i programmet
- forklare, hvordan flere klasser og objekter samarbejder om at løse en opgave
- begrunde centrale valg i programmets opbygning
- anvende relevante fagbegreber som objektreferencer, arv, polymorfi, abstrakte klasser og ansvarsfordeling
- besvare spørgsmål om gruppens kode og løsning

## Forberedelse inden undervisningen

Gruppen skal have forberedt præsentationen inden undervisningen.

I skal:

1. Kontrollere, at spillet kan startes og afvikles på den computer, der anvendes til præsentationen.
2. Forberede en kort demonstration af spillet.
3. Udvælge en sekvens i spillet til en kode-walkthrough.
4. Finde de relevante klasser og metoder frem i IntelliJ.
5. Aftale, hvem der præsenterer de enkelte dele.

Gruppen bestemmer selv, om én person gennemfører hele præsentationen, eller om gruppemedlemmerne deler opgaven mellem sig.

Alle gruppemedlemmer skal dog kunne forklare gruppens løsning og besvare spørgsmål til koden.

## Præsentationen

Hver præsentation består af tre dele.

### 1. Demonstration af spillet

Start med at præsentere og demonstrere jeres spil.

Fortæl kort:

- hvad spillet hedder
- hvilken verden eller historie spillet foregår i
- hvad spilleren kan gøre
- om I har lavet særlige funktioner eller udvidelser

Kør derefter en kort, forberedt sekvens i spillet. Det kan eksempelvis være:

- at bevæge sig mellem rummene
- at samle ting op og anvende dem
- at spise mad og ændre health
- at equippe og anvende et våben
- at kæmpe mod og besejre en fjende

Vælg på forhånd de kommandoer, I vil anvende, så demonstrationen bliver kort og sammenhængende.

### 2. Kode-walkthrough

Efter demonstrationen skal I gennemføre en walkthrough af en selvvalgt sekvens i spillet.

En sekvens er et samlet programforløb, hvor flere dele af programmet samarbejder. Det kan eksempelvis være:

- `go` – hvordan spilleren flyttes mellem rummene
- `take` eller `drop` – hvordan items flyttes mellem et rum og inventory
- `eat` – hvordan mad findes, fjernes og påvirker spillerens health
- `equip` – hvordan et våben vælges fra inventory
- `attack` – hvordan spilleren, våbnet, fjenden og brugergrænsefladen samarbejder
- en fjendes død – hvordan fjenden fjernes fra rummet og efterlader sit våben

Walkthroughen skal være forberedt. I skal på forhånd have valgt:

- hvilken kommando eller hændelse der starter sekvensen
- hvilke klasser og metoder der bliver involveret
- i hvilken rækkefølge metoderne kaldes
- hvilke objekter der ændrer tilstand undervejs
- hvordan resultatet kommer tilbage til `UserInterface` og vises for brugeren

Følg sekvensen trin for trin i IntelliJ. Forklar ikke nødvendigvis hver enkelt kodelinje. Fokusér på samarbejdet mellem objekterne og på, hvorfor ansvaret er placeret i de valgte klasser.

Brug de fagbegreber, der passer til jeres løsning, for eksempel:

- objektreference
- association
- arv
- polymorfi
- abstrakt klasse
- enum
- ArrayList
- indkapsling
- ansvarsfordeling

### 3. Spørgsmål

Efter demonstrationen og kode-walkthroughen kan underviseren og resten af klassen stille spørgsmål.

Spørgsmålene kan eksempelvis handle om:

- hvorfor en metode er placeret i en bestemt klasse
- hvordan objekterne kommunikerer
- hvordan arv eller polymorfi anvendes
- hvordan gruppen håndterer de forskellige udfald af en kommando
- hvilke dele af løsningen der var sværest
- hvad gruppen ville ændre eller forbedre med mere tid

## Praktiske råd

- Hav projektet åbent og klar i IntelliJ, inden det er jeres tur.
- Test den planlagte spilsekvens på forhånd.
- Hav de relevante klasser og metoder åbne i faner.
- Gør teksten stor nok til, at hele klassen kan læse den.
- Brug eventuelt IntelliJs **Presentation Mode**.
- Luk uvedkommende programmer og notifikationer.
- Hold øje med tiden, og prioritér de vigtigste dele.
- Hav gerne jeres klasse- eller aktivitetsdiagram klar, hvis det hjælper forklaringen.

## Når de andre grupper præsenterer

Når en anden gruppe præsenterer, skal I lytte aktivt og være klar til at stille spørgsmål.

Læg især mærke til:

- hvordan deres løsning adskiller sig fra jeres
- hvor ansvaret er placeret i deres klasser
- hvordan deres objekter samarbejder
- hvordan de anvender arv og polymorfi
- hvilke problemer de har løst på en anden måde end jer

Der findes ikke kun én korrekt måde at strukturere Adventure-spillet på. Formålet med præsentationerne er også at se flere mulige løsninger på de samme krav.

## Aktiviteter i undervisningen

Grupperne præsenterer efter rækkefølgen i klassens præsentationsplan:

- [A-klassens præsentationsplan](https://erhvervsakademikbenhavn.sharepoint.com/:x:/r/sites/Team-E26AB/_layouts/15/Doc.aspx?action=edit&sourcedoc=%7B3b88b7b6-20c0-4cac-b2cf-18449771f9ca%7D&wdExp=TEAMS-TREATMENT&web=1)
- [B-klassens præsentationsplan](https://erhvervsakademikbenhavn.sharepoint.com/:x:/r/sites/Team-E26AB/_layouts/15/Doc.aspx?action=edit&sourcedoc=%7B6fbbf830-8885-4e0d-ba00-f8e15f06e3b6%7D&wdExp=TEAMS-TREATMENT&web=1)

Hver gruppe:

1. demonstrerer spillet
2. gennemfører sin forberedte kode-walkthrough
3. besvarer spørgsmål

Demonstration af spillet samt¨kode-walkthrough bør ikke tage mere end 10 minutter.  
Herefter følger spørgsmål i ca 5 minutter.

## Det vigtigste at tage med

- Forbered både demonstration og kode-walkthrough inden undervisningen.
- Start med at vise spillet, før I går ind i koden.
- Vælg én sammenhængende sekvens frem for mange løsrevne kodestykker.
- Forklar samarbejdet mellem objekterne og klassernes ansvar.
- Gruppen bestemmer selv, hvem der præsenterer.
- Alle gruppemedlemmer skal kunne forklare løsningen og besvare spørgsmål.
