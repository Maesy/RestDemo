package hu.vanio.restdemo.service;

import hu.vanio.restdemo.entity.CarEntity;
import hu.vanio.restdemo.exceptions.CarNotFoundException;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Meszaros Andras <andras.meszaros@vanio.hu>
 */
public class CarRegisterServiceTest {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(CarRegisterServiceTest.class);

    public CarRegisterServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCarById method, of class CarRegisterService.
     */
    @Test
    public void testGetCarById() throws Exception {
        System.out.println("getCarById");

        MockCarRegisterRepository mockCarRegisterRepository = new MockCarRegisterRepository();
        MockMailSender mockMailSender = new MockMailSender();
        Long id = 1L;
        CarEntity carEntity = new CarEntity(id, new Date(), "Ford", false, 2.5D, 200000);
        mockCarRegisterRepository.getCarEntityMap().put(id, carEntity);
        CarRegisterService instance = new CarRegisterService(mockCarRegisterRepository, mockMailSender);

        hu.vanio.restdemo.entity.CarEntity result = instance.getCarById(id);
        assertEquals(carEntity, result);
    }

    /**
     * Test of getCarById method, of class CarRegisterService.
     */
    @Test(expected = CarNotFoundException.class)
    public void testGetCarByNotExistingId() throws Exception {
        System.out.println("getCarById");

        MockCarRegisterRepository mockCarRegisterRepository = new MockCarRegisterRepository();
        MockMailSender mockMailSender = new MockMailSender();
        CarRegisterService instance = new CarRegisterService(mockCarRegisterRepository, mockMailSender);

        instance.getCarById(111L);

        fail("Nem dobott exceptiont, igy hibas a teszt!");
    }

    /**
     * Test of updateCar method, of class CarRegisterService.
     */
    @Test
    public void testUpdateExistingCar() throws Exception {
        System.out.println("updateCar");
        //Place element to update
        MockCarRegisterRepository mockCarRegisterRepository = new MockCarRegisterRepository();
        MockMailSender mockMailSender = new MockMailSender();
        Long id = 1L;
        CarEntity carEntity = new CarEntity(id, new Date(), "Ford", false, 2.5D, 200000);
        mockCarRegisterRepository.getCarEntityMap().put(id, carEntity);
        //Create updated element
        CarEntity editedCarEntity = new CarEntity(id, new Date(), "Mercedes", true, 2.5D, 400000);

        CarRegisterService instance = new CarRegisterService(mockCarRegisterRepository, mockMailSender);

        Boolean expResult = true;
        Boolean result = instance.updateCar(editedCarEntity);

        //Checking if the result object is the same as the updated object
        assertEquals(expResult, result);
    }

    /**
     * Test of getCarById method, of class CarRegisterService.
     */
    @Test(expected = CarNotFoundException.class)
    public void testUpdateNotExistingCar() throws Exception {
        //Place element to update
        MockCarRegisterRepository mockCarRegisterRepository = new MockCarRegisterRepository();
        MockMailSender mockMailSender = new MockMailSender();
        //Create updated element
        CarEntity editedCarEntity = new CarEntity(1L, new Date(), "Mercedes", true, 2.5D, 400000);

        CarRegisterService instance = new CarRegisterService(mockCarRegisterRepository, mockMailSender);
        instance.updateCar(editedCarEntity);

        fail("Nem dobott exceptiont, igy hibas a teszt!");
    }

    /**
     * Test of searchCar method, of class CarRegisterService.
     */
    @Test
    public void testSearchOneMatchingCar() throws Exception {
        System.out.println("searchCar");

        MockCarRegisterRepository mockCarRegisterRepository = new MockCarRegisterRepository();
        MockMailSender mockMailSender = new MockMailSender();
        CarRegisterService instance = new CarRegisterService(mockCarRegisterRepository, mockMailSender);
        CarEntity carEntity = new CarEntity(1L, new Date(), "Ford Sierra", false, 2.5D, 200000);
        String searchToken = "ierr";

        mockCarRegisterRepository.getCarEntityMap().put(1L, carEntity);

        List<hu.vanio.restdemo.entity.CarEntity> result = instance.searchCar(searchToken);

        assertEquals(1, result.size());
    }

