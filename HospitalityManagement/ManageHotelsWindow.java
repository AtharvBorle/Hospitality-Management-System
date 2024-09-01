import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ManageHotelsWindow {
    private HotelDAO hotelDAO;

    public ManageHotelsWindow() {
        hotelDAO = new HotelDAO();

        JFrame frame = new JFrame("Manage Hotels");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel idLabel = new JLabel("Hotel ID:");
        idLabel.setBounds(10, 20, 80, 25);
        panel.add(idLabel);

        JTextField idText = new JTextField(20);
        idText.setBounds(150, 20, 165, 25);
        panel.add(idText);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 50, 80, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(150, 50, 165, 25);
        panel.add(nameText);

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(10, 80, 80, 25);
        panel.add(locationLabel);

        JTextField locationText = new JTextField(20);
        locationText.setBounds(150, 80, 165, 25);
        panel.add(locationText);

        JLabel amenitiesLabel = new JLabel("Amenities:");
        amenitiesLabel.setBounds(10, 110, 80, 25);
        panel.add(amenitiesLabel);

        JTextField amenitiesText = new JTextField(20);
        amenitiesText.setBounds(150, 110, 165, 25);
        panel.add(amenitiesText);

        JButton addButton = new JButton("Add Hotel");
        addButton.setBounds(10, 200, 150, 25);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String location = locationText.getText();
                String amenities = amenitiesText.getText();

                Hotel hotel = new Hotel(0, name, location, amenities);
                try {
                    hotelDAO.addHotel(hotel);
                    JOptionPane.showMessageDialog(panel, "Hotel added successfully!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton updateButton = new JButton("Update Hotel");
        updateButton.setBounds(170, 200, 150, 25);
        panel.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the Hotel ID to update
                int hotelId = Integer.parseInt(JOptionPane.showInputDialog(panel, "Enter Hotel ID to update:"));
        
                // Prompt for new values
                String name = JOptionPane.showInputDialog(panel, "Enter new Name:");
                String location = JOptionPane.showInputDialog(panel, "Enter new Location:");
                String amenities = JOptionPane.showInputDialog(panel, "Enter new Amenities:");
        
                // Check if any value is null or empty and handle accordingly
                if (name == null || location == null || amenities == null || name.trim().isEmpty() || location.trim().isEmpty() || amenities.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "All fields must be filled out. Update failed.");
                    return;
                }
        
                // Create a new Hotel object with the updated values
                Hotel hotel = new Hotel(hotelId, name, location, amenities);
                try {
                    hotelDAO.updateHotel(hotel);
                    JOptionPane.showMessageDialog(panel, "Hotel updated successfully!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Failed to update the hotel.");
                }
            }
        });

        JButton deleteButton = new JButton("Delete Hotel");
        deleteButton.setBounds(330, 200, 150, 25);
        panel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int hotelId = Integer.parseInt(JOptionPane.showInputDialog(panel, "Enter Hotel ID to delete:"));
                try {
                    hotelDAO.deleteHotel(hotelId);
                    JOptionPane.showMessageDialog(panel, "Hotel deleted successfully!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton viewButton = new JButton("View Hotels");
        viewButton.setBounds(490, 200, 150, 25);
        panel.add(viewButton);

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Hotel> hotels = hotelDAO.getAllHotels();
                    StringBuilder sb = new StringBuilder();
                    for (Hotel hotel : hotels) {
                        sb.append("ID: ").append(hotel.getHotelId()).append("\n")
                          .append("Name: ").append(hotel.getName()).append("\n")
                          .append("Location: ").append(hotel.getLocation()).append("\n")
                          .append("Amenities: ").append(hotel.getAmenities()).append("\n\n");
                    }
                    JOptionPane.showMessageDialog(panel, sb.toString(), "Hotel List", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
