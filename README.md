# FOOD DELIVERY API 
## _Register_ functionality 


> User Story:
> 
> As a User I want to be able to register to the app, so I can use the food delivery service.

>Functionalities:
>
####Functionality 1. 

User should submit the following fields in order to successfully register to FoodDelivery app:
 - username
 - password
 - fullname
 - address
 - city
 - state
 - zip
 - phone
 
 Once user has been successfully registered, all submitted information should be saved in food_delivery_db schema in DB and 
 following tables: custom_user, user_profile. Write a query to fetch username,password,fullname,address,city,state,zip,phone from custom_user and user_profile tables.
 
 -----------
 
 See following example to test manually:
 
 Allowed HTTPs request to register a user:
  
  **`POST`**
 
 API Endpoint: 
 ```json
  http://ec2-18-218-51-74.us-east-2.compute.amazonaws.com:8080/user/register
 ```
 Request body:
 ```json
{
	"username":"John",
	"password": "Test123",
	"fullName": "John Doe",
	"address": "123 main st",
	"city": "Chicago",
	"state": "IL",
	"zip": "60625",
	"phone": "112131321"
}
```
##### Scenario 1:
```gherkin
Given user registers to food delivery app with the following fields:
|username|password| fullName|  address  |  city |state| zip |phone    |
|John    |Test123 |John Doe |123 main st|Chicago| IL  |60625|112131321|
Then verify that status code is 200
Then verify that response message is "User successfully registered"
Then verify that user information successfully saved in DB
```
Example response body: 
```json
{
"status": "User successfully registered"
}
```
####Functionality 2. 

User tries to register with existing username.
And user should get corresponding error message "User already exist".
And User data should not be stored in DB

##### Scenario 1:
```gherkin
Given user registers to food delivery app with an existing username:
Then verify that status code is 400
Then verify that response message is "User successfully registered"
Then verify that user information successfully saved in DB
```
Example response body: 
```json
{
"status": "User already exist"
}
```

####Functionality 3. 

User tries to register without one or more fields.
And user should get corresponding error messages based on the missing field.
And User data should not be stored in DB

##### Scenario 1:
```gherkin
Given user registers to food delivery app without username
Then verify that status code is 400
Then verify that response message is "Username is required"
Then verify that user information is not saved in DB

```
Example response body: 
```json
{
"status": "Username is required"
}
```
##### Scenario 2:
```gherkin
Given user registers to food delivery app without password
Then verify that status code is 400
Then verify that response message is "Password is required"
Then verify that user information is not saved in DB

```
Example response body: 
```json
{
"status": "Password is required"
}
```
##### Scenario 3:
```gherkin
Given user registers to food delivery app without fullname
Then verify that status code is 400
Then verify that response message is "Full name is required"
Then verify that user information is not saved in DB

```
Example response body: 
```json
{
"status": "Full name is required"
}
```
##### Scenario 4:
```gherkin
Given user registers to food delivery app without address
Then verify that status code is 400
Then verify that response message is "Address is required"
Then verify that user information is not saved in DB

```
Example response body: 
```json
{
"status": "Address is required"
}
```
##### Scenario 5:
```gherkin
Given user registers to food delivery app without city
Then verify that status code is 400
Then verify that response message is "City is required"
Then verify that user information is not saved in DB

```
Example response body: 
```json
{
"status": "City is required"
}
```
##### Scenario 6:
```gherkin
Given user registers to food delivery app without state
Then verify that status code is 400
Then verify that response message is "State is required"
Then verify that user information is not saved in DB

```
Example response body: 
```json
{
"status": "State is required"
}
```
##### Scenario 7:
```gherkin
Given user registers to food delivery app without zip code
Then verify that status code is 400
Then verify that response message is "Zip code is required"
Then verify that user information is not saved in DB

```
Example response body: 
```json
{
"status": "Zip code is required"
}
```
##### Scenario 8:
```gherkin
Given user registers to food delivery app without phone
Then verify that status code is 400
Then verify that response message is "Phone is required"
Then verify that user information is not saved in DB

```
Example response body: 
```json
{
"status": "Phone is required"
}
```