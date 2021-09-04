package task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public final class SecondTask {
    private final Path textPath;

    public SecondTask(final Path textPath) {
        if (!Files.exists(textPath) || Files.isDirectory(textPath)) {
            throw new IllegalArgumentException("Provided textpath is not a file or doesn't exit");
        }

        this.textPath = textPath;
    }

    public HashMap<String, Integer> getWordsFrequency() throws IOException {
        final var freqMap = new HashMap<String, Integer>();

        final String text;
        try (final var reader = Files.newBufferedReader(textPath)) {
             text = reader.lines().collect(Collectors.joining());
        }

        final var words = List.of(text.split("\\s+"));
        final var uniqueWords = new HashSet<>(words);

        for (var word : uniqueWords) {
            freqMap.put(word, Collections.frequency(words, word));
        }

        return freqMap;
    }
}
