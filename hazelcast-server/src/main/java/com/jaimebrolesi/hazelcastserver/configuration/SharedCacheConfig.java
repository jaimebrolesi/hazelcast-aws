package com.jaimebrolesi.hazelcastserver.configuration;

import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.MapConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Configuration class bases on application-sharedCaches.yml.
 *
 * @author Jaime Brolesi
 * Creation date: 04/04/2018
 */
@Getter
@Setter
@Slf4j
class SharedCacheConfig {

    /**
     * Indicates the a name for the cache.
     */
    private String name;

    /**
     * Indicates the max elements quantity for the cache. (The size is quantitative. Ex: 10, 20, 30).
     */
    private int maxSize;

    /**
     * Indicates the time to live of the cache element.
     */
    private int ttl;

    /**
     * Indicates the time to idle of the cache. (Different from ttl the idle time increments the time living of the cache every time the cache is called).
     */
    private int timeIdle;

    /**
     * LFU - Least Frequently Used. LRU - Least Recently Used.
     */
    private EvictionPolicy evictionPolicy;

    /**
     * Indicates the amount of cache backups on others nodes.
     */
    private int backupCount;

    /**
     * Indicates the format of cache BINARY, OBJECT or NATIVE.
     */
    private InMemoryFormat inMemoryFormat;


    /**
     * Convert a {@link SharedCacheConfig} into {@link MapConfig}.
     * @return new {@link MapConfig} instance.
     */
    public MapConfig toMapConfig() {
        log.info("creating cache with name: " + name);
        MapConfig mapConfig = new MapConfig()
                .setName(name)
                .setTimeToLiveSeconds(ttl)
                .setMaxIdleSeconds(timeIdle)
                .setEvictionPolicy(evictionPolicy)
                .setBackupCount(backupCount)
                .setInMemoryFormat(inMemoryFormat);

        mapConfig.getMaxSizeConfig().setSize(maxSize);
        return mapConfig;
    }
}
