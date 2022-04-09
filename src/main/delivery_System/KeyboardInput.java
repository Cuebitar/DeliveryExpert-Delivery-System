package delivery_System;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class KeyboardInput {
    private static final Scanner sn = new Scanner(System.in);
    private static final String stringError = "Sorry, cannot contain \"" + AbstractFile.getDELIMITER() + "\".";

    public static String askString(String info) {
        String input;

        while (true) {
            System.out.printf("%nEnter %s: ", info);
            input = sn.nextLine();
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

    public static double askPositiveDouble(String info) {
        double input;
        String errorMessage = "\nPlease enter a positive number.";

        while (true) {
            System.out.printf("%nEnter %s: ", info);
            try {
                input = Double.parseDouble(sn.nextLine());
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

    public static int askPositiveInt(String info) {
        int input;
        String errorMessage = "Please enter a positive integer.";

        while (true) {
            System.out.printf("%nEnter %s: ", info);
            try {
                input = Integer.parseInt(sn.nextLine());
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

    public static boolean askBoolean(String info) {
        String ans;
        while (true) {
            System.out.printf("%n%n%s%s", info, " (y/n)? ");
            ans = sn.nextLine().toLowerCase();

            if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes")) {
                return true;
            } else if (ans.equalsIgnoreCase("n") || ans.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("Please enter \"Y/y\" or \"N/n\".");
            }
        } // end of while
    }

    public static LocalDate askDate(String dateName) {
        String stringDate;
        LocalDate date;

        while (true) {
            System.out.print("\nEnter " + dateName + " in \"dd/mm/yyyy\" format: ");
            try {
                LocalDate today = LocalDate.now();
                stringDate = sn.nextLine();
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
}
