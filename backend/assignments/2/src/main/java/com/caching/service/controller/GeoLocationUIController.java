package com.caching.service.controller;

import com.caching.service.dto.ResponseDTOForward;
import com.caching.service.model.ForwardGeoCodingParams;
import com.caching.service.model.ReverseGeoCodingParams;
import com.caching.service.service.GeoLocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
@Controller
@Slf4j
public class GeoLocationUIController {
    public GeoLocationUIController(GeoLocationService service, ForwardGeoCodingParams params, ReverseGeoCodingParams reverseGeoCodingParams) {
        this.service = service;
        this.params = params;
        this.reverseGeoCodingParams = reverseGeoCodingParams;
    }


    private GeoLocationService service;

    private ReverseGeoCodingParams reverseGeoCodingParams;

    private ForwardGeoCodingParams params;



    /**
     * A method that performs forward geocoding.
     *
     * @param  address  the address to be geocoded
     * @param  model    the model object to add attributes
     * @return          the name of the view to be rendered
     */
    @GetMapping(value = "/geocodingUI")
    public String forwardGeoCodingHTML(@RequestParam("address") String address, Model model) throws IOException {
        model.addAttribute("address", address);

        params.setAddress(address);
        log.info(params.getAddress());

        var locations = service.performForwardGeoCoding(params);
        model.addAttribute("locations", locations);

        return "locations";
    }

    /**
     * Performs reverse geocoding based on the provided latitude and longitude.
     *
     * @param  lat   the latitude as a String
     * @param  lon   the longitude as a String
     * @param  model the model for the view
     * @return       the view name for the reverse geocoding results
     */
    @GetMapping(value = "/reverse-geocodingUI")
    public String reverseGeoCoding(@RequestParam("lat") String lat, @RequestParam("lon") String lon, Model model) throws IOException {
        model.addAttribute("lat", lat);
        model.addAttribute("lon", lon);

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
        model.addAttribute("addresses", addresses);
        log.info("Address got is: " + addresses.toString());

        return "addresses";
    }

    /**
     * A description of the entire Java function.
     *
     * @param  model	description of parameter
     * @return       description of return value
     */
    @GetMapping(value = "/")
    public String welcome(Model model) {
        return "welcome";
    }
}
