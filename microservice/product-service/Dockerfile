FROM openjdk:17-alpine

WORKDIR /product-service

COPY ./target/*.jar /product-service/app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]