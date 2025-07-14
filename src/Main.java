import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class SupabaseConnector {
    public static void main(String[] args) {
        Connection conn = null;
        String jdbcUrl = "jdbc:postgresql://db.nujfphmaqaqyzqbqbxkh.supabase.co:5432/postgres?user=postgres&password=Admin@123&sslmode=require";

        try {
            // Load PostgreSQL JDBC driver (optional for newer Java versions)
            Class.forName("org.postgresql.Driver");

            // Establish connection
            conn = DriverManager.getConnection(jdbcUrl);
            System.out.println("✅ Successfully connected to Supabase!");

            // Create SQL statement
            Statement st = conn.createStatement();
            String query = "INSERT INTO student(Sid, Sname, Sroll, Smarks) VALUES (1, 'nihal', 68, 232.5)";
            int r = st.executeUpdate(query);

            if (r > 0) {
                System.out.println("✅ Inserted successfully");
            } else {
                System.out.println("⚠️ Insertion failed");
            }

            // Close connection
            conn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("🚫 JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("🚫 Connection or SQL operation failed:");
            e.printStackTrace();
        }
        System.out.println("Done");
    }
}