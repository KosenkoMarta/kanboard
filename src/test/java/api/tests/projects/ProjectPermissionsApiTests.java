package api.tests.projects;

import api.steps.ProjectApiSteps;
import api.steps.UserApiSteps;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.RandomGenerator;

import static api.enums.ProjectRoles.MANAGER;

public class ProjectPermissionsApiTests {
    private static final String USERNAME = "user";
    private static final String PASSWORD = "q1w2e3r4";
    private static final String PROJECTNAME = "project";
    private String projectId;
    private String userId;
    UserApiSteps userApiSteps = new UserApiSteps();
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    private SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME + RandomGenerator.getRandomInt(), PASSWORD);
        projectId = projectApiSteps.createProjectRequiredParam(PROJECTNAME + RandomGenerator.getRandomInt());
    }

    @Test
    @Step("API test checks positive case of adding a project user")
    public void addProjectUserApiTest(){
        Boolean isAdded = projectApiSteps.addProjectUser(projectId, userId, MANAGER.getRoleProject());
        softAssert.assertTrue(isAdded, "Project user is not added");
        softAssert.assertAll();
    }

    @Test
    @Step("API test checks negative case of adding a project user")
    public void addProjectUserApiNegativeTest(){
        Boolean isAdded = projectApiSteps.addProjectUser(projectId+projectId, userId, MANAGER.getRoleProject());
        softAssert.assertFalse(isAdded, "Project user is added");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        projectApiSteps.removeProject(projectId);
        userApiSteps.deleteUser(userId);
    }
}
