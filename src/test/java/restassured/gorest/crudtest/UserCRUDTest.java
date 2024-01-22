package restassured.gorest.crudtest;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import restassured.gorest.testbase.TestBase;
import restassured.gorest.userinfo.UserSteps;
import restassured.gorest.utils.TestUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTest extends TestBase {
    static String name = TestUtils.getRandomValue() + "Anita";
    static String email = TestUtils.getRandomValue() + "anita@gmail.com";
    static String gender = "Female";
    static String status = "Active";
    static int userID;

    @Steps
    UserSteps userSteps;

    @Title("This will create a new user")
    @Test
    public void test001() {
        ValidatableResponse response = userSteps.createUser(name, email, gender, status);
        response.statusCode(201);
        userID = response.extract().path("id");
    }

    @Test
    public void test002() {
        HashMap<String, Object> userMap = userSteps.getUserInfoByUserID(userID);
        Assert.assertThat(userMap, hasValue(name));

    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {
        userSteps.updateUser(userID, name, email, gender, status).statusCode(200);
        HashMap<String, Object> userMap = userSteps.getUserInfoByUserID(userID);
        Assert.assertThat(userMap, hasValue(userID));
    }

    @Title("Delete the user and verify if the user is deleted")
    @Test
    public void test004() {
        userSteps.deleteUser(userID).statusCode(204);
        userSteps.getUserById(userID).statusCode(404);
    }
}