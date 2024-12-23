import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Tag;
import io.qameta.allure.Allure;

@Tag("registerUser")
public class TestRegisterUser{
    public void positiveTestRegisterUser(String email, String password) {
        String requestBody = "{ \"email\": " + "\"" + email + "\"" + ", \"password\": " + "\"" + password + "\"" + "}";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("https://reqres.in/api/register");

        assertEquals(200, response.getStatusCode(), "Expected status code 201");
        assertNotNull(response.jsonPath().getString("id"), "ID should not be null");
        assertNotNull(response.jsonPath().getString("token"), "Token should not be null");
    }
    public void negativeTestRegisterUserWithoutPassword(String email) {
        String requestBody = "{ \"email\": " + "\"" + email + "\"" + "}";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("https://reqres.in/api/register");

        assertEquals(400, response.getStatusCode(), "Expected status code 400");
        assertEquals("{\"error\":\"Missing password\"}", response.getBody().asString(), "Error message is incorrect");
    }
}