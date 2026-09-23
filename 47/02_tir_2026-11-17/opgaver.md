# Opgaver – User stories og product backlog

Arbejd **to og to**. Opgaverne bruger Kulturhuset fra [dagens side](README.md#eksemplet-kulturhuset),
ikke Delfinen – Delfinens user stories skriver I bagefter i gruppen.

Her er kundens tekst igen, så I har den ved hånden:

> *Programchefen opretter events med navn, dato og sal og vil gerne kunne se en liste over de
> kommende events. Billetsælgeren sælger billetter i lugen: i døren på dagen (150 kr.), i forsalg
> (120 kr.) og i forsalg med studierabat (90 kr.). Køber man i forsalg 10 dage eller mere før
> eventet, er der 20 % rabat. Til en studiebillet skal man oplyse sit studiekort-id. Der må ikke
> sælges flere billetter, end der er pladser i salen. Programchefen vil gerne se, hvor mange
> billetter der er solgt til hvert event, og hvor meget eventet har indbragt. Intet må gå tabt,
> når programmet lukkes.*

Der er [vejledende løsninger](loesninger.md).

---

## Opgave 1 – Hvad er der galt?

Her er otte "user stories", som en gruppe har skrevet til Kulturhuset. Skriv for hver, **hvad der
er galt** (brug gerne et bogstav fra INVEST), og **skriv den om**, så den bliver god. Nogle af dem
er slet ikke user stories og skal flyttes et andet sted hen. Hvor?

1. *Som udvikler vil jeg lave en klasse `Event`, så vi har noget at gemme events i.*
2. *Som billetsælger vil jeg kunne administrere billetter.*
3. *Programmet skal være hurtigt og nemt at bruge.*
4. *Som bruger vil jeg kunne se en liste.*
5. *Som programchef vil jeg kunne oprette et event og se, hvor meget alle events har indbragt, så
   jeg har styr på det hele.*
6. *Som billetsælger vil jeg have en menu med tallene 1–5, så jeg kan vælge.*
7. *Som programchef vil jeg kunne oprette et event.*
8. *Gem alt i en CSV-fil med semikolon.*

## Opgave 2 – Skriv user stories

Gå kundens tekst igennem **én bruger ad gangen** (programchef, billetsælger) og skriv alle de user
stories, I kan finde. Brug formatet *Som ... vil jeg ... så ...*.

* Hvor mange fik I? (Der er ikke et rigtigt tal, men under 6 er sandsynligvis for få.)
* Har I en story for *"Intet må gå tabt"*? Hvilken bruger har I skrevet den for?
* Er der noget i teksten, som ikke er en user story, men en **regel** i en anden stories
  acceptkriterier?

## Opgave 3 – Find grænserne

Tag denne user story:

> *Som billetsælger vil jeg kunne se prisen på en billet, før jeg sælger den, så jeg kan sige den
> til kunden.*

Eventet er **torsdag 10-12-2026**.

1. Find alle **grænser** i prisreglerne.
2. Skriv acceptkriterier i formatet *Givet – når – så* med **konkrete datoer og beløb**, så hver
   grænse er dækket på begge sider, og så alle tre billettyper er med.
3. Hvor mange acceptkriterier fik I? Hvilke kunne I undvære, uden at en fejl ved en grænse kunne
   slippe igennem?
4. Hvad er prisen på en studiebillet købt 10 eller flere dage før? Regn den ud. Hvad skriver I, hvis
   rabatten havde givet et beløb med øre?

## Opgave 4 – Det, der går galt

Skriv mindst **fire acceptkriterier for fejltilfælde** til denne user story:

> *Som billetsælger vil jeg kunne sælge en billet til et event, så kunden kan komme ind.*

Tænk på: et event, der ikke findes; et event, der er udsolgt; en dørbillet, der ikke sælges på
dagen; et event, der har været; en studiebillet uden studiekort-id. Skriv, hvad **brugeren**
oplever: hvilken besked, og hvad der **ikke** sker.

## Opgave 5 – Del en epic op

Denne user story er alt for stor:

> *Som billetsælger vil jeg kunne sælge alle slags billetter efter husets regler, så vi får solgt
> billetter.*

1. Del den op i **mindst fire** user stories. Hver skal kunne laves for sig og give billetsælgeren
   noget, hun ikke kunne før.
2. Skriv, **efter hvad** I har delt (regler, det enkle først, glad vej først, ...).
3. En gruppe foreslår at dele den i *"Lav UI til salg"*, *"Lav Ticket-klassen"* og *"Gem billetter
   i fil"*. Hvad er problemet? Hvor hører de tre ting hjemme i stedet?
4. Skriv opgave-tjeklisten for **én** af jeres nye stories.

## Opgave 6 – INVEST-tjek

Vurdér hver story med INVEST. Hvilket bogstav fejler den på, hvis nogen? Ret den, der fejler.

| # | User story |
|---|---|
| a | Som programchef vil jeg kunne se antallet af solgte billetter pr. event, så jeg kan se, hvad der sælger. |
| b | Som billetsælger vil jeg kunne sælge billetter, der gemmes med `PrintStream` i `tickets.csv`, så de ikke går tabt. |
| c | Som programchef vil jeg have, at programmet ser professionelt ud, så huset får et godt image. |
| d | Som billetsælger vil jeg kunne sælge en studiebillet med studiekort-id, så studerende kan få rabat. |
| e | Som programchef vil jeg kunne planlægge hele næste sæson med kunstnere, sale, priser, markedsføring og budget, så huset kan planlægge. |

## Opgave 7 – Prioritér en backlog

Her er ti user stories til Kulturhuset, i tilfældig rækkefølge. Estimaterne er givet.

| Id | User story (forkortet) | Størrelse |
|---|---|---|
| A | Programchefen kan se indtægt pr. event | S |
| B | Billetsælgeren kan sælge en dørbillet | S |
| C | Programchefen kan oprette et event | M |
| D | Salg afvises, når eventet er udsolgt | S |
| E | Events og billetter er der stadig efter genstart | M |
| F | Billetsælgeren kan sælge forsalgsbilletter med og uden rabat | M |
| G | Programchefen kan rette et event | M |
| H | Programchefen kan se listen over kommende events | S |
| I | Billetsælgeren kan sælge en studiebillet | S |
| J | Programchefen kan se kommende events sorteret efter dato | S |

1. Sortér dem, så det vigtigste står øverst. Skriv en begrundelse for de fem øverste.
2. Tegn afhængighederne: hvilke stories kan ikke laves, før en anden er færdig?
3. Gruppen har plads til **ca. 6 dages arbejde** i første sprint (S = ½ dag, M = 1 dag). Hvilke
   stories tager I med? Hvad er jeres **sprintmål** i én sætning?
4. Sammenlign med et andet par. Hvor er I uenige, og hvilke argumenter vinder?

## Opgave 8 – Planning poker

Brug planning poker på stories fra opgave 2 eller 5. Lav kort med **S**, **M** og **L** (eller
brug fingrene: 1, 2, 3).

1. Læs storyen og acceptkriterierne højt.
2. Alle vælger et kort i hemmelighed og vender på samme tid.
3. Er I uenige, forklarer den højeste og den laveste hvorfor. Stem igen.

Skriv ned, hvad der fik jer til at ændre mening. Var det noget, der manglede i acceptkriterierne?

---

## Udfordring 1 – Studentercaféen

> *En studentercafé åbner i stueetagen. Caféen vil have en app, hvor kunderne kan se dagens og
> ugens menu, forudbestille mad og drikke til et bestemt tidspunkt, få særlige tilbud og optjene
> loyalitetspoint. Personalet skal kunne lægge menuen ind, se dagens forudbestillinger og markere
> dem som hentet.*

1. Find **fem epics**.
2. Skriv mindst **tre user stories** under hver epic.
3. Skriv acceptkriterier til de tre stories, I ville lave først, og begrund, hvorfor lige dem.

## Udfordring 2 – Fra acceptkriterium til test

Tag jeres acceptkriterier fra opgave 3. Skriv for hvert, hvordan en JUnit-test af det ville se ud:
testens navn, og hvad der står i `assertEquals`. Brug `calculatePrice(TicketType type, LocalDate
purchaseDate)` på klassen `Event`, som i
[designklassediagrammet fra i går](../01_man_2026-11-16/README.md#domænemodel-eller-designklassediagram).
Hvorfor er det vigtigt, at købsdatoen er en **parameter**, og at metoden ikke selv kalder
`LocalDate.now()`?
