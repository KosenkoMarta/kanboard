package api.steps;

import api.models.Result;
import api.models.args.BodyArgs;
import api.models.args.users.CreateUser;
import api.models.args.users.UpdateUser;
import api.models.args.users.UserDetails;
import api.models.args.users.UserId;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import static api.enums.UserRoles.ADMIN;
import static api.methods.Users.*;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;

public class UserApiSteps extends BaseApiSteps {
    public String createUser(String username, String password){

        CreateUser args = CreateUser.builder()
                .username(username)
                .password(password)
                .role(ADMIN.getRole())
                .build();

        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(CREATE_USER)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        Result result = response.as(Result.class);
        return result.getResult().toString();
    }

    public boolean deleteUser(String userId){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new UserId(Integer.valueOf(userId)))
                .method(DELETE_USER)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return (boolean) response.as(Result.class).getResult();
    }

    public Result<UserDetails> getUserInfo(String userId){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new UserId(Integer.valueOf(userId)))
                .method(GET_USER)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return response.as(new TypeRef<Result<UserDetails>>() {});
    }

    public boolean updateUserRoleRequiredParam(String userId, String userRole){
        UpdateUser args = UpdateUser.builder()
                .id(Integer.valueOf(userId))
                .role(userRole)
                .build();
        return createUserBody(args);
    }

    public boolean updateUserRoleNoRequiredParam(String userRole){
        UpdateUser args = UpdateUser.builder()
                .role(userRole)
                .build();
        return createUserBody(args);
    }

    private boolean createUserBody(UpdateUser args){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(UPDATE_USER)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return (boolean) response.as(Result.class).getResult();
    }

}
