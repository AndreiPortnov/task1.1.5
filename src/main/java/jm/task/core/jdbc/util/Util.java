package jm.task.core.jdbc.util;

import com.mysql.jdbc.Driver;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static jm.task.core.jdbc.constants.StaticConstants.*;

public class Util {
    // реализуйте настройку соеденения с БД

    private final Connection connection;

    String url = URL_KEY;
    String username = USERNAME_KEY;
    String password = PASSWORD_KEY;

    public Util() {

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
