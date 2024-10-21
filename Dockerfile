FROM ubuntu:latest
LABEL authors="dyl01"

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/hello-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar","app.jar"]