package rooms;
import interfaces.IObserver;
import java.util.List;
import java.util.Map;

abstract public class BaseSala {
    protected int ID;
    protected List<IObserver> observers;
    protected Map<String, Map<Integer, IObserver>> schedule;

    abstract String getStatus();
    abstract void setStatus(String newStatus);
}
