package hotelmanagementsystem.hotelmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;



public class DatabaseConnection {
    public Connection databaseLink;

    public Connection findConnection(){
        String databaseName = "hotel_management";
        String databaseUser = "root";
        String databasePassword = "Ruth,1971";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch (Exception e){
            e.printStackTrace();
        }

        return databaseLink;
    }
}
