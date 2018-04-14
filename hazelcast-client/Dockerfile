FROM openjdk:8-jdk-alpine3.7

VOLUME /tmp

ADD /target/hazelcast-client-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","-jar","/app.jar"]