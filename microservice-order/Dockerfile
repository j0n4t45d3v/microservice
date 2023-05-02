FROM openjdk:17-alpine

WORKDIR /order

COPY ./target/*.jar /order/app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]