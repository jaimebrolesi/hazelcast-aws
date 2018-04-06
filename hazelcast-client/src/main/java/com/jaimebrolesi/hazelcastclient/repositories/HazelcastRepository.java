package com.jaimebrolesi.hazelcastclient.repositories;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Hazelcast repository.
 *
 * @author Jaime Brolesi
 * Creation date: 05/04/2018
 */
@Repository
public class HazelcastRepository {

    private final CacheManager cacheManager;

    private final HazelcastInstance hzInstance;

    private static final String CACHE = "cache";

    private static final String NO_CACHE = "nocache";

    public HazelcastRepository(CacheManager cacheManager, HazelcastInstance hzInstance) {
        this.cacheManager = cacheManager;
        this.hzInstance = hzInstance;
    }

    public void insert(String name, Object key, Object value) {
        Cache cache = cacheManager.getCache(name);
        assert cache != null;
        cache.put(key, value);
    }

    public <T> T find(String name, Object key, Class<T> clazz) {
        Cache cache = cacheManager.getCache(name);
        assert cache != null;
        return cache.get(key, clazz);
    }

    public void evict(String name, Object key) {
        Cache cache = cacheManager.getCache(name);
        assert cache != null;
        cache.evict(key);
    }

    public void evictAll(String name) {
        Cache cache = cacheManager.getCache(name);
        assert cache != null;
        cache.clear();
    }

    public void evictAllCaches() {
        cacheManager.getCacheNames().forEach(this::evictAll);
    }

    public <T> List<T> getAllElementsKey(String name, Class<T> clazz) {
        IMap map = hzInstance.getMap(name);
        List<T> result = new ArrayList<>();

        map.forEach((k, v) -> result.add((T) k));
        return result;
    }

    public Map<String, Object> getAllElements(String name, Set keys) {
        IMap map = hzInstance.getMap(name);
        Map result = map.getAll(keys);
        keys.removeAll(result.keySet());
        return new HashMap<String, Object>() {{
            put(CACHE, result);
            put(NO_CACHE, keys);
        }};
    }
}
