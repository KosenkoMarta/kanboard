package api.tests.projects;

import api.models.Result;
import api.models.args.projects.ProjectProperties;
import api.steps.ProjectApiSteps;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.RandomGenerator;

public class ProjectActionsApiTests {
    private static final String PROJECTNAME = "project";
    private String projectId;
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();

    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApiSteps.createProjectRequiredParam(PROJECTNAME + RandomGenerator.getRandomInt());
    }
    @Test
    @Step("API test checks negative case of removal a project")
    public void removeProjectNegativeApiTest(){
        SoftAssert softAssert = new SoftAssert();
        if (projectId != null) {
            Boolean isDeleted = projectApiSteps.removeProject(projectId + projectId);
            softAssert.assertFalse(isDeleted.booleanValue(), "Project is removed");
        } else {
            softAssert.fail("Project not found for removal");
        }
        softAssert.assertAll();
    }

    @Test
    @Step("API test checks positive case of getting a project properties by project id")
    public void getProjectPropertiesTest(){
        SoftAssert softAssert = new SoftAssert();

        Result<ProjectProperties> projectProperties = projectApiSteps.getProjectProperties(Integer.valueOf(projectId));
        projectProperties.getResult().getName().contains(PROJECTNAME);
        softAssert.assertNotNull(projectProperties.getResult().getUrl(), "URL is null");
        softAssert.assertAll();
    }

    @Test
    @Step("API test checks negative case of getting a project properties by project id")
    public void getProjectPropertiesNegativeTest(){
        SoftAssert softAssert = new SoftAssert();

        Result<ProjectProperties> projectProperties = projectApiSteps.getProjectProperties(Integer.valueOf(projectId+projectId));
        softAssert.assertNull(projectProperties.getResult(), "Returns project properties");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        projectApiSteps.removeProject(projectId);
    }

}
