package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionDB {
	private static final String HOST = "jdbc:mysql://localhost:3333/";
    private static final String USER = "admin";
    private static final String PASS = "admin";
    private static final String DB_NAME = "bd_banco?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(HOST + DB_NAME, USER, PASS);
    }
}
