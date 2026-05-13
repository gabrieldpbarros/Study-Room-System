package interfaces;
import observers.User;

public interface ISchedule {
    User getOccupantAt(String date, int time);
    void insertReservation(String date, int time, User user);
    void removeReservation(String date,int ime, User user);
    boolean requestReservation(String date, int time, User user, IPolicyStrategy policy);
    boolean isFullWithin(String begin, String end);
}
