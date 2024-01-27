package com.caching.service.controller;

import com.caching.service.dto.ResponseDTOForward;
import com.caching.service.dto.ResponseDTOReverse;
import com.caching.service.model.ForwardGeoCodingParams;
import com.caching.service.service.GeoLocationService;
import com.caching.service.model.ReverseGeoCodingParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class GeoLocationController {
       public GeoLocationController(GeoLocationService service, ForwardGeoCodingParams params, ReverseGeoCodingParams reverseGeoCodingParams) {
        this.service = service;
        this.params = params;
        this.reverseGeoCodingParams = reverseGeoCodingParams;
    }


    private GeoLocationService service;

    private ReverseGeoCodingParams reverseGeoCodingParams;

    private ForwardGeoCodingParams params;

    /**
     * Method to perform forward geocoding based on the given address.
     *
     * @param  address   the address for geocoding
     * @return          a list of response DTOs for forward geocoding
     */
    @GetMapping(value = "/geocoding")
    public List<ResponseDTOForward> forwardGeoCoding(@RequestParam("address") String address) throws IOException {
        params.setAddress(address);
        log.info(params.getAddress());

        List<ResponseDTOForward> locations = service.performForwardGeoCoding(params);

        try {

            ResponseDTOForward firstLocation = locations.get(0);
            log.debug("first location for null check "+firstLocation);
            return locations;

        } catch (IndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No addresses found for the given coordinates");
        }
    }

    /**
     * Performs reverse geocoding based on the provided latitude and longitude.
     *
     * @param  lat   the latitude as a String
     * @param  lon   the longitude as a String
     * @param  model the model for the view
     * @return       the view name for the reverse geocoding results
     */
    @GetMapping(value = "/reverse-geocoding")
    public List<ResponseDTOReverse> reverseGeoCoding(@RequestParam("lat") String lat, @RequestParam("lon") String lon, Model model) throws IOException {


        try {
            double latitude = Double.parseDouble(lat);
            double longitude = Double.parseDouble(lon);
            log.info(latitude+" "+longitude);
        } catch (NumberFormatException e) {
            // Handle the case where latitude or longitude is not a valid double
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Latitude or longitude is not valid", e);
        }

        reverseGeoCodingParams.setLat(lat);
        reverseGeoCodingParams.setLon(lon);

        var addresses = service.performReverseGeoCoding(reverseGeoCodingParams);

        log.info("Address got is: " + addresses.toString());
        if (addresses.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No addresses found for the given coordinates");
        }

        return addresses;
    }


}
