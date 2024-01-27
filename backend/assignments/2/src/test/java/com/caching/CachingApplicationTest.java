package com.caching;

import com.caching.service.CachingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = CachingApplication.class)

class CachingApplicationTest {

    @Test
    void contextLoads() {
        // This test method can be empty; it's just used to ensure that the application context loads successfully
    }
}