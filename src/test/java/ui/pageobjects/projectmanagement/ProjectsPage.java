package ui.pageobjects.projectmanagement;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import ui.pageobjects.LoginPage;
import ui.pageobjects.header.HeaderSection;

public class ProjectsPage extends HeaderSection {
    @Step("Open Projects page")
    public LoginPage openProjectSummaryPage(){
        Selenide.open("/projects");
        return new LoginPage();
    }
}
