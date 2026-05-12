package rooms;

public class GroupRoom extends BaseRoom {
    private String roomType = "Sala para grupos";

    public GroupRoom(int id) {
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
