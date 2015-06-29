package hu.vanio.restdemo.controller;

import hu.vanio.restdemo.entity.CarEntity;
import hu.vanio.restdemo.exceptions.CarNotFoundException;
import hu.vanio.restdemo.model.data.CollectionResponseData;
import hu.vanio.restdemo.model.data.CommonResponseData;
import hu.vanio.restdemo.model.data.SimpleResponseData;
import hu.vanio.restdemo.model.StandardResponse;
import hu.vanio.restdemo.service.CarRegisterService;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author meszaros.andras
 */
@RestController
public class CarRegisterController {

    /**
     * Logger
     */
    private static final Logger Log = LoggerFactory.getLogger(CarRegisterController.class);
    
    /**
     * Car register service
     */
    private final CarRegisterService carRegisterService;

    @Autowired
    public CarRegisterController(CarRegisterService carRegisterService) {
        this.carRegisterService = carRegisterService;
    }

    /**
     * POST
     *
     * @param carEntity
     * @param request
     * @return 
     * @throws javax.mail.MessagingException 
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/cars/")
    public ResponseEntity<StandardResponse> addCar(@RequestBody CarEntity carEntity, HttpServletRequest request) throws MessagingException {
        return new ResponseEntity<>(new StandardResponse(request.getRequestURI(), HttpStatus.CREATED, new SimpleResponseData(this.carRegisterService.addCar(carEntity))), HttpStatus.CREATED);
    }

    /**
     * GET
     *
     * @param id
     * @param request
     * @return 
     * @throws javax.mail.MessagingException
     * @throws hu.vanio.restdemo.exceptions.CarNotFoundException
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/cars/id/{id}")
    public ResponseEntity<StandardResponse> getCar(@PathVariable Long id, HttpServletRequest request) throws MessagingException, CarNotFoundException {
        return new ResponseEntity<>(new StandardResponse(request.getRequestURI(), HttpStatus.OK, new CommonResponseData(this.carRegisterService.getCarById(id))), HttpStatus.OK);
    }

    /**
     * PUT
     *
     * @param carEntity
     * @param request
     * @return 
     * @throws hu.vanio.restdemo.exceptions.CarNotFoundException 
     * @throws javax.mail.MessagingException
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/api/cars/")
    public ResponseEntity<StandardResponse> updateCar(@RequestBody CarEntity carEntity, HttpServletRequest request) throws CarNotFoundException, MessagingException {
       return new ResponseEntity<>(new StandardResponse(request.getRequestURI(), HttpStatus.OK, new SimpleResponseData(this.carRegisterService.updateCar(carEntity))), HttpStatus.OK);
    }

    /**
     * DELETE
     * 
     * @param id
     * @param request
     * @return 
     * @throws javax.mail.MessagingException
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/api/cars/id/{id}")
    public ResponseEntity<StandardResponse> deleteCar(@PathVariable Long id, HttpServletRequest request) throws MessagingException {
        return new ResponseEntity<>(new StandardResponse(request.getRequestURI(), HttpStatus.NO_CONTENT, new SimpleResponseData(this.carRegisterService.deleteCar(id))), HttpStatus.NO_CONTENT);
    }

    /**
     * SEARCH
     *
     * @param searchToken
     * @param request
     * @return 
     * @throws javax.mail.MessagingException
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/cars/search", params="searchToken")
    public ResponseEntity<StandardResponse> searchCar(@RequestParam("searchToken") String searchToken, HttpServletRequest request) throws MessagingException {
        return new ResponseEntity<>(new StandardResponse(request.getRequestURI(), HttpStatus.OK, new CollectionResponseData(this.carRegisterService.searchCar(searchToken))), HttpStatus.OK);
    }
}
