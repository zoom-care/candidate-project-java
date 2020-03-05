##Daniels Readme
This file describes assumptions about this project and motivates some of the design choices.

#### How to start the project.
1. Clone the project and cd to the project folder.
2. You need java installed. Run `java --version` from command line to make sure java is installed.
3. You need maven installed. Run `mvn --version` from command line to make sure maven is installed.
4. You can build the project with `mvn clean install` from the command line
5. You can run the project with `./mvnw spring-boot:run` from the command line

#### Api docs.
To get an employee by id:
`
http://localhost:8080/employee?id=<employeeId>
`

To get all toplevel employees:
`
http://localhost:8080/employee/toplevel
`

### Project requirements:
- There is a database with tables `Employee` and `Properties`.
- Implement endpoint: Get employee by id
- Implement endpoint: Get toplevel employees
- Each employee returned will have its associated properties in Map form.
- Each employee also has a List<Employee> containing the supervisors.

###### Because I cannot ask questions to the team I am making some assumptions about the required functionality which are as follows.
1. Because it is not clear what `Getting a top down list of employees` means when it comes to functional requirements, I am assuming that it means getting a list of employees who are either the CEO or have their supervisor as CEO.
2. This part is confusing `The result for each employee should include...as well as a nested list of all direct reports for that employee` given the data model. Given the instructions and the data model, I am assuming that an employee can only have 1 supervisor, and the supervisorId would correspond to another's employeeId. In which case an employee can have have max 1 supervisorId. For the sake of this test a nested list is used to return the supervisor, but it is worth mentioning that this is bad design. For a couple reasons
    1. It is confusing from a developers point of view to use a list when the maximum number of items it can contain is 1.
    2. Creating unnecessary objects such as a list in this case is bad practice.
3. There are no requirements around logging therefore I did not do any logging. I felt it wasnt entirely necessary and the expception mappers give the user an idea if something is going wrong.
4. There are no requirements around http response codes ... I just used a 404 if an employeeId does not exist and a 400 if the datatype passed as parameter was bad.
5. There are no requirements around API framework for this reason I am using jersey because I prefer it over spring.
6. I am not using the most up to date versions of junit and jersey because I am not 100% familiar with these versions and I don't want to risk wasting time on understanding particularities of the updated versions. If this were a real project however I would strive to use the most up to date versions of dependencies.
7. I am not implementing spring profiles because this is a simple project. If it were a real project I would use profiles and load application properties based on that.
8. I did not write any unit tests because doing this project took more time than I thought it was going to take and there wasn't a whole lot to test. In reality I would have at a minimum wrote unit tests for all service methods. I would have used mockito to mock the repositories for these tests.
9. The data model is not ideal. In real life I would not have implemented so many getById functions. I would have used JPA a little more to created relationships between the Employee and Properties if it were possible.
10. The exception handling is not idea. It seemed like for this case the only real thing to worry about was sending in ids that did not exist in the database and bad data through this endpoint. Therefore I wrote exception mappers for this case with the appropriate http response codes, it is not a super maintainable pattern though if this application were to grow. Alittle refactoring would be necessary.
11. The model around Employee for returning to the user is not ideal. I made ClientEmployee extend Employee so to add properties that were not supposed to be part of the data model. A better pattern could be used though.