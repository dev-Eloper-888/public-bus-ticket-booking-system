import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Trip {

    private String tripId;
    private String source;
    private String destination;
    private String via;
    private LocalDateTime departureDateTime;
    private Bus bus;
    private double pricePerSeat;
    private Set<Integer> bookedSeats;

    public Trip(
            String tripId,
            String source,
            String destination,
            String via,
            LocalDateTime departureDateTime,
            Bus bus,
            double pricePerSeat) {

        this.tripId = tripId;
        this.source = source;
        this.destination = destination;
        this.via = via;
        this.departureDateTime = departureDateTime;
        this.bus = bus;
        this.pricePerSeat = pricePerSeat;
        this.bookedSeats = new HashSet<>();
    }

    public Set<Integer> getAvailableSeats() {

        Set<Integer> availableSeats = new HashSet<>();

        for (int i = 1; i <= bus.getSeatCount(); i++) {
            if (!bookedSeats.contains(i)) {
                availableSeats.add(i);
            }
        }

        return availableSeats;
    }

    public boolean isSeatAvailable(int seatNumber) {

        return bus.isValidSeat(seatNumber)
                && !bookedSeats.contains(seatNumber);
    }

    public boolean reserveSeats(Set<Integer> seats) {

        for (int seat : seats) {
            if (!isSeatAvailable(seat)) {
                return false;
            }
        }

        bookedSeats.addAll(seats);
        return true;
    }

    public void releaseSeats(Set<Integer> seats) {
        bookedSeats.removeAll(seats);
    }

    public String getTripId() {
        return tripId;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getVia() {
        return via;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public Bus getBus() {
        return bus;
    }

    public double getPricePerSeat() {
        return pricePerSeat;
    }
}