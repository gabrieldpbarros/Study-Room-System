package factories;

import rooms.BaseRoom;
import rooms.GroupRoom;
import rooms.LabRoom;
import rooms.PrivateRoom;

public class RoomFactory {
    private RoomFactory(){}

    public static BaseRoom createRoom(int roomId, int roomType) {
        if (roomType == 2) {
            return new LabRoom(roomId);
        } else if (roomType == 3) {
            return new PrivateRoom(roomId);
        }

        return new GroupRoom(roomId);
    }

    public static GroupRoom createGroup(int roomId) {
        return new GroupRoom(roomId);
    }
    public static LabRoom createLab(int roomId) {
        return new LabRoom(roomId);
    }
    public static PrivateRoom createPrivate(int roomId) {
        return new PrivateRoom(roomId);
    }
}
