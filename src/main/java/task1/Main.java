package task1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        RandomArray thread1 = new RandomArray(10);
        SumOfArrayNumbers thread2 = new SumOfArrayNumbers(thread1.getArrayNumbers());
        AverageOfArrayNumbers thread3 = new AverageOfArrayNumbers(thread1.getArrayNumbers());

        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println("Thread " + thread1.getName() + " has been interrupted");
        }

        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread " + thread2.getName() + " has been interrupted");
        }

        thread3.start();
        try {
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("Thread " + thread3.getName() + " has been interrupted");
        }

        showResult(thread1.getArrayNumbers(), thread2.getSum(), thread3.getAverage());
    }

    static void showResult(int[] arr, int sum, double avg) {
        System.out.print("Array:\t");
        for (int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println("\b\b\nSum:\t" + sum);
        System.out.println("Avg:\t" + avg);
    }
}