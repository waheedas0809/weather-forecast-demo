package com.demo.weather.forecast.services;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.demo.weather.forecast.common.ServiceException;
import com.demo.weather.forecast.model.Forecast;
import com.demo.weather.forecast.model.Points;

@Component
public class ForecastProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastProcessor.class);
	
	@Autowired
	public RestTemplate restTemplate;


	public Points getPointsData(String inputData) throws ServiceException {

		try {
			String url  = "https://api.weather.gov/points/" + inputData;
			URI uri;
			uri = new URI(url);

			HttpHeaders headers = new HttpHeaders();
			headers.set("X-COM-PERSIST", "true");
			headers.set("X-COM-LOCATION", "USA");

			HttpEntity<?> entity = new HttpEntity<>(headers);
			LOGGER.info("Calling wheather API : " + url);
			ResponseEntity<Points> result = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<Points>() {
			});
			return result.getBody();
		} catch (URISyntaxException e) {
			LOGGER.error("Exception while calling wheather API", e);
			throw new ServiceException("33333", "URISyntaxException");
		}
	}
	

	public Forecast getForecastData(String forecastUrl) throws ServiceException {


		try {
			URI uri;
			uri = new URI(forecastUrl);

			HttpHeaders headers = new HttpHeaders();
			headers.set("X-COM-PERSIST", "true");
			headers.set("X-COM-LOCATION", "USA");

			HttpEntity<?> entity = new HttpEntity<>(headers);
			LOGGER.info("Calling wheather forecast API : " + forecastUrl);
			ResponseEntity<Forecast> result = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<Forecast>() {
			});
			return result.getBody();
		} catch (URISyntaxException e) {
			LOGGER.error("Exception while calling wheather API", e);
			throw new ServiceException("44444", "URISyntaxException");
		}
	}
}
