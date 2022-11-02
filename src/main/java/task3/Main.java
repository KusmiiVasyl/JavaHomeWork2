package task3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //For example: from "D:/node_modules"  to "F:/node_modules";
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the path to an existing directory");
        String path1 = in.nextLine();
        System.out.println("Enter the path to a new directory");
        String path2 = in.nextLine();

        Thread thread = new Thread(new CopyDirectory(path1, path2));
        thread.start();
    }
}
