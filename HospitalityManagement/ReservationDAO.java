import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    private Connection connection;

    public ReservationDAO() {
        try {
            connection = DatabaseConnector.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addReservation(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO Reservations (hotel_id, guest_id, room_id, check_in, check_out) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reservation.getHotelId());  // Added hotel_id
            stmt.setInt(2, reservation.getGuestId());
            stmt.setInt(3, reservation.getRoomId());
            stmt.setDate(4, reservation.getCheckIn());
            stmt.setDate(5, reservation.getCheckOut());
            stmt.executeUpdate();
        }
    }

    public List<Reservation> getAllReservations() throws SQLException {
        String sql = "SELECT * FROM Reservations";
        List<Reservation> reservations = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Reservation reservation = new Reservation(
                    rs.getInt("reservation_id"),
                    rs.getInt("hotel_id"),  // Added hotel_id
                    rs.getInt("guest_id"),
                    rs.getInt("room_id"),
                    rs.getDate("check_in"),
                    rs.getDate("check_out")
                );
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    public void updateReservation(Reservation reservation) throws SQLException {
        String sql = "UPDATE Reservations SET hotel_id = ?, guest_id = ?, room_id = ?, check_in = ?, check_out = ? WHERE reservation_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reservation.getHotelId());  // Added hotel_id
            stmt.setInt(2, reservation.getGuestId());
            stmt.setInt(3, reservation.getRoomId());
            stmt.setDate(4, reservation.getCheckIn());
            stmt.setDate(5, reservation.getCheckOut());
            stmt.setInt(6, reservation.getReservationId());
            stmt.executeUpdate();
        }
    }

    public void deleteReservation(int reservationId) throws SQLException {
        String sql = "DELETE FROM Reservations WHERE reservation_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reservationId);
            stmt.executeUpdate();
        }
    }
}
