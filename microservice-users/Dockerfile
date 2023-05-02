FROM openjdk:17-alpine

WORKDIR /user

COPY ./target/*.jar /user/app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]