package com.jaimebrolesi.hazelcastclient.controllers;

import com.jaimebrolesi.hazelcastclient.repositories.HazelcastRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller to manage all caches information.
 *
 * @author Jaime Brolesi
 * Creation date: 05/04/2018
 */
@RestController
@RequestMapping("/cache")
@Slf4j
public class CacheController {

    private final HazelcastRepository hzRepo;

    public CacheController(HazelcastRepository hzRepo) {
        this.hzRepo = hzRepo;
    }

    @RequestMapping(value = "/evictElement/{cache}/{key}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void evict(@PathVariable String cache, @PathVariable Object key) {
        log.info("Evict cache with name [{}] and element key [{}]", cache, key);
        hzRepo.evict(cache, key);
    }

    @RequestMapping(value = "/evictAllElements/{cache}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void evictAll(@PathVariable String cache) {
        log.info("Evict all elements from cache with name [{}]", cache);
        hzRepo.evictAll(cache);
    }

    @RequestMapping(value = "/evictAllCaches", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void clean() {
        log.info("Evict all caches");
        hzRepo.evictAllCaches();
    }

    @RequestMapping(value = "/insert/{cache}", method = RequestMethod.POST)
    public Map<String, String> insert(@PathVariable String cache) {

        hzRepo.insert(cache, "foo", "bar");
        String bar = hzRepo.find(cache, "foo", String.class);

        return new HashMap<String, String>() {{
            put("foo", bar);
        }};
    }
}
