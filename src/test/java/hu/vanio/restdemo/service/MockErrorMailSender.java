package hu.vanio.restdemo.service;

import hu.vanio.restdemo.mail.MailSender;
import javax.mail.MessagingException;

/**
 *
 * @author Meszaros Andras <andras.meszaros@vanio.hu>
 */
public class MockErrorMailSender implements MailSender {

    @Override
    public void send(String from, String to, String subject, String body) throws MessagingException {
        throw new MessagingException("An error occured during sending the mail!");
    }
    
}
