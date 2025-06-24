package roomescape.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberViewController {

    @GetMapping("/reservation")
    public String getMemberReservationPage() {
        return "reservation";
    }

    @GetMapping
    public String getPopularThemesPage() {
        return "index";
    }
}
