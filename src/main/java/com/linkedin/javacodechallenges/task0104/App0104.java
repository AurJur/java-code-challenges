package com.linkedin.javacodechallenges.task0104;

import java.util.Scanner;

/**
 * @author Aurelijus Jurkus
 * @since 15-Jan-2023
 */
public class App0104 {

    private static final double MINIMUM_CCF_USAGE = 2;
    private static final double MINIMUM_CHARGE = 18.84;
    private static final double PRICE_FOR_CCF = 3.9;
    private static final int GALLONS_PER_CCF = 748;

    public static double calculateWaterBill(double gallonsUsage) {
        int ccfsUsage = gallonsToCcfs(gallonsUsage);

        if (ccfsUsage <= MINIMUM_CCF_USAGE) {
            return MINIMUM_CHARGE;
        } else {
            return MINIMUM_CHARGE + (ccfsUsage - MINIMUM_CCF_USAGE) * PRICE_FOR_CCF;
        }
    }

    private static int gallonsToCcfs(double gallons) {
        return (int) Math.ceil(gallons / GALLONS_PER_CCF);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many gallons of water did you " +
                "use this month?");
        double usage = scanner.nextDouble();
        System.out.println("Your water bill is " +
                calculateWaterBill(usage));
        scanner.close();
    }
}
