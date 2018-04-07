FROM java:8

VOLUME /tmp

ADD /target/hazelcast-server-*.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/app.jar"]