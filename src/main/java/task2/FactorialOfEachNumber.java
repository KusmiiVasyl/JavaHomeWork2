package task2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FactorialOfEachNumber implements Runnable {
    FileRandomNumbers fileRandomNumbers;

    private final String pathFile = "src\\main\\java\\task2\\files\\factorialNumbers.txt";

    public FactorialOfEachNumber(FileRandomNumbers fileRandomNumbers) {
        this.fileRandomNumbers = fileRandomNumbers;
    }

    @Override
    public synchronized void run() {
        fileRandomNumbers.nextThread();
        System.out.println("FactorialOfEachNumber");
        writeFile();
        showResult();
    }

    private void writeFile() {
        String result = getResult();
        try (FileWriter writer = new FileWriter(pathFile, false)) {
            writer.write(result);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void readFile() {
        try (FileReader reader = new FileReader(pathFile)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                System.out.print(scan.nextLine() + "\n");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void showResult() {
        System.out.println("---------------------------------");
        System.out.println("Thread 3:  FactorialOfEachNumber.");
        System.out.println("The file is written.\n" +
                "The data content of the file:");
        readFile();
        System.out.println("---------------------------------");
    }

    private String getResult() {
        int[] arr = fileRandomNumbers.getRandomNumbers();
        String result = "";
        for (int i : arr) {
            result += "Facorial " + i + " = " + factorialOfNumber(i) + "\n";
        }
        return result;
    }

    private double factorialOfNumber(int num) {
        if (num <= 1) return 1;
        return factorialOfNumber(num - 1) * num;
    }
}
