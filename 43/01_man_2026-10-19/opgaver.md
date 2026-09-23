# Opgaver – GitHub i grupper

Lav **først** [Filmsamling del 0](../../projekter/filmsamling/del-0-github.md). Den er dagens krav.
Opgaverne her er ekstra træning i det, der går galt, når flere arbejder i samme repository – så
I har prøvet det hele, **før** det sker midt i filmsamlingen.

Opgaverne laves i et **øve-repository** – ikke i gruppens filmsamling. Så må det gerne blive rodet.

Der er [vejledende løsninger](loesninger.md), men de fleste opgaver har ét rigtigt facit: at I har
prøvet det. Skriv undervejs ned, hvad I **forventer** sker, før I trykker – og sammenlign bagefter.

> **I roller:** Opgaverne kalder personerne **A** og **B** (og **C**, hvis I er tre). Byt roller
> undervejs, så alle har prøvet at få en konflikt.

---

## Opsætning (10 minutter)

1. **A** opretter et nyt projekt i IntelliJ, fx `konflikt-oevelse`. Det behøver ikke være Maven –
   et almindeligt IntelliJ-projekt er fint. Sæt flueben i **Create Git repository**.
2. A laver filen `README.md` i roden af projektet:

   ```markdown
   # Konfliktøvelse

   | Fornavn | GitHub |
   |---|---|
   ```

3. A laver klassen `Main` i `src`:

   ```java
   public class Main {
       public static void main(String[] args) {
           System.out.println("Velkommen!");
       }
   }
   ```

4. A committer (`Start`) og deler projektet på GitHub (**Git → GitHub → Share Project on
   GitHub**) og tilføjer de andre som **collaborators**.
5. **B** (og C) accepterer invitationen og cloner repositoriet (**File → New → Project from Version
   Control**). Kør `Main` – virker det?

> **Øver du alene?** Så kan du være både A og B: clone dit eget repository **en gang til** i en
> anden mappe (fx `konflikt-oevelse-b`) og åbn den i et ekstra IntelliJ-vindue. De to mapper opfører
> sig præcis som to computere.

---

## Del A – Når Git selv klarer det

### Opgave 1 – Forskellige filer

1. A og B puller begge.
2. **A** laver en ny klasse `Movie` med én attribut, `private String title;`. Commit og push.
3. **B** laver en ny klasse `Menu` med en metode, der udskriver `1. Opret en film`. Commit.
   **Push.**

* Hvad sker der, da B pusher?
* Hvad gør B så? Gør det.
* Kig i historikken (**Git**-vinduet, fanen **Log**). Hvor mange commits er der kommet? Hvad hedder
  den sidste?

### Opgave 2 – Samme fil, forskellige steder

1. Alle puller.
2. **A** ændrer overskriften i `README.md` til `# Konfliktøvelse – hold E26`. Commit og push.
3. **B** tilføjer (uden at pulle) en tom linje og derunder linjen `Øvelse i Git – 1. semester.`
   **under** tabellen i `README.md`. Commit og push.

* Blev der en konflikt? Hvorfor – eller hvorfor ikke?
* Åbn `README.md` hos B bagefter. Står begge ændringer der?

---

## Del B – Konflikter

### Opgave 3 – Den samme linje

1. Alle puller.
2. **A** og **B** ændrer **begge** teksten i `Main` – til hver sin tekst.
3. Begge committer. **A** pusher. **B** pusher – og puller, da det bliver afvist.
4. B løser konflikten med **Merge...** i IntelliJ. Vælg én af teksterne – eller skriv en tredje.
5. Kør `Main`. Push.

Byt roller og gør det igen. (Det er den samme øvelse som i del 0 – den skal sidde på rygraden.)

### Opgave 4 – Begge skriver sig på listen

1. Alle puller.
2. **A** og **B** tilføjer **samtidig** hver sin linje **nederst i tabellen** i `README.md` – deres
   eget navn og GitHub-brugernavn. Ingen puller.
3. A committer og pusher. B committer, pusher og puller.

* Skriv ned, før I prøver: bliver der en konflikt?
* Når B løser den: denne gang skal **begge** linjer med. Hvordan får I det i **Merge...**-vinduet?

