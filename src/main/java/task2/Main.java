package task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the file path:");
        String path = in.nextLine(); // You can enter the following path:
                                     // "src\main\java\task2\files\numbers.txt"

        FileRandomNumbers fileRandomNumbers = new FileRandomNumbers(path, 10);
        new Thread(fileRandomNumbers).start();
        new Thread(new FactorialOfEachNumber(fileRandomNumbers)).start();
        new Thread(new SearchPrimeNumbers(fileRandomNumbers)).start();
    }
}
