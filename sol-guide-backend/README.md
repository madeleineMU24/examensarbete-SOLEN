### SÖLEN - Backend
*Sol-guide för uteserveringen*

---
**Beskrivning**<br>

Backend-delen av Sölen ansvarar för logiken bakom applikationen, inklusive beräkning av solens position och hantering av
API-förfrågningar från frontend.

Systemet är utvecklat i Java med hjälp av Spring Boot, och projektet byggs och hanteras med Maven. Backend tillhandahåller
ett REST-baserat API som frontend använder för att hämta information om solens position i förhållande till olika uteserveringar.

Genom att kombinera geografiska koordinater med tidsbaserade solpositionsberäkningar kan backend avgöra om solen når en
specifik plats vid ett visst klockslag.

Eftersom projektet fortfarande befinner sig i en betafas tas i nuläget inte hänsyn till byggnader eller andra objekt som
kan skapa skuggor.

---

**Teknik**<br>

- Java
- Spring Boot
- REST API
- Maven
- JWT (JSON Web Token) för autentisering och skydd av admin-funktioner

---
**Starta projektet**<br>

*Krav*
- Java 17
- Maven
- MySQL

**Databas**

Projektet använder MySQL för lagring av data. Backend kommunicerar med databasen via Spring Boot.

Databasanslutningen konfigureras i:

[application.properties](src/main/resources/application.properties)

I konfigurationen används miljövariabler för databasuppgifter:
```
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
jwt.secret=${JWT_SECRET}
```

För att köra projektet behöver följande miljövariabler sättas lokalt:
```
DB_URL
DB_USERNAME
DB_PASSWORD
JWT_SECRET
```

**Kör projektet:** öppna [SolGuideApplication](src/main/java/com/example/sol_guide/SolGuideApplication.java)
och kör `main()`-metoden

---

**Exempel på endpoints:**

| COMMAND | ENDPOINTS         | OPERATIONER                      | EXTRA                       |
|---------|-------------------|----------------------------------|-----------------------------|
| POST    | /restaurant       | Lägger till ny restaurang        | Kräver "ADMIN"              |
| GET     | /restaurant       | Hämtar alla restauranger         | -                           |
| GET     | /restaurant/sun   | Hämtar bara restauranger med sol | -                           |
| GET     | /restaurant/sunny | Hämtar restauranger med sol      | Välj att inkludera skuggiga |
| GET     | /restaurant/{id}  | Hämtar restaurang med valt id    | -                           |
| DELETE  | /restaurant/{id}  | Radera restaurang med valt id    | Kräver "ADMIN"              |
| POST    | /auth/login       | Genererar JWT-token för admin    | -                           |


---

**Lathund för restaurang data**

Förberedd data så det är lättare att starta programmet utan behöva hitta på egen.

<details>
<summary>Restaurang-data (Klicka för att visa)</summary>

    {
        "name": "MamaWolf",
        "latitude": 59.31770352291398,
        "longitude": 18.059945538044744,
        "deckDirection": 0.0,
        "deckWidth": 180.0
    },
    {
        "name": "Falloumi",
        "latitude": 59.30819506331677,
        "longitude": 18.077776628454423,
        "deckDirection": 180.0,
        "deckWidth": 180.0
    },
    {
        "name": "Hobo Hotel",
        "latitude": 59.33082435995828,
        "longitude": 18.06686813176458,
        "deckDirection": 270.0,
        "deckWidth": 180.0,
    },
    {
        "name": "Waan Thai",
        "latitude": 59.34177776499803,
        "longitude": 18.038451761988465,
        "deckDirection": 315.0,
        "deckWidth": 180.0,
    },
    {
        "name": "Scandwich",
        "latitude": 59.3332566816448,
        "longitude": 18.06598869815202,
        "deckDirection": 90.0,
        "deckWidth": 180.0,
    },
    {
        "name": "TAK",
        "latitude": 59.33073207682604,
        "longitude": 18.06693046636627,
        "deckDirection": 180.0,
        "deckWidth": 270.0,
    },
    {
        "name": "Snaps",
        "latitude":  59.315431129344816,
        "longitude":  18.0724528379134,
        "deckDirection": 135,
        "deckWidth": 200,
    },
    {
        "name": ”Bistro Bananas”,
        "latitude":  59.311820802399005,
        "longitude":  18.07538080493043,
        "deckDirection”: 45,
        "deckWidth”: 145,
    },
    {
        "name": ”Häktet”,
        "latitude":  59.31819465443313, 
        "longitude":  18.054588326603785,
        "deckDirection": 180,
        "deckWidth”: 80,
    },
    {
        "name": ”Ölstugan”,
        "latitude":  59.31514497947498,
        "longitude":  18.03485135861848,
        "deckDirection”: 270,
        "deckWidth”: 200,
    },
    {
        "name": ”Pelago”,
        "latitude":  59.31943905891413,
        "longitude":  18.073342562873385,
        "deckDirection": 315,
        "deckWidth”: 320,
    },
    {
        "name": ”Restaurang Ugglan”,
        "latitude":  59.3064768130665,
        "longitude":  18.006451701838945,
        "deckDirection": 180,
        "deckWidth”: 180,
    },
    {
        "name": ”Plåtparken”,
        "latitude":  59.309463844100854,
        "longitude":  18.031083008143632,
        "deckDirection": 135,
        "deckWidth”: 200,
    },
    {
        "name": ”The Old Brewer”,
        "latitude":  59.31825624505065,
        "longitude":  18.03262882495899,
        "deckDirection": 260,
        "deckWidth”: 180,
    },
    {
        "name": ”Old Beefeater Inn”,
        "latitude":  59.3185682212711,
        "longitude":  18.07127467579737,
        "deckDirection": 280,
        "deckWidth”: 90,
    }

</details>

---

**Projektstruktur**
````
src/
└─ main
   ├─ java/com/example/sol_guide/    
   │       ├─ controller/
   │       ├─ service/
   │       ├─ repository/
   │       ├─ model/
   │       ├─ security/
   │       └─ utils/
   └─ resources/
        └─ application.properties
````