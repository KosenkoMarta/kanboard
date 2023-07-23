package ui.pageobjects.tasks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.pageobjects.header.HeaderSection;
import ui.pageobjects.LoginPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TaskSummaryPage extends HeaderSection {
    public SelenideElement removeItem() {
        return $x("//a[text()='Remove']");
    }
    public SelenideElement closeThisTaskItem() {
        return $x("//a[text()='Close this task']");
    }
    public SelenideElement addCommentItem() {
        return $x("//a[text()='Add a comment']");
    }
    public SelenideElement taskStatusClosed() {
        return $x("//span[contains(text(), 'closed')]");
    }
    public SelenideElement yesBtn() {
        return $("#modal-confirm-button");
    }
    public SelenideElement cancelBtn() {
        return $x("//a[text()='cancel']");
    }
    public SelenideElement commentAuthor(String userName) {
        return $x("//strong[@class='comment-username'][text()='"+userName+"']");
    }
    public SelenideElement commentText(String commentText) {
        return $x("//p[text()='"+commentText+"']");
    }

    @Step("Open Task Summary page")
    public LoginPage openTaskSummaryPage(Integer taskId){
        Selenide.open("/task/"+taskId);
        return new LoginPage();
    }
    @Step("User clicks on Remove menu item, gets confirmation modal window and clicks on Yes button")
    public BoardPage clickRemoveItem(){
        removeItem().shouldBe(Condition.visible).click();
        yesBtn().shouldBe(Condition.visible).click();
        return new BoardPage();
    }

    @Step("User clicks on Close this task menu item, gets confirmation modal window and clicks on Yes button")
    public TaskSummaryPage clickCloseThisTask(){
        closeThisTaskItem().shouldBe(Condition.visible).click();
        yesBtn().shouldBe(Condition.visible).click();
        return this;
    }

    @Step("User clicks on Add a comment menu item")
    public NewCommentPage clickAddCommentItem(){
        addCommentItem().shouldBe(Condition.visible).click();
        return new NewCommentPage();
    }
}
