package delivery.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class CalculateAdditionalChargesTest {

	@Test // TCN005
	@Parameters({ "true,true,25", "true,false,10", "false,true,15", "false,false,0" })
	public void testCalculateAdditionalChargeValidValues(String sameDayDelivery, String withInsurance,
			double expectedAdditionalCharge) {
		Delivery del = new Delivery("", "", "", "", "", "2", sameDayDelivery, withInsurance, "", "", "22/02/2022");
		del.calculateAdditionalCharge();
		assertEquals(expectedAdditionalCharge, del.getAdditionalCharge(), 0);
	}

}
