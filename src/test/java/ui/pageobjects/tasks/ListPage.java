package ui.pageobjects.tasks;

import io.qameta.allure.Step;
import ui.pageobjects.header.HeaderSection;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ListPage extends HeaderSection {
    private String taskId;
    public SelenideElement taskConfigurationDropdown(String taskId) {
        return $x("//strong[contains(text(),'"+taskId+"')]");
    }
    public SelenideElement removeMenuItem() {
        return $(".dropdown-submenu-open li:nth-last-child(2)");
    }
    public SelenideElement taskName(String taskId) {
        return $x("//a[contains(text(),'"+taskId+"')]");
    }
    public SelenideElement tasksAlert() {
        return $("p.alert");
    }

    @Step("User clicks on the Task name")
    public TaskSummaryPage clickOnTaskName(){
        taskName(taskId).shouldBe(Condition.visible).click();
        return new TaskSummaryPage();
    }
}
