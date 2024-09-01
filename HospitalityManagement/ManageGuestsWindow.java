import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ManageGuestsWindow {
    private GuestDAO guestDAO;

    public ManageGuestsWindow() {
        guestDAO = new GuestDAO();

        JFrame frame = new JFrame("Manage Guests");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Guest Name:");
        nameLabel.setBounds(10, 20, 100, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(150, 20, 165, 25);
        panel.add(nameText);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 50, 100, 25);
        panel.add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(150, 50, 165, 25);
        panel.add(emailText);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(10, 80, 100, 25);
        panel.add(phoneLabel);

        JTextField phoneText = new JTextField(20);
        phoneText.setBounds(150, 80, 165, 25);
        panel.add(phoneText);

        JButton addButton = new JButton("Add Guest");
        addButton.setBounds(10, 120, 150, 25);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String email = emailText.getText();
                String phone = phoneText.getText();

                Guest guest = new Guest(0, name, email, phone);
                try {
                    guestDAO.addGuest(guest);
                    JOptionPane.showMessageDialog(panel, "Guest added successfully!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton viewButton = new JButton("View Guests");
        viewButton.setBounds(10, 160, 150, 25);
        panel.add(viewButton);

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Guest> guests;
                try {
                    guests = guestDAO.getAllGuests();
                    StringBuilder builder = new StringBuilder();
                    for (Guest guest : guests) {
                        builder.append("ID: ").append(guest.getGuestId())
                               .append(", Name: ").append(guest.getName())
                               .append(", Email: ").append(guest.getEmail())
                               .append(", Phone: ").append(guest.getPhone())
                               .append("\n");
                    }
                    JOptionPane.showMessageDialog(panel, builder.toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Add components for deleting guests
        JLabel deleteIdLabel = new JLabel("Guest ID to Delete:");
        deleteIdLabel.setBounds(10, 200, 150, 25);
        panel.add(deleteIdLabel);

        JTextField deleteIdText = new JTextField(20);
        deleteIdText.setBounds(150, 200, 165, 25);
        panel.add(deleteIdText);

        JButton deleteButton = new JButton("Delete Guest");
        deleteButton.setBounds(10, 240, 150, 25);
        panel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idText = deleteIdText.getText();
                try {
                    int guestId = Integer.parseInt(idText);
                    guestDAO.deleteGuest(guestId);
                    JOptionPane.showMessageDialog(panel, "Guest deleted successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Invalid ID format.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JLabel updateIdLabel = new JLabel("Guest ID to Update:");
        updateIdLabel.setBounds(10, 270, 150, 25);
        panel.add(updateIdLabel);

        JTextField updateIdText = new JTextField(20);
        updateIdText.setBounds(150, 270, 165, 25);
        panel.add(updateIdText);

        JButton updateButton = new JButton("Update Guest");
        updateButton.setBounds(10, 310, 150, 25);
        panel.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idText = updateIdText.getText();

                // Prompt for new values
                String name = JOptionPane.showInputDialog(panel, "Enter new Name:");
                String email = JOptionPane.showInputDialog(panel, "Enter new Email:");
                String phone = JOptionPane.showInputDialog(panel, "Enter new Phone:");

                // Check if any value is null or empty and handle accordingly
                if (name == null || email == null || phone == null || name.trim().isEmpty() || email.trim().isEmpty() || phone.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "All fields must be filled out. Update failed.");
                    return;
                }

                try {
                    int guestId = Integer.parseInt(idText);
                    Guest guest = new Guest(guestId, name, email, phone);
                    guestDAO.updateGuest(guest);
                    JOptionPane.showMessageDialog(panel, "Guest updated successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Invalid ID format.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Failed to update the guest.");
                }
            }
        });
    }
}
