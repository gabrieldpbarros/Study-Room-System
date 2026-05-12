package policies;
import interfaces.IPolicyStrategy;
import observers.User;

public class PriorityStrategy implements IPolicyStrategy {
    @Override
    public boolean canReserve(User reserver, User current) {
        if (current.getRole().equals("docente")) return false;
        else return reserver.getRole().equals("docente");
    }
}
