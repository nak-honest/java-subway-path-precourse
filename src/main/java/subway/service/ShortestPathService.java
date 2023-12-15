package subway.service;

import subway.domain.*;
import subway.repository.SectionRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ShortestPathService {
    private final ShortestPathFinder shortestPathFinder;

    public ShortestPathService(ShortestPathFinder shortestPathFinder) {
        this.shortestPathFinder = shortestPathFinder;
    }

    public Path findShortestPathByDistance(Station sourceStation, Station targetStation) {
        validateSameStation(sourceStation, targetStation);
        List<Station> stations = shortestPathFinder.findShortestPathByDistance(sourceStation, targetStation);
        return new Path(stations, calculateWeight(stations));
    }

    public Path findShortestPathByDuration(Station sourceStation, Station targetStation) {
        validateSameStation(sourceStation, targetStation);
        List<Station> stations = shortestPathFinder.findShortestPathByDuration(sourceStation, targetStation);
        return new Path(stations, calculateWeight(stations));
    }

    private Weight calculateWeight(List<Station> stations) {
        return IntStream.range(0, stations.size() - 1)
                .mapToObj(i -> SectionRepository.findByStations(stations.get(i), stations.get(i + 1)))
                .map(Section::getWeight)
                .reduce(new Weight(0, 0), Weight::add);
    }

    private void validateSameStation(Station sourceStation, Station targetStation) {
        if (sourceStation.equals(targetStation)) {
            throw new IllegalArgumentException("[ERROR] 출발역과 도착역은 동일할 수 없습니다.");
        }
    }
}
