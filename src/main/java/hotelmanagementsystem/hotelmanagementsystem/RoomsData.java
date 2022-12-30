package hotelmanagementsystem.hotelmanagementsystem;

public class RoomsData {
    private Integer roomNumber;
    private String roomType;
    private String roomStatus;
    private Double roomPrice;

    public RoomsData(Integer roomNumber, String roomType, String roomStatus, Double roomPrice){
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomStatus = roomStatus;
        this.roomPrice = roomPrice;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }
}
