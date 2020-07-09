package be.intecbrussel.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPort {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://moktok.javawebdev.com:33306/omer",
                "omer",
                "omer2020"
        );
    }
}
