import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

public class Booking {

    private String bookingId;
    private Customer customer;
    private Trip trip;
    private Set<Integer> selectedSeats;
    private double totalAmount;
    private BookingStatus status;
    private Payment payment;

    public Booking(
            String bookingId,
            Customer customer,
            Trip trip,
            Set<Integer> selectedSeats) {

        this.bookingId = bookingId;
        this.customer = customer;
        this.trip = trip;
        this.selectedSeats = selectedSeats;
        this.totalAmount = calculateTotal();
        this.status = BookingStatus.PENDING;

        this.payment = new Payment(
                "PAY-" + bookingId,
                totalAmount
        );
    }

    public double calculateTotal() {
        return selectedSeats.size() * trip.getPricePerSeat();
    }

    public boolean confirm(boolean paymentSuccess) {

        if (status != BookingStatus.PENDING) {
            return false;
        }

        // Reserve the selected seats first.
        if (!trip.reserveSeats(selectedSeats)) {
            return false;
        }

        // Process simulated payment.
        boolean paymentResult =
                payment.processPayment(paymentSuccess);

        // If payment fails, release the seats.
        if (!paymentResult) {
            trip.releaseSeats(selectedSeats);
            return false;
        }

        status = BookingStatus.CONFIRMED;
        return true;
    }

    public double cancel() {

        if (status != BookingStatus.CONFIRMED) {
            return 0.0;
        }

        LocalDateTime now = LocalDateTime.now();

        long hoursRemaining =
                Duration.between(
                        now,
                        trip.getDepartureDateTime()
                ).toHours();

        double refundPercentage;

        if (hoursRemaining > 24) {
            refundPercentage = 1.0;
        } else if (hoursRemaining >= 12) {
            refundPercentage = 0.5;
        } else {
            refundPercentage = 0.0;
        }

        double refund =
                totalAmount * refundPercentage;

        trip.releaseSeats(selectedSeats);

        status = BookingStatus.CANCELLED;

        return refund;
    }

    public String getBookingDetails() {

        return
                "Booking ID: " + bookingId +
                "\nCustomer: " + customer.getName() +
                "\nTrip ID: " + trip.getTripId() +
                "\nSource: " + trip.getSource() +
                "\nDestination: " + trip.getDestination() +
                "\nVia: " + trip.getVia() +
                "\nSeats: " + selectedSeats +
                "\nTotal Amount: Rs." + totalAmount +
                "\nStatus: " + status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public Set<Integer> getSelectedSeats() {
        return selectedSeats;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Payment getPayment() {
        return payment;
    }

    public Trip getTrip() {
        return trip;
    }

    public Customer getCustomer() {
        return customer;
    }
}