package hu.vanio.restdemo.service;

import hu.vanio.restdemo.entity.CarEntity;
import hu.vanio.restdemo.exceptions.CarNotFoundException;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Meszaros Andras <andras.meszaros@vanio.hu>
 */
@RunWith(MockitoJUnitRunner.class)
public class CarRegisterServiceTest {
    
    @Mock
    MockCarRegisterRepository mockCarRegisterRepository;
    
    @Mock
    MockMailSender mockMailSender;
    
    String mockMailRecipientAddress = "a@b.hu";
    
    String mockMailSenderAddress = "a@b.hu";
    
    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(CarRegisterServiceTest.class);
    
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
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCarById() throws Exception {
        System.out.println("UT getCarById");
        
        //Mock car entity
        CarEntity mockCarEntity = Mockito.mock(CarEntity.class);
        
        //mock get method call result
        when(this.mockCarRegisterRepository.get(mockCarEntity.getId())).thenReturn(mockCarEntity);
        
        CarRegisterService instance = new CarRegisterService(this.mockCarRegisterRepository, this.mockMailSender, this.mockMailRecipientAddress, this.mockMailSenderAddress);
        
        hu.vanio.restdemo.entity.CarEntity result = instance.getCarById(mockCarEntity.getId());
        
        verify(mockCarRegisterRepository).get(mockCarEntity.getId());
        
        assertEquals(mockCarEntity, result);
    }
    
    /**
     * Test of getCarById method, of class CarRegisterService.
     * @throws java.lang.Exception
     */
    @Test(expected = CarNotFoundException.class)
    public void testGetCarByNotExistingId() throws Exception {
        System.out.println("getCarById");
        Long id = 1111L;
        //mock get method call result
        when(this.mockCarRegisterRepository.get(id)).thenThrow(CarNotFoundException.class);
        
        CarRegisterService instance = new CarRegisterService(this.mockCarRegisterRepository, this.mockMailSender, this.mockMailRecipientAddress, this.mockMailSenderAddress);
        
        instance.getCarById(id);
        verify(mockCarRegisterRepository).get(id);
        fail("Nem dobott exceptiont, igy hibas a teszt!");
    }
    
    /**
     * Test of updateCar method, of class CarRegisterService.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateExistingCar() throws Exception {
        System.out.println("updateCar");
        
        //Mock car entity
        CarEntity mockCarEntity = Mockito.mock(CarEntity.class);
        when(this.mockCarRegisterRepository.get(mockCarEntity.getId())).thenReturn(mockCarEntity);
        
        //Instantiate service class
        CarRegisterService instance = new CarRegisterService(this.mockCarRegisterRepository, this.mockMailSender, this.mockMailRecipientAddress, this.mockMailSenderAddress);
        //Get entity from repository
        mockCarEntity = instance.getCarById(mockCarEntity.getId());
        //Update a field on the entity
        mockCarEntity.setCarType("mockCarType");
        
        //Mock the result of the update call
        when(this.mockCarRegisterRepository.update(mockCarEntity)).thenReturn(Boolean.TRUE);
        
        //Define expected result
        Boolean expResult = Boolean.TRUE;
        Boolean result = instance.updateCar(mockCarEntity);
        
        //Verify that the call happened
        verify(mockCarRegisterRepository).update(mockCarEntity);
        
        //Check results if they are as expected
        assertEquals(expResult, result);
    }
    
}
