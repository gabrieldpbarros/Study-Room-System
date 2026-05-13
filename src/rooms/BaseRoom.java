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
            observer.update(roomType, "placeholder", this.id);
    }

    // Sobrecarga 1
    protected void notifyObservers(String roomType, String procedure, User specificUser) {
        for (IObserver observer : this.observers) {
            if (!observer.equals(specificUser)) {
                observer.update(roomType, "placeholder", this.id);
            } else {
                // something
                if (procedure.equals("RESERVA")) {
                    observer.update(roomType, "placeholder", this.id);
                } else if (procedure.equals("CANCELAMENTO")) {

                }
            }
        }
    }

    // Sobrecarga 2
    protected void notifyObservers(String roomType, String procedure, User previous, User current) {
        for (IObserver observer : this.observers) {
            if (observer.equals(previous)) {
                // lost
            } else if (observer.equals(current)) {
                // reserved
            } else
                observer.update(roomType, "placeholder", this.id);
        }
    }

    protected void parseNotification(User previousOccupant, User newOccupant) {
        if (previousOccupant == null)
            this.notifyObservers(this.getRoomType(), "RESERVA", newOccupant);
        else if (newOccupant == null)
            this.notifyObservers(this.getRoomType(), "CANCELAMENTO", previousOccupant);
        else if (!previousOccupant.equals(newOccupant))
            this.notifyObservers(this.getRoomType(), "SOBRESCRITA", previousOccupant, newOccupant);
        else
            this.notifyObservers(this.getRoomType());

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
            this.parseNotification(currentOccupant, user); // currentOccupant == null
            return true;
        }
        this.parseNotification(currentOccupant, currentOccupant);
        return false;
    }

    public boolean cancelReservation(String date, int time, User user) {
        var currentOccupant = this.schedule.getOccupantAt(date, time);
        if (user.equals(currentOccupant)) {
            this.schedule.removeReservation(date, time, user);
            this.parseNotification(currentOccupant, null);
            return true;
        }
        return false;
    }

    abstract String getRoomType();
}
