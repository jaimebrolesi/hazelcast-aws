package com.jaimebrolesi.hazelcastserver.configuration;

import com.hazelcast.config.AwsConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to initialize the cache and define the environment. (AWS or localhost)
 *
 * @author Jaime Brolesi
 * Creation date: 04/04/2018
 */
@Configuration
@EnableCaching
public class HazelcastConfig {

    /** Inject the application-sharedCaches.yml configuration. */
    private SharedCacheProperties sharedCacheProperties;

    /** Mancenter enabled property. */
    @Value("${hazelcast-server.mancenter.enabled}")
    private Boolean mancenterEnabled;

    /** Mancenter URL property. */
    @Value("${hazelcast-server.mancenter.url}")
    private String mancenterURL;

    /** Tcp enabled property. */
    @Value("${hazelcast-server.tcp.enabled}")
    private Boolean tcpEnabled;

    /** Multicast enabled property. */
    @Value("${hazelcast-server.multicast.enabled}")
    private Boolean multicastEnabled;

    /** AWS enabled property. */
    @Value("${hazelcast-server.aws.enabled}")
    private Boolean awsEnabled;

    /** AWS host header property. */
    @Value("${hazelcast-server.aws.host.header:}")
    private String awsHostHeader;

    /** AWS security group property. */
    @Value("${hazelcast-server.aws.security.group:}")
    private String awsSecurityGroup;

    /** AWS secret key property. */
    @Value("${hazelcast-server.aws.secret.key:}")
    private String awsSecretKey;

    /** AWS access key property. */
    @Value("${hazelcast-server.aws.access.key:}")
    private String awsAccessKey;

    /** AWS region property. */
    @Value("${hazelcast-server.aws.region:}")
    private String awsRegion;

    /** AWS tag key property. */
    @Value("${hazelcast-server.aws.tag.key:}")
    private String awsTagKey;

    /** AWS tag value property. */
    @Value("${hazelcast-server.aws.tag.value:}")
    private String awsTagValue;

    /**
     * Method used to initialize a HazelCast server instance.
     *
     * @return new hazelcast server instance.
     */
    @Bean
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();
        config.getManagementCenterConfig()
                .setEnabled(this.mancenterEnabled)
                .setUrl(this.mancenterURL);

        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
        joinConfig.getTcpIpConfig().setEnabled(this.tcpEnabled);
        joinConfig.getMulticastConfig().setEnabled(this.multicastEnabled);

        if(this.awsEnabled){
            AwsConfig awsConfig = joinConfig.getAwsConfig();
            awsConfig.setEnabled(this.awsEnabled);
            awsConfig.setHostHeader(this.awsHostHeader);
            awsConfig.setSecurityGroupName(this.awsSecurityGroup);
            awsConfig.setSecretKey(this.awsSecretKey);
            awsConfig.setAccessKey(this.awsAccessKey);
            awsConfig.setRegion(this.awsRegion);
            awsConfig.setTagKey(this.awsTagKey);
            awsConfig.setTagValue(this.awsTagValue);
        }

        sharedCacheProperties.getCaches().forEach(cacheConfig -> config.addMapConfig(cacheConfig.toMapConfig()));

        return Hazelcast.newHazelcastInstance(config);
    }

    /**
     * Inject {@link SharedCacheProperties} service.
     * @param sharedCacheProperties bean to be injected.
     */
    @Autowired
    public void setSharedCacheProperties(SharedCacheProperties sharedCacheProperties) {
        this.sharedCacheProperties = sharedCacheProperties;
    }
}
