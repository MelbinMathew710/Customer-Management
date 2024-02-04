# Customer-Management - Spring Boot Project Readme


This project is a Customer Management System implemented in Spring Boot using MySQL database.


## Table of Contents
1. [How to Run the Project](#how-to-run-the-project)
2. [Setup the Database](#setup-the-database)
3. [Hosted URL](#hosted-url)
4. [API Documentation](#api-documentation)

---


## How to Run the Project

### Prerequisites
- Java Development Kit (JDK) installed (version 8 or later)
- Apache Maven installed
- MySQL DB installed and running

### Steps
1. Clone the repository:
   
   git clone https://github.com/your-username/Customer-Management.git
   cd Customer-Management

2. Run the application:

   a.	Open the project in an Integrated Development Environment (IDE) of your choice, such as IntelliJ IDEA or Eclipse.
   b.	Configure MySQL connection:
   c.	Open the application.properties file in the project's src/main/resources directory.
   d.	Set the MySQL connection properties:
   e. spring.datasource.url=jdbc:mysql://localhost:3306/your-database-name ?createTableIfNotExists=true
      spring.datasource.username= your-username
      spring.datasource.password= your-password 
      spring.jpa.hibernate.ddl-auto=update
   f.	Replace your-username, your-password, and your-database-name with the appropriate values for your MySQL setup.
   g.	Build the project:
   h.	Using Maven: Open a terminal or command prompt, navigate to the project's root directory, and run the following command:
   i.	mvn clean package 
   j.	Run the project:
   k.	Using Maven: In the same terminal or command prompt, run the following command:
   l.	arduinoCopy code
   m.	mvn spring-boot:run 
   n.	The Spring Boot application will start and establish a connection with the MySQL database.
   

4. The application will start, and you can access it at http://localhost:8080.

---


## API Documentation

### 1. Create Customer

- Endpoint: `POST /api/customers/createCustomer`
- Input JSON Structure:
  
{
  "first_name": "Jane",
  "last_name": "Doe",
  "street": "Elvnu Street",
  "address": "H no 2 ",
  "city": "Delhi",
  "state": "Delhi",
  "email": "sam@gmail.com",
  "phone": "12345678"
}

Output:
HTTP Status Code: 201 Created
Response Body: CustomerResponseDTO


### 2. Update Customer by ID
Endpoint: PUT /api/customers/updateCustomer/{id}
Input JSON Structure:

{
  "firstName": "Updated Name",
  "lastName": "Doe",
  "street": "Updated Street",
  "address": "Updated Address",
  "city": "Delhi",
  "state": "Delhi",
  "email": "updated@gmail.com",
  "phone": "9876543210"
}
Output:
HTTP Status Code: 200 OK
Response Body: Success message


### 3. Get All Customers
Endpoint: GET /api/customers/getAllCustomers
Query Parameters: pageNo, pageSize, sortBy, search
Output:
HTTP Status Code: 200 OK
Response Body: List of CustomerResponseDTO


### 4. Get Customer by ID
Endpoint: GET /api/customers/getCustomerById/{id}
Path Parameter: id
Output:
HTTP Status Code: 200 OK
Response Body: CustomerResponseDTO


### 5. Delete Customer by ID
Endpoint: DELETE /api/customers/deleteCustomer/{id}
Path Parameter: id
Output:
HTTP Status Code: 200 OK
Response Body: Success message
