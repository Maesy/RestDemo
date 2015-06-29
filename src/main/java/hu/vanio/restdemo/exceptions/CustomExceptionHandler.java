package hu.vanio.restdemo.exceptions;

import hu.vanio.restdemo.model.ErrorResponse;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author meszaros.andras
 */
@ControllerAdvice
public class CustomExceptionHandler {
    
    /**
     * 
     * @param ex
     * @param request
     * @param response
     * @return 
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MessagingException.class)
    @ResponseBody
    public ErrorResponse messagingErrorHandler(MessagingException ex, HttpServletRequest request, HttpServletResponse response){
        return new ErrorResponse(request.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR, "General error");
    }
    
    /**
     *
     * @param ex
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(RestDemoException.class)
    @ResponseBody
    public ErrorResponse restDemoErrorHandler(
            RestDemoException ex,
            HttpServletRequest request,
            HttpServletResponse response) {
        ResponseStatus responseStatus
                    = ex.getClass().getAnnotation(ResponseStatus.class);
            response.setStatus(responseStatus.value().value());
            HttpStatus hs = HttpStatus.valueOf(responseStatus.value().value());
            String errorReason = responseStatus.reason();
            
        return new ErrorResponse(
                request.getRequestURI(),
                hs, errorReason);
    }
}
