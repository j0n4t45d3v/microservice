FROM openjdk:17-alpine

WORKDIR /order

COPY target/*.jar /order/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]