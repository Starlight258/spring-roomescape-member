package roomescape.fixture;

import static org.hamcrest.Matchers.is;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import roomescape.dto.request.reservation.ReservationPreservationRequest;
import roomescape.dto.request.theme.ThemePreservationRequest;
import roomescape.dto.response.reservation.ReservationPreservationResponse;
import roomescape.dto.response.reservationtime.ReservationTimePreservationResponse;
import roomescape.dto.response.theme.ThemeRetrievalResponse;

public class E2ETestFixture {

    public static Long saveReservationTime() {
        Map<String, String> params = new HashMap<>();
        params.put("startAt", "10:00");

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

    public static Long saveTheme() {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new ThemePreservationRequest("추리", "셜록", "thumbnail.png"))
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

    public static Long saveReservation() {
        Long timeId = E2ETestFixture.saveReservationTime();
        Long themeId = E2ETestFixture.saveTheme();

        ReservationPreservationResponse response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new ReservationPreservationRequest("mint", "2026-02-05", timeId, themeId))
                .when().post("/reservations")
                .then().log().all()
                .statusCode(201)
                .extract()
                .as(ReservationPreservationResponse.class);

        return response.id();
    }
}
