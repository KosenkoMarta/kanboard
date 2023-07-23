package ui.pageobjects.tasks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class NewCommentPage {
    private final SelenideElement commentModalWindow = $("#modal-content");
    private final SelenideElement commentArea = $x("//*[@id=\"modal-content\"]/form/div[1]/div/div[2]/textarea");
    private final SelenideElement saveBtn = $x("//*[@id='modal-content']/form/div[2]/div/button");
    private final SelenideElement cancelBtn = $(".form-actions>a");

    @Step("User creates the first comment in the task")
    public TaskSummaryPage createFirstComment(String commentText){
        getCommentModalWindow().shouldBe(Condition.visible);
        getCommentArea().shouldBe(Condition.visible).sendKeys(commentText);
        getSaveBtn().shouldBe(Condition.visible).click();
        return new TaskSummaryPage();
    }

    @Step("User fills in some first comment in the task and clicks on Cancel button")
    public TaskSummaryPage cancelFirstComment(String commentText){
        getCommentModalWindow().shouldBe(Condition.visible);
        getCommentArea().shouldBe(Condition.visible).sendKeys(commentText);
        getCancelBtn().shouldBe(Condition.visible).click();
        return new TaskSummaryPage();
    }
}
