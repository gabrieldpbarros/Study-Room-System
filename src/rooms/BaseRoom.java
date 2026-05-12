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

    protected void notifyObservers(String roomType, String procedure) {
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
        var currentOccupant = schedule.getOccupantAt(date, time);
        if (schedule.requestReservation(date, time, user, policy)) {
            if (currentOccupant != null) {
                // something
            }

            this.schedule.insertReservation(date, time, user);
            //this.notifyObservers();
            return true;
        }
        return false;
    }

    public boolean cancelReservation(String date, int time, User user) {
        var currentOccupant = this.schedule.getOccupantAt(date, time);
        if (currentOccupant.equals(user)) {
            this.schedule.removeReservation(date, time, user);
            //this.notifyObservers();
            return true;
        }
        return false;
    }

    abstract String getStatus();
    abstract void setStatus(String newStatus);
}
