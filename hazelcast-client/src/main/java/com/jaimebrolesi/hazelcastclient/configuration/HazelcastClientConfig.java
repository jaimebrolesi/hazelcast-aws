package com.jaimebrolesi.hazelcastclient.configuration;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientAwsConfig;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

/**
 * Application class used to start the Hazelcast like a client.
 *
 * @author Jaime Brolesi
 * Creation date: 05/04/2018
 */
@Configuration
@EnableCaching
@DependsOn("hazelcastProperties")
public class HazelcastClientConfig {

//    @Value("${hazelcast-client.aws.enable}")
//    private Boolean awsEnabled;
//
//    @Value("${hazelcast-client.inside.aws}")
//    private Boolean insideAwsEnvironment;
//
//    @Value("${hazelcast-client.aws.accessKey}")
//    private String awsAccessKey;
//
//    @Value("${hazelcast-client.aws.secretKey}")
//    private String awsSecretKey;
//
//    @Value("${hazelcast-client.aws.region}")
//    private String awsRegion;
//
//    @Value("${hazelcast-client.aws.hostHeader}")
//    private String awsHostHeader;
//
//    @Value("${hazelcast-client.aws.securityGroupName}")
//    private String awsSecurityGroupName;
//
//    @Value("${hazelcast-client.aws.tagKey}")
//    private String awsTagKey;
//
//    @Value("${hazelcast-client.aws.tagValue}")
//    private String awsTagValue;
//
//    @Value("${hazelcast-client.server.ip}")
//    private String serverIp;


    private final HazelcastProperties hazelcastProperties;

    public HazelcastClientConfig(HazelcastProperties hazelcastProperties) {
        this.hazelcastProperties = hazelcastProperties;
    }

    /**
     * Method used to initialize a HazelCast client instance.
     *
     * @return new hazelcast client instance.
     */
    @Bean
    @Primary
    public HazelcastInstance hazelcastInstance() {
        ClientConfig clientConfig = new ClientConfig();
        ClientNetworkConfig clientNetworkConfig = new ClientNetworkConfig();
        if (hazelcastProperties.getAws().getEnabled()) {
            ClientAwsConfig clientAwsConfig = new ClientAwsConfig();

            clientAwsConfig.setEnabled(true);
            clientAwsConfig.setInsideAws(hazelcastProperties.getAws().getInsideAwsEnvironment());
            clientAwsConfig.setAccessKey(hazelcastProperties.getAws().getAccessKey());
            clientAwsConfig.setSecretKey(hazelcastProperties.getAws().getSecretKey());
            clientAwsConfig.setRegion(hazelcastProperties.getAws().getRegion());
            clientAwsConfig.setHostHeader(hazelcastProperties.getAws().getHostHeader());
            clientAwsConfig.setSecurityGroupName(hazelcastProperties.getAws().getSecurityGroupName());
            clientAwsConfig.setTagKey(hazelcastProperties.getAws().getTagKey());
            clientAwsConfig.setTagValue(hazelcastProperties.getAws().getTagValue());

            clientConfig.setNetworkConfig(clientNetworkConfig.setAwsConfig(clientAwsConfig));
        } else {
            clientConfig.getNetworkConfig()
                    .addAddress(hazelcastProperties.getServerIp());
        }

        return HazelcastClient.newHazelcastClient(clientConfig);
    }

    /**
     * Method used to override the spring cache manager to hazelcast cache manager.
     *
     * @return Hazelcast cache manager instance.
     */
    @Bean
    CacheManager cacheManager() {
        return new HazelcastCacheManager(hazelcastInstance());
    }
}
