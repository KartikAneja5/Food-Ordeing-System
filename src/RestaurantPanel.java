import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class RestaurantPanel {
    static String DB_URL = "jdbc:postgresql://db.nujfphmaqaqyzqbqbxkh.supabase.co:5432/postgres?user=postgres&password=Admin@123&sslmode=require";

    public static void main(String[] args) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DB_URL);
            if (conn != null) {
                System.out.println("Connection Established");
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            throw new RuntimeException(e);
        }
        RestaurantPanel rp=new RestaurantPanel();
        rp.Login();

//        JFrame frame = new JFrame("Food Ordering System");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(420, 420);
//        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 40));
//        frame.setVisible(true);

//        JLabel l1 = new JLabel("Welcome to Food Express");
//        l1.setFont(new Font("Arial", Font.BOLD, 20));
//        frame.add(l1);
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center alignment with spacing
//
//        JButton button1 = new JButton("I am a customer");
//        button1.setPreferredSize(new Dimension(150, 40));
//        button1.addActionListener(e -> showLogin("Customer"));
//        buttonPanel.add(button1);
//
//        JButton button2 = new JButton("I am a Restaurant Owner");
//        button2.setPreferredSize(new Dimension(150, 40));
//        button2.addActionListener(e -> showLogin("Restaurant"));
//        buttonPanel.add(button2);
//
//        // Add the button panel to the frame
//        frame.add(buttonPanel);
//        frame.setVisible(true);
    }

    private static void Login() {
        JFrame loginFrame = new JFrame("Restaurant  Login");
        loginFrame.setSize(300, 300);
        loginFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginFrame.setVisible(true);

        // Title Label
        JLabel titleLabel = new JLabel("Restaurant Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginFrame.add(titleLabel, gbc);

        // Subtitle Label
        JLabel subtitleLabel = new JLabel("Please login to continue", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        loginFrame.add(subtitleLabel, gbc);

        // Username Label
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        loginFrame.add(new JLabel("Username/Email:"), gbc);

        // Username Field
        JTextField usernameField = new JTextField(20);
        gbc.gridx = 1;
        loginFrame.add(usernameField, gbc);

        // Password Label
        gbc.gridy = 3;
        gbc.gridx = 0;
        loginFrame.add(new JLabel("Password:"), gbc);

        // Password Field
        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        loginFrame.add(passwordField, gbc);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(52, 152, 219)); // Blue color
        loginButton.setForeground(Color.WHITE);
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        loginFrame.add(loginButton, gbc);

        // Register Link
        JLabel registerLabel = new JLabel("Don't have an account? Register here", JLabel.CENTER);
        registerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        registerLabel.setForeground(Color.BLUE);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridy = 5;
        loginFrame.add(registerLabel, gbc);

        // Action listeners
        loginButton.addActionListener(e -> {
            String email = usernameField.getText();
            String password = new String(passwordField.getPassword());
            // System.out.println(userType + " login attempt: " + email);

            // Here you can add your authentication logic
            // For example, check against the database
            try (Connection conn = DriverManager.getConnection(DB_URL)) {
                String query = "SELECT * FROM restaurant WHERE restaurantemailid = ? AND password = ?"; // Ensure 'email' matches your DB column
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, email);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // Login successful
                    JOptionPane.showMessageDialog(loginFrame, "Login successful!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    showRestaurantManagementPanel(email);
                } else {
                    // Login failed
                    JOptionPane.showMessageDialog(loginFrame, "Invalid email or password",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(loginFrame, "Database connection error: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            loginFrame.dispose(); // Close the login frame after login attempt
        });

        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Implement registration logic here

                Registration();
               // JOptionPane.showMessageDialog(loginFrame, "Registration functionality not implemented yet.");
            }
        });

        loginFrame.setLocationRelativeTo(null); // Center the login frame
    }

    private static void Registration(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

// Title
        JLabel titleLabel = new JLabel("CREATE NEW ACCOUNT", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

// Field labels in column 0
        String[] labels = {"RestaurantName:", "RestaurantPhone:", "restaurantEmail:","RestaurantAddress", "Password:", "Confirm Password:", "City"};
        JTextField[] fields = {
                new JTextField(20),
                new JTextField(20),
                new JTextField(20),
                new JTextField(20),
                new JPasswordField(20),
                new JPasswordField(20),
                new JTextField(20)

        };

// Position fields using grid
        for (int i = 0; i < labels.length; i++) {
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            panel.add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            panel.add(fields[i], gbc);
        }

// Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBackground(new Color(46, 125, 50)); // Green color
        registerButton.setForeground(Color.WHITE);
        gbc.gridy = labels.length + 1;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(registerButton, gbc);


// Back to Login Link
        JLabel loginLabel = new JLabel("Already have an account? Login here");
        loginLabel.setForeground(Color.BLUE.darker());
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridy++;
        panel.add(loginLabel, gbc);

// Action Listeners
        // Action Listeners
        registerButton.addActionListener(e -> {
                    // Add registration logic here
                    String restaurantName = fields[0].getText();
                    String restaurantPhone = fields[1].getText();
                    String restaurantEmail = fields[2].getText();
                    String restaurantAddress = fields[3].getText();
                    String password = new String(((JPasswordField) fields[4]).getPassword());
                    String confirmPassword = new String(((JPasswordField) fields[5]).getPassword());
                    String city = fields[6].getText();
                    // Check if passwords match
                    if (!password.equals(confirmPassword)) {
                        JOptionPane.showMessageDialog(panel, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    // Here you can add your database insertion logic
                    // For example, insert the new restaurant into the database
                    try (Connection conn = DriverManager.getConnection(DB_URL)) {
                        String query = "INSERT INTO restaurant (restaurantName, restaurantPhone, restaurantEmailId, restaurantAddress, password, city) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, restaurantName);
                        stmt.setString(2, restaurantPhone);
                        stmt.setString(3, restaurantEmail);
                        stmt.setString(4, restaurantAddress);
                        stmt.setString(5, password); // Consider hashing the password before storing
                        stmt.setString(6, city);
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(panel, "Registration successful!");
                            // Clear fields after registration
                            for (JTextField field : fields) {
                                field.setText("");
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(panel, "Database connection error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
            // Show success message
            JOptionPane.showMessageDialog(panel, "Registration successful!");
            // Clear fields after registration
            for (JTextField field : fields) {
                field.setText("");
            } });

// Optionally, you can add an action for the login label if needed
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Logic to show login panel or perform other actions
               // Login();
                JOptionPane.showMessageDialog(panel, "Redirecting to login...");
            }
        });
// Create a new JFrame for the registration panel
        JFrame registrationFrame = new JFrame("Registration");
        registrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registrationFrame.setSize(400, 400);
        registrationFrame.add(panel);
        registrationFrame.setLocationRelativeTo(null); // Center the frame
        registrationFrame.setVisible(true);
    }

    private static void showRestaurantManagementPanel(String restaurantEmail) {
        JFrame managementFrame = new JFrame("Restaurant Management");
        managementFrame.setSize(600, 400);
        managementFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Manage Your Dishes", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        managementFrame.add(titleLabel, gbc);

        JTextField dishNameField = new JTextField(20);
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        managementFrame.add(new JLabel("Dish Name:"), gbc);
        gbc.gridx = 1;
        managementFrame.add(dishNameField, gbc);

        // Add Dish Button
        JButton addDishButton = new JButton("Add Dish");
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        managementFrame.add(addDishButton, gbc);

        // Remove Dish Button
        JButton removeDishButton = new JButton("Remove Dish");
        gbc.gridy = 3;
        managementFrame.add(removeDishButton, gbc);

        // Edit Dish Button
        JButton editDishButton = new JButton("Edit Dish");
        gbc.gridy = 4;
        managementFrame.add(editDishButton, gbc);

        // Remove Restaurant Button
        JButton removeRestaurantButton = new JButton("Remove My Restaurant");
        gbc.gridy = 5;
        managementFrame.add(removeRestaurantButton, gbc);

//        // Action Listeners
//        addDishButton.addActionListener(e -> {
//            String dishName = dishNameField.getText();
//            // Add logic to insert the dish into the database
//            try (Connection conn = DriverManager.getConnection(DB_URL)) {
//                String query = "INSERT INTO dishes (restaurantEmailId, dishName) VALUES (?, ?)";
//                PreparedStatement stmt = conn.prepareStatement(query);
//                stmt.setString(1, restaurantEmail);
//                stmt.setString(2, dishName);
//                int rowsAffected = stmt.executeUpdate();
//                if (rowsAffected > 0) {
//                    JOptionPane.showMessageDialog(managementFrame, "Dish added successfully!");
//                    dishNameField.setText(""); // Clear the field
//                } else {
//                    JOptionPane.showMessageDialog(managementFrame, "Failed to add dish.", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(managementFrame, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        });
//
//        removeDishButton.addActionListener(e -> {
//            String dishName = dishNameField.getText();
//            // Add logic to remove the dish from the database
//            try (Connection conn = DriverManager.getConnection(DB_URL)) {
//                String query = "DELETE FROM dishes WHERE restaurantEmailId = ? AND dishName = ?";
//                PreparedStatement stmt = conn.prepareStatement(query);
//                stmt.setString(1, restaurantEmail);
//                stmt.setString(2, dishName);
//                int rowsAffected = stmt.executeUpdate();
//                if (rowsAffected > 0) {
//                    JOptionPane.showMessageDialog(managementFrame, "Dish removed successfully!");
//                    dishNameField.setText(""); // Clear the field
//                } else {
//                    JOptionPane.showMessageDialog(managementFrame, "Failed to remove dish.", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(managementFrame, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        });
//
//        editDishButton.addActionListener(e -> {
//            String dishName = dishNameField.getText();
//            // Add logic to edit the dish in the database
//            // You may want to prompt for new dish details
//        });
//
//        removeRestaurantButton.addActionListener(e -> {
//            int confirm = JOptionPane.showConfirmDialog(managementFrame, "Are you sure you want to remove your restaurant?", "Confirm Removal", JOptionPane.YES_NO_OPTION);
//            if (confirm == JOptionPane.YES_OPTION) {
//                // Logic to remove the restaurant from the database
//                try (Connection conn = DriverManager.getConnection(DB_URL)) {
//                    String query = "DELETE FROM restaurant WHERE restaurantEmailId = ?";
//                    PreparedStatement stmt = conn.prepareStatement(query);
//                    stmt.setString(1, restaurantEmail);
//                    int rowsAffected = stmt.executeUpdate();
//                    if (rowsAffected > 0) {
//                        JOptionPane.showMessageDialog(managementFrame, "Restaurant removed successfully!");
//                        managementFrame.dispose(); // Close the management frame
//                    } else {
//                        JOptionPane.showMessageDialog(managementFrame, "Failed to remove restaurant.", "Error", JOptionPane.ERROR_MESSAGE);
//                    }
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(managementFrame, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });
        managementFrame.setLocationRelativeTo(null); // Center the frame
        managementFrame.setVisible(true);
    }
}