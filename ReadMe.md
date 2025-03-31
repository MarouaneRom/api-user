# French Users API

## Description
This Spring Boot API allows you to:
1. Register a user.
2. Retrieve the details of a registered user.

Only adult French residents are allowed to create an account.

## Features
- Input validation for required and optional attributes.
- Embedded H2 database for easy installation and execution.
- AOP integration for logging method calls, inputs, outputs, and processing time.

## Requirements
- Java 17+
- Maven 3+
- Optional: Postman for API testing

## How to Build from Sources
1. Clone the repository:
   ```bash
   git clone https://github.com/MarouaneRom/api-user.git

2. Go to the root folder of the project:
 ```bash 
   cd french-users-api 
   ```

3. Build the project using Maven:
```bash
   mvn clean install 
   ```

4. Start the Spring Boot application:
 ```bash
   mvn spring-boot:run
```  

# How to Use the API
## Base URL
### All endpoints are available at`` http/localhost/8080``

## API Endpoints
1. ### POST /users

### Registers a new user 

#### Request body  (examples) : 

``` Json 

{
"name": "Zinedine Zidane",
"birthdate": "2000-01-01",
"country": "France",
"phoneNumber": "+33123456789",
"gender": "Male"
}
```

``` Json 

{
"id": 1,
"name": "Zinedine Zidane",
"birthdate": "2000-01-01",
"country": "France",
"phoneNumber": "+33123456789",
"gender": "Male"
}
```

#### Errors 

404 Bad Request if any required field is invalid or missing

#### Example error response:

``` Json 
{
"name": "Name is required."
}
``` 

2. ### GET /users/{id}

Retrieves details of a specific user by ID

#### Response body (example):

``` Json
{
  "id": 1,
  "name": "Zinedine Zidane",
  "birthdate": "2000-01-01",
  "country": "France",
  "phoneNumber": "+33123456789",
  "gender": "Male"
} 
``` 

#### Errors

404 Not Found if the user doesn't exist.

3. ### DELETE /users/{id}

Deletes a specific user by ID.

#### Response:

204 No Content on successful deletion

#### Errors : 

404 Not Found if the users doesn't exist.

## Database Information
The project uses an embedded H2 database for ease of testing. Access the H2 console with the following details:

### URL : http://localhost:8080/h2-console

### JDBC URL : jdbc:h2:mem:testdb

### Username : sa

### Password: (leave empty)

## Testing the API
A Postman collection is included in the repository under the folder . Follow these steps:

1. Open Postman.
2. Import the collection file French User API.postman_collection.json
3. Run the requests to interact with the API 

## Logs


All API method calls are logged using AOP :
- Inputs and outputs methods.
-  Processing time for each method. 

