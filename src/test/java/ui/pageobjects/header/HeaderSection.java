package ui.pageobjects.header;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import ui.pageobjects.projectmanagement.NewProjectPage;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class HeaderSection {
    private final SelenideElement logo = $(".logo");
    private final SelenideElement title = $(".title");
    private final SelenideElement notificationBtn = $(".notification");
    private final SelenideElement addProjectBtn = $("[class='fa fa-plus fa-fw']");
    private final SelenideElement userMenuBtn = $(".avatar-letter");

    @Step("User clicks on Add project dropdown and selects Add New Project")
    public NewProjectPage addNewProjectHeader(){
        getAddProjectBtn().shouldBe(Condition.visible).click();
        NewProjectPage selectOpt = new ProjectMenuDropdown()
                .clickAddNewProject();
        return new NewProjectPage();
    }
}
