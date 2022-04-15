package delivery.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestFileInput {
	public static ArrayList<String[]> readTestInput(String fileName) {
		ArrayList<String[]> linesRead = new ArrayList<String[]>();
		Scanner inputStream = null;
		
		try {
			inputStream = new Scanner(new File(fileName)).useDelimiter(",");
		} catch(FileNotFoundException e) {
			System.out.println("Error opening the file " + fileName);
			System.exit(0);
		}
		
		while (inputStream.hasNextLine()) {
			String singleLine = inputStream.nextLine();
			String[] tokens = singleLine.split(" ");
			linesRead.add(tokens);
		}

		inputStream.close();
		return linesRead;
	}
	
	public static ArrayList<String> readTestInput2(String fileName) {
		ArrayList<String> linesRead = new ArrayList<String>();
		Scanner inputStream = null;
		
		try {
			inputStream = new Scanner(new File(fileName));
		} catch(FileNotFoundException e) {
			System.out.println("Error opening the file " + fileName);
			System.exit(0);
		}
		
		while (inputStream.hasNextLine()) {
			linesRead.add(inputStream.nextLine());
		}

		return linesRead;
	}
}