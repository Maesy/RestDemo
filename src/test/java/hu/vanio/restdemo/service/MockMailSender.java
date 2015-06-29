package hu.vanio.restdemo.service;

import hu.vanio.restdemo.mail.MailSender;
import javax.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Meszaros Andras <andras.meszaros@vanio.hu>
 */
public class MockMailSender implements MailSender {
    
    private final Logger logger = LoggerFactory.getLogger(MockMailSender.class);
    
    @Override
    public void send(String from, String to, String subject, String body) throws MessagingException {
        logger.info("Message sent from: " + from + ", to: " + to + " with the subject: " + subject + ", and message: " + body);
    }
    
}
