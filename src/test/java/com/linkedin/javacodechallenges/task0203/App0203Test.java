package com.linkedin.javacodechallenges.task0203;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aurelijus Jurkus
 * @since 15-Jan-2023
 */
class App0203Test {

    @Test
    void scrabbleWordCalculator() {
        assertEquals(10, App0203.wordScoreCalculator("very"));
        assertEquals(12, App0203.wordScoreCalculator("zoo"));
        assertEquals(28, App0203.wordScoreCalculator("maximize"));
        assertEquals(17, App0203.wordScoreCalculator("exercise"));
        assertEquals(27, App0203.wordScoreCalculator("jukebox"));
        assertEquals(26, App0203.wordScoreCalculator("flapjack"));
        assertEquals(25, App0203.wordScoreCalculator("squeeze"));
        assertEquals(11, App0203.wordScoreCalculator("school"));
        assertEquals(9, App0203.wordScoreCalculator("ex"));
        assertEquals(12, App0203.wordScoreCalculator("common"));
    }

    @Test
    void scrabbleWordCalculator_edgeCases() {
        assertEquals(0, App0203.wordScoreCalculator(""));
        assertEquals(0, App0203.wordScoreCalculator("          "));
        assertEquals(0, App0203.wordScoreCalculator("     12-183     "));

        assertEquals(10, App0203.wordScoreCalculator("v2ery"));
        assertEquals(10, App0203.wordScoreCalculator("Very"));
        assertEquals(10, App0203.wordScoreCalculator("VeRy"));
        assertEquals(10, App0203.wordScoreCalculator("VERY"));
        assertEquals(10, App0203.wordScoreCalculator("ver!y"));
        assertEquals(10, App0203.wordScoreCalculator("ver!y"));
        assertEquals(10, App0203.wordScoreCalculator("  ve ry"));
        assertEquals(10, App0203.wordScoreCalculator("  ve ry"));
        assertEquals(7, App0203.wordScoreCalculator("dadd"));
    }
}
