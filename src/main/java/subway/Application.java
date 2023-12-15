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
}
