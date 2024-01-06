FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/BookKeeper-0.0.1-SNAPSHOT.jar /app/BookKeeper.jar

EXPOSE 8080

CMD ["java", "-jar", "BookKeeper.jar"]