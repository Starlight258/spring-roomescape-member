package roomescape.dto.request;

import jakarta.validation.constraints.NotNull;

public record ReservationTimePreservationRequest(
        @NotNull
        String startAt
) {

}
