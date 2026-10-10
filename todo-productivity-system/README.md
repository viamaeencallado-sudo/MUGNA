# To-Do Lists: An Essential Tool for Daily Productivity
A Java 17 / Spring Boot / Thymeleaf / MySQL starter implementation based on the submitted proposal.

## Requirements
- JDK 17 or newer
- Maven 3.9+
- MySQL 8+
- IntelliJ IDEA (or another Java IDE)

## Setup
1. Create the database:
   ```sql
   CREATE DATABASE todo_productivity;
   ```
2. Open `src/main/resources/application.properties` and set your MySQL username/password, or provide environment variables `DB_USERNAME` and `DB_PASSWORD`.
3. Open this folder in IntelliJ IDEA as a Maven project and wait for dependencies to load.
4. Run `mvn spring-boot:run`, or run `TodoProductivityApplication.java`.
5. Visit http://localhost:8080.

The application creates tables automatically for development (`ddl-auto=update`). For production, use database migrations and `validate` instead.
Default DB user is `root`; default password is blank. Change these values for your machine.

## Features
- Create, edit, delete, and complete tasks
- Categories, priority levels, due dates, and task status
- Search by title/description and filter by category, priority, or status
- Dashboard counts for pending, in-progress, completed, and overdue tasks
- On-screen reminders for tasks due within 24 hours or already overdue
- Bootstrap responsive interface
- OOP examples: encapsulation in entities, inheritance through `User` and `Student`, polymorphic task urgency descriptions, and service/controller abstraction

## Notes
- This is a local academic starter project. It has no production authentication, email/SMS notifications, or multi-user authorization yet.
- Reminders appear inside the web dashboard; they are not pushed to a device.
- Review and test the project with your instructor before submission.
