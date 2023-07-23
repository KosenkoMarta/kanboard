package api.tests.users;

import api.steps.UserApiSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.RandomGenerator;

public class DeleteUserApiTestsPos {
    private static final String USERNAME = "user";
    private static final String PASSWORD = "q1w2e3r4";
    UserApiSteps userApiSteps = new UserApiSteps();
    private String userId;
    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME + RandomGenerator.getRandomInt(), PASSWORD);
    }

    @Test
    @Step("API test checks positive case of removal a user")
    public void removeUserApiTest(){
        Boolean isDeleted = userApiSteps.deleteUser(userId);
        Assert.assertTrue(isDeleted.booleanValue(), "User is not deleted");
    }
}
