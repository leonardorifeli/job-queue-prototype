#!/bin/bash

cd ~/projects/job-queue-prototype
mvn clean install
cd ~/projects/job-queue-prototype/api
mvn tomcat7:run