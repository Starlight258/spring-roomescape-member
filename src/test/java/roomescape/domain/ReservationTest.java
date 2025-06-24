package roomescape.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import roomescape.exception.BadRequestException;

class ReservationTest {

    @Test
    void 예약은_현재_시간_이후로만_가능하다() {
        // Given
        ReservationName name = new ReservationName("mint");
        ReservationDate date = new ReservationDate(LocalDate.now());
        ReservationTime time = new ReservationTime(LocalTime.now().plusHours(1));

        // When & Then
        Assertions.assertThatCode(() -> new Reservation(name, date, time)).doesNotThrowAnyException();
    }

    @Test
    void 과거_시간으로_예약은_불가능하다() {
        // Given
        ReservationName name = new ReservationName("mint");
        ReservationDate date = new ReservationDate(LocalDate.now());
        ReservationTime time = new ReservationTime(LocalTime.now().minusHours(1));

        // When & Then
        Assertions.assertThatThrownBy(() -> new Reservation(name, date, time))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Reservation date and time should be future");

    }
}
