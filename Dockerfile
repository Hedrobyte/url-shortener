FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml /app
COPY src /app/src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
COPY --from=build /app/target/urlshortener-0.0.1-SNAPSHOT.jar /urlshortener.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "urlshortener.jar"]
