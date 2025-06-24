package roomescape.service;

import roomescape.domain.reservation.ReservationDate;
import roomescape.domain.theme.Theme;

public interface ReservationCounter {

    Long countByDateBetweenAndTheme(ReservationDate startDate, ReservationDate endDate, Theme theme);
}
