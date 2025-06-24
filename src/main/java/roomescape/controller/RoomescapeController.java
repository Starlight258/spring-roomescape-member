package roomescape.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import roomescape.dto.FindAllReservation;
import roomescape.service.ReservationService;

@Controller
public class RoomescapeController {

    private final ReservationService reservationService;

    public RoomescapeController(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/admin")
    public String getAdminHomePage(){
        return "admin/index";
    }

    @GetMapping("/admin/reservation")
    public String getAdminReservationPage(){
        return "admin/reservation-legacy";
    }

    @GetMapping("/reservations")
    @ResponseBody
    public List<FindAllReservation> findAll(){
        return reservationService.findAll();
    }
}
