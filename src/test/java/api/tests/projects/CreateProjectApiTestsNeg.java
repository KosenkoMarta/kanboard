package api.tests.projects;

import api.steps.ProjectApiSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RandomGenerator;


public class CreateProjectApiTestsNeg {
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    private String projectId;

    @Test
    @Step("API test checks negative case of creation a project - without the required parameter Project name")
    public void createProjectApiNegativeTest(){
        projectId = projectApiSteps.createProjectNoRequiredParam(RandomGenerator.getRandomInt());

        Assert.assertFalse(Boolean.parseBoolean(projectId), "Project is created");
    }
}
