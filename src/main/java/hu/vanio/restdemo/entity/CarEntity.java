package hu.vanio.restdemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import hu.vanio.restdemo.model.data.ResponseData;
import java.util.Date;

/**
 *
 * @author meszaros.andras
 */
public class CarEntity implements ResponseData {
    
    /**
     * Id
     */
    private Long id;
    
    /**
     * Date of creation
     */
    @JsonProperty("prodDate")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy.MM.dd", timezone="CET")
    private Date prodDate;
    
    /**
     * The type of the car
     */
    private String carType;
    
    /**
     * Contains if the car has an automatic transmission or not
     */
    private Boolean hasAutomaticTransmission;
    
    /**
     * Contains the cylinder capacity of the car as a floating point number
     */
    private Double cylinderCapacity;
    
    /**
     * Contains the actual sell price of the car
     */
    private Integer price;
     
    /**
     * 
     * @param id
     * @param prodDate
     * @param carType
     * @param hasAutomaticTransmission
     * @param cylinderCapacity
     * @param price 
     */
    public CarEntity(Long id, Date prodDate, String carType, Boolean hasAutomaticTransmission, Double cylinderCapacity, Integer price) {
        this();
        this.id = id;
        this.carType = carType;
        this.prodDate = prodDate;
        this.hasAutomaticTransmission = hasAutomaticTransmission;
        this.cylinderCapacity = cylinderCapacity;
        this.price = price;
    }
    
    public CarEntity(){
        
    }

    /**
     * Id
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Date of creation
     * @return the date
     */
    public Date getProdDate() {
        return prodDate;
    }

    /**
     * The type of the car
     * @return the carType
     */
    public String getCarType() {
        return carType;
    }

    /**
     * Contains if the car has an automatic transmission or not
     * @return the hasAutomaticTransmission
     */
    public Boolean getHasAutomaticTransmission() {
        return hasAutomaticTransmission;
    }

    /**
     * Contains the cylinder capacity of the car as a floating point number
     * @return the cylinderCapacity
     */
    public Double getCylinderCapacity() {
        return cylinderCapacity;
    }

    /**
     * Contains the actual sell price of the car
     * @return the price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Id
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Date of creation
     * @param prodDate the prodDate to set
     */
    @JsonProperty("prodDate")
    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    /**
     * The type of the car
     * @param carType the carType to set
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * Contains if the car has an automatic transmission or not
     * @param hasAutomaticTransmission the hasAutomaticTransmission to set
     */
    public void setHasAutomaticTransmission(Boolean hasAutomaticTransmission) {
        this.hasAutomaticTransmission = hasAutomaticTransmission;
    }

    /**
     * Contains the cylinder capacity of the car as a floating point number
     * @param cylinderCapacity the cylinderCapacity to set
     */
    public void setCylinderCapacity(Double cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
    }

    /**
     * Contains the actual sell price of the car
     * @param price the price to set
     */
    public void setPrice(Integer price) {
        this.price = price;
    }
    
    @Override
    public String toString(){
        return "{ " +
                "id: " + getId() +
                ", prodDate: " + getProdDate() +
                ", carType: " + getCarType() +
                ", hasAutomaticTransmission: " + getHasAutomaticTransmission() +
                ", cylinderCapacity: " + getCylinderCapacity() +
                ", price: " + getPrice() +
                " }";
    }
}
