#!/usr/bin/env bash

function getVersion(){
    mvn_version=$(mvn -q \
        -Dexec.executable="echo" \
        -Dexec.args='${project.version}' \
        --non-recursive \
        org.codehaus.mojo:exec-maven-plugin:1.3.1:exec)

    if [[ $mvn_version == *"SNAPSHOT"* ]]
    then
        for str in $(echo "$mvn_version" | grep -o -e "[^-SNAPSHOT]*"); do
            version="$str";
        done
    fi
}

function update(){
    mvn release:update-versions -DperformRelease=true -B
    getVersion
}

function afterUpdate(){
    git commit . -m"develop $version updated"
    git pull origin develop
    git push origin develop
}

function main(){
    if [ "$TRAVIS_BRANCH" = "develop" ]; then
        update
        afterUpdate
    fi
}

main

