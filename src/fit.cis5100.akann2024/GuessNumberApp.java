package fit.cis5100.akann2024;

import java.util.Scanner;

public class GuessNumberApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice = "y";

        while (choice.equalsIgnoreCase("y")) {
            Game game = new Game();
            game.displayWelcomeMessage();
            game.displayPleaseGuessMessage();
            game.playGame(sc);
            choice = Console.getChoiceString(sc, "Try again? (y/n): ", "y", "n");
            System.out.println();
        }

        System.out.println("Bye - Come back soon!");
        System.out.println();
    }
}