package roomescape.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import roomescape.exception.BadRequestException;

class ThemeTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("테마 이름은 필수이다")
    void 테마_이름은_비어있어서는_안된다(String input) {
        Assertions.assertThatThrownBy(() -> new Theme(input, "", ""))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Theme name is mandatory");
    }
}
