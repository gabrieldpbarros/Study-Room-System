package observers;
import interfaces.IObserver;

public class User implements IObserver {
    private int id;
    private String name;
    private String role;

    public User(int id, String name, String role) {
        this.setId(id);
        this.name = name;
        this.role = role;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public String getRole() { return this.role; }

    @Override
    public void update(String roomType, String period, int id) {
        System.out.printf("ATUALIZAÇÃO DA RESERVA DE " + this.name + ", ID: " + this.id);
        System.out.printf("Sala do tipo " + roomType + ", ID: " + id + " liberada para o período " + period + ".");
    }
}
