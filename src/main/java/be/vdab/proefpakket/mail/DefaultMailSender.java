package be.vdab.proefpakket.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import be.vdab.proefpakket.exceptions.MailKanNietWordenVerzondenException;

@Component
class DefaultMailSender implements MailSender{
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMailSender.class);
	private final JavaMailSender sender;
	
	DefaultMailSender(JavaMailSender sender) {
		this.sender = sender;
	}

	@Async
	@Override
	public void verstuurProefpakket(String brouwernaam,String emailAdres,String voornaam) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(emailAdres);
			message.setSubject("test voor mijn automatisch verzonden mail");
			message.setText("Beste "+voornaam+",\n\n"+
							"Bedankt voor uw interesse. U ontvangt uw proefpakket "+brouwernaam+" binnenkort.\n\n"+
							"Vele groetjes,\n"+
							"Kristof");
			sender.send(message);
		} catch (MailException ex) {
			LOGGER.error("De mail van de bestelling kan niet worden verzonden.",ex);
			throw new MailKanNietWordenVerzondenException();
		}
	}
}
