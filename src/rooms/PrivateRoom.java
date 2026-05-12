package rooms;

import observers.User;

public class PrivateRoom extends BaseRoom {
    private String roomType = "Sala individual";

    public PrivateRoom(int id) {
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
