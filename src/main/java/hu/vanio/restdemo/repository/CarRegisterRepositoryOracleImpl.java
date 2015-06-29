package hu.vanio.restdemo.repository;

import hu.vanio.restdemo.constants.DBRecordHasAutomaticTransmissionValue;
import hu.vanio.restdemo.constants.DBTable;
import hu.vanio.restdemo.constants.DBTableColumn;
import hu.vanio.restdemo.entity.CarEntity;
import hu.vanio.restdemo.exceptions.CarNotFoundException;
import java.util.List;
import javax.mail.MessagingException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author meszaros.andras
 */
@Repository
@Profile("oracle")
public class CarRegisterRepositoryOracleImpl implements CarRegisterRepository{
    
    private static final Logger Log = LoggerFactory.getLogger(CarRegisterRepository.class);
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CarRegisterRepositoryOracleImpl(DataSource dataSource) {
        Log.info(" ===>CarRegisterRepositoryOracleImpl");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public Boolean add(CarEntity carEntity) throws DataAccessException{
        int rowCount;
        String sqlString;
        
        sqlString = "INSERT INTO " + DBTable.CAR_REGISTER + "(" + DBTableColumn.PROD_DATE + ", " + DBTableColumn.CAR_TYPE + ", " + DBTableColumn.HAS_AUTOMATIC_TRANSMISSION + ", " + DBTableColumn.CYLINDER_CAPACITY + ", " + DBTableColumn.PRICE + ") values(?,?,?,?,?)";
        
        rowCount = this.jdbcTemplate.update(
            sqlString,
            carEntity.getProdDate(),
            carEntity.getCarType(),
            carEntity.getHasAutomaticTransmission() ? DBRecordHasAutomaticTransmissionValue.Y : DBRecordHasAutomaticTransmissionValue.N,
            carEntity.getCylinderCapacity(),
            carEntity.getPrice()
        );
            
        Log.info("Number of inserted rows: " + rowCount);
        
        return rowCount == 1;
    }
    
    /**
     *
     * @param id
     * @return
     * @throws hu.vanio.restdemo.exceptions.CarNotFoundException
     * @throws javax.mail.MessagingException
     */
    @Override
    public CarEntity get(Long id) throws CarNotFoundException, DataAccessException, MessagingException {
        String sqlString = "SELECT * FROM " + DBTable.CAR_REGISTER + " WHERE " + DBTableColumn.ID + "=?";
        Object [] params = { id };
        
        RowMapper rowMapper = new CarRegisterRowMapper();
        CarEntity carEntity = (CarEntity) this.jdbcTemplate.queryForObject(sqlString, params, rowMapper);
        
        return carEntity;
    }

    /**
     *
     * @param carEntity
     * @return
     * @throws CarNotFoundException
     * @throws javax.mail.MessagingException
     */
    @Override
    public Boolean update(CarEntity carEntity) throws CarNotFoundException, DataAccessException, MessagingException {
        int rowCount = 0;
        if(carEntity != null){
            CarEntity resultCarEntity = this.get(carEntity.getId());
            if(resultCarEntity != null){
                String sqlString = "UPDATE " + DBTable.CAR_REGISTER + " SET " + DBTableColumn.PROD_DATE + "=?, " + DBTableColumn.CAR_TYPE + "=?, " + DBTableColumn.HAS_AUTOMATIC_TRANSMISSION + "=?, " + DBTableColumn.CYLINDER_CAPACITY + "=?, " + DBTableColumn.PRICE + "=? WHERE " + DBTableColumn.ID + "=?";
                
                rowCount = this.jdbcTemplate.update(
                        sqlString,
                        carEntity.getProdDate(),
                        carEntity.getCarType(),
                        (carEntity.getHasAutomaticTransmission() ? DBRecordHasAutomaticTransmissionValue.Y : DBRecordHasAutomaticTransmissionValue.N),
                        carEntity.getCylinderCapacity(),
                        carEntity.getPrice(),
                        carEntity.getId()
                );
            }
        }else{
            Log.error("Invalid input!");
        }
        
        return rowCount == 1;
    }

    @Override
    public Boolean delete(Long id) {
        int rowCount;
        String sqlString;
        sqlString = "DELETE FROM " + DBTable.CAR_REGISTER + " WHERE " + DBTableColumn.ID + "=?";
        
        rowCount = this.jdbcTemplate.update(
            sqlString,
            id
        );
        
        Log.info("Number of deleted rows: " + rowCount);
        
        return rowCount == 1;
    }

    @Override
    public List<CarEntity> search(String searchToken) {
        String sqlString = "SELECT * FROM " + DBTable.CAR_REGISTER + " WHERE " + DBTableColumn.CAR_TYPE + " LIKE (?) ORDER BY " + DBTableColumn.ID;
        Object [] params = { ("%" + searchToken + "%") };
        
        RowMapper rowMapper = new CarRegisterRowMapper();
        List<CarEntity> list = this.jdbcTemplate.query(sqlString, params, rowMapper);
        
        Log.info("Number of elements matching the search: " + list.size());
        
        return list;
    }
}
