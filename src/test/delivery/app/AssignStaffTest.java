package delivery.app;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import java.util.ArrayList;


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
		MockedStatic mocked = mockStatic(Foo.class)) {
			 mocked.when(Foo::method).thenReturn("bar")
		
		expectedStaff = new Staff(staffId, staffName, staffTelNo);
		
		mockKeyboardInput = mock(KeyboardInput.class);
		when(mockKeyboardInput.getSalesAgentPosition(anyString())).thenReturn(currentPosition);
		when(mockKeyboardInput.getSalesAmount(anyString())).thenReturn(salesAmount);
		
		
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
	@Test
	@Parameters
	public void testAssignStaffInvalid(String invalidInput, Class expectedExceptionClass) {
		actualDelivery = sut.addDelivery(deliveryList);
		// for invalid, staff == null
		assertTrue(actualDelivery.getStaff() == null);
	}
	
	// 4
	// Test for multiple thrown exceptions in one test
	@Test
	@Parameters
	public void testAssignStaffInvalid2(String invalidInput, Class expectedExceptionClass) {
		actualDelivery = sut.addDelivery(deliveryList);
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
	
	// 5
	@Test
	@Parameters (method = "getParametersForAssignStaffIntegrationTest")
	public void assignStaffIntegrationTestValid(String staffId, String staffName, String staffTelNo) {
		expectedStaff = new Staff(staffId, staffName, staffTelNo);
		
		actualDelivery = sut.addDelivery(deliveryList);
		
		assertTrue(actualDelivery.getStaff().equals(expectedStaff));
	}
	
	// 6
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
		return new Object[] {"0", "-1", "6", "", "abc"};
	}
	
	public Object[] parametersForTestAssignStaffInvalid2() {
		return new Object[] {
			new Object[] {null, NullPointerException.class}, 
			new Object[] {1, Exception.class},
			new Object[] {'1', Exception.class}
		};
	}
	
	public Object[] getParametersForAssignStaffIntegrationTest() {
		return new Object[] {
			new Object[] {"5", "Teoh Yin Shui", "+6015-428 7399"}, // Assign staff using name
			new Object[] {"6", "Haanii a/l Kishor Ramasamy", "+6018-053 4527"} // Assign staff using id
		};
	}
}
