package roomescape.dto.response.reservation;

import roomescape.domain.Reservation;
import roomescape.dto.response.reservationtime.ReservationTimeRetrievalResponse;
import roomescape.dto.response.theme.ThemeRetrievalResponse;

public record ReservationRetrievalResponse(Long id, String name, String date, ReservationTimeRetrievalResponse time,
                                           ThemeRetrievalResponse theme) {

    public static ReservationRetrievalResponse from(Reservation reservation) {
        ReservationTimeRetrievalResponse time = ReservationTimeRetrievalResponse.from(reservation.getTime());
        ThemeRetrievalResponse theme = ThemeRetrievalResponse.from(reservation.getTheme());
        return new ReservationRetrievalResponse(reservation.getId(), reservation.getName().getName(),
                reservation.getDate().getDate().toString(), time, theme);
    }
}
