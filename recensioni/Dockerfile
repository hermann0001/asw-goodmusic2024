# Usa un'immagine base con Java (ad esempio OpenJDK 17)
FROM openjdk:21-jdk-slim

# Imposta la directory di lavoro dentro il container
WORKDIR /app

# Copia il file .jar del servizio "recensioni" all'interno del container
COPY build/libs/recensioni.jar app.jar

# Espone la porta 8082 per il servizio "recensioni"
EXPOSE 8080

# Esegui il servizio Java quando il container parte
CMD ["java", "-Xms64m", "-Xmx128m", "-jar", "app.jar"]
