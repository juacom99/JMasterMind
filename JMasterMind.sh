#!/bin/bash

if [[ -z "${JAVA_HOME}" ]]; then
  echo "JAVA_HOME is not set, please install java 16 or greater and set the JAVA_HOME environment variable before running this game"
else
  $JAVA_HOME/bin/java -jar JMasterMind.jar
fi


