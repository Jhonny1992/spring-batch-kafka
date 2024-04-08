FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/ms-micro1-0.0.1-SNAPSHOT.jar app.jar
ENV BROKEN_URL null
ENV EUREKA_URL null
ENTRYPOINT ["java","-jar","/app.jar"]