import java.sql.Date;

public class Reservation {
    private int reservationId;
    private int hotelId;  // Added hotelId
    private int guestId;
    private int roomId;
    private Date checkIn;
    private Date checkOut;

    // Constructor
    public Reservation(int reservationId, int hotelId, int guestId, int roomId, Date checkIn, Date checkOut) {
        this.reservationId = reservationId;
        this.hotelId = hotelId;  // Initialize hotelId
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    // Getters and Setters
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getHotelId() {  // Added getter for hotelId
        return hotelId;
    }

    public void setHotelId(int hotelId) {  // Added setter for hotelId
        this.hotelId = hotelId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }
}
