#!/usr/bin/env bash
git checkout master
git reset --hard
git pull origin master
mvn release:clean release:prepare -PperformRelease=true -B
mvn release:preapre
git-flow release start "$1"
git-flow release publish "$1"
git-flow release finish "$1"
git push --tags
