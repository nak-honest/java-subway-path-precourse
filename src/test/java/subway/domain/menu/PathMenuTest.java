package subway.domain.menu;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import subway.domain.menu.MainMenu;
import subway.domain.menu.PathMenu;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PathMenuTest {
    @ParameterizedTest
    @ValueSource(strings = {"q", "Q", "0", " ", "3"})
    void 경로_조회_메뉴가_1_2_B_중_하나가_아니라면_예외를_발생시킨다(String menu) {
        assertThatThrownBy(() -> PathMenu.of(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 메인 메뉴는 1, 2, B 중 하나여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "B"})
    void 경로_조회_메뉴가_1_2_B_중_하나라면_예외를_발생시키지_않는다(String menu) {
        assertThatCode(() -> PathMenu.of(menu))
                .doesNotThrowAnyException();
    }
}
