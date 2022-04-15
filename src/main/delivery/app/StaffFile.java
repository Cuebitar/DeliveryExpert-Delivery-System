package delivery.app;


import java.util.ArrayList;
import java.util.Scanner;

public class StaffFile extends AbstractFile {

	final static String FILENAME = "Staff.txt";

	public StaffFile() {
		this.retrieveFile(FILENAME);
	}

	public void readStaffFile(ArrayList<Staff> staffList) {
		String id, name, telNo;
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			Scanner sc = new Scanner(line);
			sc.useDelimiter(AbstractFile.getDELIMITER());
			while (sc.hasNext()) {
				id = sc.next();
				name = sc.next();
				telNo = sc.next();
				Staff addStaff = new Staff(id, name, telNo);
				staffList.add(addStaff);
			}
			sc.close();
		}
	}

}
