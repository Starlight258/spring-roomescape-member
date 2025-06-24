package roomescape.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class ReservationName {

    private String name;

    public ReservationName(final String name) {
        this.name = name;
    }

    protected ReservationName() {
    }

    public String getName() {
        return name;
    }
}
