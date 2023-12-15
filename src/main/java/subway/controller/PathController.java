package subway.controller;

import subway.domain.Path;
import subway.domain.Station;
import subway.domain.menu.MainMenu;
import subway.domain.menu.Menus;
import subway.domain.menu.MenusFactory;
import subway.domain.menu.PathMenu;
import subway.repository.StationRepository;
import subway.service.ShortestPathService;
import subway.view.InputView;
import subway.view.OutputView;

public class PathController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ShortestPathService shortestPathService;
    private final MenusFactory menusFactory;

    public PathController(InputView inputView, OutputView outputView, ShortestPathService shortestPathService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.shortestPathService = shortestPathService;
        menusFactory = new MenusFactory(this);
    }

    public void run() {
        Menus mainMenus = menusFactory.createMainMenus();
        MainMenu mainMenu = selectMainMenu();

        while (!mainMenu.equals(MainMenu.QUIT)) {
            mainMenus.run(mainMenu);
            mainMenu = selectMainMenu();
        }
    }

    private MainMenu selectMainMenu() {
        outputView.writeMainMenu();
        return MainMenu.of(inputView.readMenu());
    }

    public void runPathMenu() {
        Menus pathMenus = menusFactory.createPathMenus();
        outputView.writePathMenu();
        PathMenu pathMenu = PathMenu.of(inputView.readMenu());

        pathMenus.run(pathMenu);
    }

    public void runLookupPathByDistance() {
        Station startStation = new Station(inputView.readStartStation());
        Station endStation = new Station(inputView.readEndStation());

        Path path = shortestPathService.findShortestPathByDistance(startStation, endStation);
    }
}
