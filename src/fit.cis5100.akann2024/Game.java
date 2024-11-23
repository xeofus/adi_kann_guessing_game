package fit.cis5100.akann2024;

import java.util.Scanner;

public class Game {
    private final int number;
    private int guessNumber;
    private int counter;

    public Game() {
        number = getRandomNumber();
        guessNumber = 0;
        counter = 1;
    }

    public void displayWelcomeMessage() {
        System.out.println("Welcome to the Guess the Number Game");
        System.out.println("++++++++++++++++++++++++++++++++++++");
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
            System.out.println("Great work! You are a mathematical wizard.\n");
        } else if (counter <= 7) {
            System.out.println("Not too bad! You've got some potential.\n");
        } else {
            System.out.println("What took you so long? Maybe you should take some lessons.\n");
        }
    }

    public void displayGuessAgainMessage() {
        int difference = guessNumber - number;
        if (guessNumber > number) {
            if (difference > 10) {
                System.out.println("Way too high! Guess again.\n");
            } else {
                System.out.println("Too high! Guess again.\n");
            }
        } else {
            if (difference < -10) {
                System.out.println("Way too low! Guess again.\n");
            } else {
                System.out.println("Too low! Guess again.\n");
            }
        }
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
}
