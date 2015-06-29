package hu.vanio.restdemo.constants;

/**
 *
 * @author meszaros.andras
 */
public enum DBTableColumn {

    /** */
    ID("id"),

    /** */
    PROD_DATE("prod_date"),

    /** */
    CAR_TYPE("car_type"),

    /** */
    HAS_AUTOMATIC_TRANSMISSION("has_automatic_transmission"),

    /** */
    CYLINDER_CAPACITY("cylinder_capacity"),

    /** */
    PRICE("price");
    
    /** */
    private final String value;
    
    DBTableColumn(String value){
        this.value = value;
    }
    
    @Override
    public String toString(){
        return this.value;
    }
}
