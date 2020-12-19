package sg.edu.iss.ca.service;

import sg.edu.iss.ca.email.AbstractMail;
import sg.edu.iss.ca.email.AccountMail;
import sg.edu.iss.ca.email.HTMLMail;
import sg.edu.iss.ca.email.RestockMail;
import sg.edu.iss.ca.email.SimpleMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    // Use it to send Simple text emails
    public void sendSimpleMail(SimpleMail mail) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());

        mailSender.send(message);
    }

    // Use it to send HTML emails
    public void sendHTMLMail(HTMLMail mail) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");

        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        message.setContent(mail.getContent(), "text/html");

        mailSender.send(message);
    }
    
    public void sendAccountMail(AccountMail mail, String username, String password) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent(username, password));

        mailSender.send(message);
    }
    
    public void sendRestockMail(RestockMail mail, String productName, String supplierName) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent(productName, supplierName));

        mailSender.send(message);
    }
}
