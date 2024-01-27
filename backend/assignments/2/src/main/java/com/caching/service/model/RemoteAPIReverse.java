package com.caching.service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class RemoteAPIReverse {
    @Value("${app.key}")
    private String apiKey;

    String lat ="";
    String lon="";

    @Value("${app.urlReverse}")
    private String urlReverse ;
    @Value("${app.urlReverseMid}")
    private String urlReverseMid ;
}
