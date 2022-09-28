# Room Occupancy Manager

## running with CLI
```./gradlew bootRun```

## running tests with CLI
```./gradlew test```

## calling endpoint when application is running

```
curl --location --request POST 'localhost:8080/api/manager/bookrooms' \
--header 'Content-Type: application/json' \
--data-raw '{
"economyRooms": 3,
"premiumRooms": 3,
"customersBudgets": [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
}'
```

## sample wrong curls and responses
* negative number of free rooms
```
curl --location --request POST 'localhost:8080/api/manager/bookrooms' \
--header 'Content-Type: application/json' \
--data-raw '{
"economyRooms": -1,
"premiumRooms": 3,
"customersBudgets": [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
}'
```

```
{
    "codes": [
        "Min.roomOccupancyRequestDto.economyRooms",
        "Min.economyRooms",
        "Min.java.lang.Integer",
        "Min"
    ],
    "arguments": [
        {
            "codes": [
                "roomOccupancyRequestDto.economyRooms",
                "economyRooms"
            ],
            "arguments": null,
            "defaultMessage": "economyRooms",
            "code": "economyRooms"
        },
        0
    ],
    "defaultMessage": "The value must be positive",
    "objectName": "roomOccupancyRequestDto",
    "field": "economyRooms",
    "rejectedValue": -1,
    "bindingFailure": false,
    "code": "Min"
}
```
* empty customer budgets section
```
curl --location --request POST 'localhost:8080/api/manager/bookrooms' \
--header 'Content-Type: application/json' \
--data-raw '{
    "economyRooms": 1,
    "premiumRooms": 3,
    "customersBudgets": []
}'
```

```
{
    "codes": [
        "NotEmpty.roomOccupancyRequestDto.customersBudgets",
        "NotEmpty.customersBudgets",
        "NotEmpty.java.util.List",
        "NotEmpty"
    ],
    "arguments": [
        {
            "codes": [
                "roomOccupancyRequestDto.customersBudgets",
                "customersBudgets"
            ],
            "arguments": null,
            "defaultMessage": "customersBudgets",
            "code": "customersBudgets"
        }
    ],
    "defaultMessage": "Customer budgets are empty",
    "objectName": "roomOccupancyRequestDto",
    "field": "customersBudgets",
    "rejectedValue": [],
    "bindingFailure": false,
    "code": "NotEmpty"
}
```

## Swagger UI access when application is running
[Swagger UI](http://localhost:8080/swagger-ui/index.html)

## App properties
response message, currency and threshold between economy and premium are customizable by editing
[application.properties](src/main/resources/application.properties) file