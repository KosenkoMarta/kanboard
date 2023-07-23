package ui.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.SneakyThrows;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class LoginPage {
    public final SelenideElement userNameField = $x("//*[@id=\"form-username\"]");
    public final SelenideElement passwordField = $x("//*[@id='form-password']");
    public final SelenideElement signInBtn = $x("//*[@type='submit']");
    public final SelenideElement credsErrorAlert = $(".alert");

    @Step("Open the Login page")
    public LoginPage openLoginPage(){
        Selenide.open("");
        return new LoginPage();
    }

    @SneakyThrows
    @Step("User logs in the app")
    public <T> T loginGeneric (Class<T> returnType, String login, String password){
        getUserNameField().shouldBe(Condition.visible).sendKeys(login);
        getPasswordField().shouldBe(Condition.visible).sendKeys(password);
        getSignInBtn().shouldBe(Condition.visible).click();
        return returnType.newInstance();
    }

}
