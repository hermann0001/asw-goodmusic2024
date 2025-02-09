# Usa un'immagine base con Java (ad esempio OpenJDK 17)
FROM openjdk:21-jdk-slim

# Imposta la directory di lavoro dentro il container
WORKDIR /app

# Copia il file .jar del servizio "connessioni" all'interno del container
COPY build/libs/connessioni.jar app.jar

# Espone la porta 8081 per il servizio "connessioni"
EXPOSE 8080

# Esegui il servizio Java quando il container parte
CMD ["java", "-Xms64m", "-Xmx128m", "-jar", "app.jar"]
