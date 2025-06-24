package roomescape.dto.response;

import java.time.LocalDate;
import roomescape.domain.Reservation;

public record ReservationPreservationResponse(Long id, String name, LocalDate date, ReservationTimeRetrieval time) {

    public static ReservationPreservationResponse from(final Reservation reservation) {
        return new ReservationPreservationResponse(reservation.getId(), reservation.getName().getName(),
                reservation.getDate().getDate(), ReservationTimeRetrieval.from(reservation.getTime()));
    }
}
