FROM java:8

VOLUME $HOME/tmp

ADD hazelcast-client-*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]