### Opgave 5 – Konflikten i terminalen

Samme situation som i opgave 3, men B løser den **uden** IntelliJ's konfliktvindue.

1. Alle puller. A og B ændrer begge teksten i `Main`. Begge committer. A pusher.
2. **B** åbner en terminal i projektmappen (Git Bash på Windows, Terminal på Mac – eller fanen
   **Terminal** nederst i IntelliJ) og skriver:

   ```bash
   git push
   git pull
   ```

   Stopper Git med `fatal: Need to specify how to reconcile divergent branches.`, så læs afsnittet
   [Løs konflikten i terminalen](README.md#løs-konflikten-i-terminalen) og skriv
   `git config --global pull.rebase false`. Pull så igen.
3. Åbn `Main.java` i IntelliJ. Find markeringerne `<<<<<<<`, `=======` og `>>>>>>>`.
   Hvilken tekst er B's, og hvilken er A's?
4. Ret filen, så den ser rigtig ud, og fjern markeringerne.
5. Skriv `git status`. Hvad står der om `Main.java`?
6. Afslut:

   ```bash
   git add Main.java
   git commit --no-edit
   git push
   ```

### Opgave 6 – Fortryd en merge

Lav en konflikt igen, som i opgave 5 – men **fortryd** den denne gang, før du løser den:

```bash
git merge --abort
```

* Hvordan ser `Main.java` ud nu? Hvad siger `git status`?
* Pull igen, og løs konflikten rigtigt.

---

## Del C – Det lumske

### Opgave 7 – Ingen konflikt, men den kompilerer ikke

1. **A** laver klassen `Greeter` og ændrer `Main`, så den bruger den. Commit og push. Alle puller.

   ```java
   public class Greeter {
       public void printWelcome() {
           System.out.println("Velkommen til filmsamlingen!");
       }
   }
   ```

   ```java
   public class Main {
       public static void main(String[] args) {
           Greeter greeter = new Greeter();
           greeter.printWelcome();
       }
   }
   ```

2. **A** omdøber metoden `printWelcome` til `showWelcome` – højreklik på navnet → **Refactor →
   Rename**, så IntelliJ også retter kaldet i `Main`. Kør. Commit og push.
3. **B** laver (uden at pulle) en ny klasse `Menu`, der også hilser:

   ```java
   public class Menu {
       public void start() {
           Greeter greeter = new Greeter();
           greeter.printWelcome();
           System.out.println("1. Opret en film");
       }
   }
   ```

   Kør – det virker hos B. Commit, push, pull.

* Blev der en konflikt?
* Kør programmet hos B efter merge. Hvad sker der?
* Hvem har "skylden"? Hvad kunne have forhindret det?
* Ret fejlen, commit og push.

### Opgave 8 – Glemt at committe

1. Alle puller.
2. **A** ændrer teksten `1. Opret en film` i `Menu` til `1. Opret film`. Commit og push.
3. **B** ændrer den samme linje til `1. Tilføj en film` – men **committer ikke**. B puller.

* Hvad siger Git (eller IntelliJ)?
* Hvad er den sikre måde at komme videre på? Gør det, og løs konflikten.

---

## Udfordringer

### Udfordring 1 – Tre på én gang

Er I tre: **alle tre** ændrer den samme linje i `Main` og committer. A pusher, så B, så C. Hvor
mange konflikter løser B og C tilsammen? Hvorfor ikke flere?

### Udfordring 2 – Se historikken som en graf

I terminalen:

```bash
git log --oneline --graph
```

Find de steder, hvor historikken deler sig og samles igen. Hvilke commits er merge-commits? Sammenlign
med **Insights → Network** på GitHub.

### Udfordring 3 – Accept Yours

Lav en konflikt, hvor **A** har ændret **tre** linjer i en fil og **B** kun én af de samme linjer.
B løser den med **Accept Yours**. Hvad blev der af A's to andre ændringer? Hvad lærer det jer om
knappen?

### Udfordring 4 – `.gitignore`

Åbn `.gitignore` i øve-projektet. Hvad står der? Hvad ville der ske med konflikterne, hvis mappen
med de kompilerede `.class`-filer (`out`) **ikke** stod der, og alle committede den efter hver
kørsel?
