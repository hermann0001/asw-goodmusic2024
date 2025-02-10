#!/bin/bash

echo "Running GOODMUSIC"

# build
./gradlew clean build
if [ $? -ne 0 ]; then
  exit 1
fi
docker compose up -d --build

echo "Waiting for containers to be ready..."

# Wait for the services to be healthy (adjust timeout if needed)
for service in recensioni connessioni recensioni-seguite; do
  until docker ps | grep -q "$service"; do
    sleep 2
  done
done
echo "All services are running. Opening shells..."
echo -e "recensioni\nconnessioni\nrecensioni-seguite" | xargs -d '\n' -I {} setsid x-terminal-emulator -e "docker exec -it {} bash"