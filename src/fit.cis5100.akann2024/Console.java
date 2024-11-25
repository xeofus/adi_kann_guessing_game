package fit.cis5100.akann2024;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

    public static int getIntWithinRange(Scanner sc, String prompt, int min, int max) {
        int i = 0;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print(prompt);
                String input = sc.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("You chose to exit. Goodbye!");
                    System.exit(0);
                }

                i = Integer.parseInt(input);

                if (i <= min) {
                    System.out.println("Error! Number must be greater than " + min);
                } else if (i >= max) {
                    System.out.println("Error! Number must be less than " + max);
                } else {
                    isValid = true;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Error! Invalid integer value. Try again or type 'exit' to quit.");
            }
        }
        return i;
    }

    public static String getRequiredString(Scanner sc, String prompt) {
        String s = "";
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            s = sc.nextLine().trim();
            if (s.isEmpty()) {
                System.out.println("Error! This entry is required. Try again.");
            } else {
                isValid = true;
            }
        }
        return s;
    }

    public static String getChoiceString(Scanner sc, String prompt, String s1, String s2) {
        String s = "";
        boolean isValid = false;
        while (!isValid) {
            s = getRequiredString(sc, prompt);
            if (!s.equalsIgnoreCase(s1) && !s.equalsIgnoreCase(s2)) {
                System.out.println("Error! Entry must be '" + s1 + "' or '" + s2 + "'. Try again.");
            } else {
                isValid = true;
            }
        }
        return s;
    }
}
