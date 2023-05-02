FROM openjdk:17-alpine

WORKDIR /product

COPY target/*.jar /product/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]