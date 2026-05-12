package rooms;

public class LabRoom extends BaseRoom {
    private String roomType = "Laboratório";

    public LabRoom(int id) {
        super(id);
    }

    @Override
    String getStatus() {
        return "";
    }

    @Override
    void setStatus(String newStatus) {

    }
}
