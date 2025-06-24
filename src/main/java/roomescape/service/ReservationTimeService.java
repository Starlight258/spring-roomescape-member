package roomescape.service;

import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.domain.ReservationTime;
import roomescape.dto.request.ReservationTimePreservationRequest;
import roomescape.dto.response.ReservationTimePreservationResponse;
import roomescape.dto.response.ReservationTimeRetrieval;
import roomescape.repository.ReservationTimeRepository;

@Service
public class ReservationTimeService {

    private final ReservationTimeRepository reservationTimeRepository;

    public ReservationTimeService(final ReservationTimeRepository reservationTimeRepository) {
        this.reservationTimeRepository = reservationTimeRepository;
    }

    public ReservationTimePreservationResponse create(final ReservationTimePreservationRequest request) {
        ReservationTime savedReservationTime = reservationTimeRepository.save(new ReservationTime(request.startAt()));
        return ReservationTimePreservationResponse.from(savedReservationTime);
    }

    public List<ReservationTimeRetrieval> findAll() {
        List<ReservationTime> reservationTimes = reservationTimeRepository.findAll();
        return reservationTimes.stream()
                .map(ReservationTimeRetrieval::from)
                .toList();
    }

    public void remove(final Long id) {
        reservationTimeRepository.deleteById(id);
    }
}
