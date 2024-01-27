package com.caching.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResponseDTOForward implements Serializable {
    private String display_name;
    private String lat;
    private String lon;
}
