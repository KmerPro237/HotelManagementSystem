package hotelmanagementsystem.hotelmanagementsystem;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Button availableRoomsAddButton;

    @FXML
    private Button availableRoomsCheckinButton;

    @FXML
    private Button availableRoomsClearButton;

    @FXML
    private TableColumn<RoomsData, String> availableRoomsColumnPrice;

    @FXML
    private TableColumn<RoomsData, String> availableRoomsColumnRoomNumber;

    @FXML
    private TableColumn<RoomsData, String> availableRoomsColumnRoomType;

    @FXML
    private TableColumn<RoomsData, String> availableRoomsColumnStatus;

    @FXML
    private Button availableRoomsDeleteButton;

    @FXML
    private AnchorPane availableRoomsForm;

    @FXML
    private TextField availableRoomsPriceLabel;

    @FXML
    private TextField availableRoomsRoomNumberLabel;

    @FXML
    private ComboBox<?> availableRoomsRoomStatusComboBox;

    @FXML
    private ComboBox<?> availableRoomsRoomTypeComboBox;

    @FXML
    private TextField availableRoomsSearchField;

    @FXML
    private TableView<RoomsData> availableRoomsTableView;

    @FXML
    private Button availableRoomsUpdateButton;

    @FXML
    private TableColumn<?, ?> customersColumnCheckedin;

    @FXML
    private TableColumn<?, ?> customersColumnCheckedout;

    @FXML
    private TableColumn<?, ?> customersColumnCustomerNumber;

    @FXML
    private TableColumn<?, ?> customersColumnCustomerPhoneNumber;

    @FXML
    private TableColumn<?, ?> customersColumnFirstName;

    @FXML
    private TableColumn<?, ?> customersColumnLastName;

    @FXML
    private TableColumn<?, ?> customersColumnTotalPayment;

    @FXML
    private AnchorPane customersForm;

    @FXML
    private TextField customersSearchField;

    @FXML
    private TableView<?> customersTableView;

    @FXML
    private AreaChart<?, ?> dashboardAreaChart;

    @FXML
    private Button dashboardAvailableRoomsButton;

    @FXML
    private Button dashboardCustomersButton;

    @FXML
    private Button dashboardDashboardButton;

    @FXML
    private AnchorPane dashboardForm;

    @FXML
    private Button dashboardSignoutButton;

    @FXML
    private Label dashboardTodaysBookingsLabel;

    @FXML
    private Label dashboardTodaysIncomeLabel;

    @FXML
    private Label dashboardTotalIncomeLabel;

    @FXML
    private Label dashboardUsername;

    @FXML
    private FontAwesomeIconView windowCloseButton;

    @FXML
    private FontAwesomeIconView windowMinimizeButton;

    //Rooms' Data
    private  ObservableList<RoomsData> contentsOfRoomDataList;

    //Database tools
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    //Combobox Elements
    private static ComboBoxesData comboBoxesData = new ComboBoxesData();

    /**
     * Adds rooms into the Database
     */
    public void addRoomToDatabase(){
        String sql = "insert into rooms(roomNumber, roomType, roomStatus, roomPrice) values(?,?,?,?)";

        //databaseConnection = new DatabaseConnection();
        connection = databaseConnection.findConnection();

        try{

            String roomNumber = availableRoomsRoomNumberLabel.getText();
            String roomType = (String) availableRoomsRoomTypeComboBox.getSelectionModel().getSelectedItem();
            String roomStatus = (String) availableRoomsRoomStatusComboBox.getSelectionModel().getSelectedItem();
            String roomPrice = availableRoomsPriceLabel.getText();

            Alert alert;

            if(roomNumber.isEmpty()||roomType.isEmpty()||roomStatus.isEmpty()||roomPrice.isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else{
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, roomNumber);
                preparedStatement.setString(2, roomType);
                preparedStatement.setString(3, roomStatus);
                preparedStatement.setString(4, roomPrice);

                preparedStatement.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Room successfully added!");
                alert.showAndWait();

                //Updates data in the table view
                availableRoomsUpdateDataAndPopulateTableView();

                //Selected Data will be cleared after successfully adding a room
                availableRoomsClearData();

            }


        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public ObservableList<RoomsData> availableRoomsRoomDataList(){
        ObservableList<RoomsData> roomDataList = FXCollections.observableArrayList();

        String sqlQuery = "select * from rooms";

        connection = databaseConnection.findConnection();

        try{
            RoomsData roomsData;

            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                roomsData = new RoomsData(resultSet.getInt("roomNumber"),
                                                    resultSet.getString("roomType"),
                                                    resultSet.getString("roomStatus"),
                                                    resultSet.getDouble("roomPrice"));

                roomDataList.add(roomsData);
            }


        } catch(Exception e){
            e.printStackTrace();
        }

        return roomDataList;
    }

    /**
     * Updates data in the room tableview
     */
    public void availableRoomsUpdateDataAndPopulateTableView(){
        contentsOfRoomDataList = availableRoomsRoomDataList();

        availableRoomsColumnRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        availableRoomsColumnRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        availableRoomsColumnStatus.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));
        availableRoomsColumnPrice.setCellValueFactory(new PropertyValueFactory<>("roomPrice"));

        availableRoomsTableView.setItems(contentsOfRoomDataList);
    }

    /**
     * Enables the selection of elements a specific room type from the combo box
     */
    public void availableRoomsRoomTypeComboBoxElements(){

        String roomTypesArray[] = comboBoxesData.getRoomType();

        List<String> roomTypesList = new ArrayList<>();

        for(String roomType : roomTypesArray){
            roomTypesList.add(roomType);
        }

        ObservableList roomTypeObservableList = FXCollections.observableArrayList(roomTypesList);
        availableRoomsRoomTypeComboBox.setItems(roomTypeObservableList);

    }

    /**
     * Enables the selection of elements a room status from the combo box
     */
    public void availableRoomsRoomStatusComboBoxElements(){
        String roomStatusArray[] = comboBoxesData.getRoomStatus();

        List<String> roomStatusList = new ArrayList<>();

        for(String roomStatus : roomStatusArray){
            roomStatusList.add(roomStatus);
        }

        ObservableList roomStatusObservableList = FXCollections.observableArrayList(roomStatusList);
        availableRoomsRoomStatusComboBox.setItems(roomStatusObservableList);
    }

    /**
     * Selected Data will be cleared after successfully adding a room
     */
    public void availableRoomsClearData(){
        availableRoomsRoomNumberLabel.setText("");
        availableRoomsRoomTypeComboBox.getSelectionModel().clearSelection();
        availableRoomsRoomStatusComboBox.getSelectionModel().clearSelection();
        availableRoomsPriceLabel.setText("");
    }


    /**
     * Called to initialize a controller after its root element has been completely processed.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        availableRoomsRoomTypeComboBoxElements();
        availableRoomsRoomStatusComboBoxElements();
        availableRoomsUpdateDataAndPopulateTableView();

    }


}
