package be.vdab.proefpakket.restclients;

import java.math.BigDecimal;
import java.math.RoundingMode;

class MainWeather {
	private BigDecimal temp;
	
	private BigDecimal converteerInCelsius(BigDecimal tempInFahrenheit) {
		return temp.subtract(BigDecimal.valueOf(32))
				   .multiply(BigDecimal.valueOf(5))
				   .divide(BigDecimal.valueOf(9))
				   .setScale(1,RoundingMode.HALF_UP);
	}
	
	public void setTemp(BigDecimal temp) {
		this.temp = temp;
	}
	public BigDecimal getTemp() {
		return temp;
	}
}