package com.linkedin.javacodechallenges.task0302;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aurelijus Jurkus
 * @since 17-Jan-2023
 */
class App0302Test {


    private static final String JOKE = "Knock knock!";

    private final ByteArrayOutputStream printOut = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpEach() {
        System.setOut(new PrintStream(printOut));
    }

    @AfterEach
    public void cleanUpEach() {
        System.setOut(System.out);
    }

    @Test
    void parseJoke() {
        Optional<String> jokeOpt = App0302.parseJoke("{\"id\":\"abc\",\"status\":200,\"joke\":\"" + JOKE + "\"}");
        assertTrue(jokeOpt.isPresent());
        assertEquals(JOKE, jokeOpt.get());
    }

    @Test
    void parseJoke_parseError() {

        Optional<String> jokeOpt = App0302.parseJoke("today");
        assertFalse(jokeOpt.isPresent());
        assertEquals("Must be out of jokes for now.\n", printOut.toString());
    }

    @Test
    void parseJoke_noJokes() {

        Optional<String> jokeOpt = App0302.parseJoke("[]");
        assertFalse(jokeOpt.isPresent());
    }
}
