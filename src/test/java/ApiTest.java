import org.junit.jupiter.api.Test;

public class ApiTest {
    @Test
    public void apiTest(){
        TestRegisterUser testRegisterUser = new TestRegisterUser();
        TestGetUser testGetUser = new TestGetUser();
        TestDeleteUser testDeleteUser = new TestDeleteUser();
        TestUpdateUser testUpdateUser = new TestUpdateUser();

        testRegisterUser.positiveTestRegisterUser("eve.holt@reqres.in","pistol");
        testRegisterUser.negativeTestRegisterUserWithoutPassword("eve.holt@reqres.in");

        testGetUser.positiveTestGetUser(2);
        testGetUser.negativeTestGetUserNotFound(9999);

        testDeleteUser.positiveTestDeleteUser(2);
        testDeleteUser.negativeTestDeleteUserNotFound(9999);

        testUpdateUser.positiveTestUpdateUser("John", "Developer", 2);
        testUpdateUser.negativeTestUpdateUser("", "", 9999);

    }

}
