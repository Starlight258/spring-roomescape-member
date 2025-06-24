package roomescape.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.domain.theme.Theme;
import roomescape.domain.theme.ThemeDescription;
import roomescape.domain.theme.ThemeName;
import roomescape.domain.theme.ThemeThumbnail;
import roomescape.dto.request.theme.ThemePreservationRequest;
import roomescape.dto.response.theme.ThemeRetrievalResponse;
import roomescape.exception.BadRequestException;
import roomescape.exception.ConflictException;
import roomescape.repository.ReservationRepository;
import roomescape.repository.ThemeRepository;

@Service
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final ReservationRepository reservationRepository;

    public ThemeService(final ThemeRepository themeRepository, final ReservationRepository reservationRepository) {
        this.themeRepository = themeRepository;
        this.reservationRepository = reservationRepository;
    }

    public ThemeRetrievalResponse create(final @Valid ThemePreservationRequest request) {
        ThemeName name = new ThemeName(request.name());
        validateThemeNameExists(name);

        ThemeDescription description = new ThemeDescription(request.description());
        ThemeThumbnail thumbnail = new ThemeThumbnail(request.thumbnail());
        Theme savedTheme = themeRepository.save(
                new Theme(name, description, thumbnail)
        );
        return ThemeRetrievalResponse.from(savedTheme);
    }

    public List<ThemeRetrievalResponse> findAll() {
        List<Theme> themes = themeRepository.findAll();
        return themes.stream()
                .map(ThemeRetrievalResponse::from)
                .toList();
    }

    public void remove(final Long id) {
        validateReservationNotExists(id);
        themeRepository.deleteById(id);
    }

    private void validateThemeNameExists(final @NotBlank ThemeName name) {
        if (themeRepository.existsByName(name)) {
            throw new ConflictException("Theme is already exists");
        }
    }

    private void validateReservationNotExists(final Long themeId) {
        if (reservationRepository.existsByThemeId(themeId)) {
            throw new BadRequestException("The theme is referenced by reservation");
        }
    }
}
