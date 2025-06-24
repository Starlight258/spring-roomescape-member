package roomescape.dto;

import static roomescape.common.LocalTimeUtils.truncatedLocalTimeByMinutes;

import java.time.LocalTime;
import roomescape.domain.Reservation;

public record FindAllReservation(Long id, String name, String date, String time) {

    public static FindAllReservation from(Reservation reservation) {
        LocalTime truncatedLocalTime = truncatedLocalTimeByMinutes(reservation.getTime());
        return new FindAllReservation(reservation.getId(), reservation.getName(), reservation.getDate().toString(),
                truncatedLocalTime.toString());
    }
}
