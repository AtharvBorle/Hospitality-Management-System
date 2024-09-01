import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {
    private Connection connection;

    public GuestDAO() {
        try {
            connection = DatabaseConnector.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGuest(Guest guest) throws SQLException {
        String sql = "INSERT INTO Guests (name, email, phone) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, guest.getName());
            stmt.setString(2, guest.getEmail());
            stmt.setString(3, guest.getPhone());
            stmt.executeUpdate();
        }
    }

    public List<Guest> getAllGuests() throws SQLException {
        String sql = "SELECT * FROM Guests";
        List<Guest> guests = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Guest guest = new Guest(
                    rs.getInt("guest_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone")
                );
                guests.add(guest);
            }
        }
        return guests;
    }

    public void updateGuest(Guest guest) throws SQLException {
        String sql = "UPDATE Guests SET name = ?, email = ?, phone = ? WHERE guest_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, guest.getName());
            stmt.setString(2, guest.getEmail());
            stmt.setString(3, guest.getPhone());
            stmt.setInt(4, guest.getGuestId());
            stmt.executeUpdate();
        }
    }

    public void deleteGuest(int guestId) throws SQLException {
        String sql = "DELETE FROM Guests WHERE guest_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, guestId);
            stmt.executeUpdate();
        }
    }
}
