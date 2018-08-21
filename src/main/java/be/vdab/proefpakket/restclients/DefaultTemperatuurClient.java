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
	private final URI uri;
	private final RestTemplate restTemplate;
	
	DefaultTemperatuurClient(@Value("${weatherURL}") URI uri, RestTemplateBuilder builder){
		this.uri = uri;
		this.restTemplate = builder.build();
	}
	
	@Override
	public BigDecimal getTemperatuur(String plaatsnaam) {
		try {
			URI uriMetPlaatsnaam = URI.create(uri.toString().replace("XXX", plaatsnaam));
			WeatherData data = restTemplate.getForObject(uriMetPlaatsnaam, WeatherData.class);
			return data.getMainWeather().getTemp().setScale(1,RoundingMode.HALF_UP);
		} catch (RestClientException ex) {
			throw new TemperatuurNietGevondenException();
		}
	}
}