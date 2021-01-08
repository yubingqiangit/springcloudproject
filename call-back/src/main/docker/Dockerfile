FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD call-back-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]