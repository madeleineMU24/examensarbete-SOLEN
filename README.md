### Examensarbete SÖLEN 
*Sol-guiden för uteserveringen*

----


**Översikt**<br>
**Sölen** är en beta-version av en webbaserad applikation som gör det möjligt att se om solen når en specifik uteservering 
vid en viss tidpunkt. Syftet med applikationen är att hjälpa användare att hitta soliga platser att sitta på genom att 
visualisera solens position i förhållande till olika uteserveringar.

Användaren kan antingen se om solen träffar en uteservering i realtid, eller söka på ett specifikt klockslag för att 
kontrollera om platsen ligger i sol vid den tiden.

I denna betaversion baseras beräkningarna enbart på solens position och geografiska koordinater. Byggnader och andra 
objekt som kan skapa skugga tas ännu inte med i beräkningen, vilket innebär att resultaten ska ses som en uppskattning.

Projektet består av en backend som hanterar beräkningar och API-kommunikation, samt en frontend som presenterar 
informationen i ett användarvänligt gränssnitt.

----

**Projektstuktur**
```
Examensarbete
            ├──  Sol-Guide-Backend -> Server, API och databaslogik
            └──  Sol-Guide-Frontend -> Användargränssnitt
```

----

**Tekniker**

| Backend             | Frontend     |
|---------------------|--------------|
| • Java              | • JavaScript |
| • SpringBoot        | • React      |
| • Maven             | • Node.js    |
| • MySQL             | • HTML       |
| • JWT-autentisering | • CSS        |

----

**Installation**

*1. Klona projektet*
```bash 
   git clone https://github.com/madeleineMU24/examensarbete-SOLEN.git
   ```
*2. Starta backend*<br>
Se [backend-README](sol-guide-backend/README.md)<br>

*3. Starta frontend*<br>
Se [frontend-README](sol-guide-frontend/README.md)

---

**Funktioner**

- Kontrollera om solen når en specifik uteservering i realtid
- Sök på ett specifikt klockslag för att se om en uteservering ligger i sol
- Visualisering av solens position baserat på geografiska koordinater
- Webbaserat gränssnitt för enkel användning
- REST API som kopplar frontend och backend

---

**Författare**

Projektet utvecklades av **Madeleine Thunholm** som examensarbete inom utbildningen **Mjukvaruutvecklare** vid **Folkuniversitet**

**UML-diagram** finns här för att titta och läsa: [UML-diagram](./docs/uml-diagram.svg)