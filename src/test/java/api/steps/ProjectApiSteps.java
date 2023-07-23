package api.steps;


import api.models.Result;
import api.models.args.BodyArgs;
import api.models.args.projects.CreateProject;
import api.models.args.projects.ProjectId;
import api.models.args.projects.ProjectName;
import api.models.args.projects.ProjectProperties;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.util.List;

import static api.methods.Projects.*;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;

public class ProjectApiSteps extends BaseApiSteps{
    public String createProjectRequiredParam(String projectName){
        CreateProject args = CreateProject.builder()
                .name(projectName)
                .build();
        return createProjectBody(args);
    }

    public String createProjectNoRequiredParam(Integer ownerId){
        CreateProject args = CreateProject.builder()
                .owner_id(ownerId)
                .build();
        return createProjectBody(args);
    }

    private String createProjectBody(CreateProject args){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(CREATE_PROJECT)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        Result result = response.as(Result.class);
        return result.getResult().toString();
    }

    public boolean removeProject(String projectId){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new ProjectId(Integer.valueOf(projectId)))
                .method(REMOVE_PROJECT)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return (boolean) response.as(Result.class).getResult();
    }

    public boolean addProjectUser(String projectId, String userId, String projectRole){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(List.of(projectId, userId, projectRole))
                .method(ADD_PROJECT_USER)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return (boolean) response.as(Result.class).getResult();
    }

    public Result<ProjectProperties> getProjectProperties (Integer projectId){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new ProjectId(projectId))
                .method(GET_PROJECT_BY_ID)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return response.as(new TypeRef<Result<ProjectProperties>>() {});
    }
    public Result<ProjectProperties> getProjectPropertiesByName (String projectName){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new ProjectName(projectName))
                .method(GET_PROJECT_BY_NAME)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return response.as(new TypeRef<Result<ProjectProperties>>() {});
    }
}
