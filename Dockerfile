FROM openjdk:17

COPY target/video-service-0.0.1-SNAPSHOT.jar docker-video-service.jar

ENTRYPOINT ["java", "-jar", "/docker-video-service.jar"]