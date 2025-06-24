package roomescape.service;

import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationTime;
import roomescape.dto.request.ReservationPreservationRequest;
import roomescape.dto.response.ReservationPreservationResponse;
import roomescape.dto.response.TotalReservationRetrieval;
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
        ReservationTime reservationTime = reservationTimeRepository.findById(request.timeId())
                .orElseThrow(() -> new IllegalArgumentException("예약 시간이 존재하지 않습니다."));
        Reservation savedReservation = reservationRepository.save(
                new Reservation(request.name(), request.date(), reservationTime));
        return ReservationPreservationResponse.from(savedReservation);
    }

    public List<TotalReservationRetrieval> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(TotalReservationRetrieval::from)
                .toList();
    }

    public void remove(final Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}
