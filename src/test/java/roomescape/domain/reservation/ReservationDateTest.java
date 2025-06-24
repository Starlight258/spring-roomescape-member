package roomescape.domain.reservation;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import roomescape.exception.BadRequestException;

class ReservationDateTest {

    @Test
    void 예약_날짜는_과거일수_없다() {
        LocalDate yesterdayDate = LocalDate.now().minusDays(1);
        Assertions.assertThatThrownBy(() -> new ReservationDate(yesterdayDate))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Reservation date must not be previous");
    }
}
