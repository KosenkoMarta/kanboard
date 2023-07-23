package ui.pageobjects.dashboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.pageobjects.header.HeaderSection;
import ui.pageobjects.LoginPage;
import ui.pageobjects.projectmanagement.ProjectSummaryPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardOverviewPage extends HeaderSection {
    private String projectId;

    public SelenideElement projectConfigurationDropdown(String projectId) {
        return $x("//strong[contains(text(),'"+projectId+"')]");
    }

    public SelenideElement configureThisProjectMenuItem() {
        return $(".dropdown-submenu-open li:nth-last-child(1)");
    }
    @Step("Open the User Dashboard page")
    public LoginPage openUserDashboardPage(){
        Selenide.open("/dashboard");
        return new LoginPage();
    }

    @Step("User selects a project from the list")
    public ProjectSummaryPage selectConfigureThisProject(){
        projectConfigurationDropdown(projectId).shouldBe(Condition.visible).click();
        configureThisProjectMenuItem().shouldBe(Condition.visible).click();
        return new ProjectSummaryPage();
    }
}
