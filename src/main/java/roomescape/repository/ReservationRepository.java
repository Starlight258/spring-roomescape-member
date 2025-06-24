package roomescape.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationDate;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByDateAndTimeIdAndThemeId(final ReservationDate date, final Long timeId, final Long themeId);

    boolean existsByTimeId(Long timeId);

    boolean existsByThemeId(Long themeId);
}
