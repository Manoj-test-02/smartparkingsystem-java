# Smart Parking Lot System

A REST API for managing a multi-floor parking facility. The application is built with Java and Spring Boot and uses Spring Data JPA with an in-memory H2 database.

The project focuses on core parking-lot workflows: creating floors and parking slots, assigning a compatible available slot when a vehicle enters, generating an entry ticket, calculating the parking fee when the vehicle exits, and making the slot available again.

## Features

- Create parking floors and retrieve the configured floors.
- Create parking slots associated with an existing floor.
- Support three vehicle types: `BIKE`, `CAR`, and `BUS`.
- Find an available slot that matches the incoming vehicle type.
- Create an active parking ticket when a vehicle enters.
- Calculate a fee using a strategy-based fee calculator.
- Complete the ticket and release the parking slot when a vehicle exits.
- Validate the license plate and return readable validation errors.
- Handle common application exceptions centrally.
- Use an H2 in-memory database for local development.

## Technology Stack

- **Language:** Java 17
- **Framework:** Spring Boot 4.1.1
- **Build tool:** Gradle
- **Persistence:** Spring Data JPA / Hibernate
- **Database:** H2 in-memory database
- **Validation:** Jakarta Bean Validation
- **API testing:** Bruno, Postman, or another HTTP client

## Project Structure

The Gradle project is inside the `ParkingLotSystem/` directory.

```text
smartparkingsystem-java/
├── README.md
└── ParkingLotSystem/
    ├── build.gradle
    ├── settings.gradle
    ├── gradlew
    ├── gradlew.bat
    ├── gradle/
    │   └── wrapper/
    └── src/
        ├── main/
        │   ├── java/
        │   │   └── com/airtribe/ParkingLotSystem/
        │   │       ├── ParkingLotSystemApplication.java
        │   │       ├── controller/
        │   │       │   ├── CentralExceptionController.java
        │   │       │   ├── ParkingFloorController.java
        │   │       │   ├── ParkingSlotController.java
        │   │       │   └── ParkingTicketController.java
        │   │       ├── dto/
        │   │       │   ├── EntryDTO.java
        │   │       │   ├── EntryTicketDTO.java
        │   │       │   ├── ExitTicketDTO.java
        │   │       │   └── ParkingSlotDTO.java
        │   │       ├── exception/
        │   │       │   ├── ParkingFloorNotFoundException.java
        │   │       │   ├── ParkingSlotNotAvailableException.java
        │   │       │   └── VehicleNotFoundException.java
        │   │       ├── model/
        │   │       │   ├── ParkingFloor.java
        │   │       │   ├── ParkingSlot.java
        │   │       │   ├── ParkingStatus.java
        │   │       │   ├── ParkingTicket.java
        │   │       │   └── VehicleType.java
        │   │       ├── repository/
        │   │       │   ├── ParkingFloorRepository.java
        │   │       │   ├── ParkingSlotRepository.java
        │   │       │   └── ParkingTicketRepository.java
        │   │       ├── service/
        │   │       │   ├── ParkingFloorService.java
        │   │       │   ├── ParkingSlotService.java
        │   │       │   └── ParkingTicketService.java
        │   │       └── util/
        │   │           ├── FeeStrategy.java
        │   │           ├── ParkingFee.java
        │   │           ├── ParkingSlotMap.java
        │   │           └── StandardFee.java
        │   └── resources/
        │       └── application.properties
        └── test/
            └── java/com/airtribe/ParkingLotSystem/
                └── ParkingLotSystemApplicationTests.java
```

### Package responsibilities

| Package | Responsibility |
|---|---|
| `controller` | Exposes REST endpoints and maps HTTP requests to service methods. `CentralExceptionController` translates handled exceptions into HTTP responses. |
| `dto` | Defines request and response objects used by the entry, exit, and slot APIs. |
| `exception` | Contains custom exceptions for missing floors, unavailable slots, and vehicles that do not have an active ticket. |
| `model` | Contains JPA entities and enums representing floors, slots, tickets, vehicle types, and ticket statuses. |
| `repository` | Provides Spring Data JPA access to floors, slots, and tickets. |
| `service` | Implements floor, slot, entry, and exit business logic. |
| `util` | Contains slot-selection logic and the parking-fee strategy abstraction and implementation. |

## Prerequisites

- JDK 17 or later (the Gradle build targets Java 17).
- A terminal or IDE such as IntelliJ IDEA.
- Bruno or another REST client to call the endpoints.

## Run the Application

1. Clone or download this repository.
2. Open a terminal in the `ParkingLotSystem/` directory.
3. Run the application using the Gradle wrapper.

**Windows (PowerShell or Command Prompt):**

```powershell
.\gradlew.bat bootRun
```

**macOS / Linux:**

```bash
./gradlew bootRun
```

The application is configured to run at:

```text
http://localhost:1000
```

To run the tests:

