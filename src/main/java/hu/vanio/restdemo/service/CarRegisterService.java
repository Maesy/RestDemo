package hu.vanio.restdemo.service;

import hu.vanio.restdemo.controller.CarRegisterController;
import hu.vanio.restdemo.entity.CarEntity;
import hu.vanio.restdemo.exceptions.CarNotFoundException;
import hu.vanio.restdemo.mail.MailSender;
import hu.vanio.restdemo.repository.CarRegisterRepository;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author meszaros.andras
 */
@Service
public class CarRegisterService {

    /**
     * Logger
     */
    private static final Logger Log = LoggerFactory.getLogger(CarRegisterController.class);
    
    private final MailSender smtpMailSender;
    
    private final CarRegisterRepository repository;
    
    private final String recipient;
    
    private final String sender;

    /**
     *
     * @param repository
     * @param smtpMailSender
     * @param mailRecipient
     * @param mailSender
     */
    @Autowired
    public CarRegisterService(CarRegisterRepository repository, MailSender smtpMailSender, @Value("${mail.recipient}") String mailRecipient, @Value("${mail.sender}") String mailSender) {
        Log.info("===>CarRegisterService: " + repository);
        Log.info("===>CarRegisterService: " + smtpMailSender);
        this.repository = repository;
        this.smtpMailSender = smtpMailSender;
        this.recipient = mailRecipient;
        this.sender = mailSender;
    }

    public CarEntity getCarById(Long id) throws MessagingException, CarNotFoundException {
        Log.info("GET service: " + id);
        CarEntity carEntity = null;
        try {
            carEntity = this.repository.get(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new CarNotFoundException();
        }
        this.sendMail(
                    "GET action - " + ((carEntity != null) ? "Success" : "Failed"),
                    "Querying element with the id: " + id + " result: " + carEntity);
        
        return carEntity;
    }

    public Boolean updateCar(CarEntity carEntity) throws MessagingException, CarNotFoundException {
        Log.info("PUT service: " + carEntity);
        Boolean success = this.repository.update(carEntity);
        this.sendMail(
            "PUT action - " + (success ? "Success" : "Error"),
            "Updating element with the id: " + carEntity.getId() + ", result: " + carEntity
        );
         
        return success;
    }

    public List<CarEntity> searchCar(String searchToken) throws MessagingException {
        Log.info("Search service: " + searchToken);
        List<CarEntity> list = new ArrayList<>();
        try {
            list = this.repository.search(searchToken);
        } catch (EmptyResultDataAccessException ex) {
            
        }
        this.sendMail(
                    "SEARCH action - " + ((list.isEmpty()) ? "Success" : "Failed"),
                    "Querying element with the type containing: " + searchToken + " result: " + list
            );
        return list;
    }

    public Boolean deleteCar(Long id) throws MessagingException, CarNotFoundException {
        Log.info("DELETE service: " + id);
        Boolean success = this.repository.delete(id);
        this.sendMail(
            "DELETE action - " + (success ? "Success" : "Error"),
            (success ? "Success " : "Error ") + " deleting element with id: " + id
        );
        
        return success;
    }

    public Boolean addCar(CarEntity carEntity) throws MessagingException {
        Log.info("POST service: " + carEntity);
        Boolean success = this.repository.add(carEntity);
        this.sendMail(
            "POST action - " + (success ? "Success" : "Error"),
            (success ? "Success " : "Error ") + " inserting: " + carEntity + " to the database!"
        );
        
        return success;
    }
    
    /**
     * 
     * @param subject
     * @param body
     * @throws MessagingException 
     */
    private void sendMail(String subject, String body) throws MessagingException {
        this.smtpMailSender.send(this.sender, this.recipient, subject, body);
    }

}
