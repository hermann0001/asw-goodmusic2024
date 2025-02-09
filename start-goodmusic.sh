#!/bin/bash

# Script per avviare l'applicazione GoodMusic con due database PostgreSQL

echo Running GOODMUSIC 

# Crea una rete Docker per i microservizi e i database
docker network create goodmusic-network

# Avvia il container per Consul
docker run -d --hostname localhost --name asw-consul --publish 8500:8500 --network goodmusic-network docker.io/hashicorp/consul

echo "Costruendo le immagini Docker per i microservizi..."

# Costruisci l'immagine docker per i microservizi
docker build -t recensioni ./recensioni
docker build -t connessioni ./connessioni
docker build -t recensioni-seguite ./recensioni-seguite
docker build -t api-gateway ./api-gateway

# Avvia i container PostgreSQL per i servizi "recensioni" e "connessioni"
docker run -d --name db-recensioni --hostname db-recensioni \
  --network goodmusic-network \
  --mount type=volume,src=recensioni-db-data,dst=/var/lib/postgresql/data \
  -e POSTGRES_USER=recensioni_user \
  -e POSTGRES_PASSWORD=recensioni_pass \
  -e POSTGRES_DB=recensioni_db \
  -p 5432:5432 postgres:latest   # La porta 5432 dell'host inoltra richiesta di servizio alla porta 5432 del container

docker run -d --name db-connessioni --hostname db-connessioni \
  --network goodmusic-network \
  --mount type=volume,src=connessioni-db-data,dst=/var/lib/postgresql/data \
  -e POSTGRES_USER=connessioni_user \
  -e POSTGRES_PASSWORD=connessioni_pass \
  -e POSTGRES_DB=connessioni_db \
  -p 5433:5432 postgres:latest   # La porta 5433 dell'host inoltra richiesta di servizio alla porta 5432 del container

# Attendi affinché i container siano pronti
until docker exec db-recensioni pg_isready -U recensioni_user && docker exec db-connessioni pg_isready -U connessioni_user; do
    echo "Aspettando che i database siano pronti..."
    sleep 5
done

echo "Avviando i microservizi Java nei container..."

# Avvia i microservizi Java e collegali alla rete Docker
docker run -d --name api-gateway --network goodmusic-network -p 8080:8080 api-gateway
docker run -d --name connessioni --network goodmusic-network -p 8081:8080 connessioni
docker run -d --name recensioni --network goodmusic-network -p 8082:8080 recensioni
docker run -d --name recensioni-seguite --network goodmusic-network -p 8083:8080 recensioni-seguite

echo "Tutti i servizi sono stati avviati!"
