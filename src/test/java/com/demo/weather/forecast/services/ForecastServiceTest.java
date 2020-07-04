package com.demo.weather.forecast.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.weather.forecast.common.ServiceException;

@SpringBootTest
public class ForecastServiceTest {
	@Autowired
    private ForecastService forecastService;
	
	@Test
	public void whencalled_thenReturnPointsData() throws ServiceException {
		System.out.println();
		Assertions.assertEquals(forecastService.getPointsData("33.1237245,-96.7671707").getProperties().getForecast(),
				"https://api.weather.gov/gridpoints/FWD/90,118/forecast");
	}

}
