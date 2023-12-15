package subway.service;

import subway.domain.Section;
import subway.domain.ShortestPathFinder;
import subway.domain.Station;

import java.util.Collections;
import java.util.List;

public class ShortestPathService {
    private final ShortestPathFinder shortestPathFinder;

    public ShortestPathService(ShortestPathFinder shortestPathFinder) {
        this.shortestPathFinder = shortestPathFinder;
    }

    public List<Section> findShortestPathByDistance(Station sourceStation, Station targetStation) {
        validateSameStation(sourceStation, targetStation);
        return Collections.emptyList();
    }

    private void validateSameStation(Station sourceStation, Station targetStation) {
        if (sourceStation.equals(targetStation)) {
            throw new IllegalArgumentException("[ERROR] 출발역과 도착역은 동일할 수 없습니다.");
        }
    }
}
