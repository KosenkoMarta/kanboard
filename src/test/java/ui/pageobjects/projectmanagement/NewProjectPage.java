package ui.pageobjects.projectmanagement;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.pageobjects.header.HeaderSection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class NewProjectPage {
    private String projectNameAdded;
    public SelenideElement newProjectModal() {
        return $("#modal-content");
    }
    public SelenideElement projectNameField() {
        return $x("//*[@id='form-name']");
    }
    public SelenideElement projectIdentifierField() {
        return $x("//*[@id='form-identifier']");
    }
    public SelenideElement projectNameErrorAlert() {
        return $(".form-errors");
    }
    public SelenideElement columnTaskLimitCheckbox() {
        return $x("//*[@name='per_swimlane_task_limits']");
    }
    public SelenideElement taskLimitField() {
        return $("input[name='task_limit']");
    }
    public SelenideElement duplicateProjectDropdown() {
        return $x("//select");
    }
    public SelenideElement duplicateFromOption(String projectNameAdded) {
        return $x("//option[contains(text(),'"+projectNameAdded+"')]");
    }
    public SelenideElement saveBtn() {
        return $("button[type='submit']");
    }
    public SelenideElement cancelBtn() {
        return $(".form-actions>a");
    }

    @Step("User fills in a project name and clicks on Save button")
    public ProjectSummaryPage createNewProject(String projectName){
        projectNameField().shouldBe(Condition.visible).sendKeys(projectName);
        saveBtn().shouldBe(Condition.visible).click();
        return new ProjectSummaryPage();
    }
    @Step("User creates a new project with a project name and identifier")
    public ProjectSummaryPage createNewProject(String projectName, String identifier){
        projectNameField().shouldBe(Condition.visible).sendKeys(projectName);
        projectIdentifierField().shouldBe(Condition.visible).sendKeys(identifier);
        saveBtn().shouldBe(Condition.visible).click();
        return new ProjectSummaryPage();
    }

    @Step("User creates a new project with a project name, identifier and task limit")
    public ProjectSummaryPage createNewProject(String projectName, String identifier, String taskLimit){
        projectNameField().shouldBe(Condition.visible).sendKeys(projectName);
        projectIdentifierField().shouldBe(Condition.visible).sendKeys(identifier);
        taskLimitField().shouldBe(Condition.visible).sendKeys(taskLimit);
        saveBtn().shouldBe(Condition.visible).click();
        return new ProjectSummaryPage();
    }

    @Step("User creates a new project with a project name and duplicating objects from the existing project")
    public ProjectSummaryPage createNewProjectDuplicated (String projectName){
        projectNameField().shouldBe(Condition.visible).sendKeys(projectName);
        duplicateProjectDropdown().shouldBe(Condition.visible);
        duplicateFromOption(projectNameAdded).click();
        saveBtn().shouldBe(Condition.visible).click();
        return new ProjectSummaryPage();
    }

    @Step("User fills in a project name and clicks on Cancel button")
    public HeaderSection cancelCreateNewProject(String projectName){
        projectNameField().shouldBe(Condition.visible).sendKeys(projectName);
        cancelBtn().shouldBe(Condition.visible).click();
        return new HeaderSection();
    }
}
