package com.demo.weather.forecast.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.demo.weather.forecast.services.ForecastService;
 
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ForecastController.class)
class ForecastControllerTest {
  @Autowired
  private MockMvc mockMvc;


  @MockBean
  private ForecastService forecastService;

  @Test
  void whenValidInput_thenReturns200() throws Exception {
	  MvcResult result = mockMvc.perform(get("/forecast/api/rawdata?inputData=33.1237245,-96.7671707"))
	            .andExpect(status().isOk())
	            .andReturn();

	String content = result.getResponse().getContentAsString();
	 
    mockMvc.perform(get("/forecast/api/rawdata?inputData=33.1237245,-96.7671707")).andExpect(status().isOk()).andExpect(content().string(""));
  }

}