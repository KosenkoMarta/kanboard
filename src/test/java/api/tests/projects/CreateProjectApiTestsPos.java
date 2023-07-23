package api.tests.projects;

import api.steps.ProjectApiSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.RandomGenerator;

public class CreateProjectApiTestsPos {
    private static final String PROJECTNAME = "project";
    private String projectId;
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();

    @Test
    @Step("API test checks positive case of creation a project")
    public void createProjectApiTest(){
        projectId = projectApiSteps.createProjectRequiredParam(PROJECTNAME + RandomGenerator.getRandomInt());
        Assert.assertNotEquals(projectId, false, "Project is not created");
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        projectApiSteps.removeProject(projectId);
    }


}
