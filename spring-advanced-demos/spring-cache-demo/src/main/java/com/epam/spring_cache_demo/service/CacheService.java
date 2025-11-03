package com.epam.spring_cache_demo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CacheService {

    private static final Logger LOG = LogManager.getLogger(CacheService.class);
    private final CacheManager cacheManager;

    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void evictAllCaches() {
        cacheManager.getCacheNames()
                .forEach(name -> {
                    if (Objects.nonNull(cacheManager.getCache(name))) {
                        cacheManager.getCache(name).clear();
                    }
                });

        LOG.info("\n All caches cleared successfully at " + java.time.LocalTime.now() + "\n");
    }

    /**
     * Clears cache entries for a specific cache name.
     *
     * @param cacheName the name of the cache to clear
     */
    public void evictCacheByName(String cacheName) {
        if (cacheManager.getCache(cacheName) != null) {
            cacheManager.getCache(cacheName).clear();
            LOG.info("Cache cleared for: " + cacheName + " at " + java.time.LocalTime.now());
        }
    }
}
