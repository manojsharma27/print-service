FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD target/print-service-0.0.1-SNAPSHOT.jar /app/print-service.jar
ENTRYPOINT ["java", "-jar", "/app/print-service.jar"]