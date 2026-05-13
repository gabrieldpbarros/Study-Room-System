package events;
import interfaces.INotificationEvent;
import observers.User;


public class NotificationEvent implements INotificationEvent{
    private String roomType;
    private int roomId;
    private String date;
    private int time;
    private String procedure;
    private User previousOccupant;
    private User newOccupant;

    public NotificationEvent(String roomType, int roomId, String date, int time, String procedure, User previousOccupant, User newOccupant) {
        this.roomType = roomType;
        this.roomId = roomId;
        this.date = date;
        this.time = time;
        this.procedure = procedure;
        this.previousOccupant = previousOccupant;
        this.newOccupant = newOccupant;
    }

    public String getRoomType() {
        return this.roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public User getPreviousOccupant() {
        return this.previousOccupant;
    }

    public void setPreviousOccupant(User previousOccupant) {
        this.previousOccupant = previousOccupant;
    }

    public User getNewOccupant() {
        return newOccupant;
    }

    public void setNewOccupant(User newOccupant) {
        this.newOccupant = newOccupant;
    }

    @Override
    public String getEventType() {
        return this.procedure;
    }

    @Override
    public String getMessage() {
        return "Notificação da Sala " + roomId + " no dia " + date + ": " + procedure;
    }

    @Override
    public int getTargetId() {
        return this.roomId;
    }
}
