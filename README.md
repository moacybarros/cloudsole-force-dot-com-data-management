# Spring MVC REST API Data Management Application

    Try it now: http://cloudsole.herokuapp.com

CloudSole is your Force.com cloud console to query, create, update and delete any record in your Salesforce org. This web application uses Spring MVC, Force.com REST API.

## Running the application locally

Setup OAuth Remote Access in Salesforce.com

    1. Go to Salesforce.com's Setup page
    2. Go to Develop -> Remote Access
    3. Add a new Remote Access config with a URL of: `http://localhost:8080/_auth`

Add environment variables for authenticating to Salesforce.com (replace the values with the ones from the Remote Access definition on Salesforce.com):

- On Linux/Mac:

        $ export OAUTH_CLIENT_KEY=3MVM3_GuVCQ3gmEE5al72RmBfiAWhBX5O2wYc9zTZ8ytj1E3NF7grV_G99OxTyEcY71Tc46TOvzK_rzoyYYPk
        $ export OAUTH_CLIENT_SECRET=1319558946720906100
        $ export EMAIL_PASSWORD=youremailpassword

- On Windows:

        $ set OAUTH_CLIENT_KEY=3MVM3_GuVCQ3gmEE5al72RmBfiAWhBX5O2wYc9zTZ8ytj1E3NF7grV_G99OxTyEcY71Tc46TOvzK_rzoyYYPk
        $ set OAUTH_CLIENT_SECRET=1319558946720906100
        $ set EMAIL_PASSWORD=youremailpassword

Build with:

    $ mvn clean install

Then run it with:

    $ java -jar target/dependency/webapp-runner.jar target/*.war

Screenshots:
    ![ScreenShot](https://github.com/thysmichels/cloudsole-force-dot-com-data-management/raw/master/img/CloudSole%20Force.com%20Data%20Management.png "Login Screen")
    ![ScreenShot](https://github.com/thysmichels/cloudsole-force-dot-com-data-management/raw/master/img/CloudSole%20Data%20Management%202.png "SOQL Query Screen")
    ![ScreenShot](https://github.com/thysmichels/cloudsole-force-dot-com-data-management/raw/master/img/CloudSole%20Data%20Management%203.png "Table SOQL Query Screen")
    ![ScreenShot](https://github.com/thysmichels/cloudsole-force-dot-com-data-management/raw/master/img/CloudSole%20Data%20Management%205.png "Edit Screen")



