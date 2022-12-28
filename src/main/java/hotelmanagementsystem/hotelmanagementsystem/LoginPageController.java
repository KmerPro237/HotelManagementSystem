package hotelmanagementsystem.hotelmanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.*;

public class LoginPageController {

    @FXML
    private Button loginButton;

    @FXML
    private Button closeButton;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField passwordTextfield;

    @FXML
    private StackPane stack_form;

    @FXML
    private TextField usernameTextfield;

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private Connection connection;

    public void exit(){
        System.exit(0);
    }

    public void validateLogin(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.findConnection();

        String username = usernameTextfield.getText();
        username = username.trim();
        String password = passwordTextfield.getText();
        String sqlQuery ="select * from administratordata where Username = ? and Password = ?";

        try{
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            Alert alert;

            if(username.isEmpty() || password.isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all blank fields!");
                alert.showAndWait();
            } else {

                if (resultSet.next()) {

                    //Inserting Alerts
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Login successful!");
                    alert.showAndWait();

                    //loginButton.getScene().getWindow().hide();  -----> Should normally hide the login Button if the given data is correct

                    //If login is successful, open the dashboard
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();

                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Username/Password incorrect!");
                    alert.showAndWait();
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
