# Lav dit eget tekstspil

**Frivilligt.** Det her er ikke en opgave og tæller ikke med i noget. Det er til
dig, der har lyst til at lave et spil, som de andre på holdet kan spille — over
netværket, fra hver sin computer.

Du skriver **to klasser**. Resten klarer et bibliotek.

```java
public void play(Room room) {
    int hemmeligt = 1 + (int) (Math.random() * 100);
    room.tellAll("Jeg tænker på et tal mellem 1 og 100.");

    while (true) {
        for (Player p : room.players()) {
            int gæt = p.askInt("Dit gæt?", 1, 100);

            if (gæt == hemmeligt) {
                room.tellAll(p.name() + " ramte det! Det var " + hemmeligt + ".");
                return;
            }
            room.tellAll(p.name() + " gættede " + gæt + " — for "
                         + (gæt < hemmeligt ? "lavt" : "højt") + ".");
        }
    }
}
```

Det er et helt spil, som flere kan spille sammen over nettet. `askInt` venter på
svar præcis som en `Scanner` — tastaturet står bare på en anden computer. Der er
ingen netværkskode, ingen tråde og ingen fejlhåndtering, fordi biblioteket tager
sig af det.

## Hvordan virker det egentlig?

Det er værd at forstå, for så giver resten sig selv.

**Dit spil kører på din egen maskine.** Når du trykker på den grønne pil i
IntelliJ, starter der et helt almindeligt Java-program hos dig. Det ringer ud til
serveren og siger "jeg har et spil, der hedder Terningespil, og der skal være
2-4 spillere". Fra det øjeblik står dit spil i lobbyen.

**Spillerne kører deres eget program på deres egen maskine.** Også det ringer ud
til serveren.

**Serveren sender bare tekst frem og tilbage.** Den kender ikke dit spil. Den ved
ikke, hvad en terning er, hvem der har tur, eller hvad der er et gyldigt svar.
Den ved kun, at der sidder nogle spillere ved et bord, og at et program vil sende
en linje tekst til en af dem.

```
   din maskine                serveren                 Emmas maskine
 ┌──────────────┐          ┌────────────┐            ┌──────────────┐
 │ DiceGame     │─────────▶│  lobby     │◀───────────│ StartPlayer  │
 │ (dit spil)   │◀─────────│  borde     │───────────▶│ (Emma)       │
 └──────────────┘          │  beskeder  │            └──────────────┘
                           │            │            ┌──────────────┐
                           │ INGEN      │◀───────────│ StartPlayer  │
                           │ spilregler │───────────▶│ (Noah)       │
                           └────────────┘            └──────────────┘
```

Læg mærke til pilene: **både dit spil og spillerne ringer ud til serveren.**
Ingen ringer ind til din maskine, så du skal ikke lave huller i noget.

### Hvad der sker, når du skriver `p.askInt("Dit gæt?", 1, 100)`

1. Dit program sender teksten `Dit gæt?` til serveren.
2. Serveren sender den videre til Emmas program.
3. Emma ser spørgsmålet og skriver `banan`.
4. Emmas svar kommer tilbage til **dit** program.
5. Dit program ser, at `banan` ikke er et tal, og sender et nyt spørgsmål:
   *"Please type a whole number between 1 and 100."*
6. Emma skriver `42`. Nu returnerer `askInt` tallet 42, og din kode kører videre.

Punkt 5 er det vigtige: **det er dit program på din maskine, der tjekker svaret.**
Serveren aner ikke, at der var noget galt — den sendte bare en linje tekst begge
veje. Derfor kan `askInt` aldrig give dig andet end et tal, og derfor behøver du
aldrig at skrive `try`/`catch` eller tjekke input.

Imens `askInt` venter, står dit program stille på præcis den linje — nøjagtig som
når en `Scanner` venter på tastaturet. Det er hele idéen: **konsollen er skiftet
ud med en anden persons computer.**

### Tre ting, der følger af det

