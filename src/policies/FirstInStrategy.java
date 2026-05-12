package policies;
import interfaces.IPolicyStrategy;
import observers.User;

public class FirstInStrategy implements IPolicyStrategy {
    @Override
    public void applyPolicy() {

    }

    @Override
    public boolean canReserve(User reserver, User current) {
        return false;
    }
}
