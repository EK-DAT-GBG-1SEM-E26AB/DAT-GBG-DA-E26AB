# Opgaver – Git branching

Dagens opgaver foregår i et **øvelsesprojekt**, `branch-oevelse`, ikke i Delfinen-repoet. Så
gør det ikke noget, hvis noget går galt. Lav gerne et nyt øvelsesprojekt og start forfra, hvis I
kører helt fast.

* **Del 1** (opgave 1–6) laver du **alene**, på din egen computer. Du behøver ikke GitHub.
* **Del 2** (opgave 7–12) laver I **sammen** i Delfinen-gruppen, med et fælles repo på GitHub.

> **Når tiden er knap:** opgave 1, 2, 5, 7, 8, 9 og 12 er kernen – de dækker det, I skal bruge i
> Delfinen fra i morgen. Resten (3, 4, 6, 10, 11 og udfordringerne) kan I tage bagefter eller
> vende tilbage til, når I møder situationen i projektet.

Gør hver opgave i **terminalen** (Git Bash på Windows, Terminal på Mac, eller fanen **Terminal**
nederst i IntelliJ). Når en opgave virker, så **gør den samme opgave i IntelliJ** med
VCS-widget'en øverst i vinduet. Kommandoerne og de tilsvarende steder i IntelliJ står i
[tabellen på dagens side](README.md#kommandoerne-samlet).

Kør **`git log --oneline --graph --all`** efter hver opgave, og se, hvordan historikken har ændret
sig. Det er den vigtigste vane i dag. (I IntelliJ: **Git**-vinduet, fanen **Log**.)

I [løsningerne](loesninger.md) står det output, I skal forvente. Jeres commit-id'er (fx `c542afb`)
bliver nogle andre.

---

# Del 1 – Alene

## Opgave 1 – Øvelsesprojektet

1. Opret et nyt IntelliJ-projekt, `branch-oevelse` (Build system: **IntelliJ**), med flueben i
   **Create Git repository**.
2. Opret de fire klasser i `src`:

```java
public abstract class Animal {

    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String makeSound();
}
```

```java
public class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return "Vov";
    }
}
```

```java
public class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return "Mjav";
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        Animal[] animals = {new Dog("Fido"), new Cat("Misser")};

        for (Animal animal : animals) {
            System.out.println(animal.getName() + " siger " + animal.makeSound());
        }
    }
}
```

3. Kør `Main`. Commit med beskeden `Opret Animal, Dog, Cat og Main`. Tjek i commit-vinduet, at
   `out/` **ikke** er med. (IntelliJ's `.gitignore` sørger normalt for det.)
4. Kør `git branch`. Står der `master`, så omdøb den med `git branch -m main`.

## Opgave 2 – Din første branch

Dyrene skal kunne spise. Det laver du på en branch.

1. Lav branchen `eat-feature` og skift til den.
2. Tilføj en abstrakt metode i `Animal`:

```java
    public abstract boolean eat(String food);
```

3. Implementér den i `Dog` (en hund spiser alt) og `Cat` (en kat spiser kun fisk):

```java
    @Override
    public boolean eat(String food) {
        return true;
    }
```

```java
    @Override
    public boolean eat(String food) {
        return food.equals("fisk");
    }
```

4. Kør `Main`, så du ved, at det kompilerer. Commit: `Dyr kan spise`.
5. Kør `git branch` og `git log --oneline --graph --all`. Hvilken branch står du på? Hvor mange
   commits har `main`, og hvor mange har `eat-feature`?

## Opgave 3 – Hvor blev koden af?

1. Skift til `main`.
2. Kig i `Animal.java`. Hvor er `eat`?
3. Kør `Main`. Virker det?
4. Skift tilbage til `eat-feature`, og kig i `Animal.java` igen.
5. Forklar med dine egne ord, hvad der sker med filerne, når du skifter branch.
6. Slut af med at skifte til `main`.

## Opgave 4 – main flytter sig

Nu lader vi, som om en anden i gruppen har sat noget ind i `main`, mens du arbejdede på
`eat-feature`.

1. Stå på `main`. Tilføj en velkomst øverst i `main`-metoden i `Main`:

```java
        System.out.println("Velkommen til dyrehaven");
```

2. Kør, og commit: `Velkomst i Main`.
3. Kør `git log --oneline --graph --all`. Tegn historikken på papir. Hvor deler den sig?

> I Delfinen committer I **ikke** direkte på `main`. Her gør vi det kun for at efterligne, at en
> anden har merget noget.

## Opgave 5 – Merge ned, merge op

1. Skift til `eat-feature`.
2. **Merge ned:** merge `main` ind i `eat-feature` (`git merge main --no-edit`). Står velkomsten nu
   også i `Main` på din branch?
3. Kør `Main`.
4. Afprøv den nye metode. Tilføj denne linje i loopet i `Main`, lige efter den, der skriver lyden:

```java
            System.out.println(animal.getName() + " spiser fisk: " + animal.eat("fisk"));
```

5. Kør, og commit: `Afprøv eat i Main`.
6. **Merge op:** skift til `main`, og merge `eat-feature` ind. Hvad skriver Git? Hvorfor står der
   **Fast-forward**?
7. Kør `git log --oneline --graph --all`. Find merge-commit'en fra trin 2.
8. Slet `eat-feature`.

## Opgave 6 – Glemt at committe

1. Lav branchen `jump-feature`.
2. Tilføj i `Animal`:

```java
    public abstract void jump(double distance);
```

   Implementér den **ikke** i `Dog` og `Cat` endnu. Programmet kompilerer nu ikke.
3. **Uden at committe**: skift til `main`. Lykkes det?
4. Kør `git status`, og prøv at køre `Main`. Hvad er der sket? Hvorfor kompilerer `main` ikke?
5. Skift tilbage til `jump-feature`, og commit ændringen (`Påbegyndt jump`). Skift så til `main`,
   og kør `Main` igen.
6. Du beslutter, at dyrene alligevel ikke skal hoppe. Prøv at slette `jump-feature` med
   `git branch -d`. Hvad siger Git? Hvorfor? Slet den så alligevel.

---

# Del 2 – Sammen

Nu arbejder I i Delfinen-gruppen i **ét** fælles øvelses-repo. Personerne kaldes **A**, **B**,
**C** og **D** herunder. Er I kun tre, så lad A også være D. Sid, så I kan se hinandens skærme,
og sig højt, hvad I gør.

## Opgave 7 – Fælles repo

1. **Alle:** kør `git config --global pull.rebase false` (se
   [dagens side](README.md#når-push-bliver-afvist) for hvorfor).
2. **A:** del dit `branch-oevelse` på GitHub: **Git → GitHub → Share Project on GitHub**, og sørg
   for, at **Private** ikke er markeret. Invitér de andre som collaborators (**Settings →
   Collaborators → Add people**).
3. **B, C, D:** acceptér invitationen, og klon repoet (**File → New → Project from Version
   Control**). Kør `Main`.
4. **Alle:** kør `git log --oneline --graph --all`. Kan I se A's historik fra del 1, også
   merge-commit'en?

## Opgave 8 – Push en branch, hent en branch

1. **A:** lav branchen `bird`. Tilføj klassen `Bird`:

```java
public class Bird extends Animal {

    public Bird(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return "Pip";
    }

    @Override
    public boolean eat(String food) {
        return food.equals("frø");
    }
}
```

   og sæt en fugl ind i arrayet i `Main`:

```java
        Animal[] animals = {new Dog("Fido"), new Cat("Misser"), new Bird("Pippi")};
```

2. **A:** kør, commit (`Tilføj Bird`) og push branchen: `git push -u origin bird`.
3. **Alle:** åbn repoet på GitHub, og klik på branch-menuen, hvor der står `main`. Hvor mange
   branches er der?
4. **B:** kør `git branch -a`. Kan du se `bird`? Kør `git fetch` og så `git branch -a` igen.
   Hvad er forskellen?
5. **B:** skift til `bird`, og kør `Main`. Skift så tilbage til `main`.

## Opgave 9 – Konflikt mellem to branches

A og B laver hver sit dyr **samtidig**. Begge sætter dyret ind på den **samme linje** i `Main`.

1. **B:** stå på `main`, og lav branchen `fish`. Tilføj klassen `Fish` (lyd `"Blub"`, spiser
   `"plankton"`, ligesom `Bird`), og sæt den ind i arrayet:

```java
        Animal[] animals = {new Dog("Fido"), new Cat("Misser"), new Fish("Nemo")};
```

   Kør og commit (`Tilføj Fish`). **Merge ikke endnu.**
2. **A:** følg tjeklisten fra dagens side og få `bird` ind i `main`: switch til `main`, pull,
   switch til `bird`, merge `main` ned, test, switch til `main`, merge `bird` op, push.
3. **B:** nu er det din tur. Følg den samme tjekliste med `fish`. Hvad sker der, når du merger
   `main` ned i `fish`?
4. **B:** løs konflikten, så **både** fuglen og fisken er med. Gør det i IntelliJ, hvis du kan
   (**Merge...** eller **Resolve Manually**), og se filen i terminalen bagefter. Kør `Main`. Afslut mergen.
5. **B:** merge `fish` op i `main`, og push.
6. **Alle:** pull `main`, og kør `Main`. Er alle fire dyr med?
7. Kør `git log --oneline --graph --all` hos B. Tegn den.

Byt roller (C og D), og gør det igen med to andre dyr, fx `Horse` og `Cow`, så alle har prøvet at
løse en konflikt.

## Opgave 10 – Afvist push

1. **A:** pull `main`. Lav branchen `cat-sound`, og ret kattens lyd til `"Miav"`. Commit.
2. **B:** pull `main`. Lav branchen `dog-sound`, og ret hundens lyd til `"Vuf"`. Commit.
3. **A:** switch til `main`, merge `cat-sound` ind, og push.
4. **B:** switch til `main` **uden at pulle**, merge `dog-sound` ind, og push. Hvad sker der?
5. **B:** løs det: `git pull --no-edit`, kør `Main` (siger katten *Miav* og hunden *Vuf*?), og push igen.
6. Hvilket trin i tjeklisten sprang B over? Hvorfor gik det alligevel godt her?

## Opgave 11 – Ryd op

1. **Alle:** kør `git branch -a`. Hvor mange branches har I hver især, lokalt og på GitHub?
2. Slet alle lokale branches, der er merget ind i `main`, med `git branch -d`.
3. Slet de branches på GitHub, som **du** har pushet, med `git push origin --delete ...`.
4. **Alle:** kør `git fetch --prune` og `git branch -a` igen. Er der nu kun `main` tilbage?

## Opgave 12 – Hele arbejdsgangen

Nu prøver I det, I skal gøre i Delfinen. Hver person tager **én** user story nedenfor, laver den på
sin egen branch og følger **hele tjeklisten** fra dagens side, trin 1–5. Mål: Alle fire er merget
ind i `main` inden for 45 minutter, og `main` kan køre efter **hver** merge.

| Branch | User story |
|---|---|
| `animal-count` | Som besøgende vil jeg se, hvor mange dyr der er i dyrehaven, så jeg ved, hvad jeg kan nå. (Skriv fx `Der er 4 dyr` efter velkomsten. Tallet skal regnes ud, ikke skrives fast.) |
| `fish-eaters` | Som dyrepasser vil jeg se, hvilke dyr der spiser fisk, så jeg ved, hvem der skal have fisk. (Skriv navnene til sidst.) |
| `horse` | Som besøgende vil jeg kunne se en hest, så dyrehaven er mere spændende. (Klassen `Horse`, sat ind i arrayet.) |
| `age` | Som dyrepasser vil jeg kende dyrenes alder, så jeg kan passe de gamle dyr. (Attribut `age` i `Animal`, og alderen skrives i loopet, fx `Fido (3 år) siger Vov`.) |

Undervejs:

* Den, der bliver færdig først, har det let. Den sidste får (sandsynligvis) en konflikt i `Main`.
* Den, der laver `age`, skal ændre konstruktøren i `Animal` og **alle** subklasser. Hvad betyder det
  for de andre? Skal `horse` merge `main` ned, efter at `age` er merget?
* Bagefter: Hvilke konflikter fik I? Hvordan kunne I have undgået dem?

---

## Udfordring 1 – Stash

Nogle gange skal du skifte branch midt i noget, du ikke vil committe. Så kan du lægge ændringerne
til side med **stash**.

1. Lav en branch, `stash-test`, og ret noget i `Dog`. Commit.
2. Ret noget mere i `Dog`, **uden** at committe. Prøv at skifte til `main`. Hvad siger Git?
3. Kør `git stash`. Kør `git status`. Hvor blev ændringen af?
4. Skift til `main`, og tilbage igen. Kør `git stash list` og så `git stash pop`. Er ændringen
   tilbage?
5. Find det samme i IntelliJ: i **Commit**-vinduet (<kbd>Alt</kbd>+<kbd>0</kbd>), højreklik og
   vælg **Git → Stash Changes**. Stashen hentes tilbage med **Unstash** og **Apply Stash**.

Hvorfor anbefaler dagens side alligevel at committe på din egen branch i stedet for at stashe?

## Udfordring 2 – Pull request på GitHub

I stedet for at merge op lokalt kan man bede om at få sin branch merget på GitHub og lade en anden
kigge den igennem først.

1. **A:** lav branchen `snake` med en `Snake`-klasse, sæt den ind i arrayet, commit og push
   branchen.
2. **A:** gå til repoet på GitHub. Der står en gul bjælke over fillisten med knappen **Compare &
   pull request**. Klik på den.
3. Tjek, at **base** er `main`, og **compare** er `snake`. Skriv en titel og en kort beskrivelse
   (hvilken user story, hvad der er lavet), og klik **Create pull request**.
4. **B:** åbn fanen **Pull requests**, og klik på A's pull request. Klik på **Files changed**, og
   læs ændringerne igennem. Skriv en kommentar til en linje, hvis du har en.
5. **B:** klik **Merge pull request** og **Confirm merge**. Klik derefter **Delete branch**.
6. **A og B:** `git switch main` og `git pull`. Er slangen med? Slet den lokale `snake` med
   `git branch -d snake`.
7. Diskutér: Hvad får man ud af en pull request i forhold til at merge lokalt? Hvad koster det?

Se GitHubs vejledninger til
[at oprette](https://docs.github.com/en/pull-requests/how-tos/create-pull-requests/creating-a-pull-request)
og [at merge](https://docs.github.com/en/pull-requests/how-tos/merge-and-close-pull-requests/merging-a-pull-request)
en pull request.

## Udfordring 3 – Kun IntelliJ

Lav opgave 2–5 igen i et nyt øvelsesprojekt, men **uden terminalen**: kun med VCS-widget'en og
**Git**-vinduet. Find merge-commit'en og fast-forwarden i **Log**-fanen. Hvilken måde foretrækker
du, og hvorfor?
