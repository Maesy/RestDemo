package hu.vanio.restdemo.model.data;

import hu.vanio.restdemo.entity.CarEntity;
import java.util.List;

/**
 *
 * @author meszaros.andras
 */
public class CollectionResponseData implements ResponseData{
    
    private final List<CarEntity> data;
    
    public CollectionResponseData(List
            <CarEntity> data){
        this.data = data;
    }

    /**
     * @return the carEntityList
     */
    public List<CarEntity> getData() {
        return data;
    }
}
