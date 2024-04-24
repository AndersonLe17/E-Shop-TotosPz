# Build stage
FROM maven:3-openjdk-17 AS build

COPY src /app/src

COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package -DskipTests -P dev

# Package stage
FROM openjdk:17-jdk-alpine

ARG JAR_FILE=/app/target/eshop-0.0.1-SNAPSHOT.jar

COPY --from=build $JAR_FILE /app/totospizza-eshop-app.jar

ENTRYPOINT ["java", "-jar", "/app/totospizza-eshop-app.jar"]