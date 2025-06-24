package roomescape.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
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

    public Reservation(final ReservationName name, final ReservationDate date, final ReservationTime time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public Reservation(final String name, final String date, final ReservationTime time) {
        this.name = new ReservationName(name);
        this.date = new ReservationDate(date);
        this.time = time;
    }

    protected Reservation() {
    }

    public Long getId() {
        return id;
    }

    public ReservationName getName() {
        return name;
    }

    public ReservationDate getDate() {
        return date;
    }

    public ReservationTime getTime() {
        return time;
    }
}
