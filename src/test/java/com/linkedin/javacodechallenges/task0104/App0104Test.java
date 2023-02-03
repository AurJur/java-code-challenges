package com.linkedin.javacodechallenges.task0104;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aurelijus Jurkus
 * @since 15-Jan-2023
 */
class App0104Test {

    @Test
    void calculateWaterBill() {
        assertEquals(22.74, App0104.calculateWaterBill(1800), 0);
        assertEquals(22.74, App0104.calculateWaterBill(2244), 0);
        assertEquals(26.64, App0104.calculateWaterBill(2245), 0);
        assertEquals(26.64, App0104.calculateWaterBill(2992), 0);
        assertEquals(46.14, App0104.calculateWaterBill(6000), 0);
        assertEquals(53.94, App0104.calculateWaterBill(8000), 0);

        assertEquals(18.84, App0104.calculateWaterBill(0), 0);
        assertEquals(18.84, App0104.calculateWaterBill(1496), 0);
        assertEquals(22.74, App0104.calculateWaterBill(1497), 0);
        assertEquals(18.84, App0104.calculateWaterBill(-20), 0);
    }
}
