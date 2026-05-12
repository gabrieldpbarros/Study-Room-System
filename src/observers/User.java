package observers;
import interfaces.IObserver;

public class User implements IObserver {
    private int id;
    private String role;

    @Override
    public void update(String roomType, String period, int id) {
        System.out.printf("Sala do tipo " + roomType + ", ID: " + id + " liberada para o período " + period + ".");
    }
}
