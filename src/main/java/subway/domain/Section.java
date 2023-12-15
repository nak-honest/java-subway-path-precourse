package subway.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Section {
    private static final int SECTION_SIZE = 2;

    private final Set<Station> stations;
    private final Weight weight;

    public Section(Set<Station> stations, Weight weight) {
        this.stations = new HashSet<>(stations);
        this.weight = weight;
        validateSectionSize();
    }

    private void validateSectionSize() {
        if (stations.size() != SECTION_SIZE) {
            throw new IllegalArgumentException(String.format("[ERROR] 구간은 %d개의 역으로 구성되어야 합니다.", SECTION_SIZE));
        }
    }

    public boolean hasStations(Station sourceSection, Station targetStation) {
        validateSameSection(sourceSection, targetStation);
        return stations.contains(sourceSection) && stations.contains(targetStation);
    }

    private void validateSameSection(Station sourceSection, Station targetStation) {
        if (sourceSection.equals(targetStation)) {
            throw new IllegalArgumentException("[ERROR] 구간은 서로 다른 두개의 역으로 이루어져야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Section)) {
            return false;
        }

        Section section = (Section) o;
        return section.stations.equals(stations) && section.weight.equals(weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stations, weight);
    }

    public int getDistance() {
        return weight.getDistance();
    }

    public int getDuration() {
        return weight.getDuration();
    }

    public Set<Station> getStations() {
        return stations;
    }

    public Weight getWeight() {
        return weight;
    }
}
