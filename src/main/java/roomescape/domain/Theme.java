package roomescape.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import roomescape.exception.BadRequestException;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    private String thumbnail;

    public Theme(final String name, final String description, final String thumbnail) {
        validateName(name);
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    private void validateName(final String name) {
        if (name.isBlank()) {
            throw new BadRequestException("Theme name is mandatory");
        }
    }
}
