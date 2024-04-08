FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/ms-batch-0.0.1-SNAPSHOT.jar app.jar
ENV CONFIG_SERVER null
ENV BROKER_URL null
ENV EUREKA_URL null
ENTRYPOINT ["java","-jar","/app.jar"]