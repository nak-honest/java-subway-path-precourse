package subway.repository;

import subway.domain.Line;
import subway.util.TextFileReaderWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LineRepository {
    private static final String FILE_PATH = "src/main/resources/lines.md";
    private static final File file = new File(FILE_PATH);

    private LineRepository() {}

    public static List<Line> lines() {
        return TextFileReaderWriter.readTextFile(file).stream()
                .map(Line::new)
                .collect(Collectors.toList());
    }

    public static void addLine(Line line) {
        TextFileReaderWriter.appendTextFile(file, line.getName());
    }

    public static void deleteLineByName(String name) {
        TextFileReaderWriter.removeTextInFile(file, name);
    }

    public static void deleteAll() {
        TextFileReaderWriter.removeAllTextInFile(file);
    }

    public static Line findByName(String name) {
        return lines().stream()
                .filter(line -> line.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 노선입니다."));
    }
}
