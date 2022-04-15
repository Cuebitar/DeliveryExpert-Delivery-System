package delivery.app;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@RunWith(JUnitParamsRunner.class)
public class CalculateChargesTest {

	private double weight;
	private double distance;
	private double expectedCharge;

	static Scanner inputStream;
	static File file;
	static Scanner input;

	@Test
	public void testCalculateChargeForDocumentValidValues() throws FileNotFoundException {
		ClassLoader classLoader = this.getClass().getClassLoader();
		file = new File(classLoader.getResource("F001-1TestData.txt").getFile());
		inputStream = new Scanner(file);
		String lineRead = null;

		while (inputStream.hasNextLine()) {
			lineRead = inputStream.nextLine();
			input = new Scanner(lineRead);
			input.useDelimiter(",");

			while (input.hasNext()) {
				weight = input.nextDouble();
				distance = input.nextDouble();
				expectedCharge = input.nextDouble();
			}

			Document doc = new Document(weight, distance);
			doc.calculateCharge();
			assertEquals(expectedCharge, doc.getCharge(), 0);
			input.close();
		}
		inputStream.close();
	}

	@Test(expected = IllegalArgumentException.class)
	@Parameters({ "-1, 50", "50, -1", "0, 50", "50, 0", "50001,50", "50,81" })
	public void testCalculateChargeForDocumentInvalidValues(double weight, double distance) {
		Document doc = new Document(weight, distance);
		doc.calculateCharge();
	}

	@Test
	public void testCalculateChargeForParcelValidValues() throws FileNotFoundException {
		ClassLoader classLoader = this.getClass().getClassLoader();
		file = new File(classLoader.getResource("F001-2TestData.txt").getFile());
		inputStream = new Scanner(file);
		String lineRead = null;

		while (inputStream.hasNextLine()) {
			lineRead = inputStream.nextLine();
			input = new Scanner(lineRead);
			input.useDelimiter(",");

			while (input.hasNext()) {
				weight = input.nextDouble();
				distance = input.nextDouble();
				expectedCharge = input.nextDouble();
			}

			Parcel par = new Parcel(weight, distance);
			par.calculateCharge();
			assertEquals(expectedCharge, par.getCharge(), 0);
			input.close();
		}
		inputStream.close();
	}

	@Test(expected = IllegalArgumentException.class)
	@Parameters({ "-1, 50", "50, -1", "0, 50", "50,0", "50001,50", "50,81" })
	public void testCalculateChargeForParcelInvalidValues(double weight, double distance) {
		Parcel par = new Parcel(weight, distance);
		par.calculateCharge();
	}

}
