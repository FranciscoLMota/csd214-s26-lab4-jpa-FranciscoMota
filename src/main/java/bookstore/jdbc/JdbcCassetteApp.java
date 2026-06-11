package bookstore.jdbc;

import bookstore.pojos.CassetteTape;
import java.sql.*;

public class JdbcCassetteApp {
    private static final String URL = "jdbc:mysql://localhost:3333/bookstore";
    private static final String USER = "csd214";
    private static final String PASS = "itstudies12345";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            // 1. Setup Table
            createTable(conn);

            // 2. Create (Insert)
            System.out.println("\n--- INSERTING ---");
            CassetteTape c1 = new CassetteTape("Hedonista", "25-02-2022", "Clarissa", 45.3, 25.0, 3, false);
            insertCassette(conn, c1);

            // 3. Read (Select)
            System.out.println("\n--- READING ---");
            listCassettes(conn);

            // 4. Update
            System.out.println("\n--- UPDATING ---");
            updateCassettePrice(conn, "Hedonista", 55.00);
            listCassettes(conn);

            // 5. Delete
            System.out.println("\n--- DELETING ---");
            deleteCassette(conn, "Hedonista");
            listCassettes(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS physicalReleases (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "product_id VARCHAR(36), " + // Column for our UUID
                "title VARCHAR(255), " +
                "artist VARCHAR(255), " +
                "dateOfRelease VARCHAR(10), " +
                "price DOUBLE, " +
                "hasAutoReverse BOOLEAN NOT NULL DEFAULT FALSE, " +
                "sizeInches INT, " +
                "rotationsPerMinute INT, " +
                "copies INT)";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'physicalRelease' is ready.");
        }
    }

    private static void insertCassette(Connection conn, CassetteTape c) throws SQLException {
        // SECURITY: Use ? placeholders to prevent SQL Injection
        String sql = "INSERT INTO physicalReleases (product_id, title, price, artist, dateOfRelease, hasAutoReverse, copies) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getProductId()); // The UUID String from the POJO
            ps.setString(2, c.getTitle());
            ps.setDouble(3, c.getPrice());
            ps.setString(4, c.getArtist());
            ps.setString(5, c.getDateOfRelease());
            ps.setBoolean(6, c.getHasAutoReverse());
            ps.setInt(7, c.getCopies());
            ps.executeUpdate();
            System.out.println("Cassette saved to DB: " + c.getTitle());
        }
    }

    private static void listCassettes(Connection conn) throws SQLException {
        String sql = "SELECT * FROM physicalReleases";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("DB ID: %d | UUID: %s | Title: %s | Artist: %s |  Copies: %d | Price: $%.2f%n",
                        rs.getInt("id"),
                        rs.getString("product_id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getInt("copies"),
                        rs.getDouble("price"));
            }
        }
    }

    private static void updateCassettePrice(Connection conn, String title, double newPrice) throws SQLException {
        String sql = "UPDATE physicalReleases SET price = ? WHERE title = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newPrice);
            ps.setString(2, title);
            int rowsAffected = ps.executeUpdate();
            System.out.println("Updated " + rowsAffected + " record(s).");
        }
    }

    private static void deleteCassette(Connection conn, String title) throws SQLException {
        String sql = "DELETE FROM physicalReleases WHERE title = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.executeUpdate();
            System.out.println("Deleted Cassette: " + title);
        }
    }
}

