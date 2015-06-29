package hu.vanio.restdemo.model;

import hu.vanio.restdemo.model.data.ResponseData;
import org.springframework.http.HttpStatus;

/**
 *
 * @author meszaros.andras
 */
public class StandardResponse {
    
    private final String uri;
    
    private final Integer statusCode;
    
    private final HttpStatus status;
    
    private final ResponseData data;
    
    /**
     *
     * @param uri
     * @param status
     * @param data
     */
    public StandardResponse(String uri, HttpStatus status, ResponseData data){
        
        this.uri = uri;
        this.statusCode = status.value();
        this.status = status;
        this.data = data;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @return the statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @return the status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * @return the data
     */
    public ResponseData getData() {
        return data;
    }
}
