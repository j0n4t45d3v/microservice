FROM openjdk:17-alpine

WORKDIR /gateway

COPY target/*.jar /gateway/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]