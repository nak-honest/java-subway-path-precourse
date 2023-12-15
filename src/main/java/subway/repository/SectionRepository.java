package subway.repository;

import subway.domain.Section;
import subway.domain.Station;
import subway.domain.Weight;
import subway.util.TextFileReaderWriter;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class SectionRepository {
    private static final String DELIMITER = ", ";
    private static final int SOURCE_STATION_INDEX = 0;
    private static final int TARGET_STATION_INDEX = 1;
    private static final int DISTANCE_INDEX = 2;
    private static final int DURATION_INDEX = 3;
    private static final String FILE_PATH = "src/main/resources/sections.md";
    private static final File file = new File(FILE_PATH);
    private SectionRepository() {}

    public static List<Section> sections() {
        return TextFileReaderWriter.readTextFile(file).stream()
                .map(SectionRepository::stringToSection)
                .collect(Collectors.toList());
    }

    private static Section stringToSection(String string) {
        List<String> split = Arrays.asList(string.split(DELIMITER));
        Weight weight = new Weight(
                Integer.parseInt(split.get(DISTANCE_INDEX)),
                Integer.parseInt(split.get(DURATION_INDEX)));
        return new Section(Set.of(
                StationRepository.findByName(split.get(SOURCE_STATION_INDEX)),
                StationRepository.findByName(split.get(TARGET_STATION_INDEX))),
                weight);
    }

    public static void addSection(Section section) {
        TextFileReaderWriter.appendTextFile(file, sectionToString(section));
    }

    private static String sectionToString(Section section) {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        section.getStations().stream()
                .map(Station::getName)
                .forEach(stringJoiner::add);

        stringJoiner.add(String.valueOf(section.getWeight().getDistance()));
        stringJoiner.add(String.valueOf(section.getWeight().getDuration()));

        return stringJoiner.toString();
    }

    public static void deleteSection(Section deletedSection) {
        TextFileReaderWriter.removeTextInFile(file, sectionToString(deletedSection));
    }

    public static void deleteAll() {
        TextFileReaderWriter.removeAllTextInFile(file);
    }

    public static Section findByStations(Station sourceStation, Station targetStation) {
        return sections().stream()
                .filter(section -> section.hasStations(sourceStation, targetStation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 구간입니다."));
    }
}
