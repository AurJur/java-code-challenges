package com.linkedin.javacodechallenges.task0201;

import java.time.LocalDate;
import java.time.ZoneId;

/**
 * @author Aurelijus Jurkus
 * @since 15-Jan-2023
 */
public class App0201 {

    private static final ZoneId ZONE_ID = ZoneId.of("Europe/Vilnius");

    public static void main(String[] args) {
        LocalDate today = LocalDate.now(ZONE_ID);
        System.out.println("100 days from now is " + calculate100DaysFrom(today));
    }

    static LocalDate calculate100DaysFrom(LocalDate date) {
        return date.plusDays(100);
    }
}
