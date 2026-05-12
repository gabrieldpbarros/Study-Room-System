package rooms;
import interfaces.IObserver;
import interfaces.IPolicyStrategy;
import interfaces.ISchedule;
import observers.User;
import schedule.Schedule;
import java.util.ArrayList;
import java.util.List;

abstract public class BaseRoom {
    protected int id;
    protected List<IObserver> observers;
    protected ISchedule schedule;

    public BaseRoom(int id) {
        this.id = id;
        this.observers = new ArrayList<>();
        this.schedule = new Schedule();
    }

    protected void notifyObservers(String roomType) {
        for (IObserver observer : this.observers)
            observer.update(roomType, "placeholder",this.id);
    }

    public void addObserver(IObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    public ISchedule getSchedule() {
        return this.schedule;
    }

    public void updateSchedule(ISchedule newSchedule) {
        this.schedule = newSchedule;
    }

    public boolean addReservation(String date, int time, User user, IPolicyStrategy policy) {
        return this.schedule.requestReservation(date, time, user, policy);
    }

    abstract String getStatus();
    abstract void setStatus(String newStatus);
}
