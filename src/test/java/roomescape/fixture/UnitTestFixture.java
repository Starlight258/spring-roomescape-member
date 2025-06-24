package roomescape.fixture;

import roomescape.domain.theme.Theme;
import roomescape.domain.theme.ThemeDescription;
import roomescape.domain.theme.ThemeName;
import roomescape.domain.theme.ThemeThumbnail;

public class UnitTestFixture {

    public static Theme makeTheme() {
        return new Theme(new ThemeName("추리"),
                new ThemeDescription("셜록"),
                new ThemeThumbnail("thumbnail.png")
        );
    }

}
