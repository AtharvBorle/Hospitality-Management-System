import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MainGUI {
    private HotelDAO hotelDAO;
    private RoomDAO roomDAO;
    private GuestDAO guestDAO;
    private ReservationDAO reservationDAO;

    public MainGUI() {
        hotelDAO = new HotelDAO();
        roomDAO = new RoomDAO();
        guestDAO = new GuestDAO();
        reservationDAO = new ReservationDAO();

        JFrame frame = new JFrame("Hospitality Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton hotelButton = new JButton("Manage Hotels");
        hotelButton.setBounds(100, 20, 200, 25);
        panel.add(hotelButton);

        hotelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManageHotelsWindow();  // Opens the Hotel Management Window
            }
        });

        JButton roomButton = new JButton("Manage Rooms");
        roomButton.setBounds(100, 60, 200, 25);
        panel.add(roomButton);

        roomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManageRoomsWindow();  // Opens the Room Management Window
            }
        });

        JButton guestButton = new JButton("Manage Guests");
        guestButton.setBounds(100, 100, 200, 25);
        panel.add(guestButton);

        guestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManageGuestsWindow();  // Opens the Guest Management Window
            }
        });

        JButton reservationButton = new JButton("Manage Reservations");
        reservationButton.setBounds(100, 140, 200, 25);
        panel.add(reservationButton);

        reservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManageReservationsWindow();  // Opens the Reservation Management Window
            }
        });
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
