FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/spring-boot-docker-1.0-SNAPSHOT.jar

COPY ${JAR_FILE} spring-boot-docker.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/spring-boot-docker.jar"]