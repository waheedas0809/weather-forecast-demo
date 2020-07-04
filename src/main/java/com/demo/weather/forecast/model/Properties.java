package com.demo.weather.forecast.model;

public class Properties {

	private String cwa;
	private int  gridX;
	private int  gridY;
    private String  forecast;
    private RelativeLocation  relativeLocation;
	public String getCwa() {
		return cwa;
	}
	public void setCwa(String cwa) {
		this.cwa = cwa;
	}
	public int getGridX() {
		return gridX;
	}
	public void setGridX(int gridX) {
		this.gridX = gridX;
	}
	public int getGridY() {
		return gridY;
	}
	public void setGridY(int gridY) {
		this.gridY = gridY;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	public RelativeLocation getRelativeLocation() {
		return relativeLocation;
	}
	public void setRelativeLocation(RelativeLocation relativeLocation) {
		this.relativeLocation = relativeLocation;
	}
    
}