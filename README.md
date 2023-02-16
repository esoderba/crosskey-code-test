# Crosskey code test

## Local cli app

### Run using docker
```
cd cli-mortage-calc
nvm install
docker build -t cli-mortage-calculator
docker run cli-mortage-calculator
```
#### Alternatively
```
cd cli-mortage-calc
nvm install
java -jar /target/cli-mortage-calculator.jar "filepath"
```
### Run tests
```
cd cli-mortage-calc
mvn test
```

## Vaadin based web interface
Demo: https://web-calc.fly.dev/
### Run using the following commands
```
cd web-mortage-calc
mvn clean package -Pproduction
docker build -t web-calc .
docker run -p 8080:8080 web-calc
```
The web app should then be running on localhost:8080

### This should be a single maven project instead of two separate ones but due to time restrictions the web implementation is separate and very barebones.
