package roomescape.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationDate;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByDateAndTimeId(final ReservationDate date, final Long timeId);

    boolean existsByTimeId(Long timeId);
}
