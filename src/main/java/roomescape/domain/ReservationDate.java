package roomescape.domain;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;
import roomescape.common.TimeUtils;

@Embeddable
public class ReservationDate {

    private LocalDate date;

    public ReservationDate(final String date) {
        this.date = TimeUtils.parseLocalDate(date);
    }

    protected ReservationDate() {
    }

    public LocalDate getDate() {
        return date;
    }
}
