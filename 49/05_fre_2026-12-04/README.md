# Delfinen – sprint 2: kontingent, restance og datoer

## Beskrivelse

Projektdag i sprint 2. Dagens kvalitetstema er **kassererens del**: kontingent, forventet indtægt
og restancelisten ([F5–F8](../../projekter/delfinen/readme.md#funktionelle-krav)).

De fleste grupper har lavet kontingentberegningen i sprint 1 og testet den efter
[23-11](../../48/01_man_2026-11-23/README.md). Men det er også den del, hvor de
fleste **små fejl** gemmer sig: en alder, der er ét år forkert i månederne op til fødselsdagen, `>` i stedet for
`>=` ved 60 år, eller en test, der er grøn i dag og rød om et halvt år. Og det er den del, som
projektbeskrivelsen kræver unit tests af.

Vi viser teknikken på et **andet** eksempel – en musikskole – så I selv skal oversætte den til
Delfinens regler.

## Læringsmål

Når du har arbejdet med dagens materiale, skal du kunne:

* regne en alder ud i hele år fra en fødselsdato med `LocalDate` og `Period`
* forklare, hvorfor man gemmer fødselsdatoen og ikke alderen
* skrive en beregning, der tager **datoen som parameter**, så den kan testes
* teste grænseværdier **på** fødselsdagen og **dagen før**
* lave en restanceliste og en forventet indtægt, der **returnerer** data i stedet for at udskrive
  dem

## Se disse videoer før undervisningen:

Til genopfriskning af datoer:

* [dates & times](https://www.youtube.com/watch?v=xTtL8E4LzTQ&list=PLEeqf0uSZqXsz7oU2U-VAxhQZ021PRVnd&t=10h11m42s)
  (til: 10:20:24)

Og læs [Det skal I bruge fra LocalDate](../../projekter/filmsamling/del-5-datoer.md#det-skal-i-bruge-fra-localdate)
i Filmsamling igen.

## Læs nedenstående før undervisningen

---

### Eksemplet: musikskolen

Musikskolen Tonen har disse takster for et års undervisning:

| Elev | Takst |
|---|---|
| under 13 år | 1800 kr. |
| 13 til og med 24 år | 2400 kr. |
| 25 år og derover | 3000 kr. |
| 67 år og derover | 20 % rabat af 3000 kr. = 2400 kr. |

Grænserne gælder fra og med fødselsdagen: man betaler 2400 kr. fra sin 13-års fødselsdag.

---

### Alder: regn den ud, gem den ikke

```java
import java.time.LocalDate;
import java.time.Period;

public class Student {

    private static final int CHILD_FEE = 1800;
    private static final int YOUTH_FEE = 2400;
    private static final int ADULT_FEE = 3000;

    private String name;
    private LocalDate birthDate;
    private boolean hasPaid;          // en ny elev har ikke betalt endnu

    public Student(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    // Alder i hele år på en bestemt dato
    public int getAge(LocalDate date) {
        return Period.between(birthDate, date).getYears();
    }

    // ...
}
```

`Period.between(fra, til)` giver perioden mellem to datoer i år, måneder og dage, og `getYears()`
giver de hele år. Det er præcis det, vi mener med alder. Se
[Period](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/Period.html) i Javas
dokumentation.

> Den [23-11](../../48/01_man_2026-11-23/README.md#alder-og-grænser-i-delfinen) brugte vi
> `ChronoUnit.YEARS.between(birthDate, date)`. Den giver præcis det samme – hele år – også for
> dem, der er født den 29. februar. Den eneste forskel er, at den returnerer en `long`. Brug den,
> I allerede har, og test den.

Hvorfor ikke bare gemme alderen? Fordi den kun passer **indtil næste fødselsdag**. En elev, der
blev oprettet som 12-årig i september, er stadig 12 i filen i marts – selvom hun fyldte 13 i
januar og skal betale mere. Fødselsdatoen ændrer sig aldrig.

#### En fejl, der er let at lave

```java
int age = date.getYear() - birthDate.getYear();     // FORKERT
```

En elev, der er født 14-12-2008, er **17** år den 04-12-2026. Men `2026 - 2008` er 18. Linjen
ignorerer, om eleven har haft fødselsdag i år. Den giver kun det rigtige svar, når eleven allerede
har haft fødselsdag i år – og det er det, der gør fejlen svær at opdage.

---

### Beregningen tager datoen som parameter

```java
public int calculateFee(LocalDate date) {
    int age = getAge(date);

    if (age < 13) {
        return CHILD_FEE;
    }
    if (age < 25) {
        return YOUTH_FEE;
    }
    if (age >= 67) {
        return ADULT_FEE * 80 / 100;      // 20 % pensionistrabat
    }
    return ADULT_FEE;
}
```

To ting at lægge mærke til:

* **Rækkefølgen af `if`'erne betyder noget.** Hver `return` afslutter metoden, så når vi kommer til
  `age >= 67`, ved vi allerede, at eleven er mindst 25. Tegn gerne et
  [aktivitetsdiagram](../../38/01_man_2026-09-14/README.md), hvis I er i tvivl om jeres egen
  rækkefølge.
* **`ADULT_FEE * 80 / 100`** i stedet for `ADULT_FEE * 0.8`. Med heltal undgår vi decimaler –
  kontingentet er et helt antal kroner. Gang først, og dividér bagefter – ellers smider
  heltalsdivisionen decimalerne væk for tidligt. Prøv selv at regne ud, hvad `ADULT_FEE / 100 * 80`
  og `ADULT_FEE * 80 / 100` giver, hvis taksten var 3050.

Og den vigtigste: metoden kalder **ikke** `LocalDate.now()`. Den, der kalder metoden, bestemmer
datoen. Brugerfladen sender `LocalDate.now()` med – testen sender en fast dato.

#### Hvorfor ikke bare LocalDate.now()?

Forestil jer denne test, skrevet i dag:

```java
// FORKERT: testen afhænger af, hvilken dag den bliver kørt
@Test
void childFee() {
    Student student = new Student("Test", LocalDate.of(2013, 12, 10));
    assertEquals(1800, student.calculateFee(LocalDate.now()));
}
```

Den er grøn i dag, 04-12-2026, hvor eleven er 12. Den **10-12-2026** bliver den rød, fordi eleven
fylder 13 – uden at nogen har rørt koden. Afleverer I 08-12, og reviewer en anden gruppe koden
11-12, er jeres test rød til peer reviewet.

---

### Test grænserne – på dagen og dagen før

En test med en 10-årig og en 40-årig fanger ikke, om I har skrevet `<` eller `<=`. Det gør en test
**på** fødselsdagen og **dagen før**:

```java
// Eleven fylder 13 den 10-12-2026
private final Student student = new Student("Test", LocalDate.of(2013, 12, 10));

@Test
void childFeeDayBefore13thBirthday() {
    assertEquals(1800, student.calculateFee(LocalDate.of(2026, 12, 9)));
}

@Test
void youthFeeOn13thBirthday() {
    assertEquals(2400, student.calculateFee(LocalDate.of(2026, 12, 10)));
}

@Test
void pensionerDiscountFrom67thBirthday() {
    Student pensioner = new Student("Pensioner", LocalDate.of(1959, 12, 4));
    assertEquals(3000, pensioner.calculateFee(LocalDate.of(2026, 12, 3)));
    assertEquals(2400, pensioner.calculateFee(LocalDate.of(2026, 12, 4)));
}
```

For musikskolen giver det tre grænser med to tests hver – plus én test for hver takst.

**I Delfinen** er der fire takster og grænser ved **18** og **60** år, og passive medlemmer betaler
altid 500 kr. Skriv listen over tests op i gruppen, før I skriver dem. Hvor mange bliver det? Har I
husket en passiv på 65?

> **Skudår.** En elev født 29-02-2012 fylder 13 den 01-03-2025 – både ifølge `Period` og
> `ChronoUnit.YEARS.between`. Den 28-02-2025 er hun stadig 12. Det er et grænsetilfælde, I ikke
> behøver teste – men det viser, at "hvornår fylder man år?" ikke altid er et teknisk spørgsmål.
> Vil kunden hellere have, at hun fylder år den 28. februar, skal kunden sige det.

---

### Kassererens overblik

```java
public class MusicSchool {

    private ArrayList<Student> students = new ArrayList<>();

    public int calculateExpectedIncome(LocalDate date) {
        int total = 0;
        for (Student student : students) {
            total += student.calculateFee(date);
        }
        return total;
    }

    public ArrayList<Student> getStudentsInArrears() {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (!student.hasPaid()) {
                result.add(student);
            }
        }
        return result;
    }
}
```

* **Forventet indtægt** er summen af **alle** elevers takst – betalt eller ej. Det er den samme
  regel som i [Delfinen](../../projekter/delfinen/readme.md#reglerne-gjort-præcise).
* **Restancelisten** returnerer en liste. Den skriver ikke noget ud. Brugerfladen løber listen
  igennem og viser navn og beløb – og beløbet hentes med `calculateFee(date)`, så det altid passer
  med taksten.
* Begge metoder ligger i den klasse, der har **alle** eleverne. Hver elev ved selv, hvad hun skal
  betale, og om hun har betalt.

Testene er korte, fordi metoderne returnerer noget:

```java
@Test
void onlyUnpaidStudentsAreInArrears() {
    MusicSchool school = new MusicSchool();
    Student paid = new Student("Paid", LocalDate.of(2000, 1, 1));
    Student unpaid = new Student("Unpaid", LocalDate.of(2000, 1, 1));
    school.addStudent(paid);
    school.addStudent(unpaid);
    paid.registerPayment();

    assertEquals(1, school.getStudentsInArrears().size());
    assertEquals("Unpaid", school.getStudentsInArrears().get(0).getName());
}
```

Test også den tomme skole: ingen indtægt, ingen restance – og ingen exception.

---

### Tjekliste for dagen

- [ ] Fødselsdatoen gemmes – alder og junior/senior **regnes ud**
- [ ] Kontingentberegningen tager datoen som parameter
- [ ] Der er tests af alle fire takster: 1000, 1600, 1200 og 500
- [ ] Grænserne ved 18 og 60 år er testet på fødselsdagen **og** dagen før
- [ ] En passiv over 60 betaler 500 – og det er testet
- [ ] Ingen test bruger `LocalDate.now()`
- [ ] Forventet indtægt tæller **alle** medlemmer med
- [ ] Restancelisten viser medlemsnummer, navn og beløb – og betalte medlemmer er ikke med
- [ ] En betaling bliver gemt og er der stadig, når programmet startes igen
- [ ] Alt er merget til `main`

---

## Det vigtigste at tage med

* gem **fødselsdatoen**, regn alderen ud: `Period.between(birthDate, date).getYears()`
* `date.getYear() - birthDate.getYear()` er forkert, indtil man har haft fødselsdag i år
* beregninger tager **datoen som parameter** – så kan testene bestemme datoen
* test grænserne **på** fødselsdagen og **dagen før**
* rækkefølgen af `if`'erne med `return` betyder noget
* overblik og lister **returneres** – brugerfladen udskriver dem

## Aktiviteter i undervisningen

### 1. Daily stand-up

Foran boardet. Hvor langt er I med sprintmålet? Er der noget, der skal skæres fra?

### 2. Test-listen

Skriv i gruppen alle de tests op, kontingentberegningen skal have – takster og grænser. Sammenlign
med de tests, I har. Skriv dem, der mangler.

### 3. Sprint 2

Arbejd videre på sprint backloggen. Gå [tjeklisten](#tjekliste-for-dagen) igennem sidst på dagen,
og merge til `main`.
