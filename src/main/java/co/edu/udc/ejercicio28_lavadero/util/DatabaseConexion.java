package co.edu.udc.ejercicio28_lavadero.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConexion {

    private static final String URL = "jdbc:sqlite:./lavadero.db";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL);
    }
}
