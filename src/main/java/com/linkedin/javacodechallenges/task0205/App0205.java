package com.linkedin.javacodechallenges.task0205;

import java.util.List;

/**
 * @author Aurelijus Jurkus
 * @since 16-Jan-2023
 */
public class App0205 {

    public static double calculateAverageChangeInvested(List<Double> purchases) {

        return purchases.stream()
                .mapToDouble(purchase -> Math.ceil(purchase) - purchase)
                .average()
                .orElse(0);
    }

    public static void main(String[] args) {
        List<Double> purchases = List.of(12.38, 38.29, 5.27, 3.21);
        System.out.println(calculateAverageChangeInvested(purchases));
    }
}
