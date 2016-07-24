#!/bin/sh

echo "Creating docker image $IMAGE_VERSION"
docker build -t $IMAGE_VERSION .
docker tag $IMAGE_VERSION $IMAGE:latest
docker login --email=$DOCKER_HUB_EMAIL --username=$DOCKER_HUB_USERNAME --password=$DOCKER_HUB_PASSWORD
docker push $IMAGE

# travis komt met een verouderde gcloud versie en we hebben een nieuwe nodig
# want anders kunnen we kubectl niet installeren
gcloud version
# If the SDK is not already cached, download it and unpack it
if [ ! -d $HOME/$CLOUDSDK_INSTALL_DIR ]; then
  curl https://sdk.cloud.google.com | bash;
fi
source /home/travis/.bashrc
gcloud version
gcloud components install kubectl
gcloud auth activate-service-account --key-file client-secret.json
gcloud components update kubectl
gcloud config set compute/zone europe-west1-b
gcloud config set core/project cerioscoop-project
gcloud container clusters get-credentials cerioscoop-cluster

# met het patch commando kunnen we de image in een deployment updaten
kubectl patch deployment cerioscoop-deployment -p   '{"spec":{"template":{"spec":{"containers":[{"name":"cerioscoop-deployment","image":"'"$IMAGE_VERSION"'"}]}}}}'
