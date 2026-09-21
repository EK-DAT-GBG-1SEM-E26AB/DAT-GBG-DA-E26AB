# Opgaver – Design: User stories, Controller, Ansvar og afhængigheder, Coupling og Cohesion

## Opvarmningsøvelse – skriv en user story

Skriv en user story på en post-it, før I går videre til de øvrige opgaver. Området som user story'en skal omhandle er noget her på EK.

Tænk på jer selv som brugeren. Det handler om at beskrive, hvad I gerne vil kunne gøre, og hvorfor det er vigtigt for jer. Fokusér ikke på tekniske løsninger – fokusér på behovet og værdien.

Brug formatet:

- Som [bruger] vil jeg [handling], så [grund/behov/værdi].

Eksempel:

- Som underviser vil jeg kunne se, hvem der deltager aktivt i undervisningen, så jeg kan reducere behovet for reeksamen.

Del derefter jeres post-it med en makker, og brug den som udgangspunkt for resten af opgaverne.

Når I har delt med en makker, tager vi jeres user stories i plenum.

## Opgave 1: Skriv user stories

Skriv 3–5 user stories til projektet bogsamling, idet vi forestiller os, at vi endnu ikke er gået i gang med at kode. Brug formatet:

- Som [bruger] vil jeg [handling], så [grund/behov/værdi].

Eksempel:

- Som bruger vil jeg kunne finde en bog ud fra titel, så jeg hurtigt kan finde den rigtige bog.

### Diskussion: Del jeres user stories med en makker

Del jeres user stories med en makker og diskuter dem i denne rækkefølge:

- Er det tydeligt, hvorfor user storyen giver værdi for brugeren?
- Følger den formatet, så den tydeligt indeholder bruger, handling og værdi?
- Er det klart, hvad der ønskes løst?

Tal sammen om, hvad der virker tydeligt, og hvad der kan gøres mere præcist.

### Kvalitetskontrol med INVEST

Gå herefter gennem hver user story med INVEST-kriterierne:

- Independent: Kan user storyen løses selvstændigt?
- Negotiable: Er den ikke for teknisk eller for låst fast?
- Valuable: Giver den tydelig værdi for brugeren?
- Estimable: Kan den vurderes i omfang?
- Small: Er den lille nok til at forstå og løse?
- Testable: Kan den testes?

Vurder, hvilke user stories der er stærke, og hvilke der bør rettes eller deles op i mindre historier.

## Opgave 2: Skriv acceptkriterier

Vælg én af dine user stories og prøv at skrive mindst 3 acceptkriterier.

Brug formatet:

- Givet [forudsætning], når [handling], så [resultat]

### Diskussion: Gennemgå jeres acceptkriterier

Del jeres acceptkriterier med en makker og diskuter, om de er dækkende for user storyen.

Spørg ind til:

- Dækker acceptkriterierne den værdi, user storyen skal skabe?
- Er der nogen væsentlige ting, som ikke er beskrevet, så der er huller i løsningen?
- Mangler der edge cases, fejlmeddelelser eller tydelige resultater?

Diskuter, hvor acceptkriterierne er klare og hvor de bør udvides eller præciseres.

### Kvalitetskontrol med SMART

Gå derefter gennem hvert acceptkriterium med SMART-kriterierne:

- Specific: Er kriteriet præcist og tydeligt?
- Measurable: Kan vi måle eller teste det?
- Achievable: Er det realistisk at få lavet?
- Relevant: Dækker det den rigtige værdi fra user storyen?
- Time-bound: Er det tydeligt, hvornår det er færdigt?

Vurder, hvilke acceptkriterier der er gode nok, og hvilke der skal gøres mere konkrete eller mere komplette.

## Opgave 3: Analyser ansvar

Beskriv, hvilket ansvar hver af følgende klasser bør have:

- `Book`
- `Library`
- `LibraryController`
- `Main`

Skriv 1–2 sætninger om hver klasse.

## Opgave 4: Find dårlig design

Tag et eksempel på kode, hvor meget står i `Main`, og forklar, hvorfor det er dårligt design.

Besvar disse spørgsmål:

- Hvad gør `Main` for meget?
- Hvilke dele kunne flyttes til andre klasser?
- Hvorfor giver det bedre design?

## Opgave 5: Coupling og cohesion

Forklar forskellen på:

- høj og lav coupling
- høj og lav cohesion

Giv mindst ét eksempel fra bogsamlingen.

## Opgave 6: Refaktoring

Beskriv en bedre struktur for bogsamlingen. Hvilke klasser skal der være, og hvilke ansvar har de?

Skriv en kort plan for en refaktoreret løsning.
