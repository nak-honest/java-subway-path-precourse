package subway.controller;

import subway.domain.menu.MainMenu;
import subway.domain.menu.Menus;
import subway.domain.menu.MenusFactory;
import subway.view.InputView;
import subway.view.OutputView;

public class PathController {
    private final InputView inputView;
    private final OutputView outputView;
    private final MenusFactory menusFactory;

    public PathController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        menusFactory = new MenusFactory(this);
    }

    public void run() {
        Menus mainMenus = menusFactory.createMainMenus();
        MainMenu mainMenu = selectMainMenu();

        while (!mainMenu.equals(MainMenu.QUIT)) {
            mainMenu = selectMainMenu();
        }
    }

    private MainMenu selectMainMenu() {
        outputView.writeMainMenu();
        return MainMenu.of(inputView.readMenu());
    }
}