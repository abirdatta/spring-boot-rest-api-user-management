#!/bin/bash
sed -e "s/%REPLICA_COUNT%/${APP_POD_COUNT}/g;s/%USER_API_IMAGE_TAG%/${APP_DOCKER_IMAGE_VERSION}/g" app-deployment.yaml > app-deployment-build.yaml
