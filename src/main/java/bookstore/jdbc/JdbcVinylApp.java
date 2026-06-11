package bookstore.jdbc;

import bookstore.pojos.VinylRecord;

import java.sql.*;

public class JdbcVinylApp {
    private static final String URL = "jdbc:mysql://localhost:3333/bookstore";
    private static final String USER = "csd214";
    private static final String PASS = "itstudies12345";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            // 1. Setup Table
            createTable(conn);

            // 2. Create (Insert)
            System.out.println("\n--- INSERTING ---");
            VinylRecord v1 = new VinylRecord("Hedonista", "25-02-2022", "Clarissa", 45.3, 25.0, 3, 12, 33);
            insertVinyl(conn, v1);

            // 3. Read (Select)
            System.out.println("\n--- READING ---");
            listVinyls(conn);

            // 4. Update
            System.out.println("\n--- UPDATING ---");
            updateVinylPrice(conn, "Hedonista", 55.00);
            listVinyls(conn);

            // 5. Delete
            System.out.println("\n--- DELETING ---");
            deleteVinyl(conn, "Hedonista");
            listVinyls(conn);

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

    private static void insertVinyl(Connection conn, VinylRecord v) throws SQLException {
        // SECURITY: Use ? placeholders to prevent SQL Injection
        String sql = "INSERT INTO physicalReleases (product_id, title, price, artist, dateOfRelease, sizeInches, rotationsPerMinute, copies) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getProductId()); // The UUID String from the POJO
            ps.setString(2, v.getTitle());
            ps.setDouble(3, v.getPrice());
            ps.setString(4, v.getArtist());
            ps.setString(5, v.getDateOfRelease());
            ps.setInt(6, v.getRecordSizeInches());
            ps.setInt(7, v.getRotationsPerMinute());
            ps.setInt(8, v.getCopies());
            ps.executeUpdate();
            System.out.println("Vinyl saved to DB: " + v.getTitle());
        }
    }

    private static void listVinyls(Connection conn) throws SQLException {
        String sql = "SELECT * FROM physicalReleases";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("DB ID: %d | UUID: %s | Title: %s | Artist: %s |  Copies: %d | Record Size: %d Inches | Price: $%.2f%n",
                        rs.getInt("id"),
                        rs.getString("product_id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getInt("copies"),
                        rs.getInt("sizeInches"),
                        rs.getDouble("price"));
            }
        }
    }

    private static void updateVinylPrice(Connection conn, String title, double newPrice) throws SQLException {
        String sql = "UPDATE physicalReleases SET price = ? WHERE title = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newPrice);
            ps.setString(2, title);
            int rowsAffected = ps.executeUpdate();
            System.out.println("Updated " + rowsAffected + " record(s).");
        }
    }

    private static void deleteVinyl(Connection conn, String title) throws SQLException {
        String sql = "DELETE FROM physicalReleases WHERE title = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.executeUpdate();
            System.out.println("Deleted vinyl: " + title);
        }
    }
}

