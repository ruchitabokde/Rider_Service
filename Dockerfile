FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/rider-service-0.0.1-SNAPSHOT.jar app.jar
COPY ride_riders.csv ./ride_riders.csv

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
