FROM openjdk:17-alpine

WORKDIR /user

COPY target/*.jar /user/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]