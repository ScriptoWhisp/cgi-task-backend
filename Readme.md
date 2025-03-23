# Flight Booking and Seat Recommendation Backend

This repository contains the backend for the Flight Booking and Seat Recommendation application. It is built using Spring Boot and the latest Java LTS version.

This is the backend part of the project. To start the whole application first read instructions, setup and start the frontend part [cgi-task-frontend](https://gitlab.cs.ttu.ee/datjul/cgi-task-frontend)

## Overview

The backend provides RESTful APIs for:
- **Flight Search and Filtering:** Users can search for flights by destination, departure (city/airport), departure date (time is ignored), and price range.
- **Seat Recommendation:** After selecting a flight, the system recommends airplane seats based on user preferences such as extra legroom, window seat, near exit, and group seating (adjacent seats).
- **Random Flight Generation:** A feature to generate random flights along with associated airplanes and seats. Seats are generated with various properties (price, extra legroom) and randomly marked as booked.

## Technologies Used

### Backend

- **Java 21**
- **Gradle**
- **Spring Boot**
#### Libraries
- **Liquibase**
- **MapStruct**
- **Validation**
- **Lombok**

### Frontend

- **Node.js**
- **Vue.js**

### Database

- **PostgreSQL**

## Requirements (For backend)

- **Java 21**
- **Docker Compose**
- **PostgreSQL**
- **Gradle** (Used to install dependencies and build the project)


## Project Structure

- **Domain:** Contains entity classes (`FlightEntity`, `AirplaneEntity`, `SeatEntity`).
- **DTOs and Mappers:** Data Transfer Objects and mapping interfaces (using MapStruct).
- **Repositories:** Spring Data JPA repositories for CRUD operations.
- **Services:** Business logic for flight search, seat recommendation, and random flight generation.
- **Controllers:** REST controllers exposing endpoints:
    - **/api/flights** for flight search and filtering.
    - **/api/seat-schemas** for seat recommendation (returns a 2D array of SeatDto).
    - **/api/generate/flights** for generating random flights.

## How to Run

For this application you need PostgreSQL database up and running, for this in the project directory located docker compose file.
Use

  ```bash
  docker compose up -d
  ```

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/ScriptoWhisp/cgi-task-backend.git
   cd cgi-task-backend
   ```


## Build the Project:

Using Gradle:

```
./gradlew build

```
## Run the Application:

```
./gradlew bootRun
```

The application will start on http://localhost:8080.

## Run Using IntelliJ IDEA (second option)

Open the Project:

Launch IntelliJ IDEA.
Click on Open and select the project directory.
Run the Application:

Navigate to the Application class.
Click the Run button or press Shift + F10 (^ + R on mac).

## API Endpoints:

    Flight Search:
    GET /api/flights
    Query parameters: destination, departure, departureTime (date), priceLow, priceHigh, pageNo, pageSize.

    Seat Recommendation:
    GET /api/seat-schemas
    Query parameters: airplaneId and seat preferences (price, extraLegroom, nearWindow, nearExit, seatsCount).

    Random Flight Generation:
    POST /api/generate/flights?count=3
    Generates random flights along with airplanes and seats.

## Challenges and Assumptions

- Date Filtering: Flight departure time is compared by date only (ignoring time) using a range query.

- Seat Recommendation Logic: The engine accounts for various preferences and searches for consecutive seats when multiple tickets are purchased.

- Random Data Generation: Random flights include randomly generated airplanes and seats (with dynamic properties and random bookings).

- Assumptions: For simplicity, some calculations (e.g., seat price based on row) are kept basic.

## Time Spent and References

Time Spent: Approximately 8 hours.
References: Some ideas and code snippets were inspired by online resources (e.g., SpringBoot, Vue.js, MapStruct docs).


