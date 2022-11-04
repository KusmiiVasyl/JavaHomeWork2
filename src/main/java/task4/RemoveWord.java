package task4;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class RemoveWord implements Runnable {
    private FileContainWord fileContainWord;
    private StringBuffer wordsFindContent;
    private Set<String> wordsBad;

    public RemoveWord(FileContainWord fileContainWord) {
        this.fileContainWord = fileContainWord;
    }

    @Override
    public synchronized void run() {
        fileContainWord.nextThread();
        if (readFileBadWords() && readFileFindContent()) {
            writeResultToFile();
            showResult();
        }
    }

    private void showResult() {
        System.out.println("---------------------------------");
        System.out.println("Thread 2:  RemoveWord.");
        System.out.println("Bad words removed.");
        System.out.println("---------------------------------");
    }

    private boolean readFileBadWords() {
        wordsBad = new HashSet<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the path to the bad words file.");
        String path = in.nextLine(); // E:\JAVA\Projects\JavaHomeWork2\src\main\java\task4\files\badWords.txt
        File dir = new File(path);
        if (dir.exists()) {
            try (FileReader reader = new FileReader(path)) {
                Scanner scan = new Scanner(reader);
                while (scan.hasNext()) {
                    wordsBad.addAll(Arrays.stream(scan.nextLine().split("[\\s\\t\\n.,]+"))
                            .collect(Collectors.toSet()));
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException(ex);
            }
            return true;
        } else {
            System.out.println("The specified path for file with bad words does not exist.");
            return false;
        }
    }

    private boolean readFileFindContent() {
        wordsFindContent = new StringBuffer();
        String path = fileContainWord.getPathForFileFindContent();
        File dir = new File(path);
        if (dir.exists()) {
            try (FileReader reader = new FileReader(path)) {
                Scanner scan = new Scanner(reader);
                while (scan.hasNext()) {
                    wordsFindContent.append(wordsRemove(scan.nextLine()));
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException(ex);
            }
            return true;
        } else {
            System.out.println("The specified file of find content does not exist.");
            return false;
        }
    }

    private void writeResultToFile() {
        String path = fileContainWord.getPathForFileFindContent();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
            bw.write(String.valueOf(wordsFindContent));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String wordsRemove(String line) {
        for (String s : wordsBad) {
            line = line.replace(s, "");
        }
        return line;
    }
}
