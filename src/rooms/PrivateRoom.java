package rooms;

import observers.User;

public class PrivateRoom extends BaseRoom {
    private String roomType = "Sala individual";

    public PrivateRoom(int id) {
        super(id);
    }

    @Override
    String getRoomType() {
        return this.roomType;
    }
}
