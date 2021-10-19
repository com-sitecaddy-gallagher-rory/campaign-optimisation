Campaign Group Optimisation Application
========================================

This repository contains an application which is an implementation of a system that takes a group of campaigns, associated within a campaign group, 
and performs an optimisation on them based on some criteria.

This project is setup as a Spring Boot REST API. You should be able to run this as a spring boot app as it should include its own server instance.

Once the project is started you can view the API Docs via the swagger UI at: http://localhost:8080/swagger-ui.html#/

**NOTES**:

**Before starting the app**:
There is an MySQL database which will need to be configured in the application.properties to input user name and password.

There are 2 sql dump files which can be used to create a blank database or one which will contain sample data.
The sample data can be imported to the database with a command as follows:
>> mysql -u username -p campaign_groups < campaign_groups_dump.sql

If the database is blank at startup then a sample Campaign Group will be created and the campaigns in the csv file will be imported. This file is located in the resources folder.

**Using the App**
Once the app is running you can view the Groups with a call the Rest Endpoints as shown in the swagger documentation (see link above)
- View all campaign groups 
  http://localhost:8080/api/group   or   curl -X GET --header 'Accept: application/json' 'http://localhost:8080/api/group'
- View all campaigns for a campaign group (through a call to campaign)
  http://localhost:8080/api/campaign/group/30 
  or curl -X GET --header 'Accept: application/json' 'http://localhost:8080/api/campaign/group/30'
- View latest optimisations for a campaign group (through a call to optimisation)
  This will display any optimisations persisted to the database which have a status = "proposed" . The status can get updated to "applied" when that action is taken.
  http://localhost:8080/api/optimisation/view/{groupId} 
  or curl -X GET --header 'Accept: application/json' 'http://localhost:8080/api/optimisation/view/{groupId}'
- Create an optisation without persisting it. i.e. this is just a preview of an optimisation on the group.
  http://localhost:8080/api/optimisation/create/{groupId}
  or curl -X GET --header 'Accept: application/json' 'http://localhost:8080/api/optimisation/create/{groupId}'
- Create and Persist an optimisation for a group - this will save the optimisation to the database with a status of "proposed"
  http://localhost:8080/api/optimisation/save/{groupId} 
  or curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' 'http://localhost:8080/api/optimisation/save/{groupId}'
- View latest recommendations for an optimisation (through a call to recommendation)
  http://localhost:8080/api/recommendation/8 
  or curl -X GET --header 'Accept: application/json' 'http://localhost:8080/api/recommendation/8'
- Apply / accept latest optimisation / recommendations to a campaign group / campaigns
  http://localhost:8080/api/optimisation/apply/{groupId}
  or curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' 'http://localhost:8080/api/optimisation/apply/{groupId}'

**Further work**
There are a few things that I would have liked to try but I didnt really have the time:
  - Add/Update the logic, complete the API to provide more functionality for each resource.
  - Run the app with Docker as well as a MySQL container to prevent end users needing to have a MySql instance
  - Add more tests
  - Add security
  - Add logging / metrics
