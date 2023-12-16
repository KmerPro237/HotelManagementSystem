package hotelmanagementsystem.hotelmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.*;
import javafx.stage.StageStyle;


import java.io.IOException;

public class HotelManagementSystem extends Application {

    private double x = 0;
    private double y = 0;

    private Utils utils = new Utils();

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HotelManagementSystem.class.getResource("loginpage.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        //scene.getStylesheets().add(getClass().getResource("@loginDesign.css").toString());

        //Trying the effects of the Parent class and comparing its functionalities
        //with those of the FXMLLoader-Class

        utils.setStage("loginpage.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}