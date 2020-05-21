
<a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ"><img src="https://cdn.discordapp.com/attachments/674220525895286789/710595472062021702/Artboard_34x.png" title="RVsRUs" alt="RVs R Us"></a>

# RVs R Us Motorhome - Exam Project

> A Final Exam Project for a University 2nd Semester, Computer Science (Java)
> Project Name: Motorhome (codename RVs R Us)

Technologies Used
-
- Java 11
- SpringBoot, JPA
- Thymeleaf
- Maven
- JUnit
- HTML5 & CSS3
- mySQL, JDBC connector
- SQL Injection Prevention
- Git
- Visual Paradigm, UML (Documentation)
- Jira (Documentation, Project Management)
- IntelliJ (Development Environment)

*Due to the project requirements, certain features have been omittied:*
 - Hosted/Deployed WebApp
 - Hosted/Deployed Database
 - Security (SSL, data encryption/hashing)
 - Environment-based configuration

# Installation
*The provided source code has been written and tested in Jetbrains IntelliJ. It is not guaranteed to work as-is when imported with other IDEs*

The Web App can be run in 3 ways:
-
- Run Locally - simply compile and run the code via your IDE. The website can be accessed via `localhost:portNumber`. By Default, we web app is running on port 8080 ([localhosst:8080](localhost:8080))
- Packaged as a jar file - Run `mvn package` in the terminal to create a jar package which then can be run locally or on a server
- Deployed to a web hosting *(the exact method depends on the hosting service)*

The Web App is configured via a `application.properties` file. 
The following variables can be edited:
- Website port (the post on which the application is listening)
- Database Connection Information *(Username, password, url)*

All variables have to be configured properly in order for the application to work

License
-
The Software is released under an [MIT license](https://opensource.org/licenses/MIT)