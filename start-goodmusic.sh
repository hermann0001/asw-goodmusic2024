#!/bin/bash

echo "Running GOODMUSIC"

BUILD_FLAG=false
if [[ "$1" == "--build" ]]; then
  BUILD_FLAG=true
fi

if [ "$BUILD_FLAG" = true ]; then
  ./gradlew clean build
  if [ $? -ne 0 ]; then
    exit 1
  fi
  docker compose up -d --build
else
  docker compose up -d
fi

echo "Waiting for containers to be ready..."

services=("recensioni" "connessioni" "recensioni-seguite")

# Wait for the services to be healthy (adjust timeout if needed)
for service in "${services[@]}"; do
  until docker ps | grep -q "$service"; do
    sleep 2
  done
done

if ! command -v gnome-terminal &> /dev/null; then
  echo "Error: gnome-terminal is not installed or not available in your system."
  echo "You can manually view logs using: docker logs -f <container_name>"
  exit 1
fi

echo "All services are running. Opening shells..."

for service in "${services[@]}"; do
  if docker ps --format "{{.Names}}" | grep -q "^$service$"; then
    gnome-terminal -- bash -c "
      echo -ne \"\033]0;$service\007\";
      docker logs -f $service
    " || echo "Failed to open gnome-terminal for $service. Try running: docker logs -f $service"
  fi
done