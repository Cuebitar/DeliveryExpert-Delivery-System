package main.java.delivery.app;

import main.java.delivery.database.TransactionFile;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        ArrayList<Delivery> deliveryList = new ArrayList<>();
        TransactionFile transactionFile = new TransactionFile();
        transactionFile.retrieve(deliveryList);
        for (Delivery i: deliveryList) {
            transactionFile.deliveryNote(i);
        }

    }
}
