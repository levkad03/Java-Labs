package org.example.manager;

import org.example.models.Cat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:postgresql://webapp-postgres:5432/webapp";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    static {
        try {
            Class.forName("org.postgresql.Driver");

            try (Connection conn = getConnection()) {
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute("CREATE TABLE IF NOT EXISTS cats (id SERIAL PRIMARY KEY, name VARCHAR(255) NOT NULL)");

                }

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }

    public static void addCat(Cat cat) {
        String sql = "INSERT INTO cats (name) VALUES (?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, cat.getName());
            pstmt.executeUpdate();

            // Отримання згенерованого id
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cat.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Cat> getAllCats() {
        List<Cat> cats = new ArrayList<>();

        String sql = "SELECT id, name FROM cats ORDER BY id";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cat cat = new Cat();
                cat.setId(rs.getInt("id"));
                cat.setName(rs.getString("name"));
                cats.add(cat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cats;

    }
}
