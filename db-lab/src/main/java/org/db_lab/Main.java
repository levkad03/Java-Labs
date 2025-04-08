package org.db_lab;

import org.db_lab.mapper.UserMapper;
import org.db_lab.model.User;
import org.db_lab.repository.DbRepository;
import org.db_lab.repository.JdbcDbRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:database.db";

        try (Connection connection = DriverManager.getConnection(url)) {
            connection.createStatement().execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY,
                    name TEXT,
                    email TEST
                );
            """);

            UserMapper userMapper = new UserMapper();
            DbRepository<User, Integer> userRepository = new JdbcDbRepository<User, Integer>(connection, "users", userMapper);

            // Create
            User newUser = new User();
            newUser.setId(1);
            newUser.setName("Lev");
            newUser.setEmail("Lev@example.com");

            userRepository.save(newUser);
            System.out.println("Saved: " + newUser.getName());

            // List of all users
            List<User> users = userRepository.findAll();
            for (User u : users) {
                System.out.println(u.getId() + ": " + u.getName() + " (" + u.getEmail() + ")");
            }

            // Updated
            newUser.setName("Lev2");
            userRepository.update(newUser);

            // Find by id
            User updated = userRepository.findById(1);
            System.out.println("Updated: " + updated.getName());

            // Delete
            userRepository.deleteById(1);
            System.out.println("User deleted");



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}