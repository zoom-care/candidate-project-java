##Daniels Readme
This file describes assumptions about this project and motivates some of the design choices.

### Project requirements:
- There is a database with tables `Employee` and `Properties`.
- Implement endpoint: Get employee by id
- Implement endpoint: Get employees by supervisor id
- Each employee returned will have its associated properties in Map form.
- Each employee also has a List<Employee> containing the supervisors.

###### Because I cannot ask questions to the team I am making some assumptions about the required functionality which are as follows.
1. Because it is not clear what `Getting a top down list of employees` means when it comes to functional requirements, I am assuming that it means getting a list of employees by their supervisor's id.
2. This part is confusing `The result for each employee should include...as well as a nested list of all direct reports for that employee` given the data model. Given the instructions and the data model, I am assuming that an employee can only have 1 supervisor, and the supervisorId would correspond to another's employeeId. In which case an employee can have have max 1 supervisorId. For the sake of this test a nested list is used to return the supervisor, but it is worth mentioning that this is bad design. For a couple reasons
    1. It is confusing from a developers point of view to use a list when the maximum number of items it can contain is 1.
    2. Creating unnecessary objects such as a list in this case is bad practice.
3. There are no requirements around logging. For this reason I am using the built in logger to java, but I would use log4j2 or some sort of logging framework in real life.
4. There are no requirements around http response codes ...
5. There are no requirements around API framework for this reason I am using jersey because I prefer it over spring.

