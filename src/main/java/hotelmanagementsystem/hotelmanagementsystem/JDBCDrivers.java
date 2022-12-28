package hotelmanagementsystem.hotelmanagementsystem;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class JDBCDrivers {
    private static final String URL = "jdbc:hsqldb:mem:beispiel";
    private static final String USER ="sa";
    private static final String PASSWORD = "";


    private JDBCDrivers(){
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void printDrivers(){
        final Enumeration<Driver> drivers = DriverManager.getDrivers();

        while(drivers.hasMoreElements()){
            Driver driver = drivers.nextElement();
            System.out.println(driver.getClass().getName());
        }

    }

    public static void main(String[] args) {
        JDBCDrivers.printDrivers();
    }

}
