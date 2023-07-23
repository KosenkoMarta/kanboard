package ui.pageobjects.projectmanagement;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import ui.pageobjects.LoginPage;
import ui.pageobjects.header.HeaderSection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ProjectSummaryPage extends HeaderSection{
    private final SelenideElement removeItem = $x("//a[contains(text(), 'Remove')]");
    private final SelenideElement removeProjectModalWindow = $("#modal-box");
    private final SelenideElement yesBtn = $("#modal-confirm-button");
    private final SelenideElement cancelBtn = $(".form-actions>a");
    private final SelenideElement backlogOpenTasks = $x("//td[contains(text(),'Backlog')]/following-sibling::td[3]");

    @Step("Open Project Summary page")
    public LoginPage openProjectSummaryPage(Integer projectId){
        Selenide.open("/project/"+projectId);
        return new LoginPage();
    }

    @Step("User clicks on Remove menu item, gets confirmation modal window and clicks on Yes button")
    public ProjectsPage removeProject(){
        getRemoveItem().shouldBe(Condition.visible).click();
        getRemoveProjectModalWindow().shouldBe(Condition.visible);
        getYesBtn().shouldBe(Condition.visible).click();
        return new ProjectsPage();
    }

    @Step("User clicks on Remove menu item, gets confirmation modal window and clicks on Cancel button")
    public ProjectSummaryPage removeProjectCancel(){
        getRemoveItem().shouldBe(Condition.visible).click();
        getCancelBtn().shouldBe(Condition.visible).click();
        return this;
    }
}
