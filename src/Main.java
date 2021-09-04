import task.*;

import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String... args) throws IOException {
        System.out.println("Doing first task demo...");
        doFirstTaskDemo();

        System.out.println("Doing second task demo...");
        doSecondTaskDemo();
    }

    private static void doFirstTaskDemo() throws IOException  {
        final var scanner = new Scanner(System.in);
        System.out.print("Enter a path to the directory: ");

        final var path = Paths.get(scanner.nextLine());

        try {
            final var firstTask = new FirstTask(path);
            final var files = firstTask.listOnlySpecificFiles();
            System.out.println(files);
        } catch (NotDirectoryException e) {
            System.err.println("You entered path to the file, but expected directory");
        }
    }

    private static void doSecondTaskDemo() throws IOException {
        final var filePath = Paths.get("text.txt");

        try {
            final var secondTask = new SecondTask(filePath);
            final var frequencies = secondTask.getWordsFrequency();

            System.out.println("Word frequency stats:\n");
            for (final var entry : frequencies.entrySet()) {
                System.out.printf("%s => %d\n", entry.getKey(), entry.getValue());
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
