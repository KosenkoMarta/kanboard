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
import ui.pageobjects.tasks.TaskSummaryPage;
import ui.tests.BaseTest;
import utils.DBReader;
import utils.RandomGenerator;
import utils.database.Tasks;

import static api.enums.UserRoles.ADMIN;

public class TaskTests extends BaseTest {
    private final static String USERNAME = "user";
    private final static String PASSWORD = "q1w2e3r4";
    private String userId;
    private String username;
    private Boolean isRoleUpdated;
    private String projectId;
    private String projectName = "Test";
    private String taskName = "Task";
    private String commentText = "This is the comment " + RandomGenerator.getRandomInt();
    private String taskId;
    private Tasks taskInfoDB;
    private String commentId;
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
        taskId=taskApiSteps.createTaskRequiredParams(taskName + RandomGenerator.getRandomInt(), Integer.valueOf(projectId));
    }

    @Test
    @Step("Checking case Remove a task")
    public void removeTaskTest(){
        SelenideElement addedTask = new TaskSummaryPage()
                .openTaskSummaryPage(Integer.valueOf(taskId))
                .loginGeneric(TaskSummaryPage.class, username, PASSWORD)
                .clickRemoveItem()
                .taskId(taskId);
        addedTask.should(Condition.disappear);

        Assert.assertNull(taskInfoDB= DBReader.getTaskFromDBById(taskId),"Task is not removed");
    }


    @Test
    @Step("Checking case Close this task")
    public void closeTaskTest(){
        SelenideElement taskStatus = new TaskSummaryPage()
                .openTaskSummaryPage(Integer.valueOf(taskId))
                .loginGeneric(TaskSummaryPage.class, username, PASSWORD)
                .clickCloseThisTask()
                .taskStatusClosed().shouldBe(Condition.visible);
        taskStatus.shouldHave(Condition.text("close"));
    }

    @Test
    @Step("Checking case Add the first comment to the task")
    public void addFirstCommentTest(){
        SelenideElement comment = new TaskSummaryPage()
                .openTaskSummaryPage(Integer.valueOf(taskId))
                .loginGeneric(TaskSummaryPage.class, username, PASSWORD)
                .clickAddCommentItem()
                .createFirstComment(commentText)
                .commentText(commentText).shouldBe(Condition.visible);
        comment.shouldHave(Condition.exactText(commentText));

        Assert.assertNotNull(commentId = DBReader.getCommentIdFromDBByUserTaskComment(taskId,userId,commentText), "Comment is not created");
    }


    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        taskApiSteps.removeTask(Integer.valueOf(taskId));
        projectApiSteps.removeProject(projectId);
        userApiSteps.deleteUser(userId);
    }

}
