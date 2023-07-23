package ui.tests.project;

import api.steps.ProjectApiSteps;
import api.steps.UserApiSteps;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pageobjects.dashboard.DashboardOverviewPage;
import ui.pageobjects.header.HeaderSection;
import ui.pageobjects.projectmanagement.ProjectSummaryPage;
import ui.tests.BaseTest;
import utils.DBReader;
import utils.RandomGenerator;

import static api.enums.UserRoles.ADMIN;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class CancelProjectActionsTests extends BaseTest {
    private final static String USERNAME = "user";
    private final static String PASSWORD = "q1w2e3r4";
    private String userId;
    private String username;
    private Boolean isRoleUpdated;
    private String projectId;
    private String projectName = "Test"+ RandomGenerator.getRandomInt();
    UserApiSteps userApiSteps = new UserApiSteps();
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();

    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME + RandomGenerator.getRandomInt(), PASSWORD);
        isRoleUpdated = userApiSteps.updateUserRoleRequiredParam(userId, ADMIN.getRole());
        username = userApiSteps.getUserInfo(userId).getResult().getUsername();
        projectId = projectApiSteps.createProjectRequiredParam(projectName);
    }

    @Test
    @Step("Checking case Cancel creation a new project")
    public void cancelAddNewProjectTest() {
        SelenideElement newProjectTitle = new DashboardOverviewPage()
                .openUserDashboardPage()
                .loginGeneric(HeaderSection.class, username, PASSWORD)
                .addNewProjectHeader()
                .cancelCreateNewProject(projectName)
                .getTitle().shouldBe(visible);
        newProjectTitle.shouldHave(text("Dashboard"));
    }

    @Test
    @Step("Checking case Cancel removal a project")
    public void cancelRemoveProjectTest(){
        SelenideElement newProjectTitle = new ProjectSummaryPage()
                .openProjectSummaryPage(Integer.valueOf(projectId))
                .loginGeneric(ProjectSummaryPage.class, username, PASSWORD)
                .removeProjectCancel()
                .getTitle().shouldBe(visible);
        newProjectTitle.shouldHave(text(projectName));

        Assert.assertNotNull(DBReader.getProjectIdFromDBByName(projectName), "Project is removed");
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        projectApiSteps.removeProject(projectId);
        userApiSteps.deleteUser(userId);
    }
}
