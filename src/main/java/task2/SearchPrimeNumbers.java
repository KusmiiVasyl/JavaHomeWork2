package task2;

import java.io.*;
import java.util.Scanner;

public class SearchPrimeNumbers implements Runnable {
    FileRandomNumbers fileRandomNumbers;

    private final String pathFile = "src\\main\\java\\task2\\files\\simpleNumbers.txt";

    public SearchPrimeNumbers(FileRandomNumbers fileRandomNumbers) {
        this.fileRandomNumbers = fileRandomNumbers;
    }

    @Override
    public synchronized void run() {
        fileRandomNumbers.nextThread();
        writeFile();
        showResult();
    }

    private void writeFile() {
        String result = getSimpleDigit();
        if (result == "") result = "There are no prime numbers.";
        try (FileWriter writer = new FileWriter(pathFile, false)) {
            writer.write(result);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void readFile() {
        try (FileReader reader = new FileReader(pathFile)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNext()) {
                System.out.print(scan.nextLine() + ", ");
            }
            System.out.println("\b\b");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void showResult() {
        System.out.println("---------------------------------");
        System.out.println("Thread 2:  SearchPrimeNumbers.");
        System.out.println("The file is written.\n" +
                "The data content of the file:");
        readFile();
        System.out.println("---------------------------------");
    }

    private String getSimpleDigit() {
        String result = "";
        System.out.println(result);
        int[] arr = fileRandomNumbers.getRandomNumbers();
        for (int i : arr) {
            if (checkSimple(i)) {
                result += i + "\n";
            }
        }
        return result;
    }

    private boolean checkSimple(int num) {
        if (num <= 1)
            return false;
        else if (num <= 3)
            return true;
        else if (num % 2 == 0 || num % 3 == 0)
            return false;
        int n = 5;
        while (n * n <= num) {
            if (num % n == 0 || num % (n + 2) == 0)
                return false;
            n = n + 6;
        }
        return true;
    }
}
