FROM openjdk:21-bookworm

ADD build/libs/recensioni.jar /recensioni.jar

ENTRYPOINT ["java", "-Xms64m", "-Xmx128m", "-jar", "/recensioni.jar"]
