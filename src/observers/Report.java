package observers;
import interfaces.IObserver;

public class Report implements IObserver {
    private int id;

    public Report(int id) {
        this.setId(id);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    @Override
    public void update(String roomType, String period, int id) {

    }
}
