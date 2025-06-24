package roomescape.fixture;

import static org.hamcrest.Matchers.is;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import roomescape.dto.request.reservation.ReservationPreservationRequest;
import roomescape.dto.request.theme.ThemePreservationRequest;
import roomescape.dto.response.reservation.ReservationPreservationResponse;
import roomescape.dto.response.reservationtime.ReservationTimePreservationResponse;
import roomescape.dto.response.theme.ThemeRetrievalResponse;

public class E2ETestFixture {

    public static final String DEFAULT_THEME_NAME = "기억저장소";

    public static Long saveReservationTime(LocalTime time) {
        Map<String, String> params = new HashMap<>();
        params.put("startAt", time.toString());

        ReservationTimePreservationResponse response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(params)
                .when().post("/times")
                .then().log().all()
                .statusCode(201)
                .extract()
                .as(new TypeRef<>() {
                });

        return response.id();
    }

    public static Long saveTheme(String name) {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new ThemePreservationRequest(name, "memory", "thumbnail.png"))
                .when().post("/themes")
                .then().log().all()
                .statusCode(201)
                .body("id", is(1));

        List<ThemeRetrievalResponse> themes = RestAssured.given().log().all()
                .when().get("/themes")
                .then().log().all()
                .statusCode(200)
                .body("size()", is(1))
                .extract()
                .as(new TypeRef<>() {
                });

        return themes.getFirst().id();
    }

    public static Long saveReservation(LocalDate date, Long timeId, Long themeId) {
        ReservationPreservationResponse response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new ReservationPreservationRequest("mint", date.toString(), timeId, themeId))
                .when().post("/reservations")
                .then().log().all()
                .statusCode(201)
                .extract()
                .as(ReservationPreservationResponse.class);

        return response.id();
    }
}
