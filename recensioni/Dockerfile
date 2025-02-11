FROM openjdk:21-jdk-slim

WORKDIR /app

ADD build/libs/recensioni.jar app.jar

ENTRYPOINT ["java", "-Xms64m", "-Xmx128m", "-jar", "app.jar"]
