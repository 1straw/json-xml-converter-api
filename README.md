# XML/JSON Converter API

Detta är en enkel REST-baserad Java Spring Boot-applikation som konverterar mellan JSON och XML, både via direkt POST-data och genom att hämta data från externa URL\:er.

## Funktionalitet

Applikationen erbjuder fyra huvudsakliga endpoints:

| Metod | Endpoint                                 | Beskrivning                                       |
| ----- | ---------------------------------------- | ------------------------------------------------- |
| POST  | /api/convert/json-to-xml                 | Konverterar JSON-sträng till XML.                 |
| POST  | /api/convert/xml-to-json                 | Konverterar XML-sträng till JSON.                 |
| GET   | /api/convert/fetch-json-to-xml?url={url} | Hämtar JSON från en URL och konverterar till XML. |
| GET   | /api/convert/fetch-xml-to-json?url={url} | Hämtar XML från en URL och konverterar till JSON. |

## Exempel

### Konvertera JSON till XML

curl -X POST [http://localhost:8080/api/convert/json-to-xml](http://localhost:8080/api/convert/json-to-xml)&#x20;
-H "Content-Type: application/json"&#x20;
-d '{"person": {"name": "Alice", "age": 30}}'

### Konvertera XML till JSON

curl -X POST [http://localhost:8080/api/convert/xml-to-json](http://localhost:8080/api/convert/xml-to-json)&#x20;
-H "Content-Type: application/xml"&#x20;
-d '<person><name>Alice</name><age>30</age></person>'

### Konvertera JSON från URL

curl "[http://localhost:8080/api/convert/fetch-json-to-xml?url=https://example.com/data.json](http://localhost:8080/api/convert/fetch-json-to-xml?url=https://example.com/data.json)"

## Felhantering

Om JSON eller XML är ogiltigt, eller om data inte kan hämtas från angiven URL, returneras ett felmeddelande i respektive format:

* XML-konverteringsfel → {"error": "Felmeddelande"}
* JSON-konverteringsfel → <error>Felmeddelande</error>

## Swagger UI

API-dokumentation finns tillgänglig via Swagger UI när du kör applikationen:
```
http://localhost:8080/swagger-ui.html
```
## Teknologier

* Java 21 corretto
* Spring Boot
* Jackson (ObjectMapper / XmlMapper)
* Swagger (OpenAPI 3 via springdoc-openapi)
* Lombok
* SLF4J för loggning

## Tester

(Valfritt tillägg: Beskriv om du har enhetstester eller hur man kör tester.)

## Säkerhet

(Valfritt tillägg: Om du har API-nyckelbaserad säkerhet för Swagger eller endpoints.)

## Bygg & Kör

./mvnw clean install
./mvnw spring-boot\:run

## Projektstruktur (översikt)

se.datakonvertering
├── config              # Swagger-konfiguration
├── controller          # REST-controller med konverteringsendpoints
├── exceptions          # Anpassade undantag & global felhantering
├── service             # Konverteringslogik

