package hu.vanio.restdemo.model;

import org.springframework.http.HttpStatus;

/**
 *
 * @author meszaros.andras
 */
public class ErrorResponse {
    
    private final Integer status;
    
    private final HttpStatus error;
    
    private final String message;
    
    private final String path;
    
    /**
     * 
     * @param path
     * @param status
     * @param message 
     */
    public ErrorResponse(String path, HttpStatus status, String message){
        this.status = status.value();
        this.error = status;
        this.message = message;
        this.path = path;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @return the error
     */
    public HttpStatus getError() {
        return error;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

}
