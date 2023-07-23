package ui.tests.login;

import api.steps.UserApiSteps;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ui.pageobjects.LoginPage;
import ui.pageobjects.header.HeaderSection;
import ui.tests.BaseTest;
import utils.RandomGenerator;

import static utils.EnvProperties.BASE_URL;

public class LoginCreatedUserTests extends BaseTest {
    private final static String USERNAME = "user";
    private final static String PASSWORD = "q1w2e3r4";
    private final static String URL = BASE_URL +"/";
    private String userId;
    private String username;
    UserApiSteps userApiSteps = new UserApiSteps();
    private SoftAssert softAssert;

    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME + RandomGenerator.getRandomInt(), PASSWORD);
        username = userApiSteps.getUserInfo(userId).getResult().getUsername();
        softAssert = new SoftAssert();
    }

    @Test
    @Step("Checking positive case Login as newly created user")
    public void loginCreatedUserTest() {
        new LoginPage()
                .openLoginPage()
                .loginGeneric(HeaderSection.class, username, PASSWORD);
        softAssert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(),
                URL, "The logIn was not successful");
        softAssert.assertAll();
    }

    @Test
    @Step("Checking negative case Login as newly created user")
    public void loginCreatedUserNegativeTest() {
        new LoginPage()
                .openLoginPage()
                .loginGeneric(HeaderSection.class, username, "");
        softAssert.assertNotEquals(WebDriverRunner.getWebDriver().getCurrentUrl(),
                URL, "The logIn was successful");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        userApiSteps.deleteUser(userId);
    }
}
