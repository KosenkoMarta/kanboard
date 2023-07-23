package ui.pageobjects.header;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import ui.pageobjects.projectmanagement.NewProjectPage;

import static com.codeborne.selenide.Selenide.$;
@Getter
public class ProjectMenuDropdown extends HeaderSection{
    private final SelenideElement projectMenuDropdown = $(".dropdown-submenu-open");
    private final SelenideElement addNewProjectMenuItem = $(".dropdown-submenu-open>li:nth-of-type(1)");
    private final SelenideElement addNewPersonalProjMenuItem = $(".dropdown-submenu-open>li:nth-of-type(2)");

    @Step("User clicks on Add new project dropdown menu item")
    public NewProjectPage clickAddNewProject(){
        getAddNewProjectMenuItem().shouldBe(Condition.visible).click();
        return new NewProjectPage();
    }
}
