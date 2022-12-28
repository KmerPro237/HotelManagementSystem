package hotelmanagementsystem.hotelmanagementsystem;

public class ComboBoxesData {
    private String roomType[];
    private String roomStatus[];
    private String paymentStatus[];


    public String[] getRoomType() {
        roomType = new String[]{"Single-room", "Double-room", "Triple-room", "Deluxe", "King-room", "Business-suite", "Family-suite", "Other"};

        return roomType;
    }

    public String[] getRoomStatus() {
        roomStatus = new String[]{"Available", "Reserved", "Occupied", "Not-available", "Being-serviced", "Other"};

        return roomStatus;
    }

    public String[] getPaymentStatus() {
        paymentStatus = new String[]{"Unpaid", "Pending", "Completed", "Failed", "Declined", "Canceled", "Abandoned", "Setting", "Settled", "Refunded", "Other"};

        return paymentStatus;
    }
}
