package schedule;
import interfaces.IPolicyStrategy;
import interfaces.ISchedule;
import observers.User;
import java.util.HashMap;
import java.util.Map;

public class Schedule implements ISchedule {
    private Map<String, Map<Integer, User>> calendar;

    public Schedule() {
        this.calendar = new HashMap<>();
    }

    @Override
    public User getOccupantAt(String date, int time) {
        if (!calendar.containsKey(date)) return null;
        return calendar.get(date).get(time);
    }

    @Override
    public void insertReservation(String date, int time, User user) {
        var dailySchedule = this.calendar.computeIfAbsent(date, k -> new HashMap<>());
        dailySchedule.put(time, user);
    }

    @Override
    public void removeReservation(String date, int time, User user) {
        var dailySchedule = this.calendar.get(date);
        dailySchedule.remove(time);
    }

    @Override
    public boolean requestReservation(String date, int time, User user, IPolicyStrategy policy) {
        var occupant = this.getOccupantAt(date, time);
        return occupant == null || policy.canReserve(user, occupant);
    }
}
