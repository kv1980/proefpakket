package be.vdab.proefpakket.entities;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.valueobjects.Adres;

@RunWith(SpringRunner.class)
public class BrouwerTest {
	private Brouwer brouwer;
	private static final long CORRECT_ONDERNEMINGSNR = 426388541;
	private static final long INCORRECT_ONDERNEMINGSNR = 426388542;
	private Validator validator;
	
	@Before
	public void before() {
		brouwer = new Brouwer("brouwernaam",new Adres());
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    validator = factory.getValidator();
	}
	
	@Test
	public void het_ondernemingsnummer_mag_niet_leeg_zijn() {
		brouwer.setOndernemingsNr(null);
		Set<ConstraintViolation<Brouwer>> constraintViolations = validator.validate(brouwer);
        assertEquals( 1, constraintViolations.size() );
        assertEquals( "mag niet leeg zijn", constraintViolations.iterator().next().getMessage() );
	}
	
	@Test
	public void het_ondernemingsnummer_moet_een_positief_getal_zijn() {
		brouwer.setOndernemingsNr(-1*CORRECT_ONDERNEMINGSNR);
		Set<ConstraintViolation<Brouwer>> constraintViolations = validator.validate(brouwer);
        assertEquals( 1, constraintViolations.size() );
        assertEquals( "moet positief zijn", constraintViolations.iterator().next().getMessage() );
	}
	
	@Test
	public void het_ondernemingsnummer_mag_geen_incorrect_controlegetal_krijgen() {
		brouwer.setOndernemingsNr(INCORRECT_ONDERNEMINGSNR);
		Set<ConstraintViolation<Brouwer>> constraintViolations = validator.validate(brouwer);
        assertEquals( 1, constraintViolations.size() );
        assertEquals( "geen geldig ondernemingsnummer", constraintViolations.iterator().next().getMessage() );
	}
	
	@Test
	public void het_ondernemingsnummer_moet_een_correct_controlegetal_hebben() {
		brouwer.setOndernemingsNr(CORRECT_ONDERNEMINGSNR);
		Set<ConstraintViolation<Brouwer>> constraintViolations = validator.validate(brouwer);
        assertEquals( 0, constraintViolations.size() );
	}
}