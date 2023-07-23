package api.tests.projects;

import api.steps.ProjectApiSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.RandomGenerator;

public class RemoveProjectApiTests {
    private static final String PROJECTNAME = "project";
    private String projectId;
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();

    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApiSteps.createProjectRequiredParam(PROJECTNAME + RandomGenerator.getRandomInt());
    }

    @Test
    @Step("API test checks positive case of removal a project")
    public void removeProjectApiTest() {
        Boolean isDeleted = projectApiSteps.removeProject(projectId);
        Assert.assertTrue(isDeleted.booleanValue(), "Project is not deleted");
    }
}
