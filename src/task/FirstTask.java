package task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class FirstTask {
    private final Path directory;

    public FirstTask(final Path directory) throws NotDirectoryException {
        if (!Files.isDirectory(directory)) {
            throw new NotDirectoryException("Specified path is not a directory");
        }

        this.directory = directory;
    }

    public List<Path> listOnlySpecificFiles() throws IOException {
        return Files.walk(directory)
                .filter(this::isNeedPath)
                .collect(Collectors.toList());
    }

    private boolean isNeedPath(final Path path) {
        final var stringPath = path.toString();
        final var needExtensions = Arrays.asList(".java", ".class", ".txt");

        return Files.isRegularFile(path) && needExtensions.stream().anyMatch(stringPath::endsWith);
    }
}
