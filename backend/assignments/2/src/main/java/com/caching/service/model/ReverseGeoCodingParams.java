package com.caching.service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class ReverseGeoCodingParams {
    private String lat;
    private String lon;
}
