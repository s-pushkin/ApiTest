import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Test;

public class ApiTest {
    @Test
    @Feature("User Registration")
    @Severity(SeverityLevel.CRITICAL)
    public void apiTest() {
        Allure.step("Step 1: Register User with valid data", () -> {
            TestRegisterUser testRegisterUser = new TestRegisterUser();
            testRegisterUser.positiveTestRegisterUser("eve.holt@reqres.in", "pistol");
        });

        Allure.step("Step 2: Try registering user without password", () -> {
            TestRegisterUser testRegisterUser = new TestRegisterUser();
            testRegisterUser.negativeTestRegisterUserWithoutPassword("eve.holt@reqres.in");
        });

        Allure.step("Step 3: Get User with ID 2", () -> {
            TestGetUser testGetUser = new TestGetUser();
            testGetUser.positiveTestGetUser(2);
        });

        Allure.step("Step 4: Try getting a non-existing user", () -> {
            TestGetUser testGetUser = new TestGetUser();
            testGetUser.negativeTestGetUserNotFound(9999);
        });

        Allure.step("Step 5: Delete User with ID 2", () -> {
            TestDeleteUser testDeleteUser = new TestDeleteUser();
            testDeleteUser.positiveTestDeleteUser(2);
        });

        Allure.step("Step 6: Try deleting a non-existing user", () -> {
            TestDeleteUser testDeleteUser = new TestDeleteUser();
            testDeleteUser.negativeTestDeleteUserNotFound(9999);
        });

        Allure.step("Step 7: Update User with valid data", () -> {
            TestUpdateUser testUpdateUser = new TestUpdateUser();
            testUpdateUser.positiveTestUpdateUser("John", "Developer", 2);
        });

        Allure.step("Step 8: Try updating a non-existing user", () -> {
            TestUpdateUser testUpdateUser = new TestUpdateUser();
            testUpdateUser.negativeTestUpdateUser("", "", 9999);
        });
    }
}
