package api.tests.tasks;

import api.steps.ProjectApiSteps;
import api.steps.TaskApiSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.RandomGenerator;
import java.util.List;

public class CreateTaskApiTestsNeg {
    private static final String PROJECTNAME = "project";
    private static final String TASK_TITLE = "task";
    private String projectId;
    private String taskId;
    private String colorId="green";
    private Integer columnId=2;
    private Integer ownerId= 1;
    private Integer creatorId= 1;
    private String dateDue="2023-07-22";
    private String description="";
    private Integer categoryId=0;
    private Integer score=0;
    private Integer swimlaneId=1;
    private Integer priority=1;
    private Integer recurrenceStatus=0;
    private Integer recurrenceTrigger=0;
    private Integer recurrenceFactor=0;
    private Integer recurrenceTimeframe=0;
    private Integer recurrenceBasedate=0;
    private String reference="";
    private List<String> tags=List.of("tag1", "tag2");
    private String dateStarted="2023-07-20";
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    TaskApiSteps taskApiSteps = new TaskApiSteps();

    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApiSteps.createProjectRequiredParam(PROJECTNAME + RandomGenerator.getRandomInt());
    }

    @Test
    @Step("API test checks negative case of creation a task")
    public void createTaskApiNegativeTest(){
        taskId = taskApiSteps.createTask(TASK_TITLE + RandomGenerator.getRandomInt(), Integer.valueOf(projectId),
                colorId, columnId, ownerId, creatorId, dateDue, description, categoryId, score,
                swimlaneId, priority, recurrenceStatus, recurrenceTrigger, recurrenceFactor, recurrenceTimeframe,
                recurrenceBasedate, reference, tags, dateStarted);
        Assert.assertFalse(Boolean.parseBoolean(taskId), "Task is created");
    }


    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        projectApiSteps.removeProject(projectId);
    }
}
