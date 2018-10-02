package com.jackson.cursomc.services;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;

import com.jackson.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido pedido);
	
	void sendHtmlEmail(MimeMessage msg);

}