- **Lukker du IntelliJ, forsvinder dit spil fra lobbyen.** Det kørte jo hos dig.
  Ingen kan spille det, mens din maskine er slukket.
- **`System.out.println` skriver på din egen skærm** — ikke hos spillerne. Det
  er ubrugeligt til at vise noget i spillet, men rigtig godt til at finde fejl:
  du kan skrive det hemmelige tal ud til dig selv, mens du tester.
- **Går dit program ned, går kun dit spil ned.** Serveren og de andres spil kører
  videre. Fejlen står i din egen konsol, hvor du kan se den.

## To ting du kan gøre — og hvornår

**Spille andres spil.** Det kan du med det samme. Du skal bare åbne skabelonen og
køre `StartPlayer`. Du behøver ikke selv at lave et spil for at være med.

**Lave dit eget spil.** Her skriver du `implements Game`, og det giver først
mening, når vi har haft **interfaces** — det gør vi omkring Adventure-projektet.
Prøv endelig før, men bliv ikke overrasket, hvis `implements` ligner volapyk
indtil da.

## Kom i gang

1. **Hent skabelonen.** Øverst på repoets forside er der en grøn **Code**-knap →
   *Download ZIP*. Pak ud, og find `00_vejledninger/tekstspil/skabelon`.
2. **Kopiér `skabelon`-mappen** ud et sted, hvor du har dine egne projekter, og
   omdøb den til dit spil.
3. **Åbn mappen i IntelliJ** — File → Open → vælg mappen, ikke filerne i den.
   IntelliJ henter selv biblioteket. Første gang tager det et øjeblik.
4. **Lav `kodeord.txt`** (se nedenfor).
5. **Omdøb de to klasser**, når du skal lave dit eget spil: højreklik på
   klassenavnet → Refactor → Rename. `MyGame` og `MyGameMatch` skal begge
   omdøbes. `StartPlayer` skal du lade være.

Der ligger tre filer: `MyGame` og `MyGameMatch` er **dit spil**, og `StartPlayer` er
bare en grøn pil, der starter spiller-programmet.

## Kodeordet

Serveren kræver et kodeord, så det ikke er hele internettet, der kan koble sig på
jeres spil.

Kodeordet står i **Teams**, i holdets team — det ligger ikke her i repoet, for
det kan alle på internettet læse.

**Lav en fil, der hedder `kodeord.txt`** i projektmappen — samme sted som
`pom.xml` ligger. I filen skriver du ordet fra Teams, og ikke andet. Så finder
programmet det selv; du skal ikke kalde noget.

```
mit-spil/
├── pom.xml
├── kodeord.txt      <- den her, som du selv laver
└── src/
```

**Kodeordet må ikke på GitHub.** Et offentligt repo kan alle læse, og så kan alle
koble sig på. Derfor står `kodeord.txt` i `.gitignore` — så tager git den ikke
med, heller ikke hvis du skriver `git add .`. Tjek det med `git status`: filen
skal *ikke* stå på listen.

Det er derfor kodeordet ligger i en fil og ikke i koden: **koden deler du, filen
beholder du.** Sådan gør man også uden for skolen.

## Sådan spiller I

Du skal have **flere programmer i gang på én gang**: spillet, og én spiller for
hver, der er med. I IntelliJ bliver de til hver sin fane nederst i Run-vinduet.

1. **Start spilprogrammet** — grøn pil ud for `main` i `MyGame`. Nu står spillet
   i lobbyen, og alle på holdet kan se det.

2. **Start en spiller** — grøn pil ud for `main` i `StartPlayer`.

   Skal I være flere på den samme computer, skal IntelliJ have lov at køre
   `StartPlayer` mere end én gang: **Run → Edit Configurations…**, vælg `StartPlayer`,
   og sæt flueben i **Allow multiple instances** (den ligger under *Modify
   options*). Uden det flueben genstarter IntelliJ bare den spiller, der
   allerede kører.

   Du skriver dine svar nede i Run-vinduet, præcis som når du bruger en
   `Scanner`.

