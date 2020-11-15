package org.jobsity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TxtParser {
    private Path path;

    List<String> load(String file) {
        try {
            return Files.readAllLines(Paths.get(file));
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
