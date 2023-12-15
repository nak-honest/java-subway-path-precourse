package subway.domain;

import java.util.Objects;

public final class Weight {
    private final int distance;
    private final int duration;

    public Weight(int distance, int duration) {
        validate(distance, duration);
        this.distance = distance;
        this.duration = duration;
    }

    private void validate(int distance, int duration) {
        if (distance < 0) {
            throw new IllegalArgumentException("[ERROR] 거리는 0 이상이어야 합니다.");
        }
        if (duration < 0) {
            throw new IllegalArgumentException("[ERROR] 소요 시간은 0 이상이어야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Weight)) {
            return false;
        }

        Weight weight = (Weight) o;
        return weight.distance == distance && weight.duration == duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, duration);
    }

    public int getDistance() {
        return distance;
    }

    public int getDuration() {
        return duration;
    }
}
