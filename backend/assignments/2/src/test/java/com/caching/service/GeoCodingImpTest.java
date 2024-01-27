package com.caching.service;

import com.caching.service.dto.ResponseDTOReverse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.beans.factory.annotation.Value;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@ComponentScan(basePackages = "com.kdu.caching")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GeoCodingImpTest {

    private static final long REQUEST_DELAY_MS=800;

    private static String mockAPIForwardResponse;


    private static String mockReverseAPIResponse;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CaffeineCacheManager cacheManager;


    /**
     * Negative test case for the geocoding endpoint with an invalid address.
     * The request is intentionally made with an invalid address to check for a negative response.
     *
     * @throws Exception If an error occurs during the tests.
     */

        @Test
    @Order(1)
     void testGetReverseGeoCodeNegative() {
        int resultCode = HttpStatus.OK.value();
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/reverse-geocoding")
                            .param("lat", "cat")
                            .param("lon", "-120.781462")
                            .contentType(MediaType.TEXT_HTML))
                    .andReturn();

            resultCode = result.getResponse().getStatus();
            if (resultCode != HttpStatus.OK.value()) {
                throw new Exception();
         }
        } catch (Exception e) {
            assertTrue(resultCode >= HttpStatus.BAD_REQUEST.value() &&
                            resultCode < HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "HTTP Status Code should be 4xx for client error");

        } finally {
            if (resultCode == HttpStatus.OK.value()) {
                fail("Expected HttpClientErrorException, but got a response with status code: " + resultCode);
            }
        }
    }

        /**
         * Test for getting geocode
         *
         * @throws Exception
         */
        @Test
    @Order(2)
     void testGetGeoCode() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/geocoding")
                        .param("address", "delhi")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


        String actualResponse = result.getResponse().getContentAsString();


        assertNotNull(actualResponse, "Response body should not be null");

        String expectedResponse = mockAPIForwardResponse;

        assertEquals(expectedResponse, actualResponse, "Coordinates should match");

    }
       /**
     * Test case to verify the reverse geocoding endpoint with invalid parameters.
     * The request is intentionally made with invalid lat and other scenarios to check for a negative response.
     *
     * @throws Exception If an error occurs during the tests.
     */
        @Test
    @Order(2)
     void testGetReverseGeoCode() throws Exception {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/reverse-geocoding")
                            .param("lat", "37.4311234")
                            .param("lon", "-120.7813136"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
        String actualResponse = result.getResponse().getContentAsString();
            System.out.println("Response "+result.getResponse());

        assertNotNull(actualResponse, "Response body should not be null");


        String expectedValue = mockReverseAPIResponse;
            System.out.println("mock reverse response "+mockReverseAPIResponse);

        assertEquals(expectedValue, actualResponse, "Address should match");
    }


    /**
     * Parses the JSON response to create a list of ResponseDTOReverse objects containing address details.
     *
     * @param  jsonResponse  the JSON response string to be parsed
     * @return               a list of ResponseDTOReverse objects representing the parsed addresses
     */
    private List<ResponseDTOReverse> parseResponseReverse(String jsonResponse) {
        List<ResponseDTOReverse> addresses = new ArrayList<>();
        log.info("jsonResponse : " + jsonResponse);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonResponse);

            JsonNode addressNode = root.get("address");

            String road = getStringOrNull(addressNode, "road");
            String houseNumber = getStringOrNull(addressNode, "house_number");
            String suburb = getStringOrNull(addressNode, "neighbourhood");
            String neighbourhood = getStringOrNull(addressNode, "suburb");
            String city = getStringOrNull(addressNode, "city");
            String county = getStringOrNull(addressNode, "county");
            String state = getStringOrNull(addressNode, "state");
            String postcode = getStringOrNull(addressNode, "postcode");
            String country = getStringOrNull(addressNode, "country");
            String countyCode = getStringOrNull(addressNode, "country_code");

            ResponseDTOReverse address = new ResponseDTOReverse();
            address.setRoad(road);
            address.setHouseNumber(houseNumber);
            address.setSuburb(suburb);
            address.setNeighbourhood(neighbourhood);
            address.setCity(city);
            address.setCounty(county);
            address.setState(state);
            address.setPostcode(postcode);
            address.setCountry(country);
            address.setCountryCode(countyCode);

            addresses.add(address);
            log.info("Successfully retrieved response");
        } catch (Exception e) {
            log.error("Error parsing response: " + e.getMessage());
        }

        return addresses;
    }
    private String getStringOrNull(JsonNode node, String fieldName) {
        JsonNode fieldNode = node.get(fieldName);
        return fieldNode != null && !fieldNode.isNull() ? fieldNode.asText() : "-";
    }
   /**
    * Test case to verify that the cache is populated and successfully retrieved on the second call for the geocoding endpoint.
    *
    * @throws Exception If an error occurs during the tests.
    */
    @Test
    @Order(3)
     void testGeoCodingCacheHitWithEndpoint() throws Exception {
        // Call the endpoint with a specific address
        // First request, cache should miss
        hitGeoCodingCache("delhi");
        Thread.sleep(1000);

        // Second request with the same address, cache should hit
        hitGeoCodingCache("delhi");

        // Ensure that the cache is populated after the first request
        assertNotNull("Cache 'geocoding' should not be null", cacheManager.getCache("geocoding").toString());
        assertNotNull("Cache entry 'delhi' should not be null", cacheManager.getCache("geocoding").get("delhi").toString());

        // Clear the cache after the test
        cacheManager.getCache("geocoding").clear();
    }

    /**
     * Test geo coding cache eviction.
     *
     * @throws Exception  description of exception
     */
    @Test
    @Order(6)
    void testGeoCodingCacheEviction() throws Exception {
        // Call the method with address goa
        hitGeoCodingCache("goa");

        // Introduce a 1-second gap
        Thread.sleep(1000);

        hitGeoCodingCache("delhi");

        // Verify that the cache miss count has increased for the first call
        assertNull(cacheManager.getCache("geocoding").get("goa"), "Cache evict unsuccessful - Cache entry must be null");
        assertNotNull(cacheManager.getCache("geocoding").get("delhi"), "Cache evict unsuccessful - Cache entry must be null");
    }


    /**
     * Test the reverse geocoding cache hit with endpoint.
     *
     * @throws Exception   if an error occurs
     */
    @Test
    @Order(4)
     void testReverseGeoCodingCacheHitWithEndpoint() throws Exception {
        ArrayList<Double> keyForCache = new ArrayList<>(List.of(37.431155, -120.781462));

        // Call the endpoint
        hitReverseGeoCodingCache("37.431155", "-120.781462");

        Thread.sleep(1000);

        // Verify that the cached value is retrieved the second time
        Object cachedValue = cacheManager.getCache("reverse-geocoding").get(keyForCache.toString());

        // Ensure that the cache is populated after the first request
        assertNotNull("Cache 'reverse-geocoding' should not be null", cacheManager.getCache("reverse-geocoding").toString());
        assertNotNull("Cache entry '[37.431155, -120.781462]' should not be null", (String) cachedValue);

        // Call the endpoint again
        hitReverseGeoCodingCache("37.431155", "-120.781462");

        // Verify that the cached value is retrieved the second time
        assertNotNull("Cache 'reverse-geocoding' should not be null", cacheManager.getCache("reverse-geocoding").toString());
        assertNotNull("Cache entry '[37.431155, -120.781462]' should not be null", (String) cachedValue);
    }

    /**
     * Hits the reverse geocoding cache with the given latitude and longitude coordinates.
     *
     * @param  lat  the latitude coordinate
     * @param  lon  the longitude coordinate
     * @throws Exception
     */
    private void hitReverseGeoCodingCache(String lat, String lon) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reverse-geocoding")
                        .param("lat", lat)
                        .param("lon", lon)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
        /**
     * A test for the method testGetGeoCodeNegative, which tests handling of invalid addresses.
     *
     */
    @Test
    @Order(4)
     void testGetGeoCodeNegative() {

        int resultCode = HttpStatus.OK.value();
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/geocoding")
                            .param("address", "invalid_address")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
            resultCode = result.getResponse().getStatus();
            if (resultCode != HttpStatus.OK.value()) {
                throw new Exception();
            }
        } catch (Exception e) {
            assertTrue(resultCode >= HttpStatus.BAD_REQUEST.value() &&
                            resultCode < HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "HTTP Status Code should be 4xx for client error");

        } finally {
            if (resultCode == HttpStatus.OK.value()) {
                fail("Expected HttpClientErrorException, but got a response with status code: " + resultCode);
            }
        }


    }

    /**
     * Test for geocoding cache miss.
     *
     * @throws Exception  exception thrown if an error occurs
     */
    @Test
    @Order(5)
     void testGeoCodingCacheMiss() throws Exception {
        // Call the method with a specific address (First time)
        hitGeoCodingCache("goa");

        // Verify that the cache miss count has increased for the first call
        assertNull(cacheManager.getCache("geocoding").get("goa"), "Cache miss unsuccessful: Cache entry must be null");
    }
    @AfterEach
    @BeforeEach

public void tearDown() throws InterruptedException {

        cacheManager.getCache("geocoding").clear();
        cacheManager.getCache("reverse-geocoding").clear();
        Thread.sleep(REQUEST_DELAY_MS);
    }
    private synchronized void hitGeoCodingCache(String address) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/geocoding")
                        .param("address", address)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }

    @BeforeAll
    public static void setup(@Value("${app.mockForwardAPIResponse}") String mockForwardResponse, @Value("${app.mockReverseAPIResponse}")String mockReverseResponse )  {

        mockAPIForwardResponse=mockForwardResponse;
        mockReverseAPIResponse = mockReverseResponse;
        System.out.println("mock forward response "+mockForwardResponse+" "+mockReverseResponse);
    }
}

