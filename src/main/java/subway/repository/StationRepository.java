package subway.repository;

import subway.domain.Station;
import subway.util.TextFileReaderWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StationRepository {
    private static final String FILE_PATH = "src/main/resources/stations.md";
    private static final File file = new File(FILE_PATH);

    private StationRepository() {}

    public static List<Station> stations() {
        return TextFileReaderWriter.readTextFile(file).stream()
                .map(Station::new)
                .collect(Collectors.toList());
    }

    public static void addStation(Station station) {
        TextFileReaderWriter.appendTextFile(file, station.getName());
    }

    public static void deleteStation(String name) {
        TextFileReaderWriter.removeTextInFile(file, name);
    }

    public static void deleteAll() {
        TextFileReaderWriter.removeAllTextInFile(file);
    }

    public static Station findByName(String name) {
        return stations().stream()
                .filter(station -> station.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 역입니다."));
    }
}
