#!/usr/bin/env bash

set -ev

if [[ -z "$REPO" ]] ; then
    echo "Cannot find REPO env var"
    exit 1
fi

if [[ -z "$COMMIT" ]] ; then
    echo "Cannot find COMMIT env var"
    exit 1
fi

if [[ "$(uname)" == "Darwin" ]]; then
    DOCKER_CMD=docker
else
    DOCKER_CMD="sudo docker"
fi

$DOCKER_CMD run --rm -v $HOME/.m2:/root/.m2 -v $(pwd):/usr/src/mymaven -w /usr/src/mymaven maven:3.2-jdk-8 mvn -DskipTests package

$DOCKER_CMD build -t ${REPO}:${COMMIT} .;
