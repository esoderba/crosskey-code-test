# Crosskey code test

## Local cli app

### Run using the following commands
1. cd cli-mortage-calc
2. nvm install
#### Run via Docker image
3. docker build -t cli-mortage-calculator
4. docker run cli-mortage-calculator
#### Run locally
3. java -jar /target/cli-mortage-calculator.jar "filepath"

### Run tests
1. mvn test


## Vaadin based web interface

### Run using the following commands
1. cd web-mortage-calc
2. mvn install
3. java -jar /target/webmortagecalc-1.0-SNAPSHOT.jar
### Alternatively
1. cd web-mortage-calc
2. mvnw

### The web implementation is unfortunately very barebones due to time restrictions
