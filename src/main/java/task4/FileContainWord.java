package task4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileContainWord extends Thread {
    private String pathDirectory;
    private String searchWord;
    private String pathForFileFindContent;
    private int countWriteFiles = 0;
    private boolean isFinishThread = false;

    public FileContainWord(String pathDirectory, String searchWord) {
        this.pathDirectory = pathDirectory;
        this.searchWord = searchWord;
        setPathForFileFindContent();
    }

    private void setPathForFileFindContent() {
        String dirName = System.getProperty("user.dir");
        dirName += "/src/main/java/task4/files";
        File d = new File(dirName);
        d.mkdirs();
        String path = d + File.separator + "findContent.txt";
        this.pathForFileFindContent = path;
    }

    public String getPathForFileFindContent() {
        return pathForFileFindContent;
    }

    @Override
    public void run() {
        createFileForFindContent();
        searchFile();
        showResult();
        isFinishThread = true;
    }

    private void searchFile() {
        try {
            Stream<Path> listDir = Files.walk(Paths.get(pathDirectory));
            listDir.forEach(source -> {
                if (isFileContainWord(String.valueOf(source))) {
                    writeFindContentToFile(String.valueOf(source));
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private boolean isFileContainWord(String path) {
        File file = new File(path);
        if (file.isFile()) {
            try (FileReader reader = new FileReader(file.getAbsolutePath())) {
                Scanner scan = new Scanner(reader);
                while (scan.hasNext()) {
                    if (scan.next().contains(searchWord)) return true;
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException(ex);
            }
        }
        return false;
    }

    private void writeFindContentToFile(String path) {
        countWriteFiles++;
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(path);
            os = new FileOutputStream(pathForFileFindContent, true);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void createFileForFindContent() {
        File file = new File(pathForFileFindContent);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void nextThread() {
        try {
            if (isFinishThread) {
                notify();
            } else {
                wait();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showResult() {
        System.out.println("---------------------------------");
        System.out.println("Thread 1:  FileContainWord.");
        System.out.println(countWriteFiles + " files containing the word \"" + searchWord + "\" were found.\n" +
                "And written to the file \"" + pathForFileFindContent + "\".");
        System.out.println("---------------------------------");
    }
}
