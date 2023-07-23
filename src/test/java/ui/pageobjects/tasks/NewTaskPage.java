package ui.pageobjects.tasks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class NewTaskPage {
    private final SelenideElement taskTitle = $("#form-title");
    private final SelenideElement saveBtn = $("button[type=submit]");

    @Step("User fills in Task name field and click on Save button")
    public BoardPage createNewTaskFromDropdown(String taskName){
        getTaskTitle().shouldBe(Condition.visible).sendKeys(taskName);
        getSaveBtn().shouldBe(Condition.visible).click();
        return new BoardPage();
    }
}
