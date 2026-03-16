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
```

För att köra projektet behöver följande miljövariabler sättas lokalt:
```
DB_URL
DB_USERNAME
DB_PASSWORD
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