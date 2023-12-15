package subway.domain.menu;

import subway.controller.PathController;

import java.util.Map;

public class MenusFactory {
    private final PathController subwayController;

    public MenusFactory(PathController subwayController) {
        this.subwayController = subwayController;
    }

    public Menus createMainMenus() {
        Map<MenuComponent, Runnable> menus = Map.of(
                MainMenu.PATH, subwayController::runPathMenu
        );

        return new Menus(menus);
    }

    public Menus createPathMenus() {
        Map<MenuComponent, Runnable> menus = Map.of(
                PathMenu.LOOKUP_BY_DISTANCE, subwayController::runLookupPathByDistance,
                PathMenu.BACK, () -> {}
        );

        return new Menus(menus);
    }
}
