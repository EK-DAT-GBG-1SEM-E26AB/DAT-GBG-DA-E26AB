# Peer review af Delfinen

> Fredag 11-12. **Obligatorisk.** Del af [Delfinen-projektet](readme.md).

Projektet er afleveret. Nu skal en anden gruppe se på jeres kode, og I skal se på deres. Samme dag
får I feedback fra en underviser.

## Hvad er peer review?

Et **peer review** er et kodereview, hvor det er jeres medstuderende ("peers"), der reviewer. I har
prøvet kodereview før, både i Adventure og Filmsamling og mandag 30-11. Denne gang er der to
forskelle:

1. **Programmet er færdigt.** Der er ikke noget at nå at rette til afleveringen. Formålet er at
   lære af det, I har lavet, og af det, de andre har lavet.
2. **Reviewerne lader, som om de skal overtage projektet.** Forestil jer, at Delfinen har købt
   programmet, og at jeres gruppe skal vedligeholde det fremover. Kan I finde rundt i koden? Kan I
   tilføje et menupunkt uden at bryde noget? Kan I skifte CSV-filerne ud med noget andet?

Det er **S'et i FURPS** fra 26-10: *Supportability*, hvor let programmet er at vedligeholde og
ændre. Det handler ikke om, hvor pæn brugerfladen er.

> **Hvorfor bruge tid på det, når projektet er afleveret?** Fordi man lærer meget af at læse andres
> kode, og fordi det er første gang, nogen udefra læser jeres. Spørgsmål, I ikke kan svare på i
> dag, kan I tænke over, inden I går til eksamen.

---

## Før 11-12: forbered jer

### Selvrefleksion

Vi anbefaler, at gruppen inden reviewet skriver en kort **selvrefleksion** over projektet. Den er
ikke et krav og skal ikke afleveres, men den er god forberedelse: til samtalen med underviseren, til
at vise reviewerne, hvor I selv er i tvivl, og til eksamen. Læg den gerne i
`docs/selvrefleksion.md`.

Alle valg i et softwareprojekt er også fravalg af noget andet. Svar på spørgsmålene nedenfor, og
**begrund hvert svar** med fordele og ulemper ved det, I valgte:

1. **Filer:** Gemmer I medlemmer og resultater i én fil eller flere? Skriver I til filen løbende
   eller kun, når programmet lukker?
2. **Medlemsnummer:** Hvordan finder programmet det næste nummer? Hvad sker der med nummeret, hvis
   et medlem bliver meldt ud?
3. **Alder:** Hvordan regner I alderen ud, og hvorfor gemmer I fødselsdatoen og ikke alderen?
4. **Arv:** Har I brugt arv til konkurrencesvømmere? Hvorfor, eller hvorfor ikke?
5. **Enums:** Hvor har I brugt enum, og hvad havde alternativet været?
6. **Top 5:** Hvordan finder I de fem hurtigste? Har I brugt `Comparator` eller `Comparable`? Hvilken
   klasse har ansvaret for at lave listen, og hvorfor lige den?
7. **Test:** Hvad har I unit-testet, og hvad har I kun testet ved at køre programmet? Hvilke
   grænsetilfælde (fx 18 og 60 år) er dækket?
8. **Git:** Hvordan har I brugt branches? Hvad gik godt, og hvor fik I konflikter? Hvordan ser en
   god commit-besked ud?
9. **Boardet:** Hvilke kolonner havde I? Var boardet opdateret? Brugte I det til stand-up? Kunne
   I have klaret jer uden?
10. **Kodekvalitet:** Hvad har I gjort for at holde koden enkel og læsbar? Hvilken del af koden er
    sværest at forstå for en udefra? **Hvad vil I gerne have, at reviewerne ser særligt på?**
11. **Læring:** Hvad var det mest lærerige ved projektet, for jer hver især og som gruppe?

Det er ikke en rapport. Korte, ærlige svar er bedre end lange. *"Vi nåede ikke at teste top 5,
fordi ..."* er et fint svar.

### Gør koden klar til at blive læst

* Repoets `README.md` fortæller, hvordan programmet startes, og hvilke krav der er lavet.
* Eksempeldata ligger i repoet, så reviewerne kan prøve programmet med det samme.
* Find **hash'en** på jeres afleverede commit (de første 7 tegn, se *Git → Log* i IntelliJ eller
  *Commits* på GitHub). Reviewet sker på den version.

---

## Fredag 11-12: sådan foregår det

Underviseren fortæller på dagen, hvilke grupper der reviewer hinanden, og hvornår I har jeres
vejlederfeedback.

### Grupperne deler sig

Hver gruppe deler sig i to par:

