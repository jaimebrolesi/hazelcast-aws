# hazelcast-aws

This project aims to create a cluster of Hazelcast on AWS enviroment.

## Getting started
Starts the mancenter docker container to monitoring the Hazelcast Cluster:

```
$ docker run -p 8081:8080 -d hazelcast/management-center
```
Do not forget to change the port if you have another service running on port 8081.

## Usage
- Run the `hazelcast-server` application twice. <br />
- The management center only accepts two instances. (free-layer)
- Run the `hazelcast-client` application.
- See `hazelcast-client` `CacheController` to do some operations.
- Access `http://localhost:8081/mancenter` to see the result of your actions.

## Docker
For Docker enthusiast run the `docker-compose.yml` with:

```
$ docker-compose -f docker-compose.yml up --scale hz-server=2
```
- Two instances of hazelcast-server will be created
- Access the app using `http://localhost:8080`
- Management center can be found on `http://localhost:8081/mancenter`

## Considerations

For obvious reasons I did not setted the AWS configuration, so, use your credentials to populate them.

<b>Enjoy!</b>
