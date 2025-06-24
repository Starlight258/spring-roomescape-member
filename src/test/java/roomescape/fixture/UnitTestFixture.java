package roomescape.fixture;

import roomescape.domain.Theme;

public class UnitTestFixture {

    public static Theme makeTheme() {
        return new Theme("추리", "셜록", "thumbnail.png");
    }

}
