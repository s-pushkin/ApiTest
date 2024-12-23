import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Tag;
import io.qameta.allure.Allure;

@Tag("updateUser")
public class TestUpdateUser {
    public void positiveTestUpdateUser(String name, String job, int id) {
        String requestBody = "{ \"name\": \"" + name + "\", \"job\": \"" + job + "\" }";

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .put("https://reqres.in/api/users/" + id);

        assertEquals(200, response.getStatusCode());
        assertEquals(name, response.jsonPath().getString("name"));
        assertEquals(job, response.jsonPath().getString("job"));
    }
    public void negativeTestUpdateUser(String name, String job, int id) {
        String requestBody = "{ \"name\": \"" + name + "\", \"job\": \"" + job + "\" }";

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .put("https://reqres.in/api/users/" + id);

        assertEquals(200, response.getStatusCode());
        assertEquals(name, response.jsonPath().getString("name"));
        assertEquals(job, response.jsonPath().getString("job"));
    }
}