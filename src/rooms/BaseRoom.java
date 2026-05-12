package rooms;
import interfaces.IObserver;
import interfaces.IPolicyStrategy;
import observers.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class BaseRoom {
    protected int id;
    protected List<IObserver> observers;
    protected Map<String, Map<Integer, User>> schedule;

    public BaseRoom(int id) {
        this.id = id;
        this.observers = new ArrayList<>();
        this.schedule = new HashMap<>();
    }

    private User getOccupantAt(String date, int time) {
        return this.schedule.get(date).get(time);
    }

    private void insertReservation(String date, int time, User user) {
        var dailySchedule = this.schedule.computeIfAbsent(date, k -> new HashMap<>());
        dailySchedule.put(time, user);
    }

    protected void notifyObservers(String roomType) {
        for (IObserver observer : this.observers)
            observer.update(roomType, "placeholder",this.id);
    }

    public void addObserver(IObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    public Map<String, Map<Integer, User>> getSchedule() {
        return this.schedule;
    }

    public void updateSchedule(Map<String, Map<Integer, User>> newSchedule) {
        this.schedule = newSchedule;
    }

    public boolean addReservation(String date, int time, User user, IPolicyStrategy policy) {
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

    abstract String getStatus();
    abstract void setStatus(String newStatus);
}
