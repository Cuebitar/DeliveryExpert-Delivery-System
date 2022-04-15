package delivery.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CalculateAdditionalChargesTest {

	private String sameDayDelivery, withInsurance;
	private double expectedAdditionalCharge;
	static Scanner inputStream;
	static File file;
	static Scanner input;

	@Test
	public void testCalculateAdditionalChargeValidValues() throws FileNotFoundException {
		ClassLoader classLoader = this.getClass().getClassLoader();
		file = new File(classLoader.getResource("F002TestData.txt").getFile());
		inputStream = new Scanner(file);
		String lineRead = null;

		while (inputStream.hasNextLine()) {
			lineRead = inputStream.nextLine();
			input = new Scanner(lineRead);
			input.useDelimiter(",");

			while (input.hasNext()) {
				sameDayDelivery = input.next();
				withInsurance = input.next();
				expectedAdditionalCharge = input.nextDouble();
			}

			Delivery del = new Delivery("", "", "", "", "", "2", sameDayDelivery, withInsurance, "", "", "22/02/2022");
			del.calculateAdditionalCharge();
			assertEquals(expectedAdditionalCharge, del.getAdditionalCharge(), 0);
			input.close();
		}
		inputStream.close();
	}

}
