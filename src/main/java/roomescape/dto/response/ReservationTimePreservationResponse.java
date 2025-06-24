package roomescape.dto.response;

import roomescape.domain.ReservationTime;

public record ReservationTimePreservationResponse(Long id, String startAt) {

    public static ReservationTimePreservationResponse from(final ReservationTime reservationTime) {
        return new ReservationTimePreservationResponse(reservationTime.getId(),
                reservationTime.getStartAt().toString());
    }
}
