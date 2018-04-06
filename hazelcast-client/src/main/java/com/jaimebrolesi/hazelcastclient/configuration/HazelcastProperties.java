package com.jaimebrolesi.hazelcastclient.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Hazelcast client properties.
 *
 * @author Jaime Brolesi
 * Creation date: 05/04/2018
 */
@Component
@ConfigurationProperties(prefix = "hazelcast-client")
@Getter
@Setter
public class HazelcastProperties {

    private String serverIp;
    private AwsProperties aws;
}
