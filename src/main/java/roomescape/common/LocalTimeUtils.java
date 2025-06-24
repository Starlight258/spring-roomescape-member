package roomescape.common;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class LocalTimeUtils {

    public static LocalTime truncatedLocalTimeByMinutes(final LocalTime localTime){
        return localTime.truncatedTo(ChronoUnit.MINUTES);
    }
}
