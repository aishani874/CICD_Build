FROM eclipse-temurin:17-jre-alpine

WORKDIR /usr/app

COPY target/app.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]