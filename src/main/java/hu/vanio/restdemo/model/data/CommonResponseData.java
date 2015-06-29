package hu.vanio.restdemo.model.data;

import hu.vanio.restdemo.entity.CarEntity;

/**
 *
 * @author meszaros.andras
 */
public class CommonResponseData implements ResponseData{
    
    private final CarEntity data;
    
    public CommonResponseData(CarEntity data){
        this.data = data;
    }

    /**
     * @return the carEntityList
     */
    public CarEntity getData() {
        return data;
    }
}
