package interfaces;
import observers.User;

public interface IPolicyStrategy {
    boolean canReserve(User reserver, User current);
}
