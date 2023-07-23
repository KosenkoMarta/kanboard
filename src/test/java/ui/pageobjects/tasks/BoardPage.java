package ui.pageobjects.tasks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.pageobjects.header.HeaderSection;
import ui.pageobjects.LoginPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BoardPage extends HeaderSection {
    public SelenideElement configurationDropdown() {
        return $("[class='fa fa-cog']");
    }
    public SelenideElement addNewTaskMenuItem() {
        return $x("//ul[@class='dropdown-submenu-open']//li[4]");
    }
    public SelenideElement addedTask(String addedTaskName) {
        return $x("//a[contains(text(),'"+addedTaskName+"')]");
    }
    public SelenideElement taskId(String taskId) {
        return $x("//strong[contains(text(),'"+taskId+"')]");
    }
    public SelenideElement list() {
        return $(".view-listing");
    }

    @Step("Open Project Board page")
    public LoginPage openProjectBoardPage(Integer projectId){
        Selenide.open("/board/"+projectId);
        return new LoginPage();
    }
    @Step("User clicks on the Configuration dropdown and selects Add New Task option")
    public NewTaskPage addNewTaskDropdown(){
        configurationDropdown().shouldBe(Condition.visible).click();
        addNewTaskMenuItem().shouldBe(Condition.visible).click();
        return new NewTaskPage();
    }
    @Step("User clicks on the List tab")
    public ListPage navigateToListPage(){
        list().shouldBe(Condition.visible).click();
        return new ListPage();
    }
}
