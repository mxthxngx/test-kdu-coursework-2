package com.caching.service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Data
@Component
@RequiredArgsConstructor
public class RemoteAPIForward {

    @Value("${app.key}")
    private String apiKey;

    String address ="";

    @Value("${app.urlForward}")
    private String url ;
}
