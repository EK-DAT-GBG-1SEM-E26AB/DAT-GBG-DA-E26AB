# Kode review – Adventure

> Skema til code review af Adventure-projektet. Del af det samlede
> [Adventure-projekt](readme.md).

Dette er et skema til code review for et projekt – hvor der både bliver fokuseret på overordnet
objektorienteret struktur, navngivning af metoder og variabler, brug af kommentarer, håndtering af
exceptions og andre detaljer.

> **Der er ikke fokus på brugergrænseflade og brugeroplevelsen.**

## Sådan gør I

Reviewet foregår **gruppe mod gruppe**: to grupper sætter sig sammen og reviewer hinandens kode –
først den ene gruppes, så den andens. Den gruppe, der bliver reviewet, er "programmørerne"; den
anden gruppe er reviewere. I udfylder dette dokument sammen, og reviewerne overdrager det
efterfølgende til programmørerne.

1. Én fra reviewer-gruppen cloner projektet fra GitHub og åbner det i IntelliJ.
2. En anden fra reviewer-gruppen skriver noter i en kopi af dette dokument.
3. Både reviewere og programmører følger med på skærmen med projekt-koden.

| | |
|---|---|
| **Gruppen, der reviewes** | *navne* |
| **GitHub repository** | *url* |
| **Commit hash** (7 cifre) | |
| **Reviewer-gruppen** | *navne* |

### Sådan læses skemaerne

* Spørgsmål markeret med 💣 er **dårlige** – de skal helst kunne besvares med **nej**.
* Sæt kryds i `[ ]`, hvis der kan svares **ja** til et spørgsmål.
* Hvis der er grund til at give eksempler eller uddybende kommentarer, så skriv dem på linjen
  umiddelbart under spørgsmålet.
* Nogle spørgsmål er måske ikke relevante – for eksempel er der ingen grund til at skrive om
  exception-håndtering for en klasse, der ikke har exceptions. I de tilfælde: **slet** blot
  spørgsmålet.

---

## GitHub

Før der kigges på kode, tag et kig på, hvad der ellers ligger i GitHub.

* [ ] Er der en `.gitignore`-fil?
* [ ] 💣 Er der uvedkommende filer i GitHub (for eksempel `.class`-filer eller lignende)?
* [ ] Ligger dokumentation og andet ikke-kode i en `docs`-mappe?

---

## Klasser

Gentag dette afsnit for hver klasse i projektet. Hvis der er hele sektioner, der ikke giver mening
at svare på – hvis der for eksempel ikke er brugt exceptions i en klasse – så slet det afsnit.

### Klassen: `-navn-`

* [ ] Er klassen godt navngivet?
* [ ] Er det let at regne ud, hvilken rolle klassen har?
* [ ] Er der brugt tydelig ental/flertal for at vise, om klassen indeholder ét eller flere
      underobjekter?

#### Attributter / fields

* [ ] 💣 Er der oprettet attributter, der burde være lokale variable i stedet?
* [ ] 💣 Er der erklæret ubenyttede attributter?

**Er attributter navngivet godt?**

* [ ] Er navnene selvforklarende?
* [ ] Er navnene fra "problemdomænet"?
* [ ] Er navnene tydeligt og korrekt ental eller flertal?
* [ ] Er navnene alle på samme sprog (engelsk/dansk)?

**Har attributter korrekt access?**

* [ ] Er der brugt `private`?
* [ ] Er der getter og setter (hvis nødvendigt)?
* [ ] 💣 Er attributter gjort `public` uden getter og setter?
* [ ] 💣 Er der hverken brugt `private` eller `public`, men bare 'ingenting'?

**Getters / setters**

* [ ] 💣 Er der ubenyttede get- og set-metoder?
* [ ] 💣 Er der metoder kaldet `get` eller `set`, som ikke er en getter/setter?
* [ ] 💣 Eller omvendt: er der gettere eller settere, der hedder noget andet end `get` eller `set`?

#### Metoder

Kig på hver metode, først "udefra", altså uden at læse koden inde i metoderne. Det hjælper måske at
"folde" alle metoderne sammen (<kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>-</kbd>, på Mac
<kbd>Cmd</kbd>+<kbd>Shift</kbd>+<kbd>-</kbd>).

**Er metoder navngivet godt?**

* [ ] Er navnene selvforklarende?
* [ ] Er navnene fra "problemdomænet"?
* [ ] Er det tydeligt, hvad hver metode gør, ud fra sit navn?
* [ ] Er det tydeligt nok ud fra navnet, om metoder returnerer eller modtager værdier?
* [ ] Hvis metoden modtager parametre, er det så umiddelbart til at regne ud, hvad de er til, ud fra
      navngivningen?
* [ ] Hvis metoden returnerer en værdi, giver datatypen så umiddelbar mening?
* [ ] Er der en fornuftig ensartethed/struktur i navngivningen af metoderne?
* [ ] Er metodenavnene navngivet med korrekt brug af `camelCase`?
* [ ] Er parameternavne navngivet med korrekt brug af `camelCase`?
* [ ] Er navnene alle på samme sprog (engelsk/dansk)?

#### Kode

Kig derefter **ind** i hver metode og vurdér koden inde i dem. I skal ikke besvare en sektion
særskilt for hver eneste metode, men kigge på metoderne som helhed, og blot notere, hvis der er et
sted i en metode, der afviger.

**Lokale variabler inde i metoder**

* [ ] Er variabler navngivet korrekt `camelCase`?
* [ ] Er variablernes navne i tilstrækkelig grad fra "problemdomænet"?
* [ ] Er variablerne erklæret, hvor de skal bruges?
* [ ] Har variablerne den korrekte datatype?
* [ ] Er navnene alle på samme sprog (engelsk/dansk)?

**Generel kodestil**

* [ ] Er koden pænt (og korrekt) formateret med indrykninger etc.?
* [ ] Er koden "skimbar" og umiddelbart let at overskue, uden at skulle nærlæse hver linje?
* [ ] 💣 Er der smarte "ninja-tricks", der gør koden kompakt, men måske sværere for nogle at læse?

**Exceptions**

* [ ] Bliver exceptions fanget (altså er der en `catch` fremfor blot en `throws`)?
* [ ] 💣 Bliver exceptions blot håndteret med en `catch` med `printStackTrace`?
* [ ] Bliver exceptions håndteret, der hvor det giver mening – kan programmet fortsætte efter en
      exception?
* [ ] Er der lavet custom exceptions (dvs. egne exception-typer) – og giver de bedre mening end de
      oprindelige?

#### Kommentarer

* [ ] Er der kommentarer, der f.eks. viser, hvor en fancy løsning er fundet på nettet?
* [ ] Er der kommentarer, der beskriver hvordan/hvorfor en metode er opbygget anderledes end
      forventet?
* [ ] Er der kommentarer til udvikleren selv, om ting der skal rettes/ændres i fremtiden?
* [ ] 💣 Er der kommentarer, der ikke længere giver mening, og måske burde have været slettet?
* [ ] Er der gode kommentarer i programmet?
* [ ] 💣 Er der overflødige kommentarer? Kunne f.eks. være `i++; // lægger en til i`

---

## Yderligere kommentarer til koden fra reviewerne

*Her kan I skrive yderligere noter, som I reviewere måtte have – ting der ikke lige passer ind i
skemaerne ovenfor. Ros til særligt elegant kode, spørgsmål til hvordan programmørerne fandt på en
eller anden løsning!*
