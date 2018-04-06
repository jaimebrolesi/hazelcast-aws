package com.jaimebrolesi.hazelcastclient.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class AwsProperties {

    private Boolean enabled;

    private Boolean insideAwsEnvironment;

    private String accessKey;

    private String secretKey;

    private String region;

    private String hostHeader;

    private String securityGroupName;

    private String tagKey;

    private String tagValue;
}
