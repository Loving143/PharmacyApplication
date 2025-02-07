package com.pharmacy.security;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.pharmacy.services.EmailServices;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSending implements EmailServices  {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	private Configuration freemarkerConfig;
	
	@Value("${spring.mail.username")
	private String fromEmailId;
	
	
	
	@Override
	public void sendEmail(String recipient , String body , String subject, Map<String,Object>model) {
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//		mailMessage.setFrom(fromEmailId);
//		mailMessage.setTo(recipient);
//		mailMessage.setText(body);
//		mailMessage.setSubject(subject);
//		
//		javaMailSender.send(mailMessage);
		
		
		 try {
	            // Load FreeMarker template
	            Template template = freemarkerConfig.getTemplate("emailTemplate.ftl");

	            // Process the template into a String
	            String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

	            // Create a MIME message
	            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

	            // Set email details
	            helper.setFrom(fromEmailId);
	            helper.setTo(recipient);
	            helper.setSubject(subject);
	            helper.setText(htmlBody, true); // `true` enables HTML content

	            // Attach logo as an inline image
	            ClassPathResource logoResource = new ClassPathResource("static/images/pharmacy.jpg");
	            helper.addInline("logoImage", logoResource);
	            // Send email
	            javaMailSender.send(mimeMessage);

	        } catch (Exception e) {
	            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
	        }
	    }

	@Override
	public void sendPrescriptionEmail(String to, String qrCodeBase64) {
		
		
	}

		
	}

	


