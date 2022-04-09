package main.java.delivery.app;

import main.java.delivery.database.ClientFile;
import main.java.delivery.database.StaffFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Delivery {
    private ArrayList<Item> itemList;
    private Staff staff;
    private Client client;
    private Receiver receiver;

    private int deliveryID;
    private boolean sameDayDelivery;
    private boolean withInsurance;
    private double totalCharge;
    private String pickUpLocation;
    private String dropOffLocation;
    private LocalDate pickUpDate;

    public Delivery(String itemList, String staffID, String clientID, String receiverName, String receiverTelNo,
                    String deliveryID, String sameDayDelivery, String withInsurance, String pickUpLocation,
                    String dropOffLocation, String pickUpDate) {

        this.itemList = retrieveItems(itemList);
        this.staff = findStaff(staffID);
        this.client = findClient(clientID);
        this.receiver = new Receiver(receiverName, receiverTelNo);
        this.deliveryID = Integer.parseInt(deliveryID);
        this.withInsurance = Boolean.parseBoolean(withInsurance);
        this.sameDayDelivery = Boolean.parseBoolean(sameDayDelivery);
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.pickUpDate = LocalDate.parse(pickUpDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        calculateTotalCharge();
    }

    public Delivery() {
    }

    public int getDeliveryID() {
        return deliveryID;
    }

    public Client getClient() {
        return client;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public Staff getStaff() {
        return staff;
    }

    public boolean isSameDayDelivery() {
        return sameDayDelivery;
    }

    public boolean isWithInsurance() {
        return withInsurance;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public String getPickUpDate() {
        return pickUpDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    // get the items in String
    public String getItemList() {
        String itemType;
        StringBuilder sb = new StringBuilder();
        StringBuilder str = new StringBuilder();
        for (Item item : itemList) {
            if (item instanceof Parcel) {
                itemType = "Parcel";
            } else {
                itemType = "Document";
            }
            double weight = item.getWeight();
            double distance = item.getDistance();
            String str1 = String.format("%s %s %s ", itemType, weight, distance);
            str = sb.append(str1);
        }

        return str.toString();
    }

    // get items ArrayList
    public ArrayList<Item> getItems() {
        return itemList;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public void printDeliveryDetails() {
        String temp = sameDayDelivery ? "Yes" : "No";
        String temp2 = withInsurance ? "Yes" : "No";

        System.out.println("\n\n\n\n          Delivery ID: " + deliveryID);
        System.out.println();
        System.out.println("  --- Client ---");
        System.out.println("Client ID       : " + client.getId());
        System.out.println("Client Name     : " + client.getName());
        System.out.println("Client Phone No.: " + client.getTelNo());
        System.out.println();
        System.out.println("  --- Receiver ---");
        System.out.println("Receiver Name     : " + receiver.getName());
        System.out.println("Receiver Phone No.: " + receiver.getTelNo());
        System.out.println();
        System.out.println("  --- Staff ---");
        System.out.println("Staff ID       : " + staff.getId());
        System.out.println("Staff Name     : " + staff.getName());
        System.out.println("Staff Phone No.: " + staff.getTelNo());
        System.out.println();
        System.out.println("  --- Delivery ---");
        System.out.println("Same day delivery     : " + temp);
        System.out.println("Deliver with insurance: " + temp2);
        System.out.println("Pick Up Location      : " + pickUpLocation);
        System.out.println("Drop Off Location     : " + dropOffLocation);
        System.out.println("Pick Up Date          : " + pickUpDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println();
        System.out.println("  --- Items ---");
        System.out.println("Item No | Weight (g) | Distance (km) | Charge (RM) | Item Type");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.printf("%7s | %10.2f | %13.2f | %11.2f | %9s\n", i + 1, itemList.get(i).getWeight(),
                    itemList.get(i).getDistance(), itemList.get(i).getCharge(), itemList.get(i).itemType());
        }
        System.out.println("\n\n");
        if (sameDayDelivery) {
            System.out.println("Same Day Delivery:                      RM   10.00");
        }
        if (withInsurance) {
            System.out.println("Deliver with Insurance:                 RM   15.00");
        }
        System.out.printf("\n%-40sRM%8.2f%n", "Total Charge:", totalCharge);
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s", getDeliveryID(), getClient().getId(), getReceiver().getName(),
                getReceiver().getTelNo(), getStaff().getId(), isSameDayDelivery(), isWithInsurance(), getPickUpLocation(),
                getDropOffLocation(), getPickUpDate(), getItemList());
    }

    // find existing staff from Staff.txt
    public static Staff findStaff(String info) {
        // read Staff.txt to an ArrayList
        ArrayList<Staff> staffList = new ArrayList<>();
        StaffFile staffFile = new StaffFile();
        staffFile.retrieve(staffList);

        for (Staff value : staffList) {
            if (value.getId().equals(info) || value.getName().toLowerCase().contains(info.toLowerCase())) {
                return value;
            }
        }
        return new Staff();
    }

    // find existing client from Client.txt
    public static Client findClient(String info) {
        // read Client.txt to an ArrayList
        ArrayList<Client> clientList = new ArrayList<>();
        ClientFile clientFile = new ClientFile();
        clientFile.retrieve(clientList);

        for (Client value : clientList) {
            if (value.getId().equals(info) || value.getName().toLowerCase().contains(info.toLowerCase())) {
                return value;
            }
        }

        return new Client();
    }

    // transform items from String to ArrayList
    private ArrayList<Item> retrieveItems(String itemList) {
        ArrayList<Item> items = new ArrayList<>();

        StringTokenizer str = new StringTokenizer(itemList, " ");
        ArrayList<String> itemInfo = new ArrayList<>();

        // If tokens are present
        while (str.hasMoreTokens()) {
            itemInfo.add(str.nextToken());
        }

        for (int j = 0; j < itemInfo.size(); j += 3) {
            String itemType = itemInfo.get(j);
            double weight = Double.parseDouble(itemInfo.get(j + 1));
            double distance = Double.parseDouble(itemInfo.get(j + 2));
            if (itemType.equals("Parcel")) {
                items.add(new Parcel(weight, distance));
            } else {
                items.add(new Document(weight, distance));
            }
        }

        return items;
    }

    private void calculateTotalCharge() {
        for (Item anItem : itemList) {
            totalCharge += anItem.getCharge();
        }
        if (sameDayDelivery) {
            totalCharge += 10;
        }
        if (withInsurance) {
            totalCharge += 15;
        }
    }
}