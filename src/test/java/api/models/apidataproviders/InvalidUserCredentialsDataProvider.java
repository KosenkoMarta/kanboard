package api.models.apidataproviders;

import org.testng.annotations.DataProvider;

public class InvalidUserCredentialsDataProvider {
    @DataProvider(name="invalidUserCredentials")
    public static Object [][] userCredentialsDataProvider(){
        return new Object[][]{
                {"", "admin"},
                {"", ""},
                {"admin", "123456"}
        };
    }
}
