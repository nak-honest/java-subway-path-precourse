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
        );

        return new Menus(menus);
    }

}
