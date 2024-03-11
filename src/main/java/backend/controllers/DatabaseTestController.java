package backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class DatabaseTestController {

    @Autowired
    private DataSource dataSource;

    // tries to connect to the database and returns success status
    @GetMapping("/test")
    public String testDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return "Database connection successful!";
        } catch (SQLException e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
}
