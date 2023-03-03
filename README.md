# Shop Service

Service for handling all shop related operations

### Required External Services

- Authentication-Authorization Service

### Depends on

- MongoDB instance

## APIs

| Functionality | REST Endpoint | Parameter | Body | Response |
| --- | --- | --- | --- | --- |
| Get Information of Shop | **GET** `/user/shop` | email - String |     | JSON String |
| Get Information of List of Shops | **GET** `/user/shop/listShops` | shopIds - String\[\] |     | JSON String |
| Add New Shop | **POST** `/user/shop` |     | JSON String | JSON String |
| Edit Shop | **PUT** `/user/shop` |     | JSON String | JSON String |
| Disable Shop | **DELETE** `/user/shop` | email - String |     | JSON String |
| Enable Shop | \*\*PATCH \*\*`/user/shop` | email - String |     | JSON String |

## Configuration

Edit the properties of **application.yml** file

```yaml
# Eureka properties
eureka:
  client:
    fetch-registry: true
    register-with-eureka: true
    service-url:
      defaultZone: address of the eureka server (Eg: http://localhost:8761/eureka)
  instance:
    hostname: specify the hostname of service here (Eg: localhost)

# Server properties
server:
  port: port in which the shop service is to be run (Eg: 8095)

# Application properties
spring:
  application:
    name: name of the application (Eg: SHOP-SERVICE)
# MongoDB properties
  data:
    mongodb:
      database: mongoDB database name (Eg: testWorkingDB)
      host: name of mongoDB host (Eg: localhost)
      port: port in which mongoDB is being run (Eg: 27017)
```

## Local Deployment

Service Registry should be started for successful execution of all queries.

In application.yml file, change the properties

| Property | Value | Example |
| --- | --- | --- |
| eureka_hostname | hostname of eureka server | service-registry |
| service_hostname | hostname of service (try to use the same as in docker-compose) | shop-service |
| mongodb_hostname | hostname of mongodb | shop-db |
| mongodb\_database\_name | database name | shopDB |

### Using Docker

Create docker bridge network: `docker network create -d bridge pigihi-network`

docker-compose can be used to run the application and the corresponding mongodb instance

1.  Go to project folder
2.  Open terminal and run `docker-compose up`
3.  The application can be accessed at localhost:8095 (port 8095 is set in docker-compose)
4.  MongoDB port is set to 27022

To run only the application

1.  Go to project folder
2.  Open terminal and run `docker build .`
3.  Run `docker run -p 8095:8095 docker_image_name`
4.  The application can be accessed at localhost:8095

### Using Gradle

MongoDB should be run seperately and the configurations should be updated in application.yml

1.  Go to project folder
2.  Open terminal and run `./gradlew build`
3.  Run `./gradlew bootRun`

* * *
