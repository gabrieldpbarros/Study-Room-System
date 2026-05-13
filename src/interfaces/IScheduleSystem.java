package interfaces;

import observers.User;
import rooms.BaseRoom;

import java.util.HashMap;

public interface IScheduleSystem {
    void setPolicy(IPolicyStrategy newPolicy);
    void newUser(String username, String role);
    void newRoom(int roomId, int roomType);
    void makeSchedule(User user, Integer roomId, String date, int time);
    void cancelSchedule(User user, Integer roomId, String date, int time);
    HashMap<Integer, BaseRoom> getRooms(String begin, String end, boolean fullOnly);
    void reportRooms(String begin, String end, boolean fullOnly);
}
