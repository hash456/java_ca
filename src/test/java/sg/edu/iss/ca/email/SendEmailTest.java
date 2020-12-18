package sg.edu.iss.ca.email;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.iss.ca.service.MailSenderService;

@SpringBootTest
public class SendEmailTest{
	
	@Autowired
    private MailSenderService senderService;
	
	@Test
	void TestEmail() {
		
	    // send a simple mail
        senderService.sendSimpleMail(new SimpleMail("isabel889@gmail.com"));

        //send an HTML mail
        
        try{
        	senderService.sendHTMLMail(new HTMLMail("isabel889@gmail.com"));
        } catch (MessagingException me) {}
	}
}
