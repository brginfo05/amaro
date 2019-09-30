#!/bin/bash

mvn package
find target -name \*.jar | xargs java -jar