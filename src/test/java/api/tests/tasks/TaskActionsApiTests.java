package api.tests.tasks;


import api.models.Result;
import api.models.args.tasks.TaskDetails;
import api.steps.ProjectApiSteps;
import api.steps.TaskApiSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.RandomGenerator;

public class TaskActionsApiTests {
    private static final String PROJECTNAME = "project";
    private static final String TASK_TITLE = "task";
    private String projectId;
    private String taskId;
    Result<TaskDetails> taskDetails;
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    TaskApiSteps taskApiSteps = new TaskApiSteps();

    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApiSteps.createProjectRequiredParam(PROJECTNAME + RandomGenerator.getRandomInt());
        taskId = taskApiSteps.createTaskRequiredParams(TASK_TITLE + RandomGenerator.getRandomInt(), Integer.valueOf(projectId));
    }

    @Test
    @Step("API test checks positive case of getting a task")
    public void getTaskPropertiesApiTest(){
        taskDetails = taskApiSteps.getTaskDetails(Integer.valueOf(taskId));
        taskDetails.getResult().getTitle().contains(TASK_TITLE);
        Assert.assertNotNull(taskDetails.getResult().getUrl(), "URL is null");
    }

    @Test
    @Step("API test checks negative case of getting a task")
    public void getTaskPropertiesApiNegativeTest(){
        taskDetails = taskApiSteps.getTaskDetails(Integer.valueOf(taskId+taskId));
        Assert.assertNull(taskDetails.getResult(), "Task properties are returned");
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        taskApiSteps.removeTask(Integer.valueOf(taskId));
        projectApiSteps.removeProject(projectId);
    }
}
