package roomescape.e2e;

import static org.hamcrest.Matchers.is;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import roomescape.dto.request.reservation.ReservationPreservationRequest;
import roomescape.fixture.E2ETestFixture;
import roomescape.fixture.UnitTestFixture;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(properties = {
        "spring.sql.init.data-locations="
})
public class ReservationE2ETest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void findReservations() {
        Long timeId = E2ETestFixture.saveReservationTime(LocalTime.of(10, 0));
        Long themeId = E2ETestFixture.saveTheme();
        E2ETestFixture.saveReservation(UnitTestFixture.makeFutureDate(), timeId, themeId);
        RestAssured.given().log().all()
                .when().get("/reservations")
                .then().log().all()
                .statusCode(200)
                .body("size()", is(1));
    }

    @Test
    void saveReservation() {
        Long timeId = E2ETestFixture.saveReservationTime(LocalTime.of(10, 0));
        Long themeId = E2ETestFixture.saveTheme();

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new ReservationPreservationRequest("mint", "2026-02-05", timeId, themeId))
                .when().post("/reservations")
                .then().log().all()
                .statusCode(201)
                .body("id", is(1));

        RestAssured.given().log().all()
                .when().get("/reservations")
                .then().log().all()
                .statusCode(200)
                .body("size()", is(1));
    }

    @Test
    void deleteReservation() {
        saveReservation();

        RestAssured.given().log().all()
                .when().delete("/reservations/1")
                .then().log().all()
                .statusCode(204);

        RestAssured.given().log().all()
                .when().get("/reservations")
                .then().log().all()
                .statusCode(200)
                .body("size()", is(0));
    }
}
