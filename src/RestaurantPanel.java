import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RestaurantPanel {
    public static void main(String[] args) {
        Connection conn = null;
        String DB_URL = "jdbc:postgresql://db.nujfphmaqaqyzqbqbxkh.supabase.co:5432/postgres?user=postgres&password=Admin@123&sslmode=require";

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

}