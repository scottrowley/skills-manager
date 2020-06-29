# Skills Manager
A small spring application for managing user skills. Due to limited time, it's still far
from production ready.

This is a Spring Boot based application providing the capability to manage users, and skills.

## API
This is a very brief summary of the API endpoints available for use. Ideally this would
be documented using something like redoc.
### Users
```
POST /person
{
  "forename": "John",
  "surname": "Doe"
}

PUT /person/{id}
{
  "forename": "John",
  "surname": "Doe"
}

GET /person/{id}

GET /person

DELETE /person/{id}
```

### Skills
```
POST /skill
{
  "name": "Java"
}

PUT /skill/{id}
{
  "name": "C#"
}

GET /skill/{id}

GET /skill

DELETE /skill/{id}
```

### Register a skill proficiency
```
POST /person/{id}/skill 
{
  "skillId": 123,
  "proficiency": "WORKING"
}

Where valid proficiencies are AWARENESS, WORKING, PRACTITIONER, EXPERT
```

## Running locally
To build and run this application locally you will require:
* Maven 3
* MySql
* Java 8

First, start mysql and create the necessary database and permissions:
```
create database <name>;
create user '<user>'@'%' identified by '<password>';
grant all on <name>.* to '<user>'@'%';
```
replacing `<user>`, `<name>`, and `<password>` with your desired values.
Next, modify the values in `application.properties` to match your database details.

You should now be able to build and run the application through the IDE or command line.

## Notes
Unfortunately due to time constraints the functionality of this application is quite limited.
While you can perform CRUD operations for skills and users and create register skill proficiencies against users,
I have not yet added more useful queries such as viewing all users with a skill, or viewing all skills of a user.