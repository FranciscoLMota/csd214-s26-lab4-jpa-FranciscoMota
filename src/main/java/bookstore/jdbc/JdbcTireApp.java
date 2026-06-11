package bookstore.jdbc;

import bookstore.pojos.Tire;
import bookstore.pojos.VinylRecord;

import java.sql.*;

public class JdbcTireApp {
    private static final String URL = "jdbc:mysql://localhost:3333/bookstore";
    private static final String USER = "csd214";
    private static final String PASS = "itstudies12345";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            // 1. Setup Table
            createTable(conn);

            // 2. Create (Insert)
            System.out.println("\n--- INSERTING ---");
            Tire t1 = new Tire("Michelan", 250.0, 10, 24);
            insertTire(conn, t1);

            // 3. Read (Select)
            System.out.println("\n--- READING ---");
            listTires(conn);

            // 4. Update
            System.out.println("\n--- UPDATING ---");
            updateTirePrice(conn, "Michelan", 24, 55.00);
            listTires(conn);

            // 5. Delete
            System.out.println("\n--- DELETING ---");
            deleteVinyl(conn, "Michelan", 24);
            listTires(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS vehiclePart (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "product_id VARCHAR(36), " + // Column for our UUID
                "manufacturer VARCHAR(255), " +
                "diameter INT, " +
                "coldCrankingAmps INT, " +
                "price DOUBLE, " +
                "copies INT)";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'vehiclePart' is ready.");
        }
    }

    private static void insertTire(Connection conn, Tire t) throws SQLException {
        // SECURITY: Use ? placeholders to prevent SQL Injection
        String sql = "INSERT INTO vehiclePart (product_id, manufacturer, diameter, price, copies) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getProductId()); // The UUID String from the POJO
            ps.setString(2, t.getManufacturer());
            ps.setInt(3, t.getDiameter());
            ps.setDouble(4, t.getPrice());
            ps.setInt(5, t.getCopies());
            ps.executeUpdate();
            System.out.println("Tire saved to DB: " + t.getManufacturer() + " - " + t.getDiameter());
        }
    }

    private static void listTires(Connection conn) throws SQLException {
        String sql = "SELECT * FROM vehiclePart";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("DB ID: %d | UUID: %s | Manufacturer: %s | Diameter: %s | Copies: %d | Price: $%.2f%n",
                        rs.getInt("id"),
                        rs.getString("product_id"),
                        rs.getString("manufacturer"),
                        rs.getInt("diameter"),
                        rs.getInt("copies"),
                        rs.getDouble("price"));
            }
        }
    }

    private static void updateTirePrice(Connection conn, String manufacturer, int diameter, double newPrice) throws SQLException {
        String sql = "UPDATE vehiclePart SET price = ? WHERE manufacturer = ? AND diameter = ? ";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newPrice);
            ps.setString(2, manufacturer);
            ps.setInt(3, diameter);
            int rowsAffected = ps.executeUpdate();
            System.out.println("Updated " + rowsAffected + " record(s).");
        }
    }

    private static void deleteVinyl(Connection conn, String manufacturer, int diameter) throws SQLException {
        String sql = "DELETE FROM vehiclePart WHERE manufacturer = ? AND diameter = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, manufacturer);
            ps.setInt(2, diameter);
            ps.executeUpdate();
            System.out.println("Deleted Tire: " + manufacturer);
        }
    }
}

