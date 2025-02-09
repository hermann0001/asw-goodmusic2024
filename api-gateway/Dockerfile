# Usa un'immagine base con Java (ad esempio OpenJDK 17)
FROM openjdk:21-jdk-slim

# Imposta la directory di lavoro dentro il container
WORKDIR /app

# Copia il file .jar del servizio "api-gateway" all'interno del container
COPY build/libs/api-gateway.jar app.jar

# Espone la porta 8083 per l'API Gateway
EXPOSE 8080

# Esegui l'API Gateway quando il container parte
CMD ["java", "-Xms64m", "-Xmx128m", "-jar", "app.jar"]
