# Spring Boot "Restful service" Example Project

This is a sample Java / Maven / Spring Boot (version 2.5.4) application that can be used for saving a fx deals using MongoDB.

## How to Run 

This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. 

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* Make sure that you have a mongoDB active on your local machine at port (27017)
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar -Dspring.profiles.active=test target\fx-deals.war
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=test"
```
* Check the stdout or logs//FxDealsDemo.log file to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2022-02-21 10:17:21 INFO  Http11NioProtocol:173 - Starting ProtocolHandler ["http-nio-8080"]
2022-02-21 10:17:21 INFO  TomcatWebServer:220 - Tomcat started on port(s): 8080 (http) with context path ''
2022-02-21 10:17:21 INFO  DocumentationPluginsBootstrapper:160 - Context refreshed
2022-02-21 10:17:21 INFO  DocumentationPluginsBootstrapper:163 - Found 1 custom documentation plugin(s)
2022-02-21 10:17:21 INFO  ApiListingReferenceScanner:41 - Scanning for api listing references
2022-02-21 10:17:22 INFO  ProgressSoftDemoApplication:61 - Started ProgressSoftDemoApplication in 4.007 seconds (JVM running for 4.818)
```

## About the Service

The service is just a simple analyze FX deals REST service. It uses a NoSql database (Mongo) to store the data. You can also do with another database like MySQL or PostgreSQL. If your database connection properties work, you can call some REST endpoints defined in ```com.progressSoft.demo.controller.FxDealsController``` on **port 8080**. (see below)

More interestingly, you can start calling some of the operational endpoints (see full list below) like ```/metrics``` and ```/health``` (these are available on **port 8080**)

 
Here is what this little application demonstrates: 

* Full integration with the latest **Spring** Framework: inversion of control, dependency injection, etc.
* Packaging as a single war with embedded container (tomcat 8): No need to install a container separately on the host just run using the ``java -jar`` command
* Demonstrates how to set up healthcheck, metrics, info, environment, etc. endpoints automatically on a configured port. Inject your own health / metrics info with a few lines of code.
* Writing a RESTful service using annotation: supports  JSON request / response; simply use desired ``Accept`` header in your request
* Exception mapping from application exceptions to the right HTTP response with exception details in the body
* Automatic CRUD functionality against the data source using Spring *Repository* pattern
* All APIs are "self-documented" by Swagger2 using annotations 

Here are some endpoints you can call:

### Get information about system health, configurations, etc.

```
http://localhost:8080/actuator/env
http://localhost:8080/actuator/health
http://localhost:8080/actuator/info
http://localhost:8080/actuator/metrics
```

### save Fx deal record

```
POST /FxDeals/saveFxDeal
Accept: application/json
Content-Type: application/json
{
  "dealAmount": 1234.23434,
  "dealTimestamp": "22-07-2022 22:54:00",
  "fromCurrencyCode": "USD",
  "id":null,
  "toCurrencyCode": "USD"
}
RESPONSE: HTTP 200 (saving)
```

### To view Swagger 2 API docs

Run the server and browse to localhost:8080/swagger-ui

# About Spring Boot

Spring Boot is an "opinionated" application bootstrapping framework that makes it easy to create new RESTful services (among other types of applications). It provides many of the usual Spring facilities that can be configured easily usually without any XML. In addition to easy set up of Spring Controllers, etc. Spring Boot comes with the Actuator module that gives the application the following endpoints helpful in monitoring and operating the service:

**/metrics** Shows “metrics” information for the current application.

**/health** Shows application health information.

**/info** Displays arbitrary application info.

**/configprops** Displays a collated list of all @ConfigurationProperties.

**/mappings** Displays a collated list of all @RequestMapping paths.

**/beans** Displays a complete list of all the Spring Beans in your application.

**/env** Exposes properties from Spring’s ConfigurableEnvironment.

**/trace** Displays trace information (by default the last few HTTP requests).

### To view your mongoDB datbase

The 'test' profile runs on H2 in-memory database. To view and query the database you can browse to http://localhost:8090/h2-console. Default username is 'sa' with a blank password. Make sure you disable this in your production profiles. For more, see https://goo.gl/U8m62X



# Questions and Comments: muh.shadid@gmail.com