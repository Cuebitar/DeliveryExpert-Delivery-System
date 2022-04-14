package delivery.app;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value = Suite.class)
@SuiteClasses(value = { 
		CalculateChargesTest.class, 
		CalculateChargesTest.class, 
		AddClientTest.class,
		AssignStaffTest.class, 
		DisplayNoteTest.class }
)

public class TestSuite {
}