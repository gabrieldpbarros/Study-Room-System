package rooms;

import observers.User;

public class LabRoom extends BaseRoom {
    private String roomType = "Laboratório";

    public LabRoom(int id) {
        super(id);
    }

    @Override
    String getRoomType() {
        return this.roomType;
    }
}
