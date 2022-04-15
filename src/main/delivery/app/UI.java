package delivery.app;

import java.util.Scanner;

public class UI {
	// display the program name
	public static void displayHeading(String heading) {
		System.out.printf("%n%n");
		System.out.print("***************************************");
		System.out.printf("%n%n");
		System.out.print("            ");
		System.out.print(heading);
		System.out.printf("%n%n");
		System.out.print("***************************************");
		System.out.printf("%n%n");
	}

	// display the functions provided in this program
	public static void displayMainMenu() {
		System.out.println("1. Search Delivery   ");
		System.out.println("2. Add New Delivery  ");
		System.out.println("3. Cancel Delivery   ");
		System.out.println();
		System.out.println("4. Check Delivery Fee");
		System.out.println("5. Add New Client    ");
		System.out.println("0. Exit              ");
	}

	
}
