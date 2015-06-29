package hu.vanio.restdemo.service;

import hu.vanio.restdemo.entity.CarEntity;
import hu.vanio.restdemo.exceptions.CarNotFoundException;
import hu.vanio.restdemo.repository.CarRegisterRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.mail.MessagingException;

/**
 *
 * @author Meszaros Andras <andras.meszaros@vanio.hu>
 */
public class MockCarRegisterRepository implements CarRegisterRepository{
    
    private final Map<Long, CarEntity> carEntityMap = new HashMap<>();
    
    @Override
    public Boolean add(CarEntity carEntity) {
        this.carEntityMap.put(carEntity.getId(), carEntity);
        return true;
    }

    @Override
    public Boolean update(CarEntity carEntity) throws CarNotFoundException, MessagingException {
        //Get element by id
        hu.vanio.restdemo.entity.CarEntity carEntityToUpdate = this.get(carEntity.getId());
        if(carEntityToUpdate == null){
            throw new CarNotFoundException();
        }
        //Update result element with updated elements
        carEntityToUpdate.setId(carEntity.getId());
        carEntityToUpdate.setCarType(carEntity.getCarType());
        carEntityToUpdate.setCylinderCapacity(carEntity.getCylinderCapacity());
        carEntityToUpdate.setHasAutomaticTransmission(carEntity.getHasAutomaticTransmission());
        carEntityToUpdate.setPrice(carEntity.getPrice());
        carEntityToUpdate.setProdDate(carEntity.getProdDate());
        //Put updates
        this.carEntityMap.put(carEntityToUpdate.getId(), carEntityToUpdate);
        
        return true;
    }

    @Override
    public Boolean delete(Long id) {
        CarEntity result = this.carEntityMap.get(id);
        if(result == null){
            throw new CarNotFoundException();
        }
        
        return (this.carEntityMap.remove(id) != null);
    }

    @Override
    public List<CarEntity> search(String searchToken) {
        List list = new ArrayList<>();
        for (Entry entry : carEntityMap.entrySet()) {
            CarEntity carEntity = (CarEntity) entry.getValue();
            if(carEntity.getCarType().contains(searchToken)){
                list.add(carEntity);
            }
        }
        
        return list;
    }

    @Override
    public CarEntity get(Long id) throws CarNotFoundException, MessagingException {
        CarEntity result = this.carEntityMap.get(id);
        if(result == null){
            throw new CarNotFoundException();
        }
        
        return result;
    }

    /**
     * @return the carEntityMap
     */
    public Map<Long, CarEntity> getCarEntityMap() {
        return carEntityMap;
    }
    
}
