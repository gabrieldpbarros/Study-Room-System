package interfaces;
import observers.User;

public interface ISchedule {
    User getOccupantAt(String date, int time);
    void insertReservation(String date, int time, User user);
    boolean requestReservation(String date, int time, User user, IPolicyStrategy policy);
}
