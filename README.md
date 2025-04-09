# Drone Delivery Service - Java Spring Boot Application

This is a Java-based REST API for managing drones-based delivery. 
It supports registering drones, loading a drone with medication, checking loaded medications for a given drone, check drone availability for loading, and check drone information (battery).

############################

~~ Tech Stack

- Java 17
- Spring Boot
- Maven
- H2 Database
- Postman (for API testing)

############################

~~ Build Instructions

1. Clone the repository or unzip the folder
git clone https://github.com/Malabiga/TheDrone.git

2. Run the application
mvn spring-boot:run

3. For testing
.json collection file is provided

4. Database access
- http://localhost:8082/h2-console

Login 
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:drone_db
Username: sa
Password: *blank

5. Configuration
- on applicatoin.properties we can always set the duration scheduler milliseconds
or search -> drone.scheduler.fixed-rate=10000

6. Check logs to view the transactions