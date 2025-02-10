#!/bin/bash

echo "Stopping GOODMUSIC..."

# Stop and remove only the application's containers
docker compose down

# Remove dangling containers related to the application
dangling_containers=$(docker ps -a -q --filter "status=exited")
if [ -n "$dangling_containers" ]; then
  echo "Removing dangling containers..."
  docker rm $dangling_containers
else
  echo "No dangling containers to remove."
fi