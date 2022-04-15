package delivery.app;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import delivery.app.KeyboardInput;
import delivery.app.TransactionList;
import domain.Staff;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import java.util.ArrayList;

import domain.Delivery;
import domain.Staff;


@RunWith(JUnitParamsRunner.class)
public class AssignStaffTest {
	TransactionList sut;
	static ArrayList<Delivery> deliveryList;
	Staff expectedStaff;
	Delivery actualDelivery;
	KeyboardInput mockKeyboardInput;
	
	@BeforeClass
	public static void setUpClass() {
		deliveryList = new ArrayList<Delivery>();
	}
	
	@Before
	public void setUp() {
		sut = new TransactionList();
	}
	
	// ******************* *********** Unit Testing *************************************
	
	// 1
	@Test
	@Parameters (method = "getParametersForTestAssignStaffValid")
	public void testAssignStaffUsingStaffIdValid(String staffId, String staffName, String staffTelNo) {
		expectedStaff = new Staff(staffId, staffName, staffTelNo);
		
		mockKeyboardInput = mock(KeyboardInput.class);
		
		
		actualDelivery = sut.addDelivery(deliveryList);
		
		assertTrue(actualDelivery.getStaff().equals(expectedStaff));
	}
	
	// 2
	@Test
	@Parameters (method = "getParametersForTestAssignStaffValid")
	public void testAssignStaffUsingStaffNameValid(String staffId, String staffName, String staffTelNo) {
		expectedStaff = new Staff(staffId, staffName, staffTelNo);
		
		mockKeyboardInput = mock(KeyboardInput.class);
		
		
		actualDelivery = sut.addDelivery(deliveryList);
		
		assertTrue(actualDelivery.getStaff().equals(expectedStaff));
	}
	
	// 3
	// Test for multiple thrown exceptions in one test
	@Test
	@Parameters
	public void testAssignStaffInvalid(String invalidInput, Class expectedExceptionClass) {
		// for invalid, staff == null
		assertTrue(actualDelivery.getStaff() == null);
		
		try {
	        // exception throwing code
	    } catch (Exception e) {
            if (e.getClass() != expectedExceptionClass)
            	Assert.fail();
	    }
	}
	
	// ********************************* Integration Testing *****************************
	
	// 4
	@Test
	@Parameters
	public void assignStaffIntegrationTestValid(String staffId, String staffName, String staffTelNo) {
		expectedStaff = new Staff(staffId, staffName, staffTelNo);
		
		actualDelivery = sut.addDelivery(deliveryList);
		
		assertTrue(actualDelivery.getStaff().equals(expectedStaff));
	}
	
	// 5
	@Test
	@Parameters
	public void assignStaffIntegrationTestInvalid() {
		// Gives invalid staff id or staff name to console
		actualDelivery = sut.addDelivery(deliveryList);
		
		assertTrue(actualDelivery.getStaff() == null);
	}
	
	// ******************************** Test Data ***************************************
	
	// To check if the system assigns the staff according to the user input 
	public Object[] getParametersForTestAssignStaffValid() {
		return new Object[] {
				new Object[] {"5", "Teoh Yin Shui", "+6015-428 7399"},
				new Object[] {"6", "Haanii a/l Kishor Ramasamy", "+6018-053 4527"}
		};
	}
	
	public Object[] parametersForTestAssignStaffInvalid() {
		// For invalid id
		new Object[] {"0", "Haanii a/l Kishor Ramasamy", "+6018-053 4527"},
		new Object[] {"-1", "Haanii a/l Kishor Ramasamy", "+6018-053 4527"},
		new Object[] {"6", "Haanii a/l Kishor Ramasamy", "+6018-053 4527"},
		
		// For invalid name
		new Object[] {"6", "", "+6018-053 4527"},
		new Object[] {"6", "abc", "+6018-053 4527"}
	}
		return new Object[] {
				new Object[] {null, NullPointerException.class}, 
				new Object[] {1,   
				'1'};
	}
	
	public Object[] parametersForTestAssignStaffIntegration() {
		return new Object[] {
				new Object[] {"5", "Teoh Yin Shui", "+6015-428 7399"}, // Assign staff using name
				new Object[] {"6", "Haanii a/l Kishor Ramasamy", "+6018-053 4527"} // Assign staff using id
		};
	}
}
