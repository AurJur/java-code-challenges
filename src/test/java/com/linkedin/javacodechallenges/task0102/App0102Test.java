package com.linkedin.javacodechallenges.task0102;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aurelijus Jurkus
 * @since 15-Jan-2023
 */
class App0102Test {

    @Test
    void isPasswordComplex_true() {
        assertTrue(App0102.isPasswordComplex("Happy12"));
        assertTrue(App0102.isPasswordComplex("1SarahL ength"));
        assertTrue(App0102.isPasswordComplex("Password12"));
        assertTrue(App0102.isPasswordComplex("2PassW@ord1"));
        assertTrue(App0102.isPasswordComplex("01Sall#y9"));
    }

    @Test
    void isPasswordComplex_false() {
        assertFalse(App0102.isPasswordComplex("12aB "));
        assertFalse(App0102.isPasswordComplex("Ab112"));
        assertFalse(App0102.isPasswordComplex("happy"));
        assertFalse(App0102.isPasswordComplex("happy_1"));
        assertFalse(App0102.isPasswordComplex("p assword12"));
        assertFalse(App0102.isPasswordComplex("Sur18"));
        assertFalse(App0102.isPasswordComplex(""));
    }
}
