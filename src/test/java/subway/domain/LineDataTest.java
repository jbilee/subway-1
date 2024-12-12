package subway.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import subway.domain.constants.LineData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineDataTest {
    @DisplayName("Common 노선을 찾는다.")
    @ParameterizedTest
    @CsvSource({"교대역,양재시민의숲역,false", "교대역,강남역,true"})
    void 노선_테스트(String departure, String destination, boolean expectedValue) {
        assertEquals(expectedValue, LineData.hasCommonRoute(departure, destination));
    }
}
