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

