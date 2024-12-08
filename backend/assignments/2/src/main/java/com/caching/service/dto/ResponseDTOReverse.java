package com.caching.service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseDTOReverse {
    private String road;
    private String houseNumber;
    private String neighbourhood;
    private String suburb;
    private String city;
    private String county;
    private String state;
    private String postcode;
    private String country;
    private String countryCode;

}
