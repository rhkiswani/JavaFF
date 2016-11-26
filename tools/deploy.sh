#!/usr/bin/env bash

function getVersion(){
    MVN_VERSION=$(mvn -q \
        -Dexec.executable="echo" \
        -Dexec.args='${project.version}' \
        --non-recursive \
        org.codehaus.mojo:exec-maven-plugin:1.3.1:exec)

    if [[ $MVN_VERSION == *"SNAPSHOT"* ]]
    then
        for EACH in `echo "$MVN_VERSION" | grep -o -e "[^-SNAPSHOT]*"`; do
            version="$EACH";
        done
    fi
}

function prepare(){
    git checkout master
    git reset --hard
    git pull origin master
    getVersion
}

function release(){
    mvn release:clean release:prepare -DperformRelease=true -B
    mvn release:perform
    git commit . -m"release $version"
    git push origin master
}

function postRelease(){
    git checkout develop
    git reset --hard
    git pull origin develop
    git pull origin master
    git push origin develop
}

function main(){
    prepare
    release
    postRelease
}

main

