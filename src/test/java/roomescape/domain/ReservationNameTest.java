package roomescape.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import roomescape.exception.BadRequestException;

class ReservationNameTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void 이름은_비어있어서는_안된다(String input) {
        Assertions.assertThatThrownBy(() -> new ReservationName(input))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Name is mandatory");
    }

}
