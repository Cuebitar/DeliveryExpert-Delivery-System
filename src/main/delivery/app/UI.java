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

	// let user chose which eventNo he/she wants to perform
	public static int askEventNo(int beginEventNo, int endEventNo) {
		if (beginEventNo > endEventNo) {
			System.out.printf("%nBug at UI.askEventNo(int, int): the beginEventNo should not greater than the endEventNo.");
			System.exit(1);
		}

		int eventNo;
		final String errorMessage = "Sorry, input failed. Please enter the number of option you want to perform.\n";

		while (true) {
			try {
				System.out.printf("%nPlease select> ");
				eventNo = Integer.parseInt(scanner.nextLine());

				if (eventNo >= beginEventNo && eventNo <= endEventNo) {
					break;
				} else {
					System.out.print(errorMessage);
				}
			} catch (NumberFormatException e) {
				System.out.print(errorMessage);
			}
		}
		return eventNo;
	}
	
}
