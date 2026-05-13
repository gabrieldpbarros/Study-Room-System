package interfaces;

public interface INotificationEvent {
    String getEventType();
    String getMessage();
    int getTargetId();
}
