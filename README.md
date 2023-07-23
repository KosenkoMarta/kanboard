# Kanboard
## Need install
Java 17, Maven, Docker Desktop
Copy the docker-compose.yml file and run the command $ docker compose up on the folder with the file

## Run test
Your can run test without changing code using system property or profiles in console
* mvn clean test -Papi-regression - for running all API tests
* mvn clean test -Pui-regression -Dbrowser=chrome -Dheadless=false - for running all UI tests in Chrome
* mvn clean test -Pui-regression -Dbrowser=chrome -Dheadless=true - for running all UI tests in Chrome headless mode
* mvn clean test -Pui-regression -Dbrowser=firefox -Dheadless=false - for running all UI tests in Firefox
* mvn clean test -Ptask -Dbrowser=chrome -Dheadless=false

## Run the commands to get Allure report
* allure generate target/allure-results
* allure serve target/allure-results



