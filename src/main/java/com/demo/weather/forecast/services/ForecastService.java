package com.demo.weather.forecast.services;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.weather.forecast.common.ServiceException;
import com.demo.weather.forecast.model.Forecast;
import com.demo.weather.forecast.model.Points;

@Service
public class ForecastService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastService.class);
    
	@Autowired
	public ForecastProcessor forecastProcessor;
	
	@PostConstruct
    public void init() 	
	{
		// for any initializations or one time loads		
	}

	public Points getPointsData(String inputData) throws ServiceException {
		try {
		Points points = forecastProcessor.getPointsData(inputData);
		return points;
		} catch (Exception e) {
			LOGGER.error("Exception in getPointsData method" + e.getMessage(), e);
			throw new ServiceException("4321", "invlaid input");
		}
	}
	
	public String getWeatherProcessedData(String inputData) throws ServiceException {
		if (inputData == null || inputData.trim().length() == 0) {
			throw new ServiceException("1234","Empty input");
		}
		Points points = forecastProcessor.getPointsData(inputData);
		Forecast forecast  = forecastProcessor.getForecastData(points.getProperties().getForecast());
		StringBuffer buffer = new StringBuffer();
		// Printing
		buffer.append("--------------------------------------------------------------------------------------<br>");
		buffer.append("City name : " + points.getProperties().getRelativeLocation().getProperties().getCity() + "<br>");
		buffer.append("State  name : " + points.getProperties().getRelativeLocation().getProperties().getState() + "<br>");
		buffer.append("Time : " + forecast.getProperties().getUpdated() + "<br>");
		buffer.append("<br>--------------------------------------------------------------------------------------<br>");
		forecast.getProperties().getPeriods().stream().forEach( period -> {
			buffer.append("Day : " + period.getName() + " Time : " + period.getStartTime());
			// buffer.append(period.isDaytime() ? " Day " : " Night");
			buffer.append("<br>Short Forecast : " + period.getShortForecast());
			buffer.append("<br>Detailed Forecast : " + period.getDetailedForecast());
			buffer.append("<br>Wind Speed : " + period.getWindSpeed());
			buffer.append("<br>Temperature: " + period.getTemperature() + period.getTemperatureUnit());
			buffer.append("<br>--------------------------------------------------------------------------------------<br>");
		});
		// Printing using logging
		LOGGER.info("--------------------------------------------------------------------------------------");
		LOGGER.info("City name : " + points.getProperties().getRelativeLocation().getProperties().getCity());
		LOGGER.info("State  name : " + points.getProperties().getRelativeLocation().getProperties().getState());
		LOGGER.info("Time : " + forecast.getProperties().getUpdated());
		LOGGER.info("--------------------------------------------------------------------------------------");
		forecast.getProperties().getPeriods().stream().forEach( period -> {
			LOGGER.info("Day : " + period.getName() + " Time : " + period.getStartTime());
			// LOGGER.info(period.isDaytime() ? " Day " : " Night");
			LOGGER.info("Short Forecast : " + period.getShortForecast());
			LOGGER.info("Detailed Forecast : " + period.getDetailedForecast());
			LOGGER.info("Wind Speed : " + period.getWindSpeed());
			LOGGER.info("Temperature: " + period.getTemperature() + period.getTemperatureUnit());
			LOGGER.info("--------------------------------------------------------------------------------------");
		});
		return buffer.toString();
	}
	
}
