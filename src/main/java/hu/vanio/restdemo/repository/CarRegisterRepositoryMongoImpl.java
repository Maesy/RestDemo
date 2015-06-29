package hu.vanio.restdemo.repository;

import com.mongodb.WriteResult;
import hu.vanio.restdemo.controller.CarRegisterController;
import hu.vanio.restdemo.entity.CarEntity;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 *
 * @author meszaros.andras
 */
@Repository
@Profile("mongo")
public class CarRegisterRepositoryMongoImpl implements CarRegisterRepository{
    
    /**
     * Logger
     */
    private static final Logger Log = LoggerFactory.getLogger(CarRegisterController.class);
    
    /** */
    private final MongoTemplate mongoTemplate;

    public CarRegisterRepositoryMongoImpl(MongoTemplate mongoTemplate) {
        Log.info(" ===>CarRegisterRepositoryMongoImpl");
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Boolean add(CarEntity carEntity) {
        Log.info("===>ADD: " + carEntity);
        this.mongoTemplate.insert(carEntity);
        return true;
    }

    @Override
    public Boolean update(CarEntity carEntity) {
        CarEntity updatedCarEntity = null;
        if(carEntity != null){
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(carEntity.getId()));
            Update update = new Update();
            
            update.set("prodDate", carEntity.getProdDate());
            update.set("carType", carEntity.getCarType());
            update.set("hasAutomaticTransmission", carEntity.getHasAutomaticTransmission());
            update.set("cylinderCapacity", carEntity.getCylinderCapacity());
            update.set("price", carEntity.getPrice());
            
            updatedCarEntity = this.mongoTemplate.findAndModify(query, update, CarEntity.class);
        }else{
            Log.error("Invalid input!");
        }
        
        Log.info("Updated entity: " + updatedCarEntity);
        return updatedCarEntity != null;
    }

    @Override
    public Boolean delete(Long id) {
        Log.info("===>DELETE: " + id);
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        WriteResult result = this.mongoTemplate.remove(query, CarEntity.class);
        Log.info("===>DELETE RESULT: " + result);
        return result.getN() == 1;
    }

    @Override
    public List<CarEntity> search(String searchToken) {
        Log.info("===>SEARCH: " + searchToken);
        Query query = new Query();
        query.addCriteria(Criteria.where("carType").regex(searchToken, "i"));
        return this.mongoTemplate.find(query, CarEntity.class);
    }

    @Override
    public CarEntity get(Long id) {
        Log.info("===>GET: " + id);
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return this.mongoTemplate.findOne(query, CarEntity.class);
    }

}
