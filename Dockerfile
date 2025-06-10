FROM amazoncorretto:21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package

FROM amazoncorretto:21 AS runtime

WORKDIR /app

COPY target/DataKonvertering-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

# 🔹 Kör applikationen
CMD ["java", "-jar", "/app/app.jar"]