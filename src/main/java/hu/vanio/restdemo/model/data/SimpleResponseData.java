package hu.vanio.restdemo.model.data;

/**
 *
 * @author meszaros.andras
 */
public class SimpleResponseData implements ResponseData{
    
    private final Boolean data;
    
    public SimpleResponseData(Boolean data){
        this.data = data;
    }

    /**
     * @return the success
     */
    public Boolean getData() {
        return data;
    }
}
