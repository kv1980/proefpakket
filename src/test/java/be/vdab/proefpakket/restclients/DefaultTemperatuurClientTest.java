package be.vdab.proefpakket.restclients;

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
	public void temperatuur_wordt_correct_ingelezen() {
		BigDecimal temp = client.getTemperatuur("Dentergem");
		System.out.println("----------------------"+temp);
	}
	
	@Test (expected = TemperatuurNietGevondenException.class)
	public void temperatuur_van_onbestaande_plaats_wordt_niet_ingelezen() {
		BigDecimal temp = client.getTemperatuur("XXX");
	}
}
