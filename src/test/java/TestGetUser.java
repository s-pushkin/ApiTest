import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Tag;
import io.qameta.allure.Allure;

@Tag("getUser")
public class TestGetUser {
    public void positiveTestGetUser(int id) {
        Response response = RestAssured.given()
                .get("https://reqres.in/api/users/" + id);

        assertEquals(200, response.getStatusCode(), "Expected status code 200");
        assertNotNull(response.jsonPath().getString("data.id"), "ID should not be null");
        assertEquals("Janet", response.jsonPath().getString("data.first_name"), "Expected first name is Janet");
    }

    public void negativeTestGetUserNotFound(int id) {
        Response response = RestAssured.given()
                .get("https://reqres.in/api/users/" + id);

        assertEquals(404, response.getStatusCode(), "Expected status code 404");
        assertEquals("{}", response.getBody().asString(), "Error message is incorrect");
    }
}