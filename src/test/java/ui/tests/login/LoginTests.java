package ui.tests.login;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ui.dataprovidersui.LoginDataProvider;
import ui.pageobjects.header.HeaderSection;
import ui.pageobjects.header.UserMenuDropdown;
import ui.pageobjects.LoginPage;
import ui.tests.BaseTest;

import static utils.EnvProperties.BASE_URL;

public class LoginTests extends BaseTest {
    private final static String USERNAME = "admin";
    private final static String PASSWORD = "admin";
    private final static String URL = BASE_URL+"/login";


    @Test(dataProvider = "adminCredentials", dataProviderClass = LoginDataProvider.class)
    @Step("Checking positive and negative cases Login as Admin with username {0} and password {1}")
    public void loginTest(String username, String password, String url) {
        SoftAssert softAssert = new SoftAssert();
        new LoginPage()
                .openLoginPage()
                .loginGeneric(HeaderSection.class, username, password);
        softAssert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), url
                , "The logIn was not successful");
        softAssert.assertAll();
    }

    @Test
    @Step("Checking positive case Login as Admin then Logout")
    public void logoutTest() {
        SoftAssert softAssert = new SoftAssert();
        new LoginPage()
                .openLoginPage()
                .loginGeneric(HeaderSection.class, USERNAME, PASSWORD);
        new UserMenuDropdown()
                .logOut();
        softAssert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), URL, "The logout was not successful");
        softAssert.assertAll();
    }
}
