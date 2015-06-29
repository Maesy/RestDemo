package hu.vanio.restdemo.repository;

import hu.vanio.restdemo.entity.CarEntity;
import hu.vanio.restdemo.exceptions.CarNotFoundException;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author meszaros.andras
 */
public interface CarRegisterRepository {
    
    public Boolean add(CarEntity carEntity);
    
    public Boolean update(CarEntity carEntity) throws CarNotFoundException, DataAccessException, MessagingException;
    
    public Boolean delete(Long id);
    
    public List<CarEntity> search(String searchToken);

    public CarEntity get(Long id) throws CarNotFoundException, DataAccessException, MessagingException;
}
