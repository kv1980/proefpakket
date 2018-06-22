package be.vdab.proefpakket.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OndernemingsNrValidator implements ConstraintValidator<OndernemingsNr,Long> {

	@Override
	public void initialize(OndernemingsNr ondernemingsNr) {
	}
	
	@Override
	public boolean isValid(Long ondernemingsNr, ConstraintValidatorContext context) {
		if (ondernemingsNr == null || ondernemingsNr < 0) {
			return true;
		}
		long laatsteTweeCijfers = ondernemingsNr % 100;
		long controleGetal = 97 - (ondernemingsNr /100) % 97;
		return laatsteTweeCijfers == controleGetal;
	}
}
