FROM openjdk:17-alpine

WORKDIR /gateway

COPY ./target/*.jar /gateway/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]