package subway.repository;

import subway.domain.Section;
import subway.domain.Station;

import java.util.*;

public class SectionRepository {
    private SectionRepository() {}

    private static final List<Section> sections = new ArrayList<>();

    public static List<Section> sections() {
        return Collections.unmodifiableList(sections);
    }

    public static void addSection(Section section) {
        sections.add(section);
    }

    public static boolean deleteSection(Section deletedSection) {
        return sections.removeIf(section -> Objects.equals(section, deletedSection));
    }

    public static void deleteAll() {
        sections.clear();
    }

    public static Section findByStations(Station sourceStation, Station targetStation) {
        return sections.stream()
                .filter(section -> section.hasStations(sourceStation, targetStation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 구간입니다."));
    }
}
