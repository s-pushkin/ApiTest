import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Tag;
import io.qameta.allure.Allure;

@Tag("deleteUser")
public class TestDeleteUser {
    public void positiveTestDeleteUser(int id) {
        Response response = RestAssured.given()
                .delete("https://reqres.in/api/users/" + id);

        assertEquals(204, response.getStatusCode(), "Expected status code 204");
    }
    public void negativeTestDeleteUserNotFound(int id) {
        Response response = RestAssured.given()
                .delete("https://reqres.in/api/users/" + id);

        assertEquals(204, response.getStatusCode(), "Expected status code 204");
    }
}