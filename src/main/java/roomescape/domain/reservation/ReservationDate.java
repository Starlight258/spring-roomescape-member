package roomescape.domain.reservation;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import roomescape.common.TimeUtils;
import roomescape.exception.BadRequestException;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReservationDate {

    @Column(nullable = false)
    private LocalDate date;

    public ReservationDate(final LocalDate date) {
        validateNotPreviousDate(date);
        this.date = date;
    }

    public ReservationDate(final String date) {
        this(TimeUtils.parseLocalDate(date));
    }

    private void validateNotPreviousDate(final LocalDate date) {
        LocalDate nowDate = LocalDate.now();
        if (date.isBefore(nowDate)) {
            throw new BadRequestException("Reservation date must not be previous");
        }
    }
}
