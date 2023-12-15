package subway.domain.menu;

import java.util.HashMap;
import java.util.Map;

public class Menus {
    private final Map<MenuComponent, Runnable> menus;

    public Menus(Map<MenuComponent, Runnable> menus) {
        this.menus = new HashMap<>(menus);
    }

    public void run(MenuComponent menuComponent) {
        menus.get(menuComponent).run();
    }
}
