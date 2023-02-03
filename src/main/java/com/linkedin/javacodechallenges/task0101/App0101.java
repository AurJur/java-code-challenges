package com.linkedin.javacodechallenges.task0101;

import java.util.Scanner;

/**
 * @author Aurelijus Jurkus
 * @since 15-Jan-2023
 */
public class App0101 {

    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a number: ");
        int userNum = scanner.nextInt();
        System.out.println("Is the number even? " + isEven(userNum));

        scanner.close();
    }
}
