package com.caching.service.service;

import com.caching.service.dto.ResponseDTOForward;
import com.caching.service.dto.ResponseDTOReverse;
import com.caching.service.model.ForwardGeoCodingParams;
import com.caching.service.model.RemoteAPIForward;
import com.caching.service.model.RemoteAPIReverse;
import com.caching.service.model.ReverseGeoCodingParams;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GeoLocationService {

    private final RemoteAPIForward remoteAPIForward;
    private final RemoteAPIReverse remoteAPIReverse;
    private final CaffeineCacheManager caffeineCacheManager;

    @Autowired
    public GeoLocationService(RemoteAPIForward remoteAPIForward, RemoteAPIReverse remoteAPIReverse, CaffeineCacheManager caffeineCacheManager) {
        this.remoteAPIForward = remoteAPIForward;
        this.remoteAPIReverse = remoteAPIReverse;
        this.caffeineCacheManager = caffeineCacheManager;
    }

    /**
     * Perform forward geocoding using the given address.
     *
     * @param params the forward geocoding parameters containing the address
     * @return a list of response DTOs containing the geocoding information
     */
    @Cacheable(cacheNames = "geocoding", key = "#params.address", unless = "#params.getAddress().toLowerCase().contains('goa') or #result[0].display_name.toLowerCase().contains('goa')")
    public List<ResponseDTOForward> performForwardGeoCoding(ForwardGeoCodingParams params) throws IOException {
        validateAddress(params.getAddress());

        List<ResponseDTOForward> cachedResponse = getCachedResponse("geocoding", params.getAddress(), ResponseDTOForward.class);
        if (cachedResponse != null) {
            return cachedResponse;
        }

        List<ResponseDTOForward> response = fetchForwardGeoData(params.getAddress());
        if (response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data returned from forward geocoding API.");
        }
        return response;
    }

    /**
     * Perform reverse geocoding based on the given parameters.
     *
     * @param params the parameters for reverse geocoding
     * @return a list of reverse geocoding responses
     */
    @Cacheable(cacheNames = "reverse-geocoding", key = "{#params.lat, #params.lon}")
    public List<ResponseDTOReverse> performReverseGeoCoding(ReverseGeoCodingParams params) {
        String cacheKey = params.getLat() + "," + params.getLon();

        List<ResponseDTOReverse> cachedResponse = getCachedResponse("reverse-geocoding", cacheKey, ResponseDTOReverse.class);
        if (cachedResponse != null) {
            return cachedResponse;
        }

        List<ResponseDTOReverse> response = fetchReverseGeoData(params.getLat(), params.getLon());
        if (response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data returned from reverse geocoding API.");
        }
        cacheResponse("reverse-geocoding", cacheKey, response);
        return response;
    }

    private void validateAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address parameter is missing or empty.");
        }
    }

    private <T> List<T> getCachedResponse(String cacheName, String key, Class<T> type) {
        return caffeineCacheManager.getCache(cacheName).get(key, List.class);
    }

    private void cacheResponse(String cacheName, String key, Object response) {
        caffeineCacheManager.getCache(cacheName).put(key, response);
    }

    private List<ResponseDTOForward> fetchForwardGeoData(String address) {
        String url = buildForwardGeoURL(address);
        String jsonResponse = fetchDataFromAPI(url);
        return parseForwardGeoResponse(jsonResponse);
    }

    private List<ResponseDTOReverse> fetchReverseGeoData(String lat, String lon) {
        String url = buildReverseGeoURL(lat, lon);
        String jsonResponse = fetchDataFromAPI(url);
        return parseReverseGeoResponse(jsonResponse);
    }

    private String buildForwardGeoURL(String address) {
        try {
            return remoteAPIForward.getUrl() + URLEncoder.encode(address, "UTF-8") + "&api_key=" + remoteAPIForward.getApiKey();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error encoding address.", e);
        }
    }

    private String buildReverseGeoURL(String lat, String lon) {
        try {
            return remoteAPIReverse.getUrlReverse() + URLEncoder.encode(lat, "UTF-8") +
                    remoteAPIReverse.getUrlReverseMid() + remoteAPIReverse.getLon() +
                    URLEncoder.encode(lon, "UTF-8") + "&api_key=" + remoteAPIReverse.getApiKey();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error encoding coordinates.", e);
        }
    }

    private String fetchDataFromAPI(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new ResponseStatusException(HttpStatus.valueOf(responseCode), "Error while fetching data from API.");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching data from API.", e);
        }
    }

    private List<ResponseDTOForward> parseForwardGeoResponse(String jsonResponse) {
        List<ResponseDTOForward> locations = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonResponse);

            for (JsonNode node : root) {
                locations.add(new ResponseDTOForward(
                        node.get("display_name").asText(),
                        node.get("lat").asText(),
                        node.get("lon").asText()
                ));
            }
        } catch (Exception e) {
            log.error("Error parsing forward geocoding response: ", e);
        }
        return locations;
    }

    private List<ResponseDTOReverse> parseReverseGeoResponse(String jsonResponse) {
        List<ResponseDTOReverse> addresses = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode addressNode = root.get("address");

            ResponseDTOReverse address = new ResponseDTOReverse();
            address.setRoad(getJsonNodeValue(addressNode, "road"));
            address.setHouseNumber(getJsonNodeValue(addressNode, "house_number"));
            address.setSuburb(getJsonNodeValue(addressNode, "suburb"));
            address.setNeighbourhood(getJsonNodeValue(addressNode, "neighbourhood"));
            address.setCity(getJsonNodeValue(addressNode, "city"));
            address.setCounty(getJsonNodeValue(addressNode, "county"));
            address.setState(getJsonNodeValue(addressNode, "state"));
            address.setPostcode(getJsonNodeValue(addressNode, "postcode"));
            address.setCountry(getJsonNodeValue(addressNode, "country"));
            address.setCountryCode(getJsonNodeValue(addressNode, "country_code"));

            addresses.add(address);
        } catch (Exception e) {
            log.error("Error parsing reverse geocoding response: ", e);
        }
        return addresses;
    }

    private String getJsonNodeValue(JsonNode node, String fieldName) {
        JsonNode fieldNode = node.get(fieldName);
        return fieldNode != null && !fieldNode.isNull() ? fieldNode.asText() : "-";
    }
}