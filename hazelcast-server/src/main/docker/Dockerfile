FROM java:8

VOLUME $HOME/tmp

ADD hazelcast-server-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]