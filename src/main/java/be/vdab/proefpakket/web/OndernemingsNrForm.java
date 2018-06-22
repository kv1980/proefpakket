package be.vdab.proefpakket.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import be.vdab.proefpakket.constraints.OndernemingsNr;

public class OndernemingsNrForm {
	@NotNull
	@Positive
	@OndernemingsNr
	private long ondernemingsNr;

	public long getOndernemingsNr() {
		return ondernemingsNr;
	}

	public void setOndernemingsNr(long ondernemingsNr) {
		this.ondernemingsNr = ondernemingsNr;
	}
}