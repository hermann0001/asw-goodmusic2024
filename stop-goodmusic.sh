#!/bin/bash

echo Halting GOODMUSIC   

pkill -f 'recensioni-seguite.jar'
pkill -f 'recensioni.jar'
pkill -f 'connessioni.jar'
pkill -f 'api-gateway.jar'

sleep 4 

docker stop asw-consul 
docker rm asw-consul 

#termino i container dei database 
docker stop db-recensioni
docker rm db-recensioni
docker stop db-connessioni
docker rm db-connessioni

#termino i container dei microservizi
for i in $(seq 1 2); do
  docker stop connessioni-$i
  docker rm connessioni-$i
done

for i in $(seq 1 2); do
  docker stop recensioni-$i 
  docker rm recensioni-$i 
done

for i in $(seq 1 2); do
  docker stop recensioni-seguite-$i 
  docker rm recensioni-seguite-$i
done

docker stop api-gateway
docker rm api-gateway

#elimina la rete
docker network rm goodmusic-network