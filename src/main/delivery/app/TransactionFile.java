package delivery.app;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TransactionFile extends AbstractFile {

	// need to call retrieveFile first to be able to read and write to file

	public TransactionFile() {

	}

	public void deliveryNote(Delivery aDelivery) {
		File newDeliveryNote = new File("DeliveryNote/" + aDelivery.getDeliveryID() + ".txt");
		boolean openFile;
		try {
			openFile = newDeliveryNote.createNewFile();
			FileWriter writeFile = new FileWriter(newDeliveryNote);
			if (openFile) {
				writeFile.write(this.printDeliveryNote(aDelivery));
			}
			writeFile.close();

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void deleteDeliveryNote(int deliveryID) {
		try {
			File f = new File("DeliveryNote/" + deliveryID + ".txt");
			if (f.delete()) {
				System.out.println("\nDelivery note: " + f.getName() + " deleted.");
			} else {
				System.out.println("Delivery note deletion failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String printDeliveryNote(Delivery aDelivery) {
		String temp = aDelivery.isSameDayDelivery() ? "Yes" : "No";
		String temp2 = aDelivery.isWithInsurance() ? "Yes" : "No";
		StringBuilder deliveryNote = new StringBuilder();
		deliveryNote.append("          Delivery ID: ").append(aDelivery.getDeliveryID()).append("\n\n");
		deliveryNote.append("  --- Client ---" + "\n");
		deliveryNote.append("Client ID       : ").append(aDelivery.getClient().getId()).append("\n");
		deliveryNote.append("Client Name     : ").append(aDelivery.getClient().getName()).append("\n");
		deliveryNote.append("Client Phone No.: ").append(aDelivery.getClient().getTelNo()).append("\n\n");
		deliveryNote.append("  --- Receiver ---" + "\n");
		deliveryNote.append("Receiver Name     : ").append(aDelivery.getReceiver().getName()).append("\n");
		deliveryNote.append("Receiver Phone No.: ").append(aDelivery.getReceiver().getTelNo()).append("\n\n");
		deliveryNote.append("  --- Staff ---" + "\n");
		deliveryNote.append("Staff ID       : ").append(aDelivery.getStaff().getId()).append("\n");
		deliveryNote.append("Staff Name     : ").append(aDelivery.getStaff().getName()).append("\n");
		deliveryNote.append("Staff Phone No.: ").append(aDelivery.getStaff().getTelNo()).append("\n\n");
		deliveryNote.append("  --- Delivery ---" + "\n");
		deliveryNote.append("Same day delivery     : ").append(temp).append("\n");
		deliveryNote.append("Deliver with insurance: ").append(temp2).append("\n");
		deliveryNote.append("Pick Up Location      : ").append(aDelivery.getPickUpLocation()).append("\n");
		deliveryNote.append("Drop Off Location     : ").append(aDelivery.getDropOffLocation()).append("\n");
		deliveryNote.append("Pick Up Date          : ").append(aDelivery.getPickUpDate()).append("\n\n");
		deliveryNote.append("  --- Items ---" + "\n");
		deliveryNote.append("Item No | Weight (g) | Distance (km) | Charge (RM) | Item Type" + "\n");
		for (int i = 0; i < aDelivery.getItems().size(); i++) {
			deliveryNote.append(String.format("%7s | %10.2f | %13.2f | %11.2f | %9s\n", i + 1,
					aDelivery.getItems().get(i).getWeight(), aDelivery.getItems().get(i).getDistance(),
					aDelivery.getItems().get(i).getCharge(), aDelivery.getItems().get(i).itemType()));
		}
		deliveryNote.append("\n\n");
		if (aDelivery.isSameDayDelivery()) {
			deliveryNote.append("Same Day Delivery:                      RM   10.00" + "\n");
		}
		if (aDelivery.isWithInsurance()) {
			deliveryNote.append("Deliver with Insurance:                 RM   15.00" + "\n");
		}
		deliveryNote.append(String.format("\n%-40sRM%8.2f%n", "Total Charge:", aDelivery.getTotalCharge()));
		System.out.println("\nDelivery note for delivery ID: " + aDelivery.getDeliveryID() + " is created.");
		return deliveryNote.toString();
	}

	public void writeTransactionFile(ArrayList<Delivery> deliveryList) {
		try {
			fileWriter = new FileWriter(fileName, false);
			printWriter = new PrintWriter(fileWriter, false);
			printWriter.flush();
			for (Delivery i : deliveryList) {
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

	public void readTransactionFile(ArrayList<Delivery> deliveryList) {
		String staffID, clientID, deliveryID, sameDayDelivery, withInsurance;
		String receiverName, receiverTelNo, pickUpLocation, dropOffLocation, pickUpDate, itemList;
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			Scanner sc = new Scanner(line);
			sc.useDelimiter(AbstractFile.getDELIMITER());
			while (sc.hasNext()) {
				deliveryID = sc.next();
				clientID = sc.next();
				receiverName = sc.next();
				receiverTelNo = sc.next();
				staffID = sc.next();
				sameDayDelivery = sc.next();
				withInsurance = sc.next();
				pickUpLocation = sc.next();
				dropOffLocation = sc.next();
				pickUpDate = sc.next();
				itemList = sc.next();
				Delivery addTransaction = new Delivery(itemList, staffID, clientID, receiverName, receiverTelNo,
						deliveryID, sameDayDelivery, withInsurance, pickUpLocation, dropOffLocation, pickUpDate);
				deliveryList.add(addTransaction);
			}
			sc.close();
		}
	}

}
