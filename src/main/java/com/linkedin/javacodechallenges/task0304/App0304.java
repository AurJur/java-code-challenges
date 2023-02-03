package com.linkedin.javacodechallenges.task0304;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;
import java.util.Scanner;

/**
 * @author Aurelijus Jurkus
 * @since 18-Jan-2023
 */
public class App0304 {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        List<TicketHolder> ticketHolders = TicketUtils.importTicketHoldersFromCsv("ticketholders.csv");
        while (shallContinue()) {
            ImmutablePair<String, Integer> attendeeNameAndQuantity = TicketUtils.askAttendeeNameAndQuantity();
            TicketUtils.processTickets(ticketHolders, attendeeNameAndQuantity.left, attendeeNameAndQuantity.right);
        }
        scanner.close();
    }

    private static boolean shallContinue() {
        System.out.println("Shall we continue (Y/N)?");
        String userInput = scanner.nextLine();
        if (userInput.equalsIgnoreCase("Y")) {
            return true;
        } else if (userInput.equalsIgnoreCase("N")) {
            return false;
        } else {
            System.out.println("You entered '%s'. I do not understand.".formatted(userInput));
            return shallContinue();
        }
    }

}
