#!/bin/sh
./gradlew clean oneJar
java -jar build/libs/GovNotifyPoc-1.0-SNAPSHOT-standalone.jar "$@"
