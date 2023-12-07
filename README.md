# Rijksmuseum Quiz Applicatie

Dit project omvat een individueel project als afsluiting van het software engineering trainee traject bij Sogyo. Het gaat om een persoonlijk gekozen project-onderwerp en -doel, met individueel vastgelegde persoonlijke leerdoelen. Dit project is niet officieel verbonden aan het Rijksmuseum.



## Doelstelling van project

Het doel voor het project is om een web applicatie samen te stellen die verbinding maakt met de openbare API van het Rijksmuseum, om informatie en data in te halen over verschillende kunstwerken in de Rijksmuseum collectie. Op basis van deze data worden vragen gegenereerd die aan de gebruiker gesteld worden om hun kennis van kunst en kunstgeschiedenis op de proef te stellen. Deze vragen zullen veelal visueel zijn, en hiertoe gebruik maken van afbeeldingen die deel zijn van de data die het Rijksmuseum beschikbaar stelt.

Vanuit een design oogpunt is het doel om een gebruiksvriendelijke en aangename applicatie te maken, die zorgt voor een leuke user experience. Hiertoe zal het belangrijk zijn om niet alleen een back-end te hebben die succesvol vragen kan genereren, en gebruiksinput kan verwerken, maar ook een goed design voor de front-end, zodat de eindgebruiker in staat is om op aangename wijze de applicatie te gebruiken en ervaren.


***

## Software Stack

Het domein van de applicatie zal worden geprogrammeerd in Java, en specifiek zal Spring Boot worden gebruikt voor de back-end architectuur.

De database waarin gebruikersgegevens, zoals scores, worden opgeslagen zal gebruik maken van een relationele database in MySQL.

De front-end interface zal worden gemaakt als een Vite server, met React als TypeScript framework, en Tailwind.css als visueel framework.

Als build tool voor de back-end zal Gradle worden gebruikt.

Als build tool voor de front-end zal Node worden gebruikt.

## Persoonlijke leerdoelen

- Overzicht houden over een volledige full-stack applicatie

- Vragen stellen aan collega's en Academy wanneer ik er niet uit kom, en dat ook toegeven als dat zo is

- Werken met vertical slices en minimum viable products

- Focussen op het leerproces boven een 'perfect' product

## Technische leerdoelen

- Leren werken met Spring Boot als nieuw framework

- Verdiepen in React en Tailwind voor front-end, om echt te begrijpen hoe het werkt

- Leren werken met een externe API, en hoe die aan te roepen

- Verkennen van verschillende libraries en mogelijkheden binnen Java en TypeScript, en hun werking doorgronden

***

## Back-end

De back-end server gebruikt een Java API via Boostrap, en gebruikt Gradle als build tool. De server kan worden gestart als volgt:

```bash
# Build the project
./gradlew build
# Run the Jetty server
./gradlew run
```

De back-end server luistert op `http://localhost:8080`

## Front-end

De front-end server is gebouwd als Vite server met NPM, en maakt gebruik van React en TypeScript. De server kan worden gestart als volgt:

```bash
# Install dependencies for project
cd client
npm install
# Start the front-end server
npm run dev
```

De front-end server luistert op `http://localhost:5173`

## Database

De persistence laag van de applicatie waarmee gebruikers en hun score worden opgeslagen is opgezet om contact te maken met een (lokale) MySQL database. Deze database kan als SQL script automatisch worden opgezet via het script dat [hier](SQLDatabaseInitialisationScript.md) te vinden is.

De URL voor deze lokale database dient vervolgens ook met gebruiksnaam en wachtwoord te worden ingevuld op de aangegeven plaatsen in [UserRepositoryDatabase.java](persistence/src/main/java/ip/rijksmuseumquiz/persistence/UserRepositoryDatabase.java).
