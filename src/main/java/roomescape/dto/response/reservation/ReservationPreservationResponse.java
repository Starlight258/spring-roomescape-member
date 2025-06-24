package roomescape.dto.response.reservation;

import java.time.LocalDate;
import roomescape.domain.Reservation;
import roomescape.dto.response.reservationtime.ReservationTimeRetrievalResponse;

public record ReservationPreservationResponse(Long id, String name, LocalDate date, ReservationTimeRetrievalResponse time) {

    public static ReservationPreservationResponse from(final Reservation reservation) {
        return new ReservationPreservationResponse(reservation.getId(), reservation.getName().getName(),
                reservation.getDate().getDate(), ReservationTimeRetrievalResponse.from(reservation.getTime()));
    }
}
