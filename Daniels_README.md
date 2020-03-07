## Daniels Readme
This file how to start/run the project. It describes assumptions about this project and motivates some of the design choices.

#### How to start the project.
1. Clone the project and cd to the project folder.
2. You need java installed. Run `java --version` from command line to make sure java is installed.
3. You need maven installed. Run `mvn --version` from command line to make sure maven is installed.
4. You can build the project with `mvn clean install` from the command line
5. You can run the project with `./mvnw spring-boot:run` from the command line
6. You can use postman to create http requests to call the service https://www.postman.com/

#### Project requirements as I understood them:
- Use embeded database with tables `Employee` and `Properties`.
- Implement endpoint: Get employee by id
- Implement endpoint: Get toplevel employees
- Each employee returned will have its associated properties in Map form.
- Each employee also has a List<Employee> containing the supervisors.

## Api docs.
#### To get an employee by id:
`
http://localhost:8080/employee?id=<employeeId>
`

#### To get all toplevel employees:
`
http://localhost:8080/employee/toplevel
`

#### Response codes
`200` - Resource is found and no exceptions are thrown.

`400` - If a non numeric string is sent in to retrieve an employee.

## Comments

1. Because it is not clear what `Getting a top down list of employees` means when it comes to functional requirements, I am assuming that it means getting a list of employees who are either the CEO or have their supervisor as CEO.
2. This part is confusing `The result for each employee should include...as well as a nested list of all direct reports for that employee` given the data model. Given the instructions and the data model, I am assuming that an employee can only have 1 supervisor, and the supervisorId would correspond to another's employeeId. In which case an employee can have have max 1 supervisorId. For the sake of this test a nested list is used to return the supervisor, but it is worth mentioning that this is bad design. For a couple reasons
    1. It is confusing from a developers point of view to use a list when the maximum number of items it can contain is 1.
    2. Creating unnecessary objects such as a list in this case is bad practice.
3. There are no requirements around logging therefore I did not do any logging. I felt it wasnt entirely necessary and the expception mappers give the user an idea if something is going wrong. In addition because the project is small and can run entirely locally, using the debugger is quite useful when running unit tests and integration tests. On a real project I would configure log4j2 or something similar. 
4. It sounded like usage of spring is preferred so I used spring for the dependency injection but chose to use Jersey for the API because I slightly prefer it over spring for this use case.
5. I am not using the most up to date versions of some of the dependencies because I am not 100% familiar with these versions and I don't want to risk wasting time on understanding particularities of the updated versions. If this were a real project however I would strive to use the most up to date versions of dependencies.
6. I am not implementing spring profiles because this is a simple project. If it were a real project I would use profiles and load application properties based on that.

## Weaknesses
1. The data model is not ideal. In real life I would not have implemented so many getById functions. I would have used JPA a little more to created relationships between the Employee and Properties if it were possible. An option would be to make every employee have a List\<Property> instead of a hashmap, then you could use @OneToMany annotation that JPA offers. But I did not do that in order to follow instructions.
2. The exception handling is not ideal. It seemed like for this case the only real thing to worry about was sending in ids that did not exist in the database and bad data through this endpoint. Therefore I wrote exception mappers for this case with the appropriate http response codes, it is not a super maintainable pattern though if this application were to grow. If it were to grow, then a little refactoring would be necessary.
3. The model around Employee for returning to the user is not ideal. I made ClientEmployee extend Employee so to add properties that were not supposed to be part of the data model. A better pattern could be used though.
4. The setters in ClientEmployee are not ideal. This makes the object mutable. For the sake of integration testing I did this, but i would strive for a pattern without setters in a real project. Maybe using @JsonProperty or somthing similar.

## Strengths
1. This demonstrates many elements of a full blown enterprise level microservice.
2. The architecture in general is quite scalable disregarding some database and data model quirks.
3. This project meets the requirements and goes above and beyond what was asked for in the instructions. This project demonstrates a solid understanding of spring, how to use jersey in conjunction with a springboot project and how to use spring/JPA for creating a data model and database repositories, how to use junit/mockito for unit testing and also how to use RestTemplate for doing integration testsing against an embedded H2 db.