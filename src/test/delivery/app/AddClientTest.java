package delivery.app;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class AddClientTest {

	private Object[] parametersForTestRetrieveFileValidValues() {
		return new Object[] {
				new Object[] { "Client.txt" },
		};
	}

	@Test()
	@Parameters()
	public void testRetrieveFileValidValues(String filename) {
		AbstractFile af = new ClientFile();
		af.readFromFile(filename);
		assertEquals(filename, af.getFileName());
	}

	private Object[] parametersForTestRetrieveFileInvalidValues() {
		return new Object[] {
				new Object[] { ".txt" },
				new Object[] { "" },
		};
	}

	@Test(expected = IllegalArgumentException.class)
	@Parameters()
	public void testRetrieveFileInvalidValues(String filename) {
		AbstractFile af = new ClientFile();
		af.readFromFile(filename);
	}

}
