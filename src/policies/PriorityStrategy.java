package policies;
import interfaces.IPolicyStrategy;
import observers.User;

public class PriorityStrategy implements IPolicyStrategy {
    @Override
    public boolean canReserve(User reserver, User current) {
        if (current.getRole().equals("docente")) return false;
        else if (reserver.getRole().equals("docente")) return true;
        else return false;
    }
}
