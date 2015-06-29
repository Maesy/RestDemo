package hu.vanio.restdemo.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 *
 * @author meszaros.andras
 */
public class SmtpMailSender implements MailSender
{
    private static final Logger Log = LoggerFactory.getLogger(SmtpMailSender.class);
    
    private final JavaMailSender javaMailSender;
    
    /**
     *
     * @param javaMailSender
     */
    public SmtpMailSender(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }
    
    /**
     *
     * @param from
     * @param to
     * @param subject
     * @param body
     * @throws javax.mail.MessagingException
     */
    @Override
    public void send(String from, String to, String subject, String body) throws MessagingException
    {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        
        helper = new MimeMessageHelper(message, true);
        Log.info("Sending mail from: " + from);
        Log.info("Sending mail to: " + to);
        Log.info("Subject: " + subject);
        Log.info("Body: " + body);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);
        
        javaMailSender.send(message);
    }
}
