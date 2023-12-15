package subway.domain;

import java.util.Objects;

public class Station {
    private String name;

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Station)) {
            return false;
        }

        Station station = (Station) o;
        return station.name.equals(name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
