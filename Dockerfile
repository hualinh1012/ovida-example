FROM eclipse-temurin:21

WORKDIR .

COPY target/springboot-example-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]