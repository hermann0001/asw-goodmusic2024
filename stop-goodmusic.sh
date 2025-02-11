#!/bin/bash

echo "Stopping GOODMUSIC..."

docker compose down

echo "Closing all opened terminal windows..."

# Find all gnome-terminal windows opened by this script and close them

services=("recensioni" "connessioni" "recensioni-seguite")

for service in "${services[@]}"; do
  wmctrl -l | grep "$service" | awk '{print $1}' | xargs -I {} wmctrl -ic {}
done  

# Alternative method (ensure no terminal remains open)
pkill --oldest gnome-terminal
dangling_containers=$(docker ps -a -q --filter "status=exited")
if [ -n "$dangling_containers" ]; then
  echo "Removing dangling containers..."
  docker rm $dangling_containers
else
  echo "No dangling containers to remove."
fi