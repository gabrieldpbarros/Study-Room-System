package interfaces;

import observers.User;

public interface IScheduleSystem {
    void setPolicy(IPolicyStrategy newPolicy);
    void newUser();
    void newRoom(int roomId);
    void makeSchedule(User user, Integer roomId, String date, int time);
    void cancelSchedule(User user, Integer roomId, String date, int time);
    void getFreeRooms();
    void getBusyRooms();
    void reportRooms();
}
