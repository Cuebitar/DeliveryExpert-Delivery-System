package delivery_System;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class StaffFile extends AbstractFile{
    final static String NAME = "Staff.txt";

    public StaffFile() {
        super(NAME);
    }

    public void retrieve(ArrayList<Staff> staffList) {
        try {
            file = new File(fileName);
            fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String str = fileScanner.nextLine();
                readStaffFile(str, staffList);
            }
        } catch (FileNotFoundException e) {
            System.out.print("Can't read file");
        }
    }

    private void readStaffFile(String str, ArrayList<Staff> staffList) {
        String id, name, telNo;
        Scanner sc = new Scanner(str);
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