    @Test
    public void testSearchMultipleMatchingCar() throws Exception {
        MockCarRegisterRepository mockCarRegisterRepository = new MockCarRegisterRepository();
        MockMailSender mockMailSender = new MockMailSender();
        CarRegisterService instance = new CarRegisterService(mockCarRegisterRepository, mockMailSender);
        CarEntity carEntity1 = new CarEntity(1L, new Date(), "Ford Sierra", false, 2.5D, 200000);
        CarEntity carEntity2 = new CarEntity(2L, new Date(), "Ford Sierra", false, 3.5D, 999999);
        String searchToken = "ierr";

        mockCarRegisterRepository.getCarEntityMap().put(1L, carEntity1);
        mockCarRegisterRepository.getCarEntityMap().put(2L, carEntity2);

        List<hu.vanio.restdemo.entity.CarEntity> result = instance.searchCar(searchToken);

        assertEquals(2, result.size());
    }

    @Test
    public void testSearchZeroMatchingCar() throws Exception {
        MockCarRegisterRepository mockCarRegisterRepository = new MockCarRegisterRepository();
        MockMailSender mockMailSender = new MockMailSender();
        CarRegisterService instance = new CarRegisterService(mockCarRegisterRepository, mockMailSender);
        String searchToken = "ierr";

        List<hu.vanio.restdemo.entity.CarEntity> result = instance.searchCar(searchToken);

        assertEquals(0, result.size());
    }

    /**
     * Test of deleteCar method, of class CarRegisterService.
     */
    @Test
    public void testDeleteExistingCar() throws Exception {
        System.out.println("deleteCar");

        //Place element to update
        MockCarRegisterRepository mockCarRegisterRepository = new MockCarRegisterRepository();
        MockMailSender mockMailSender = new MockMailSender();
        Long id = 1L;
        CarEntity carEntity = new CarEntity(id, new Date(), "Ford", false, 2.5D, 200000);
        mockCarRegisterRepository.getCarEntityMap().put(id, carEntity);
        CarRegisterService instance = new CarRegisterService(mockCarRegisterRepository, mockMailSender);

        Boolean expResult = true;
        Boolean result = instance.deleteCar(id);
        assertEquals(expResult, result);
    }

    @Test(expected = CarNotFoundException.class)
    public void testDeleteNotExistingCar() throws Exception {
        //Place element to update
        MockCarRegisterRepository mockCarRegisterRepository = new MockCarRegisterRepository();
        MockMailSender mockMailSender = new MockMailSender();
        CarRegisterService instance = new CarRegisterService(mockCarRegisterRepository, mockMailSender);

        instance.deleteCar(Long.MIN_VALUE);

        fail("Nem dobott exceptiont, igy hibas a teszt!");
    }

    /**
     * Test of addCar method, of class CarRegisterService.
     */
    @Test
    public void testAddCar() throws Exception {
        System.out.println("addCar");

        Long id = 1L;
        MockCarRegisterRepository mockCarRegisterRepository = new MockCarRegisterRepository();
        MockMailSender mockMailSender = new MockMailSender();
        CarRegisterService instance = new CarRegisterService(mockCarRegisterRepository, mockMailSender);
        hu.vanio.restdemo.entity.CarEntity carEntity = new CarEntity(id, new Date(), "Ford", false, 2.5D, 200000);

        Boolean expResult = true;
        Boolean result = instance.addCar(carEntity);
        //First check
        assertEquals(expResult, result);

        CarEntity resultCarEntity = mockCarRegisterRepository.getCarEntityMap().put(id, carEntity);
        //Second check
        assertEquals(resultCarEntity, carEntity);
    }

}