* **Et par bliver hjemme** og tager imod reviewerne. De forklarer, hvis reviewerne spørger, og
  **tager noter**.
* **Et par er reviewere** hos en anden gruppe.

Bagefter deler parrene noterne med hinanden, så alle fire får hele feedbacken.

### Reviewerne kloner og kører

For at det bliver et review og ikke en demonstration, **kloner en af reviewerne projektet på sin
egen maskine** og kører det der. Alle samles om den maskine.

* Kan programmet ikke køre, er det i sig selv en vigtig observation. Skriv den ned, og læs så
  koden alligevel.
* Det er **reviewerne, der styrer**. Den reviewede gruppe svarer på spørgsmål, men viser ikke
  rundt.

### Hvad kigger reviewerne på?

Der er ikke et fast skema som i Adventure. Brug spørgsmålene her som inspiration. I skal ikke
svare på dem ét for ét.

**Navngivning**

* Er metoderne navngivet ens? Hedder UI-metoderne fx alle `showXxx`, eller er der både `show`,
  `print` og `display`?
* Kan man se, hvad en `public` metode gør, ud fra navnet alene? Og hvilke parametre den skal have?
* Er der styr på ental og flertal (`member` / `members`)?
* Kan man genkende domænemodellens begreber i klasse- og package-navnene?

**Struktur**

* Er packages meningsfulde, og ligger klasser, der hører sammen, i samme package?
* Er det tydeligt, hvilke klasser der bruger hvilke, eller kalder de hinanden på kryds og tværs?
* Har den klasse, der ejer data, også ansvaret for at regne på dem (**Information Expert**)? Hvor
  ligger kontingentberegningen?
* Er der metoder, der kalder videre på det, en anden metode returnerer, som
  `club.getMembers().get(0).getResults().get(2)` (**Law of Demeter**)?
* Hvor svært ville det være at tilføje et menupunkt? At skifte CSV-filerne ud med noget andet?

**Kode og algoritmer**

* Er der lange metoder, der ville blive lettere at forstå, hvis de blev delt i mindre?
* Er der kode, der er skrevet flere gange (**redundans**)?
* Kan man skimme koden og forstå den, eller skal man læse hver linje?
* Er der unit tests til det mest komplicerede? Er de grønne?
* Hvad sker der, hvis man taster noget forkert?

**Kommentarer**

* Er der kommentarer, hvor koden er svær at forstå, og ingen, hvor den taler for sig selv?
* Er der gamle kommentarer, der ikke passer mere (fx en `TODO` om noget, der er lavet)?
* Er der udkommenteret kode, der burde være slettet?

### Sådan giver man feedback

* **Vær konkret.** Ikke *"navngivningen er dårlig"*, men *"`calc()` i `Member` burde hedde
  `calculateFee()`"*. Nævn klasse og metode.
* **Sig også, hvad der er godt.** Hvad ville I selv have gjort sådan? Hvad lærte I af deres kode?
* **Foreslå, i stedet for at dømme.** *"Kunne man ...?"* virker bedre end *"I skulle have ..."*.
* **Snak om koden, ikke om personerne.**

Den reviewede gruppe skal ikke forsvare sig, men forstå. Spørg ind, hvis noget er uklart, og skriv
det ned.

### Noterne

Den reviewede gruppe skriver noterne efter denne skabelon:

```markdown
# Peer review af Delfinen

Gruppe under review:
Reviewet af (gruppe):
Repository:
Commit (7 tegn):

## Navngivning
## Struktur
## Kode og algoritmer
## Kommentarer
## Det, der var godt
## De tre vigtigste forslag til forbedring
```

Afslut med at blive enige om **de tre vigtigste forslag**. Det er dem, I skal tage med videre.

### Vejlederfeedback

Samme dag har hver gruppe en samtale med en underviser om projektet: programmet og processen, og
jeres selvrefleksion, hvis I har skrevet en. Alle fire skal være der.

---

## Obligatorisk fremmøde

Peer review og vejlederfeedback 11-12 er **obligatorisk**, og **alle fire i gruppen skal være
til stede**. Underviserne registrerer fremmødet. Der er **ingen aflevering i itslearning** til
peer reviewet.

Læg gerne noterne fra reviewet i `docs/peer-review.md` i jeres repo, så hele gruppen har dem.

Reviewet og vejlederfeedbacken tager udgangspunkt i den commit, I afleverede 08-12. Vi anbefaler, at
I venter med at rette i koden til efter 11-12, så alle ser den samme version. Filer i `docs/`
må gerne komme til efter afleveringen.

> **Kan en af jer ikke deltage 11-12?** Kontakt underviseren i god tid.
