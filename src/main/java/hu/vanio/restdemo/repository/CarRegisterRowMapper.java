package hu.vanio.restdemo.repository;

import hu.vanio.restdemo.constants.DBRecordHasAutomaticTransmissionValue;
import hu.vanio.restdemo.constants.DBTableColumn;
import hu.vanio.restdemo.entity.CarEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author meszaros.andras
 */
public class CarRegisterRowMapper implements RowMapper<CarEntity> {
    
    @Override
    public CarEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong(DBTableColumn.ID.toString());
        Date prodDate = rs.getDate(DBTableColumn.PROD_DATE.toString());
        String carType = rs.getString(DBTableColumn.CAR_TYPE.toString());
        Boolean hasAutomaticTransmission = rs.getString(DBTableColumn.HAS_AUTOMATIC_TRANSMISSION.toString()).equals(DBRecordHasAutomaticTransmissionValue.Y.toString());
        Double cylinderCapacity = rs.getDouble(DBTableColumn.CYLINDER_CAPACITY.toString());
        Integer price = rs.getInt(DBTableColumn.PRICE.toString());
        
        return new CarEntity(id, prodDate, carType, hasAutomaticTransmission, cylinderCapacity, price);
    }
}


