package be.vdab.proefpakket.mail;

public interface MailSender {
	void verstuurProefpakket(String brouwernaam, String emailAdres,String voornaam);
}