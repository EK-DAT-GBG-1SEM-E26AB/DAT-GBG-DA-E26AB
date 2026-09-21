# Opgaver – Design: User stories, Controller, Ansvar og afhængigheder, Coupling og Cohesion

## Opvarmningsøvelse – skriv en user story

Skriv en user story på en post-it, før I går videre til de øvrige opgaver.

Tænk på jer selv som brugeren. Det handler om at beskrive, hvad I gerne vil kunne gøre, og hvorfor det er vigtigt for jer. Fokusér ikke på tekniske løsninger – fokusér på behovet og værdien.

Brug formatet:

- Som [bruger] vil jeg [handling], så [grund/behov/værdi].

Eksempel:

- Som bruger vil jeg kunne finde en bog hurtigt, så jeg kan spare tid og undgå frustration.

Del derefter jeres post-it med en makker, og brug den som udgangspunkt for resten af opgaverne.

Når I har delt med en makker, så tager vi jeres user stories i plenum.

## Opgave 1: Skriv user stories

Skriv 3–5 user stories til projektet bogsamling. Brug formatet:

- Som [bruger] vil jeg [handling], så [grund/behov/værdi].

Eksempel:

- Som bruger vil jeg kunne finde en bog ud fra titel, så jeg hurtigt kan finde den rigtige bog.

## Opgave 2: Skriv acceptkriterier

Vælg én af dine user stories og skriv mindst 3 acceptkriterier.

Brug formatet:

- Givet [forudsætning], når [handling], så [resultat]

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
