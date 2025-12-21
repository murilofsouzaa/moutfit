FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/Eden.jar Eden.jar

EXPOSE 9090

CMD["java", "-jar", "Eden.jar"]