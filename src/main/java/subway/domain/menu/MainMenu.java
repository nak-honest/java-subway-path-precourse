package subway.domain.menu;

import java.util.Arrays;

public enum MainMenu {
    PATH("1"),
    QUIT("Q");

    private final String menu;

    MainMenu(String menu) {
        this.menu = menu;
    }

    public static MainMenu of(String menu) {
        return Arrays.stream(MainMenu.values())
                .filter(mainMenu -> mainMenu.menu.equals(menu))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 메인 메뉴는 1 또는 Q 이어야 합니다."));
    }
}
