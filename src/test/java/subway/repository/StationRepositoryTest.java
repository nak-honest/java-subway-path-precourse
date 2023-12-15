package subway.repository;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import subway.domain.Station;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StationRepositoryTest {
    @ParameterizedTest
    @ValueSource(strings = {"양주역", "도봉산역", "광운대역"})
    void 존재하지_않는_역을_조회하는_경우_예외를_발생시킨다(String stationName) {
        // given
        StationRepository.addStation(new Station("강남역"));
        StationRepository.addStation(new Station("역삼역"));

        // when & then
        assertThatThrownBy(() -> StationRepository.findByName(stationName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 역입니다.");
    }
}
