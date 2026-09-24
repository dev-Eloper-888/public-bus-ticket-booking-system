public class Bus {

    private String busNumber;
    private BusType busType;
    private int seatCount;

    public Bus(String busNumber, BusType busType, int seatCount) {
        this.busNumber = busNumber;
        this.busType = busType;
        this.seatCount = seatCount;
    }

    public boolean isValidSeat(int seatNumber) {
        return seatNumber >= 1 && seatNumber <= seatCount;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public BusType getBusType() {
        return busType;
    }
}