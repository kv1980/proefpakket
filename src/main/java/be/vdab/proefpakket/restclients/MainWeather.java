package be.vdab.proefpakket.restclients;

import java.math.BigDecimal;
import java.math.RoundingMode;

class MainWeather {
	private BigDecimal temp;
	
	public void setTemp(BigDecimal temp) {
		this.temp = temp;
	}
	public BigDecimal getTemp() {
		return temp;
	}
}