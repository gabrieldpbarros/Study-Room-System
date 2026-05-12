package schedule;
import interfaces.IPolicyStrategy;
import interfaces.ISchedule;
import observers.User;

import java.util.HashMap;
import java.util.Map;

public class Schedule implements ISchedule {
    private Map<String, Map<Integer, User>> schedule;

    public Schedule() {
        this.schedule = new HashMap<>();
    }

    @Override
    public User getOccupantAt(String date, int time) {
        return this.schedule.get(date).get(time);
    }

    @Override
    public void insertReservation(String date, int time, User user) {
        var dailySchedule = this.schedule.computeIfAbsent(date, k -> new HashMap<>());
        dailySchedule.put(time, user);
    }

    @Override
    public boolean requestReservation(String date, int time, User user, IPolicyStrategy policy) {
        if (!this.schedule.containsKey(date))
            // CASO 1: Data sem reservas
            this.insertReservation(date, time, user);
        else {
            // CASO 2: Data com reservas
            var dateSchedule = this.schedule.get(date);
            if (!dateSchedule.containsKey(time))
                // CASO 2.1: Sem reservas no horário
                this.insertReservation(date, time, user);
            else {
                // CASO 2.2: Reserva previamente feita naquele horário
                var occupant = this.getOccupantAt(date, time);
                if (policy.canReserve(user, occupant))
                    // Política permite
                    this.insertReservation(date, time, user);
                else
                    // Política não permite
                    return false;
            }
        }
        return true;
    }
}
