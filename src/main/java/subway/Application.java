package subway;

import subway.controller.PathController;
import subway.domain.*;
import subway.repository.LineRepository;
import subway.repository.SectionRepository;
import subway.repository.StationRepository;
import subway.service.ShortestPathService;
import subway.view.InputView;
import subway.view.OutputView;
import subway.view.Writer;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Application {
    private static final List<String> DEFAULT_STATIONS =
            List.of("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역");
    private static final List<String> DEFAULT_LINES = List.of("2호선", "3호선", "신분당선");
    private static final Map<List<String>, List<Integer>> DEFAULT_SECTION =
            Map.of(List.of("교대역", "강남역"), List.of(2, 3),
                    List.of("강남역", "역삼역"), List.of(2, 3),
                    List.of("교대역", "남부터미널역"), List.of(3, 2),
                    List.of("남부터미널역", "양재역"), List.of(6, 5),
                    List.of("양재역", "매봉역"), List.of(1, 1),
                    List.of("강남역", "양재역"), List.of(2, 8),
                    List.of("양재역", "양재시민의숲역"), List.of(10, 3));

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        Writer writer = new Writer(System.out::print, System.out::println);
        InputView inputView = new InputView(scanner::nextLine, writer);
        OutputView outputView = new OutputView(writer);
        ShortestPathFinder shortestPathFinder = new ShortestPathFinder();
        ShortestPathService shortestPathService = new ShortestPathService(shortestPathFinder);

        PathController pathController = new PathController(inputView, outputView, shortestPathService);
        pathController.run();
    }

    private static void initStationsAndLinesAndSections() {
        DEFAULT_STATIONS.stream()
                .map(Station::new)
                .forEach(StationRepository::addStation);

        DEFAULT_LINES.stream()
                .map(Line::new)
                .forEach(LineRepository::addLine);

        for (Map.Entry<List<String>, List<Integer>> entry : DEFAULT_SECTION.entrySet()) {
            Set<Station> stations = Set.of(StationRepository.findByName(entry.getKey().get(0)),
                    StationRepository.findByName(entry.getKey().get(1)));
            Weight weight = new Weight(entry.getValue().get(0), entry.getValue().get(1));

            SectionRepository.addSection(new Section(stations, weight));
        }
    }
}
