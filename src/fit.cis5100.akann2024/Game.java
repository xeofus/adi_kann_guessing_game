package fit.cis5100.akann2024;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {
    private final int number;
    private int guessNumber;
    private int counter;
    private final Set<String> providedHints;

    public Game() {
        number = getRandomNumber();
        guessNumber = 0;
        counter = 1;
        providedHints = new HashSet<>();
    }

    public void displayWelcomeMessage() {
        System.out.println("----------------------------------------");
        System.out.println("Welcome to the Guess the Number Game");
        System.out.println("++++++++++++++++++++++++++++++++++++");
        System.out.println("You can type 'exit' at any time to quit.");
        System.out.println();
    }

    public int getRandomNumber() {
        return (int) (Math.random() * 100) + 1;
    }

    public void displayPleaseGuessMessage() {
        System.out.println("I'm thinking of a number from 1 to 100.");
        System.out.println("Try to guess it.");
        System.out.println();
    }

    public void displayCorrectGuessMessage() {
        System.out.println("You got it in " + counter + " tries.");
        if (counter <= 3) {
            System.out.println("Great work! You are a mathematical wizard.");
        } else if (counter <= 7) {
            System.out.println("Not too bad! You've got some potential.");
        } else {
            System.out.println("What took you so long? Maybe you should take some lessons.");
        }
        System.out.println("----------------------------------------");
    }

    public void displayGuessAgainMessage() {
        int difference = guessNumber - number;
        if (guessNumber > number) {
            if (difference > 10) {
                System.out.println("Way too high! Guess again.");
            } else {
                System.out.println("Too high! Guess again.");
            }
        } else {
            if (difference < -10) {
                System.out.println("Way too low! Guess again.");
            } else {
                System.out.println("Too low! Guess again.");
            }
        }

        // Provide one unrepeatable hint per prompt
        if (!providedHints.contains("two-digit number") && number >= 10 && number <= 99) {
            System.out.println("Hint: The correct number is a two-digit number.");
            providedHints.add("two-digit number");
        } else if (!providedHints.contains("even") && number % 2 == 0) {
            System.out.println("Hint: The correct number is even.");
            providedHints.add("even");
        } else if (!providedHints.contains("odd") && number % 2 != 0) {
            System.out.println("Hint: The correct number is odd.");
            providedHints.add("odd");
        } else if (!providedHints.contains("multiple of 5") && number % 5 == 0) {
            System.out.println("Hint: The number is a multiple of 5.");
            providedHints.add("multiple of 5");
        }

        System.out.println();
    }

    public void playGame(Scanner sc) {
        while (guessNumber != number) {
            guessNumber = Console.getIntWithinRange(sc, "Enter number: ", 0, 101);
            if (guessNumber == number) {
                displayCorrectGuessMessage();
            } else {
                displayGuessAgainMessage();
            }
            counter++;
        }
    }

    public void displayGameSummary() {
        System.out.println("\u001B[36mGame Summary\u001B[0m");
        System.out.println("\u001B[33m----------------------------------------\u001B[0m");
        System.out.println("\u001B[32mThe correct number was: \u001B[0m" + number);
        System.out.println("\u001B[32mNumber of attempts: \u001B[0m" + counter);
        if (counter <= 3) {
            System.out.println("\u001B[32mExcellent job! You guessed it very quickly.\u001B[0m");
        } else if (counter <= 7) {
            System.out.println("\u001B[33mGood job! You did well.\u001B[0m");
        } else {
            System.out.println("\u001B[31mYou eventually got it! Keep practicing to improve.\u001B[0m");
        }
        System.out.println("\u001B[33m----------------------------------------\u001B[0m");
        System.out.println();
    }
}
