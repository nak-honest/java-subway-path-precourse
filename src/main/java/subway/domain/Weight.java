package subway.domain;

public class Weight {
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

    public int getDistance() {
        return distance;
    }

    public int getDuration() {
        return duration;
    }
}
