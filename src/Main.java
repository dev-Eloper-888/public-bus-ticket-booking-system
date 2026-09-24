import java.time.LocalDateTime;
import java.util.*;

public class Main {

    static List<Trip> trips = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        loadSampleData();

        while (true) {

            clearScreen();
            showMainMenu();

            char choice = readKey();

            switch (choice) {

                case '1':
                    bookTickets();
                    break;

                case '2':
                    viewBookings();
                    break;

                case '3':
                    cancelBooking();
                    break;

                case '4':
                    exitApplication();
                    return;

                default:
                    showError("Invalid choice.");
                    pause();
            }
        }
    }

    // =========================================================
    // INPUT
    // =========================================================

static char readKey() {

    try {
        int key = System.in.read();

        // Consume remaining characters from this input line
        while (System.in.available() > 0) {
            int next = System.in.read();

            if (next == '\n') {
                break;
            }
        }

        return Character.toUpperCase((char) key);

    } catch (Exception e) {
        return '\0';
    }
}
    static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                int value = scanner.nextInt();
                scanner.nextLine();

                return value;

            } catch (Exception e) {

                scanner.nextLine();
                showError("Please enter a valid number.");
            }
        }
    }

    static String readLine(String message) {

        System.out.print(message);
        return scanner.nextLine();
    }

    static void pause() {

        System.out.println();
        System.out.print("Press ENTER to continue...");
        scanner.nextLine();
    }

    // =========================================================
    // SCREEN
    // =========================================================

    static void clearScreen() {

        try {

            new ProcessBuilder(
                    "cmd",
                    "/c",
                    "cls"
            ).inheritIO().start().waitFor();

        } catch (Exception e) {

            for (int i = 0; i < 40; i++) {
                System.out.println();
            }
        }
    }

    static void header(String title) {

        System.out.println();
        System.out.println(
                "=============================================================="
        );

        System.out.println(
        " BUSBOOK     HOME   BOOK TICKET   BOOKINGS   CANCEL   EXIT"
        );

        System.out.println(
                "--------------------------------------------------------------"
        );

        System.out.println(
                "                         " + title
        );

        System.out.println(
                "=============================================================="
        );

        System.out.println();
    }

    static void showMainMenu() {

        header("HOME");

        System.out.println("[1] BOOK TICKET");
        System.out.println("[2] MY BOOKINGS");
        System.out.println("[3] CANCEL BOOKING");
        System.out.println("[4] EXIT");

        System.out.println();
        System.out.print("Select: ");
    }

    static void showError(String message) {

        System.out.println();
        System.out.println("[!] " + message);
    }

    // =========================================================
    // SAMPLE DATA
    // =========================================================

    static void loadSampleData() {

    Bus bus1 = new Bus("B101", BusType.AC_SEATER, 40);
    Bus bus2 = new Bus("B102", BusType.AC_SLEEPER, 30);
    Bus bus3 = new Bus("B103", BusType.NON_AC, 40);

    // Ahmedabad -> Mumbai
    trips.add(new Trip(
            "T01",
            "Ahmedabad",
            "Mumbai",
            "Vadodara, Surat",
            LocalDateTime.now().plusHours(30),
            bus1,
            800
    ));

    // Ahmedabad -> Rajkot
    trips.add(new Trip(
            "T02",
            "Ahmedabad",
            "Rajkot",
            "Limbdi",
            LocalDateTime.now().plusHours(35),
            bus2,
            700
    ));

    // Vadodara -> Mumbai
    trips.add(new Trip(
            "T03",
            "Vadodara",
            "Mumbai",
            "Bharuch, Surat",
            LocalDateTime.now().plusHours(40),
            bus3,
            650
    ));

    // Vadodara -> Gandhinagar
    trips.add(new Trip(
            "T04",
            "Vadodara",
            "Gandhinagar",
            "Anand",
            LocalDateTime.now().plusHours(25),
            bus1,
            500
    ));

    // Surat -> Mumbai
    trips.add(new Trip(
            "T05",
            "Surat",
            "Mumbai",
            "Vapi",
            LocalDateTime.now().plusHours(45),
            bus2,
            900
    ));

    // Surat -> Rajkot
    trips.add(new Trip(
            "T06",
            "Surat",
            "Rajkot",
            "Bharuch, Ahmedabad",
            LocalDateTime.now().plusHours(50),
            bus3,
            750
    ));
}

    // =========================================================
    // SOURCE SELECTION
    // =========================================================

    static List<String> getSources() {

        Set<String> sources =
                new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

        for (Trip trip : trips) {
            sources.add(trip.getSource());
        }

        return new ArrayList<>(sources);
    }

    static String selectSource() {

        clearScreen();
        header("SELECT SOURCE");

        List<String> sources = getSources();

        for (int i = 0; i < sources.size(); i++) {

            System.out.println(
                    "[" + (i + 1) + "] " + sources.get(i)
            );
        }

        System.out.println();
        System.out.println("[B] BACK");
        System.out.print("\nSelect: ");

        char key = readKey();

        if (key == 'B') {
            return null;
        }

        int choice = key - '0';

        if (choice >= 1 && choice <= sources.size()) {

            return sources.get(choice - 1);
        }

        showError("Invalid selection.");
        pause();

        return null;
    }

    // =========================================================
    // DESTINATION SELECTION
    // =========================================================

    static List<String> getDestinations(String source) {

        Set<String> destinations =
                new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

        for (Trip trip : trips) {

            if (trip.getSource().equalsIgnoreCase(source)) {

                destinations.add(
                        trip.getDestination()
                );
            }
        }

        return new ArrayList<>(destinations);
    }

    static String selectDestination(String source) {

        clearScreen();
        header("SELECT DESTINATION");

        System.out.println("From: " + source);
        System.out.println();

        List<String> destinations =
                getDestinations(source);

        for (int i = 0; i < destinations.size(); i++) {

            System.out.println(
                    "[" + (i + 1) + "] "
                    + destinations.get(i)
            );
        }

        System.out.println();
        System.out.println("[B] BACK");
        System.out.print("\nSelect: ");

        char key = readKey();

        if (key == 'B') {
            return null;
        }

        int choice = key - '0';

        if (choice >= 1 && choice <= destinations.size()) {

            return destinations.get(choice - 1);
        }

        showError("Invalid selection.");
        pause();

        return null;
    }

    // =========================================================
    // BUS RESULTS
    // =========================================================

    static Trip selectTrip(
            String source,
            String destination) {

        clearScreen();
        header("AVAILABLE BUSES");

        System.out.println(
                source + "  →  " + destination
        );

        System.out.println();

        List<Trip> results = new ArrayList<>();

        for (Trip trip : trips) {

            if (trip.getSource().equalsIgnoreCase(source)
                    && trip.getDestination()
                    .equalsIgnoreCase(destination)) {

                results.add(trip);
            }
        }

        results.sort(
                Comparator.comparing(
                        Trip::getDepartureDateTime
                )
        );

        if (results.isEmpty()) {

            System.out.println("No buses found.");
            pause();

            return null;
        }

        for (int i = 0; i < results.size(); i++) {

            Trip trip = results.get(i);

            System.out.println(
                    "----------------------------------------------------------"
            );

            System.out.println(
                    "[" + (i + 1) + "] "
                    + trip.getTripId()
                    + "   "
                    + trip.getBus().getBusNumber()
            );

            System.out.println(
                    "    Type      : "
                    + trip.getBus().getBusType()
            );

            System.out.println(
                    "    Via       : "
                    + trip.getVia()
            );

            System.out.println(
                    "    Departure : "
                    + trip.getDepartureDateTime()
            );

            System.out.println(
                    "    Price     : Rs."
                    + trip.getPricePerSeat()
                    + " / seat"
            );
        }

        System.out.println(
                "----------------------------------------------------------"
        );

        System.out.println();
        System.out.println("[B] BACK");
        System.out.print("\nSelect bus: ");

        char key = readKey();

        if (key == 'B') {
            return null;
        }

        int choice = key - '0';

        if (choice >= 1 && choice <= results.size()) {

            return results.get(choice - 1);
        }

        showError("Invalid bus selection.");
        pause();

        return null;
    }

    // =========================================================
    // BOOK TICKETS
    // =========================================================

    static void bookTickets() {

        // SOURCE

        String source = selectSource();

        if (source == null) {
            return;
        }

        // DESTINATION

        String destination =
                selectDestination(source);

        if (destination == null) {
            return;
        }

        // BUS

        Trip trip =
                selectTrip(source, destination);

        if (trip == null) {
            return;
        }

        // SEATS

        Set<Integer> seats =
                selectSeats(trip);

        if (seats == null || seats.isEmpty()) {
            return;
        }

        // PASSENGER

        clearScreen();
        header("PASSENGER DETAILS");

        System.out.println(
                "Selected Seats: " + seats
        );

        System.out.println(
                "Total: Rs."
                + (seats.size()
                * trip.getPricePerSeat())
        );

        System.out.println();

        String name =
                readLine("Name : ");

        String phone =
                readLine("Phone: ");

        Customer customer =
                new Customer(
                        "C" + (bookings.size() + 1),
                        name,
                        phone
                );

        Booking booking =
                new Booking(
                        "BK" + (bookings.size() + 1),
                        customer,
                        trip,
                        seats
                );

        // REVIEW

        clearScreen();
        header("REVIEW BOOKING");

        System.out.println(
                "Booking ID : "
                + booking.getBookingId()
        );

        System.out.println(
                "Passenger  : "
                + name
        );

        System.out.println(
                "Phone      : "
                + phone
        );

        System.out.println(
                "Route      : "
                + source
                + " → "
                + destination
        );

        System.out.println(
                "Bus        : "
                + trip.getBus().getBusNumber()
        );

        System.out.println(
                "Type       : "
                + trip.getBus().getBusType()
        );

        System.out.println(
                "Via        : "
                + trip.getVia()
        );

        System.out.println(
                "Seats      : "
                + seats
        );

        System.out.println(
                "Total      : Rs."
                + booking.getTotalAmount()
        );

        System.out.println();
        System.out.println("[1] PROCEED TO PAYMENT");
        System.out.println("[B] BACK");
        System.out.print("\nSelect: ");

        char reviewChoice = readKey();

        if (reviewChoice == 'B') {
            return;
        }

        if (reviewChoice != '1') {

            showError("Invalid choice.");
            pause();

            return;
        }

        // PAYMENT

        processPayment(booking);
    }

    // =========================================================
    // SEAT SELECTION
    // =========================================================

    static Set<Integer> selectSeats(Trip trip) {

    while (true) {

        clearScreen();
        header("SELECT SEATS");

        System.out.println(
                "Bus   : " + trip.getBus().getBusNumber()
        );

        System.out.println(
                "Type  : " + trip.getBus().getBusType()
        );

        System.out.println(
                "Price : Rs." + trip.getPricePerSeat() + " / seat"
        );

        System.out.println();

        System.out.println("                         FRONT");
        System.out.println();

        Set<Integer> available =
                trip.getAvailableSeats();

        for (int i = 1;
             i <= trip.getBus().getSeatCount();
             i++) {

            if (available.contains(i)) {
                System.out.printf("[%02d] ", i);
            } else {
                System.out.print("[XX] ");
            }

            if (i % 4 == 0) {
                System.out.println();
            }
        }

        System.out.println();

        System.out.println("----------------------------------------------");
        System.out.println("Available seats: " + available);
        System.out.println();
        System.out.println("Enter seat numbers separated by spaces.");
        System.out.println("Example: 3 4");
        System.out.println("Example: 10 11");
        System.out.println();
        System.out.println("[B] BACK");
        System.out.print("Seats: ");

        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("B")) {
            return null;
        }

        Set<Integer> seats = new LinkedHashSet<>();

        try {

            String[] values = input.split("\\s+");

            for (String value : values) {

                int seat = Integer.parseInt(value);

                if (!trip.isSeatAvailable(seat)) {

                    showError(
                            "Seat " + seat +
                            " is invalid or already booked."
                    );

                    pause();
                    seats.clear();
                    break;
                }

                seats.add(seat);
            }

            if (seats.isEmpty()) {
                continue;
            }

            // Show selected seats before continuing
            clearScreen();
            header("SEATS SELECTED");

            System.out.println(
                    "Bus: " + trip.getBus().getBusNumber()
            );

            System.out.println();

            System.out.println(
                    "Selected Seats : " + seats
            );

            System.out.println(
                    "Number of Seats: " + seats.size()
            );

            System.out.println(
                    "Price per Seat : Rs."
                    + trip.getPricePerSeat()
            );

            System.out.println(
                    "Total Amount   : Rs."
                    + (seats.size()
                    * trip.getPricePerSeat())
            );

            System.out.println();

            System.out.println("[1] CONTINUE");
            System.out.println("[2] CHANGE SEATS");
            System.out.println("[B] BACK");

            System.out.print("\nSelect: ");

            char choice = readKey();

            if (choice == '1') {
                return seats;
            }

            if (choice == '2') {
                continue;
            }

            if (choice == 'B') {
                return null;
            }

        } catch (NumberFormatException e) {

            showError(
                    "Please enter valid seat numbers."
            );

            pause();
        }
    }
}

    // =========================================================
    // PAYMENT
    // =========================================================

    static void processPayment(
            Booking booking) {

        clearScreen();
        header("PAYMENT");

        System.out.println(
                "Booking ID : "
                + booking.getBookingId()
        );

        System.out.println(
                "Amount     : Rs."
                + booking.getTotalAmount()
        );

        System.out.println();

        System.out.println("[1] UPI");
        System.out.println("[2] CARD");
        System.out.println("[3] NET BANKING");

        System.out.println();

        System.out.println(
                "[B] BACK"
        );

        System.out.print(
                "\nPayment Method: "
        );

        char method = readKey();

        if (method == 'B') {
            return;
        }

        if (method < '1' || method > '3') {

            showError(
                    "Invalid payment method."
            );

            pause();
            return;
        }

        clearScreen();
        header("PAYMENT");

        System.out.println(
                "Amount: Rs."
                + booking.getTotalAmount()
        );

        System.out.println();

        System.out.println(
                "[1] PAY SUCCESSFULLY"
        );

        System.out.println(
                "[2] SIMULATE PAYMENT FAILURE"
        );

        System.out.println();

        System.out.println(
                "[B] BACK"
        );

        System.out.print(
                "\nSelect: "
        );

        char choice = readKey();

        if (choice == 'B') {
            return;
        }

        boolean success;

        if (choice == '1') {

            success = true;

        } else if (choice == '2') {

            success = false;

        } else {

            showError(
                    "Invalid choice."
            );

            pause();
            return;
        }

        boolean confirmed =
                booking.confirm(success);

        if (confirmed) {

            bookings.add(booking);

            showConfirmation(
                    booking
            );

        } else {

            clearScreen();
            header("PAYMENT FAILED");

            System.out.println(
                    "Payment failed."
            );

            System.out.println(
                    "Your selected seats have been released."
            );

            System.out.println();

            System.out.println(
                    "[1] TRY AGAIN"
            );

            System.out.println(
                    "[2] HOME"
            );

            System.out.print(
                    "\nSelect: "
            );

            char retry = readKey();

            if (retry == '1') {

                processPayment(booking);

            }
        }
    }

    // =========================================================
    // CONFIRMATION
    // =========================================================

    static void showConfirmation(
            Booking booking) {

        clearScreen();
        header("BOOKING CONFIRMED");

        System.out.println(
                "              ✓ PAYMENT SUCCESSFUL"
        );

        System.out.println();

        System.out.println(
                booking.getBookingDetails()
        );

        System.out.println();

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "[1] MY BOOKINGS"
        );

        System.out.println(
                "[2] BOOK ANOTHER TICKET"
        );

        System.out.println(
                "[3] HOME"
        );

        System.out.println();

        System.out.print(
                "Select: "
        );

        char choice = readKey();

        switch (choice) {

            case '1':
                viewBookings();
                break;

            case '2':
                bookTickets();
                break;

            default:
                return;
        }
    }

    // =========================================================
    // MY BOOKINGS
    // =========================================================

    static void viewBookings() {

        clearScreen();
        header("MY BOOKINGS");

        if (bookings.isEmpty()) {

            System.out.println(
                    "No bookings found."
            );

            pause();
            return;
        }

        for (Booking booking : bookings) {

            System.out.println(
                    "----------------------------------------------------------"
            );

            System.out.println(
                    booking.getBookingDetails()
            );
        }

        System.out.println(
                "----------------------------------------------------------"
        );

        System.out.println();
        System.out.println("[B] BACK");

        readKey();
    }

    // =========================================================
    // CANCEL BOOKING
    // =========================================================

    static void cancelBooking() {

        clearScreen();
        header("CANCEL BOOKING");

        if (bookings.isEmpty()) {

            System.out.println(
                    "No bookings found."
            );

            pause();
            return;
        }

        for (Booking booking : bookings) {

            System.out.println(
                    booking.getBookingId()
                    + " | "
                    + booking.getTrip().getSource()
                    + " → "
                    + booking.getTrip().getDestination()
                    + " | "
                    + booking.getStatus()
            );
        }

        System.out.println();
        System.out.println(
                "Enter Booking ID:"
        );

        String bookingId =
                scanner.nextLine().trim();

        for (Booking booking : bookings) {

            if (booking.getBookingId()
                    .equalsIgnoreCase(bookingId)) {

                if (booking.getStatus()
                        != BookingStatus.CONFIRMED) {

                    showError(
                            "This booking cannot be cancelled."
                    );

                    pause();
                    return;
                }

                double refund =
                        booking.cancel();

                clearScreen();
                header("BOOKING CANCELLED");

                System.out.println(
        "Booking ID : "
        + booking.getBookingId()
);

System.out.println(
        "Status     : CANCELLED"
);

System.out.println();

System.out.println(
        "Refund Amount : Rs."
        + refund
);

System.out.println();

System.out.println(
        "This amount will be refunded to your"
);

System.out.println(
        "original payment method."
);

pause();

                return;
            }
        }

        showError(
                "Booking ID not found."
        );

        pause();
    }

    // =========================================================
    // EXIT
    // =========================================================

    static void exitApplication() {

        clearScreen();

        System.out.println();
        System.out.println(
                "=============================================================="
        );

        System.out.println(
                "        Thank you for using BUSBOOK!"
        );

        System.out.println(
                "=============================================================="
        );

        System.out.println();
    }
}