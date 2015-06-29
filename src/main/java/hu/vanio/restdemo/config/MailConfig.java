package hu.vanio.restdemo.config;

import hu.vanio.restdemo.mail.SmtpMailSender;
import hu.vanio.restdemo.mail.MailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;

/**
 *
 * @author meszaros.andras
 */
@Configuration
@EnableConfigurationProperties
public class MailConfig {
    
    @Bean
    public MailSender smtpMailSender(JavaMailSender javaMailSender){
        return new SmtpMailSender(javaMailSender);
    }
}
