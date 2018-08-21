package be.vdab.proefpakket.restclients;

import com.fasterxml.jackson.annotation.JsonProperty;

class WeatherData {
	@JsonProperty("main")
	private MainWeather mainWeather;

	public MainWeather getMainWeather() {
		return mainWeather;
	}

	public void setMainWeather(MainWeather mainWeather) {
		this.mainWeather = mainWeather;
	}
}