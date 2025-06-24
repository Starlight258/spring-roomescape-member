package roomescape.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import roomescape.exception.BadRequestException;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ReservationName name;

    @Embedded
    private ReservationDate date;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "time_id")
    private ReservationTime time;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "theme_id")
    private Theme theme;

    public Reservation(final ReservationName name, final ReservationDate date, final ReservationTime time,
                       final Theme theme) {
        validateFutureDateTime(date, time);
        this.name = name;
        this.date = date;
        this.time = time;
        this.theme = theme;
    }

    private void validateFutureDateTime(final ReservationDate date, final ReservationTime time) {
        LocalDateTime dateTime = LocalDateTime.of(date.getDate(), time.getStartAt());
        LocalDateTime nowDateTime = LocalDateTime.now();
        if (!dateTime.isAfter(nowDateTime)) {
            throw new BadRequestException("Reservation date and time should be future");
        }
    }
}
