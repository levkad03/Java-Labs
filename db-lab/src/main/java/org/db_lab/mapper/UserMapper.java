package org.db_lab.mapper;

import org.db_lab.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements EntityMapper<User, Integer> {
    @Override
    public User fromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));

        return user;
    }

    @Override
    public PreparedStatement createInsertStatement(Connection conn, String tableName, User user) throws SQLException {
        String sql = "INSERT INTO " + tableName + " (id, name, email) VALUES (?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, user.getId());
        stmt.setString(2, user.getName());
        stmt.setString(3, user.getEmail());
        return stmt;
    }

    @Override
    public PreparedStatement createUpdateStatement(Connection conn, String tableName, User user) throws SQLException {
        String sql = "UPDATE " + tableName + " SET name = ?, email = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setInt(3, user.getId());
        return stmt;
    }
}
