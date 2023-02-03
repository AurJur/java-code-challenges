package com.linkedin.javacodechallenges.task0103;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Aurelijus Jurkus
 * @since 15-Jan-2023
 */
public class DoubleOrNothing {

    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);

    public void playGame() {

        int currentPoints = 10;
        printCurrentPoints(currentPoints);

        while (currentPoints > 0 && askUserToContinue()) {
            currentPoints = playAndRecalculate(currentPoints);
            printCurrentPoints(currentPoints);
        }

        printBye();
        scanner.close();
    }

    boolean askUserToContinue() {
        System.out.println("Do you want to play? (Y/N):");
        String userInput = scanner.nextLine();
        if (userInput.equalsIgnoreCase("Y")) {
            return true;
        } else if (userInput.equalsIgnoreCase("N")) {
            return false;
        } else {
            System.out.println("You entered '%s'. I do not understand.".formatted(userInput));
            return askUserToContinue();
        }
    }

    private void printCurrentPoints(int currentPoints) {
        System.out.println("You have %s points.".formatted(currentPoints));
    }

    private int playAndRecalculate(int currentPoints) {
        boolean win = random.nextBoolean();
        if (win) {
            System.out.println("You doubled.");
            return currentPoints * 2;
        } else {
            System.out.println("You lost.");
            return 0;
        }
    }

    private void printBye() {
        System.out.println("Bye.");
    }

}
