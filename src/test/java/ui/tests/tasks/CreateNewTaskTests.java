package ui.tests.tasks;

import api.steps.ProjectApiSteps;
import api.steps.TaskApiSteps;
import api.steps.UserApiSteps;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pageobjects.tasks.BoardPage;
import ui.tests.BaseTest;
import utils.DBReader;
import utils.RandomGenerator;

import static api.enums.UserRoles.ADMIN;

public class CreateNewTaskTests extends BaseTest {
    private final static String USERNAME = "user";
    private final static String PASSWORD = "q1w2e3r4";
    private String userId;
    private String username;
    private Boolean isRoleUpdated;
    private String projectId;
    private String projectName = "Test";
    private String taskName = "Task" + RandomGenerator.getRandomInt();
    private String taskId;
    UserApiSteps userApiSteps = new UserApiSteps();
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    TaskApiSteps taskApiSteps = new TaskApiSteps();

    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME + RandomGenerator.getRandomInt(), PASSWORD);
        isRoleUpdated = userApiSteps.updateUserRoleRequiredParam(userId, ADMIN.getRole());
        username = userApiSteps.getUserInfo(userId).getResult().getUsername();
        projectId = projectApiSteps.createProjectRequiredParam(projectName + RandomGenerator.getRandomInt());
    }
    @Test
    @Step("Checking case Create a new task from the dropdown")
    public void addNewTaskFromDropdownTest(){
        SelenideElement addedTask = new BoardPage()
                .openProjectBoardPage(Integer.valueOf(projectId))
                .loginGeneric(BoardPage.class, username, PASSWORD)
                .addNewTaskDropdown()
                .createNewTaskFromDropdown(taskName)
                .addedTask(taskName).shouldBe(Condition.visible);
        addedTask.shouldHave(Condition.exactText(taskName));

        Assert.assertNotNull(taskId = DBReader.getTaskIdFromDBByTitle(taskName), "Task is not created");

    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        taskApiSteps.removeTask(Integer.valueOf(taskId));
        projectApiSteps.removeProject(projectId);
        userApiSteps.deleteUser(userId);
    }
}
