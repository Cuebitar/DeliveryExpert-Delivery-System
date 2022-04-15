package delivery.app;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StaffFile extends AbstractFile {

    // need to call retrieveFile first to be able to read and write to file
    private final static String FILENAME = "Staff.txt";

    private ArrayList<Staff> staffList;

    public StaffFile() {
        staffList = new ArrayList<>();
    }

    @Override
    public void readFromFile(String fileName) {
        try {
            this.fileName = fileName;
            file = new File(fileName);
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File does not exist : " + fileName);
        }
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
        System.out.println(staffList.size());
    }

    @Override
    public void writeToFile(String fileName) {

    }

    public ArrayList<Staff> getStaffList() {
        return staffList;
    }

}
