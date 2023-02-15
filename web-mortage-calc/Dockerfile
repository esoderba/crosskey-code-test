FROM openjdk:17-jdk-slim
COPY target/*.jar app.jar
COPY prospects.txt prospects.txt
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