3. **I hvert spiller-vindue**: skriv et navn, vælg spillet, og lav et bord i det
   ene vindue. I det andet skriver du **bordets navn** ved "Which game?" — så
   hopper du direkte derhen. Skriv `ready` begge steder, og spillet går i gang.

Serveren kører hele tiden, så du skal ikke selv starte noget.

## Byg et terningespil, trin for trin

Her bygger vi et helt spil fra bunden. Start med skabelonen, og følg med.

### Trin 0: Omdøb klasserne

Højreklik på `MyGame` → Refactor → Rename → `DiceGame`. Gør det samme med
`MyGameMatch` → `DiceGameMatch`. IntelliJ retter selv alle steder, hvor navnene
bruges.

**Klassenavne, variabelnavne og kommentarer skriver vi altid på engelsk** — det
er sådan, Java-kode ser ud alle steder uden for Danmark, og det er den vane, I
skal have. Teksten inde i anførselstegnene er noget andet: den læser jeres
spillere, så den skriver I på dansk.

### Trin 1: Fortæl hvad spillet hedder

I `DiceGame.java` retter du de fire første metoder. Det er ren beskrivelse —
her sker der ikke noget spil.

```java
public String name() {
    return "Terningespil";
}

public String description() {
    return "Alle slår med en terning. Hojeste slag vinder.";
}

public int minPlayers() {
    return 2;
}

public int maxPlayers() {
    return 6;
}
```

Kør den grønne pil. Der står nu `Terningespil` i lobbyen — prøv at starte
`StartPlayer` og se efter. Spillet gør ikke noget endnu, men det er *der*.

### Trin 2: Alle slår én gang

Nu til `DiceGameMatch.java`. Her er hele spillet:

```java
import textgame.Match;
import textgame.Player;
import textgame.Room;

public class DiceGameMatch implements Match {

    public void play(Room room) {
        room.tellAll("Alle slår med en terning. Højeste slag vinder!");

        for (Player p : room.players()) {
            p.ask("Tryk enter for at slå");

            int roll = 1 + (int) (Math.random() * 6);
            room.tellAll(p.name() + " slog " + roll + ".");
        }
    }
}
```

Læg mærke til `for (Player p : room.players())` — den løber igennem spillerne i
den rækkefølge, de satte sig ved bordet. Det er sådan, du giver folk tur på skift.

`p.ask(...)` bruges her bare til at vente på spilleren. Vi er ligeglade med, hvad
hun skriver; vi vil bare have, at hun trykker enter, før terningen ruller.

Prøv det med to spillere. Det virker — men ingen vinder endnu.

### Trin 3: Find vinderen

Vi skal huske det højeste slag og hvem der slog det. To variable uden for løkken:

```java
public void play(Room room) {
    room.tellAll("Alle slår med en terning. Højeste slag vinder!");

    Player winner = null;
    int highest = 0;

    for (Player p : room.players()) {
        p.ask("Tryk enter for at slå");

        int roll = 1 + (int) (Math.random() * 6);
        room.tellAll(p.name() + " slog " + roll + ".");

        if (roll > highest) {
            highest = roll;
            winner = p;
        }
    }

    room.tellAll(winner.name() + " vandt med " + highest + "!");
}
```

Det er præcis den samme "find den største"-løkke, som du har skrevet med arrays.
Forskellen er kun, at tallene kommer fra terningen i stedet for fra et array.

### Trin 4: Tre runder og point

Nu bliver det et rigtigt spil. Vi giver hver spiller et pointtal, og spiller tre
runder. Point gemmer vi i et array — ét felt pr. spiller:

