package hotelmanagementsystem.hotelmanagementsystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Has Utils-methods which can be directly used in other classes
 * Aims at avoiding unnecessary code repetition
 */

public class Utils {

    private double x = 0;
    private double y = 0;

    private String resourceName;

    /**
     * Enables to exit a window
     */
    public void closeWindow(){
        System.exit(0);
    }

    /**
     * Sets the stage and configures the scene
     */
    public void setStage(String resourceName){

        Parent root = insertResourceName(resourceName);

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        root.setOnMousePressed((MouseEvent mouseEvent) ->{
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

        stage.setScene(scene);
        stage.show();
    }

    public Parent insertResourceName(String resourceName){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(resourceName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return root;
    }


}
