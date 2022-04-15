package delivery.app;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientFile extends AbstractFile {

    // need to call retrieveFile first to be able to read and write to file
    private final static String FILENAME = "Client.txt";

    private ArrayList<Client> clientList;

    public ClientFile() {
        clientList = new ArrayList<>();
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
                Client addClient = new Client(id, name, telNo);
                clientList.add(addClient);
            }
            sc.close();
        }
        System.out.println(clientList.size());
    }

    @Override
    public void writeToFile(String fileName) {
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

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public void setClientList(ArrayList<Client> clientList) {
        this.clientList = clientList;
    }
}
