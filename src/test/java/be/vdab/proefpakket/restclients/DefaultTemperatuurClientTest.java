package be.vdab.proefpakket.restclients;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.proefpakket.exceptions.TemperatuurNietGevondenException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultTemperatuurClientTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private DefaultTemperatuurClient client;
	
	@Test
	public void temperatuur_van_een_bestaande_plaats_wordt_ingelezen() {
		assertEquals(BigDecimal.class, client.getTemperatuur("Dentergem").getClass());
	}
	
	@Test (expected = TemperatuurNietGevondenException.class)
	public void temperatuur_van_onbestaande_plaats_wordt_niet_ingelezen() {
		assertEquals(BigDecimal.class, client.getTemperatuur("XXX").getClass());
	}
}