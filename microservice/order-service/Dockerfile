FROM openjdk:17-alpine

WORKDIR /order-service

COPY ./target/*.jar /order-service/app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]