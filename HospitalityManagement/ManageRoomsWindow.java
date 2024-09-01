import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ManageRoomsWindow {
    private RoomDAO roomDAO;

    public ManageRoomsWindow() {
        roomDAO = new RoomDAO();

        JFrame frame = new JFrame("Manage Rooms");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel hotelIdLabel = new JLabel("Hotel ID:");
        hotelIdLabel.setBounds(10, 20, 80, 25);
        panel.add(hotelIdLabel);

        JTextField hotelIdText = new JTextField(20);
        hotelIdText.setBounds(150, 20, 165, 25);
        panel.add(hotelIdText);

        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setBounds(10, 50, 100, 25);
        panel.add(roomNumberLabel);

        JTextField roomNumberText = new JTextField(20);
        roomNumberText.setBounds(150, 50, 165, 25);
        panel.add(roomNumberText);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setBounds(10, 80, 80, 25);
        panel.add(typeLabel);

        JTextField typeText = new JTextField(20);
        typeText.setBounds(150, 80, 165, 25);
        panel.add(typeText);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 110, 80, 25);
        panel.add(priceLabel);

        JTextField priceText = new JTextField(20);
        priceText.setBounds(150, 110, 165, 25);
        panel.add(priceText);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(10, 140, 80, 25);
        panel.add(statusLabel);

        JTextField statusText = new JTextField(20);
        statusText.setBounds(150, 140, 165, 25);
        panel.add(statusText);

        JButton addButton = new JButton("Add Room");
        addButton.setBounds(10, 180, 150, 25);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get input values
                String hotelIdInput = hotelIdText.getText();
                String roomNumber = roomNumberText.getText();
                String type = typeText.getText();
                String priceInput = priceText.getText();
                String status = statusText.getText();
        
                // Validate inputs
                if (hotelIdInput == null || roomNumber == null || type == null || priceInput == null || status == null ||
                    hotelIdInput.trim().isEmpty() || roomNumber.trim().isEmpty() || type.trim().isEmpty() || priceInput.trim().isEmpty() || status.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "All fields must be filled out. Add failed.");
                    return;
                }
        
                // Convert hotelIdInput and priceInput to appropriate types
                int hotelId;
                double price;
                try {
                    hotelId = Integer.parseInt(hotelIdInput);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Invalid Hotel ID. Add failed.");
                    return;
                }
                
                try {
                    price = Double.parseDouble(priceInput);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Invalid Price value. Add failed.");
                    return;
                }
        
                // Create Room object
                Room room = new Room(0, hotelId, roomNumber, type, price, status);
        
                // Add Room to the database
                try {
                    roomDAO.addRoom(room);
                    JOptionPane.showMessageDialog(panel, "Room added successfully!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Failed to add the room.");
                }
            }
        });
        

        JButton updateButton = new JButton("Update Room");
        updateButton.setBounds(10, 220, 150, 25);
        panel.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the Room ID to update
                int roomId = Integer.parseInt(JOptionPane.showInputDialog(panel, "Enter Room ID to update:"));
        
                // Prompt for new values
                String hotelIdInput = JOptionPane.showInputDialog(panel, "Enter new Hotel ID:");
                String roomNumber = JOptionPane.showInputDialog(panel, "Enter new Room Number:");
                String type = JOptionPane.showInputDialog(panel, "Enter new Type:");
                String priceInput = JOptionPane.showInputDialog(panel, "Enter new Price:");
                String status = JOptionPane.showInputDialog(panel, "Enter new Status:");
        
                // Check if any value is null or empty and handle accordingly
                if (hotelIdInput == null || roomNumber == null || type == null || priceInput == null || status == null ||
                    hotelIdInput.trim().isEmpty() || roomNumber.trim().isEmpty() || type.trim().isEmpty() || priceInput.trim().isEmpty() || status.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "All fields must be filled out. Update failed.");
                    return;
                }
        
                // Convert price to double and handle possible NumberFormatException
                double price;
                try {
                    price = Double.parseDouble(priceInput);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Invalid price value. Update failed.");
                    return;
                }
        
                // Convert hotelIdInput to int
                int hotelId;
                try {
                    hotelId = Integer.parseInt(hotelIdInput);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Invalid Hotel ID. Update failed.");
                    return;
                }
        
                // Create a new Room object with the updated values
                Room room = new Room(roomId, hotelId, roomNumber, type, price, status);
                try {
                    roomDAO.updateRoom(room);
                    JOptionPane.showMessageDialog(panel, "Room updated successfully!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Failed to update the room.");
                }
            }
        });
        

        JButton deleteButton = new JButton("Delete Room");
        deleteButton.setBounds(10, 260, 150, 25);
        panel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int roomId = Integer.parseInt(JOptionPane.showInputDialog(panel, "Enter Room ID to delete:"));
                try {
                    roomDAO.deleteRoom(roomId);
                    JOptionPane.showMessageDialog(panel, "Room deleted successfully!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Add the View Rooms button
        JButton viewButton = new JButton("View Rooms");
        viewButton.setBounds(10, 300, 150, 25);
        panel.add(viewButton);

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Room> rooms = roomDAO.getAllRooms();
                    StringBuilder sb = new StringBuilder();
                    for (Room room : rooms) {
                        sb.append("Room ID: ").append(room.getRoomId()).append("\n")
                          .append("Hotel ID: ").append(room.getHotelId()).append("\n")
                          .append("Room Number: ").append(room.getRoomNumber()).append("\n")
                          .append("Type: ").append(room.getType()).append("\n")
                          .append("Price: ").append(room.getPrice()).append("\n")
                          .append("Status: ").append(room.getStatus()).append("\n\n");
                    }
                    JOptionPane.showMessageDialog(panel, sb.toString(), "Rooms List", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
