package com.linkedin.javacodechallenges.task0304;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.linkedin.javacodechallenges.task0304.App0304.*;

/**
 * @author Aurelijus Jurkus
 * @since 18-Jan-2023
 */
@UtilityClass
public class TicketUtils {

    public static ImmutablePair<String, Integer> askAttendeeNameAndQuantity() {
        System.out.println("What is the full name under the ticket?");
        String name = scanner.nextLine();
        System.out.println("How many is your party?");
        int quantity = Integer.parseInt(scanner.nextLine());
        return ImmutablePair.of(name, quantity);
    }

    public static List<TicketHolder> importTicketHoldersFromCsv(String fileName) {

        if (!fileName.endsWith(".csv")) {
            System.out.print("This is not a CSV file: " + fileName + "\n");
            return Collections.emptyList();
        }

        List<TicketHolder> ticketHolders = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName, StandardCharsets.UTF_8))) {
            csvReader.readNext();//skip the headers

            String[] row;
            while ((row = csvReader.readNext()) != null) {
                String name = row[0];
                String quantity = row[1];
                TicketHolder ticketHolder = new TicketHolder(name, Integer.parseInt(quantity));
                ticketHolders.add(ticketHolder);
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
        return ticketHolders;
    }

    public static boolean processTickets(List<TicketHolder> ticketHolders, String name, Integer currentlyPassingQuantity) {
        Optional<TicketHolder> optionalTicketHolder = findOptionalTicketHolder(ticketHolders, name);
        if (optionalTicketHolder.isEmpty()) {
            System.out.println("Go to hell, your name is not on the list!");
            return false;
        } else {
            TicketHolder existingTicketHolder = optionalTicketHolder.get();
            int initialQuantity = existingTicketHolder.quantity();
            if (initialQuantity < currentlyPassingQuantity) {
                System.out.print("You don't have enough tickets for your whole party. You only have " + initialQuantity + "\n");
                return false;
            } else if (initialQuantity == currentlyPassingQuantity) {
                ticketHolders.remove(existingTicketHolder);
                System.out.println("All good.");
                return true;
            } else {
                System.out.print("I see you still have more coming. I'm keeping your name on the list for them!\n");
                int leftQuantity = initialQuantity - currentlyPassingQuantity;
                updateTicketHolders(ticketHolders, name, leftQuantity);
                return true;
            }
        }
    }

    static Optional<TicketHolder> findOptionalTicketHolder(List<TicketHolder> ticketHolders, String name) {
        return ticketHolders.stream().filter(th -> th.name().equalsIgnoreCase(name)).findFirst();
    }

    private static void updateTicketHolders(List<TicketHolder> ticketHolders, String name, int quantity) {
        Optional<TicketHolder> optionalTicketHolder = findOptionalTicketHolder(ticketHolders, name);
        TicketHolder newTicketHolder = new TicketHolder(name, quantity);
        if (optionalTicketHolder.isPresent()) {
            TicketHolder existingTicketHolder = optionalTicketHolder.get();
            ticketHolders.remove(existingTicketHolder);
        }
        ticketHolders.add(newTicketHolder);
    }

}
