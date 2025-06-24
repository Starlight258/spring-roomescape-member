package roomescape.fixture;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.Map;
import roomescape.dto.request.ReservationPreservationRequest;
import roomescape.dto.response.ReservationPreservationResponse;
import roomescape.dto.response.ReservationTimePreservationResponse;

public class TestFixture {

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

    public static Long saveReservation() {
        Long timeId = TestFixture.saveReservationTime();

        ReservationPreservationResponse response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new ReservationPreservationRequest("mint", "2026-02-05", timeId))
                .when().post("/reservations")
                .then().log().all()
                .statusCode(201)
                .extract()
                .as(ReservationPreservationResponse.class);

        return response.id();
    }
}
