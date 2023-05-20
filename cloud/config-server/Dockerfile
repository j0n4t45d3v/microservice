FROM openjdk:17-alpine

WORKDIR /config-server

COPY ./target/*.jar /config-server/app.jar

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "app.jar"]