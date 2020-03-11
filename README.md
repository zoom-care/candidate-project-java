# This is an in progress entry for the ZOOM+CARE coding challenge.

## Current defficencies
Things left to work on 

1. The endpoints should be locked down so that they aren't all open by default. 
2. The health status endpoint should likely be changed to only show status not details. 
3. Mockito need to be written to exercise the Employee class and controller
4. Additional data to test edge cases or invalid/partial data should be added to the database.

## Usage
The endpoint /API/V2/EMPLOYEE was created to serve as an RESTFUL API interface, which serves JSON objects.  Ideally another service would be created with a front end (maybe NodeJS/React stack) which would query this API.

## Example use cases

http://localhost:8080/API/V2/EMPLOYEE 
This returns the top level employees.  In this case it is only the CEO.

http://localhost:8080/API/V2/EMPLOYEE?id=2
This returns a direct report of the CEO.

http://localhost:8080/API/V2/EMPLOYEE?id=cat
For any input to the ID parameter which is not a valid long, we return the top level employees.

http://localhost:8080/API/V2/EMPLOYEE?id=0
This returns no results, since a specific employee number was asked for and that employee does not exist.  This could instead return the top level employees.

## Exercise summary
This was the first time I've used the Sprint-Boot framework.  There may be more efficent way to implement this service using spring addons I'm not aware of yet.  The current API as described could be more efficent under a NoSQL database, since it appears that we're always accessing the entire 'document' rather than in a more relational manner.  IF this were to be momre than a one-off I would have implemented a database abstraction layer, rather than writing such a tightly coupled solution.  I recognise this may not be the most reusable code, but I would argue it's easy to maintian due to its brevity.  It also has a benefit of only reaching out to the DB ONCE per API call, rather than reaching out to get all the employees, then all their properties, then all their direct reports.
