package hu.vanio.restdemo.mail;

import javax.mail.MessagingException;

/**
 *
 * @author meszaros.andras
 */
public interface MailSender {

    void send(String from, String to, String subject, String body) throws MessagingException;
    
}
