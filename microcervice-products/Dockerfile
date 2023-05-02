FROM openjdk:17-alpine

WORKDIR /product

COPY ./target/*.jar /product/app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]