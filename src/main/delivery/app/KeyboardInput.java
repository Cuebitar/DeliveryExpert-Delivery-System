package delivery.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class KeyboardInput {
	
	public static Scanner scanner = new Scanner(System.in);
	private static final String stringError = "Sorry, cannot contain \"" + AbstractFile.getDELIMITER() + "\".";
	private static final KeyboardInput instance = new KeyboardInput();
	
	private KeyboardInput() {}
	
	public static KeyboardInput getInstance() {
		return instance;
	}
	
	public String askString(String info) {
		String input;

		while (true) {
			System.out.printf("%nEnter %s: ", info);
			input = scanner.nextLine();
			if (input.contains(AbstractFile.getDELIMITER())) {
				System.out.printf("%s%n", stringError);
			} else if (input.equals("")) {
				System.out.printf("\nPlease enter a %s.", info);
			} else {
				break;
			}
		}
		return input;
	}

	public double askPositiveDouble(String info) {
		double input;
		String errorMessage = "\nPlease enter a positive number.";

		while (true) {
			System.out.printf("%nEnter %s: ", info);
			try {
				input = Double.parseDouble(scanner.nextLine());
				if (input <= 0) {
					System.out.println(errorMessage);
				} else {
					return input;
				}
			} catch (NumberFormatException e) {
				System.out.println(errorMessage);
			}
		}
	}

	public int askPositiveInt(String info) {
		int input;
		String errorMessage = "Please enter a positive integer.";

		while (true) {
			System.out.printf("%nEnter %s: ", info);
			try {
				input = Integer.parseInt(scanner.nextLine());
				if (input < 1) {
					System.out.println(errorMessage);
				} else {
					return input;
				}
			} catch (NumberFormatException e) {
				System.out.println(errorMessage);
			}
		}
	}

	public boolean askBoolean(String info) {
		String ans;
		while (true) {
			System.out.printf("%n%n%s%s", info, " (y/n)? ");
			ans = scanner.nextLine().toLowerCase();

			if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes")) {
				return true;
			} else if (ans.equalsIgnoreCase("n") || ans.equalsIgnoreCase("no")) {
				return false;
			} else {
				System.out.println("Please enter \"Y/y\" or \"N/n\".");
			}
		} // end of while
	}

	public LocalDate askDate(String dateName) {
		String stringDate;
		LocalDate date;

		while (true) {
			System.out.print("\nEnter " + dateName + " in \"dd/mm/yyyy\" format: ");
			try {
				LocalDate today = LocalDate.now();
				stringDate = scanner.nextLine();
				date = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				if (date.isBefore(today)) {
					System.out.printf("%n%s%n", "Sorry, please enter a future date.");
				} else {
					return date;
				}
			} catch (DateTimeParseException e) {
				System.out.printf("%n%s%n", "Sorry, please enter a valid date.");
			}
		}
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
