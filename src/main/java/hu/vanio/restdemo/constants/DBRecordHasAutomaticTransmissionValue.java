package hu.vanio.restdemo.constants;

/**
 *
 * @author meszaros.andras
 */
public enum DBRecordHasAutomaticTransmissionValue {
    /** */
    Y("Y"),

    /** */
    N("N");
    
    /** */
    private final String value;
    
    DBRecordHasAutomaticTransmissionValue(String value){
        this.value = value;
    }
    
    @Override
    public String toString(){
        return this.value;
    }
}
