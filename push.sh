#!/usr/bin/env bash

set -ev

if [[ -z "$1" ]] ; then
    echo "Must pass image to push (format: REPO/MODULE:TAG)"
    exit 1
fi

DOCKER_PUSH=1;
while [ $DOCKER_PUSH -gt 0 ] ; do
    echo "Pushing $1";
    docker push $1;
    DOCKER_PUSH=$(echo $?);
    if [[ "$DOCKER_PUSH" -gt 0 ]] ; then
        echo "Docker push failed with exit code $DOCKER_PUSH";
    fi;
done;
