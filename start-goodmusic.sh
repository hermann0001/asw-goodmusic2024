#!/bin/bash

# Script per avviare l'applicazione GoodMusic con due database PostgreSQL

echo Running GOODMUSIC

# build
./gradlew clean build

# Avvia il container per Consul
docker run -d --hostname localhost --name asw-consul --publish 8500:8500 docker.io/hashicorp/consul

# Avvia i container PostgreSQL per i servizi "recensioni" e "connessioni"
docker run -d --name db-recensioni --hostname db-recensioni \
  --mount type=volume,src=recensioni-db-data,dst=/var/lib/postgresql/data \
  -e POSTGRES_USER=recensioni_user \
  -e POSTGRES_PASSWORD=recensioni_pass \
  -e POSTGRES_DB=recensioni_db \
  -p 5432:5432 postgres:latest   #La porta 5432 dell'host inoltra richiesta di servizio alla porta 5432 del container 
 
docker run -d --name db-connessioni --hostname db-connessioni \
  --mount type=volume,src=connessioni-db-data,dst=/var/lib/postgresql/data \
  -e POSTGRES_USER=connessioni_user \
  -e POSTGRES_PASSWORD=connessioni_pass \
  -e POSTGRES_DB=connessioni_db \
  -p 5433:5432 postgres:latest   #La porta 5433 dell'host inoltra richiesta di servizio alla porta 5432 del container

docker run -d --name db-recensioni-seguite --hostname db-recensioni-seguite \
  --mount type=volume,src=recensioni-seguite-db-data,dst=/var/lib/postgresql/data \
  -e POSTGRES_USER=user \
  -e POSTGRES_PASSWORD=pass \
  -e POSTGRES_DB=recensioni-seguite_db \
  -p 5434:5432 postgres:latest   #La porta 5434 dell'host inoltra richiesta di servizio alla porta 5432 del container


# Attendi affinché i container siano pronti
sleep 15

# Avvia i microservizi Java
java -Xms64m -Xmx128m -jar recensioni/build/libs/recensioni.jar &
java -Xms64m -Xmx128m -jar connessioni/build/libs/connessioni.jar &
java -Xms64m -Xmx128m -jar recensioni-seguite/build/libs/recensioni-seguite.jar &

java -Xms64m -Xmx128m -jar api-gateway/build/libs/api-gateway.jar &
