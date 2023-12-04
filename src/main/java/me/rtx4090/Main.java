// yes i love chatGPT haha
package me.rtx4090;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of measurements (N): ");
        int measurementCount = scanner.nextInt();

        ArrayList<Double> measurements = new ArrayList<>();

        // Allowing the user to input multiple data points
        for (int i = 1; i <= measurementCount; i++) {
            System.out.print("Enter the measurement for trial " + i + ": ");
            double measurement = scanner.nextDouble();
            measurements.add(measurement);
        }


        // Calculate the mean (average)
        double mean = calculateMean(measurements);

        // Calculate the standard deviation
        double standardDeviation = calculateStandardDeviation(measurements, mean);

        // Calculate Type A uncertainty
        double typeAUncertainty = standardDeviation / Math.sqrt(measurementCount);

        // Assuming instrument precision is the minimum scale division
        System.out.print("Enter the instrument's minimum scale division: ");
        double minScaleDivision = scanner.nextDouble();

        // Calculate Type B uncertainty
        double typeBUncertainty = minScaleDivision / (2 * Math.sqrt(3));

        // Calculate combined uncertainty (root sum of squares)
        double combinedUncertainty = Math.sqrt(Math.pow(typeAUncertainty, 2) + Math.pow(typeBUncertainty, 2));

        System.out.println("Mean: " + mean);
        System.out.println("Standard Deviation: " + standardDeviation);
        System.out.println("Type A Uncertainty (u_A): " + typeAUncertainty);
        System.out.println("Type B Uncertainty (u_B): " + typeBUncertainty);
        System.out.println("Combined Uncertainty: " + combinedUncertainty);
    }

    // Calculate the mean (average)
    private static double calculateMean(ArrayList<Double> measurements) {
        double sum = 0;
        for (double measurement : measurements) {
            sum += measurement;
        }
        return sum / measurements.size();
    }

    // Calculate the standard deviation
    private static double calculateStandardDeviation(ArrayList<Double> measurements, double mean) {
        double sumSquaredDifferences = 0;
        for (double measurement : measurements) {
            sumSquaredDifferences += Math.pow(measurement - mean, 2);
        }
        return Math.sqrt(sumSquaredDifferences / measurements.size());
    }
}
