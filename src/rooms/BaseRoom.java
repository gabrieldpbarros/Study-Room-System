package rooms;
import events.NotificationEvent;
import interfaces.INotificationEvent;
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

    protected void notifyObservers(INotificationEvent event) {
        for (IObserver observer : this.observers)
            observer.update(event);
    }

    protected void parseNotification(User previousOccupant, User newOccupant, String date, int time) {
        INotificationEvent event;
        if (previousOccupant == null)
            event = new NotificationEvent(this.getRoomType(), this.id, date, time, "RESERVA", previousOccupant, newOccupant);
        else if (newOccupant == null)
            event = new NotificationEvent(this.getRoomType(), this.id, date, time, "CANCELAMENTO", previousOccupant, newOccupant);
        else
            event = new NotificationEvent(this.getRoomType(), this.id, date, time, "SOBRESCRITA", previousOccupant, newOccupant);
        this.notifyObservers(event);

    }

    public int getId() {
        return this.id;
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
            this.schedule.insertReservation(date, time, user);
            this.parseNotification(currentOccupant, user, date, time); // currentOccupant == null
            return true;
        }
        return false;
    }

    public boolean cancelReservation(String date, int time, User user) {
        var currentOccupant = this.schedule.getOccupantAt(date, time);
        if (user.equals(currentOccupant)) {
            this.schedule.removeReservation(date, time, user);
            this.parseNotification(currentOccupant, null, date, time);
            return true;
        }
        return false;
    }

    abstract String getRoomType();
}
