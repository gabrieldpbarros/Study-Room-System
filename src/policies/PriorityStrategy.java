package policies;
import interfaces.IPolicyStrategy;
import observers.User;

public class PriorityStrategy implements IPolicyStrategy {
    @Override
    public void applyPolicy() {

    }

    @Override
    public boolean canReserve(User reserver, User current) {
        return false;
    }
}
