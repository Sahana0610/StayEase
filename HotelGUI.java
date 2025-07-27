import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class HotelGUI extends JFrame {
    private JTextArea output;
    private JTextField roomField, guestField;
    private JComboBox<String> roomTypeBox;

    public HotelGUI() {
        setTitle("Hotel Management System");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        output = new JTextArea();
        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        roomField = new JTextField();
        guestField = new JTextField();
        roomTypeBox = new JComboBox<>(new String[] {"Standard", "Deluxe", "Premium Suite"});

        inputPanel.add(new JLabel("Room Number (1–30):"));
        inputPanel.add(roomField);
        inputPanel.add(new JLabel("Guest Name:"));
        inputPanel.add(guestField);
        inputPanel.add(new JLabel("Room Type:"));
        inputPanel.add(roomTypeBox);

        JButton bookBtn = new JButton("Book Room");
        JButton viewBtn = new JButton("View Rooms");
        JButton checkoutBtn = new JButton("Checkout Room");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(bookBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(checkoutBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event listeners
        viewBtn.addActionListener(e -> viewRooms());
        bookBtn.addActionListener(e -> bookRoom());
        checkoutBtn.addActionListener(e -> checkoutRoom());
    }

    private void viewRooms() {
        try (Connection conn = DBHelper.getConnection()) {
            output.setText("Room Status:\n");
            for (int i = 1; i <= 30; i++) {
                String query = "SELECT guest, room_type FROM bookings WHERE room=?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, i);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String guest = rs.getString("guest");
                    String type = rs.getString("room_type");
                    output.append("Room " + i + " (" + type + "): " + guest + "\n");
                } else {
                    output.append("Room " + i + ": Available\n");
                }
            }
        } catch (SQLException e) {
            output.setText("Error: " + e.getMessage());
        }
    }

    private void bookRoom() {
        int room;
        try {
            room = Integer.parseInt(roomField.getText().trim());
            if (room < 1 || room > 30) {
                output.setText("Room number must be between 1 and 30.");
                return;
            }
        } catch (NumberFormatException e) {
            output.setText("Invalid room number.");
            return;
        }

        String guest = guestField.getText().trim();
        if (guest.isEmpty()) {
            output.setText("Guest name required.");
            return;
        }

        String roomType = (String) roomTypeBox.getSelectedItem();

        try (Connection conn = DBHelper.getConnection()) {
            String checkQuery = "SELECT * FROM bookings WHERE room=?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, room);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                output.setText("Room already booked.");
                return;
            }

            String insertQuery = "INSERT INTO bookings (room, guest, room_type) VALUES (?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setInt(1, room);
            insertStmt.setString(2, guest);
            insertStmt.setString(3, roomType);
            insertStmt.executeUpdate();
            output.setText("Room booked successfully!");

        } catch (SQLException e) {
            output.setText("Error: " + e.getMessage());
        }
    }

    private void checkoutRoom() {
        int room;
        try {
            room = Integer.parseInt(roomField.getText().trim());
            if (room < 1 || room > 30) {
                output.setText("Room number must be between 1 and 30.");
                return;
            }
        } catch (NumberFormatException e) {
            output.setText("Invalid room number.");
            return;
        }

        try (Connection conn = DBHelper.getConnection()) {
            String deleteQuery = "DELETE FROM bookings WHERE room=?";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, room);
            int affected = deleteStmt.executeUpdate();
            if (affected > 0) {
                output.setText("Checkout successful.");
            } else {
                output.setText("Room was not booked.");
            }
        } catch (SQLException e) {
            output.setText("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HotelGUI().setVisible(true);
        });
    }
}
