#!/usr/bin/env bash
mvn package -DskipTests
cp target/*.jar python/methods/
sed -i "mvn help:evaluate -Dexpression=project.version | grep -e '^[^\[]'" python/_version.txt
python python/setup.py sdist