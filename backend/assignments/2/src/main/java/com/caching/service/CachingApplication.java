package com.caching.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CachingApplication {

	/**
	 * The main function for running the CachingApplication with the "dev" profile.
	 *
	 * @param  args  the command-line arguments
	 * @return       void
	 */
	public static void main(String[] args) {
		new SpringApplicationBuilder(CachingApplication.class)
				.profiles("dev")
				.run(args);
	}

}
