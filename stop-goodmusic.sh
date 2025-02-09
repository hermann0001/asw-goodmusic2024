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