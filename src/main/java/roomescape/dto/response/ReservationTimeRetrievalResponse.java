package roomescape.dto.response;

import static roomescape.common.TimeUtils.truncatedLocalTimeByMinutes;

import java.time.LocalTime;
import roomescape.domain.ReservationTime;

public record ReservationTimeRetrievalResponse(Long id, String startAt) {

    public static ReservationTimeRetrievalResponse from(ReservationTime reservationTime) {
        LocalTime truncatedLocalTime = truncatedLocalTimeByMinutes(reservationTime.getStartAt());
        return new ReservationTimeRetrievalResponse(reservationTime.getId(), truncatedLocalTime.toString());
    }
}
