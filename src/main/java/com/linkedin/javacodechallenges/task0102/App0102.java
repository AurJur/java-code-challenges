package com.linkedin.javacodechallenges.task0102;

import java.util.Scanner;

/**
 * @author Aurelijus Jurkus
 * @since 15-Jan-2023
 */
public class App0102 {

    public static boolean isPasswordComplex(String password) {

        if (password.length() < 6) {
            return false;
        }

        if (password.toLowerCase().equals(password)) {
            return false;
        }

        if (password.toUpperCase().equals(password)) {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                return true;
            }
        }

        return false;

        //or this:
        /*return password.length() >= 6
                && password.matches(".*\\d.*")
                && password.matches(".*[a-z].*")
                && password.matches(".*[A-Z].*");*/
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a password: ");
        String userInput = scanner.nextLine();
        System.out.println("Is the password complex? "
                + isPasswordComplex(userInput));

        scanner.close();
    }
}
