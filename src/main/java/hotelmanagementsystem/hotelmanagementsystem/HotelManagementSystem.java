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

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HotelManagementSystem.class.getResource("loginpage.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        //scene.getStylesheets().add(getClass().getResource("@loginDesign.css").toString());

        //Trying the effects of the Parent class and comparing its functionalities
        //with those of the FXMLLoader-Class

        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));

        Scene scene = new Scene(root);

        root.setOnMouseClicked((MouseEvent mouseEvent) -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });


        root.setOnMouseDragged((MouseEvent mouseEvent) ->{
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);

            stage.setOpacity(.8);
        });

        root.setOnMouseReleased((MouseEvent mouseEvent) -> {
            stage.setOpacity(1);
        });

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setTitle("LoginPage");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}