# Kode review af de færdige projekter

## Beskrivelse

Filmsamlingen er afleveret. Tre uger i ét fælles repository: CRUD, tests, datoer, exceptions,
filer, packages og sortering – bygget oven på den samme kode, lag for lag.

I dag læser I hinandens kode. To grupper sætter sig sammen – **hele gruppe mod hele gruppe** – og
reviewer hinandens færdige projekter efter [review-skemaet](../../projekter/filmsamling/kode-review.md).
I har læst den anden gruppes kode i går. I dag får I svar på jeres spørgsmål – og skal selv svare på
deres.

Dagen har tre dele:

1. **Runde 1** – I reviewer den anden gruppes projekt, eller de reviewer jeres
2. **Runde 2** – rollerne byttes
3. **Refleksion** – hvad tager du med fra Filmsamlingen?

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* gennemføre et code review sammen med en anden gruppe og holde det til koden
* give feedback, der er konkret, saglig og til at handle på
* forklare og begrunde jeres egen kode over for en anden udvikler
* modtage feedback og vælge, hvad I vil gøre med den
* aflevere et review som et issue på GitHub
* sætte ord på, hvad du har lært i projektet, og hvad du vil gøre anderledes i Delfinen

## Se disse videoer før undervisningen:

Ingen video i dag. Find jeres noter fra i går frem, og læs afsnittet
[Fredag 06-11: reviewet](../../projekter/filmsamling/kode-review.md#fredag-06-11-reviewet) i
review-skemaet.

## Læs nedenstående før undervisningen

---

### Dagens forløb

Planen herunder er et **forslag**. Underviseren fortæller ved dagens start, hvordan det bliver:
hvem der sidder med hvem, og hvor lang tid der er til hver runde.

| Del | Hvad | Forslag til tid |
| --- | --- | --- |
| – | Grupperne finder hinanden og gør klar | første kvarter |
| 1 | **Runde 1:** gruppe A reviewer gruppe B's projekt | ca. 1 time |
| – | Pause | |
| 2 | **Runde 2:** gruppe B reviewer gruppe A's projekt | ca. 1 time |
| 3 | Issues på GitHub og refleksion | sidste halve time |

Del tiden **ligeligt** mellem de to runder. Den gruppe, der starter, må ikke æde den andens tid.

---

### Roller i hver runde

Alle fra begge grupper er med hele tiden.

| Rolle | Hvem | Opgave |
| --- | --- | --- |
| **Fører** | én fra reviewer-gruppen | har projektet åbent i IntelliJ på en skærm, som alle kan se |
| **Skriver** | én fra reviewer-gruppen | udfylder skemaet undervejs |
| **Reviewere** | resten af reviewer-gruppen | stiller spørgsmålene fra skemaet og fra i går |
| **Programmører** | hele den gruppe, der bliver reviewet | forklarer deres kode og svarer på spørgsmål |

Skift gerne fører og skriver halvvejs, så flere prøver begge dele.

**Gå skemaet igennem i rækkefølge:** *GitHub*, *Kør programmet og testene*, *Packages*, *Klasser*,
*FileHandler*, *Comparator-klasserne*, *Tests*. I har det meste fra i går – i dag handler det om at
**tale** om det. Brug tiden på jeres spørgsmål og på de steder, hvor I var i tvivl, ikke på at
læse op af det, I allerede har skrevet.

Afslut hver runde med afsnittet *Yderligere kommentarer*:

* **Det er særlig godt:** mindst tre ting
* **De tre vigtigste ting at rette** – i prioriteret rækkefølge

---

### Som reviewer: stil de gode spørgsmål

Et spørgsmål, der starter med **hvorfor**, lærer begge grupper mest:

* *"Hvorfor ligger `sortMovies` i `Controller` og ikke i `MovieCollection`?"*
* *"Hvordan ved `FileHandler`, at en linje i filen er ødelagt?"*
* *"Hvad sker der, hvis man sletter den film, man lige har søgt på?"*
* *"Hvis I skulle tilføje en ny sorteringsmulighed, hvor mange klasser skulle I så ændre?"*
* *"Hvilken af jeres tests fangede flest fejl undervejs?"*

Det næstsidste spørgsmål er værd at stille til alle. Svaret siger meget om, hvor godt
`Comparator`-interfacet og `SortField` er brugt.

**Sådan giver I feedback, der kan bruges** – samme regler som i
[Adventure](../../41/05_fre_2026-10-09/README.md#sådan-giver-i-god-feedback):

* **konkret:** klasse, metode, gerne linjenummer – *"`handle()` i `Controller` linje 42"*
* **om koden, ikke om personen:** *"metoden er lang"*, ikke *"I skriver lange metoder"*
* **spørg, før I dømmer:** der kan være en god grund
* **ros det, der er godt** – det er også feedback

### Som programmør: forklar – og lyt

* **Alle** i gruppen svarer – også om klasser, man ikke selv har skrevet. Det var et krav i
  projektet, og det bliver det igen til eksamen.
* **Forklar hvorfor**, ikke bare hvad. *"Vi lagde det i `Controller`, fordi ..."*
* **Forsvar jer ikke.** Et fund er ikke en anklage. Sig *"det har I ret i"*, når de har ret, og
  *"vi valgte det, fordi ..."*, når I har en grund.
* **Skriv ned**, hvad I får at vide – også det, I er uenige i. Tænk over det, før I afviser det.

---

### Aflevér reviewet som et issue

Når en runde er slut, får den reviewede gruppe skemaet som et **issue** i deres repository – se
[Aflevering af reviewet](../../projekter/filmsamling/kode-review.md#aflevering-af-reviewet):

1. I den anden gruppes repository på GitHub: fanen **Issues → New issue**.
2. Titel: `Code review fra gruppe ...`.
3. Indsæt det udfyldte skema, og klik **Create**.

Er fanen **Issues** væk, har gruppen slået den fra. Så kan de slå den til under **Settings →
General → Features → Issues** – eller I sender dem skemaet på anden vis. Se også GitHubs vejledning
[Creating an issue](https://docs.github.com/en/issues/tracking-your-work-with-issues/using-issues/creating-an-issue).

Et issue er i øvrigt præcis det, man bruger i rigtige projekter til at holde styr på fejl og
opgaver. I kommer til at bruge dem igen i Delfinen.

---

### Efter reviewet

Afleveringen er lukket, og reviewet handler om den version, der lå der ved deadline. I må gerne
rette det, I har fået at vide – det bliver jeres kode bedre af – men det tæller ikke med i
afleveringen.

Det vigtigste, I tager med, er ikke rettelserne. Det er **vanerne**: de ting, I vil gøre fra
starten næste gang. Det er det, refleksionen handler om.

---

### Refleksion

Skriv svarene til dig selv – 5–10 linjer i alt. Gem dem; de kan bruges til semesterevalueringen
fredag 13-11 og når Delfinen starter.

1. Hvad i jeres Filmsamling er du **mest stolt af**?
2. Hvilket begreb forstår du nu, som du ikke forstod for tre uger siden? (Unit test, exceptions,
   filer, packages, interfaces, …)
3. Hvad var **sværest** – teknisk eller i samarbejdet – og hvad hjalp jer videre?
4. Hvordan gik det med at arbejde i **ét fælles repository**? Hvor mange merge-konflikter fik I, og
   hvad lærte I af dem?
5. Hvilken feedback fik I i dag, som du vil tage med dig?
6. Delfinen er et projekt i grupper på fire, med to sprints. Hvad vil du gøre **anderledes**?

Tal derefter kort om svarene i gruppen.

---

### Næste uge og derefter

Næste uge er **repetitionsuge**: tre dage, hvor vi samler op på semestrets programmering – fra
variable og loops til interfaces, exceptions og filer – med opgaver i stigende sværhedsgrad. Det
starter [mandag 09-11](../../46/01_man_2026-11-09/README.md).

Mandag 16-11 går vi i gang med [Delfinen](../../projekter/delfinen/readme.md), semestrets
eksamensprojekt.

---

## Det vigtigste at tage med

* to runder, lige lang tid – alle fra begge grupper er med hele tiden
* reviewer-gruppen fører og skriver; programmørerne forklarer
* de bedste spørgsmål starter med **hvorfor**
* feedback er **konkret**, handler om **koden** – og ros er også feedback
* alle i gruppen skal kunne forklare **hele** programmet
* reviewet afleveres som et **issue** i den anden gruppes repository
* skriv refleksionen ned – den er til dig selv, og til Delfinen

## Aktiviteter i undervisningen

### 1. Gør klar (første kvarter)

Sæt jer sammen med den anden gruppe. Åbn begge projekter på den commit, der lå der ved deadline,
og find skemaerne fra i går frem. Aftal, hvilken gruppe der starter.

### 2. Runde 1 og runde 2

Følg [Roller i hver runde](#roller-i-hver-runde). Underviseren går rundt og lytter med – og hjælper,
hvis I går i stå eller er uenige om noget.

### 3. Issues

Opret issuet i den anden gruppes repository, og tjek, at I selv har fået deres.

### 4. Refleksion

Skriv svarene på [refleksionsspørgsmålene](#refleksion) hver for sig, og tal dem derefter igennem i
gruppen.
