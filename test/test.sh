#!/usr/bin/env bash

set -ev

SCRIPT_DIR=`dirname "$0"`
SCRIPT_NAME=`basename "$0"`
SSH_OPTS=-oStrictHostKeyChecking=no

if [[ "$(uname)" == "Darwin" ]]; then
    DOCKER_CMD=docker
else
    DOCKER_CMD="sudo docker"
fi

if [[ -z $($DOCKER_CMD images | grep test-container) ]] ; then
    if [[ -n ${TRAVIS} ]] ; then
        echo "Building test container for TRAVIS"
        docker build -t test-container $SCRIPT_DIR/travis > /dev/null
    elif [[ -n ${SNAP_CI} ]] ; then
        echo "Building test container on SNAP CI"
        docker build -t test-container $SCRIPT_DIR/snap > /dev/null
    fi
fi

echo "Testing $1"
CODE_DIR=$(cd $SCRIPT_DIR/..; pwd)
$DOCKER_CMD run \
    --rm \
    --name test \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v $CODE_DIR:$CODE_DIR -w $CODE_DIR \
    -e COVERALLS_TOKEN=$COVERALLS_TOKEN \
    -e TRAVIS_JOB_ID=$TRAVIS_JOB_ID \
    -e TRAVIS_BRANCH=$TRAVIS_BRANCH \
    -e TRAVIS_PULL_REQUEST=$TRAVIS_PULL_REQUEST \
    -e TRAVIS=$TRAVIS \
    test-container \
    sh -c "export PYTHONPATH=\$PYTHONPATH:\$PWD/test ; python test/$@"
