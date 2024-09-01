import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ManageReservationsWindow {
    private ReservationDAO reservationDAO;

    public ManageReservationsWindow() {
        reservationDAO = new ReservationDAO();

        JFrame frame = new JFrame("Manage Reservations");
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

        JLabel guestIdLabel = new JLabel("Guest ID:");
        guestIdLabel.setBounds(10, 50, 80, 25);
        panel.add(guestIdLabel);

        JTextField guestIdText = new JTextField(20);
        guestIdText.setBounds(150, 50, 165, 25);
        panel.add(guestIdText);

        JLabel roomIdLabel = new JLabel("Room ID:");
        roomIdLabel.setBounds(10, 80, 80, 25);
        panel.add(roomIdLabel);

        JTextField roomIdText = new JTextField(20);
        roomIdText.setBounds(150, 80, 165, 25);
        panel.add(roomIdText);

        JLabel checkInLabel = new JLabel("Check-In:");
        checkInLabel.setBounds(10, 110, 80, 25);
        panel.add(checkInLabel);

        JTextField checkInText = new JTextField(20);
        checkInText.setBounds(150, 110, 165, 25);
        panel.add(checkInText);

        JLabel checkOutLabel = new JLabel("Check-Out:");
        checkOutLabel.setBounds(10, 140, 80, 25);
        panel.add(checkOutLabel);

        JTextField checkOutText = new JTextField(20);
        checkOutText.setBounds(150, 140, 165, 25);
        panel.add(checkOutText);

        JButton addButton = new JButton("Add Reservation");
        addButton.setBounds(10, 180, 150, 25);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int hotelId = Integer.parseInt(hotelIdText.getText());
                int guestId = Integer.parseInt(guestIdText.getText());
                int roomId = Integer.parseInt(roomIdText.getText());
                Date checkIn = Date.valueOf(checkInText.getText());
                Date checkOut = Date.valueOf(checkOutText.getText());

                Reservation reservation = new Reservation(0, hotelId, guestId, roomId, checkIn, checkOut);
                try {
                    reservationDAO.addReservation(reservation);
                    JOptionPane.showMessageDialog(panel, "Reservation added successfully!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton updateButton = new JButton("Update Reservation");
        updateButton.setBounds(10, 220, 150, 25);
        panel.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int reservationId = Integer.parseInt(JOptionPane.showInputDialog(panel, "Enter Reservation ID to update:"));
                int hotelId = Integer.parseInt(JOptionPane.showInputDialog(panel, "Enter new Hotel ID:"));
                int guestId = Integer.parseInt(JOptionPane.showInputDialog(panel, "Enter new Guest ID:"));
                int roomId = Integer.parseInt(JOptionPane.showInputDialog(panel, "Enter new Room ID:"));
                Date checkIn = Date.valueOf(JOptionPane.showInputDialog(panel, "Enter new Check-In date (YYYY-MM-DD):"));
                Date checkOut = Date.valueOf(JOptionPane.showInputDialog(panel, "Enter new Check-Out date (YYYY-MM-DD):"));

                Reservation reservation = new Reservation(reservationId, hotelId, guestId, roomId, checkIn, checkOut);
                try {
                    reservationDAO.updateReservation(reservation);
                    JOptionPane.showMessageDialog(panel, "Reservation updated successfully!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton deleteButton = new JButton("Delete Reservation");
        deleteButton.setBounds(10, 260, 150, 25);
        panel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int reservationId = Integer.parseInt(JOptionPane.showInputDialog(panel, "Enter Reservation ID to delete:"));
                try {
                    reservationDAO.deleteReservation(reservationId);
                    JOptionPane.showMessageDialog(panel, "Reservation deleted successfully!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton viewButton = new JButton("View Reservations");
        viewButton.setBounds(10, 300, 150, 25);
        panel.add(viewButton);

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Reservation> reservations;
                try {
                    reservations = reservationDAO.getAllReservations();
                    StringBuilder builder = new StringBuilder();
                    for (Reservation reservation : reservations) {
                        builder.append("ID: ").append(reservation.getReservationId())
                               .append(", Hotel ID: ").append(reservation.getHotelId())
                               .append(", Guest ID: ").append(reservation.getGuestId())
                               .append(", Room ID: ").append(reservation.getRoomId())
                               .append(", Check-In: ").append(reservation.getCheckIn())
                               .append(", Check-Out: ").append(reservation.getCheckOut())
                               .append("\n");
                    }
                    JOptionPane.showMessageDialog(panel, builder.toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
