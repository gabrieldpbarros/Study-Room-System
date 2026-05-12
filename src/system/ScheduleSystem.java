package system;
import interfaces.IPolicyStrategy;
import interfaces.IScheduleSystem;
import observers.User;
import rooms.BaseRoom;
import rooms.GroupRoom;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.System.out;

public class ScheduleSystem implements IScheduleSystem {
    private IPolicyStrategy systemPolicy;
    private ArrayList<User> userList = new ArrayList<>();
    private HashMap<Integer, BaseRoom> roomHash = new HashMap<>();
    @Override
    public void setPolicy(IPolicyStrategy newPolicy) {
        systemPolicy = newPolicy;
    }

    @Override
    public void newUser() {
        int userId = userList.size();
        User myUser = new User();
        userList.add(myUser);
        out.print("Criacao de Sala Realizada Com Sucesso!");
    }

    @Override
    public void newRoom(int roomId) {
        BaseRoom targetRoom = roomHash.get(roomId);
        if(targetRoom!= null){
            out.print("Criacao de Sala Fracassou: Duplicata Presente");
            return;
        }

        GroupRoom myRoom = new GroupRoom(roomId); // factory entra possivelmente aqui (?)
        roomHash.put(roomId, myRoom);
        out.print("Criacao de Sala Realizada Com Sucesso!");
    }

    @Override
    public void makeSchedule(User user, Integer roomId, String date, int time) {
        BaseRoom targetRoom = roomHash.get(roomId);
        if (targetRoom == null) {
            out.print("Agendamento Fracassou: Sala Inexistente");
            return;
        }

        boolean success = targetRoom.addReservation(date, time, user, systemPolicy);
        if(success) out.print("Agendamento Realizado Com Sucesso!");
        else out.print("Agendamento Fracassou: Sala Ocupada.");
    }

    @Override
    public void cancelSchedule(User user, Integer roomId, String date, int time) {
        BaseRoom targetRoom = roomHash.get(roomId);
        if (targetRoom == null) {
            out.print("Cancelamento Fracassou: Sala Inexistente");
            return;
        }

        boolean success = targetRoom.cancelReservation(date, time, user);
        if(success) out.print("Cancelamento Realizado Com Sucesso!");
        else out.print("Cancelamento Fracassou: Usuario Nao Possui a Sala");
    }

    @Override
    public void getFreeRooms() {

    }

    @Override
    public void getBusyRooms() {

    }

    @Override
    public void reportRooms() {

    }
}
