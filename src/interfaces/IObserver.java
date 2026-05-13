package interfaces;

public interface IObserver {
    void update(String roomType, int time, int id, String procedure);
    void update(String roomType, int time, int id, String procedure, IObserver user);
    void update(String roomType, int time, int id, String procedure, IObserver user, boolean hasPriority);
}
