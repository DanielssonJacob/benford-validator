# benford-validiator

## Endpoints:

| Method | Path                             | Description                    |
|--------|----------------------------------|--------------------------------|
| POST   | /v1/distribution/benford/analyze | Analyze text for Benford's Law |

| Endpoint                               | Request                                                 | Response:                                                                                                                                                                                                                                                                         |
|----------------------------------------|---------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| POST: /v1/distribution/benford/analyze | {"text": "1 2 3 4 5 6 7 8 9","significanceLevel": 0.05} | {"expectedDistribution":{"1":0.301,"2":0.176,"3":0.125,"4":0.097,"5":0.079,"6":0.067,"7":0.058,"8":0.051,"9":0.046},"actualDistribution":{"1":0.111,"2":0.111,"3":0.111,"4":0.111,"5":0.111,"6":0.111,"7":0.111,"8":0.111,"9":0.111},"chiSquareValue":3.61,"isSignificant":false} 

## Building & Running

To build or run the project, use one of the following tasks:

| Task                      | Description                                                          |
|---------------------------|----------------------------------------------------------------------|
| `./gradlew test`          | Run the tests                                                        |
| `./gradlew build`         | Build everything                                                     |
| `./gradlew buildFatJar`   | Build an executable JAR of the server with all dependencies included |
| `./gradlew run`           | Run the server                                                       |
| `./gradlew spotlessApply` | Formats source code according to Spotless rules                      |

## Docker

To build the Docker image:

```sh
docker build -t benford-validator .
```

To run service in a container:

```sh
docker run -p 8080:8080 benford-validator
```