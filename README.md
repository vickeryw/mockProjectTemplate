# mockProjectTemplate

## Background info
This was an opportunity to use some technology we've not used before in our project to much capacity with the aim of improving our mocks.
A small list of technologies used to improve our mocks:
+ JUnit Jupiter
+ Lombok
+ Spring Actuator
+ Spring Admin
+ Google fluent assertions

## JUnit Jupiter
This is the new version of JUnit, this is to speed up our test exectuion. You can use Jupiter to run tests in parallel. Jupiter is also backwards compatabile with JUnit 4. The dependency is JUnit Jupiter Vintage.

## Lombok
A list of Lombok features: https://projectlombok.org/features/all
We've used it to easily create getters, setters, constructors and handle exceptions being thrown. We also use Slf4j annotation at class level so we don't have to initialise a logger each class.

## Spring actuator
We've used this to monitor the health of our system as well  as monitor the HTTP traces of our REST API endpoints.

## Spring Admin
We connect to Spring Admin, this displays everything from actuator and the logs of our application.

## Google Fluent assertions
These are just nicer assertions than JUnit. They can be used in conjunction.

## How to build
This is built via maven. In the pom file, you'll see some docker set up. If you run 'mvn clean install -Ddocker', maven will build you a docker image and tag it as latest and with whatever the version of the pom is.

## How to run
This is just like an other spring boot project. It can be run via the main method on a tomcat server. However, if you checkout the docker project, you can run this mock project with  docker-compose.
