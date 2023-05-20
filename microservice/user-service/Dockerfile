FROM openjdk:17-alpine

WORKDIR /user-service

COPY ./target/*.jar /user-service/app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]