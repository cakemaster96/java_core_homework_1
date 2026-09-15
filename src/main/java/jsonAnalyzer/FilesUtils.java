package jsonAnalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FilesUtils {
    public static List<Path> findInputFiles(Path directory)
            throws IOException {
        try (Stream<Path> paths = Files.list(directory)) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName()
                            .toString().endsWith("_data.txt"))
                    .sorted()
                    .toList();
        }
    }
}

