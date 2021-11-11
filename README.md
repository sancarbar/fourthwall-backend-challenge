# Fourthwall Backend Challenge
This is the implementation
of [Fourthwall Backend Coding Challenge](https://gist.github.com/wbaumann/aaa5ef095e213ffbea35b7ca3cc251a7)

Technologies used: Kotlin + Spring Boot

The REST API was secured using JWT.

Use the following credentials to access the Cinema Owner endpoint to modify movie times:
```json
{"email":"santiago@mail.com", "password":"passw0rd"}
```
Use the following credentials to only be able to view the movie times and movie's information:
```json
{"email":"andres@mail.com", "password":"passw0rd"}
```

In order to run this project you need to provide the following environment variables:

* MONGODB_URI
* SECRET
* OMDB_API_KEY

To run this project on your local environment use the following command:

```shell
  ./gradlew bootRun --args='--app.secret=This-is-the-less-secret-secret2020 --spring.data.mongodb.uri=mongodb+srv://admin:Ibpqe4S0vfbyo15w@cluster0.zbmvj.mongodb.net/myFirstDatabase?retryWrites=true&w=majority --app.omdb_api_key=e4f33820'
```

Run unit tests:

```shell
  ./gradlew test
```

Use the following POSTMAN collection to test and verify the API:

https://www.getpostman.com/collections/0b7847b2c66e6f32be65
