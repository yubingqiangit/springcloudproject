FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD payment8002-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]