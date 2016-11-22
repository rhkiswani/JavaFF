#!/usr/bin/env bash
git checkout master
git reset --hard
git pull origin master
mvn release:clean release:prepare -DperformRelease=true -B
mvn release:perform
git commit . -m"release $1"
git push origin master
