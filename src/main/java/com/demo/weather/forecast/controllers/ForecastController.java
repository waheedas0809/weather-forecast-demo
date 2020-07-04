package com.demo.weather.forecast.controllers;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.weather.forecast.common.ServiceException;
import com.demo.weather.forecast.model.Points;
import com.demo.weather.forecast.services.ForecastService;

@RestController
@RequestMapping("forecast/api")
@Validated
public class ForecastController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastController.class);
	@Autowired
	ForecastService forecastService;
	
	@GetMapping(value = "/rawdata")
	public Points getWeatherData(@RequestParam @NotBlank @Size(min = 3) String inputData) throws ServiceException {

		LOGGER.info("In getWeatherData with inputs {}", inputData);
		return forecastService.getPointsData(inputData);
	}
	
	@GetMapping(value = "/processed-data")
	public String getWeatherProcessedData(@RequestParam @NotBlank @Size(min = 3) String inputData) throws ServiceException {

		LOGGER.info("In getWeatherProcessedData with inputs {}", inputData);
		return forecastService.getWeatherProcessedData(inputData);
	}


}
