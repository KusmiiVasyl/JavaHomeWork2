package task1;

import java.util.Arrays;


public class AverageOfArrayNumbers extends Thread {
    private double average;
    private int[] arrayNumbers;

    public AverageOfArrayNumbers(int[] array) {
        this.arrayNumbers = array;
    }

    public double getAverage() {
        return average;
    }

    @Override
    public void run() {
        average = Arrays.stream(arrayNumbers).average().orElse(Double.NaN);
    }
}
