package subway.domain.menu;

import java.util.Arrays;

public enum PathMenu implements MenuComponent {
    LOOKUP_BY_DISTANCE("1"),
    LOOKUP_BY_TIME("2"),
    BACK("B");

    private final String menu;

    PathMenu(String menu) {
        this.menu = menu;
    }

    public static PathMenu of(String menu) {
        return Arrays.stream(PathMenu.values())
                .filter(pathMenu -> pathMenu.menu.equals(menu))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 메인 메뉴는 1, 2, B 중 하나여야 합니다."));
    }
}
