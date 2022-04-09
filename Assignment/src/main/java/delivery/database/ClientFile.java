package main.java.delivery.database;

import main.java.delivery.app.Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientFile extends AbstractFile {
    final static String NAME = "Client.txt";

    public ClientFile() {
        super(NAME);
    }

    public void save(ArrayList<Client> clientList) {
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

    public void retrieve(ArrayList<Client> clientList) {
        try {
            file = new File(fileName);
            fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String str = fileScanner.nextLine();
                readClientFile(str, clientList);
            }
        } catch (FileNotFoundException e) {
            System.out.print("Can't read file");
        }
    }

    private void readClientFile(String str, ArrayList<Client> clientList) {
        String id, name, telNo;
        Scanner sc = new Scanner(str);
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
