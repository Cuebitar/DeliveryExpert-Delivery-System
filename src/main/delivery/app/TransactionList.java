package delivery.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import domain.Client;
import domain.Delivery;
import domain.Staff;

public class TransactionList { // controller class for Delivery class

	public boolean searchDeliveryList(ArrayList<Delivery> deliveryList, String name) {
		// search delivery list by client's name and display the delivery details
		boolean found = false;
		for (Delivery delivery : deliveryList) {
			if (delivery.getClient().getName().toLowerCase().contains(name.toLowerCase())) {
				delivery.printDeliveryDetails();
				found = true;
			}
		}
		if (!found) {
			System.out.println("No delivery found.");
		}
		return found;
	}

	public Delivery addDelivery(ArrayList<Delivery> deliveryList) {
		// add a new delivery to list and print the delivery note (output text file)
		String clientName = KeyboardInput.askString("client's name or ID");
		Client client = Delivery.findClient(clientName);
		if (client.getName() == null) {
			System.out.println("No such client.");
			return new Delivery();
		}
		String staffName = KeyboardInput.askString("staff name or ID");
		Staff staff = Delivery.findStaff(staffName);
		if (staff.getName() == null) {
			System.out.println("No such staff.");
			return new Delivery();
		}
		String receiverName = KeyboardInput.askString("receiver name");
		String receiverTelNo = KeyboardInput.askString("receiver phone no.");
		String pickUpLocation = KeyboardInput.askString("pick up location");
		String dropOffLocation = KeyboardInput.askString("drop off location");
		LocalDate date = KeyboardInput.askDate("pick up date");
		boolean sameDayDelivery = KeyboardInput.askBoolean("Same day delivery");
		boolean withInsurance = KeyboardInput.askBoolean("Deliver with insurance");
		int deliveryID = deliveryList.get(deliveryList.size() - 1).getDeliveryID() + 1;

		StringBuilder sb = new StringBuilder();
		StringBuilder str = new StringBuilder();
		String str1;
		double distance = KeyboardInput.askPositiveDouble("distance in km");
		int numberOfItems = KeyboardInput.askPositiveInt("number of items");
		for (int i = 0; i < numberOfItems; i++) {
			System.out.printf("\n\n-- Item %o --\n ", (i + 1));
			double weight = KeyboardInput.askPositiveDouble("weight in grams");
			boolean document = KeyboardInput.askBoolean("Item type: Document");
			if (document) {
				str1 = String.format("%s %s %s ", "Document", weight, distance);
			} else {
				str1 = String.format("%s %s %s ", "Parcel", weight, distance);
			}
			str = sb.append(str1);
		}

		Delivery newDelivery = new Delivery(str.toString(), staff.getId(), client.getId(), receiverName, receiverTelNo,
				String.valueOf(deliveryID), String.valueOf(sameDayDelivery), String.valueOf(withInsurance),
				pickUpLocation, dropOffLocation, date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		deliveryList.add(newDelivery);

		System.out.println("\nNew delivery added.");
		return newDelivery;
	}

	public int cancelDelivery(ArrayList<Delivery> deliveryList, String name) {
		// search delivery list and cancel the delivery from the delivery list
		boolean found = searchDeliveryList(deliveryList, name);
		if (found) {
			int deliveryID = KeyboardInput.askPositiveInt("the delivery ID to select");
			boolean confirm = KeyboardInput.askBoolean("Confirm cancellation");
			if (confirm) {
				for (int i = 0; i < deliveryList.size(); i++) {
					if (deliveryList.get(i).getDeliveryID() == deliveryID) {
						deliveryList.remove(i);
						System.out.println("\nDelivery cancelled.");
						return deliveryID;
					}
				}
				System.out.println("No delivery was cancelled.");
			}
		}
		return 0;
	}

}
