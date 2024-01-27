# Assignment 2

## GeoCoding and Reverse GeoCoding

### Forward Geocoding

Forward geocoding is the process of converting human-readable addresses (like street addresses) into geographic coordinates (latitude and longitude). It helps in pinpointing locations on a map.

- **Forward Geocoding (JSON API):**

```jsx
http://localhost:8080/geocoding?address=${value}
```

- **Forward Geocoding (UI):**

```jsx
http://localhost:8080/geocodingUI?address=${value}
```

### Reverse Geocoding

Reverse geocoding is the opposite process. It involves converting geographic coordinates (latitude and longitude) into a human-readable address format. This is useful for finding out the address of a given set of coordinates.

- **Reverse Geocoding (JSON API):**

```jsx
http://localhost:8080/reverse-geocoding?lat=${value}&lon=${value}
```

- **Reverse Geocoding (UI):**

```jsx
http://localhost:8080/reverse-geocodingUI?lat=${value}&lon=${value}
```

Replace `${value}` with the appropriate values.

Both forward and reverse geocoding are commonly used in mapping applications, navigation systems, and location-based services.
This repository contains test cases for the GeoCoding service implemented in the `com.caching.service` package. These tests cover various scenarios to ensure the correctness and robustness of the GeoCoding functionality.

## Test Cases

### Negative Test Case for Reverse GeoCoding Endpoint

This test case checks the behavior of the reverse geocoding endpoint when provided with invalid latitude and longitude coordinates. The expected behavior is to receive a client error response (4xx). This test verifies that the endpoint handles invalid input gracefully.

### Positive Test Case for GeoCoding Endpoint

This test case verifies the correctness of the forward geocoding endpoint by providing a valid address ("delhi") and comparing the received response with a pre-saved mock response. It ensures that the service correctly converts the address to geographic coordinates.

### Positive Test Case for Reverse GeoCoding Endpoint

Similar to the forward geocoding test, this case validates the reverse geocoding endpoint by providing valid latitude and longitude coordinates and comparing the returned address with a pre-saved mock response.

### GeoCoding Cache Hit Test with Endpoint

This test case verifies the caching mechanism of the forward geocoding endpoint. It makes two requests with the same address ("delhi"). The first request is expected to miss the cache, while the second request should hit the cache, indicating that the service correctly caches previously fetched results.

### GeoCoding Cache Eviction Test

This test ensures that the caching mechanism evicts entries after a specified duration. It first populates the cache with an address ("goa"), waits for a specified delay, and then performs another request with a different address ("delhi"). The test verifies that the entry for "goa" is evicted from the cache while the entry for "delhi" remains.

### Reverse GeoCoding Cache Hit Test with Endpoint

Similar to the forward geocoding cache hit test, this case verifies the caching mechanism of the reverse geocoding endpoint. It makes two requests with the same latitude and longitude coordinates. The second request is expected to hit the cache, indicating that the service correctly caches previously retrieved addresses.

### Negative Test Case for GeoCoding Endpoint

This negative test case checks the behavior of the forward geocoding endpoint when provided with an invalid address. The expected behavior is to receive a client error response (4xx). This test ensures that the service handles invalid addresses appropriately.

### GeoCoding Cache Miss Test

This test case verifies that the caching mechanism works by checking that the cache misses when a specific address is requested for the first time.

## Notes

- The tests have been modified to use the `https://geocode.maps.co/` API instead of the provided API.
- Although the API source has changed, the essence of the test cases remains the same. Only the logic has been modified according to the new API.
- The use of `Thread.sleep` in the setup and teardown methods is to adhere to the API's rate limiting policy, which allows only one request per second.
- External API requests are mocked to save time during testing. The responses used for comparison have been pre-saved for both forward and reverse geocoding.
- The tests are designed to ensure the correctness, reliability, and efficiency of the GeoCoding service.

Feel free to run these tests to ensure the proper functioning of the GeoCoding service under various conditions.
## Instructions for KDUers to complete the assignment
1. The API that must be used for the forward and reverse geocoding must be taken from [postion-stack](https://positionstack.com/)
2. Caching keys and names format :
    * Forward geocoding key = "#address"
    * Forward geocoding name = "geocoding"
    * Reverse geocoding key = "{#latitude,#longitude}"
    * Reverse geocoding name = "reverse-geocoding"
3. APIs Format :
    * Forward geocoding = "http://localhost:${port}/geocoding?address=${value}"
    * Reverse geocoding = "http://localhost:${port}/reverse-geocoding?latitude=${value}&longitude=${value}"
4. API request params names with the following sequence :
    * Forward geocoding = "address"
    * Reverse geocoding = "latitude" , "longitude"
5. In case of forward geocoding, no caching must be done for the location named - “goa” - it should always be re-fetched.

Your application must have:

- In-Memory caching support
- Lookup in an in-memory cache for :
  -- lat/long corresponding to an address.
  -- Address corresponding to latitude and longitude
- If the record is not found in the cache, invoke the third party location API to fetch the lat/long for a given address and persist that in the in-memory cache.
- Remove one or more/all stale and unused records from cache so that fresh values can be loaded into the cache again.
- Avoid evicting too much data out of the cache by selectively updating the entries whenever you alter them
- Clearly log the mapping for lat/long corresponding to an address, and address corresponding to latitude and longitude.

## This Assignment consists of 8 test cases :
1. `testGetGeoCode` -  Test case to verify the successful retrieval of geocoding information from the external API via the `/geocoding` endpoint.
2. `testGetReverseGeoCode` -  Test case to verify the successful retrieval of reverse geocoding information from the external API via the "/reverse-geocoding" endpoint.
3. `testGeoCodingCacheHitWithEndpoint` -  Test case to verify that the cache is populated and successfully retrieved on the second call for the geocoding endpoint.
4. `testReverseGeoCodingCacheHitWithEndpoint` -  Test case to verify that the cache is populated and successfully retrieved on the second call for the reverse geocoding endpoint.
5. `testGeoCodingCacheMiss` -  Test case to verify that calling the geocoding endpoint with a specific address - *goa* results in a cache miss.
6. `testGeoCodingCacheEviction` -  Test case to verify the eviction of cache entries for geocoding information.
7. `testGetGeoCodeNegative` - Test case to verify the geocoding endpoint with an invalid address.
8. `testGetReverseGeoCodeNegative` - Test case to verify the reverse geocoding endpoint with invalid parameters.