**Windows**
```powershell
.\gradlew.bat test
```

**macOS / Linux**
```bash
./gradlew test
```

## Database Configuration

The current configuration is in `src/main/resources/application.properties`:

```properties
spring.application.name=ParkingLotSystem
server.port=1000

spring.datasource.url=jdbc:h2:mem:ParkingSystem
spring.datasource.username=user
spring.datasource.password=user
spring.datasource.driver-class-name=org.h2.Driver

spring.jpa.show-sql=true
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

H2 console URL:

```text
http://localhost:1000/h2-console
```

Use these connection details in the console:

| Setting | Value |
|---|---|
| JDBC URL | `jdbc:h2:mem:ParkingSystem` |
| User Name | `user` |
| Password | `user` |

**Note:** This is an in-memory database. Data is temporary and is normally lost when the application stops or restarts.

## API Reference

All examples assume the application is running on `http://localhost:1000`. Send and receive JSON unless otherwise noted.

### 1. Create a parking floor

- **Method:** `POST`
- **Endpoint:** `/api/addnewfloors`

Example request:

```json
{
  "floorNumber": 1,
  "parkingLotId": 1,
  "floorAvailability": true
}
```

`floorId` is generated by the application, so it should not be supplied when creating a floor.

### 2. Get all parking floors

- **Method:** `GET`
- **Endpoint:** `/availablefloors`

Returns the configured parking floors. If no floors are present, the service throws `ParkingFloorNotFoundException`.

### 3. Add a parking slot

- **Method:** `POST`
- **Endpoint:** `/api/addparkingslots`

Create the floor first and use its generated `floorId`.

Example request:

```json
{
  "floorId": 1,
  "slotNumber": "C001",
  "vehicleType": "CAR"
}
```

Supported `vehicleType` values: `BIKE`, `CAR`, `BUS`.

The service checks that the referenced floor exists, creates the slot, and initially marks it as available. In the current implementation, `parked: true` means the slot is available; the field becomes `false` when the slot is assigned to an entering vehicle.

### 4. Vehicle entry / reserve a parking spot

- **Method:** `POST`
- **Endpoint:** `/api/entry`

Example request:

```json
{
  "licensePlate": "TN01AB1234",
  "vehicleType": "CAR"
}
```

The license plate must contain 4–15 uppercase letters, digits, or hyphens, according to the current validation pattern. The service selects the first available slot whose vehicle type matches the request and creates an `ACTIVE` ticket.

The response is an `EntryTicketDTO` containing the ticket ID, license plate, and entry time. If no compatible slot is available, the service throws `ParkingSlotNotAvailableException`.

### 5. Vehicle exit / release a parking spot

- **Method:** `PUT`
- **Endpoint:** `/api/exits/{licenseplate}`

Example:

```http
PUT http://localhost:1000/api/exits/TN01AB1234
```

The service finds the active ticket for the license plate, calculates the fee, records the exit time, changes the ticket status to `COMPLETED`, and marks the associated slot available again.

The response is an `ExitTicketDTO` containing the ticket ID, license plate, entry time, exit time, and total fee. If no active ticket is found for that license plate, the service throws `VehicleNotFoundException`.

## Parking Fee Rules

The current `StandardFee` implementation charges by the started hour. A partial hour is rounded up to a full billable hour, with a minimum charge of one hour.

| Vehicle type | Rate per billable hour |
|---|---:|
| `BIKE` | 20 |
| `CAR` | 30 |
| `BUS` | 50 |

For example, a car parked for 61 minutes is charged for 2 billable hours: `2 × 30 = 60`.

The rates are defined in the `VehicleType` enum. `FeeStrategy` defines the fee-calculation contract, `StandardFee` implements the calculation, and `ParkingFee` delegates to the configured strategy.

## Ticket Status and Slot Allocation

- A newly created entry ticket has status `ACTIVE`.
- After a successful exit, the ticket status becomes `COMPLETED`.
- A slot is initially available (`parked = true`), becomes occupied (`parked = false`) on entry, and becomes available again on exit.
- The slot-selection utility chooses the first available slot matching the requested vehicle type. It does not currently search for a larger compatible slot or rank slots by floor.

## Error Handling

`CentralExceptionController` handles the following exceptions:

| Exception | Current HTTP status | Purpose |
|---|---:|---|
| `ParkingFloorNotFoundException` | 404 | No parking floor found, or the requested floor ID does not exist. |
| `VehicleNotFoundException` | 404 | No active parking ticket found for the supplied license plate. |
| `ParkingSlotNotAvailableException` | 404 | No available slot matches the requested vehicle type. |
| `MethodArgumentNotValidException` | 400 | Request DTO validation failed; response contains field-level messages. |
| `IllegalArgumentException` | 400 | An argument is invalid; response contains the exception message. |

Example validation response:

```json
{
  "licensePlate": "License Should not be Empty"
}
```

