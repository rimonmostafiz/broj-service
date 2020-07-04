FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/broj-service-0.0.1.jar broj-service.jar
ENTRYPOINT ["java", "-jar", "broj-service.jar"]