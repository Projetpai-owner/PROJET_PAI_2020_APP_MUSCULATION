package fr.univ.lille.fil.mbprestservice.service;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.exceptions.EmailSendingException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
/**
 * Service pour l'envoie des email.
 * @author Clément
 *
 */
public class MailService implements Runnable{
	
	private String username = "projetpai2020fa@gmail.com";
	private String password = "paifa2020";
	private String destination;
	private String object;
	private String msg;
	
	
	public MailService (String destination, String object, String msg) {
		this.destination=destination;
		this.object=object;
		this.msg=msg;
	}

	@Override
	public void run() {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
				
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(this.username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destination));
			message.setSubject(object);
			message.setText(msg);
			Transport.send(message);
			
		} catch (MessagingException e) {
			throw new EmailSendingException();			
		}
		
	}

}
