package com.epam.spring_cache_demo.scheduler;

import com.epam.spring_cache_demo.service.CacheService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheEvictScheduler {

    private static final Logger LOG = LogManager.getLogger(CacheEvictScheduler.class);
    private final CacheService cacheService;

    public CacheEvictScheduler(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    /**
     * Periodically clears all caches to maintain data consistency and prevent stale data.
     * <p>
     * This scheduled task runs at a fixed interval (every 1 minute in this configuration)
     * and triggers cache eviction across all configured cache names.
     * </p>
     *
     * <p><b>Note:</b> Adjust the interval as per the application's caching and data refresh needs.</p>
     */
    @Scheduled(fixedRate = 60000) // 1 minute
    public void clearAllCachesOnTimer() {
        LOG.info("Scheduled Cache Cleanup Triggered — clearing all caches at {}", java.time.LocalTime.now());
        cacheService.evictAllCaches();
        LOG.info("Cache cleanup completed successfully.\n");
    }
}
