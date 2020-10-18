#!/bin/bash

GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m'
RED='\033[0;31m'

set -e

if [[ $# -eq 0 ]]; then
     echo -e "\n${RED}###############Please mention service name '###################${NC}\n"
     exit 0
fi

printf "\n${BLUE}###############MVN build###################${NC}\n"
mvn clean install -pl "$1"

printf "\n${BLUE}###############Docker login###################${NC}\n"
sudo docker login registry-1.docker.io

printf "\n${BLUE}###############Docker build###################${NC}\n"
sudo docker-compose build --no-cache "$1"

printf "\n${BLUE}###############Docker tag###################${NC}\n"
sudo docker tag "$1" registry-1.docker.io/aramohanyan/"$1"

printf "\n${BLUE}###############Docker push###################${NC}\n"
sudo docker push registry-1.docker.io/aramohanyan/"$1"

#printf "\n${BLUE}###############restart aws tasks###################${NC}\n"
#index=0
#taskArn=$(aws ecs list-tasks --cluster default --query "taskArns[${index}]" --output text)

#until [ "$taskArn" = "None" ]
#do 
#  aws ecs stop-task --cluster default --task $taskArn
#  ((index++))
#  taskArn=$(aws ecs list-tasks --cluster default --query "taskArns[${index}]" --output text)
#done

#aws ecs update-service --cluster default --service noteservice-service --force-new-deployment

printf "${GREEN}###############Done successfully###################${NC}\n"


