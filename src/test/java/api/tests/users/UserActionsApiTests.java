package api.tests.users;

import api.models.Result;
import api.models.args.users.UserDetails;
import api.steps.UserApiSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.RandomGenerator;

import static api.enums.UserRoles.ADMIN;
import static api.enums.UserRoles.USER;

public class UserActionsApiTests {
    private static final String USERNAME = "user";
    private static final String PASSWORD = "q1w2e3r4";
    UserApiSteps userApiSteps = new UserApiSteps();
    private String userId;
    private Boolean isRoleUpdated;
    private Boolean isDeleted;
    private Result<UserDetails> userProperties;
    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME + RandomGenerator.getRandomInt(), PASSWORD);
    }

    @Test
    @Step("API test checks negative case of removal a user")
    public void removeUserApiTest(){
        isDeleted = userApiSteps.deleteUser(userId + RandomGenerator.getRandomInt());
        Assert.assertFalse(isDeleted.booleanValue(), "User is removed");
    }

    @Test
    @Step("API test checks positive case of getting a user properties")
    public void getUserTest(){
        userProperties = userApiSteps.getUserInfo(userId);
        userProperties.getResult().getUsername().contains(USERNAME);
        userProperties.getResult().getRole().equals(ADMIN.getRole());
        userProperties.getResult().getId().equals(userId);
    }

    @Test
    @Step("API test checks negative case of getting a user properties")
    public void getUserNegativeTest(){
        userProperties = userApiSteps.getUserInfo(userId+userId);
        Assert.assertNull(userProperties.getResult(), "Returns user properties");
    }

    @Test
    @Step("API test checks positive case of updating a user role")
    public void updateUserTest(){
        isRoleUpdated = userApiSteps.updateUserRoleRequiredParam(userId, USER.getRole());
        Assert.assertTrue(isRoleUpdated, "The user role is not updated");
    }
    @Test
    @Step("API test checks negative case of updating a user role")
    public void updateUserNegativeTest(){
        isRoleUpdated = userApiSteps.updateUserRoleNoRequiredParam(USER.getRole());
        Assert.assertFalse(isRoleUpdated, "The user role is updated");
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        userApiSteps.deleteUser(userId);
    }
}
