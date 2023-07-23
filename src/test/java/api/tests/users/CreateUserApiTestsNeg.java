package api.tests.users;

import api.steps.UserApiSteps;
import api.models.apidataproviders.InvalidUserCredentialsDataProvider;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserApiTestsNeg {
    UserApiSteps userApiSteps = new UserApiSteps();
    private String userIdResult;

    @Test(dataProvider = "invalidUserCredentials", dataProviderClass = InvalidUserCredentialsDataProvider.class)
    @Step("API test checks negative cases of creation a user with username {0} and password {1}")
    public void createUserApiTestNeg(String username, String password){
        userIdResult = userApiSteps.createUser(username,password);
        Assert.assertFalse(Boolean.parseBoolean(userIdResult), "User is created");
    }
}
