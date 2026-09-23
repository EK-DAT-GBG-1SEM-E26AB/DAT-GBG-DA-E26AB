# Projektarbejde, sprint 1: test af beregningerne

## Beskrivelse

Dagens undervisning er afsat til [Delfinen](../../projekter/delfinen/readme.md), sprint 1.

Dagens tema er **unit test af beregningerne**. Delfinen har regler med tal og grænser: kontingent
efter alder og aktivitetsform, rabat fra 60 år, junior og senior ved 18 år. Den slags er præcis
det, man **ikke** kan nøjes med at afprøve ved at køre programmet et par gange. Projektet kræver
JUnit 5-tests af **mindst kontingentberegningen, med alle fire takster og grænserne ved 18 og 60
år** (se [ikke-funktionelle krav](../../projekter/delfinen/readme.md#ikke-funktionelle-krav)), og
Definition of Done kræver tests, hvor der er beregninger.

I lærte JUnit i [Filmsamling del 5](../../projekter/filmsamling/del-5-test.md). I dag
genopfrisker vi det og tilføjer to ting, der er vigtige i Delfinen: **datoen som parameter** og
**test af grænser**. Eksemplet er igen Kulturhuset: billetpriser, der afhænger af, hvor mange dage
før eventet man køber.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* skrive JUnit 5-tests med **Arrange – Act – Assert** og `@BeforeEach`
* forklare, hvorfor en metode, der regner med datoer, skal have **datoen som parameter**
* finde **grænserne** i en regel og teste på **begge sider** af hver grænse
* teste, at ugyldige tilfælde bliver **afvist** med `assertThrows`
* oversætte **acceptkriterier** til tests
* køre **alle** tests, før du merger

## Se disse videoer før undervisningen:

Ingen ny video. Genlæs i stedet [Filmsamling del 5 – Test](../../projekter/filmsamling/del-5-test.md),
især *Arrange – Act – Assert* og *Hvilke tilfælde skal testes?*

## Læs nedenstående før undervisningen

---

### Datoen er en parameter

Prisen på en billet til Kulturhuset afhænger af, hvor mange dage før eventet den købes. Man kunne
skrive metoden sådan her:

```java
// Sådan skal det IKKE gøres: resultatet afhænger af, hvilken dag metoden kaldes
public int calculatePrice(TicketType type) {
    long daysBefore = ChronoUnit.DAYS.between(LocalDate.now(), date);
    // ...
}
```

Så kan man ikke teste den. En test, der i dag siger *"10 dage før giver rabat"*, bliver rød i
morgen, fordi det så kun er 9 dage før. Ingen har rørt koden, men testen fejler.

Giv i stedet datoen med som **parameter**:

```java
public int calculatePrice(TicketType type, LocalDate purchaseDate) {
    long daysBefore = ChronoUnit.DAYS.between(purchaseDate, date);
    // ...
}
```

Programmet kalder den med `LocalDate.now()`, og testene kalder den med **faste datoer**. Det er det
samme tip, som står i Delfinens krav: `calculateFee(LocalDate date)`.

Her er hele metoden. Konstanterne gør reglerne lette at finde og rette:

```java
public class Event {

    private static final int DOOR_PRICE = 150;
    private static final int PRESALE_PRICE = 120;
    private static final int STUDENT_PRICE = 90;
    private static final int EARLY_DAYS = 10;           // så mange dage før eller mere giver rabat
    private static final int EARLY_DISCOUNT_PERCENT = 20;

    private int id;
    private String name;
    private LocalDate date;

    // constructor og gettere er udeladt her

    // Prisen afhænger af, hvornår billetten købes – derfor er købsdatoen en parameter
    public int calculatePrice(TicketType type, LocalDate purchaseDate) {
        long daysBefore = ChronoUnit.DAYS.between(purchaseDate, date);

        if (daysBefore < 0) {
            throw new IllegalArgumentException("Eventet har allerede været afholdt");
        }

        if (type == TicketType.DOOR) {
            if (daysBefore != 0) {
                throw new IllegalArgumentException("Dørbilletter sælges kun på dagen");
            }
            return DOOR_PRICE;
        }

        int price = PRESALE_PRICE;
        if (type == TicketType.STUDENT) {
            price = STUDENT_PRICE;
        }

        if (daysBefore >= EARLY_DAYS) {
            price = price * (100 - EARLY_DISCOUNT_PERCENT) / 100;
        }
        return price;
    }
}
```

Læg mærke til `price * (100 - EARLY_DISCOUNT_PERCENT) / 100`. Det er **heltalsregning**: 120 · 80 /
100 = 96. Skriver man `price * 0.8`, får man en `double`, og så skal man tage stilling til øre og
afrunding. Kontingenterne i Delfinen er hele kroner, så det samme trick virker dér.

---

### Find grænserne

Fejl gemmer sig ved grænserne. Skrev programmøren `>` eller `>=`? `<` eller `<=`? Derfor tester man
**på begge sider af hver grænse**, ikke bare et tilfældigt tal i midten.

| Grænse | Test lige før | Test lige efter |
|---|---|---|
| 10 dage før giver rabat | 9 dage før: 120 kr. | 10 dage før: 96 kr. |
| Dørbillet kun på dagen | dagen før: afvist | på dagen: 150 kr. |
| Efter eventet | på dagen: 120 kr. | dagen efter: afvist |

Med eventet torsdag 10-12-2026 er "9 dage før" tirsdag 01-12-2026 og "10 dage før" mandag
30-11-2026. **Regn datoerne ud i hånden, og skriv dem i testen.** Så kan man læse testen og se,
hvad den påstår.

Det er de samme tilfælde som acceptkriterierne fra
[17-11](../../47/02_tir_2026-11-17/loesninger.md#opgave-3--find-grænserne). Har I skrevet gode
acceptkriterier, har I allerede jeres testliste.

---

### Testene

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    private Event concert;

    @BeforeEach
    void setUp() {
        // Eventet ligger fast – så bliver testene aldrig røde, fordi tiden går
        concert = new Event(1, "Julekoncert", LocalDate.of(2026, 12, 10));
    }

    @Test
    void doorTicketOnTheDayCosts150() {
        int price = concert.calculatePrice(TicketType.DOOR, LocalDate.of(2026, 12, 10));

        assertEquals(150, price);
    }

    @Test
    void presaleNineDaysBeforeHasNoDiscount() {
        int price = concert.calculatePrice(TicketType.PRESALE, LocalDate.of(2026, 12, 1));

        assertEquals(120, price);
    }

    @Test
    void presaleTenDaysBeforeHasDiscount() {
        int price = concert.calculatePrice(TicketType.PRESALE, LocalDate.of(2026, 11, 30));

        assertEquals(96, price);
    }

    @Test
    void studentNineDaysBeforeHasNoDiscount() {
        assertEquals(90, concert.calculatePrice(TicketType.STUDENT, LocalDate.of(2026, 12, 1)));
    }

    @Test
    void studentTenDaysBeforeHasDiscount() {
        assertEquals(72, concert.calculatePrice(TicketType.STUDENT, LocalDate.of(2026, 11, 30)));
    }

    @Test
    void presaleOnTheDayHasNoDiscount() {
        assertEquals(120, concert.calculatePrice(TicketType.PRESALE, LocalDate.of(2026, 12, 10)));
    }

    @Test
    void doorTicketBeforeTheDayIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> concert.calculatePrice(TicketType.DOOR, LocalDate.of(2026, 12, 9)));
    }

    @Test
    void ticketAfterTheEventIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> concert.calculatePrice(TicketType.PRESALE, LocalDate.of(2026, 12, 11)));
    }
}
```

Otte tests, alle grønne. Læg mærke til:

* **Testnavnet siger, hvad der testes, og hvad der skal ske.** Bliver `presaleTenDaysBeforeHasDiscount`
  rød, ved man med det samme, hvor man skal lede.
* **Én ting pr. test.** Fejler en test, skal den kun kunne fejle af én grund.
* **`assertEquals(forventet, faktisk)`**, forventet først.
* **`assertThrows`** med en lambda tester, at ugyldige tilfælde bliver afvist, som i
  [Filmsamling del 6](../../projekter/filmsamling/del-6-exceptions.md#test-at-der-bliver-kastet).

> **Prøv at lave en fejl med vilje.** Ret `>=` til `>` i `daysBefore >= EARLY_DAYS`, og kør
> testene. To tests bliver røde: dem med præcis 10 dage. Det er dét, grænsetests er til: de
> fanger lige præcis den slags fejl, som man ikke ser ved at kigge på koden.

---

### Alder og grænser i Delfinen

I Delfinen er det **alderen**, der har grænser, og alderen regnes ud fra fødselsdatoen på en given
dag. Det gøres på samme måde som dagene ovenfor, bare i år:

```java
long age = ChronoUnit.YEARS.between(birthDate, date);
```

`ChronoUnit.YEARS.between` tæller **hele** år. For en, der er født 24-11-2008, giver den **17** den
23-11-2026 og **18** den 24-11-2026. Det er præcis grænsen fra
[Reglerne gjort præcise](../../projekter/delfinen/readme.md#reglerne-gjort-præcise): man er senior
**fra og med** sin 18-års fødselsdag.

Grænserne i Delfinen er altså **dagen før** og **på** fødselsdagen, både ved 18 og ved 60 år. Lav
en tabel som den ovenfor, før I skriver testene: hvilke medlemmer (fødselsdato, aktiv eller passiv)
og hvilken dato giver hvilket kontingent?

---

### Tests i arbejdsgangen

* **Skriv testen, mens du laver beregningen**, ikke til sidst i sprinten. Nogle skriver endda
  testen **først** ud fra acceptkriteriet og laver så koden, til testen bliver grøn.
* **Kør alle tests, når du har merget `main` ned** i din branch (trin 3 i tjeklisten fra 19-11).
  Højreklik på test-mappen → **Run 'All Tests'**. Det er her, I opdager, at en andens ændring har
  ødelagt jeres beregning.
* **Push aldrig til `main` med røde tests.** Definition of Done kræver, at `main` kan bygge og
  køre.
* **Find I en fejl, så skriv en test, der fanger den**, før I retter den. Så kommer den ikke igen.

---

## Det vigtigste at tage med

* metoder, der regner med datoer, får **datoen som parameter**; testene bruger **faste datoer**
* test på **begge sider af hver grænse**: 9 og 10 dage, 17 og 18 år, 59 og 60 år
* **regn de forventede værdier ud i hånden** og skriv dem i testen
* ét tilfælde pr. test, og et navn, der siger hvad
* `assertThrows` tester, at ugyldige tilfælde afvises
* **acceptkriterierne er jeres testliste**
* kør **alle** tests efter merge ned, og push aldrig røde tests til `main`

## Aktiviteter i undervisningen

### 1. Stand-up (15 min)

Hvor er I i forhold til sprintmålet? Hvilke stories er merget til `main`?

### 2. Kontingentet og dets tests

Er kontingentberegningen ikke lavet endnu, så er den et godt valg i dag. Er den lavet, så gennemgå
testene sammen:

1. Lav **grænsetabellen** for kontingentet: alle fire takster, 17/18 år og 59/60 år, med konkrete
   fødselsdatoer og en fast dato. Sammenlign med jeres acceptkriterier.
2. Tjek, at der er en test for **hver række** i tabellen.
3. Lav en fejl med vilje i beregningen (fx `>` i stedet for `>=`). Bliver en test rød? Hvis ikke,
   mangler der en test.

### 3. Arbejd videre på sprinten

Hvad der ellers står i jeres Sprint Backlog. Har I tid, så skriv også tests til restancelisten og
den samlede forventede indtægt, som projektbeskrivelsen foreslår.

### Tjekliste, før I går hjem

- [ ] Kontingentberegningen har tests for alle fire takster og grænserne ved 18 og 60 år.
- [ ] Alle tests er grønne på `main`.
- [ ] Alt, der ikke er færdigt, er committet og pushet på sin branch.
- [ ] Boardet passer.
