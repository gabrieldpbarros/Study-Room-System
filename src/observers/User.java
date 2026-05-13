package observers;
import events.NotificationEvent;
import interfaces.INotificationEvent;
import interfaces.IObserver;

public class User implements IObserver {
    private int id;
    private String name;
    private String role;

    public User(int id, String name, String role) {
        this.setId(id);
        this.name = name;
        this.role = role;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public String getRole() { return this.role; }

    public String getName() {
        return name;
    }

    @Override
    public void update(INotificationEvent event) {
        if (event instanceof NotificationEvent roomEvent) {
            boolean isPrevious = this.equals(roomEvent.getPreviousOccupant());
            boolean isNew = this.equals(roomEvent.getNewOccupant());
            String roomInfo = roomEvent.getRoomType() + " (ID: " + roomEvent.getTargetId() + ")";
            String dateTime = "dia " + roomEvent.getDate() + " às " + roomEvent.getTime() + "h";

            switch (roomEvent.getEventType()) {
                case "RESERVA":
                    if (isNew)
                        System.out.printf("[SISTEMA para %s]: Sucesso! Você reservou a %s para o %s.\n", this.name, roomInfo, dateTime);
                    else
                        System.out.printf("[ALERTA GERAL para %s]: A %s foi reservada no %s.\n", this.name, roomInfo, dateTime);
                    break;

                case "CANCELAMENTO":
                    if (isPrevious)
                        System.out.printf("[SISTEMA para %s]: Confirmação. Sua reserva na %s para o %s foi cancelada.\n", this.name, roomInfo, dateTime);
                    else
                        System.out.printf("[ALERTA GERAL para %s]: Um horário na %s foi LIBERADO no %s!\n", this.name, roomInfo, dateTime);
                    break;

                case "SOBRESCRITA":
                    if (isPrevious)
                        System.out.printf("[AVISO URGENTE para %s]: Sua reserva na %s para o %s foi CANCELADA por prioridade de Docente.\n", this.name, roomInfo, dateTime);
                    else if (isNew)
                        System.out.printf("[SISTEMA para %s]: Sucesso! Você utilizou sua prioridade e reservou a %s para o %s.\n", this.name, roomInfo, dateTime);
                    else
                        System.out.printf("[ALERTA GERAL para %s]: Ocorreu uma sobrescrita por prioridade na %s no %s.\n", this.name, roomInfo, dateTime);
                    break;

                default:
                    System.out.println("[SISTEMA]: Notificação desconhecida recebida.");
            }
        }
    }
}
