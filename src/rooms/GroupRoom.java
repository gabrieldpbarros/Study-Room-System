package rooms;

import observers.User;

public class GroupRoom extends BaseRoom {
    private String roomType = "Sala para grupos";

    public GroupRoom(int id) {
        super(id);
    }

    @Override
    void parseNotification(User previousOccupant, User newOccupant) {

    }

    @Override
    String getStatus() {
        return "";
    }

    @Override
    void setStatus(String newStatus) {

    }
}
