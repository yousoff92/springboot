# REST CRUD Service

## Introduction 
1. This a sample of Spring Boot application for REST CRUD service.
2. This sample does not use any JPA/Hibernate implementation.
## Prequisite 
1. Installed Java JDK 1.8.x
2. Installed MySQL 5.x.x and create a local database connection.
3. Installed Apache Maven 3.3.x
4. (Optional) Installed any preferred IDE. Recommended to use Eclipse 4.7.x (Oxygen).
## Getting Started 
To view available request API :
1. In IDE, run `MainApplication.java` and open this link in a new browser `http://localhost:8080/swagger-ui.html`
2. It will display list of requests in Swagger UI.

To run unit test :
1. In IDE, run JUnit Test for this class `ControllerTest`

To run unit testing with reporting
1. Open CMD in project directory, run `surefire-report:report site -DgenerateReports=false`