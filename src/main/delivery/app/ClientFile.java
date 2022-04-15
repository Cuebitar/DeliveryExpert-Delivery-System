package delivery.app;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientFile extends AbstractFile {

	// need to call retrieveFile first to be able to read and write to file

	public ClientFile() {
	}

	public void readClientFile(ArrayList<Client> clientList) {
		String id, name, telNo;
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			Scanner sc = new Scanner(line);
			sc.useDelimiter(AbstractFile.getDELIMITER());
			while (sc.hasNext()) {
				id = sc.next();
				name = sc.next();
				telNo = sc.next();
				Client addClient = new Client(id, name, telNo);
				clientList.add(addClient);
			}
			sc.close();
		}
	}

	public void writeClientFile(ArrayList<Client> clientList) {
		try {
			fileWriter = new FileWriter(fileName, false);
			printWriter = new PrintWriter(fileWriter, false);
			printWriter.flush();
			for (Client i : clientList) {
				printWriter.write(i.toString());
				printWriter.write("\n");
			}
			fileWriter.close();
			printWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}
