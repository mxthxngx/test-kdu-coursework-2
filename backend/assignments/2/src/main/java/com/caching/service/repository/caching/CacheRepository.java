package com.caching.service.repository.caching;

import com.caching.service.dto.ResponseDTOForward;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@EnableCaching
public class CacheRepository {

   
    private CacheManager cacheManager;
    @Autowired
CacheRepository(CacheManager cacheManager){
    this.cacheManager = cacheManager;
}
    /**
     * Cache data for forward geocoding using the given address and data.
     *
     * @param  address     the address to cache data for
     * @param  data        the data to be cached
     */
    @Cacheable(key = "#address" )
    public void cacheDataFowardGeoCoding(String address, List<ResponseDTOForward> data) {
        log.info("Caching the address: " + address);
        try {
            Cache cache = cacheManager.getCache("geocoding");
            cache.put(address, data);
            log.info("Caching completed successfully");
           log.info( cache.get(address).toString());
        } catch (Exception e) {
            log.error("Error caching data for address: " + address, e);
        }

    }

    /**
     * Retrieves cached data for the given address.
     *
     * @param  address  the address for which to retrieve cached data
     * @return          the cached data for the given address, or null if no cached data is found
     */
    @Cacheable(cacheNames = "geocoding", key = "#address")
    public List<ResponseDTOForward> getCachedData(String address) {
        log.info("Retrieving cached data for address: " + address);
        Cache cache = cacheManager.getCache("geocoding");
        if (cache != null && cache.get(address) != null) {
            return cache.get(address, List.class);
        } else {
            return null; // No cached data found
        }
    }
}
