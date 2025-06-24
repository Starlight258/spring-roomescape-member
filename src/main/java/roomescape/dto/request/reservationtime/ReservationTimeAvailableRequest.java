package roomescape.dto.request.reservationtime;

import jakarta.validation.constraints.NotNull;

public record ReservationTimeAvailableRequest(
        @NotNull
        String date,
        @NotNull
        Long themeId
) {

}
