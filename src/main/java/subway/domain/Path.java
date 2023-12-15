package subway.domain;

import java.util.List;

public class Path {
    private final List<Station> stations;
    private final Weight weight;

    public Path(List<Station> stations, Weight weight) {
        this.stations = stations;
        this.weight = weight;
    }

    public List<Station> getStations() {
        return stations;
    }

    public int getDistance() {
        return weight.getDistance();
    }

    public int getDuration() {
        return weight.getDuration();
    }
}
