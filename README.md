# Public Bus Ticket Booking System

A Java-based Public Bus Ticket Booking System developed as part of the **IT314 Software Engineering** course.

The system provides a simple booking workflow for searching bus trips, selecting seats, making a simulated payment, viewing bookings, and cancelling confirmed bookings with refund calculation.

---

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
- Calculate refund based on cancellation time
- Release seats after cancellation
- Support multiple bookings

---

## Application Workflow

```text
HOME
  |
  v
BOOK TICKET
  |
  v
SELECT SOURCE
  |
  v
SELECT DESTINATION
  |
  v
SELECT BUS TRIP
  |
  v
SELECT SEATS
  |
  v
ENTER PASSENGER DETAILS
  |
  v
REVIEW BOOKING
  |
  v
PAYMENT
  |
  +-------- Payment Failed --------+
  |                                |
  |                                v
  |                         Retry / Back
  |
  +-------- Payment Successful ----+
                                   |
                                   v
                            BOOKING CONFIRMED
                                   |
                    +--------------+--------------+
                    |                             |
                    v                             v
              MY BOOKINGS                     CANCEL
                                                  |
                                                  v
                                             REFUND
