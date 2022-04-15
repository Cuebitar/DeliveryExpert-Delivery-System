package delivery.app;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public abstract class AbstractFile {

	protected String fileName;
	protected Scanner fileScanner;
	protected File file;
	protected FileWriter fileWriter;
	protected PrintWriter printWriter;

	public AbstractFile() {
	}

	protected static String getDELIMITER() {
		return ";";
	}

	protected void retrieveFile(String fileName) {
		try {
			this.fileName = fileName;
			file = new File(fileName);
			fileScanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File does not exist : " + fileName);
		}
	}

}
