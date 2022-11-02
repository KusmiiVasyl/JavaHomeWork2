package task2;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class FileRandomNumbers implements Runnable {
    private String path;
    private int size;
    private boolean isFileCreate = false;
    private int[] randomNumbers;

    public FileRandomNumbers(String path, int size) {
        this.path = path;
        this.size = size;
        this.randomNumbers = new int[size];
    }

    public int[] getRandomNumbers() {
        return randomNumbers;
    }

    public String getPath() {
        return path;
    }

    @Override
    public synchronized void run() {
        writeFile();
        showResult();
    }

    private void writeFile() {
        try (FileWriter writer = new FileWriter(path, false)) {
            for (int i = 0; i < size; i++) {
                randomNumbers[i] = (int) (Math.random() * 100);
                writer.write(String.valueOf(randomNumbers[i]));
                writer.append('\n');
            }
            isFileCreate = true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void readFile() {
        try (FileReader reader = new FileReader(path)) {
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
        System.out.println("Thread 1:  FileRandomNumbers.");
        System.out.println("The file is written.\n" +
                "The data content of the file:");
        readFile();
        System.out.println("---------------------------------");
    }

    public synchronized void nextThread() {
        try {
            if (isFileCreate) {
                notify();
            } else {
                wait();
            }
        } catch (InterruptedException e) {
            System.out.println("File not create!");
        }
    }
}
