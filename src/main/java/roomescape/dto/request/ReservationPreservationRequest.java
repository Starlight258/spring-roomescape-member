package roomescape.dto.request;

import jakarta.validation.constraints.NotNull;

public record ReservationPreservationRequest(
        @NotNull
        String name,
        @NotNull
        String date,
        @NotNull
        Long timeId
) {

}
