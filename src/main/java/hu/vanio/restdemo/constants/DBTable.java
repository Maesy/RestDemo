package hu.vanio.restdemo.constants;

/**
 *
 * @author meszaros.andras
 */
public enum DBTable {

    /** */
    CAR_REGISTER("car_register");
    
    /** */
    private final String value;
    
    DBTable(String value){
        this.value = value;
    }
    
    @Override
    public String toString(){
        return this.value;
    }
}
