# 2. Git i IntelliJ – trin for trin

[← Git i IntelliJ](README.md)

Små bidder, én ting ad gangen. Læs [1. Hvad Git gør](hvad-git-goer.md) først, hvis du ikke har gjort
det.

Alle Git-handlinger findes i menuen **Git** (i den nye brugerflade: klik på ☰ øverst til venstre →
**Git**). De fleste har også en genvej og en knap i værktøjslinjen øverst, hvor projektets
branch-navn står (fx `main`).

---

## Bid 1: Find rundt

To vinduer skal du kende:

| Vindue | Genvej | Viser |
| --- | --- | --- |
| **Commit** | <kbd>Alt</kbd>+<kbd>0</kbd> | Filer, du har ændret, men ikke committet |
| **Git** | <kbd>Alt</kbd>+<kbd>9</kbd> | Historikken (fanen **Log**) |

IntelliJ farver filnavnene i projektvinduet efter, hvad Git ved om dem:

| Farve | Betyder |
| --- | --- |
| Normal (hvid/sort) | Uændret siden sidste commit |
| **Blå** | Ændret, ikke committet |
| **Grøn** | Ny fil, som Git kender, ikke committet |
| **Rød** | Ny fil, som Git **ikke** holder øje med endnu |
| Gul/brun | Ignoreret (står i `.gitignore`) – skal ikke committes |

I venstre margen i editoren viser små farvede streger de linjer, du har ændret. Klik på en streg
for at se, hvad der stod før.

---

## Bid 2: Hent de andres ændringer – Update Project

**Genvej:** <kbd>Ctrl</kbd>+<kbd>T</kbd> · **Menu:** Git → Update Project

Gør det **hver gang, du sætter dig til at arbejde**.

1. Tryk <kbd>Ctrl</kbd>+<kbd>T</kbd>.
2. Første gang spørger IntelliJ, om den skal bruge **Merge** eller **Rebase**. Vælg **Merge**, og
   klik **OK**.
3. Nederst til højre står, hvor mange filer der blev opdateret.

Update Project er det samme som *pull*, men klogere: har du ændringer, du ikke har committet, lægger
IntelliJ dem til side, henter de nye commits og lægger dine ændringer tilbage bagefter.

---

## Bid 3: Se, hvad du har ændret

1. Åbn **Commit**-vinduet (<kbd>Alt</kbd>+<kbd>0</kbd>).
2. Under **Changes** står alle ændrede filer.
3. **Dobbeltklik** på en fil: til venstre den gamle version, til højre din. Ændringerne er farvet.

Kig altid her, før du committer. Er der filer med, som du ikke kender (fx noget i `target/` eller
`out/`)? Så spørg, før du committer.

---

## Bid 4: Commit

**Genvej:** <kbd>Ctrl</kbd>+<kbd>K</kbd> · **Menu:** Git → Commit

1. Tryk <kbd>Ctrl</kbd>+<kbd>K</kbd>. Commit-vinduet åbner.
2. Sæt **flueben** ved de filer, der skal med. Normalt er det alle – men kun dem, der hører til
   den ene ting, du har lavet.
3. Skriv en **besked**, der siger, hvad commit'en gør: `Tilføj kommandoen take`, ikke `ændringer`.
4. Klik **Commit**.

Nu ligger commit'en i *dit* album. **Ingen andre kan se den endnu.**

> **Staging:** I terminalen skal filer "stages" med `git add`, før de committes. I IntelliJ er
> fluebenene din staging – du behøver ikke gøre mere.

---

## Bid 5: Push

**Genvej:** <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>K</kbd> · **Menu:** Git → Push

1. Tryk <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>K</kbd>.
2. Til venstre ser du de commits, der sendes op. Er det dem, du forventer?
3. Klik **Push**.

Siger IntelliJ **Push Rejected**? Så har en anden pushet før dig. Vælg **Merge** – se
[Nødhjælp A](noedhjaelp.md#a-push-rejected).

Når du er tryg ved det, kan du i commit-vinduet klikke på pilen ved **Commit** og vælge
**Commit and Push…**. Så gør du bid 4 og 5 i ét hug.

---

## Bid 6: Fortryd ændringer, du ikke har committet – Rollback

Har du rodet i en fil og vil tilbage til, hvordan den så ud ved sidste commit?

1. Åbn **Commit**-vinduet (<kbd>Alt</kbd>+<kbd>0</kbd>).
2. Højreklik på filen → **Rollback…** (genvej <kbd>Ctrl</kbd>+<kbd>Alt</kbd>+<kbd>Z</kbd>).
3. Klik **Rollback**.

Vil du kun fortryde **ét sted** i filen: klik på den farvede streg i margenen og vælg pilen
**Rollback**.

---

## Bid 7: Se historikken

1. Åbn **Git**-vinduet (<kbd>Alt</kbd>+<kbd>9</kbd>) → fanen **Log**.
2. Hver linje er en commit: besked, forfatter, tidspunkt.
3. Klik på en commit: til højre ser du, hvilke filer den ændrede. Dobbeltklik for at se ændringen.

Vil du se historikken for **én fil**: højreklik på filen → **Git → Show History**.

Vil du vide, hvem der skrev en bestemt linje: højreklik i venstre margen i editoren →
**Annotate with Git Blame**.

---

## Bid 8: Local History – IntelliJ's egen sikkerhedskopi

IntelliJ gemmer selv versioner af dine filer, mens du arbejder – også det, du aldrig har committet.

1. Højreklik på en fil eller mappe → **Local History → Show History…**
2. Til venstre en liste med tidspunkter. Klik på et, og se, hvordan filen så ud.
3. Højreklik på tidspunktet → **Revert** for at få den version tilbage.

Local History ligger kun på din computer og slettes efter nogle dage. Den erstatter ikke Git –
men den redder dig, når alt andet har svigtet.

---

## Bid 9: Hent et repository første gang – Clone

1. **File → New → Project from Version Control…** (på velkomstskærmen: **Clone Repository**).
2. Sæt adressen ind fra GitHub (den grønne knap **Code** → kopiér HTTPS-adressen).
3. Vælg en mappe, fx i `IdeaProjects`, og klik **Clone**.
4. Spørger IntelliJ, om du stoler på projektet: **Trust Project**.

---

## Snydeark

| Jeg vil … | Genvej | Menu |
| --- | --- | --- |
| hente de andres ændringer | <kbd>Ctrl</kbd>+<kbd>T</kbd> | Git → Update Project |
| se mine ændringer | <kbd>Alt</kbd>+<kbd>0</kbd> | Commit-vinduet |
| committe | <kbd>Ctrl</kbd>+<kbd>K</kbd> | Git → Commit |
| pushe | <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>K</kbd> | Git → Push |
| fortryde ændringer, der ikke er committet | <kbd>Ctrl</kbd>+<kbd>Alt</kbd>+<kbd>Z</kbd> | Højreklik → Rollback… |
| se historikken | <kbd>Alt</kbd>+<kbd>9</kbd> | Git-vinduet → Log |
| redde noget, der er væk | | Højreklik → Local History → Show History… |

På Mac: <kbd>Cmd</kbd> i stedet for <kbd>Ctrl</kbd>, og <kbd>Cmd</kbd>+<kbd>9</kbd> /
<kbd>Cmd</kbd>+<kbd>0</kbd> i stedet for <kbd>Alt</kbd>+<kbd>9</kbd> / <kbd>Alt</kbd>+<kbd>0</kbd>.

**Næste:** [3. Gode vaner](gode-vaner.md)
