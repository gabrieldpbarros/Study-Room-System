package interfaces;
import observers.User;

public interface IPolicyStrategy {
    void applyPolicy();
    boolean canReserve(User reserver, User current);
}
