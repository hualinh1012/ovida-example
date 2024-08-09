# Getting Started

## Reference Documentation
This is a Java and Spring Boot example as demand of Ovida.org


## Guides
The following guides to run and test the application:

### Manually install and run

Installing Prerequisites: 
    
    Install JDK 21 
    Install Mysql 8

Install database

    Install mysql instance by your self and create an "example" database
    Use data_and_structure.sql to create tables and and data set
    Use data.sql to create data set

Run steps:

    Option 1: Run inside IDE
    Open project with IntelliJ and run application from the main class


    Option 2:
    - Navigate to project folder
    - Build file jar: ./mvnw install 
    - Change directory to jar file localtion: cd target
    - Run application: java -jar springboot-example-0.0.1-SNAPSHOT.jar

### With Docker

Installing Prerequisites:

    Install Docker

Run step

    docker compose up --build

Note: first time docker compose up always fail because it take some time for mysql initialization

Data restore:

    Use data.sql to create data set

### APIs testing

Application starts on port 8080. You can use these CURL commands to quickly test it

The application uses basic authentication. And, you must provide it in the header

`Get current user`

    curl --location 'localhost:8080/users/me' \
    --header 'Authorization: Basic bGluaC5odWE6MTIzNDU2'

`Get all user`

    curl --location 'localhost:8080/users/me' \
    --header 'Authorization: Basic bGluaC5odWE6MTIzNDU2'
    
`Get all roles`

    curl --location 'localhost:8080/roles' \
    --header 'Authorization: Basic bGluaC5odWE6MTIzNDU2' 

`Add permission to role`

    curl --location --request PUT 'localhost:8080/roles/3/permissions/1' \
    --header 'Authorization: Basic bGluaC5odWE6MTIzNDU2'

`Remove permission from role`

    curl --location --request DELETE 'localhost:8080/roles/3/permissions/1' \
    --header 'Authorization: Basic bGluaC5odWE6MTIzNDU2'   


### Database structure 

users and roles will have many-to-many relationship
roles and permissions will have many-to-many relationship

![ DBeaver ERD](DBeaver_ERD.png)
![ DrawIO ERD](DrawIO_ERD.png)