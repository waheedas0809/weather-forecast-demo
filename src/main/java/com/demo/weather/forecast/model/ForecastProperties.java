package com.demo.weather.forecast.model;

import java.util.List;

public class ForecastProperties {
	private String updated;
	private List<Period> periods;
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public List<Period> getPeriods() {
		return periods;
	}
	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}
	
}
