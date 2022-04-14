package delivery.app;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class DisplayNoteTest {

	private ArrayList<Delivery> deliveryTestList = new ArrayList<Delivery>();
	private ArrayList<Client> clientTestList = new ArrayList<Client>();

	@Before
	public void setupBeforeTest() {
		ClientFile clientFile = new ClientFile();
		TransactionFile readFile = new TransactionFile();
		clientFile.retrieve(clientTestList);
		readFile.retrieve(deliveryTestList);
	}

	@Test
	@Parameters({ "Lisa" })
	public void SearchDeliveryListValidTest(String inputData) {
		TransactionList tList = new TransactionList();
		assertTrue(tList.searchDeliveryList(deliveryTestList, inputData));
	}

	@Test
	@Parameters({ "Esther", "Rose" })
	public void SearchDeliveryListInvalidTest(String inputData) {
		TransactionList tList = new TransactionList();
		assertFalse(tList.searchDeliveryList(deliveryTestList, inputData));
	}

}