```java
import java.util.List;
import textgame.Match;
import textgame.Player;
import textgame.Room;

public class DiceGameMatch implements Match {

    public void play(Room room) {
        List<Player> players = room.players();
        int[] points = new int[players.size()];

        for (int round = 1; round <= 3; round++) {
            room.tellAll("");
            room.tellAll("--- Runde " + round + " ---");

            for (int i = 0; i < players.size(); i++) {
                Player p = players.get(i);
                p.ask("Tryk enter for at slå");

                int roll = 1 + (int) (Math.random() * 6);
                points[i] = points[i] + roll;

                room.tellAll(p.name() + " slog " + roll
                             + " og har nu " + points[i] + " point.");
            }
        }

        // Find the player with the most points
        int best = 0;
        for (int i = 1; i < players.size(); i++) {
            if (points[i] > points[best]) {
                best = i;
            }
        }

        room.tellAll("");
        room.tellAll(players.get(best).name() + " vandt med "
                     + points[best] + " point!");
    }
}
```

**Hvorfor virker `points[i]`?** Fordi `players` og `points` står i samme
rækkefølge: spiller nummer `i` har sine point i felt nummer `i`. Det er
parallelle arrays, præcis som du har brugt til navne og karakterer.

Spillerlisten laver ikke numre om undervejs — den er den samme hele spillet
igennem — så det holder.

### Trin 5: Vil du slå igen?

Nu tilføjer vi et valg, så spillet får en beslutning i sig. Man må slå én gang
mere, men så tæller kun det nye slag:

```java
int roll = 1 + (int) (Math.random() * 6);
p.tell("Du slog " + roll + ".");

if (p.askYesNo("Vil du slå om?")) {
    roll = 1 + (int) (Math.random() * 6);
    p.tell("Nu slog du " + roll + ".");
}

points[i] = points[i] + roll;
room.tellAll(p.name() + " endte på " + roll + ".");
```

Læg mærke til forskellen mellem `p.tell(...)` og `room.tellAll(...)`:
**`tell` går kun til den ene spiller**, så de andre ser ikke, hvad hun slog
første gang. Det er sådan, du laver hemmeligheder i et spil.

`askYesNo` tager sig af resten: skriver spilleren `måske`, bliver hun spurgt
igen, indtil der står noget brugbart. Den forstår både `ja`, `j`, `nej`, `n` og
de engelske `yes`/`no` — så dine spillere kan svare på dansk.

### Trin 6: Alle vælger samtidig

Vil du lave sten-saks-papir eller en afstemning, skal alle svare **på én gang** —
ingen må kunne se de andres valg først. Det er `room.askAllChoice`:

```java
Answers choices = room.askAllChoice("Sten, saks eller papir?",
                                    "sten", "saks", "papir");

for (Player p : room.players()) {
    room.tellAll(p.name() + " valgte " + choices.get(p) + ".");
}
```

Alle bliver spurgt i samme øjeblik, og linjen venter, til den sidste har svaret.
`choices.get(p)` giver teksten ("sten"), og `choices.getIndex(p)` giver nummeret
(0 for sten, 1 for saks, 2 for papir) — det sidste er nemmest at regne på.

Der findes også `askAllInt`, `askAllYesNo` og `askAll`.

### Idéer, du kan bygge videre på

- **Meget-mere-terning**: man må slå så mange gange man vil, men slår man en
  1'er, mister man alle point i runden. (`while` + `askYesNo`)
- **Gæt mit tal**: computeren tænker på et tal, spillerne gætter på skift, og
  spillet siger "for højt" eller "for lavt".
- **Quiz**: to arrays med spørgsmål og svar, `askChoice` til svarmulighederne.
- **Ordleg**: `p.ask(...)` giver dig teksten præcis som skrevet — så kan du
  tjekke, om det nye ord starter med det forrige ords sidste bogstav.
- **Spion**: alle får det samme ord undtagen én. `room.only(...)` og
  `room.without(...)` sender til nogle af spillerne i stedet for alle.

## Fejlfinding

Du kan ikke se, hvad spillerne ser, mens du udvikler — men du har to gode
redskaber:

**Skriv til dig selv.** `System.out.println("secret number: " + secret);`
skriver i *din* konsol i IntelliJ, hvor ingen spillere kan se det. Perfekt til
at følge med i, hvad dit spil tænker.

