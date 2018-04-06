package com.jaimebrolesi.hazelcastserver.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Property class to read all caches from application-sharedCaches.yml.
 *
 * @author Jaime Brolesi
 * Creation date: 04/04/2018
 */
@Component
@ConfigurationProperties(prefix = "shared")
public class SharedCacheProperties {

    /**
     * List of caches from application-sharedCaches.yml.
     */
    private List<SharedCacheConfig> caches = new ArrayList<>();

    /**
     * Get all caches from application-sharedCaches.yml.
     * @return a list with all caches from application-sharedCaches.yml
     */
    public List<SharedCacheConfig> getCaches() {
        return caches;
    }
}
