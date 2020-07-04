package com.demo.weather.forecast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WeatherForecastDemoApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherForecastDemoApplication.class);

	public static void main(String[] args) {
		LOGGER.debug("This is a debug message");
		LOGGER.info("This is an info message");
		LOGGER.warn("This is a warn message");
		LOGGER.error("This is an error message");

		LOGGER.info("Entry point of the Weather Forecast Demo Application");
		SpringApplication.run(WeatherForecastDemoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		LOGGER.info("Rest Template Bean Created--------");
		return new RestTemplate();
	}

}
