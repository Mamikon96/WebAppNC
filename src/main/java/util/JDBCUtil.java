package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class JDBCUtil {
    private static Connection CONNECTION;

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        CONNECTION = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            CONNECTION = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","USER2","qwe123");
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return CONNECTION;
    }
}
