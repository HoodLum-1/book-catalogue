#!/bin/bash

# Execute management-service
java -jar management-service-0.0.1-SNAPSHOT.jar &

# Execute ui-service-thymeleaf
java -jar ui-service-thymeleaf-0.0.1-SNAPSHOT.jar &