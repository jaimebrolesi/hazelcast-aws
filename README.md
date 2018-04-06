# hazelcast-aws

This project aims to create a cluster of Hazelcast on AWS enviroment.

## Getting started
Starts the mancenter docker container to monitoring the Hazelcast Cluster:

```
$ docker run -p 8081:8080 -d hazelcast/management-center
```
Do not forget to change the port if you have another service running on port 8081.

## Usage
- Run the hazelcast-server application twice. (Change ports) <br />
- The management center only accepts two instances. (free-layer)
- Run the hazelcast-client application. (default port is 8082)

<b>Enjoy!</b>
