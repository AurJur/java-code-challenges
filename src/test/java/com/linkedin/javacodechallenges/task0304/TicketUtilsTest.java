package com.linkedin.javacodechallenges.task0304;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aurelijus Jurkus
 * @since 18-Jan-2023
 */
class TicketUtilsTest {

    private final ByteArrayOutputStream printOut = new ByteArrayOutputStream();
    private List<TicketHolder> ticketHolderList;

    @BeforeEach
    public void setUpEach() {
        System.setOut(new PrintStream(printOut));
        ticketHolderList = new ArrayList<>();
    }

    @AfterEach
    public void cleanUpEach() {
        System.setOut(System.out);
    }

    @Test
    void importTicketHoldersFromCSV() {

        TicketHolder ticketHolderOne = new TicketHolder("Jessica Jones", 2);
        TicketHolder ticketHolderTwo = new TicketHolder("Rebecca Johnson", 3);
        TicketHolder ticketHolderThree = new TicketHolder("Sarah Roberts", 1);

        List<TicketHolder> result = TicketUtils.importTicketHoldersFromCsv("ticketholders.csv");

        assertEquals(3, result.size());
        assertEquals(ticketHolderOne.name(), result.get(0).name());
        assertEquals(ticketHolderOne.name(), result.get(0).name());
        assertEquals(ticketHolderTwo.name(), result.get(1).name());

        assertEquals(ticketHolderTwo.quantity(), result.get(1).quantity());
        assertEquals(ticketHolderThree.name(), result.get(2).name());
        assertEquals(ticketHolderThree.quantity(), result.get(2).quantity());
    }

    @Test
    void importTicketHoldersFromCSV_wrongFileType() {

        List<TicketHolder> result = TicketUtils.importTicketHoldersFromCsv("sample.jpg");
        assertEquals("This is not a CSV file: sample.jpg\n", printOut.toString());
        assertTrue(result.isEmpty());
    }

    @Test
    void findTicketHolder() {

        ticketHolderList.add(new TicketHolder("Sally Roberts", 1));

        Optional<TicketHolder> result = TicketUtils.findOptionalTicketHolder(ticketHolderList, "Sally Roberts");

        assertTrue(result.isPresent());
        assertEquals("Sally Roberts", result.get().name());
        assertEquals(1, result.get().quantity());
    }

    @Test
    void findTicketHolder_DifferentCasingInInput() {

        ticketHolderList.add(new TicketHolder("sally roberts", 1));

        Optional<TicketHolder> result = TicketUtils.findOptionalTicketHolder(ticketHolderList, "Sally Roberts");

        assertTrue(result.isPresent());
        assertEquals("sally roberts", result.get().name());
        assertEquals(1, result.get().quantity());
    }

    @Test
    void findTicketHolder_DifferentCasingInCSV() {

        ticketHolderList.add(new TicketHolder("Sally Roberts", 1));
        Optional<TicketHolder> result = TicketUtils.findOptionalTicketHolder(ticketHolderList, "sally roberts");

        assertTrue(result.isPresent());
        assertEquals("Sally Roberts", result.get().name());
        assertEquals(1, result.get().quantity());
    }

    @Test
    void findTicketHolder_sameFirstName() {

        ticketHolderList.add(new TicketHolder("Sally Roberts", 1));
        ticketHolderList.add(new TicketHolder("Sally Johnson", 5));

        Optional<TicketHolder> result = TicketUtils.findOptionalTicketHolder(ticketHolderList, "Sally Johnson");

        assertTrue(result.isPresent());
        assertEquals("Sally Johnson", result.get().name());
        assertEquals(5, result.get().quantity());
    }

    @Test
    void findTicketHolder_notInList() {

        ticketHolderList.add(new TicketHolder("Sally Roberts", 1));

        Optional<TicketHolder> result = TicketUtils.findOptionalTicketHolder(ticketHolderList, "Sally Johnson");

        assertFalse(result.isPresent());
    }

    @Test
    void findTicketHolder_emptyList() {

        Optional<TicketHolder> result = TicketUtils.findOptionalTicketHolder(ticketHolderList, "Sally Johnson");

        assertFalse(result.isPresent());
    }

    @Test
    void findTicketHolder_emptyInput() {

        ticketHolderList.add(new TicketHolder("Sally Roberts", 1));

        Optional<TicketHolder> result = TicketUtils.findOptionalTicketHolder(ticketHolderList, "");

        assertFalse(result.isPresent());
    }

    @Test
    void processTickets_noneLeftover() {

        TicketHolder ticketHolder = new TicketHolder("Sally Johnson", 5);
        ticketHolderList.add(ticketHolder);

        boolean result = TicketUtils.processTickets(ticketHolderList, ticketHolder.name(), 5);

        assertTrue(ticketHolderList.isEmpty());
        assertTrue(result);
    }

    @Test
    void processTickets_someLeftOver() {

        TicketHolder ticketHolder = new TicketHolder("Sally Johnson", 5);
        ticketHolderList.add(ticketHolder);

        boolean result = TicketUtils.processTickets(ticketHolderList, ticketHolder.name(), 4);

        assertEquals("I see you still have more coming. I'm keeping your name on the list for them!\n", printOut.toString());

        TicketHolder ticketHolderUpdated = TicketUtils.findOptionalTicketHolder(ticketHolderList, ticketHolder.name()).get();

        assertEquals(1, ticketHolderUpdated.quantity());
        assertFalse(ticketHolderList.contains(ticketHolder));
        assertTrue(ticketHolderList.contains(ticketHolderUpdated));
        assertTrue(result);
    }

    @Test
    void processTickets_notEnough() {

        TicketHolder ticketHolder = new TicketHolder("Sally Roberts", 2);
        ticketHolderList.add(ticketHolder);

        boolean result = TicketUtils.processTickets(ticketHolderList, ticketHolder.name(), 4);

        assertEquals("You don't have enough tickets for your whole party. You only have 2\n", printOut.toString());
        assertFalse(result);
    }
}
