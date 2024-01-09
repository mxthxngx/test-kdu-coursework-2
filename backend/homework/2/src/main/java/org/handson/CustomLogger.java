package org.handson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLogger {
    static void customLogger(char type, String message) {
        switch (type) {
            case 'e' -> logger.error(message);
            case 'i' -> logger.info(message);
            case 'd' -> logger.debug(message);

        }
    }

    private static Logger logger = LoggerFactory.getLogger(APIResponseParser.class);
}
