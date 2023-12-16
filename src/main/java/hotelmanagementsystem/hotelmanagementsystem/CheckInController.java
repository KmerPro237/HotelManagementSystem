package hotelmanagementsystem.hotelmanagementsystem;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CheckInController implements Initializable {

    @FXML
    private Button chechinCloseButton;

    @FXML
    private Button checkinButton;

    @FXML
    private Label checkinCustomerNumber;

    @FXML
    private DatePicker checkinDate;

    @FXML
    private TextField checkinEmailAddress;

    @FXML
    private TextField checkinFirstname;

    @FXML
    private AnchorPane checkinForm;

    @FXML
    private TextField checkinLastname;

    @FXML
    private TextField checkinPhoneNumber;

    @FXML
    private Button checkinReceiptButton;

    @FXML
    private Button checkinResetButton;

    @FXML
    private DatePicker checkoutDate;

    private Utils utils = new Utils();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void closeWindow(){
        utils.closeWindow();
    }
}
