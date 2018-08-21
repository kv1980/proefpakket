package be.vdab.proefpakket.restclients;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import be.vdab.proefpakket.exceptions.TemperatuurNietGevondenException;

@Component
class DefaultTemperatuurClient implements TemperatuurClient {
	private final String uriTemplate;
	private final RestTemplate restTemplate;
	
	DefaultTemperatuurClient(@Value("${weatherURL}") String uriTemplate, RestTemplateBuilder builder){
		this.uriTemplate = uriTemplate;
		this.restTemplate = builder.build();
	}
	
	@Override
	public BigDecimal getTemperatuur(String plaatsnaam) {
		try {
			return restTemplate.getForObject(uriTemplate, WeatherData.class, plaatsnaam)
					           .getMainWeather()
					           .getTemp();
		} catch (RestClientException ex) {
			throw new TemperatuurNietGevondenException();
		}
	}
}