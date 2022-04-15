package delivery.app;

import java.util.ArrayList;

import dataAccess.ClientFile;
import dataAccess.StaffFile;
import dataAccess.TransactionFile;
import domain.Client;
import domain.Delivery;
import domain.Document;
import domain.Item;
import domain.Parcel;
import domain.Staff;

public class DeliveryApp {

	public static void main(String[] args) {

		int eventNo; // the action that user wants to perform
		int startingEventNo = 0; // avoid magic number
		int closingEventNo = 5;

		TransactionList transactions = new TransactionList();

		// read Transaction.txt to an ArrayList
		ArrayList<Delivery> deliveryList = new ArrayList<>();
		TransactionFile transactionFile = new TransactionFile();
		transactionFile.readTransactionFile(deliveryList);

		// read Client.txt to an ArrayList
		ArrayList<Client> clientList = new ArrayList<>();
		ClientFile clientFile = new ClientFile();
		clientFile.readClientFile(clientList);

		// read Staff.txt to an ArrayList
		ArrayList<Staff> staffList = new ArrayList<>();
		StaffFile staffFile = new StaffFile();
		staffFile.readStaffFile(staffList);

		// system main functions
		do {
			UI.displayHeading("DeliveryExpert");
			UI.displayMainMenu();

			eventNo = UI.askEventNo(startingEventNo, closingEventNo);
			switch (eventNo) {
			case 1:
				// search delivery list by client's name and display the delivery details
				String name = KeyboardInput.askString("client's name");
				transactions.searchDeliveryList(deliveryList, name);
				break;
			case 2:
				// add a new delivery to list and print the delivery note (output text file)
				// todo: what if they have the exact same name?
				Delivery newDelivery = transactions.addDelivery(deliveryList);
				if (!String.valueOf(newDelivery.getDeliveryID()).equals("0")) {
					transactionFile.writeTransactionFile(deliveryList);
					transactionFile.deliveryNote(newDelivery);
				}
				break;
			case 3:
				// search delivery list and cancel the delivery from the delivery list
            	String name1 = KeyboardInput.askString("client's name");
                int toBeCancel = transactions.cancelDelivery(deliveryList, name1);
				if (toBeCancel != 0) {
					transactionFile.writeTransactionFile(deliveryList);
					transactionFile.deleteDeliveryNote(toBeCancel);
				}
				break;
			case 4:
				// to check the delivery charges for document and parcel
				checkDeliveryFee();
				break;
			case 5:
				// to add new client details to Client.txt
				addNewClient(clientList);
				clientFile.writeClientFile(clientList);
				System.out.println("\nNew Client Added.");
				break;
			case 0:
				// exit the system
				System.out.printf("%nThank you for using this system!%n");
			}
		} while (eventNo > 0);
	}

	public static void checkDeliveryFee() {
		double weight, distance;

		// to ask weight and distance
		weight = KeyboardInput.askPositiveDouble("weight in grams");
		distance = KeyboardInput.askPositiveDouble("distance in km");

		// to display the calculated delivery charges
		Item document = new Document(weight, distance);
		System.out.printf("%n%nCharge for document: RM %.2f%n", document.getCharge());
		Item parcel = new Parcel(weight, distance);
		System.out.printf("Charge for parcel: RM %.2f", parcel.getCharge());
	}

	public static void addNewClient(ArrayList<Client> clientList) {
		String name, telNo;
		int id;

		// to ask client's information
		name = KeyboardInput.askString("new client's name");
		telNo = KeyboardInput.askString("phone number");
		id = Integer.parseInt(clientList.get(clientList.size() - 1).getId()) + 1;
		Client client = new Client(String.valueOf(id), name, telNo);
		clientList.add(client);
	}

}
