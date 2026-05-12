package interfaces;

public interface IScheduleSystem {
    void setPolicy();
    void newUser();
    void newRoom();
    void makeSchedule();
    void cancelSchedule();
    void getFreeRooms();
    void getBusyRooms();
    void reportRooms();
}
