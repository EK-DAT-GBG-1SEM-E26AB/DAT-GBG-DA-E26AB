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

## Opgave 7: Genbrug bogsamlingen til en udlånsmaskine for et offentligt bibliotek

Tag koden fra bogsamlingen og forestil jer, at den skal genbruges til et offentligt bibliotek, hvor borgere kan låne bøger ved hjælp af en udlånsmaskine.

Bemærk: Det er ikke nødvendigt med mange user stories. Det er helt fint at starte med kun 2–3 user stories, så vi kan komme i gang med at forstå, hvad sådan en maskine skal kunne. Hvis I er i tvivl om, hvad en udlånsmaskine skal kunne, så kan I tænke på en stående maskine i biblioteket, hvor man kan låne og returnere bøger.

### Opgave 7.1: Identificér genbrugelige dele

Gennemgå jeres eksisterende design med bogsamlingen og svar på disse spørgsmål:

- Hvilke klasser kan genbruges direkte?
- Hvilke dele skal ændres, fordi et offentligt bibliotek har andre krav end en bogsamling?
- Hvilke begreber er nye i biblioteksløsningen, fx låner, udlån, aflevering, reservation, lånetid?

Skriv kort, hvilke dele af bogsamlingen der kan bruges uden ændring, og hvilke dele der skal udvides eller refaktoreres.

### Opgave 7.2: Skriv få, men relevante user stories

Skriv 2–3 user stories til et offentligt bibliotekssystem. Brug samme format som tidligere:

- Som [bruger] vil jeg [handling], så [grund/behov/værdi].

Eksempler på fokusområder:
- låne en bog
- returnere en bog
- se, om en bog er tilgængelig
- få en meddelelse, hvis bogen allerede er udlånt

Eksempel på user story:

- Som bruger vil jeg kunne låne en bog ved hjælp af udlånsmaskinen, så jeg hurtigt kan få den med hjem.

### Opgave 7.3: Definér ansvar i det nye design

Beskriv, hvilke ansvar følgende klasser bør have i et offentligt bibliotekssystem:

- `Book`
- `LibraryMember`
- `Library`
- `LendingSystem`
- `LibraryController`
- `Main`

Skriv 1–2 sætninger om hver klasse, og forklar, hvor den nye kode skal ligge i forhold til bogsamlingen.

### Opgave 7.4: Analyser coupling og cohesion

Diskuter designet af lånesystemet ud fra disse spørgsmål:

- Hvad er høj/lav coupling i en udlånsmaskine?
- Hvad er høj/lav cohesion i en udlånsmaskine?
- Hvorfor er det vigtigt, at ansvar er tydeligt fordelt mellem klasserne?
- Giv mindst ét konkret eksempel fra biblioteksløsningen.

### Opgave 7.5: Refaktorer til bedre struktur

Lav en kort plan for en bedre struktur til udlånsmaskinen. Besvar disse spørgsmål:

- Hvilke klasser skal være med?
- Hvilke ansvar skal hver klasse have?
- Hvad bør `Main` kun gøre?
- Hvilke ansvar bør flyttes fra `Main` til andre klasser?
- Hvordan kan designet gøres mere overskueligt og lettere at udvide?

## Opgave 8: Acceptkriterier for udlånsflow

Vælg én user story fra bibliotekssystemet og skriv mindst 3 acceptkriterier.

Brug formatet:

- Givet [forudsætning], når [handling], så [resultat]

Eksempel:

- Givet at en bog er tilgængelig, når en bruger låner bogen, så markeres den som udlånt og registreres på brugerens lån.

Efterfølgende skal I diskutere:

- Er acceptkriterierne dækkende for user storyen?
- Mangler der edge cases, fx hvis bogen allerede er udlånt?
- Er der fejlmeddelelser eller tydelige resultater, der bør beskrives?
