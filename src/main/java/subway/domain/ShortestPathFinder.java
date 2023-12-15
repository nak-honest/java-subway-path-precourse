package subway.domain;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.repository.SectionRepository;
import subway.repository.StationRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ShortestPathFinder {
    private final WeightedMultigraph<Station, DefaultWeightedEdge> graphByDistance =
            new WeightedMultigraph(DefaultWeightedEdge.class);
    private final WeightedMultigraph<Station, DefaultWeightedEdge> graphByDuration =
            new WeightedMultigraph(DefaultWeightedEdge.class);
    private final DijkstraShortestPath dijkstraShortestPathByDistance = new DijkstraShortestPath(graphByDistance);
    private final DijkstraShortestPath dijkstraShortestPathByDuration = new DijkstraShortestPath(graphByDuration);

    public ShortestPathFinder() {
        initGraph();
    }

    private void initGraph() {
        StationRepository.stations()
                .forEach(station -> {
                    graphByDistance.addVertex(station);
                    graphByDuration.addVertex(station);
                });

        SectionRepository.sections()
                .forEach(section -> {
                    addEdges(graphByDistance, section.getStations(), section.getDistance());
                    addEdges(graphByDuration, section.getStations(), section.getDuration());
                });
    }

    private void addEdges(WeightedMultigraph<Station, DefaultWeightedEdge> graph, Set<Station> stations, int weight) {
        Iterator<Station> iterator = stations.iterator();
        Station firstStation = iterator.next();
        Station secondStation = iterator.next();

        graph.setEdgeWeight(graph.addEdge(firstStation, secondStation), weight);
    }

    public List<Station> findShortestPathByDistance(Station sourceStation, Station targetStation) {
        GraphPath<Station, DefaultWeightedEdge> path =
                dijkstraShortestPathByDistance.getPath(sourceStation, targetStation);
        validateNoPath(path);

        return dijkstraShortestPathByDistance.getPath(sourceStation, targetStation).getVertexList();
    }

    public List<Station> findShortestPathByDuration(Station sourceStation, Station targetStation) {
        GraphPath<Station, DefaultWeightedEdge> path =
                dijkstraShortestPathByDuration.getPath(sourceStation, targetStation);
        validateNoPath(path);

        return path.getVertexList();
    }

    private void validateNoPath(GraphPath<Station, DefaultWeightedEdge> path) {
        if (path == null) {
            throw new IllegalArgumentException("[ERROR] 경로가 존재하지 않습니다.");
        }
    }
}
