package rooms;
import interfaces.IObserver;
import java.util.List;
import java.util.Map;

abstract public class BaseRoom {
    protected int id;
    protected List<IObserver> observers;
    protected Map<String, Map<Integer, IObserver>> schedule;

    public void addObserver(IObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    protected void notifyObservers(String roomType) {
        for (IObserver observer : this.observers)
            observer.update(roomType, "placeholder",this.id);
    }

    abstract String getStatus();
    abstract void setStatus(String newStatus);
}
