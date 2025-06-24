package roomescape.dto.response;

import static roomescape.common.TimeUtils.truncatedLocalTimeByMinutes;

import java.time.LocalTime;
import roomescape.domain.ReservationTime;

public record ReservationTimeRetrieval(Long id, String startAt) {

    public static ReservationTimeRetrieval from(ReservationTime reservationTime) {
        LocalTime truncatedLocalTime = truncatedLocalTimeByMinutes(reservationTime.getStartAt());
        return new ReservationTimeRetrieval(reservationTime.getId(), truncatedLocalTime.toString());
    }
}
