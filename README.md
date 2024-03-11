This project demonstrates multiple features of the microservices architecture that are used in real world. This project consists of 4 different microservices.

i. API Gateway Microservice.

ii. College Departments Microservice.

iii. Students Microservice.

iv. Eureka Client Microservice.

The API Gateway Microservice acts as a gateway application between the end user and the other microservices.

The Eureka Client Microservice acts as a gateway application between the end user and the other microservices.

The Students Microservice performs multiple CRUD operations by connecting with the Student MySQL database.

The College Departments Microservice performs multiple CRUD operations by connecting with the College Department MySQL database and the Teacher MySQL database.

Spring boot Microservices features covered in this project are:

1. Implementation of Eureka Load balancer.
2. Implementation of API Gateway to hide the microservices from the end user.
3. Resilience4j circuitbreaker with fallback mechanism.
4. Resilience4j retry with fallback mechanism.
5. Spring profile implementation.
6. Implementation of Transaction management in microservices.
7. Spring security with basic authorization implementation.
8. Spring boot Logging implementation using logback-spring.xml with ConsoleAppender and FileAppender.
9. Global exception handling.
10. JUnit testing with Mockito framework.
11. Spring cache.
12. Spring Aspect Oriented Programming.
13. Asynchronous calls implementation.
14. Swagger UI implementation.
15. Actuator implementation.
16. Logging the unique correlation id for each requests.
17. Defining constants for code optimization and reusability.

Below is the architecture of this college management system.


![College management system architecture Microservices](https://github.com/balaji-krishna/Microservices_College_Management_System/assets/126441628/0a3cc3b0-b3a5-4317-90d0-4eb34933e714)
