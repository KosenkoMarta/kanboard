package ui.pageobjects.header;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import ui.pageobjects.LoginPage;

import static com.codeborne.selenide.Selenide.$;
@Getter
public class UserMenuDropdown extends HeaderSection{
    private final SelenideElement userMenuDropdown = $(".dropdown-submenu-open");
    private final SelenideElement userName = $(".dropdown-submenu-open>li:nth-of-type(1)");
    private final SelenideElement myDashboard = $(".dropdown-submenu-open>li:nth-of-type(2)");
    private final SelenideElement myProfile = $(".dropdown-submenu-open>li:nth-of-type(3)");
    private final SelenideElement projectsManagement = $(".dropdown-submenu-open>li:nth-of-type(4)");
    private final SelenideElement usersManagement = $(".dropdown-submenu-open>li:nth-of-type(5)");
    private final SelenideElement groupsManagement = $(".dropdown-submenu-open>li:nth-of-type(6)");
    private final SelenideElement plugins = $(".dropdown-submenu-open>li:nth-of-type(7)");
    private final SelenideElement settings = $(".dropdown-submenu-open>li:nth-of-type(8)");
    private final SelenideElement documentation = $(".dropdown-submenu-open>li:nth-of-type(9)");
    private final SelenideElement logout = $(".dropdown-submenu-open>li:nth-of-type(10)");

    @Step("User clicks on Logout User menu item")
    public LoginPage logOut(){
        new HeaderSection().getUserMenuBtn()
                .shouldBe(Condition.visible).click();
        getLogout().shouldBe(Condition.visible).click();
        return new LoginPage();
    }
}
