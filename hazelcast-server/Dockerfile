FROM openjdk:8-jdk-alpine3.7

VOLUME /tmp

ADD /target/hazelcast-server-*.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/app.jar"]