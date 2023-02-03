package com.linkedin.javacodechallenges.task0201;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aurelijus Jurkus
 * @since 15-Jan-2023
 */
class App0201Test {

    @Test
    void calculateHundredDaysFromNow() {
        LocalDate d1 = LocalDate.of(2020, 1, 1);
        assertEquals(LocalDate.of(2020, 4, 10), App0201.calculate100DaysFrom(d1));

        LocalDate d2 = LocalDate.of(2020, 11, 6);
        assertEquals(LocalDate.of(2021, 2, 14), App0201.calculate100DaysFrom(d2));

        LocalDate d3 = LocalDate.of(2022, 11, 8);
        assertEquals(LocalDate.of(2023, 2, 16), App0201.calculate100DaysFrom(d3));
    }
}
