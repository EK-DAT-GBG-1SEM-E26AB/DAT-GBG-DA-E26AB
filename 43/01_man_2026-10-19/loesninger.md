# Vejledende løsninger – GitHub i grupper

Løsningerne til [opgaverne](opgaver.md). De fleste opgaver har ikke ét facit – det vigtige er, at
I har prøvet det. Her står, hvad der **skal** ske, og hvorfor.

---

## Opgave 1 – Forskellige filer

* B's push bliver **afvist** (`rejected ... fetch first`): GitHub har A's commit, som B ikke har.
* B puller. IntelliJ spørger evt. **Merge** eller **Rebase** – vælg **Merge**. Der er ingen
  konflikt, for A og B har ændret hver sin fil. B pusher igen, og nu går det.
* I historikken er der kommet **tre** commits: A's, B's og en **merge-commit**
  (`Merge branch 'main' of https://github.com/...`), som samler de to.

## Opgave 2 – Samme fil, forskellige steder

Ingen konflikt. Git fletter linje for linje, og A og B har ændret hver sin del af filen (overskriften
og linjerne under tabellen), med uændrede linjer imellem. Bagefter står **begge** ændringer i
`README.md`.

## Opgave 3 – Den samme linje

Konflikt i `Main.java`. I **Merge...**-vinduet er linjen rød i alle tre kolonner. Klik pilen ved den
tekst, I vil beholde – eller skriv en ny tekst i midten – og klik **Apply**. Kør `Main`, og push.

## Opgave 4 – Begge skriver sig på listen

**Ja**, der bliver en konflikt – selvom ingen af dem har ændret en eksisterende linje. Begge har
skrevet en ny linje **det samme sted** (efter den sidste linje i tabellen), og Git kan ikke vide,
hvilken der skal stå først.

I **Merge...**-vinduet: tag først den ene side med (pilen), og tilføj derefter den anden – eller
skriv den manglende linje direkte i midterkolonnen. Tjek, at resultatet i midten er en gyldig
tabel:

```markdown
| Fornavn | GitHub |
|---|---|
| Anna | anna-a |
| Bo | bo-b |
```

## Opgave 5 – Konflikten i terminalen

`Main.java` ser sådan ud hos B efter `git pull` (teksterne er jeres egne):

```text
public class Main {
    public static void main(String[] args) {
<<<<<<< HEAD
        System.out.println("Velkommen til Filmarkivet!");
=======
        System.out.println("Velkommen til Annas filmhylde!");
>>>>>>> f9472589715c3dc3aa5de57155537cd52f835fff
    }
}
```

* Mellem `<<<<<<< HEAD` og `=======` står **B's** egen tekst (`HEAD` er "der, hvor jeg står").
* Mellem `=======` og `>>>>>>>` står **A's** tekst, som kom fra GitHub.

`git status` skriver bl.a.:

```text
You have unmerged paths.
  (fix conflicts and run "git commit")
  (use "git merge --abort" to abort the merge)

Unmerged paths:
  (use "git add <file>..." to mark resolution)
	both modified:   Main.java
```

Efter rettelsen skal filen være helt almindelig Java igen – **ingen** markeringslinjer:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Velkommen til Filmarkivet!");
    }
}
```

`git add Main.java` fortæller Git, at konflikten i filen er løst. `git commit --no-edit` laver
merge-commit'en.

## Opgave 6 – Fortryd en merge

Efter `git merge --abort` er `Main.java` præcis, som den var **før** `git pull` – med B's tekst og
uden markeringer. `git status` siger, at B's gren og `origin/main` er gået i hver sin retning
(`ahead 1, behind 1` i den korte udgave `git status -sb`). Intet er tabt: pull igen, og løs
konflikten.

## Opgave 7 – Ingen konflikt, men den kompilerer ikke

* **Ingen konflikt.** A har ændret linjer i `Greeter` og `Main`; B har lavet en ny fil. Git merger
  uden at klage.
* Men programmet kompilerer ikke hos B:

  ```text
  Menu.java:4: error: cannot find symbol
          greeter.printWelcome();
                 ^
    symbol:   method printWelcome()
    location: variable greeter of type Greeter
  ```

* **Ingen har skylden** – begge havde kode, der virkede. Problemet opstår først, når de to ændringer
  mødes.
* Det forhindres af: at **pulle ofte** (så havde B set omdøbningen, før `Menu` blev skrevet), at
  **sige til**, når man omdøber noget, andre bruger – og at **kompilere og køre efter hver pull**,
  så fejlen bliver fanget, **før** den bliver pushet. Fra onsdag hjælper tests også.
* Rettelsen: `greeter.printWelcome()` → `greeter.showWelcome()` i `Menu`. Kør, commit, push.

## Opgave 8 – Glemt at committe

Git nægter at merge:

```text
error: Your local changes to the following files would be overwritten by merge:
	Menu.java
Please commit your changes or stash them before you merge.
Aborting
```

Den sikre vej: **commit** B's ændring først, og pull så igen. Nu er det en helt almindelig konflikt
som i opgave 3 – løs den og push.

---

## Udfordring 1 – Tre på én gang

**To** konflikter i alt: B får én (mod A's tekst). Når B har løst den og pushet, får C én (mod
resultatet af B's merge). C skal ikke løse A's og B's konflikt igen – den er allerede løst i den
merge-commit, C puller.

## Udfordring 2 – Se historikken som en graf

`git log --oneline --graph` tegner merge-commits som steder, hvor to streger løber sammen:

```text
*   290dbbe Merge branch 'main' of https://github.com/...
|\
| * 56dd239 Tilføj Anna
* | ea52de6 Ny overskrift
|/
* 87ac77f Start
```

(Jeres id'er og beskeder er andre.)

## Udfordring 3 – Accept Yours

A's **to andre** ændringer er **væk**. **Accept Yours** tager B's udgave af **hele filen** – ikke
kun de linjer, der var i konflikt. Derfor: brug **Merge...** og se, hvad I vælger.

## Udfordring 4 – `.gitignore`

IntelliJ's `.gitignore` indeholder bl.a. `out/` – mappen med de kompilerede filer. Committede alle
`out`, ville hver ændring i koden også ændre `.class`-filerne, og to personer, der har ændret og kørt
programmet hver for sig, ville få en konflikt i filer, som ingen kan læse eller løse i hånden. Kompilerede filer laves ud fra
koden, så de skal aldrig i Git. I filmsamlingen hedder mappen `target` (Maven) – det er derfor, del
0 beder jer tjekke, at den ikke kommer med.
