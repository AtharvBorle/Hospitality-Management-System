import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {
    private Connection connection;

    public HotelDAO() {
        try {
            connection = DatabaseConnector.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addHotel(Hotel hotel) throws SQLException {
        String sql = "INSERT INTO Hotels (name, location, amenities) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, hotel.getName());
            stmt.setString(2, hotel.getLocation());
            stmt.setString(3, hotel.getAmenities());
            stmt.executeUpdate();
        }
    }

    public List<Hotel> getAllHotels() throws SQLException {
        String sql = "SELECT * FROM Hotels";
        List<Hotel> hotels = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Hotel hotel = new Hotel(
                    rs.getInt("hotel_id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getString("amenities")
                );
                hotels.add(hotel);
            }
        }
        return hotels;
    }

    public void updateHotel(Hotel hotel) throws SQLException {
        String sql = "UPDATE Hotels SET name = ?, location = ?, amenities = ? WHERE hotel_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, hotel.getName());
            stmt.setString(2, hotel.getLocation());
            stmt.setString(3, hotel.getAmenities());
            stmt.setInt(4, hotel.getHotelId());
            stmt.executeUpdate();
        }
    }

    public void deleteHotel(int hotelId) throws SQLException {
        String sql = "DELETE FROM Hotels WHERE hotel_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, hotelId);
            stmt.executeUpdate();
        }
    }
}
