package task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

public class CopyDirectory extends Thread {
    private String existDirectoryLocation;
    private String newDirectoryLocation;

    public CopyDirectory(String existDirectoryLocation, String newDirectoryLocation) {
        this.existDirectoryLocation = existDirectoryLocation;
        this.newDirectoryLocation = newDirectoryLocation;
    }

    @Override
    public void run() {
        try {
            Files.walk(Paths.get(existDirectoryLocation))
                    .forEach(source -> {
                        Path destination = Paths.get(newDirectoryLocation, source.toString()
                                .substring(existDirectoryLocation.length()));
                        try {
                            Files.copy(source, destination);
                            System.out.println("Copy\t" + source.getFileName() + "\n" +
                                    "from\t" + source + "\n" +
                                    "to\t\t" + destination + "\n");
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
