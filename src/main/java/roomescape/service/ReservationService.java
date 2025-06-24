package roomescape.service;

import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationDate;
import roomescape.domain.ReservationName;
import roomescape.domain.ReservationTime;
import roomescape.dto.request.ReservationPreservationRequest;
import roomescape.dto.response.ReservationPreservationResponse;
import roomescape.dto.response.ReservationRetrievalResponse;
import roomescape.exception.ConflictException;
import roomescape.repository.ReservationRepository;
import roomescape.repository.ReservationTimeRepository;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationTimeRepository reservationTimeRepository;

    public ReservationService(final ReservationRepository reservationRepository,
                              final ReservationTimeRepository reservationTimeRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationTimeRepository = reservationTimeRepository;
    }

    public ReservationPreservationResponse create(final ReservationPreservationRequest request) {
        ReservationName reservationName = new ReservationName(request.name());
        ReservationTime reservationTime = getReservationTime(request.timeId());
        ReservationDate reservationDate = new ReservationDate(request.date());
        validateReservationExists(reservationDate, reservationTime);

        Reservation savedReservation = reservationRepository.save(
                new Reservation(reservationName, reservationDate, reservationTime));
        return ReservationPreservationResponse.from(savedReservation);
    }

    public List<ReservationRetrievalResponse> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(ReservationRetrievalResponse::from)
                .toList();
    }

    public void remove(final Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    private ReservationTime getReservationTime(final Long timeId) {
        return reservationTimeRepository.findById(timeId)
                .orElseThrow(() -> new IllegalArgumentException("예약 시간이 존재하지 않습니다."));
    }

    private void validateReservationExists(final ReservationDate reservationDate,
                                           final ReservationTime reservationTime) {
        if (reservationRepository.existsByDateAndTimeId(reservationDate, reservationTime.getId())) {
            throw new ConflictException("Reservation is already exists");
        }
    }
}
