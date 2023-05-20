FROM openjdk:17-alpine

WORKDIR /registry-server

COPY ./target/*.jar /registry-server/app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]