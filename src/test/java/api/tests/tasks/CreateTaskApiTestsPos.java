package api.tests.tasks;

import api.steps.ProjectApiSteps;
import api.steps.TaskApiSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.RandomGenerator;

public class CreateTaskApiTestsPos {
    private static final String PROJECTNAME = "project";
    private static final String TASK_TITLE = "task";
    private String projectId;
    private String taskId;
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    TaskApiSteps taskApiSteps = new TaskApiSteps();

    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApiSteps.createProjectRequiredParam(PROJECTNAME + RandomGenerator.getRandomInt());
    }

    @Test
    @Step("API test checks positive case of creation a task")
    public void createTaskApiTest(){
        taskId = taskApiSteps.createTaskRequiredParams(TASK_TITLE + RandomGenerator.getRandomInt(), Integer.valueOf(projectId));
        Assert.assertNotEquals(taskId, false, "Task is not created");
    }


    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        taskApiSteps.removeTask(Integer.valueOf(taskId));
        projectApiSteps.removeProject(projectId);
    }
}
