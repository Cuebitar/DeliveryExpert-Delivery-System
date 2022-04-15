package delivery.app;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import java.time.LocalDate;
import java.util.ArrayList;

@RunWith(JUnitParamsRunner.class)
public class AssignStaffTest {
	TransactionList sut;
	static ArrayList<Delivery> deliveryList;
	Delivery actualDelivery;

	@Before
	public void setUp() {
		deliveryList = new ArrayList<Delivery>();
		sut = new TransactionList();
	}
	
	public void setUpMockedKeyboardInput(String staffNameOrId) {
		KeyboardInput mockedKeyboardInput = mock(KeyboardInput.class);
		
		when(mockedKeyboardInput.askString("client's name or ID")).thenReturn("1");
		when(mockedKeyboardInput.askString("staff name or ID")).thenReturn(staffNameOrId);
		when(mockedKeyboardInput.askString("receiver name")).thenReturn("abc");
		when(mockedKeyboardInput.askString("receiver phone no.")).thenReturn("123");
		when(mockedKeyboardInput.askString("pick up location")).thenReturn("asd");
		when(mockedKeyboardInput.askString("drop off location")).thenReturn("aer");
		when(mockedKeyboardInput.askDate("pick up date")).thenReturn(LocalDate.now());
		when(mockedKeyboardInput.askBoolean("Same day delivery")).thenReturn(false);
		when(mockedKeyboardInput.askBoolean("Deliver with insurance")).thenReturn(false);
		when(mockedKeyboardInput.askPositiveDouble("distance in km")).thenReturn(1.0);
		when(mockedKeyboardInput.askPositiveInt("number of items")).thenReturn(0);
	}
	
	// ******************* *********** Unit Testing *************************************
	
	// 1
	@Test
	@Parameters (method = "getParametersForTestAssignStaffValid")
	public void testAssignStaffUsingStaffIdValid(String staffId, String staffName, String staffTelNo) {
		Staff expectedStaff = new Staff(staffId, staffName, staffTelNo);
		setUpMockedKeyboardInput(staffId);
		actualDelivery = sut.addDelivery(deliveryList);
		assertTrue(actualDelivery.getStaff().equals(expectedStaff));
	}
	
	// 2
	@Test
	@Parameters (method = "getParametersForTestAssignStaffValid")
	public void testAssignStaffUsingStaffNameValid(String staffId, String staffName, String staffTelNo) {
		Staff expectedStaff = new Staff(staffId, staffName, staffTelNo);		
		setUpMockedKeyboardInput(staffName);
		actualDelivery = sut.addDelivery(deliveryList);
		assertTrue(actualDelivery.getStaff().equals(expectedStaff));
	}
	
	// 3
	@Test
	@Parameters
	public void testAssignStaffInvalid(String invalidInput, Class expectedExceptionClass) {
		setUpMockedKeyboardInput(invalidInput);
		actualDelivery = sut.addDelivery(deliveryList);
		assertTrue(actualDelivery.getStaff() == null);
	}
	
	// 4
	@Test (expected = NullPointerException.class)
	@Parameters
	public void testAssignStaffInvalid2(String invalidInput) {
		setUpMockedKeyboardInput(null);
	}
	
	// ********************************* Integration Testing *****************************
	
	// 5
	@Test
	@Parameters (method = "getParametersForAssignStaffIntegrationTest")
	public void assignStaffIntegrationTestValid(String staffId, String staffName, String staffTelNo) {
		Staff expectedStaff = new Staff(staffId, staffName, staffTelNo);
		
		// Gives the specified staffId / staffName to console
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
	
	public Object[] getParametersForAssignStaffIntegrationTest() {
		return new Object[] {
			new Object[] {"5", "Teoh Yin Shui", "+6015-428 7399"}, // Assign staff using name
			new Object[] {"6", "Haanii a/l Kishor Ramasamy", "+6018-053 4527"} // Assign staff using id
		};
	}
}
