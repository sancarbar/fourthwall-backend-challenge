# Fourthwall Backend Challenge
This is the implementation
of [Fourthwall Backend Coding Challenge](https://gist.github.com/wbaumann/aaa5ef095e213ffbea35b7ca3cc251a7)

Technologies used: Kotlin + Spring Boot

In order to run this project you need to provide the following environment variables:

```properties
MONGODB_URI=mongodb+srv://admin:Ibpqe4S0vfbyo15w@cluster0.zbmvj.mongodb.net/myFirstDatabase?retryWrites=true&w=majority
SECRET=The-monkey-secret_2020
OMDB_API_KEY=e4f33820
```

To run this project on your local environment run the following commnand:

```shell
  ./gradlew bootRun
```

Run unit tests:

```shell
  ./gradlew test
```

Use the following POSTMAN collection to test and verify your API:

https://www.getpostman.com/collections/0b7847b2c66e6f32be65
