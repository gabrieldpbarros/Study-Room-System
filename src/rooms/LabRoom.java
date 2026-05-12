package rooms;

import observers.User;

public class LabRoom extends BaseRoom {
    private String roomType = "Laboratório";

    public LabRoom(int id) {
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
