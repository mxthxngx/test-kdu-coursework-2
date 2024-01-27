package com.caching.service.service;

import com.caching.service.dto.ResponseDTOForward;
import com.caching.service.dto.ResponseDTOReverse;
import com.caching.service.model.ForwardGeoCodingParams;
import com.caching.service.model.RemoteAPIForward;
import com.caching.service.model.RemoteAPIReverse;
import com.caching.service.model.ReverseGeoCodingParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class GeoLocationService {


    private RemoteAPIForward remoteAPIForward;

    private RemoteAPIReverse remoteAPIReverse;
    @Autowired GeoLocationService(RemoteAPIForward remoteAPIForward, RemoteAPIReverse remoteAPIReverse,CaffeineCacheManager cacheManager){
      
        this.remoteAPIForward = remoteAPIForward;
        this.remoteAPIReverse = remoteAPIReverse;
        this.caffeineCacheManager=cacheManager;
    }


  private CaffeineCacheManager caffeineCacheManager;

    /**
     * Performs forward geocoding using the given address to retrieve a list of response DTOs.
     *
     * @param  params  the forward geocoding parameters containing the address
     * @return         a list of response DTOs containing the geocoding information
     */
    @Cacheable(cacheNames = "geocoding", key = "#params.address", unless = "#params.getAddress().toLowerCase().contains(\"goa\") or #result[0].display_name.toLowerCase().contains(\"goa\")")
    public List<ResponseDTOForward> performForwardGeoCoding(ForwardGeoCodingParams params) throws IOException {
        String address = params.getAddress();
        if (address == null || address.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address parameter is missing or empty");
        }
        List<ResponseDTOForward> cachedResponse = caffeineCacheManager.getCache("geocoding").get(address, List.class);
        log.info("Caching status for the address: "+params.getAddress()+" "+caffeineCacheManager.getCache("geocoding").get(params.getAddress()));
        if (cachedResponse != null) {
            return cachedResponse;
        }

        List<ResponseDTOForward> response = fetchDataFromAPI(address);
        if(response.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error while fetching data from API: empty response");
        }
        log.info(response.toString());

       
        return response;
    }

    /**
     * Parses the given JSON response and returns a list of ResponseDTOForward objects.
     *
     * @param  jsonResponse  the JSON response to be parsed
     * @return              a list of ResponseDTOForward objects parsed from the JSON response
     */
    private List<ResponseDTOForward> parseResponse(String jsonResponse) {
        List<ResponseDTOForward> locations = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonResponse);

            for (JsonNode node : root) {
                String displayName = node.get("display_name").asText();
                String lat = node.get("lat").asText();
                String lon = node.get("lon").asText();

                ResponseDTOForward location = new ResponseDTOForward(displayName, lat, lon);
                locations.add(location);
                log.info("Successfully retrieved response");
            }
        } catch (Exception e) {
            log.error("Error parsing response: " + e.getMessage());
        }

        return locations;
    }
    /**
     * Parses the given JSON response and returns a list of ResponseDTOReverse objects.
     *
     * @param  jsonResponse  the JSON response to parse
     * @return               a list of ResponseDTOReverse objects
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
    /**
     * Gets the string value from the specified JSON node field, or returns "-" if the field is null.
     *
     * @param  node       the JSON node to retrieve the field from
     * @param  fieldName  the name of the field to retrieve
     * @return           the string value of the field, or "-" if the field is null
     */
    private String getStringOrNull(JsonNode node, String fieldName) {
        JsonNode fieldNode = node.get(fieldName);
        return fieldNode != null && !fieldNode.isNull() ? fieldNode.asText() : "-";
    }


    /**
     * Fetches data from the API using the given address for geocoding.
     *
     * @param  address   the address for geocoding
     * @return          a list of ResponseDTOForward objects
     */
    private List<ResponseDTOForward> fetchDataFromAPI(String address) {
        try {
            log.info("Initiating API call for geocoding");
            String urlComplete = remoteAPIForward.getUrl() + URLEncoder.encode(address, "UTF-8")
                    + "&api_key=" + remoteAPIForward.getApiKey();
            HttpURLConnection connection = (HttpURLConnection) new URL(urlComplete).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            log.info("Response code: "+responseCode);
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new ResponseStatusException(HttpStatus.valueOf(responseCode), "Error while fetching data from API");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            if(response.isEmpty())
            {
                log.error("Response came empty!");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found");
            }

            log.info("response is: "+response);
            return parseResponse(response.toString());
        } catch (Exception e) {
            log.error("Error while fetching data from API", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request to fetching data from API "+e.toString(), e);
        }
    }

    /**
     * Fetches data from the API for reverse geocoding.
     *
     * @param  lat  latitude parameter for the API call
     * @param  lon  longitude parameter for the API call
     * @return      list of ResponseDTOReverse objects
     */
    private List<ResponseDTOReverse> fetchDataFromAPIReverse(String lat, String lon) {
        try {
            log.info("Initiating API call for reverse geocoding");
            String urlComplete = remoteAPIReverse.getUrlReverse() + URLEncoder.encode(lat, "UTF-8") + remoteAPIReverse.getUrlReverseMid()
                    + remoteAPIReverse.getLon() + lon + "&api_key=" + remoteAPIReverse.getApiKey();
            log.info("Link is " + urlComplete);
            HttpURLConnection connection = (HttpURLConnection) new URL(urlComplete).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new ResponseStatusException(HttpStatus.valueOf(responseCode), "Error while fetching data from reverse API status code");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return parseResponseReverse(response.toString());
        } catch (Exception e) {
            log.error("Error while fetching data from reverse API", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while fetching data from reverse API "+e.toString(),e);
        }
    }

    /**
     * Perform reverse geocoding based on the given parameters.
     *
     * @param  params   the parameters for reverse geocoding
     * @return          the list of reverse geocoding responses
     */
    @Cacheable(cacheNames = "reverse-geocoding", key = "{#lat, #lon}")
    public List<ResponseDTOReverse> performReverseGeoCoding(ReverseGeoCodingParams params) throws IOException {
        String cacheKey = params.getLat() + "," + params.getLon();
        List<ResponseDTOReverse> cachedResponse = caffeineCacheManager.getCache("reverseGeocoding").get(cacheKey, List.class);
        if (cachedResponse != null) {
            return cachedResponse;
        }

        List<ResponseDTOReverse> response = fetchDataFromAPIReverse( params.getLat(),  params.getLon());
        log.info(response.toString());
        caffeineCacheManager.getCache("reverseGeocoding").put(cacheKey, response);

        return response;
    }

}
