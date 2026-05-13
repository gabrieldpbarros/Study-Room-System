package rooms;
import observers.User;

public class GroupRoom extends BaseRoom {
    private String roomType = "Sala para grupos";

    public GroupRoom(int id) {
        super(id);
    }

    @Override
    String getRoomType() {
        return this.roomType;
    }
}
