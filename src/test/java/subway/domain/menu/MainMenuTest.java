package subway.domain.menu;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import subway.domain.menu.MainMenu;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MainMenuTest {
    @ParameterizedTest
    @ValueSource(strings = {"q", "1Q", " ", "2"})
    void 메인_메뉴가_1_또는_Q가_아니라면_예외를_발생시킨다(String menu) {
        assertThatThrownBy(() -> MainMenu.of(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 메인 메뉴는 1 또는 Q 이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "Q"})
    void 메인_메뉴가_1_또는_Q라면_예외를_발생시키지_않는다(String menu) {
        assertThatCode(() -> MainMenu.of(menu))
                .doesNotThrowAnyException();
    }
}
