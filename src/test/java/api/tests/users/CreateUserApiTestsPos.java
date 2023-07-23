package api.tests.users;

import api.steps.UserApiSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.RandomGenerator;

public class CreateUserApiTestsPos {
    private static final String USERNAME = "user";
    private static final String PASSWORD = "q1w2e3r4";
    UserApiSteps userApiSteps = new UserApiSteps();
    private String userId;
    @Test
    @Step("API test checks positive case of creation a user")
    public void createUserApiTest(){
        userId = userApiSteps.createUser(USERNAME + RandomGenerator.getRandomInt(), PASSWORD);
        Assert.assertNotEquals(userId, false, "User is not created");
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        userApiSteps.deleteUser(userId);
    }
}
