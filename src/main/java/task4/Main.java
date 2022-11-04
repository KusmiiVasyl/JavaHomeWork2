package task4;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the path to the directory.");
        String path = in.nextLine();
        if (checkingExistingDirectory(path)) {
            System.out.println("To search enter the word.");
            String word = in.nextLine();
            FileContainWord thread1 = new FileContainWord(path, word);
            thread1.start();
            new Thread(new RemoveWord(thread1)).start();
        }
    }

    static boolean checkingExistingDirectory(String path) {
        File dir = new File(path);
        if (dir.exists()) return true;
        else {
            System.out.println("The specified path does not exist.");
            return false;
        }
    }
}
