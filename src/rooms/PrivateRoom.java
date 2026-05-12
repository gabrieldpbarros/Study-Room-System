package rooms;

public class PrivateRoom extends BaseRoom {
    private String roomType = "Sala individual";

    public PrivateRoom(int id) {
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
