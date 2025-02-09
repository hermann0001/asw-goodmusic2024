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
docker stop db-recensioni-seguite
docker rm db-recensioni-seguite

#termino i container dei microservizi
docker stop recensioni
docker rm recensioni
docker stop connessioni
docker rm connessioni
docker stop recensioni-seguite
docker rm recensioni-seguite
docker stop api-gateway
docker rm api-gateway

#elimina la rete
docker network rm goodmusic-network