/**
 * FleetLog
 * Apr 26, 2019 11:50:20 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.services;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.deepakdaneva.fleetlog.models.CustomUserDetails;

@Service
public class EmailService {

	/*
	 * Reference for creating this class:
	 * https://www.thymeleaf.org/doc/articles/springmail.html
	 */

	public static final Logger logger = LoggerFactory.getLogger(EmailService.class);

	@Value("${app.email.template.engine.encoding}")
	private String EMAIL_TEMPLATE_ENCODING;

	@Value("${spring.mail.username}")
	private String EMAIL_FROM;

	@Autowired
	private TemplateEngine emailTemplateEngine;

	//@Autowired
	//private JavaMailSender javaMailSender;

	@Async
	public void sendRegistrationEmail(CustomUserDetails userDetails) {
		/*final Context context = new Context(Locale.ENGLISH);
		context.setVariable("userDetails", userDetails);

		final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, EMAIL_TEMPLATE_ENCODING);

		try {
			mimeMessageHelper.setSubject("Welcome!");
			mimeMessageHelper.setFrom(EMAIL_FROM);
			mimeMessageHelper.setTo(userDetails.getEmail());

			// Create the HTML body using Thymeleaf
			final String registrationEmailHTMLContent = this.emailTemplateEngine.process("registration", context);

			mimeMessageHelper.setText(registrationEmailHTMLContent, true);

			// Finally Send registration email
			this.javaMailSender.send(mimeMessage);

		} catch (MessagingException me) {
			logger.error("Can not send registration email for userid: " + userDetails.getId() + me.getMessage(), me);
		}*/
	}

}