**Spil mod dig selv.** Start `StartPlayer` to gange (husk *Allow multiple
instances*), giv dem hvert sit navn, og sæt dem ved samme bord. Så kan du spille
hele spillet igennem alene, før du inviterer andre.

**Går spillet ned**, står fejlen i din egen konsol med linjenummer og det hele.
Spillerne får bare besked om, at spillet stoppede. De andre borde kører videre.

## Hvad du kan spørge om

En `Player` — én spiller:

| | |
|---|---|
| `p.name()` | spillerens navn |
| `p.tell("...")` | skriv til spilleren |
| `p.ask("...")` | spørg, og få linjen præcis som den blev skrevet |
| `p.askInt("...")` / `p.askInt("...", 1, 100)` | spørg om et helt tal |
| `p.askDouble("...")` | spørg om et tal med komma |
| `p.askYesNo("...")` | ja eller nej |
| `p.askChoice("...", "sten", "saks")` | menu — giver teksten tilbage |
| `p.askChoiceIndex("...", "sten", "saks")` | samme menu — giver nummeret (fra 0) |

Et `Room` — hele bordet:

| | |
|---|---|
| `room.players()` | alle spillere, i den rækkefølge de kom |
| `room.tellAll("...")` | skriv til alle |
| `room.only(p)` / `room.without(p)` | kun nogle af dem |
| `room.askAll("...")`, `askAllInt`, `askAllYesNo`, `askAllChoice` | spørg alle på én gang |

Spørger du alle på én gang, får du et `Answers` tilbage: `svar.get(p)`,
`svar.getInt(p)`, `svar.getYesNo(p)`, `svar.getIndex(p)`. Det er dét, der gør
sten-saks-papir muligt: alle svarer samtidig, og ingen kan nå at se de andres
træk først.

## De fem regler

1. **Skriv to klasser.** En `Game`, der siger hvad spillet hedder og hvor mange
   der kan være med, og en `Match`, der spiller det.
2. **`newMatch()` returnerer altid et nyt objekt.** `return new MyGameMatch();`
   — aldrig et felt, aldrig det samme objekt to gange.
3. **Ingen `static` felter med spildata**, og intet foranderligt på din `Game`.
   Flere spil kan køre samtidig i det samme program.
4. **Brug aldrig `System.out` eller `Scanner` i spillet.** Tekst ud sker med
   `tell` og `tellAll`, tekst ind med `ask`. `System.out.println` skriver på
   *din* skærm, hvor ingen spillere kan se det — fint til fejlfinding,
   ubrugeligt til at spille.
5. **Når `play()` er færdig, er spillet slut.** Vil du stoppe før tid, så
   `return`.

## Ting, du ikke skal bekymre dig om

Biblioteket klarer dem:

- **Forkert input.** Skriver en spiller `banan` til `askInt`, bliver hun spurgt
  igen. Dit spil ser det aldrig.
- **En spiller mister forbindelsen.** Spillet stopper pænt for de andre. Du
  skriver ingen fejlhåndtering.
- **Nogen skriver uden for tur.** Det bliver afvist, ikke gemt.
- **Flere borde samtidig.** Hvert bord kører for sig selv, også hvis to grupper
  spiller dit spil på én gang.

## Hvis noget driller

- **"needs the class password"** — du mangler `kodeord.txt`, eller den ligger
  det forkerte sted. Programmet skriver selv, hvor det har ledt.
- **"not the class password"** — der står noget forkert i filen. Kun ordet, intet
  andet. Tjek det mod opslaget i Teams; pas på med mellemrum, du kom til at
  kopiere med.
- **Kodeordet er blevet skiftet** — så skriver serveren det samme. Hent det nye
  fra Teams og ret `kodeord.txt`.
- **IntelliJ starter den samme spiller igen i stedet for en ny** — sæt flueben i
  *Allow multiple instances*, se ovenfor.

Kildekoden til det hele ligger på
<https://github.com/tgrundtvig/TextGameServer>, hvis du bliver nysgerrig.
