package rooms;
import interfaces.IObserver;
import java.util.List;
import java.util.Map;

abstract public class BaseSala {
    protected int id;
    protected List<IObserver> observers;
    protected Map<String, Map<Integer, IObserver>> schedule;

    public void addObserver(IObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    protected void notifyObservers(String tipoSala) {
        for (IObserver observer : this.observers)
            observer.update(tipoSala, "placeholder",this.id);
    }

    abstract String getStatus();
    abstract void setStatus(String newStatus);
}
