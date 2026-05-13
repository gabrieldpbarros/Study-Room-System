package system;
import interfaces.IPolicyStrategy;
import interfaces.ISchedule;
import interfaces.IScheduleSystem;
import observers.User;
import rooms.BaseRoom;
import rooms.GroupRoom;
import java.util.ArrayList;
import java.util.HashMap;
import factories.RoomFactory;
import factories.UserFactory;

import static java.lang.System.out;

public class ScheduleSystem implements IScheduleSystem {
    private IPolicyStrategy systemPolicy;
    private ArrayList<User> userList = new ArrayList<>();
    private HashMap<Integer, BaseRoom> roomHash = new HashMap<>();
    private ScheduleSystem(){}
    private static ScheduleSystem instance;

    public static ScheduleSystem getInstance(){
        if(instance == null){
            instance = new ScheduleSystem();
        }
        return instance;
    }

    @Override
    public void setPolicy(IPolicyStrategy newPolicy) {
        systemPolicy = newPolicy;
    }

    @Override
    public void newUser(String username, String role) {
        int userId = userList.size();
        User myUser = UserFactory.createUser(userId, username, role);
        userList.add(myUser);
        out.print("Criacao de Sala Realizada Com Sucesso!");
    }

    @Override
    public void newRoom(int roomId, int roomType) {
        BaseRoom targetRoom = roomHash.get(roomId);
        if(targetRoom!= null){
            out.print("Criacao de Sala Fracassou: Duplicata Presente");
            return;
        }

        BaseRoom myRoom = RoomFactory.createRoom(roomId, roomType);
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
    public HashMap<Integer, BaseRoom> getRooms(String begin, String end, boolean fullOnly) {
        HashMap<Integer, BaseRoom> emptyRooms = new HashMap<>();
        for (BaseRoom room : roomHash.values()) {
            ISchedule schedule = room.getSchedule();
            boolean isFull = schedule.isFullWithin(begin, end);
            if ((!fullOnly && isFull) || (fullOnly && !isFull)) continue;
            int roomId = room.getId();
            emptyRooms.put(roomId, room);
        }

        return emptyRooms;
    }

    @Override
    public void reportRooms(String begin, String end, boolean fullOnly) {
        HashMap<Integer, BaseRoom> emptyRooms = getRooms(begin, end, fullOnly);
        String criteriaStr = fullOnly ? "cheias" : "vazias";
        if(emptyRooms.isEmpty()){
            out.printf("Nao existem salas " + criteriaStr + " para o periodo selecionado.");
            return;
        };

        out.print("As salas disponiveis sao:");
        for(BaseRoom room : roomHash.values()){
            out.printf("Sala " + room.getId());
        }
    }
}
