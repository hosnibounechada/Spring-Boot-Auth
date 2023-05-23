FROM openjdk:17-alpine
LABEL authors="Hosni Bounechada"
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=local", "/app.jar"]