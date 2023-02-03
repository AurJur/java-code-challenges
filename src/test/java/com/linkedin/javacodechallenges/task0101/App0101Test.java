package com.linkedin.javacodechallenges.task0101;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aurelijus Jurkus
 * @since 15-Jan-2023
 */
class App0101Test {

    @Test
    void isEven_true() {
        assertTrue(App0101.isEven(0));
        assertTrue(App0101.isEven(2));
        assertTrue(App0101.isEven(4));
        assertTrue(App0101.isEven(6));
        assertTrue(App0101.isEven(8));
        assertTrue(App0101.isEven(-20));
        assertTrue(App0101.isEven(100));
        assertTrue(App0101.isEven(-46));
    }

    @Test
    void isEven_false() {
        assertFalse(App0101.isEven(1));
        assertFalse(App0101.isEven(3));
        assertFalse(App0101.isEven(5));
        assertFalse(App0101.isEven(7));
        assertFalse(App0101.isEven(9));
        assertFalse(App0101.isEven(-11));
        assertFalse(App0101.isEven(93));
        assertFalse(App0101.isEven(-75));
    }
}
