package roomescape.fixture;

import java.time.LocalDate;
import roomescape.domain.theme.Theme;
import roomescape.domain.theme.ThemeDescription;
import roomescape.domain.theme.ThemeName;
import roomescape.domain.theme.ThemeThumbnail;

public class UnitTestFixture {

    public static Theme makeTheme() {
        return new Theme(new ThemeName("기억저장소"),
                new ThemeDescription("memory"),
                new ThemeThumbnail("thumbnail.png")
        );
    }

    public static LocalDate makeFutureDate() {
        return LocalDate.now().plusDays(1);
    }
}
