package interfaces;

import observers.User;
import rooms.BaseRoom;

import java.util.HashMap;

public interface IScheduleSystem {
    void setPolicy(IPolicyStrategy newPolicy);
    void newUser(String username, String role);
    void newRoom(int roomId);
    void makeSchedule(User user, Integer roomId, String date, int time);
    void cancelSchedule(User user, Integer roomId, String date, int time);
    HashMap<Integer, BaseRoom> getFreeRooms(String strt, int timeStart, String fnsh, int timeEnd);
    HashMap<Integer, BaseRoom> getBusyRooms(String begin, int timeStart, String end, int timeEnd);
    void reportRooms();
}
