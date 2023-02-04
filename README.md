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
    hostname: specify the hostname here (Eg: localhost)

# Server properties
server:
  port: port in which the customer service is to run (Eg: 8095)

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
