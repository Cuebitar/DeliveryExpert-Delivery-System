package domain;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import domain.Document;
import domain.Parcel;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class CalculateChargesTest {

	@Test // TCN001
	@Parameters({ "1,9,3", "299,9,3", "1,10,3", "299,10,3", "1,30,3", "299,30,3", "1,31,3", "299,31,3", "300,9,4",
			"1000,9,4", "300,10,5", "1000,10,5", "300,30,5", "1000,30,5", "300,31,6", "1000,31,6", "1001,9,6",
			"3000,9,6", "1001,10,8", "3000,10,8", "1001,30,8", "3000,30,8", "1001,31,10", "3000,31,10", "3001,9,12",
			"5000,9,12", "3001,10,18", "5000,10,18", "3001,30,18", "5000,30,18", "3001,31,25", "5000,31,25",
			"5001,9,25", "50000,9,25", "5001,10,30", "50000,10,30", "5001,30,30", "50000,30,30", "5001,31,35",
			"50000,31,35" })
	public void testCalculateChargeForDocumentValidValues(double weight, double distance, double expectedCharge) {
		Document doc = new Document(weight, distance);
		doc.calculateCharge();
		assertEquals(expectedCharge, doc.getCharge(), 0);
	}

	@Test(expected = IllegalArgumentException.class) // TCN002
	@Parameters({ "0, 50", "50,0", "50001,50", "50,81" })
	public void testCalculateChargeForDocumentInvalidValues(double weight, double distance) {
		Document doc = new Document(weight, distance);
		doc.calculateCharge();
	}

	@Test // TCN003
	@Parameters({ "1,9,5", "1000,9,5", "1,10,8", "1000,10,8", "1,30,8", "1000,30,8", "1,31,10", "1000,31,10",
			"1001,9,15", "2000,9,15", "1001,10,18", "2000,10,18", "1001,30,18", "2000,30,18", "1001,31,25",
			"2000,31,25", "2001,9,23", "3000,9,23", "2001,10,28", "3000,10,28", "2001,30,28", "3000,30,28",
			"2001,31,35", "3000,31,35", "3001,9,35", "5000,9,35", "3001,10,40", "5000,10,40", "3001,30,40",
			"5000,30,40", "3001,31,50", "5000,31,50", "5001,9,45", "50000,9,45", "5001,10,50", "50000,10,50",
			"5001,30,50", "50000,30,50", "5001,31,60", "50000,31,60" })
	public void testCalculateChargeForParcelValidValues(double weight, double distance, double expectedCharge) {
		Parcel par = new Parcel(weight, distance);
		par.calculateCharge();
		assertEquals(expectedCharge, par.getCharge(), 0);
	}

	@Test(expected = IllegalArgumentException.class) // TCN004
	@Parameters({ "0, 50", "50,0", "50001,50", "50,81" })
	public void testCalculateChargeForParcelInvalidValues(double weight, double distance) {
		Parcel par = new Parcel(weight, distance);
		par.calculateCharge();
	}

}
