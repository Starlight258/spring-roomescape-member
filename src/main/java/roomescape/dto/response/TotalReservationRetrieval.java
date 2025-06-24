package roomescape.dto.response;

import roomescape.domain.Reservation;

public record TotalReservationRetrieval(Long id, String name, String date, ReservationTimeRetrieval time) {

    public static TotalReservationRetrieval from(Reservation reservation) {
        ReservationTimeRetrieval time = ReservationTimeRetrieval.from(reservation.getTime());
        return new TotalReservationRetrieval(reservation.getId(), reservation.getName().getName(),
                reservation.getDate().getDate().toString(), time);
    }
}
