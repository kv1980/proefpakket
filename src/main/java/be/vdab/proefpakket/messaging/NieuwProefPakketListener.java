package be.vdab.proefpakket.messaging;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import be.vdab.proefpakket.mail.MailSender;

@Component
class NieuwProefpakketListener {
	private final MailSender mailSender;

	NieuwProefpakketListener(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@JmsListener(destination ="${nieuwProefpakketQueue}")
	void ontvangBoodschap(EmailData emailData) {
		mailSender.verstuurProefpakket(emailData.getBrouwerNaam(),emailData.getEmailAdres(),emailData.getVoornaam());
	}
	
}
