#!/usr/bin/env bash
git checkout master
git reset --hard
git pull origin master
mvn release:clean release:prepare -PperformRelease=true -B
mvn release:perform
