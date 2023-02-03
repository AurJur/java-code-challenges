package com.linkedin.javacodechallenges.task0103;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aurelijus Jurkus
 * @since 15-Jan-2023
 */
class App0103Test {

    private final ByteArrayOutputStream gamePrintOut = new ByteArrayOutputStream();
    private final static String expectedQuestionIfPlay = "Do you want to play? (Y/N):\r";

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(gamePrintOut));
    }

    @ParameterizedTest
    @ValueSource(strings = {"y", "Y"})
    void askUserToContinue_Yes(String positiveUserInput) {
        System.setIn(new ByteArrayInputStream(positiveUserInput.getBytes()));
        boolean expectedTrue = new DoubleOrNothing().askUserToContinue();
        assertTrue(expectedTrue);
        String expectedFinalQuestionIfPlay = expectedQuestionIfPlay + "\n";
        String actualQuestionIfPlay = gamePrintOut.toString();
        assertEquals(expectedFinalQuestionIfPlay, actualQuestionIfPlay);
    }

    @ParameterizedTest
    @ValueSource(strings = {"n", "N"})
    void askUserToContinue_No(String negativeUserInput) {
        System.setIn(new ByteArrayInputStream(negativeUserInput.getBytes()));
        boolean expectedFalse = new DoubleOrNothing().askUserToContinue();
        assertFalse(expectedFalse);
        String expectedFinalQuestionIfPlay = expectedQuestionIfPlay + "\n";
        String actualQuestionIfPlay = gamePrintOut.toString();
        assertEquals(expectedFinalQuestionIfPlay, actualQuestionIfPlay);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a\ny", "A\ny", "yes\ny", " \ny"})
    void askUserToContinue_AskAgain_ThenYes(String unclearUserInput) {
        System.setIn(new ByteArrayInputStream(unclearUserInput.getBytes()));
        boolean expectedTrue = new DoubleOrNothing().askUserToContinue();
        assertTrue(expectedTrue);
        String firstUserInput = unclearUserInput.split("\n")[0];
        String expectedFinalQuestionIfPlay = """
                %1$s
                You entered '%2$s'. I do not understand.\r
                %1$s
                """.formatted(expectedQuestionIfPlay, firstUserInput);
        String actualQuestionIfPlay = gamePrintOut.toString();
        assertEquals(expectedFinalQuestionIfPlay, actualQuestionIfPlay);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a\nn", "A\nn", "yes\nn", " \nn"})
    void askUserToContinue_AskAgain_ThenNo(String unclearUserInput) {
        System.setIn(new ByteArrayInputStream(unclearUserInput.getBytes()));
        boolean expectedFalse = new DoubleOrNothing().askUserToContinue();
        assertFalse(expectedFalse);
        String firstUserInput = unclearUserInput.split("\n")[0];
        String expectedFinalQuestionIfPlay = """
                %1$s
                You entered '%2$s'. I do not understand.\r
                %1$s
                """.formatted(expectedQuestionIfPlay, firstUserInput);
        String actualQuestionIfPlay = gamePrintOut.toString();
        assertEquals(expectedFinalQuestionIfPlay, actualQuestionIfPlay);
    }

}
