package be.vdab.proefpakket.web;

import javax.validation.constraints.NotNull;

import be.vdab.proefpakket.constraints.OndernemingsNr;

public class OndernemingsNrForm {
	@NotNull
	@OndernemingsNr
	private long ondernemingsNr;

	public long getOndernemingsNr() {
		return ondernemingsNr;
	}

	public void setOndernemingsNr(long ondernemingsNr) {
		this.ondernemingsNr = ondernemingsNr;
	}
}