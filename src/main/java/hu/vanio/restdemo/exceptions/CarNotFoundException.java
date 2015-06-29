package hu.vanio.restdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author meszaros.andras
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Car not found in the database!")
public class CarNotFoundException extends RestDemoException {
    private static final long serialVersionUID = 100L;
}
