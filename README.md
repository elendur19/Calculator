## Staring backend

Before starting backend make sure you are correct versions of Java and Maven are used.

```bash
mvn -v
```
Make sure Java 11+ and Maven 3.x are used.

To start the backend, run following commands:

```bash
cd Calculator_backend 
./mvnw spring-boot:run
```

Make sure backend is running and responding:

```bash
curl -X POST -H 'Content-Type: application/json' --data '{"expression":"2+4*2"}' http://localhost:8080/api/calculate
```

Frontend part written in Angular using node version 17
Angular can be started from visual studio code using "ng serve --open"


# Main app window
For entered math expression there is result shown after clicking "=" button
if entered expression is invalid, error will be shown
