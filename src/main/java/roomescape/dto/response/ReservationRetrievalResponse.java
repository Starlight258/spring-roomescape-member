package roomescape.dto.response;

import roomescape.domain.Reservation;

public record ReservationRetrievalResponse(Long id, String name, String date, ReservationTimeRetrievalResponse time) {

    public static ReservationRetrievalResponse from(Reservation reservation) {
        ReservationTimeRetrievalResponse time = ReservationTimeRetrievalResponse.from(reservation.getTime());
        return new ReservationRetrievalResponse(reservation.getId(), reservation.getName().getName(),
                reservation.getDate().getDate().toString(), time);
    }
}
