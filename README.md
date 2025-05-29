# Asteroids - Microservice

Run the SpringBoot Application from the directory `ScoringService`

From root directory:

    cd ScoringService
    mvn spring-boot:run

In another terminal, from the root directory, the game can be build and run using the following commands:

    mvn clean install
    mvn exex:exec

Provided REST Endpoints:

| Method |Path  | Example | Description |
|--|--|--|--|
|GET| /score | http://localhost:8080/score?points=10 | Adds points to the current score |
|GET| /current| http://localhost:8080/current | Returns the current total score |
|GET| /reset | http://localhost:8080/reset | Resets the score to 0 |
