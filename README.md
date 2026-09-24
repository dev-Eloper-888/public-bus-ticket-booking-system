# Public Bus Ticket Booking System

A Java-based Public Bus Ticket Booking System developed for IT314 – Software Engineering. The system demonstrates an object-oriented approach to bus trip search, seat selection, passenger booking, simulated payment, booking management, cancellation, and refund calculation.

## Features

- Search bus trips by source and destination
- View available bus trips
- View bus type, route, departure time, and fare
- Select one or multiple seats
- Prevent invalid and already-booked seat selection
- Calculate total booking amount
- Simulated payment processing
- Handle successful and failed payments
- Confirm successful bookings
- View existing bookings
- Cancel confirmed bookings
- Calculate cancellation refunds
- Release seats after cancellation
- Support multiple bookings

## Application Workflow

HOME → BOOK TICKET → SOURCE → DESTINATION → BUS TRIP → SEATS → PASSENGER DETAILS → REVIEW → PAYMENT → CONFIRMATION

If payment fails, the user can retry or return.

After successful payment, the booking is added to the user's bookings and can later be viewed or cancelled.

## System Design

The system uses five main classes and three enumerations.

### Classes

| Class | Responsibility |
|---|---|
| Bus | Stores bus information and validates seat numbers |
| Trip | Represents a bus trip and manages trip-specific seat availability |
| Customer | Stores passenger information |
| Booking | Manages seats, booking status, confirmation, cancellation, and refunds |
| Payment | Handles simulated payment processing and payment status |

### Enumerations

| Enum | Values |
|---|---|
| BusType | AC_SLEEPER, AC_SEATER, NON_AC |
| BookingStatus | PENDING, CONFIRMED, CANCELLED |
| PaymentStatus | SUCCESS, FAILED |

## Class Relationships

- One Bus can operate multiple Trips.
- One Customer can make multiple Bookings.
- One Trip can have multiple Bookings.
- Each Booking has one Payment.

## Project Structure

public-bus-ticket-booking-system/
├── src/
│   ├── Main.java
│   ├── Bus.java
│   ├── Trip.java
│   ├── Customer.java
│   ├── Booking.java
│   ├── Payment.java
│   ├── BusType.java
│   ├── BookingStatus.java
│   └── PaymentStatus.java
│
├── Docs/
│   ├── 202401138_Lab7_Report.pdf
│   ├── 202401138_Lab7_TestResults.pdf
│   └── 202401138_Lab7_RunInstructions.txt
│
├── TestcaseScreenshots/
│   ├── tc1.png
│   ├── tc2.png
│   ├── tc3.png
│   ├── tc4.png
│   ├── tc5.png
│   ├── tc6.png
│   └── tc7.png
│
├── uml/
│   └── uml_diagram.png
│
├── .gitignore
└── README.md

## Technologies

- Java
- Object-Oriented Programming
- Java Collections
- Java Date and Time API
- Git
- GitHub

## Requirements

- Java 21 or later recommended

## Running the Project

Clone the repository and enter the project directory.

Compile the Java source files from the src directory:

    javac *.java

Run the application:

    java Main

## Booking Process

1. Select Book Ticket
2. Select source
3. Select destination
4. Select a bus trip
5. View available seats
6. Select seats
7. Enter passenger details
8. Review booking
9. Select payment method
10. Process simulated payment
11. Receive booking confirmation

## Seat Management

Seats are represented using integer seat numbers.

Seat availability is maintained separately for each trip.

When a booking is confirmed, the selected seats are reserved.

If payment fails, the temporarily reserved seats are released.

When a confirmed booking is cancelled, its seats are released again.

## Payment

Payment processing is simulated.

Supported payment methods:

- UPI
- Card
- Net Banking

The system handles both successful and failed payment scenarios.

A failed payment does not create a confirmed booking and the selected seats are released.

No real financial transaction is performed.

## Cancellation and Refund

Confirmed bookings can be cancelled.

The current refund policy is:

| Time Before Departure | Refund |
|---|---:|
| More than 24 hours | 100% |
| 12–24 hours | 50% |
| Less than 12 hours | 0% |

Refund processing is simulated and does not perform an actual financial transaction.

## Testing

The project contains seven test cases:

| Test Case | Description |
|---|---|
| TC01 | Successful booking |
| TC02 | Invalid / duplicate seat selection |
| TC03 | Payment failure and seat release |
| TC04 | Cancellation above 24 hours |
| TC05 | Cancellation between 12 and 24 hours |
| TC06 | Cancellation below 12 hours |
| TC07 | Multiple bookings |

Test screenshots are available in the TestcaseScreenshots directory.

Complete test results are available in Docs/202401138_Lab7_TestResults.pdf.

## Documentation

- 202401138_Lab7_Report.pdf – Software Engineering design report
- 202401138_Lab7_TestResults.pdf – Test case results and screenshots
- 202401138_Lab7_RunInstructions.txt – Compilation and execution instructions
- uml_diagram.png – UML class diagram

## Current Scope

This project is designed as a lightweight Java application demonstrating the core public bus ticket booking workflow and object-oriented design.

The following components are currently simulated:

- Payment processing
- Refund processing
- Booking persistence

Bookings are maintained in memory during program execution.

## Future Improvements

- Specific-date trip selection
- Improved time-dependent cancellation testing
- More realistic trip scheduling
- Persistent booking storage
- Enhanced user interface

## Student

Dev Parmar

Student ID: 202401138

Course: IT314 – Software Engineering

## Project Status

Development in progress
